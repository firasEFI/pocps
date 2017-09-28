package dk.rim.is.ic.inttests.captia;

import dk.rim.is.ic.inttests.RegexMatcher;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.IC_CAPTIA_SAGOPRET_PATH;
import static dk.rim.is.ic.inttests.Property.IC_HOST;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_INTERNALPORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

/**
 * Integration tests of Captia SagOpret service.
 */
public class SagOpretTest {

    //run mock http://localhost:8088/mockSagOpretServiceSoapBinding

    private static final Logger LOG = LoggerFactory.getLogger(SagOpretTest.class);

    private static final String SAMPLES_PATH = "build/resources/test/captia/sagOpret/";

    private static final String VALID_REQUEST_ENVELOPE = SAMPLES_PATH + "ValidRequest_envelope.xml";
    private static final String EMPTY_REQUEST_ENVELOPE = SAMPLES_PATH + "Empty_envelope.xml";
    private static final String MALFORMED_REQUEST_ENVELOPE = SAMPLES_PATH + "MalformedRequest.xml";

    private static String sagOpretUrl;

    @BeforeClass
    public static void loadProperties() {
        sagOpretUrl = toUrl(IC_SOAP_EXTERNAL_PROTOCOL, IC_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_CAPTIA_SAGOPRET_PATH);
    }

    @Test
    public void testSagOpret_emptyMessage_expect500code() throws Exception {
        given().log().all()
                .body(new File(EMPTY_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(sagOpretUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void testSagOpret_malformedRequest_expect500code() throws Exception {
        given().log().all()
                .body(new File(MALFORMED_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(sagOpretUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("Could not parse the XML stream"));
    }

    @Test
    public void testSagOpret_validRequest_expect200code() throws Exception {
        given().log().all()
                .body(new File(VALID_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(sagOpretUrl)
                .then().log().all()
                .statusCode(200)
                .body("Envelope.Body.SagOpret_O.SagNummer", notNullValue())
                .body("Envelope.Body.SagOpret_O.SagNummer", RegexMatcher.matchesRegex("\\d{2}-\\d{7}"));
    }

}
