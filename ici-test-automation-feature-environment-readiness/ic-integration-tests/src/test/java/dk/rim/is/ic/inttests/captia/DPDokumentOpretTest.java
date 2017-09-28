package dk.rim.is.ic.inttests.captia;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static dk.rim.is.ic.inttests.Property.IC_CAPTIA_DPDOKUMENTOPRET_PATH;
import static dk.rim.is.ic.inttests.Property.IC_HOST;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_PORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.SoapUIBuilder.given;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;

/** Checks if sending personFileReady REST request to IC works as expected. */
public class DPDokumentOpretTest {

    private static final Logger LOG = LoggerFactory.getLogger(DPDokumentOpretTest.class);

    private static final String REQUESTS_PATH = "build/resources/test/captia/DPDokumentOpret/";

    private static final String EMPTY_OBJECT_PATH = REQUESTS_PATH + "PersonTestCaseEmptyRequest.xml";
    private static final String MALFORMED_PATH = REQUESTS_PATH + "PersonTestCaseMalformedRequest.xml";
    private static final String MISSING_PASSWORD_PATH = REQUESTS_PATH + "PersonTestCaseMissingPassword.xml";
    private static final String VALID_PATH = REQUESTS_PATH + "ValidRequest.xml";

    private static String serviceUrl;

    @BeforeClass
    public static void loadProperties() {
        serviceUrl = toUrl(IC_SOAP_EXTERNAL_PROTOCOL, IC_HOST, IC_SOAP_EXTERNAL_PORT, IC_CAPTIA_DPDOKUMENTOPRET_PATH);
    }

    @Test
    @Ignore //Still wating for PSRM FORM to enable the route
    public void dpDokumentOpret_whenValid_thenStatusCode200() throws Exception {
        RestAssured.given().log().all()
             //   .when().contentType(APPLICATION_XML_UTF_8)
               // .auth().preemptive().basic("xxx", "xxxx")
                .body(new File(VALID_PATH))
                .when().post(serviceUrl)
                .then().log().all().statusCode(200);
    }

    @Test
    @Ignore //Still wating for PSRM FORM to enable the route
    public void personFileReady_whenEmpty_thenStatusCode500() throws Exception {
        given(EMPTY_OBJECT_PATH).runAgainst(serviceUrl);
    }

    @Test
    @Ignore //Still wating for PSRM FORM to enable the route
    public void personFileReady_whenMalformed_thenStatusCode500() throws Exception {
        given(MALFORMED_PATH).runAgainst(serviceUrl);
    }

    @Test
    @Ignore //Still wating for PSRM FORM to enable the route
    public void personFileReady_whenMissingPassword_thenStatusCode500() throws Exception {
        given(MISSING_PASSWORD_PATH).runAgainst(serviceUrl);
    }
}
