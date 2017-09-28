package dk.rim.is.abt.nymf

import dk.rim.is.abt.util.BatchController
import dk.rim.is.abt.util.GenericDao
import dk.rim.is.abt.nymf.entity.*
import dk.rim.is.api.enums.BatchStatus
import dk.rim.is.api.enums.BatchTargetSystem
import dk.rim.is.api.enums.FordringsRelation
import dk.rim.is.api.enums.ServiceName
import dk.rim.is.api.enums.XmlType
import dk.skat.begrebsmodel._2009._01._15.MFAktionStatusKodeType
import dk.skat.begrebsmodel._2009._01._15.MFAktionKodeType
import dk.skat.begrebsmodel._2009._01._15.DMIFordringArtType
import org.junit.After
import org.junit.Before
import org.junit.Test

import java.util.function.Predicate
import java.time.LocalDateTime

import static dk.rim.is.abt.util.SpringContextNymf.buildDao

/**
 * Created by wos on 12.05.2017.
 */
class SendToPSRMTest {

	private static final String JOB_DESCRIPTION_SEND_TO_PSRM = "SendToPSRM"

	private static final int LEVERANCE_ID_1 = new Random().nextInt()
	private static final int LEVERANCE_ID_2 = new Random().nextInt()

	private static final long AKTION_ID_1 = new Random().nextInt()
	private static final long AKTION_ID_2 = new Random().nextInt()

	private static final String TRANSAKTIONS_ID_1 = "90bf52f4-c0ee-4cdc-9139-b4ce171ebc9b"
	private static final String TRANSAKTIONS_ID_2 = "181c9b4f-a5a5-4ccd-bc22-2acd7c2ba444"

	private static final String FORDRINGHAVER_REFERANCE_1 = "529687 FordringHaverReferance"
	private static final String FORDRINGHAVER_REFERANCE_2 = "412686 FordringHaverReferance"

	/* Variable for test */
	private static final BatchStatus BATCH_STATUS1 = BatchStatus.KLAR_TIL_SEND
	private static final BatchStatus BATCH_STATUS2 = BatchStatus.KLAR_TIL_SEND

	private static final BatchTargetSystem BATCH_TARGET_SYSTEM1 = BatchTargetSystem.PSRM
	private static final BatchTargetSystem BATCH_TARGET_SYSTEM2 = BatchTargetSystem.EXMF

	private static final Boolean HOERING_FLAG1 = false
	private static final Boolean HOERING_FLAG2 = false

	private static final MFAktionStatusKodeType STATUS1 = MFAktionStatusKodeType.MODTAGET
	private static final MFAktionStatusKodeType STATUS2 = MFAktionStatusKodeType.MODTAGET
	/* End Variables for test */

	private GenericDao<LeveranceEntity> leveranceEntityDao = buildDao(LeveranceEntity.class)
	private GenericDao<SeenLeverance> seenLeveranceDao = buildDao(SeenLeverance.class)
	private GenericDao<FordringsAktionState> fordringsAktionStateDao = buildDao(FordringsAktionState.class)
	private GenericDao<FordringsAktionData> fordringsAktionDataDao = buildDao(FordringsAktionData.class)
	private GenericDao<FordringsAktion> fordringsAktionDao = buildDao(FordringsAktion.class)

	private long fordringsAktion1Id
	private long fordringsAktion2Id

	private long leveranceEntity1Id
	private long leveranceEntity2Id

	private long fordringsAktionStateId1
	private long fordringsAktionStateId2

