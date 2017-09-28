package dk.rim.is.abt.integration

import dk.rim.is.abt.dao.batch.BatchJobExecutionEntity
import dk.rim.is.abt.dao.batch.CorrectionOrderEntity
import dk.rim.is.abt.dao.cisadm.IntegrationFileEntity
import dk.rim.is.abt.util.BatchController
import dk.rim.is.abt.util.GenericDao
import dk.rim.is.abt.util.SpringContext
import dk.rim.is.abt.util.SpringContextCisadm
import spock.lang.Specification
import spock.lang.Unroll

import java.sql.Timestamp
import java.time.Duration
import java.time.Instant

class FetchCorrectionsTest extends Specification {
    private static final String JOB_DESCRIPTION = "FetchCorrections"

    // From SoapUI Mock, might need to differ
    private static final String ORDER_IDENTIFIER = "ar0OdFsu5zEg1W6J1a7aQ4"

    public static final String ERROR = "ERROR"
    public static final String PENDING = "PENDING"
    public static final String RESOLVED = "RESOLVED"
    public static final String NEW = "NEW"
    public static final String FAILED = "FAILED"
    public static final String COMPLETED = "COMPLETED"
    public static final String SUCCESS = "SUCCESS"
    public static final String FAILURE = "FAILURE"

    private GenericDao<IntegrationFileEntity> integrationFileDao =
            SpringContextCisadm.buildDao(IntegrationFileEntity.class)
    private GenericDao<CorrectionOrderEntity> correctionOrderDao =
            SpringContext.buildDao(CorrectionOrderEntity.class)
    private GenericDao<BatchJobExecutionEntity> batchJobExecutionDao =
            SpringContext.buildDao(BatchJobExecutionEntity.class)

    public boolean isInteger(String str) {
        if(str == null || str.trim().isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if(!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Unroll
    def "Given #status, #orderId, #time Expect the outputs correction = #correctionStatus and batch = #batchStatus"() {
        given: "An order inserted in the BATCH database"
        def order = createOrder(status, orderId, time)
        correctionOrderDao.save(order)
        println "Order ${order.id} - ${order.status}"

        when: "I run the batch job"
        def jobReturnCode = BatchController.runAndReportIntegrationJob(JOB_DESCRIPTION)
        order = correctionOrderDao.getById(order.getId())
        def batchId = jobReturnCode.batchJobExecutionId;
        BatchJobExecutionEntity batchJobExecutionList
        batchJobExecutionList = batchJobExecutionDao.getById(Long.parseLong(batchId))

        then: "I expect the results to be #correctionStatus, #batchStatus"
        jobReturnCode.executionStatus == executionStatus
        order.getStatus() == correctionStatus
        batchJobExecutionList.getStatus() == batchStatus

        if(integrationFileExist) {
            long intFileId = order.getIntegrationFileId()
            assert integrationFileDao.getById(intFileId) != null
        }

        cleanup: "Cleaning up test data"
        if (order != null) {
            if(correctionOrderDao.getById(order.getId()) != null) {
                correctionOrderDao.deleteEntity(order)
            }
            if (order.getIntegrationFileId() != null) {
                IntegrationFileEntity integrationFileEntity = integrationFileDao.getById(order.getIntegrationFileId())
                if (integrationFileEntity != null) {
                    integrationFileDao.deleteEntity(integrationFileEntity)
                }
            }
        }

        where: "Where I give the following data"
        status   | orderId          | time           || correctionStatus | batchStatus | executionStatus    | integrationFileExist
        NEW      | null             | now()          || COMPLETED        | COMPLETED   | SUCCESS            | true
        NEW      | null             | threeDaysAgo() || COMPLETED        | COMPLETED   | SUCCESS            | true
        ERROR    | ORDER_IDENTIFIER | now()          || FAILED           | FAILED      | FAILURE            | false
        PENDING  | ORDER_IDENTIFIER | now()          || COMPLETED        | COMPLETED   | SUCCESS            | true
        PENDING  | ORDER_IDENTIFIER | threeDaysAgo() || COMPLETED        | COMPLETED   | SUCCESS            | true
        RESOLVED | ORDER_IDENTIFIER | now()          || RESOLVED         | COMPLETED   | SUCCESS            | false
        FAILED   | ORDER_IDENTIFIER | now()          || FAILED           | FAILED      | FAILURE            | false
    }

    Timestamp now() {
        return Timestamp.from(Instant.now())
    }

    Timestamp threeDaysAgo() {
        Duration threeDays = Duration.ofDays(3L)
        return Timestamp.from(Instant.now().minus(threeDays))
    }

    CorrectionOrderEntity createOrder(String s, String orderIdentifier, Timestamp timestamp) {
        CorrectionOrderEntity orderEntity = new CorrectionOrderEntity()
        orderEntity.setStatus(s)
        orderEntity.setCorrectionsDate(timestamp)
        orderEntity.setCounter(0)
        orderEntity.setPriority(201)

        if (orderIdentifier != null) orderEntity.setOrderIdentifier(orderIdentifier)
        return orderEntity
    }
}
