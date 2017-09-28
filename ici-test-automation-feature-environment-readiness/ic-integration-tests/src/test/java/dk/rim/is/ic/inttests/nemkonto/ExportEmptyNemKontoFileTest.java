package dk.rim.is.ic.inttests.nemkonto;

import dk.rim.is.common.CommonURLs;
import dk.rim.is.ic.error.ErrorCode;
import dk.rim.is.ic.inttests.Property;
import dk.rim.is.ic.inttests.UrlUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_JSON;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

//import dk.rim.is.common.entity.managedfiletransfer.IntegrationFileEntity;
//import dk.rim.is.common.entity.managedfiletransfer.IntegrationFileTypeEntity;

public class ExportEmptyNemKontoFileTest {

    private static final Logger LOG = LoggerFactory.getLogger(ExportNemKontoFilesTest.class);

    private static final String SAMPLES_PATH = "build/resources/test/nemkonto/";

    private static String exportFileToNemKontoUrl;

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

}
