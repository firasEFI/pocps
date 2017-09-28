package dk.rim.is.ic.inttests.aogd;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import dk.rim.is.ic.inttests.ContentTypes;
import dk.rim.is.ic.inttests.RegexMatcher;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

import static dk.rim.is.ic.inttests.Property.IC_AOGD_FORMATERETMEDDELELSEINDHOLDMULTIHENT_KLADDE_PATH;
import static dk.rim.is.ic.inttests.Property.IC_AOGD_FORMATERETMEDDELELSEINDHOLDMULTIHENT_PATH;
import static dk.rim.is.ic.inttests.Property.IC_AOGD_REQUESTFORMATTEDMESSAGEDOWNLOAD_PATH;
import static dk.rim.is.ic.inttests.Property.IC_HOST;
import static dk.rim.is.ic.inttests.Property.IC_REST_EXTERNAL_PORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_INTERNALPORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_INTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

/**
 * Integration tests of FormateretMeddelelseIndholdMultiHent service.
 */
public class FormateretMeddelelseIndholdMultiHentTest {
    private static final Logger LOG = LoggerFactory.getLogger(FormateretMeddelelseIndholdMultiHentTest.class);

    private static final String REQUESTS_PATH = "build/resources/test/aogd/formateretMeddelelseIndholdMultiHent/";
    private static final String PATH_PREFIX = "build/resources/test/";

    private static final String EMPTYBODY_ENVELOPE_PATH = REQUESTS_PATH + "I_EmptyBodyRequestEnvelope.xml";
    private static final String MALFORMEDBODY_ENVELOPE_PATH = REQUESTS_PATH + "I_MalformedBodyRequestEnvelope.xml";
    private static final String VALID_ENVELOPE_PATH = REQUESTS_PATH + "I_ValidRequestEnvelope.xml";

    private final String MESSAGE_ID_PARAM = "${Properties#MeddelelseID}";

    private static String requestFormattedMessageDownloadRestUrl;
    private static String fmimhSoapUrl;
    private static String fmimhKladdeSoapUrl;

    @BeforeClass
    public static void loadProperties() {
        requestFormattedMessageDownloadRestUrl = toUrl("http", IC_HOST, IC_REST_EXTERNAL_PORT, IC_AOGD_REQUESTFORMATTEDMESSAGEDOWNLOAD_PATH);
        fmimhSoapUrl = toUrl(IC_SOAP_INTERNAL_PROTOCOL, IC_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_AOGD_FORMATERETMEDDELELSEINDHOLDMULTIHENT_PATH);
        fmimhKladdeSoapUrl = toUrl(IC_SOAP_INTERNAL_PROTOCOL, IC_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_AOGD_FORMATERETMEDDELELSEINDHOLDMULTIHENT_KLADDE_PATH);

        LOG.info("Using requestFormattedMessageDownloadRestUrl: " + requestFormattedMessageDownloadRestUrl);
        LOG.info("Using fmimhSoapUrl: " + fmimhSoapUrl);
        LOG.info("Using fmimhKladdeSoapUrl: " + fmimhKladdeSoapUrl);
    }

    @Test
    public void formateretMeddelelseIndholdMultiHent_whenEmpty_thenStatusCode500() throws Exception {
        given().log().all().body(new File(EMPTYBODY_ENVELOPE_PATH))
                .when().post(fmimhSoapUrl)
                .then().log().all().statusCode(500)
                .body("Envelope.Body.Fault.faultstring", containsString("throws 101 : Teknisk fejl"));
    }

    @Test
    public void formateretMeddelelseIndholdMultiHent_whenMalformed_thenStatusCode500() throws Exception {
        given().log().all().body(new File(MALFORMEDBODY_ENVELOPE_PATH))
                .when().post(fmimhSoapUrl)
                .then().log().all().statusCode(500)
                .and().body("Envelope.Body.Fault.faultcode", containsString("Client"));
    }
    
