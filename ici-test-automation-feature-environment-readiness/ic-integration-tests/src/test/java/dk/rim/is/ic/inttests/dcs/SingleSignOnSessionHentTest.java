package dk.rim.is.ic.inttests.dcs;

import dk.rim.is.ic.inttests.Property;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * SingleSignOnSessionHent endpoint test.
 */
public class SingleSignOnSessionHentTest {

    private static final Logger LOG = LoggerFactory.getLogger(SingleSignOnSessionHentTest.class);

    private static final String SAMPLES_PATH = "build/resources/test/dcs/singleSignOnSessionHent/";

    private static final String INVALID_TOKEN_MESSAGE = SAMPLES_PATH + "InvalidToken_envelope.xml";
    private static final String MALFORMED_MESSAGE = SAMPLES_PATH + "MalformedMessage_envelope.xml";
    private static final String EMPTY_MESSAGE = SAMPLES_PATH + "EmptyMessage_envelope.xml";

    private static String ssoshUrl;

    @BeforeClass
    public static void loadProperties() {
        String protocol = Property.IC_SOAP_EXTERNAL_PROTOCOL.load();
        String host = Property.IC_HOST.load();
        String port = Property.IC_SOAP_EXTERNAL_INTERNALPORT.load();
        String ssoshServicePath = Property.IC_DCS_SSOSH_PATH.load();

        ssoshUrl =  toUrl(protocol, host, port, ssoshServicePath);
        LOG.info("Using ssoshUrl: " + ssoshUrl);
    }

    @Test
    public void SingleSignOnSessionHentTest_invalidToken_expect200code() throws Exception {
        given().log().all()
                .body(new File(INVALID_TOKEN_MESSAGE))
                .when().contentType(APPLICATION_XML_UTF_8)
                .post(ssoshUrl)
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void SingleSignOnSessionHentTest_malformedMessage_expect500code() throws Exception {
        given().log().all()
                .body(new File(MALFORMED_MESSAGE))
                .when().contentType(APPLICATION_XML_UTF_8)
                .post(ssoshUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void SingleSignOnSessionHentTest_emptyMessage_expect500code() throws Exception {
        given().log().all()
                .body(new File(EMPTY_MESSAGE))
                .when().contentType(APPLICATION_XML_UTF_8)
                .post(ssoshUrl)
                .then().log().all()
                .statusCode(500);
    }
}
