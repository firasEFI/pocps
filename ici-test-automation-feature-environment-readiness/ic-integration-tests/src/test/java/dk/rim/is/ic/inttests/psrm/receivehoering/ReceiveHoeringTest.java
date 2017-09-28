package dk.rim.is.ic.inttests.psrm.receivehoering;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import dk.rim.is.ic.inttests.util.RandomGenerator;
import dk.skat.begrebsmodel._2009._01._15.psrmoprethoeringsag.MFAktionKodeType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.*;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static dk.skat.begrebsmodel._2009._01._15.psrmoprethoeringsag.MFAktionKodeType.*;
import static io.restassured.RestAssured.given;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.hamcrest.CoreMatchers.*;

public class ReceiveHoeringTest {

    private static final Logger LOG = LoggerFactory.getLogger(ReceiveHoeringTest.class);

    private static final String ACTION_NOT_SUPPORTED = "The request contains unsupported action.";
    private static final String SAMPLES_PATH = "psrm/receiveHoering/";

    private static final String MALFORMED_MESSAGE = SAMPLES_PATH + "MalformatedEnvelop.xml";
    private static final String EMPTY_MESSAGE = SAMPLES_PATH + "EmptyBodyEnvelope.xml";
    private static final String TEMPLATE_MESSAGE = SAMPLES_PATH + "EnvelopeTemplate.xml";
    private static final long DUMMY_ACTION_ID = 1122;

    private static String receiveHoeringUrl;

    @BeforeClass
    public static void loadProperties() {
        receiveHoeringUrl = toUrl(IC_SOAP_INTERNAL_PROTOCOL, IC_INTERNAL_HOST, IC_SOAP_INTERNAL_PORT, IC_PSRM_RECEIVEHOERING_PATH);
        LOG.info("Using receiveHoeringUrl: " + receiveHoeringUrl);
    }

    @Test
    public void sendMalformedMessage_expect500code() throws Exception {
        //@formatter:off
        given().log().all()
                 .body(getMsgBody(MALFORMED_MESSAGE))
                .when().contentType(APPLICATION_XML_UTF_8)
                   .post(receiveHoeringUrl)
                .then().log().all()
                   .statusCode(SC_INTERNAL_SERVER_ERROR)
                   .body("Envelope.Body.Fault.faultcode", notNullValue())
                   .body("Envelope.Body.Fault.faultstring", notNullValue());
        //@formatter:on
    }

    @Test
    public void sendEmptyMessage_expect500code() throws Exception {
        //@formatter:off
        given().log().all()
                   .body(getMsgBody(EMPTY_MESSAGE))
                .when().contentType(APPLICATION_XML_UTF_8)
                   .post(receiveHoeringUrl)
                .then().log().all()
                   .statusCode(SC_INTERNAL_SERVER_ERROR)
                   .body("Envelope.Body.Fault.faultcode", notNullValue())
                   .body("Envelope.Body.Fault.faultstring", notNullValue());
        //@formatter:on
    }

    @Test
    public void sendValidCreateHoering_expect200code() throws Exception {
        long uniqueActionId = RandomGenerator.randomLongMax9Digits();
        sendValidRequest_expect200_actionSupported(OPRETFORDRING, uniqueActionId);
    }

    @Test
    public void sendValidEditHoering_expect500_actionNotSupported() throws Exception {
        sendValidRequest_expect500_actionNotSupported(AENDRFORDRING, DUMMY_ACTION_ID);
    }

    @Test
    public void sendValidWriteDownHoering_expect500_actionNotSupported() throws Exception {
        sendValidRequest_expect500_actionNotSupported(NEDSKRIV, DUMMY_ACTION_ID);
    }

    @Test
    public void sendValidRecallHoering_expect500_actionNotSupported() throws Exception {
        sendValidRequest_expect500_actionNotSupported(TILBAGEKALD, DUMMY_ACTION_ID);
    }

    @Test
    public void sendValidResendHoering_expect500_actionNotSupported() throws Exception {
        sendValidRequest_expect500_actionNotSupported(GENINDSENDFORDRING, DUMMY_ACTION_ID);
    }

    @Test
    public void sendValidOpskrivningregulering_expect200() throws Exception {
        long uniqueActionId = RandomGenerator.randomLongMax9Digits();
        sendValidRequest_expect200_actionSupported(OPSKRIVNINGREGULERING, uniqueActionId);
    }

    @Test
    public void sendValidWriteDownRegulationHoering_expect500_actionNotSupported() throws Exception {
        sendValidRequest_expect500_actionNotSupported(NEDSKRIVNINGANNULLERETOPSKRIVNINGINDBETALING, DUMMY_ACTION_ID);
    }

    @Test
    public void sendValidWriteUpCancelledPaymentHoering_expect500_actionNotSupported() throws Exception {
        sendValidRequest_expect500_actionNotSupported(OPSKRIVNINGANNULLERETNEDSKRIVNINGINDBETALING, DUMMY_ACTION_ID);
    }

    private void sendValidRequest_expect200_actionSupported(MFAktionKodeType actionCode, long actionId) throws IOException {
        //@formatter:off
        given().log().all().
                body(injectToTemplateBody(actionCode.name(), actionId)).
                when().
                contentType(APPLICATION_XML_UTF_8).
                post(receiveHoeringUrl)
                .then().log().all()
                .statusCode(is(SC_OK))
                .body("Envelope.Body.MFFordringIndberet_O", notNullValue());
        //@formatter:on
    }

    private void sendValidRequest_expect500_actionNotSupported(MFAktionKodeType actionCode, long actionId) throws IOException {
        //@formatter:off
        given().log().all().
            body(injectToTemplateBody(actionCode.name(), actionId)).
        when().
            contentType(APPLICATION_XML_UTF_8).
            post(receiveHoeringUrl).
        then().log().all().
            statusCode(SC_INTERNAL_SERVER_ERROR).
            body(containsString(ACTION_NOT_SUPPORTED));
        //@formatter:on
    }

    private String injectToTemplateBody(String actionCode, long actionId) throws IOException {
        String messageTemplate = getMsgBody(TEMPLATE_MESSAGE);
        return messageTemplate.replace("{{ACTION_CODE}}", actionCode).replace("{{ACTION_ID}}", String.valueOf(actionId));
    }

    private String getMsgBody(String url) throws IOException {
        return Resources.toString(Resources.getResource(url), Charsets.UTF_8);
    }
}
