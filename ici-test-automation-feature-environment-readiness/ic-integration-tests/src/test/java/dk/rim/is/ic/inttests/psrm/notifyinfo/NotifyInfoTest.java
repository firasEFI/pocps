package dk.rim.is.ic.inttests.psrm.notifyinfo;

import org.junit.BeforeClass;
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

public class NotifyInfoTest {

    private static final Logger LOG = LoggerFactory.getLogger(NotifyInfoTest.class);

    private static final String MSG_REQUEST_VALIDATION = "Request validation messages:";
    private static final String MSG_MISSING_VIRKSOMHEDSENUMMER = "Required parameter missing: FordringhaverSystemIDStruktur.VirksomhedSENummer";
    private static final String MSG_ALL_PARAMS = "geDatoFra has a value, the UnderretNummerFra and UnderretNummerTil must not";
    private static final String MSG_TOO_MANY_PARAMS = "If UnderretNummerFra has a value, S";
    private static final String MSG_MISSING_UNDERRET_PARAM = "If UnderretNummerTil has a value, UnderretNummerFra must have a value too";

    private static final String SAMPLES_PATH = "build/resources/test/psrm/notifyInfo/";
    private static final String VALID_NOTIFY_REQUEST = SAMPLES_PATH + "Notify_ValidRequest.xml";
    private static final String INVALID_REQUEST_MISSING_NUMMER = SAMPLES_PATH + "NotifyMissingNummer_InvalidRequest.xml";
    private static final String INVALID_REQUEST_ALL_PARAMS = SAMPLES_PATH + "NotifyAllParams_InvalidRequest.xml";
    private static final String INVALID_REQUEST_TO0_MANY_PARAMS = SAMPLES_PATH + "NotifyTooManyParams_InvalidRequest.xml";
    private static final String INVALID_REQUEST_MISSING_UNDERRET = SAMPLES_PATH + "NotifyMissingUnderret_InvalidRequest.xml";

    private static String notifyInfoUrl;

    @BeforeClass
    public static void loadProperties() {
        notifyInfoUrl = toUrl(IC_SOAP_INTERNAL_PROTOCOL, IC_INTERNAL_HOST, IC_SOAP_INTERNAL_PORT, IC_PSRM_NOTIFYINFO_PATH);
        LOG.info("Using notifyInfoUrl: " + notifyInfoUrl);
    }

    @Test
    public void sendValidRequest_expectMessageDelivered() throws Exception {
        //@formatter:off
        given().log().all().
            body(new File(VALID_NOTIFY_REQUEST)).
        when().
            contentType(APPLICATION_XML_UTF_8).
            post(notifyInfoUrl).
        then().log().all().
            statusCode(200).
            body("Envelope.Body.MFUnderretSamlingHent_O", notNullValue());
        //@formatter:on
    }

    @Test
    public void sendInvalidRequestMissingNummer_expectErrorInResponse() throws Exception {
        //@formatter:off
        given().log().all().
            body(new File(INVALID_REQUEST_MISSING_NUMMER)).
        when().
            contentType(APPLICATION_XML_UTF_8).
            post(notifyInfoUrl).
        then().log().all().
            statusCode(500).
            body(containsString(MSG_REQUEST_VALIDATION)).
            body(containsString(MSG_MISSING_VIRKSOMHEDSENUMMER));
        //@formatter:on
    }

    @Test
    public void sendInvalidRequestAllParameters_expectErrorInResponse() throws Exception {
        //@formatter:off
        given().log().all().
            body(new File(INVALID_REQUEST_ALL_PARAMS)).
        when().
            contentType(APPLICATION_XML_UTF_8).
            post(notifyInfoUrl).
        then().log().all().
            statusCode(500).
            body(containsString(MSG_REQUEST_VALIDATION)).
            body(containsString(MSG_ALL_PARAMS));
        //@formatter:on
    }

    @Test
    public void sendInvalidRequestTooManyParameters_expectErrorInResponse() throws Exception {
        //@formatter:off
        given().log().all().
            body(new File(INVALID_REQUEST_TO0_MANY_PARAMS)).
        when().
            contentType(APPLICATION_XML_UTF_8).
            post(notifyInfoUrl).
        then().log().all().
            statusCode(500).
            body(containsString(MSG_REQUEST_VALIDATION)).
            body(containsString(MSG_TOO_MANY_PARAMS));
        //@formatter:on
    }

    @Test
    public void sendInvalidRequestMissingUnderret_expectErrorInResponse() throws Exception {
        //@formatter:off
        given().log().all().
            body(new File(INVALID_REQUEST_MISSING_UNDERRET)).
        when().
            contentType(APPLICATION_XML_UTF_8).
            post(notifyInfoUrl).
        then().log().all().
            statusCode(500).
            body(containsString(MSG_REQUEST_VALIDATION)).
            body(containsString(MSG_MISSING_UNDERRET_PARAM));
        //@formatter:on
    }

}
