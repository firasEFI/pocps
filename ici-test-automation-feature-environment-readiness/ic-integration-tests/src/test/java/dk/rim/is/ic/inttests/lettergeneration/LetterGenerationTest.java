package dk.rim.is.ic.inttests.lettergeneration;

import dk.rim.is.ic.inttests.dcs.GetEntityInformationRouteTest;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.*;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Integration tests of Letter Generetion service.
 */
public class LetterGenerationTest {

    private static final Logger LOG = LoggerFactory.getLogger(LetterGenerationTest.class);

    private static final String SAMPLES_PATH = "build/resources/test/lettergeneration/";
    private static final String REQUEST = SAMPLES_PATH + "request.xml";
    private static final String REQUEST_EMPTY_MEDDELELSE = SAMPLES_PATH + "request_empty_meddelelse.xml";
    private static final String REQUEST_EMPTY_MEDDELELSEINDHOLD = SAMPLES_PATH + "request_empty_meddelelseIndhold.xml";
    private static final String REQUEST_EMPTY_MEDDELELSEAFSENDERREFERENCE = SAMPLES_PATH + "request_empty_meddelelseAfsenderReference.xml";
    private static final String REQUEST_EMPTY_MEDDELELSETYPENUMMER = SAMPLES_PATH + "request_empty_meddelelseTypeNummer.xml";
    private static final String REQUEST_EMPTY_KUNDENUMMER = SAMPLES_PATH + "request_empty_kundeNummer.xml";
    private static final String REQUEST_EMPTY_FAGSYSTEMNAVN = SAMPLES_PATH + "request_empty_fagsystemNavn.xml";
    private static final String REQUEST_WRONG_FAGSYSTEMNAVN = SAMPLES_PATH + "request_wrong_fagsystemNavn.xml";
    private static final String EMPTY_REQUEST = SAMPLES_PATH + "empty_request.xml";
    private static final String MALFORMED_REQUEST = SAMPLES_PATH + "malformed_request.xml";

    private static String letterGenerationUrl;

    @BeforeClass
    public static void loadProperties() {
        letterGenerationUrl = toUrl(IC_SOAP_INTERNAL_PROTOCOL, IC_EXTERNAL_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, LETTER_GENERATION_SERVICE_PATH);
        LOG.info("Using letterGenerationUrl: " + letterGenerationUrl);
    }

    @Test
    public void testLetterGeneration_correctRequest_expect200code() throws Exception {
        given().log().all()
                .body(new File(REQUEST))
                .when().contentType(APPLICATION_XML_UTF_8).post(letterGenerationUrl)
                .then().log().all()
                .statusCode(200)
                .body("Envelope.Body.GenerateLetterEkspres_O.AttachmentContainerIndhold", notNullValue());
    }

    @Test
    public void testLetterGeneration_emptyMeddelelse_expect500code() throws Exception {
        given().log().all()
                .body(new File(REQUEST_EMPTY_MEDDELELSE))
                .when().contentType(APPLICATION_XML_UTF_8).post(letterGenerationUrl)
                .then().log().all()
                .statusCode(500)
                .body("Envelope.Body.Fault.faultcode", notNullValue())
                .body("Envelope.Body.Fault.faultstring", notNullValue())
                .body(containsString("Meddelelse cannot be empty"));
    }

    @Test
    public void testLetterGeneration_emptyMeddelelseIndhold_expect500code() throws Exception {
        given().log().all()
                .body(new File(REQUEST_EMPTY_MEDDELELSEINDHOLD))
                .when().contentType(APPLICATION_XML_UTF_8).post(letterGenerationUrl)
                .then().log().all()
                .statusCode(500)
                .body("Envelope.Body.Fault.faultcode", notNullValue())
                .body("Envelope.Body.Fault.faultstring", notNullValue())
                .body(containsString("MeddelelseIndhold cannot be empty"));
    }

    @Test
    public void testLetterGeneration_emptyMeddelelseAfsenderReference_expect500code() throws Exception {
        given().log().all()
                .body(new File(REQUEST_EMPTY_MEDDELELSEAFSENDERREFERENCE))
                .when().contentType(APPLICATION_XML_UTF_8).post(letterGenerationUrl)
                .then().log().all()
                .statusCode(500)
                .body("Envelope.Body.Fault.faultcode", notNullValue())
                .body("Envelope.Body.Fault.faultstring", notNullValue())
                .body(containsString("MeddelelseAfsenderReference cannot be empty"));
    }

    @Test
    public void testLetterGeneration_emptyMeddelelseTypeNummer_expect500code() throws Exception {
        given().log().all()
                .body(new File(REQUEST_EMPTY_MEDDELELSETYPENUMMER))
                .when().contentType(APPLICATION_XML_UTF_8).post(letterGenerationUrl)
                .then().log().all()
                .statusCode(500)
                .body("Envelope.Body.Fault.faultcode", notNullValue())
                .body("Envelope.Body.Fault.faultstring", notNullValue())
                .body(containsString("MeddelelseTypeNummer cannot be empty"));
    }

    @Test
    public void testLetterGeneration_emptyKundeNummer_expect500code() throws Exception {
        given().log().all()
                .body(new File(REQUEST_EMPTY_KUNDENUMMER))
                .when().contentType(APPLICATION_XML_UTF_8).post(letterGenerationUrl)
                .then().log().all()
                .statusCode(500)
                .body("Envelope.Body.Fault.faultcode", notNullValue())
                .body("Envelope.Body.Fault.faultstring", notNullValue())
                .body(containsString("KundeNummer cannot be empty"));
    }

    @Test
    public void testLetterGeneration_emptyFagsystemNavn_expect500code() throws Exception {
        given().log().all()
                .body(new File(REQUEST_EMPTY_FAGSYSTEMNAVN))
                .when().contentType(APPLICATION_XML_UTF_8).post(letterGenerationUrl)
                .then().log().all()
                .statusCode(500)
                .body("Envelope.Body.Fault.faultcode", notNullValue())
                .body("Envelope.Body.Fault.faultstring", notNullValue())
                .body(containsString("FagsystemNavn cannot be empty"));
    }

    @Test
    public void testLetterGeneration_wrongFagsystemNavn_expect500code() throws Exception {
        given().log().all()
                .body(new File(REQUEST_WRONG_FAGSYSTEMNAVN))
                .when().contentType(APPLICATION_XML_UTF_8).post(letterGenerationUrl)
                .then().log().all()
                .statusCode(500)
                .body("Envelope.Body.Fault.faultcode", notNullValue())
                .body("Envelope.Body.Fault.faultstring", notNullValue())
                .body(containsString("FagsystemNavn has to be 'IND'"));
    }

    @Test
    public void testLetterGeneration_malformedRequest_expect500code() throws Exception {
        given().log().all()
                .body(new File(MALFORMED_REQUEST))
                .when().contentType(APPLICATION_XML_UTF_8).post(letterGenerationUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("Could not parse the XML stream"));
    }

    @Test
    public void testLetterGeneration_emptyRequest_expect500code() throws Exception {
        given().log().all()
                .body(new File(EMPTY_REQUEST))
                .when().contentType(APPLICATION_XML_UTF_8).post(letterGenerationUrl)
                .then().log().all()
                .statusCode(500);
    }


}
