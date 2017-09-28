package dk.rim.is.ic.inttests.aogd;

import dk.rim.is.ic.inttests.DatabaseTester;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.DatabaseTesterFactory.createDbTester;
import static dk.rim.is.ic.inttests.Property.IC_AOGD_MEDDELELSEMULTISEND_PATH;
import static dk.rim.is.ic.inttests.Property.IC_INTERNAL_HOST;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_INTERNAL_PORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_INTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Integration tests of MeddelelseMultiSend service.
 */
public class MeddelelseMultiSendTest {
    private static final Logger LOG = LoggerFactory.getLogger(MeddelelseMultiSendTest.class);

    private static final String REQUESTS_PATH = "build/resources/test/aogd/meddelelseMultiSend/";

    private static final String EMPTY_OBJECT_PATH = REQUESTS_PATH + "EmptyRequest.xml";
    private static final String MALFORMED_PATH = REQUESTS_PATH + "MalformedRequest.xml";
    private static final String VALID_PATH = REQUESTS_PATH + "ValidRequest.xml";
    private static final String LETTER_COMPONENT_VALID_PATH = REQUESTS_PATH + "LetterComponentValidRequest.xml";

    private static String meddelelseMultiSendUrl;

    @BeforeClass
    public static void loadProperties() {
        meddelelseMultiSendUrl = toUrl(IC_SOAP_INTERNAL_PROTOCOL, IC_INTERNAL_HOST, IC_SOAP_INTERNAL_PORT, IC_AOGD_MEDDELELSEMULTISEND_PATH);
    }

    @Test
    public void meddelelseMultiSend_whenEmpty_thenStatusCode500() throws Exception {
        given().log().all()
                .body(new File(EMPTY_OBJECT_PATH))
                .when().contentType(APPLICATION_XML_UTF_8).post(meddelelseMultiSendUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void meddelelseMultiSend_whenMalformed_thenStatusCode500() throws Exception {
        given().log().all()
                .body(new File(MALFORMED_PATH))
                .when().contentType(APPLICATION_XML_UTF_8).post(meddelelseMultiSendUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void meddelelseMultiSend_whenValid_thenStatusCode200() throws Exception {
        DatabaseTester dbTester = createDbTester();
        assertTrue(dbTester.tableExists("BREV"));
        int brevCount = dbTester.countEntities("BREV");

        given().log().all()
                .body(new File(VALID_PATH))
                .when().contentType(APPLICATION_XML_UTF_8).post(meddelelseMultiSendUrl)
                .then().log().all()
                .statusCode(200);

        assertThat(dbTester.countEntities("BREV"), is(brevCount + 1));
        assertThat(dbTester.deleteEntities("BREV", "BREV_ID = 98"), greaterThan(0));
    }

    @Test
    public void meddelelseMultiSend_whenLetterComponentValid_thenStatusCode200() throws Exception {
        DatabaseTester dbTester = createDbTester();
        assertTrue(dbTester.tableExists("BREV"));
        int brevCount = dbTester.countEntities("BREV");

        given().log().all()
                .body(new File(LETTER_COMPONENT_VALID_PATH))
                .when().contentType(APPLICATION_XML_UTF_8).post(meddelelseMultiSendUrl)
                .then().log().all()
                .statusCode(200);

        assertThat(dbTester.countEntities("BREV"), is(brevCount + 1));
        assertThat(dbTester.deleteEntities("BREV", "BREV_ID = 75234136800000000752341359420170713"), greaterThan(0));
    }
}
