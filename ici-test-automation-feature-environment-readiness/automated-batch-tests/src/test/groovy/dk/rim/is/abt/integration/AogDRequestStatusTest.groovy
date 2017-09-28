package dk.rim.is.abt.integration

import dk.rim.is.abt.dao.cisadm.CiCcEntity
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
import org.junit.After
import org.junit.Before
import org.junit.Test

import java.util.function.Predicate

import static dk.rim.is.abt.util.SpringContext.buildDao

/**
 * Created by wos on 11.05.2017.
 */
class AogDRequestStatusTest {

    private static final String AOGD_JOB_NAME = "AogDRequestStatus"

    private GenericDao<BrevEntity> brevDao = buildDao(BrevEntity.class)
    private GenericDao<GenereretBrevEntity> genereretBrevDao = buildDao(GenereretBrevEntity.class)

    private Long TEST_ID
    private Long usedGenereretBrevId
    private Long usedBrevId
    private String brevId = "08987960730000000089076629020170505"

    @Test
    void testBatchReturnCode() {
        def jobReturnCode = BatchController.runAndReportIntegrationJob(AOGD_JOB_NAME)
        assert jobReturnCode.executionStatus == "SUCCESS"
    }

    @Before
    void setupDb() {

        Random r = new Random()
        TEST_ID = (r.nextLong()*88888L)
        println "Set batchId=" + TEST_ID

        Date date = new Date()

        BrevEntity brev = new BrevEntity()
        brev.setBatchId(TEST_ID)
        brev.setBrevId(brevId)
        brev.setDatoOprettelse(date)
        brev.setDatoAogDStatusKald(null)
        brev.setDatoOpdateret(date)
        brev.setXml("test")
        brev.setStatusSend(BrevSendStatus.SENT_TO_AOGD)
        brev.setStatusProduktion(BrevStatusProduktion.PENDING_ES)

        brev.setMeddelelseId(23803658)
        brev.setMeddelelseFejlKode("")
        brev.setMeddelelseFejlTekst("")
        brevDao.save(Collections.singletonList(brev))

        usedBrevId = brev.getId()

        GenereretBrevEntity genereretBrev = new GenereretBrevEntity()

        genereretBrev.setFormateretMeddelelseId(0000000366116)
        genereretBrev.setStatusForsendelse(ForsendelseStatus.NOT_ACKNOWLEDGED)
        genereretBrev.setForsendelseFejlKode("")
        genereretBrev.setForsendelseFejlTekst("")
        genereretBrev.setBrev(brev)
        genereretBrevDao.save(genereretBrev)

        usedGenereretBrevId = genereretBrev.getId()
    }

    @After
    void clearDb() {
        GenereretBrevEntity genereretBrev = genereretBrevDao.getBy{entity -> usedGenereretBrevId.equals(entity.getId())}.get(0)
        BrevEntity brevEntity = genereretBrev.getBrev()

        genereretBrevDao.delete({entity -> usedBrevId.equals(entity.getBrev().getId())} as Predicate)
        brevDao.delete({entity -> brevEntity.getId().equals(entity.getId())} as Predicate)
    }
}
