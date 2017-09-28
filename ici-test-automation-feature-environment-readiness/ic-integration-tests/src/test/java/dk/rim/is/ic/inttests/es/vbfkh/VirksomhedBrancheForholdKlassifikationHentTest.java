package dk.rim.is.ic.inttests.es.vbfkh;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import dk.rim.is.ic.inttests.Property;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.Clock;
import java.time.Instant;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

public class VirksomhedBrancheForholdKlassifikationHentTest {
    private static final Logger LOG = LoggerFactory.getLogger(VirksomhedBrancheForholdKlassifikationHentTest.class);

    private static final String SAMPLES_PATH = "es/vbfkh/";
    private static final String REQUEST_MESSAGE = SAMPLES_PATH + "ValidRequest_envelope.xml";

    private static String esUrl;

    @BeforeClass
    public static void loadProperties() {
        String protocol = Property.IC_SOAP_EXTERNAL_PROTOCOL.load();
        String host = Property.IC_HOST.load();
        String port = Property.IC_SOAP_EXTERNAL_INTERNALPORT.load();
        String esServicePath = Property.IC_ES_VBFKH_PATH.load();

        esUrl = toUrl(protocol, host, port, esServicePath);
        LOG.info("Using eshUrl: " + esUrl);
    }

    @Test
    public void VirksomhedBrancheForholdKlassifikation_validRequest_expects200code() throws Exception {
        URL messageBodyUrl = Resources.getResource(REQUEST_MESSAGE);
        String messageBody = Resources.toString(messageBodyUrl, Charsets.UTF_8);
        String transactionId = "NC_SAML_TOKEN_EST-20170620-0001";
        String seNumber = "31597048";

        given().log().all()
                .body(messageBody
                    .replace("TRANSACTION_ID", transactionId)
                    .replace("INSERT_TID_HERE", Instant.now(Clock.systemUTC()).toString())
                    .replace("INSERT_SE_NUMBER", seNumber))
                .when()
                .contentType(APPLICATION_XML_UTF_8)
                .post(esUrl)
                .then().log().all()
                .statusCode(200)
                .body(containsString(transactionId),
                    not(anyOf(containsString("Fejl"), containsString("FejlNummer"))));
    }
}
