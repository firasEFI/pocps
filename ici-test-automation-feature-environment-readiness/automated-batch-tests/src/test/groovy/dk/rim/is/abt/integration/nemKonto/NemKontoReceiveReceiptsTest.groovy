package dk.rim.is.abt.integration.nemKonto

import dk.rim.is.abt.dao.cisadm.IntegrationFileEntity
import dk.rim.is.abt.dao.cisadm.IntegrationFileTypeEntity
import dk.rim.is.abt.util.BatchController
import dk.rim.is.abt.util.Byter
import dk.rim.is.abt.util.GenericDao
import org.junit.After
import org.junit.Before
import org.junit.Test

import java.sql.Timestamp
import java.util.function.Predicate

import static dk.rim.is.abt.util.SpringContextCisadm.buildDao
import static dk.rim.is.abt.util.file.FileReader.readByteFile
import static dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileStatus.GENERATED_BY_PSRM
import static org.assertj.core.api.Assertions.assertThat

class NemKontoReceiveReceiptsTest {

	private static final String JOB_DESCRIPTION = "NemKontoReceiveReceipts"
	private static final String STATUS = "RETRIEVED_BY_INTEGRATION"
	private static final String VALID_REQUEST = "/nemkonto/sampleNKSPayment3.xml"
	private static final String NEMKONTO_PAYMENT = "NEMKONTO_PAYMENT"

	private GenericDao<IntegrationFileEntity> fileDao =  buildDao(IntegrationFileEntity.class)
	private GenericDao<IntegrationFileTypeEntity> fileTypeDao =  buildDao(IntegrationFileTypeEntity.class)

	IntegrationFileTypeEntity fileType
	IntegrationFileEntity file
	long fileId

	Timestamp startTime

	@Test
	void testBatchReturnCode() throws Exception {
		def jobReturnCode = BatchController.runAndReportIntegrationJob(JOB_DESCRIPTION)
		assert jobReturnCode.executionStatus == "SUCCESS"

		checkStatusInDb()
	}

	@Before
	void setUp() {
		startTime = new Timestamp(System.currentTimeMillis())
		insertFileEntity()
		BatchController.runAndReportIntegrationJob("NemKontoExportPayments")
	}

	@After
	void cleanDb() {
		fileDao.delete({entity -> file.getId().equals(entity.getId())} as Predicate)
		fileDao.delete({entity -> STATUS.equals(entity.getStatus())} as Predicate)
	}

	void checkStatusInDb() {
		List<IntegrationFileEntity> files = fileDao.getBy({ entity -> STATUS.equals(entity.getStatus()) && entity.getStatusTime() >= startTime } as Predicate)

		assertThat(files).hasSize(1)
	}

	void insertFileEntity() {

		byte[] messageFileContent = Byter.toByteArray(this.getClass().getResourceAsStream(VALID_REQUEST))

		fileType = fileTypeDao.getBy({ entity -> NEMKONTO_PAYMENT.equals(entity.getFileTypeName())} as Predicate).get(0)

		file = new IntegrationFileEntity()

		file.setUuid(UUID.randomUUID().toString())
		file.setIntegrationFileTypeId(fileType.getId())
		file.setRawFileContents(messageFileContent)
		file.setFilename("testFileName.txt")
		file.setStep("EXPORT")
		file.setStatus(GENERATED_BY_PSRM as String)
		file.setStatusBy("BATCH_TEST")
		file.setCreatedBy("BATCH_TEST")
		Timestamp timestamp = new Timestamp(System.currentTimeMillis())
		file.setStatusTime(timestamp)
		file.setFileGeneratedTime(timestamp)
		file.setCreatedTime(timestamp)

		fileDao.save(file)
		fileId=file.getId()
	}

}