	@Test
	void testBatchReturnCode() {

		//TODO: MICW: Investigate that return on this job is not SENDT but AFVIST with ERROR 900
		//FordringsAktionState{id=468, status=AFVIST, hoeringFlag=false, errorCode=900, statusAendretDato=2017-05-24T14:02:31.013, batchStatus=AFVIST, batchTargetSystem=PSRM, batchError=SYSTEM_FEJL}
		def jobReturnCode = BatchController.runNyMFJob(JOB_DESCRIPTION_SEND_TO_PSRM)
        assert jobReturnCode.executionStatus == "SUCCESS"
		FordringsAktionState aktionState = fordringsAktionStateDao.getBy{entity -> fordringsAktionStateId1.equals(entity.getId())}.get(0)
        assert aktionState.getBatchStatus().equals(BatchStatus.SENDT)
        assert aktionState.getHoeringFlag().equals(false)
        assert aktionState.getStatus().equals(MFAktionStatusKodeType.MODTAGET)
        assert aktionState.getBatchTargetSystem().equals(BatchTargetSystem.PSRM)

        aktionState = fordringsAktionStateDao.getBy{entity -> fordringsAktionStateId2.equals(entity.getId())}.get(0)
        assert aktionState.getBatchStatus().equals(BatchStatus.KLAR_TIL_SEND)
        assert aktionState.getHoeringFlag().equals(false)
        assert aktionState.getStatus().equals(MFAktionStatusKodeType.MODTAGET)
        assert aktionState.getBatchTargetSystem().equals(BatchTargetSystem.EXMF)

		println "testEnd"
	}

