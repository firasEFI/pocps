package dk.rim.is.abt.integration

import dk.rim.is.abt.util.BatchController
import dk.rim.is.abt.util.GenericDao
import dk.rim.is.common.entity.brev.BrevEntity
import dk.rim.is.common.entity.brev.BrevSendStatus
import org.junit.After
import org.junit.Before
import org.junit.Test

import java.security.SecureRandom
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.function.Predicate
import java.util.function.ToLongFunction

import static dk.rim.is.abt.util.SpringContext.buildDao

class AogDSendLettersTest {
    private static final String JOB_DESCRIPTION = "AogDSendLetters"

    private GenericDao<BrevEntity> brevDao = buildDao(BrevEntity.class)
    private Long BATCH_ID
    private List<Long> BrevIdList = new ArrayList<Long>()
private Integer EntityLimit = 200

/**
 * Step 1. Upload valid requests to db x 200
 * Step 2. call AogD with a request size of 200
 * Step 3. wait for 200.OK
 */
    @Test
    void runTest() {
        System.out.println("Batch_ID is: " + BATCH_ID)
        sendFileToDB()
        if (testBatchReturnCode()) {
            checkBrevStatus()
        }
    }

    @Before
    void setup() {
        BATCH_ID = new SecureRandom().longs().limit(8).sum()
    }

    @After
    void cleanUp() {
        //clean up in the db, delete all entries with the batch-id
        //this is really slow :( :( :(
        //System.out.println("Deleting entries, please wait")

        brevDao.delete(isFromThisBatchById)
    }

    private Predicate<BrevEntity> isFromThisBatch = new Predicate<BrevEntity>() {
        @Override
        boolean test(BrevEntity brevEntity) {
            return brevEntity.batchId.equals(BATCH_ID)
        }
    }

    private Predicate<BrevEntity> isFromThisBatchById = new Predicate<BrevEntity>() {
        @Override
        boolean test(BrevEntity brevEntity) {
            return BrevIdList.contains(brevEntity.getId())
        }
    }

    private ToLongFunction<? super BrevEntity> brevToIdMapper = new ToLongFunction<? super BrevEntity>(){

        @Override
        long applyAsLong(Object value) {
            return ((BrevEntity)value).getId()
        }
    }

    private void sendFileToDB() {
        InputStream resourceStream = getClass().getResourceAsStream("/ValidRequest.xml")
        ByteArrayOutputStream buffer = new ByteArrayOutputStream()

        int nRead
        byte[] data = new byte[1000]

        while ((nRead = resourceStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead)
        }
        buffer.flush()
        String xml = buffer.toString().replace("BATCH_ID" , BATCH_ID.toString())
        buffer.close()
        resourceStream.close()

        Timestamp time = new Timestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
        List<BrevEntity> brevList = new ArrayList<BrevEntity>()
        for (int i=1; i<=EntityLimit; i++) {
            BrevEntity brev = new BrevEntity()
            brev.setBatchId(BATCH_ID)
            brev.setBrevId(new BigInteger(i) as String)
            brev.setDatoOprettelse(time)
            brev.setDatoAogDStatusKald(time)
            brev.setDatoOpdateret(time)
            brev.setXml(xml)
            brev.setStatusSend(BrevSendStatus.TO_SEND)
            brevList.add(brev)
            //System.out.println("Saved entity number " + i)
        }
        brevDao.save(brevList)//use ids to delete entities later
        BrevIdList = brevList.parallelStream().mapToLong(brevToIdMapper).toArray()
        System.out.println("Saved entities")
    }

    private void checkBrevStatus() {
        List<BrevEntity> brevList = brevDao.getBy(isFromThisBatch)
        Integer success = 0
        System.out.println("checking entities")
        for (BrevEntity brev : brevList) {
            if (BrevSendStatus.SENT_TO_AOGD.equals(brev.getStatusSend())) success++
        }
        assert success.equals(brevList.size())
        System.out.println("Entities checked OK")
    }

    private boolean testBatchReturnCode() {
        def jobReturnCode = BatchController.runAndReportIntegrationJob(JOB_DESCRIPTION)
        assert jobReturnCode.executionStatus == "SUCCESS"
        return jobReturnCode.executionStatus == "SUCCESS"
    }
}