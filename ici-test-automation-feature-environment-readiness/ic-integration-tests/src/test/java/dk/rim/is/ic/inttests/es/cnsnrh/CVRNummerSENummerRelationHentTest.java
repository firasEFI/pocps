package dk.rim.is.ic.inttests.es.cnsnrh;

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

public class CVRNummerSENummerRelationHentTest {
    private static final Logger LOG = LoggerFactory.getLogger(CVRNummerSENummerRelationHentTest.class);

    private static final String SAMPLES_PATH = "es/cnsnrh/";
    private static final String REQUEST_MESSAGE = SAMPLES_PATH + "ValidRequest_envelope.xml";

    private static String esUrl;

    @BeforeClass
    public static void loadProperties() {
        String protocol = Property.IC_SOAP_EXTERNAL_PROTOCOL.load();
        String host = Property.IC_HOST.load();
        String port = Property.IC_SOAP_EXTERNAL_INTERNALPORT.load();
        String esServicePath = Property.IC_ES_CNSNRH_PATH.load();

        esUrl = toUrl(protocol, host, port, esServicePath);
        LOG.info("Using eshUrl: " + esUrl);
    }

    @Test
    public void CVRNummerSENummerRelationHent_validRequest_expect200code() throws Exception {
        URL messageBodyUrl = Resources.getResource(REQUEST_MESSAGE);
        String messageBody = Resources.toString(messageBodyUrl, Charsets.UTF_8);
        String transaktionsId = "NC_SAML_TOKEN_EST-20170620-0002";
        String transaktionsTidId = "2017-06-20T12:30:07";
        String seNummer = "31597048";

        given().log().all()
                .body(messageBody
                        .replace("TRANSAKTIONS_ID", transaktionsId)
                        .replace("TRANSAKTIONS_TID", transaktionsTidId)
                        .replace("SE_NUMMER", seNummer)
                )
                .when()
                .contentType(APPLICATION_XML_UTF_8)
                .post(esUrl)
                .then().log().all()
                .statusCode(200)
                .body(containsString("CVRNummerSENummerRelationHent_O"),
                        not(anyOf(containsString("null:Kontekst"), containsString("Fejl"), containsString("FejlNummer"))));
    }

}