	@Before
	void setupDB() {
		LeveranceState leveranceState1 = createLeveranceState()
		LeveranceState leveranceState2 = createLeveranceState()

		FordringsAktionState fordringsAktionState1 = createFordringsAktionState(BATCH_STATUS1, STATUS1, BATCH_TARGET_SYSTEM1, HOERING_FLAG1)
		FordringsAktionState fordringsAktionState2 = createFordringsAktionState(BATCH_STATUS2, STATUS2, BATCH_TARGET_SYSTEM2, HOERING_FLAG2)

		TransaktionHistorik transaktionHistorik1 = createTransaktionHistorik(TRANSAKTIONS_ID_1)
		TransaktionHistorik transaktionHistorik2 = createTransaktionHistorik(TRANSAKTIONS_ID_2)

		FordringIndberetHistorik fordringIndberetHistorik1 = createFordringIndberetHistorik(LEVERANCE_ID_1, TRANSAKTIONS_ID_1, FORDRINGHAVER_REFERANCE_1, AKTION_ID_1, transaktionHistorik1)
		FordringIndberetHistorik fordringIndberetHistorik2 = createFordringIndberetHistorik(LEVERANCE_ID_2, TRANSAKTIONS_ID_2, FORDRINGHAVER_REFERANCE_2, AKTION_ID_2, transaktionHistorik2)

		LeveranceEntity leveranceEntity1 = creteLeveranceEntity(LEVERANCE_ID_1, fordringIndberetHistorik1, leveranceState1)
		LeveranceEntity leveranceEntity2 = creteLeveranceEntity(LEVERANCE_ID_2, fordringIndberetHistorik2, leveranceState2)

		leveranceEntityDao.save(leveranceEntity1)
		leveranceEntityDao.save(leveranceEntity2)

		FordringsAktionData fordringsAktionData1 = new FordringsAktionData()
		fordringsAktionData1.setData("<?xml version=\"1.0\" encoding=\"UTF-8\"?><sbm:FordringAktion xmlns:sbm=\"http://skat.dk/begrebsmodel/2009/01/15/\"><sbm:MFAktionKode>OPRETFORDRING</sbm:MFAktionKode><sbm:DMIFordringHaverID>1229</sbm:DMIFordringHaverID><sbm:AktionValg><sbm:OpretFordringAktion><sbm:MFOpretFordringStruktur><sbm:DMIFordringFordringArtKode>INDR</sbm:DMIFordringFordringArtKode><sbm:DMIFordringTypeKode>LIMEDIE</sbm:DMIFordringTypeKode><sbm:DMIFordringFordringHaverRef>" + FORDRINGHAVER_REFERANCE_1 + "</sbm:DMIFordringFordringHaverRef><sbm:DMIFordringStiftelseTidspunkt>2016-03-01</sbm:DMIFordringStiftelseTidspunkt><sbm:DMIFordringForfaldDato>2016-03-01</sbm:DMIFordringForfaldDato><sbm:DMIFordringSRBDato>2016-03-01</sbm:DMIFordringSRBDato><sbm:FordringBeløbStruktur><sbm:ValutaKode>DKK</sbm:ValutaKode><sbm:DMIFordringBeløb>1200</sbm:DMIFordringBeløb></sbm:FordringBeløbStruktur><sbm:FordringPeriodeStruktur><sbm:DMIFordringPeriodeFraDato>2016-03-01</sbm:DMIFordringPeriodeFraDato><sbm:DMIFordringPeriodeTilDato>2016-08-31</sbm:DMIFordringPeriodeTilDato></sbm:FordringPeriodeStruktur><sbm:FordringOprindeligBeløbStruktur><sbm:ValutaKode>DKK</sbm:ValutaKode><sbm:EFIFordringOprindeligBeløb>1200</sbm:EFIFordringOprindeligBeløb></sbm:FordringOprindeligBeløbStruktur><sbm:SagsbemærkningSamling/><sbm:DokumentSamling/><sbm:DMIFordringHaverID>1229</sbm:DMIFordringHaverID><sbm:FordringHæftelseSamling><sbm:MFHæftelseStruktur><sbm:MFKundeStruktur><sbm:Valg><sbm:PersonCPRNummer>0505784634</sbm:PersonCPRNummer></sbm:Valg></sbm:MFKundeStruktur><sbm:HæftelseForældelseDato>2019-03-01</sbm:HæftelseForældelseDato><sbm:HæftelseDom>false</sbm:HæftelseDom><sbm:HæftelseForlig>false</sbm:HæftelseForlig></sbm:MFHæftelseStruktur></sbm:FordringHæftelseSamling></sbm:MFOpretFordringStruktur><sbm:OpretUnderfordringSamling/></sbm:OpretFordringAktion></sbm:AktionValg></sbm:FordringAktion>")

		FordringsAktionData fordringsAktionData2 = new FordringsAktionData()
		fordringsAktionData2.setData("<?xml version=\"1.0\" encoding=\"UTF-8\"?><sbm:FordringAktion xmlns:sbm=\"http://skat.dk/begrebsmodel/2009/01/15/\"><sbm:MFAktionKode>OPRETFORDRING</sbm:MFAktionKode><sbm:DMIFordringHaverID>1229</sbm:DMIFordringHaverID><sbm:AktionValg><sbm:OpretFordringAktion><sbm:MFOpretFordringStruktur><sbm:DMIFordringFordringArtKode>MODR</sbm:DMIFordringFordringArtKode><sbm:DMIFordringTypeKode>LIMEDIE</sbm:DMIFordringTypeKode><sbm:DMIFordringFordringHaverRef>" + FORDRINGHAVER_REFERANCE_2 + "</sbm:DMIFordringFordringHaverRef><sbm:DMIFordringStiftelseTidspunkt>2016-03-01</sbm:DMIFordringStiftelseTidspunkt><sbm:DMIFordringForfaldDato>2016-03-01</sbm:DMIFordringForfaldDato><sbm:DMIFordringSRBDato>2016-03-01</sbm:DMIFordringSRBDato><sbm:FordringBeløbStruktur><sbm:ValutaKode>DKK</sbm:ValutaKode><sbm:DMIFordringBeløb>1200</sbm:DMIFordringBeløb></sbm:FordringBeløbStruktur><sbm:FordringPeriodeStruktur><sbm:DMIFordringPeriodeFraDato>2016-03-01</sbm:DMIFordringPeriodeFraDato><sbm:DMIFordringPeriodeTilDato>2016-08-31</sbm:DMIFordringPeriodeTilDato></sbm:FordringPeriodeStruktur><sbm:FordringOprindeligBeløbStruktur><sbm:ValutaKode>DKK</sbm:ValutaKode><sbm:EFIFordringOprindeligBeløb>1200</sbm:EFIFordringOprindeligBeløb></sbm:FordringOprindeligBeløbStruktur><sbm:SagsbemærkningSamling/><sbm:DokumentSamling/><sbm:DMIFordringHaverID>1229</sbm:DMIFordringHaverID><sbm:FordringHæftelseSamling><sbm:MFHæftelseStruktur><sbm:MFKundeStruktur><sbm:Valg><sbm:PersonCPRNummer>0505784634</sbm:PersonCPRNummer></sbm:Valg></sbm:MFKundeStruktur><sbm:HæftelseForældelseDato>2019-03-01</sbm:HæftelseForældelseDato><sbm:HæftelseDom>false</sbm:HæftelseDom><sbm:HæftelseForlig>false</sbm:HæftelseForlig></sbm:MFHæftelseStruktur></sbm:FordringHæftelseSamling></sbm:MFOpretFordringStruktur><sbm:OpretUnderfordringSamling/></sbm:OpretFordringAktion></sbm:AktionValg></sbm:FordringAktion>")

		FordringsAktion fordringsAktion1 = createFordringsAktion(AKTION_ID_1, DMIFordringArtType.INDR, FORDRINGHAVER_REFERANCE_1, fordringsAktionState1, leveranceEntity1, fordringsAktionData1)
		fordringsAktionDao.save(fordringsAktion1)

		FordringsAktion fordringsAktion2 = createFordringsAktion(AKTION_ID_2, DMIFordringArtType.MODR, FORDRINGHAVER_REFERANCE_2, fordringsAktionState2, leveranceEntity2, fordringsAktionData2)
		fordringsAktionDao.save(fordringsAktion2)

		fordringsAktionStateId1 = fordringsAktionState1.getId()
		fordringsAktionStateId2 = fordringsAktionState2.getId()

		leveranceEntity1Id = leveranceEntity1.getId()
		leveranceEntity2Id = leveranceEntity2.getId()

		fordringsAktion1Id = fordringsAktion1.getId()
		fordringsAktion2Id = fordringsAktion2.getId()

		println "FordringsAktion - $fordringsAktion1.id"
		println "FordringsAktion - $fordringsAktion2.id"
		println "FordringsAktionData - $fordringsAktionData1.id"
		println "FordringsAktionData - $fordringsAktionData2.id"
		println "LeveranceEntity - $leveranceEntity1.id"
		println "LeveranceEntity - $leveranceEntity2.id"
		println "FordringIndberetHistorik - $fordringIndberetHistorik1.id"
		println "FordringIndberetHistorik - $fordringIndberetHistorik2.id"
		println "LeveranceState - $leveranceState1.id"
		println "LeveranceState - $leveranceState2.id"
		println "TransaktionHistorik - $transaktionHistorik1.id"
		println "TransaktionHistorik - $transaktionHistorik2.id"
		println "FordringsAktionState - $fordringsAktionState1.id"
		println "FordringsAktionState - $fordringsAktionState2.id"
	}

