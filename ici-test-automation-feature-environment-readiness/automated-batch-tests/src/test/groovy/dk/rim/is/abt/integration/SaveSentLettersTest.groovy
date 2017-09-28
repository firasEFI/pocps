package dk.rim.is.abt.integration

import dk.rim.is.abt.dao.cisadm.CiPerCharEntity
import dk.rim.is.abt.dao.cisadm.CiPerEntity
import dk.rim.is.abt.dao.cisadm.CiPerIdEntity
import dk.rim.is.abt.dao.cisadm.CiPerKEntity
import dk.rim.is.abt.util.BatchController
import dk.rim.is.abt.util.GenericDao
import dk.rim.is.abt.util.SpringContextCisadm
import dk.rim.is.common.entity.brev.BrevEntity
import dk.rim.is.common.entity.brev.BrevSendStatus
import dk.rim.is.common.entity.brev.BrevStatusProduktion
import dk.rim.is.common.entity.brev.CaptiaStatus
import dk.rim.is.common.entity.brev.ForsendelseStatus
import dk.rim.is.common.entity.brev.GenereretBrevEntity
import dk.rim.is.common.entity.brev.GenereretBrevPdfEntity
import dk.rim.is.abt.dao.cisadm.CiCcEntity
import org.junit.After
import org.junit.Before
import org.junit.Test

import java.time.LocalDateTime
import java.time.Month
import java.util.function.Predicate

import static dk.rim.is.abt.util.SpringContext.buildDao

class SaveSentLettersTest {

    private GenericDao<BrevEntity> brevDao = buildDao(BrevEntity.class)

    private GenericDao<GenereretBrevEntity> genereretBrevDao = buildDao(GenereretBrevEntity.class)

    private GenericDao<GenereretBrevPdfEntity> genereretBrevPdfDao = buildDao(GenereretBrevPdfEntity.class)

    private GenericDao<CiCcEntity> generatetCiCcEntityDao = SpringContextCisadm.buildDao(CiCcEntity.class)

    private GenericDao<CiPerEntity> generatetCiPerEntityDao = SpringContextCisadm.buildDao(CiPerEntity.class)

    private GenericDao<CiPerCharEntity> generatetCiPerCharEntityDao = SpringContextCisadm.buildDao(CiPerCharEntity.class)

    private GenericDao<CiPerIdEntity> generatetCiPerIdEntityDao = SpringContextCisadm.buildDao(CiPerIdEntity.class)

    private GenericDao<CiPerKEntity> generatetCiPerKEntityDao = SpringContextCisadm.buildDao(CiPerKEntity.class)

    private static final String JOB_DESCRIPTION = "SaveSentLetters"

    private static Long TEST_ID

    private Long usedGenereretBrevId

    private String usedCcId

    private String brevIdCcId = "6364405989"

//    private String brevIdPerId = "5797390973"
    private String brevIdPerId = "8888888888"

    private String brevIdDate = "20170518"

    private String brevId = brevIdCcId + "0000000" + brevIdPerId + brevIdDate

    @Test
    void callCaptiaCreateDocument_shouldArchiveDocuments() throws Exception {
        int initialArchivedLettersCount = countGenereretBrevWithStatus(CaptiaStatus.FINISHED)

        def jobReturnCode = BatchController.runAndReportIntegrationJob(JOB_DESCRIPTION)
        assert jobReturnCode.executionStatus == "SUCCESS"

        int finalArchivedLettersCount = countGenereretBrevWithStatus(CaptiaStatus.FINISHED)
        assert finalArchivedLettersCount > initialArchivedLettersCount
    }

