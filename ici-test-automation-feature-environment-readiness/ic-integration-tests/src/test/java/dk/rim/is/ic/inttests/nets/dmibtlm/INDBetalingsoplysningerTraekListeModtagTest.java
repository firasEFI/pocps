package dk.rim.is.ic.inttests.nets.dmibtlm;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import dk.rim.is.ic.inttests.Property;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.util.RequestParameters.SAML_HEADER;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Integration test for DMIBTLM - receiving information from Nets that file is ready to be downloaded.
 */
public class INDBetalingsoplysningerTraekListeModtagTest {

    private static final Logger LOG = LoggerFactory.getLogger(INDBetalingsoplysningerTraekListeModtagTest.class);

    private static final String SAMPLES_PATH = "styretfiloverforsel/";

    private static final String VALID_MODTAGANMOD_REQUEST_ENVELOPE = SAMPLES_PATH + "StyretFiloverfoerselModtagAnmod_I_Request.xml";
    private static final String VALID_MODTAGAFSLUT_REQUEST_ENVELOPE = SAMPLES_PATH + "StyretFiloverfoerselModtagAfslut_I_Request.xml";

    private static String dmibtlmServiceUrl;

    @BeforeClass
    public static void loadProperties() {
        String protocol = Property.IC_SOAP_EXTERNAL_PROTOCOL.load();
        String host = Property.IC_HOST.load();
        String port = Property.IC_SOAP_EXTERNAL_PORT.load();
        String path = Property.IC_NETS_INDBTLM_PATH.load();
        dmibtlmServiceUrl = protocol + "://" + host + ":" + port + "/" + path;
        LOG.info("Using dmibtlmServiceUrl: " + dmibtlmServiceUrl);
    }

    @Test
    @Ignore
    public void styretfilover_modtagAnmod_valid_message_expect200code() throws Exception {
        URL messageBodyUrl = Resources.getResource(VALID_MODTAGANMOD_REQUEST_ENVELOPE);
        String messageBody = Resources.toString(messageBodyUrl, Charsets.UTF_8);

        messageBody = messageBody.replace(SAML_HEADER, ""); //TODO add saml header to request IS-15528
        given().log().all()
                .body(messageBody)
                .when().contentType(APPLICATION_XML_UTF_8).post(dmibtlmServiceUrl)
                .then().log().all()
                .statusCode(200)
                .body(containsString("StyretFiloverførselModtagAnmod_O"))
                .body(containsString("INDBetalingsoplysningerTraekListeModtagService"));
    }

    @Test
    public void when_modtagAfslut_is_called__is_not_supported() throws Exception {
        URL messageBodyUrl = Resources.getResource(VALID_MODTAGAFSLUT_REQUEST_ENVELOPE);
        String messageBody = Resources.toString(messageBodyUrl, Charsets.UTF_8);

        messageBody = messageBody.replace(SAML_HEADER, ""); //TODO add saml header to request IS-15528
        given().log().all()
                .body(messageBody)
                .when().contentType(APPLICATION_XML_UTF_8).post(dmibtlmServiceUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("<faultcode>soap:Server</faultcode>"))
                .body(containsString("Operation not supported"));
    }
}
