package dk.rim.is.ic.inttests.csrp.phsh;

import dk.rim.is.ic.inttests.Property;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

/** Checks if sending requestPersonFile REST request to IC works as expected. */
public class SendReceiptTest {
  private static final Logger LOG = LoggerFactory.getLogger(SendReceiptTest.class);

  private static String sendReceiptUrl;
  private static String ftpUrl;

  @BeforeClass
  public static void loadProperties() {
    String host = Property.IC_HOST.load();
    String port = Property.IC_REST_EXTERNAL_PORT.load();
    String path = Property.IC_CSRP_DELETEFILE_PATH.load();
    sendReceiptUrl = "http://" + host + ":" + port + "/" + path;
    String ftphost = Property.FTP_HOST.load();
    String ftpPort = Property.FTP_PORT.load();
    ftpUrl = "ftp://" + ftphost + ":" + ftpPort;

    LOG.info("Using sendReceiptUrl: " + sendReceiptUrl);
  }

  @Test
  public void sendReceipt_whenEmptyObject_thenErrorCode400() {
    given().header("TransaktionsID", "someTransaktionsID")
            .body("{}")
            .when().post(sendReceiptUrl)
            .then().statusCode(400);
  }

  @Test
  public void sendReceipt_whenMalformed_thenStatusCode500() {
    given().header("TransaktionsID", "someTransaktionsID")
            .body("{ \"serviceQNam")
            .when().post(sendReceiptUrl)
            .then().statusCode(500);
  }

  @Test
  public void sendReceipt_missingBeskedQName_thenStatusCode400() {
    given().header("TransaktionsID", "someTransaktionsID")
            .body("{ \"serviceQName\": \"test\" }")
            .when().post(sendReceiptUrl)
            .then().statusCode(400);
  }

  @Test
  public void sendReceipt_whenMissingServiceQName_thenStatusCode400() {
    given().header("TransaktionsID", "someTransaktionsID")
            .body("{ \"beskedQName\": \"test\" }")
            .when().post(sendReceiptUrl)
            .then().statusCode(400);
  }

  @Test
  public void sendReceipt_whenValid_thenStatusCode200() {
    given().header("TransaktionsID", "someTransaktionsID")
            .body("{ \"downloadURL\": \"" + ftpUrl + "/csrp_persondata_20161027012723.xml\", \"userName\": \"user\", \"password\": \"pass\", \"serviceQName\":\"\",\"beskedQName\":\"\"  }")
            .when().post(sendReceiptUrl)
            .then().statusCode(200);
  }

  @Test
  public void sendReceipt_when_transactionId_is_missing_thenStatusCode400() {
    given().body("{ \"downloadURL\": \"" + ftpUrl + "/csrp_persondata_20161027012723.xml\", \"userName\": \"user\", \"password\": \"pass\", \"serviceQName\":\"\",\"beskedQName\":\"\"  }")
            .when().post(sendReceiptUrl)
            .then().statusCode(400)
            .body(containsString("Missing HovedOplysninger header"));
  }
}
