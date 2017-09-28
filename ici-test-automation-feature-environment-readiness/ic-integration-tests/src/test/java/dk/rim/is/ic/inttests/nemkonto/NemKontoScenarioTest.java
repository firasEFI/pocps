package dk.rim.is.ic.inttests.nemkonto;

import dk.rim.is.common.CommonURLs;
import dk.rim.is.ic.inttests.Property;
import dk.rim.is.ic.inttests.UrlUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_JSON;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class NemKontoScenarioTest {

    private static final Logger LOG = LoggerFactory.getLogger(NemKontoScenarioTest.class);

    private static final String SAMPLES_PATH = "build/resources/test/nemkonto/";
    private static final String VALID_REQUEST_ENVELOPE = SAMPLES_PATH + "sampleNKSPayment.xml";

    private static String exportFileToNemKontoUrl;
    private static String retrieveFilesFromNemKontoUrl;

    @BeforeClass
    public static void loadProperties() {
        String host = Property.IC_HOST.load();
        String port = Property.IC_REST_EXTERNAL_PORT.load();

        String eksportPath = CommonURLs.NEMKONTO.ROOT + CommonURLs.NEMKONTO.EKSPORT_FILES;
        String retrievePath = CommonURLs.NEMKONTO.ROOT + CommonURLs.NEMKONTO.RETRIEVE_FILES;

        exportFileToNemKontoUrl = UrlUtils.toUrl("http", host, port, eksportPath);
        retrieveFilesFromNemKontoUrl = UrlUtils.toUrl("http", host, port, retrievePath);

        LOG.info("Using exportFileToNemKontoUrl: " + exportFileToNemKontoUrl);
        LOG.info("Using retrieveFilesFromNemKontoUrl: " + retrieveFilesFromNemKontoUrl);
    }

    @Test
    public void testExportFiles_validRequest_expect200code() throws Exception {
        String jmsMessageId = exportFile();

        //Wait for processing by NemKonto 
        Thread.sleep(10000); //TODO Use RDO API

        retrieveFiles(jmsMessageId);
    }

    private String exportFile() {
        return given().log().all()
                .body(new File(VALID_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_JSON).post(exportFileToNemKontoUrl)
                .then().log().all()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("messageId", startsWith("ID:"))
                .extract().path("messageId");
    }

    private void retrieveFiles(String jmsMessageId) {
        given().log().all()
                .body("")
                .when().contentType(APPLICATION_JSON).post(retrieveFilesFromNemKontoUrl)
                .then().log().all()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("receiptFiles", notNullValue())
                .body("receiptFiles", containsString(jmsMessageId));
    }

}
