package dk.rim.is.ic.inttests.aogd;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.IC_AOGD_MEDDELELSEMULTISENDEKSPRES_PATH;
import static dk.rim.is.ic.inttests.Property.IC_EXTERNAL_HOST;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_INTERNALPORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;

/**
 * Integration tests of MeddelelseMultiSendEkspres service.
 */
public class MeddelelseMultiSendEkspresTest {

    private static final String REQUESTS_PATH = "build/resources/test/aogd/meddelelseMultiSendEkspres/";
    private static final String PATH_PREFIX = "build/resources/test/";

    private static final String EMPTY_OBJECT_PATH = REQUESTS_PATH + "EmptyRequest.xml";
    private static final String MALFORMED_PATH = REQUESTS_PATH + "MalformedRequest.xml";
    private static final String VALID_PATH = REQUESTS_PATH + "ValidRequest.xml";

    private final String BATCH_ID_PARAM = "${Properties#MeddelelseBatchID}";
    private final String START_REF_PARAM = "${Properties#StartRef}";

    private static String meddelelseMultiSendEkspresUrl;

    @BeforeClass
    public static void loadProperties() {
        meddelelseMultiSendEkspresUrl =
                toUrl(IC_SOAP_EXTERNAL_PROTOCOL, IC_EXTERNAL_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_AOGD_MEDDELELSEMULTISENDEKSPRES_PATH);
    }

    @Test
    public void meddelelseMultiSendEkspres_whenEmpty_thenStatusCode500() throws Exception {
        given().log().all()
                .body(new File(EMPTY_OBJECT_PATH))
                .when().contentType(APPLICATION_XML_UTF_8).post(meddelelseMultiSendEkspresUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void meddelelseMultiSendEkspres_whenMalformed_thenStatusCode500() throws Exception {
        given().log().all()
                .body(new File(MALFORMED_PATH))
                .when().contentType(APPLICATION_XML_UTF_8).post(meddelelseMultiSendEkspresUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void meddelelseMultiSendEkspres_whenValid_thenStatusCode200() throws Exception {
        URL messageBodyUrl = Resources.getResource(VALID_PATH.replace(PATH_PREFIX, ""));
        String messageBody = Resources.toString(messageBodyUrl, Charsets.UTF_8);
        messageBody = messageBody.replace(BATCH_ID_PARAM,String.valueOf((System.currentTimeMillis()/1000)));
        messageBody = messageBody.replace(START_REF_PARAM,String.valueOf((System.currentTimeMillis()/100000)));
        given().log().all()
                .body(messageBody)
                .when().contentType(APPLICATION_XML_UTF_8).post(meddelelseMultiSendEkspresUrl)
                .then().log().all()
                .statusCode(200);
    }

}
