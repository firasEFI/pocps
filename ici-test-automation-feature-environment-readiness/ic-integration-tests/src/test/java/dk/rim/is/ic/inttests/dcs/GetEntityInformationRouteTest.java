package dk.rim.is.ic.inttests.dcs;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import dk.rim.is.ic.inttests.Property;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * SingleSignOnSessionHent endpoint test.
 */
public class GetEntityInformationRouteTest {

    private static final Logger LOG = LoggerFactory.getLogger(GetEntityInformationRouteTest.class);

    private static final String SYSTEM_NAME = "dcs/getentityinformation/";
    private static final String SAMPLES_PATH = "build/resources/test/" + SYSTEM_NAME;

    private static final String VALID_TOKEN_MESSAGE = SYSTEM_NAME + "ValidEnvelope.xml";
    private static final String MALFORMED_MESSAGE = SAMPLES_PATH + "MalformatedEnvelop.xml";
    private static final String EMPTY_MESSAGE = SAMPLES_PATH + "EmptyBodyEnvelope.xml";

    private static final String SKATGUID_PARAM = "${Properties#skatGuid}";

    private static String ssoshUrl;

    @BeforeClass
    public static void loadProperties() {
        String protocol = Property.IC_SOAP_EXTERNAL_PROTOCOL.load();
        String host = Property.IC_HOST.load();
        String port = Property.IC_SOAP_EXTERNAL_INTERNALPORT.load();
        String ssoshServicePath = Property.IC_DCS_GEI_PATH.load();

        ssoshUrl = toUrl(protocol, host, port, ssoshServicePath);
        LOG.info("Using ssoshUrl: " + ssoshUrl);
    }

    @Test
    public void  getEntityInformationTest_invalidToken_expect200code() throws Exception {

        String skatGuid = Property.IC_DCS_SKATGUID_PATH.load();

        URL messageTemplateUrl = Resources.getResource(VALID_TOKEN_MESSAGE);
        String messageTemplate = Resources.toString(messageTemplateUrl, Charsets.UTF_8);
        String message = messageTemplate.replace(SKATGUID_PARAM, skatGuid);

        given().log().all()
                .body(message)
                .when().contentType(APPLICATION_XML_UTF_8)
                .post(ssoshUrl)
                .then().log().all()
                .statusCode(200)
                .body(containsString("GetAdminEntityInformationOutput"))
                .body(containsString("MessageStatusBlock"));
    }

    @Test
    public void getEntityInformationTest_malformedMessage_expect500code() throws Exception {
        given().log().all()
                .body(new File(MALFORMED_MESSAGE))
                .when().contentType(APPLICATION_XML_UTF_8)
                .post(ssoshUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void getEntityInformationTest_emptyMessage_expect500code() throws Exception {
        given().log().all()
                .body(new File(EMPTY_MESSAGE))
                .when().contentType(APPLICATION_XML_UTF_8)
                .post(ssoshUrl)
                .then().log().all()
                .statusCode(500);
    }
}
