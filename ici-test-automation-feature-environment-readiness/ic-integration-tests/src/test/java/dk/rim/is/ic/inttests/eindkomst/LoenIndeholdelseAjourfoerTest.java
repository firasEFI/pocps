package dk.rim.is.ic.inttests.eindkomst;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.*;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Integration tests of Captia DokumentHent service.
 */
public class LoenIndeholdelseAjourfoerTest {

    //run mock http://localhost:8088/mockDokumentHentBinding

    private static final String SAMPLES_PATH = "build/resources/test/eindkomst/lia/";

    private static final String VALID_REQUEST_EXISTING_CPR_ENVELOPE = SAMPLES_PATH + "ValidRequest_existingCPR_envelope.xml";
    private static final String VALID_REQUEST_NONEXISTING_CPR_ENVELOPE = SAMPLES_PATH + "ValidRequest_nonexistingCPR_envelope.xml";
    private static final String EMPTY_REQUEST_ENVELOPE = SAMPLES_PATH + "Empty_envelope.xml";
    private static final String MALFORMED_REQUEST_ENVELOPE = SAMPLES_PATH + "MalformedRequest.xml";

    private static final String NO_TAX_CARD_FOR_DEBTOR_ERROR_CODE = "F0401";
    private static final String VALID_CPR = "0505859979";

    private static String liaUrl;

    @BeforeClass
    public static void loadProperties() {
        liaUrl = toUrl(IC_SOAP_EXTERNAL_PROTOCOL, IC_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_EINDKOMST_LIA_PATH);
    }

    @Test
    public void testLoenIndholdelseAjourfoer_emptyMessage_expect500code() throws Exception {
        given().log().all()
                .body(new File(EMPTY_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(liaUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void testLoenIndholdelseAjourfoer_malformedRequest_expect500code() throws Exception {
        given().log().all()
                .body(new File(MALFORMED_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(liaUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("Could not parse the XML stream"));
    }

    @Test
    public void testLoenIndholdelseAjourfoer_validRequest_nonexistingCPRNumber_expect200code() throws Exception {
        given().log().all()
                .urlEncodingEnabled(true)
                .body(new File(VALID_REQUEST_NONEXISTING_CPR_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(liaUrl)
                .then().log().all()
                .statusCode(200) // TODO RKU change back to 500 after SKATError interceptor / CalculationEngine issue is solved
                .body(containsString(NO_TAX_CARD_FOR_DEBTOR_ERROR_CODE));
    }

    @Test
    public void testLoenIndholdelseAjourfoer_validRequest_existingCPRNumber_expect200code() throws Exception {
        given().log().all()
                .urlEncodingEnabled(true)
                .body(new File(VALID_REQUEST_EXISTING_CPR_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(liaUrl)
                .then().log().all()
                .statusCode(200)
                .body(containsString(VALID_CPR));
    }
}
