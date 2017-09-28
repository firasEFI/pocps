package dk.rim.is.ic.inttests.slut;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.IC_SLUT_PERSONAARSOPGOERELSEHENT_PATH;
import static dk.rim.is.ic.inttests.Property.IC_HOST;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_INTERNALPORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Integration tests of SLUT service.
 *
 * Created by maho on 29.06.2017.
 */
public class PersonAarsopgoerelseHentTest {

    //run mock http://localhost:50000/SLUT/PersonAarsopgoerelseHentService

    private static final String SAMPLES_PATH = "build/resources/test/slut/";

    private static final String VALID_REQUEST_ENVELOPE = SAMPLES_PATH + "ValidRequest_envelope.xml";
    private static final String EMPTY_REQUEST_ENVELOPE = SAMPLES_PATH + "Empty_envelope.xml";
    private static final String MALFORMED_REQUEST_ENVELOPE = SAMPLES_PATH + "MalformedRequest.xml";

    private static String slutUrl = toUrl(IC_SOAP_EXTERNAL_PROTOCOL, IC_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_SLUT_PERSONAARSOPGOERELSEHENT_PATH);;

    @Test
    public void testSlut_emptyMessage_expect500code() throws Exception {
        given().log().all()
                .body(new File(EMPTY_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(slutUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void testSlut_malformedRequest_expect500code() throws Exception {
        given().log().all()
                .body(new File(MALFORMED_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(slutUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("Could not parse the XML stream"));
    }

    @Test
    public void testSlut_validRequest_expect200code() throws Exception {
        given().log().all()
                .body(new File(VALID_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(slutUrl)
                .then().log().all()
                .statusCode(200)
                .body(containsString("PersonÅrsopgørelseHent_O"));
    }
}
