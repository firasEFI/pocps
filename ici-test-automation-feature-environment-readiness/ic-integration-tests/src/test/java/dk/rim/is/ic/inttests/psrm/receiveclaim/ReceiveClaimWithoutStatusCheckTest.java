package dk.rim.is.ic.inttests.psrm.receiveclaim;

import dk.rim.is.ic.inttests.util.RandomGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimTestHelper.ACTION_STATUS_CODE;
import static dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimTestHelper.RECEIVE_CLAIM_URL;
import static dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimTestHelper.SAMPLES_PATH;
import static dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimTestHelper.readFileAndReplaceAktionId;
import static dk.skat.begrebsmodel._2009._01._15.fordringindberet.MFAktionStatusKodeType.MODTAGET;
import static io.restassured.RestAssured.given;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.hamcrest.CoreMatchers.is;

@RunWith(value = Parameterized.class)
public class ReceiveClaimWithoutStatusCheckTest {

    private String requestFileName;
    private String actionId;

    @Parameterized.Parameters(name = "should return positive response for {0} request [actionId={1}]")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                //TODO MICW check that feature/release2 is on all envs
                { "ValidWriteDownAnnulPaymentRequest.xml", generateUniqueActionId() },
                { "ValidWriteDownAnnulRegulationRequest.xml", generateUniqueActionId() },
                { "ValidWriteUpAnnulPayment.xml", generateUniqueActionId() },
                { "ValidWriteUpAnnulRegulation.xml", generateUniqueActionId() },
                { "ValidWriteUpRegulation.xml", generateUniqueActionId() },
                { "ValidWritedownClaim.xml", generateUniqueActionId()}
        });
    }
    public ReceiveClaimWithoutStatusCheckTest(String requestFileName, String actionId) {
        this.requestFileName = requestFileName;
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
             .body(ACTION_STATUS_CODE, is(MODTAGET.name()));
        //@formatter:on
    }

    static String generateUniqueActionId() {
        return Long.toString(RandomGenerator.randomLongMax18Digits());
    }
}