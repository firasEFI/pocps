package dk.rim.is.ic.inttests.psrm.retrieveall;

import dk.rim.is.ic.inttests.Property;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

/**
 * Calls the Integration Component's /psrm/retrieveAll operation.
 * We are using RetrieveAllCprRequestMessage variations to test the endpoint.
 * In these tests we expect that either SoapUI or PSRM mocks are running behind IC.
 *
 * Note: we are using inline message contents in below tests on purpose. This is to increase
 * readability of the test, there is no point in referencing them from external files.
 */
public class RetrieveAllCprTest {
  private static final Logger LOG = LoggerFactory.getLogger(RetrieveAllCprTest.class);

  private static String retrieveAllCprUrl;

  @BeforeClass
  public static void loadProperties() {
    String host = Property.IC_INTERNAL_HOST.load();
    String port = Property.IC_REST_INTERNAL_PORT.load();
    String path = Property.IC_PSRM_RETRIEVEALLCPR_PATH.load();
    retrieveAllCprUrl = "http://" + host + ":" + port + "/" + path;
    LOG.info("Using retrieveAllCprUrl: " + retrieveAllCprUrl);
  }

  @Test
  public void retrieveAllCpr_whenMissingAllOptionals_thenStatusCode200() {
    given().body("{}")
            .when().post(retrieveAllCprUrl)
            .then().statusCode(200);
  }

  @Test
  public void retrieveAllCpr_whenMalformed_thenStatusCode500() {
    given().body("{ \"zon")
            .when().post(retrieveAllCprUrl)
            .then().statusCode(500);
  }

  @Test
  public void retrieveAllCpr_whenMissingOptionalZone_thenStatusCode200() {
    given().body("{ \"rowCount\": 100 }")
            .when().post(retrieveAllCprUrl)
            .then().statusCode(200);
  }

  @Test
  public void retrieveAllCpr_whenMissingOptionalRowCount_thenStatusCode200() {
    given().body("{ \"zone\": \"test\" }")
            .when().post(retrieveAllCprUrl)
            .then().statusCode(200);
  }

  @Test
  public void retrieveAllCpr_whenValid_thenStatusCode200() {
    given().body("{ \"zone\": \"test\", \"rowCount\": 100 }")
            .when().post(retrieveAllCprUrl)
            .then().statusCode(200);
  }
}
