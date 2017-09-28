package dk.rim.is.ic.inttests.psrm.receiveclaim;

import dk.skat.begrebsmodel._2009._01._15.fordringindberet.MFAktionKodeType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimTestHelper.ACTION_CODE;
import static dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimTestHelper.ACTION_STATUS_CODE;
import static dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimTestHelper.RECEIVE_CLAIM_URL;
import static dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimTestHelper.SAMPLES_PATH;
import static dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimTestHelper.readFileAndReplaceAktionId;
import static dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimWithoutStatusCheckTest.generateUniqueActionId;
import static dk.skat.begrebsmodel._2009._01._15.fordringindberet.MFAktionKodeType.FEJLAGTIGHOVEDSTOLINDBERETNING;
import static dk.skat.begrebsmodel._2009._01._15.fordringindberet.MFAktionKodeType.OPRETFORDRING;
import static dk.skat.begrebsmodel._2009._01._15.fordringindberet.MFAktionKodeType.TILBAGEKALD;
import static dk.skat.begrebsmodel._2009._01._15.fordringindberet.MFAktionStatusKodeType.MODTAGET;
import static io.restassured.RestAssured.given;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.hamcrest.CoreMatchers.is;

@RunWith(value = Parameterized.class)
public class ReceiveClaimWithStatusCheckTest {
    private String requestFileName;
    private MFAktionKodeType expectedActionCode;
    private String actionId;

    @Parameterized.Parameters(name = "should return {1} status for {0} request [actionId={2}]")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "ValidCallbackClaim.xml", TILBAGEKALD, generateUniqueActionId() },
                { "ValidCreateClaim.xml", OPRETFORDRING, generateUniqueActionId() },
                { "ValidUpdateIncorrectReport.xml", FEJLAGTIGHOVEDSTOLINDBERETNING, generateUniqueActionId() },

        });
    }
    public ReceiveClaimWithStatusCheckTest(String requestFileName, MFAktionKodeType expectedActionCode, String actionId) {
        this.requestFileName = requestFileName;
        this.expectedActionCode = expectedActionCode;
        this.actionId = actionId;
    }

    @Test
    public void sendValidRequest_expectModtagetStatus() throws IOException {
        String request = readFileAndReplaceAktionId(SAMPLES_PATH + requestFileName, actionId);

        //@formatter:off
        given().log().all()
            . body(request)
        .when()
             .contentType(APPLICATION_XML_UTF_8)
             .post(RECEIVE_CLAIM_URL)
        .then().log().all()
             .statusCode(SC_OK)
             .body(ACTION_CODE, is(expectedActionCode.name()))
             .body(ACTION_STATUS_CODE, is(MODTAGET.name()));
        //@formatter:on
    }
}