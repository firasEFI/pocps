package dk.rim.is.ic.inttests.captia;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.IC_CAPTIA_DOKUMENTHENT_PATH;
import static dk.rim.is.ic.inttests.Property.IC_HOST;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_INTERNALPORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Integration tests of Captia DokumentHent service.
 */
public class DokumentHentTest {

    //run mock http://localhost:8088/mockDokumentHentBinding

    private static final String SAMPLES_PATH = "build/resources/test/captia/dokumentHent/";

    private static final String VALID_REQUEST_ENVELOPE = SAMPLES_PATH + "ValidRequest_envelope.xml";
    private static final String EMPTY_REQUEST_ENVELOPE = SAMPLES_PATH + "Empty_envelope.xml";
    private static final String MALFORMED_REQUEST_ENVELOPE = SAMPLES_PATH + "MalformedRequest.xml";

    private static String dokumentHentUrl;

    @BeforeClass
    public static void loadProperties() {
        dokumentHentUrl = toUrl(IC_SOAP_EXTERNAL_PROTOCOL, IC_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_CAPTIA_DOKUMENTHENT_PATH);
    }

    @Test
    public void testDokumentHent_emptyMessage_expect500code() throws Exception {
        given().log().all()
                .body(new File(EMPTY_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentHentUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void testDokumentHent_malformedRequest_expect500code() throws Exception {
        given().log().all()
                .body(new File(MALFORMED_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentHentUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("Could not parse the XML stream"));
    }

    @Test
    public void testDokumentHent_validRequest_nonexistingDocumentNumber_expect500code() throws Exception {
        given().log().all()
                .urlEncodingEnabled(true)
                .body(new File(VALID_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentHentUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("Det dokument som forsøges opdateret findes ikke"));
    }
}
