package dk.rim.is.ic.inttests.sap38;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.*;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Integration tests of SAP38 FinansKontoBilagOpret service.
 */
public class FinansKontoBilagOpretRouteTest {

    private static final Logger LOG = LoggerFactory.getLogger(FinansKontoBilagOpretRouteTest.class);

    private static final String SAMPLES_PATH = "build/resources/test/sap38/";

    private static final String VALID_REQUEST_ENVELOPE = SAMPLES_PATH + "ValidRequest_envelope.xml";
    private static final String MALFORMED_REQUEST_ENVELOPE = SAMPLES_PATH + "MalformedRequest.xml";

    private static String finansKontoUrl;

    @BeforeClass
    public static void loadProperties() {
        finansKontoUrl = toUrl(IC_SOAP_EXTERNAL_PROTOCOL, IC_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_SAP38_FINANSKONTO_PATH);
        LOG.info("Using finansKontoUrl: " + finansKontoUrl);
    }

    @Test
    public void testFinansKonto_malformedRequest_expect500code() throws Exception {
        //@formatter:off
        given().log().all().
                body(new File(MALFORMED_REQUEST_ENVELOPE)).
        when().
                contentType(APPLICATION_XML_UTF_8).
                post(finansKontoUrl).
        then().log().all().
                statusCode(500).
                body(containsString("Error reading"));
        //@formatter:on
    }

    @Test
    public void testFinansKonto_validRequest_invalidDocumentNumber_expect200code() throws Exception {
        //@formatter:off
        given().log().all().
                body(new File(VALID_REQUEST_ENVELOPE)).
        when().
                contentType(APPLICATION_XML_UTF_8).
                post(finansKontoUrl).
        then().log().all().
                statusCode(200).
                body(containsString("<ns2:ServiceID>FinansKontoBilagOpret</ns2:ServiceID>"));
        //@formatter:on
    }
}
