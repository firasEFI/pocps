package dk.rim.is.ic.inttests.util;

import dk.rim.is.ic.inttests.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;

/**
 * @author Radoslaw Domanski (rdo)
 * @since 31.01.2017
 */
public class BatchControl {
    private static final Logger LOG = LoggerFactory.getLogger(BatchControl.class);

    private static final String BATCH_CONTROL_URL = batchControlUrl();

    private BatchControl() {
    }

    private static String batchControlUrl() {
        String host = Property.BATCH_REST_HOST.load();
        String restPort = Property.BATCH_REST_PORT.load();

        String url = toUrl("http", host, restPort, "/integration-batch/rest/batchcontrol/start");

        LOG.info("Using BATCH_CONTROL_URL: " + url);

        return url;
    }

    public static void initiateBatchProcess(String jobName) {
        //@formatter:off
        given()
                .log().all()
                .body(jobName)
        .when()
                .post(BATCH_CONTROL_URL)
        .then()
                .log().all()
                .assertThat()
                        .statusCode(200);
        //@formatter:on
    }
}
