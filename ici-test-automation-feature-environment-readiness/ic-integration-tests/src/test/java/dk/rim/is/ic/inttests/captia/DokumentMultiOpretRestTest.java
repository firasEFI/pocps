package dk.rim.is.ic.inttests.captia;

import dk.rim.is.common.CommonURLs;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.IC_HOST;
import static dk.rim.is.ic.inttests.Property.IC_REST_EXTERNAL_PORT;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Integration test for DokumentMultiOpret rest service.
 */
public class DokumentMultiOpretRestTest {

    private static final String SAMPLES_PATH = "build/resources/test/captia/dokumentmultiopret/";
    private static final String VALID_REQUEST_BODY = SAMPLES_PATH + "ValidRequest_body.xml";
    private static String dokumentMultiOpretRestUrl;

    @BeforeClass
    public static void loadProperties() {
        String path = CommonURLs.CAPTIA.ROOT + CommonURLs.CAPTIA.CREATE_DOCUMENTS;
        dokumentMultiOpretRestUrl = toUrl("http", IC_HOST.load(), IC_REST_EXTERNAL_PORT.load(), path);
    }

    @Test
    public void testDokumentMultiOpret_emptyMessage_expect500code() throws Exception {
        given().log().all()
                .body("<ns:DokumentMultiOpret_I xmlns:ns=\"http://skat.dk/begrebsmodel/2009/01/15/\"></ns:DokumentMultiOpret_I>")
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentMultiOpretRestUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void testDokumentMultiOpret_malformedRequest_expect500code() throws Exception {
        String malformedRequest = "<ns:DokumentMultiOpret_I revi";
        given().log().all()
                .body(malformedRequest)
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentMultiOpretRestUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("UnmarshalException"));
    }

    // TODO: 16-02-2017 TOMB please verify response - valid response (with error inside) gets intercepted by camel and transformed into exception with code 500
    @Test
    public void testDokumentMultiOpret_validRequest_unknownProfileName_expect500code() throws Exception {
        given().log().all()
                .body(new File(VALID_REQUEST_BODY))
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentMultiOpretRestUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("DokumentProfilNavn ukendt"));
    }
}
