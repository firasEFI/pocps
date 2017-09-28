package dk.rim.is.ic.inttests.csrp.phsh;

import dk.rim.is.common.CommonURLs;
import dk.rim.is.ic.inttests.Property;
import dk.rim.is.ic.inttests.UrlUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Checks if sending requestPersonFile REST request to IC works as expected.
 */
public class RetrieveFileListTest {

    private static String sendReceiptUrl;

    @BeforeClass
    public static void loadProperties() {
        String host = Property.IC_HOST.load();
        String port = Property.IC_REST_EXTERNAL_PORT.load();
        String path = CommonURLs.CSRP.ROOT + CommonURLs.CSRP.RETRIEVE_FILE_LIST;

        sendReceiptUrl = UrlUtils.toUrl("http", host, port, path);
    }

    @Test
    public void test_retrieveFileList_validRequest_expect200code() {

        given().body("")
            .when()
            .post(sendReceiptUrl)
            .then()
            .statusCode(200)
            .body(containsString("fileNameList"));

    }
}