    @Test
    public void formateretMeddelelseIndholdMultiHent_whenValid_thenStatusCode200() throws Exception {
        URL messageBodyUrl = Resources.getResource(VALID_ENVELOPE_PATH.replace(PATH_PREFIX, ""));
        String messageBody = Resources.toString(messageBodyUrl, Charsets.UTF_8);
        messageBody = messageBody.replace(MESSAGE_ID_PARAM,"23803658");
        given().log().all()
                .body(messageBody)
                .when().post(fmimhSoapUrl)
                .then().log().all().statusCode(200)
                .body("Envelope.Body.FormateretMeddelelseIndholdMultiHent_O.Meddelelser.Meddelelse.size()", is(1))
                .body("Envelope.Body.FormateretMeddelelseIndholdMultiHent_O.Meddelelser.Meddelelse[0].MeddelelseID", is("23803658"));
    }

    @Test
    public void fmimhKladde_whenEmpty_thenStatusCode200() throws Exception {
        given().log().all().body(new File(EMPTYBODY_ENVELOPE_PATH))
                .when().post(fmimhKladdeSoapUrl)
                .then().log().all().statusCode(500)
                .body("Envelope.Body.Fault.faultstring", containsString("throws 101 : Teknisk fejl"));;
    }

    @Test
    public void fmimhKladde_whenMalformed_thenStatusCode500() throws Exception {
        given().log().all().body(new File(MALFORMEDBODY_ENVELOPE_PATH))
                .when().post(fmimhKladdeSoapUrl)
                .then().log().all().statusCode(500)
                .and().body("Envelope.Body.Fault.faultcode", containsString("Client"));
    }

    @Test
    public void fmimhKladde_whenValid_thenStatusCode200() throws Exception {
        URL messageBodyUrl = Resources.getResource(VALID_ENVELOPE_PATH.replace(PATH_PREFIX, ""));
        String messageBody = Resources.toString(messageBodyUrl, Charsets.UTF_8);
        messageBody = messageBody.replace(MESSAGE_ID_PARAM,"23803658");
        given().log().all()
                .body(messageBody)
                .when().post(fmimhKladdeSoapUrl)
                .then().log().all().statusCode(200)
                .body("Envelope.Body.FormateretMeddelelseIndholdMultiHent_O.Meddelelser.Meddelelse.size()", is(1))
                .body("Envelope.Body.FormateretMeddelelseIndholdMultiHent_O.Meddelelser.Meddelelse[0].MeddelelseID", is("23803658"));
    }
    
    @Test
    public void restRequestFormattedMessageDownload_whenValid_thenStatusCode200() throws Exception {
        given().log().all()
                .body("{ \"formattedMessageIdList\":[\"811651\"] }")
                .when().post(requestFormattedMessageDownloadRestUrl)
                .then().log().all()
                .statusCode(200).contentType(ContentTypes.APPLICATION_JSON)
                .body("attachmentItemsList.size()", is(1))
                .body("attachmentItemsList[0].formateretMeddelelseID", notNullValue())
                .body("attachmentItemsList[0].formateretMeddelelseID", RegexMatcher.matchesRegex("\\d*"))
                .body("attachmentItemsList[0].attachmentContainerContents", notNullValue());
    }

    @Test
    public void restRequestFormattedMessageDownload_whenEmpty_thenStatusCode500() throws Exception {
        given().log().all()
                .body("{}")
                .when().post(requestFormattedMessageDownloadRestUrl)
                .then().log().all()
                .statusCode(500).contentType(ContentTypes.TEXT_PLAIN);
    }


    @Test
    public void restRequestFormattedMessageDownload_whenMalformed_thenStatusCode500() throws Exception {
        given().log().all()
                .body("qwe { ew43 }}}}} ")
                .when().post(requestFormattedMessageDownloadRestUrl)
                .then().log().all()
                .statusCode(500).contentType(ContentTypes.TEXT_PLAIN);
    }

}
