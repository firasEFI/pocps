package dk.rim.is.ic.inttests.psrm.documentinfo;

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
 * Integration test of DocumentInformation service.
 */
public class DocumentInformationTest {

    private static final Logger LOG = LoggerFactory.getLogger(DocumentInformationTest.class);

    private static String documentInformationUrl;

    @BeforeClass
    public static void loadProperties() {
        String host = Property.IC_INTERNAL_HOST.load();
        String port = Property.IC_REST_INTERNAL_PORT.load();
        String path = Property.IC_PSRM_DOCUMENTINFO_PATH.load();
        documentInformationUrl = toUrl("http", host, port, path);
        LOG.info("Using documentInformationUrl: " + documentInformationUrl);
    }

    @Test
    public void documentInformation_whenMalformed_thenStatusCode500() {
        given().body("{ \"upda}}}}")
                .when().post(documentInformationUrl)
                .then().statusCode(500);
    }

    @Test
    @Ignore //TODO: MLO: ICI-Integration in DocumentInformationRoute.java needs ccId validation
    public void documentInformation_whenEmptyValue_thenStatusCode400() {
        given().body("{\"ccId\": null}")
                .when().post(documentInformationUrl)
                .then()
                .statusCode(400)
                .contentType("text/plain")
                .body(containsString("Validation failed"));
    }

    @Test
    public void documentInformation_whenValidRequest_nonexistingId_thenStatusCode500() {
        given().log().all()
                .body("{\"ccId\": \"AAAAAA\"}")
                .when().post(documentInformationUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("Client error"));
    }

}
