package dk.rim.is.ic.inttests.es.dmifoh;


import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import dk.rim.is.ic.inttests.Property;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Base64;
import java.util.UUID;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class DMIFordringOverblikHentTest {
    private static final Logger LOG = LoggerFactory.getLogger(DMIFordringOverblikHentTest.class);

    private static final String SAMPLES_PATH = "es/dmifoh/";
    private static final String REQUEST_MESSAGE = SAMPLES_PATH + "ValidRequest_envelope.xml";

    private static String dmifohUrl;
    private static String user;
    private static String pass;

    @BeforeClass
    public static void loadProperties() {
        String protocol = Property.IC_SOAP_EXTERNAL_PROTOCOL.load();
        String host = Property.IC_HOST.load();
        String port = Property.IC_SOAP_EXTERNAL_PORT.load();
        String esServicePath = Property.IC_ES_DMIFOH_PATH.load();
        String username = Property.IC_ES_DMIFOH_USERNAME.load();
        String password = Property.IC_ES_DMIFOH_PASSWORD.load();

        dmifohUrl = toUrl(protocol, host, port, esServicePath);
        user = username;
        pass = password;
        LOG.info("Using dmifohUrl: " + dmifohUrl + " user: " + user + " pass: " + pass);
    }

    @Test
    @Ignore // TODO: JARA - reenable this task after R 1.2
    public void FordringOverblik_validRequest_expect200code() throws Exception {
        URL messageBodyUrl = Resources.getResource(REQUEST_MESSAGE);
        String messageBody = Resources.toString(messageBodyUrl, Charsets.UTF_8);
        String authenticationHeader = "Basic " + Base64.getEncoder().encodeToString((user + ":" + pass).getBytes());

        given().log().all()
                .header("Authorization", authenticationHeader)
                .body(messageBody.replace("TRANSACTION_ID", UUID.randomUUID().toString()))
                .when()
                .contentType(APPLICATION_XML_UTF_8)
                .post(dmifohUrl)
                .then().log().all()
                .statusCode(200)
                .body(containsString("0505763661"));
    }
}
