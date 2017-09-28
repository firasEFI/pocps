package dk.rim.is.ic.inttests.nemkonto;

import dk.rim.is.common.CommonURLs;
//import dk.rim.is.common.entity.managedfiletransfer.IntegrationFileEntity;
//import dk.rim.is.common.entity.managedfiletransfer.IntegrationFileTypeEntity;
import dk.rim.is.ic.error.ErrorCode;
import dk.rim.is.ic.inttests.Property;
import dk.rim.is.ic.inttests.UrlUtils;
import dk.rim.is.ic.inttests.util.GenericDao;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileStatus.GENERATED_BY_PSRM;
import static dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileStep.EXPORT;
import static dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileTypeName.NEMKONTO_PAYMENT;
import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_JSON;
import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_JSON_UTF_8;
import static dk.rim.is.ic.inttests.SpringContext.buildDao;
import static dk.rim.is.ic.inttests.util.file.FileReader.readByteFile;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;

public class ExportNemKontoFilesTest {

    private static final Logger LOG = LoggerFactory.getLogger(ExportNemKontoFilesTest.class);

    private static final String SAMPLES_PATH = "build/resources/test/nemkonto/";
    private static final String VALID_REQUEST = SAMPLES_PATH + "sampleNKSPayment3.xml";

    private static String exportFileToNemKontoUrl;

    private GenericDao<IntegrationFileEntity> fileDao = buildDao(IntegrationFileEntity.class);
    private GenericDao<IntegrationFileTypeEntity> fileTypeDao = buildDao(IntegrationFileTypeEntity.class);

    @BeforeClass
    public static void loadProperties() {
        String host = Property.IC_HOST.load();
        String port = Property.IC_REST_EXTERNAL_PORT.load();
        String path = CommonURLs.NEMKONTO.ROOT + CommonURLs.NEMKONTO.EKSPORT_FILES;
        exportFileToNemKontoUrl = UrlUtils.toUrl("http", host, port, path);
        LOG.info("Using exportFileToNemKontoUrl: " + exportFileToNemKontoUrl);
    }

    @Test
    public void testExportFiles_emptyMessage_expect400code() throws Exception {
        given().log().all()
                .body("")
                .when().contentType(APPLICATION_JSON).post(exportFileToNemKontoUrl)
                .then().log().all()
                .statusCode(400)
                .contentType(APPLICATION_JSON)
                .body("code",equalTo(ErrorCode.EMPTY_REQUEST_MESSAGE.name()))
                .body("description",equalTo("Request message cannot be empty"))
                .body("transactionId",notNullValue())
                .body("exchangeId",notNullValue());
    }

    @Test
    public void testExportFiles_validRequest_expect200code() throws Exception {
        IntegrationFileEntity fileEntity = insertIntegrationFileEntity();
        String jsonMessage = String.format("{\"fileId\":\"%s\"}", fileEntity.getId());

        given().log().all()
                .body(jsonMessage)
                .when().contentType(APPLICATION_JSON_UTF_8).post(exportFileToNemKontoUrl)
                .then().log().all()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("messageId", startsWith("ID:"));

        deleteIntegrationFileEntity(fileEntity);
    }

    private IntegrationFileEntity insertIntegrationFileEntity() throws IOException {
        byte[] messageFileContent = readByteFile(VALID_REQUEST);

        IntegrationFileTypeEntity fileType = fileTypeDao.getBy(entity -> NEMKONTO_PAYMENT.equals(entity.getFileTypeName())).get(0);

        IntegrationFileEntity file = new IntegrationFileEntity();
        file.setUuid(UUID.randomUUID().toString());
        file.setIntegrationFileType(fileType);
        file.setRawFileContents(messageFileContent);

        file.setFileName("testFileName.txt");
        file.setStep(EXPORT);
        file.setStatus(GENERATED_BY_PSRM);

        file.setStatusBy("BATCH_TEST");
        file.setCreatedBy("BATCH_TEST");

        Date testDate = new Date();
        file.setStatusTime(testDate);
        file.setFileGeneratedTime(testDate);
        file.setCreatedTime(testDate);

        fileDao.save(file);
        return file;
    }

    private void deleteIntegrationFileEntity(IntegrationFileEntity entity) {
        fileDao.delete(entity);
    }

}
