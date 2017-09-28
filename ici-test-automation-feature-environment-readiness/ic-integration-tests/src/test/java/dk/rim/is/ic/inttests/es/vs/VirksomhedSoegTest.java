package dk.rim.is.ic.inttests.es.vs;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import dk.rim.is.ic.inttests.Property;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

public class VirksomhedSoegTest {
    private static final Logger LOG = LoggerFactory.getLogger(VirksomhedSoegTest.class);

    private static final String SAMPLES_PATH = "es/vs/";
    private static final String REQUEST_MESSAGE = SAMPLES_PATH + "ValidRequest_envelope.xml";

    private static String esUrl;

    @BeforeClass
    public static void loadProperties() {
        String protocol = Property.IC_SOAP_EXTERNAL_PROTOCOL.load();
        String host = Property.IC_HOST.load();
        String port = Property.IC_SOAP_EXTERNAL_INTERNALPORT.load();
        String esServicePath = Property.IC_ES_VS_PATH.load();

        esUrl = toUrl(protocol, host, port, esServicePath);
        LOG.info("Using eshUrl: " + esUrl);
    }

    @Test
    public void VirksomhedSoeg_validRequest_expect200code() throws Exception {
        URL messageBodyUrl = Resources.getResource(REQUEST_MESSAGE);
        String messageBody = Resources.toString(messageBodyUrl, Charsets.UTF_8);
        String transactionId = "NC_SAML_TOKEN_EST-20170626-0001";
        String seNumber = "31597048";

        given().log().all()
                .body(messageBody
                        .replace("TRANSACTIONS_ID", transactionId)
                        .replace("TRANSAKTIONS_TID", "2016-06-20T16:30:07")
                        .replace("VIRKSOMHED_CVR_NUMMER", seNumber))
                .when()
                .contentType(APPLICATION_XML_UTF_8)
                .post(esUrl)
                .then().log().all()
                .statusCode(200)
                .body(containsString(transactionId),
                        not(anyOf(containsString("Fejl"), containsString("FejlNummer"))));
    }

}
