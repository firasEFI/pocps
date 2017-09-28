package dk.rim.is.ic.inttests.es.evrh;

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

public class EjerVirksomhedRelationHentTest {
    private static final Logger LOG = LoggerFactory.getLogger(EjerVirksomhedRelationHentTest.class);

    private static final String SAMPLES_PATH = "es/evrh/";
    private static final String REQUEST_MESSAGE = SAMPLES_PATH + "ValidRequest_envelope.xml";

    private static String esUrl;

    @BeforeClass
    public static void loadProperties() {
        String protocol = Property.IC_SOAP_EXTERNAL_PROTOCOL.load();
        String host = Property.IC_HOST.load();
        String port = Property.IC_SOAP_EXTERNAL_INTERNALPORT.load();
        String esServicePath = Property.IC_ES_EVRH_PATH.load();

        esUrl = toUrl(protocol, host, port, esServicePath);
        LOG.info("Using eshUrl: " + esUrl);
    }

    @Test
    public void EjerVirksomhedRelationHent_validRequest_expect200code() throws Exception {
        URL messageBodyUrl = Resources.getResource(REQUEST_MESSAGE);
        String messageBody = Resources.toString(messageBodyUrl, Charsets.UTF_8);
        String transaktionsId = "NC_SAML_TOKEN_EST-20170619-0001";
        String transaktionsTidId = "2016-06-20T16:30:07";
        String cprnNummer = "xxxx879902";

        given().log().all()
                .body(messageBody
                        .replace("TRANSAKTIONS_ID", transaktionsId)
                        .replace("TRANSAKTIONS_TID", transaktionsTidId)
                        .replace("CPRN_NUMMER", cprnNummer)
                )
                .when()
                .contentType(APPLICATION_XML_UTF_8)
                .post(esUrl)
                .then().log().all()
                .statusCode(200)
                .body(containsString("EjerVirksomhedRelationHent_O"),
                        not(anyOf(containsString("null:Kontekst"), containsString("Fejl"), containsString("FejlNummer"))));
    }
}