	@After
	void clearDB() {
		fordringsAktionDao.delete({ entity -> entity.getAktionId() == AKTION_ID_1 } as Predicate)
		fordringsAktionDao.delete({ entity -> entity.getAktionId() == AKTION_ID_2 } as Predicate)

		leveranceEntityDao.delete({ entity -> entity.getLeveranceId() == LEVERANCE_ID_1 } as Predicate)
		leveranceEntityDao.delete({ entity -> entity.getLeveranceId() == LEVERANCE_ID_2 } as Predicate)
	}

	private static FordringsAktion createFordringsAktion(long id, DMIFordringArtType artType, String fordringhaverReferance,
														 FordringsAktionState fordringsAktionState, LeveranceEntity leveranceEntity, FordringsAktionData fordringsAktionData) {
		FordringsAktion fordringsAktion = new FordringsAktion()
		fordringsAktion.setAktionId(id)
		fordringsAktion.setAktionKode(MFAktionKodeType.OPRETFORDRING)
		fordringsAktion.setArtType(artType)
		fordringsAktion.setBeloeb(new BigDecimal(1200))
		fordringsAktion.setFordringTypeKode("LIMEDIE")
		fordringsAktion.setFordringhaverId(1229)
		fordringsAktion.setFordringhaverReferance(fordringhaverReferance)
		fordringsAktion.setFordringsId(id)
		fordringsAktion.setFordringsRelation(FordringsRelation.HOVEDFORDRING)
		fordringsAktion.setFordringshaverSystemId(62786515)
		fordringsAktion.setHovedfordringId(id)
		fordringsAktion.setModtagelsesTidspunkt(LocalDateTime.now())
		fordringsAktion.setRequestTimestamp(LocalDateTime.now())
		fordringsAktion.setLeveranceEntity(leveranceEntity)
		fordringsAktion.setFordringsAktionState(fordringsAktionState)
		fordringsAktion.setFordringsAktionData(fordringsAktionData)

		fordringsAktion
	}

