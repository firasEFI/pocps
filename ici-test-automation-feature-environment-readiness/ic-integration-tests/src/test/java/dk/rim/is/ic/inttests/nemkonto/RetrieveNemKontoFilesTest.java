package dk.rim.is.ic.inttests.nemkonto;

import dk.rim.is.common.CommonURLs;
import dk.rim.is.ic.inttests.Property;
import dk.rim.is.ic.inttests.UrlUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_JSON;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RetrieveNemKontoFilesTest {

    private static final Logger LOG = LoggerFactory.getLogger(RetrieveNemKontoFilesTest.class);

    private static String retrieveFilesFromNemKontoUrl;

    @BeforeClass
    public static void loadProperties() {
        String host = Property.IC_HOST.load();
        String port = Property.IC_REST_EXTERNAL_PORT.load();
        String path = CommonURLs.NEMKONTO.ROOT + CommonURLs.NEMKONTO.RETRIEVE_FILES;
        retrieveFilesFromNemKontoUrl = UrlUtils.toUrl("http", host, port, path);
        LOG.info("Using retrieveFilesFromNemKontoUrl: " + retrieveFilesFromNemKontoUrl);
    }

    @Test
    public void testRetrieveFiles_validRequest_expect200code() throws Exception {
        given().log().all()
                .body("")
                .when().contentType(APPLICATION_JSON).post(retrieveFilesFromNemKontoUrl)
                .then().log().all()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("receiptFiles", notNullValue());
    }

}
