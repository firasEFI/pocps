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
 * TopNiveauProcesRolleGruppeSamlingHentTest endpoint test.
 */
public class TopNiveauProcesRolleGruppeSamlingHentTest {

    private static final Logger LOG = LoggerFactory.getLogger(SingleSignOnSessionHentTest.class);

    //todo change
    private static final String SAMPLES_PATH = "build/resources/test/dcs/topNiveauProcesRolleGruppeSamlingHent/";

    //todo change
    private static final String VALID_GUID_MESSAGE = SAMPLES_PATH + "ValidGUID_envelope.xml";
    private static final String MALFORMED_MESSAGE = SAMPLES_PATH + "MalformedMessage_envelope.xml";
    private static final String EMPTY_MESSAGE = SAMPLES_PATH + "EmptyMessage_envelope.xml";

    private static String tnprgshUrl;

    // credentials basic auth for AuthenticationProxy
    private static String username;
    private static String password;

    @BeforeClass
    public static void loadProperties() {
        String protocol = Property.IC_SOAP_EXTERNAL_PROTOCOL.load();
        String host = Property.IC_HOST.load();
        String port = Property.IC_SOAP_EXTERNAL_INTERNALPORT.load();
        String tnprgshServicePath = Property.IC_DCS_TNPRGSH_PATH.load();

        tnprgshUrl = toUrl(protocol, host, port, tnprgshServicePath);
        LOG.info("Using ssoshUrl: " + tnprgshUrl);
    }

    @Test
    public void TopNiveauProcesRolleGruppeSamlingHentTest_validGuid_expect200code() throws Exception {
        given().log().all()
                .body(new File(VALID_GUID_MESSAGE))
                .when().contentType(APPLICATION_XML_UTF_8)
                .post(tnprgshUrl)
                .then().log().all()
                .statusCode(200)
                .body(containsString("getTopLevelProcessRoleGroupsOutput"))
                .body(containsString("TopLevelProcessRoleGroupId"));
    }

    @Test
    public void TopNiveauProcesRolleGruppeSamlingHentTest_malformedMessage_expect500code() throws Exception {
        given().log().all()
                .body(new File(MALFORMED_MESSAGE))
                .when().contentType(APPLICATION_XML_UTF_8)
                .post(tnprgshUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void TopNiveauProcesRolleGruppeSamlingHentTest_emptyMessage_expect500code() throws Exception {
        given().log().all()
                .body(new File(EMPTY_MESSAGE))
                .when().contentType(APPLICATION_XML_UTF_8)
                .post(tnprgshUrl)
                .then().log().all()
                .statusCode(500);
    }
}
