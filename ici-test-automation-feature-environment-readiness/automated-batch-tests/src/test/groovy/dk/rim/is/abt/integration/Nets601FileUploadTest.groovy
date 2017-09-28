package dk.rim.is.abt.integration

import dk.rim.is.abt.util.BatchController
import dk.rim.is.abt.util.Byter
import dk.rim.is.abt.util.FtpDownloader
import dk.rim.is.abt.util.GenericDao
import dk.rim.is.abt.dao.cisadm.IntegrationFileEntity
import dk.rim.is.abt.dao.cisadm.IntegrationFileTypeEntity
import org.junit.Before
import org.junit.Test

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.function.Predicate

import static dk.rim.is.abt.util.SpringContext.buildDao
/**
 * Created by mlo on 10-05-2017.
 */
class Nets601FileUploadTest {
    private static final String SAMPLE_DATA =  "/nets/sample.dat"
    private GenericDao<IntegrationFileEntity> fileDao =  buildDao(IntegrationFileEntity.class)
    private GenericDao<IntegrationFileTypeEntity> fileTypeDao = buildDao(IntegrationFileTypeEntity.class)

    private String testUuid

    @Test
    void checkServerStatus(){
        assert FtpDownloader.ftpgwListFiles().size() > 0
    }

    @Test
    void shouldSendFileFromPsrmToNets_and_receiveCallbackFromNets() throws Exception {
        //given
        insertTestRow()

        def jobStatus = BatchController.runAndReportIntegrationJob ("Nets601FileUpload")
        assert jobStatus.executionStatus == "SUCCESS"

        //Check file is on ftp here
    }

    private void insertTestRow() throws IOException {

        testUuid = UUID.randomUUID().toString()
        IntegrationFileTypeEntity fileType = fileTypeDao.getBy{entity -> "NETS_601".equals(entity.getFileTypeName())}.get(0)

        IntegrationFileEntity file = new IntegrationFileEntity()
        file.setUuid(testUuid)
        file.setIntegrationFileTypeId(fileType.getId())
        file.setId(0l)
        file.setRawFileContents(Byter.toByteArray(getClass().getResourceAsStream(SAMPLE_DATA)))

        String filename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_testFileName.xml"

        file.setFilename(filename)
        file.setStep("EXPORT")
        file.setStatus("GENERATED_BY_PSRM")

        file.setStatusBy("BATCH_TEST")
        file.setCreatedBy("BATCH_TEST")

        Timestamp timestamp = new Timestamp(System.currentTimeMillis())
        file.setStatusTime(timestamp)
        file.setFileGeneratedTime(timestamp)
        file.setCreatedTime(timestamp)

        fileDao.save(file)
    }

    @Before
    void prepareDatabase() {
        if (fileDao.getBy({p -> p.getFilename().contains("testFileName.txt")} as Predicate).size() > 0) {
            fileDao.delete({ p -> p.getFilename().contains("testFileName.txt") } as Predicate)
        }
    }
}