	private static LeveranceEntity creteLeveranceEntity(long leveranceId, FordringIndberetHistorik fih, LeveranceState ls) {
		LeveranceEntity leveranceEntity = new LeveranceEntity()
		leveranceEntity.setErrorCode(0)
		leveranceEntity.setExMfResponse("")
		leveranceEntity.setFordringshaverSystemId(62786515)
		leveranceEntity.setLeveranceId(leveranceId)
		leveranceEntity.setModtagelsesTidspunkt(LocalDateTime.now())
		leveranceEntity.setXmlType(XmlType.OIO)
		leveranceEntity.setFordringIndberetHistorik(fih)
		leveranceEntity.setLeveranceState(ls)
		leveranceEntity
	}

	private static FordringsAktionState createFordringsAktionState(BatchStatus batchStatus, MFAktionStatusKodeType status, BatchTargetSystem batchTargetSystem, Boolean hoeringFlag) {
		FordringsAktionState fordringsAktionState = new FordringsAktionState()
		fordringsAktionState.setBatchStatus(batchStatus)
		fordringsAktionState.setErrorCode(0)
		fordringsAktionState.setHoeringFlag(hoeringFlag)
		fordringsAktionState.setStatus(status)
		fordringsAktionState.setStatusAendretDato(LocalDateTime.now())
		if (batchTargetSystem != null) {
			fordringsAktionState.setBatchTargetSystem(batchTargetSystem)
		}
		fordringsAktionState
	}

	private static TransaktionHistorik createTransaktionHistorik(String transaktionId) {
		TransaktionHistorik transaktionHistorik = new TransaktionHistorik()
		transaktionHistorik.setErrorCode(0)
		transaktionHistorik.setRequestType(ServiceName.FORDRING_INDBERET)
		transaktionHistorik.setTimestamp(LocalDateTime.now())
		transaktionHistorik.setTransaktionId(transaktionId)
		transaktionHistorik
	}

	private static LeveranceState createLeveranceState() {
		LeveranceState leveranceState = new LeveranceState()
		leveranceState
	}

