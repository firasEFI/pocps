package dk.rim.is.ic.inttests.psrm.statusupdate;

import dk.rim.is.ic.inttests.Property;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Integration test for the Integration Component's /psrm/statusUpdate operation
 */
public class StatusUpdateTest {

    private static final Logger LOG = LoggerFactory.getLogger(StatusUpdateTest.class);

    private static String statusUpdateUrl;

    @BeforeClass
    public static void loadProperties() {
        String host = Property.IC_INTERNAL_HOST.load();
        String port = Property.IC_REST_INTERNAL_PORT.load();
        String path = Property.IC_PSRM_STATUSUPDATE_PATH.load();
        statusUpdateUrl = toUrl("http", host, port, path);
        LOG.info("Using statusUpdateUrl: " + statusUpdateUrl);
    }

    @Test
    public void updateStatus_whenMalformed_thenStatusCode500() {
        given().body("{ \"upda}}}}")
                .when().post(statusUpdateUrl)
                .then().statusCode(500);
    }

    @Test
    public void updateStatus_whenValidRequest_nonexistingId_thenStatusCode500() {
        given().log().all()
                .body("{\"updateStatuses\": [" +
                "  {\"brevId\": \"111\", \"status\": \"SENTTODEBTOR\"}," +
                "  {\"brevId\": \"333\", \"status\": \"FAILED\"}," +
                "  {\"brevId\": \"555\", \"status\": \"SENTTODEBTOR\"}" +
                "]}")
                .when().post(statusUpdateUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("Client error"));
    }
}