    @Before
    void setupDb() {

        Random r = new Random()
        TEST_ID = (r.nextLong()*88888L)

        Date date = new Date()

        BrevEntity brev = new BrevEntity()
        brev.setBatchId(TEST_ID)
        brev.setBrevId(brevId)
        brev.setDatoOprettelse(date)
        brev.setDatoAogDStatusKald(date)
        brev.setDatoOpdateret(date)
        brev.setXml("test")
        brev.setStatusSend(BrevSendStatus.SENT_TO_AOGD)
        brev.setStatusProduktion(BrevStatusProduktion.PRESUMED_SHIPPED)

        brev.setMeddelelseId(23803658)
        brev.setMeddelelseFejlKode("")
        brev.setMeddelelseFejlTekst("")
        brevDao.save(Collections.singletonList(brev))

        GenereretBrevEntity genereretBrev = new GenereretBrevEntity()

        genereretBrev.setFormateretMeddelelseId(0000000366116)
        genereretBrev.setStatusForsendelse(ForsendelseStatus.POSITIVELY_ACKNOWLEDGED)
        genereretBrev.setForsendelseFejlKode("")
        genereretBrev.setForsendelseFejlTekst("")
        genereretBrev.setBrev(brev)
        genereretBrevDao.save(genereretBrev)

        usedGenereretBrevId = genereretBrev.getId()

        CiCcEntity ciCcEntity = new CiCcEntity()
        ciCcEntity.setCcId(brevIdCcId)
        ciCcEntity.setUserId("HL      ")
        ciCcEntity.setPerId(brevIdPerId)
        ciCcEntity.setCcDttm(LocalDateTime.of(2017, Month.MAY, 18, 0, 0, 0))
        ciCcEntity.setCcClCd("FTXT")
        ciCcEntity.setCcTypeCd("IND0906DIV01")
        ciCcEntity.setPrintLetterSw("Y")
        ciCcEntity.setLetterPrintDttm(null)
        ciCcEntity.setLtrTmplCd("MISC-DEMO   ")
        ciCcEntity.setDescr254("SENDTOAND")
        ciCcEntity.setVersion(13 as short)
        ciCcEntity.setBatchCd("LTRPRT  ")
        ciCcEntity.setBatchNbr(1)
        ciCcEntity.setCcStatusFlg("    ")
        ciCcEntity.setC1CcDataArea("<bo>IND0906DIV_02</bo><recipientPersonId>" + brevIdPerId + "</recipientPersonId><digital>true</digital><recipientAddressId>00000000000093</recipientAddressId><addDebtOverview>false</addDebtOverview><addOCRLine>false</addOCRLine><obligations/><AogDData><Meddelelser><Meddelelse><KanalAdresseNavn/><KanalAdresseStruktur><AdresseValg><AlternativAdresseStruktur><AlternativAdresse/></AlternativAdresseStruktur></AdresseValg></KanalAdresseStruktur><Filer/><MeddelelseIndhold><InddrivelseFritekstMeddelelseStruktur><InddrivelseIndtastningFelterStruktur><InddrivelseIndtastningFelterListe/></InddrivelseIndtastningFelterStruktur><InddrivelseForbladStruktur><InddrivelseIndtastningFelterStruktur><InddrivelseIndtastningFelterListe/></InddrivelseIndtastningFelterStruktur><KanalAdresseStruktur><AdresseValg><AlternativAdresseStruktur><AlternativAdresse/></AlternativAdresseStruktur></AdresseValg></KanalAdresseStruktur></InddrivelseForbladStruktur><OCRLinieStruktur/><InddrivelseFordringOpgørelseStruktur><InddrivelseSimuleretRenteStruktur/><InddrivelseFordringshaverFordringerListe/></InddrivelseFordringOpgørelseStruktur><KanalAdresseStruktur><AdresseValg><AdresseStruktur><Adresse/></AdresseStruktur></AdresseValg></KanalAdresseStruktur></InddrivelseFritekstMeddelelseStruktur></MeddelelseIndhold></Meddelelse></Meddelelser></AogDData><draftPreviewed>true</draftPreviewed><draftBatchId>468937414955525390126032876</draftBatchId><draftLetterId>23891240</draftLetterId><statusCode>READ</statusCode><statusDate>2017-06-01-15.30.02</statusDate>")
        generatetCiCcEntityDao.save(ciCcEntity)

        usedCcId = ciCcEntity.getCcId()

        CiPerIdEntity ciPerIdEntity = new CiPerIdEntity()
        ciPerIdEntity.setPerId(brevIdPerId)
        ciPerIdEntity.setIdTypeCd("CPR     ")
        ciPerIdEntity.setPerIdNbr("0101851122")
        ciPerIdEntity.setPrimSw("Y")
        ciPerIdEntity.setVersion(5 as short)
        generatetCiPerIdEntityDao.save(ciPerIdEntity)

        CiPerEntity ciPerEntity = new CiPerEntity()
        ciPerEntity.setPerId(brevIdPerId)
        ciPerEntity.setLanguageCd("ENG")
        ciPerEntity.setLsSlFlg("N ")
        ciPerEntity.setEmailid("email@mail.com")
        ciPerEntity.setOvrdMailName1("Peter Pan")
        ciPerEntity.setOvrdMailName2("OvrdMailName2")
        ciPerEntity.setOvrdMailName3("OvrdMailName3")
        ciPerEntity.setAddress1("address1")
        ciPerEntity.setAddress2("address2")
        ciPerEntity.setAddress3("address3")
        ciPerEntity.setAddress4("address4")
        ciPerEntity.setCity("city")
        ciPerEntity.setCountry("ENG")
        ciPerEntity.setCounty("county")
        ciPerEntity.setGeoCode("geoCode")
        ciPerEntity.setHouseType("H ")
        ciPerEntity.setLsSlDescr("lsSlDescr")
        ciPerEntity.setNum1("num1")
        ciPerEntity.setNum2("num2")
        ciPerEntity.setPerOrBusFlg("P ")
        ciPerEntity.setInCityLimit("N")
        ciPerEntity.setPostal("postal")
        ciPerEntity.setState("state")
        ciPerEntity.setWebPasswd("webPasswd")
        ciPerEntity.setWebPasswdAns("webPasswdAns")
        ciPerEntity.setWebPwdHintFlg("wphf")
        ciPerEntity.setVersion(33 as short)
        ciPerEntity.setRecvMktgInfoFlg("NRCV")
        ciPerEntity.setC1PerDataArea("<postChannel>DP</postChannel><updateChildrenData>false</updateChildrenData><updateMaritalStatus>false</updateMaritalStatus>")
        ciPerEntity.setPerTypeCd("DK-PERSON   ")
        generatetCiPerEntityDao.save(ciPerEntity)

        CiPerCharEntity ciPerCharEntity = new CiPerCharEntity()
        ciPerCharEntity.setPerId(brevIdPerId)
        ciPerCharEntity.setCharTypeCd("DK-CAPTA")
        ciPerCharEntity.setCharVal("charVal")
        ciPerCharEntity.setCharValFk1("charValFk1")
        ciPerCharEntity.setCharValFk2("charValFk2")
        ciPerCharEntity.setCharValFk3("charValFk3")
        ciPerCharEntity.setCharValFk4("charValFk4")
        ciPerCharEntity.setCharValFk5("charValFk5")
        ciPerCharEntity.setEffdt(date)
        ciPerCharEntity.setAdhocCharVal("17-0000418")
        ciPerCharEntity.setVersion(4 as short)
        ciPerCharEntity.setSrchCharVal("17-0000418                                        ")
        generatetCiPerCharEntityDao.save(ciPerCharEntity)

        CiPerKEntity ciPerKEntity = new CiPerKEntity()
        ciPerKEntity.setPerId(brevIdPerId)
        ciPerKEntity.setEnvId(413970)
        generatetCiPerKEntityDao.save(ciPerKEntity)
    }

