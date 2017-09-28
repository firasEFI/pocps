package dk.rim.is.ic.inttests.captia;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import dk.rim.is.ic.inttests.Property;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Captia case scenario test.
 */
public class CaptiaCaseScenarioTest {

    //run captia mocks

    private static final Logger LOG = LoggerFactory.getLogger(DokumentHentTest.class);

    private static final String SAMPLES_PATH = "build/resources/test/captia/sagScenario/";
    private static final String SAMPLES_SHORT_PATH = "captia/sagScenario/";

    private static final String CREATE_CASE_MESSAGE = SAMPLES_PATH + "CreateCase_envelope.xml";
    private static final String UPDATE_CASE_MESSAGE = SAMPLES_SHORT_PATH + "UpdateCase_envelope.xml";

    private static String sagOpretUrl;
    private static String sagOpdaterUrl;

    private final String CASE_NUMBER_PARAM = "${Properties#SagNummer}";

    @BeforeClass
    public static void loadProperties() {
        String protocol = Property.IC_SOAP_EXTERNAL_PROTOCOL.load();
        String host = Property.IC_HOST.load();
        String port = Property.IC_SOAP_EXTERNAL_INTERNALPORT.load();

        sagOpretUrl = protocol + "://" + host + ":" + port + "/" + Property.IC_CAPTIA_SAGOPRET_PATH.load();
        sagOpdaterUrl = protocol + "://" + host + ":" + port + "/" + Property.IC_CAPTIA_SAGOPDATER_PATH.load();

        LOG.info("Using sagOpretUrl: " + sagOpretUrl);
        LOG.info("Using sagOpdaterUrl: " + sagOpdaterUrl);
    }

    @Test
    public void testDocumentCreationModification_validRequest_expect200code() throws Exception {
        String caseNumber = createNewCase();
        updateCase(caseNumber);
    }

    private String createNewCase() {
        return given().log().all()
                .body(new File(CREATE_CASE_MESSAGE))
                .header("TRANSACTION_ID", "someTestTransactionId")
                .when().contentType(APPLICATION_XML_UTF_8).post(sagOpretUrl)
                .then().log().all()
                .statusCode(200)
                .body("Envelope.Body.SagOpret_O.SagNummer", notNullValue())
                .extract().path("Envelope.Body.SagOpret_O.SagNummer");
    }

    private void updateCase(String caseNumber) throws IOException {
        URL messageTemplateUrl = Resources.getResource(UPDATE_CASE_MESSAGE);
        String messageTemplate = Resources.toString(messageTemplateUrl, Charsets.UTF_8);
        String message = messageTemplate.replace(CASE_NUMBER_PARAM, caseNumber);

        given().log().all()
                .body(message)
                .when().contentType(APPLICATION_XML_UTF_8).post(sagOpdaterUrl)
                .then().log().all()
                .statusCode(200)
                .body("Envelope.Body.SagOpdater_O", notNullValue());
    }

}
