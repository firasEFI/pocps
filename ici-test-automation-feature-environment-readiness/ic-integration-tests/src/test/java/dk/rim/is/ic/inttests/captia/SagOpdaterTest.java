package dk.rim.is.ic.inttests.captia;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.IC_CAPTIA_SAGOPDATER_PATH;
import static dk.rim.is.ic.inttests.Property.IC_HOST;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_INTERNALPORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Integration tests of Captia SagOpdater service.
 */
public class SagOpdaterTest {


    //run mock http://localhost:8088/mockSagOpdaterServicePortTypeBinding

    private static final String SAMPLES_PATH = "build/resources/test/captia/sagOpdater/";

    private static final String VALID_REQUEST_ENVELOPE = SAMPLES_PATH + "ValidRequest_envelope.xml";
    private static final String EMPTY_REQUEST_ENVELOPE = SAMPLES_PATH + "Empty_envelope.xml";
    private static final String MALFORMED_REQUEST_ENVELOPE = SAMPLES_PATH + "MalformedRequest.xml";

    private static String sagOpdaterUrl;

    @BeforeClass
    public static void loadProperties() {
        sagOpdaterUrl = toUrl(IC_SOAP_EXTERNAL_PROTOCOL, IC_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_CAPTIA_SAGOPDATER_PATH);
    }

    @Test
    public void testSagOpdater_emptyMessage_expect500code() throws Exception {
        given().log().all()
                .body(new File(EMPTY_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(sagOpdaterUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void testSagOpdater_malformedRequest_expect500code() throws Exception {
        given().log().all()
                .body(new File(MALFORMED_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(sagOpdaterUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("Could not parse the XML stream"));
    }

    // TODO: 16-02-2017 TOMB please verify response - valid response (with error inside) gets intercepted by camel and transformed into exception with code 500
    @Test
    public void testSagOpdater_validRequest_unknownProfileName_expect500code() throws Exception {
        given().log().all()
                .body(new File(VALID_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(sagOpdaterUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("SagProfilnavn ukendt"));
    }

}