	private static FordringIndberetHistorik createFordringIndberetHistorik(int leveranceId, String transaktionsId, String fordringhaverReferance, long aktionId, TransaktionHistorik transaktionHistorik) {
		FordringIndberetHistorik fordringIndberetHistorik1 = new FordringIndberetHistorik()
		fordringIndberetHistorik1.setErrorCode(0)
		fordringIndberetHistorik1.setLeveranceId(leveranceId)
		fordringIndberetHistorik1.setRequest("<sbm:MFFordringIndberet_I xmlns:sbm=\"http://skat.dk/begrebsmodel/2009/01/15/\"><sbm:Kontekst><sk:HovedOplysninger xmlns:sk=\"http://skat.dk/begrebsmodel/xml/schemas/kontekst/2007/05/31/\"><sk:TransaktionsID>" + transaktionsId + "_frbm</sk:TransaktionsID><sk:TransaktionsTid>2017-05-16T15:27:07.905</sk:TransaktionsTid></sk:HovedOplysninger></sbm:Kontekst><sbm:FordringhaverSystemIDStruktur><sbm:VirksomhedSENummer>62786515</sbm:VirksomhedSENummer></sbm:FordringhaverSystemIDStruktur><sbm:MFLeveranceID>" + leveranceId + "</sbm:MFLeveranceID><sbm:FordringAktionSamling><sbm:FordringAktion><sbm:MFAktionKode>OPRETFORDRING</sbm:MFAktionKode><sbm:DMIFordringHaverID>1229</sbm:DMIFordringHaverID><sbm:AktionValg><sbm:OpretFordringAktion><sbm:MFOpretFordringStruktur><sbm:DMIFordringFordringArtKode>INDR</sbm:DMIFordringFordringArtKode><sbm:DMIFordringTypeKode>LIMEDIE</sbm:DMIFordringTypeKode><sbm:DMIFordringFordringHaverRef>" + fordringhaverReferance + "</sbm:DMIFordringFordringHaverRef><sbm:DMIFordringStiftelseTidspunkt>2016-03-01</sbm:DMIFordringStiftelseTidspunkt><sbm:DMIFordringForfaldDato>2016-03-01</sbm:DMIFordringForfaldDato><sbm:DMIFordringSRBDato>2016-03-01</sbm:DMIFordringSRBDato><sbm:FordringBeløbStruktur><sbm:ValutaKode>DKK</sbm:ValutaKode><sbm:DMIFordringBeløb>1200</sbm:DMIFordringBeløb></sbm:FordringBeløbStruktur><sbm:FordringPeriodeStruktur><sbm:DMIFordringPeriodeFraDato>2016-03-01</sbm:DMIFordringPeriodeFraDato><sbm:DMIFordringPeriodeTilDato>2016-08-31</sbm:DMIFordringPeriodeTilDato></sbm:FordringPeriodeStruktur><sbm:FordringOprindeligBeløbStruktur><sbm:ValutaKode>DKK</sbm:ValutaKode><sbm:EFIFordringOprindeligBeløb>1200</sbm:EFIFordringOprindeligBeløb></sbm:FordringOprindeligBeløbStruktur><sbm:SagsbemærkningSamling/><sbm:DokumentSamling/><sbm:DMIFordringHaverID>1229</sbm:DMIFordringHaverID><sbm:FordringHæftelseSamling><sbm:MFHæftelseStruktur><sbm:MFKundeStruktur><sbm:Valg><sbm:PersonCPRNummer>0505784634</sbm:PersonCPRNummer></sbm:Valg></sbm:MFKundeStruktur><sbm:HæftelseForældelseDato>2019-03-01</sbm:HæftelseForældelseDato><sbm:HæftelseDom>false</sbm:HæftelseDom><sbm:HæftelseForlig>false</sbm:HæftelseForlig></sbm:MFHæftelseStruktur></sbm:FordringHæftelseSamling></sbm:MFOpretFordringStruktur><sbm:OpretUnderfordringSamling/></sbm:OpretFordringAktion></sbm:AktionValg></sbm:FordringAktion></sbm:FordringAktionSamling></sbm:MFFordringIndberet_I>")
		fordringIndberetHistorik1.setRequestHeader("\"      <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">\n" +
				"         <Assertion AssertionID=\"d913c046fddbca7a55fca34caf18cafa\" IssueInstant=\"2007-05-14T11:45:21.835Z\" Issuer=\"test.unsigned\" MajorVersion=\"1\" MinorVersion=\"1\" xmlns=\"urn:oasis:names:tc:SAML:1.0:assertion\" xmlns:saml=\"urn:oasis:names:tc:SAML:1.0:assertion\" xmlns:samlp=\"urn:oasis:names:tc:SAML:1.0:protocol\">\n" +
				"            <Conditions NotBefore=\"2007-05-14T11:43:21.834Z\" NotOnOrAfter=\"2022-05-14T11:43:21.834Z\"/>\n" +
				"            <AuthenticationStatement AuthenticationInstant=\"2007-05-14T11:45:21.834Z\" AuthenticationMethod=\"urn:oasis:names:tc:SAML:1.0:am:unspecified\">\n" +
				"               <Subject>\n" +
				"                  <NameIdentifier Format=\"urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified\" NameQualifier=\"skat\">skatGuid=W95640,ou=skatEmployee,ou=internal,ou=entities,dc=skat,dc=dk</NameIdentifier>\n" +
				"                  <SubjectConfirmation>\n" +
				"                     <ConfirmationMethod>urn:oasis:names:tc:SAML:1.0:cm:sender-vouches</ConfirmationMethod>\n" +
				"                  </SubjectConfirmation>\n" +
				"               </Subject>\n" +
				"            </AuthenticationStatement>\n" +
				"            <AttributeStatement>\n" +
				"               <Subject>\n" +
				"                  <NameIdentifier Format=\"urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified\" NameQualifier=\"skat\">skatGuid=W95640,ou=skatEmployee,ou=internal,ou=entities,dc=skat,dc=dk</NameIdentifier>\n" +
				"                  <SubjectConfirmation>\n" +
				"                     <ConfirmationMethod>urn:oasis:names:tc:SAML:1.0:cm:sender-vouches</ConfirmationMethod>\n" +
				"                  </SubjectConfirmation>\n" +
				"               </Subject>\n" +
				"               <Attribute AttributeName=\"Groups\" AttributeNamespace=\"urn:bea:security:saml:groups\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
				"                  <AttributeValue>PRIMARY_IDENTITY_skatGuid=W95640,ou=skatEmployee,ou=internal,ou=entities,dc=skat,dc=dk</AttributeValue>\n" +
				"                  <AttributeValue>AUTH_LEVEL_6</AttributeValue>\n" +
				"                  <AttributeValue>EFIFordringshaverPRG</AttributeValue>\n" +
				"               </Attribute>\n" +
				"            </AttributeStatement>\n" +
				"         </Assertion>\n" +
				"      </wsse:Security>")

		fordringIndberetHistorik1.setResponse("\"<?xml version=\"\"1.0\"\" encoding=\"\"UTF-8\"\" standalone=\"\"yes\"\"?>\n" +
				"<MFFordringIndberet_O xmlns=\"\"http://skat.dk/begrebsmodel/2009/01/15/\"\">\n" +
				"    <Kontekst>\n" +
				"        <ns2:HovedOplysningerSvar xmlns:ns=\"\"http://skat.dk/begrebsmodel/2009/01/15/\"\" xmlns:ns2=\"\"http://skat.dk/begrebsmodel/xml/schemas/kontekst/2007/05/31/\"\">\n" +
				"            <ns2:TransaktionsID>" + transaktionsId + "_frbm</ns2:TransaktionsID>\n" +
				"            <ns2:ServiceID>MFFordringIndberet</ns2:ServiceID>\n" +
				"            <ns2:TransaktionsTid>2017-05-16T15:27:10.501</ns2:TransaktionsTid>\n" +
				"            <ns2:SvarReaktion/>\n" +
				"        </ns2:HovedOplysningerSvar>\n" +
				"    </Kontekst>\n" +
				"    <FordringInfo>\n" +
				"        <FordringhaverSystemIDStruktur>\n" +
				"            <VirksomhedSENummer>62786515</VirksomhedSENummer>\n" +
				"        </FordringhaverSystemIDStruktur>\n" +
				"        <MFLeveranceID>" + leveranceId + "</MFLeveranceID>\n" +
				"        <FordringAktionStatusSamling>\n" +
				"            <MFAktionStruktur>\n" +
				"                <DMIFordringEFIFordringID>" + aktionId + "</DMIFordringEFIFordringID>\n" +
				"                <DMIFordringEFIHovedFordringID>" + aktionId + "</DMIFordringEFIHovedFordringID>\n" +
				"                <DMIFordringFordringHaverRef>" + fordringhaverReferance + "</DMIFordringFordringHaverRef>\n" +
				"                <MFAktionID>" + aktionId + "</MFAktionID>\n" +
				"                <MFAktionKode>OPRETFORDRING</MFAktionKode>\n" +
				"                <DMIFordringHaverID>1229</DMIFordringHaverID>\n" +
				"                <MFAktionStatusKode>MODTAGET</MFAktionStatusKode>\n" +
				"                <MFAktionStatusÆndretDato>2017-05-16T15:27:12.163</MFAktionStatusÆndretDato>\n" +
				"                <DMIFordringModtagelseDato>2017-05-16T15:27:10.501</DMIFordringModtagelseDato>\n" +
				"                <AfvistÅrsagSamling/>\n" +
				"            </MFAktionStruktur>\n" +
				"        </FordringAktionStatusSamling>\n" +
				"    </FordringInfo>\n" +
				"</MFFordringIndberet_O>\n" +
				"\"")
		fordringIndberetHistorik1.setStatusAendretDato(LocalDateTime.now())
		fordringIndberetHistorik1.setTimestamp(LocalDateTime.now())
		fordringIndberetHistorik1.setTransaktionHistorik(transaktionHistorik)
		fordringIndberetHistorik1
	}
}