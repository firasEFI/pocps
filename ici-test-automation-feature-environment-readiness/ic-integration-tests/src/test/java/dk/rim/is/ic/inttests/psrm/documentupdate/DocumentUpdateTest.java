package dk.rim.is.ic.inttests.psrm.documentupdate;

import dk.rim.is.ic.inttests.Property;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Integration test of DocumentUpdate service.
 */
public class DocumentUpdateTest {

    private static final Logger LOG = LoggerFactory.getLogger(DocumentUpdateTest.class);

    private static String documentUpdateUrl;

    @BeforeClass
    public static void loadProperties() {
        String host = Property.IC_INTERNAL_HOST.load();
        String port = Property.IC_REST_INTERNAL_PORT.load();
        String path = Property.IC_PSRM_DOCUMENTUPDATE_PATH.load();
        documentUpdateUrl = toUrl("http", host, port, path);
        LOG.info("Using documentUpdateUrl: " + documentUpdateUrl);
    }

    @Test
    public void documentUpdate_whenMalformed_thenStatusCode500() {
        given().body("{ \"upda}}}}")
                .when().post(documentUpdateUrl)
                .then().statusCode(500);
    }

    @Test
    @Ignore //TODO: MLO: ICI-Integration in DocumentInformationRoute.java needs ccId validation
    public void documentUpdate_whenEmptyMessage_thenStatusCode400() {
        given().body("{}")
                .when().post(documentUpdateUrl)
                .then()
                .statusCode(400)
                .contentType("text/plain")
                .body(containsString("Validation failed"));
    }

    @Test
    @Ignore //TODO: MLO: ICI-Integration in DocumentInformationRoute.java needs ccId validation
    public void documentUpdate_whenEmptyCaptiaDocNumber_thenStatusCode400() {
        given().body("{\"ccId\": \"AAAAA\", \"captiaDocNumber\": null}")
                .when().post(documentUpdateUrl)
                .then()
                .statusCode(400)
                .contentType("text/plain")
                .body(containsString("Validation failed"));
    }

    @Test
    @Ignore //TODO: MLO: ICI-Integration in DocumentInformationRoute.java needs ccId validation
    public void documentUpdate_whenEmptyCcId_thenStatusCode400() {
        given().body("{\"ccId\": null, \"captiaDocNumber\": \"BBBBB\"}")
                .when().post(documentUpdateUrl)
                .then()
                .statusCode(400)
                .contentType("text/plain")
                .body(containsString("Validation failed"));
    }

    @Test
    public void documentUpdate_whenValidRequest_nonexistingId_thenStatusCode500() {
        given().log().all()
                .body("{\"ccId\": \"AAAAA\", \"captiaDocNumber\": \"BBBBB\"}")
                .when().post(documentUpdateUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("Client error"));
    }

}