    @After
    void clearDb() {
        GenereretBrevEntity genereretBrev = genereretBrevDao.getBy{entity -> usedGenereretBrevId.equals(entity.getId())}.get(0)
        GenereretBrevPdfEntity genereretBrevPdf = genereretBrev.getGenereretBrevPdf()
        BrevEntity brevEntity = genereretBrev.getBrev()

        if(!isObjectEmpty(genereretBrevPdf)) genereretBrevPdfDao.delete({entity -> genereretBrevPdf.getId().equals(entity.getId())} as Predicate)
        if(!isObjectEmpty(genereretBrev)) genereretBrevDao.delete({entity -> genereretBrev.getId().equals(entity.getId())} as Predicate)
        if(!isObjectEmpty(brevEntity)) brevDao.delete({entity -> brevEntity.getId().equals(entity.getId())} as Predicate)

        generatetCiCcEntityDao.delete({entity -> usedCcId.equals(entity.getCcId())} as Predicate)
        generatetCiPerIdEntityDao.delete({entity -> brevIdPerId.equals(entity.getPerId())} as Predicate)
        generatetCiPerEntityDao.delete({entity -> brevIdPerId.equals(entity.getPerId())} as Predicate)
        generatetCiPerCharEntityDao.delete({entity -> brevIdPerId.equals(entity.getPerId())} as Predicate)
        generatetCiPerKEntityDao.delete({entity -> brevIdPerId.equals(entity.getPerId())} as Predicate)
    }

    private int countGenereretBrevWithStatus(CaptiaStatus status) {
        return genereretBrevDao.getBy{entity -> status.equals(entity.getStatusCaptia())}.size()
    }

    private boolean isObjectEmpty(Object entity) {
        return entity == null;
    }

}
