package dk.rim.is.ic.inttests.psrm.receiveclaim;

import org.junit.Test;

import java.io.IOException;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimTestHelper.*;
import static dk.rim.is.ic.inttests.util.file.FileReader.getResourceFileAsString;
import static dk.skat.begrebsmodel._2009._01._15.fordringindberet.MFAktionKodeType.OPRETFORDRING;
import static dk.skat.begrebsmodel._2009._01._15.fordringindberet.MFAktionStatusKodeType.AFVIST;
import static io.restassured.RestAssured.given;
import static javax.servlet.http.HttpServletResponse.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * Created by maho on 26.07.2017.
 */
public class ReceiveClaimNegativeTest {
    private static final String MALFORMED_REQUEST = SAMPLES_PATH + "MalformedRequest.xml";
    private static final String INVALIDREQUEST_NOACTION = SAMPLES_PATH + "InvalidRequestNoAction.xml";
    private static final String ACTION_NOT_SUPPORTED = "The request contains unsupported action.";

    @Test
    public void testReceiveClaim_invalidRequest_expect400_actionNotSupported() throws Exception {
        //@formatter:off
        given().log().all().
            body(getResourceFileAsString(INVALIDREQUEST_NOACTION)).
        when().
            contentType(APPLICATION_XML_UTF_8).
            post(RECEIVE_CLAIM_URL).
        then().log().all().
            statusCode(SC_BAD_REQUEST).
            body(containsString(ACTION_NOT_SUPPORTED));
        //@formatter:on
    }

    @Test
    public void testReceiveClaim_malformedRequest_expect500() throws Exception {
        //@formatter:off
        given()
                .log().all().
                body(getResourceFileAsString(MALFORMED_REQUEST)).
        when().
                contentType(APPLICATION_XML_UTF_8).
                post(RECEIVE_CLAIM_URL).
        then()
                .log().all().
                statusCode(SC_INTERNAL_SERVER_ERROR).
                body("Envelope.Body.Fault.faultcode", notNullValue()).
                body("Envelope.Body.Fault.faultstring", notNullValue());
        //@formatter:on
    }

    @Test
    public void sendRequestWithContantActionId_expectDuplicatedActionId() throws IOException {
        String request = readFileAndReplaceAktionId(SAMPLES_PATH + "ValidCreateClaim.xml", String.valueOf(1234567890));

        //@formatter:off
        given()
                .log().all()
                .body(request)
        .when()
             .contentType(APPLICATION_XML_UTF_8)
             .post(RECEIVE_CLAIM_URL)
        .then()
                .log().all(); //first response may be either a positive or error response, we don't care about it

        given()
                .log().all()
                .body(request)
        .when()
                .contentType(APPLICATION_XML_UTF_8)
                .post(RECEIVE_CLAIM_URL)
        .then().log().all()
                .statusCode(SC_OK)
                .body(ACTION_STATUS_CODE, is(AFVIST.name()))
                .body(ACTION_CODE, is(OPRETFORDRING.name()))
                .body(AFVIST_CODE, is("600"))
                .body(AFVIST_MESSAGE, is("AktionId eksisterer allerede i PSRM"));
        //@formatter:on
    }
}
