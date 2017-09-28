package dk.rim.is.ic.inttests.csrp.psmh;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.IC_CSRP_PSMH_PATH;
import static dk.rim.is.ic.inttests.Property.IC_HOST;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_INTERNALPORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Created by rku on 22-12-2016.
 */
public class PersonStamoplysningerMultiHentAccessTest {

    private static final String SAMPLES_PATH = "build/resources/test/csrp/psmh/";

    private static final String REQUEST_MESSAGE = SAMPLES_PATH + "PersonStamoplysningerMultiHent_I_Request.xml";

    private static String psmhUrl;

    @BeforeClass
    public static void loadProperties() {
        psmhUrl = toUrl(IC_SOAP_EXTERNAL_PROTOCOL, IC_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_CSRP_PSMH_PATH);
    }

    @Test
    public void PersonStamoplysningerMultiHentAccessTest_validRequest_expect200code() throws Exception {
        given().log().all()
                .body(new File(REQUEST_MESSAGE))
                .when().contentType(APPLICATION_XML_UTF_8)
                .post(psmhUrl)
                .then().log().all()
                .statusCode(200)
                .body(containsString("PersonStamoplysningerMultiHent_O"));
    }
}
