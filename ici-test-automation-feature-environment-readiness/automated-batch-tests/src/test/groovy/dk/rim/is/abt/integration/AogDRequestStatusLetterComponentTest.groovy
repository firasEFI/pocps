package dk.rim.is.abt.integration

import dk.rim.is.abt.util.BatchController
import dk.rim.is.abt.util.GenericDao
import dk.rim.is.common.entity.brev.*
import org.junit.After
import org.junit.Before
import org.junit.Test

import java.util.function.Predicate

import static dk.rim.is.abt.util.SpringContext.buildDao

/**
 * Created by msl on 27.07.2017.
 */
class AogDRequestStatusLetterComponentTest {

    private static final String AOGD_JOB_NAME = "AogDRequestStatus"

    private GenericDao<BrevEntity> brevDao = buildDao(BrevEntity.class)
    private GenericDao<GenereretBrevEntity> genereretBrevDao = buildDao(GenereretBrevEntity.class)

    private Long TEST_ID
    private Long usedGenereretBrevId
    private Long usedBrevId
    private String brevId = "75234136800000000752341359420170713"

    @Test
    void testBatchReturnCode() {
        def jobReturnCode = BatchController.runIntegrationJob(AOGD_JOB_NAME)
        assert jobReturnCode.executionStatus == "SUCCESS"
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
        brev.setDatoAogDStatusKald(null)
        brev.setDatoOpdateret(date)
        brev.setXml("test")
        brev.setStatusSend(BrevSendStatus.SENT_TO_AOGD)
        brev.setStatusProduktion(BrevStatusProduktion.PENDING_ES)

        // TODO: Is this value arbitrary or who determines it?
        brev.setMeddelelseId(23803659)
        brev.setMeddelelseFejlKode("")
        brev.setMeddelelseFejlTekst("")
        brevDao.save(Collections.singletonList(brev))

        usedBrevId = brev.getId()

        GenereretBrevEntity genereretBrev = new GenereretBrevEntity()

        // TODO: Is this value arbitrary or who determines it?
        genereretBrev.setFormateretMeddelelseId(0000000366117)
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
