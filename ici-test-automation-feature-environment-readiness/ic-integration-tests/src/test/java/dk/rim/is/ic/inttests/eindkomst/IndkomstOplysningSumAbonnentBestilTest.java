package dk.rim.is.ic.inttests.eindkomst;

import org.hamcrest.Matchers;
import org.junit.Test;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.IC_EINDKOMST_IOSAB_PATH;
import static dk.rim.is.ic.inttests.Property.IC_HOST;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_INTERNALPORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static dk.rim.is.ic.inttests.util.file.FileReader.getResourceFileAsString;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;

/**
 *
 * Created by maho on 10.08.2017.
 */
public class IndkomstOplysningSumAbonnentBestilTest {
    //run mock http://localhost:50000/mockIndkomstOplysningSumHentBinding

     private static final String SAMPLES_PATH = "eindkomst/iosab/";

     private static final String VALID_REQUEST_I_ENVELOPE = SAMPLES_PATH + "ValidRequest_I_envelope.xml";
     private static final String VALID_REQUEST_O_ENVELOPE = SAMPLES_PATH + "ValidRequest_O_envelope.xml";
     private static final String VALID_REQUEST_AFSLUT_ENVELOPE = SAMPLES_PATH + "ValidRequest_Afslut_envelope.xml";

     private static final String EMPTY_REQUEST_ENVELOPE = SAMPLES_PATH + "Empty_envelope.xml";
     private static final String MALFORMED_REQUEST_ENVELOPE = SAMPLES_PATH + "MalformedRequest.xml";

     private static final String ABONNENT_BESTILLING_IDENTIFIKATOR = "Envelope.Body.IndkomstOplysningSumAbonnentBestil_I_O.AbonnentBestillingIdentifikator";
     private static final String ABONNENT_ADGANG_STRUKTUR  = "Envelope.Body.IndkomstOplysningSumAbonnentBestil_O_O.IndkomstOplysningSumAbonnentBestilUddata.AbonnentAdgangStruktur";
     private static final String ABONNENT_UDDATA_SAMLING = "Envelope.Body.IndkomstOplysningSumAbonnentBestil_O_O.IndkomstOplysningSumAbonnentBestilUddata.AbonnentUddataSamling";

     private static String iosabUrl = toUrl(IC_SOAP_EXTERNAL_PROTOCOL, IC_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_EINDKOMST_IOSAB_PATH);

     @Test
     public void test_validRequest_I_shouldReturnAbonnentBestillingIdentifikator() throws Exception {
         given().log().all()
                 .urlEncodingEnabled(true)
                 .body(getResourceFileAsString(VALID_REQUEST_I_ENVELOPE))
                 .when().contentType(APPLICATION_XML_UTF_8).post(iosabUrl)
                 .then().log().all()
                 .statusCode(SC_OK)
                 .body(ABONNENT_BESTILLING_IDENTIFIKATOR, both(Matchers.is(any(String.class))).and(not(isEmptyOrNullString())));
     }

     @Test
     public void test_validRequest_O_shouldReturnAbonnentStructurAndData() throws Exception {
         given().log().all()
                 .urlEncodingEnabled(true)
                 .body(getResourceFileAsString(VALID_REQUEST_O_ENVELOPE))
                 .when().contentType(APPLICATION_XML_UTF_8).post(iosabUrl)
                 .then().log().all()
                 .statusCode(SC_OK)
                 .body(ABONNENT_ADGANG_STRUKTUR +".size()", is(1))
                 .body(ABONNENT_UDDATA_SAMLING+".size()", is(1));
     }

     @Test
     public void test_validRequest_Afslut_shouldResponseOK() throws Exception {
         given().log().all()
                 .urlEncodingEnabled(true)
                 .body(getResourceFileAsString(VALID_REQUEST_AFSLUT_ENVELOPE))
                 .when().contentType(APPLICATION_XML_UTF_8).post(iosabUrl)
                 .then().log().all()
                 .statusCode(SC_OK);
     }

     @Test
     public void test_emptyMessage_expect500code() throws Exception {
         given().log().all()
                 .body(getResourceFileAsString(EMPTY_REQUEST_ENVELOPE))
                 .when().contentType(APPLICATION_XML_UTF_8).post(iosabUrl)
                 .then().log().all()
                 .statusCode(SC_INTERNAL_SERVER_ERROR);
     }

     @Test
     public void test_malformedRequest_expect500code() throws Exception {
         given().log().all()
                 .body(getResourceFileAsString(MALFORMED_REQUEST_ENVELOPE))
                 .when().contentType(APPLICATION_XML_UTF_8).post(iosabUrl)
                 .then().log().all()
                 .statusCode(SC_INTERNAL_SERVER_ERROR)
                 .body(containsString("Could not parse the XML stream"));
     }
}
