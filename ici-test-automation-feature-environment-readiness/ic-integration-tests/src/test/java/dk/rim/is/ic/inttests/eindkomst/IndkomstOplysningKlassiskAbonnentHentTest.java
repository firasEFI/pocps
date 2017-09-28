package dk.rim.is.ic.inttests.eindkomst;

import org.junit.Test;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.IC_HOST;
import static dk.rim.is.ic.inttests.Property.IC_REST_EXTERNAL_PORT;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static dk.rim.is.ic.inttests.eindkomst.IndkomstOplysningKlassiskAbonnentHentTest.EINDKOMST.INDKOMST_OPLYSNING_KLASSISK_ABONNENT_HENT_AFSLUT;
import static dk.rim.is.ic.inttests.eindkomst.IndkomstOplysningKlassiskAbonnentHentTest.EINDKOMST.INDKOMST_OPLYSNING_KLASSISK_ABONNENT_HENT_I;
import static dk.rim.is.ic.inttests.eindkomst.IndkomstOplysningKlassiskAbonnentHentTest.EINDKOMST.INDKOMST_OPLYSNING_KLASSISK_ABONNENT_HENT_O;
import static dk.rim.is.ic.inttests.util.file.FileReader.getResourceFileAsString;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

/**
 * Created by maho on 25.08.2017.
 */
public class IndkomstOplysningKlassiskAbonnentHentTest {

    private static final String SAMPLES_PATH = "eindkomst/iokah/";

    private static final String VALID_REQUEST_I_BODY = SAMPLES_PATH + "ValidRequest_I_body.xml";
    private static final String VALID_REQUEST_O_BODY = SAMPLES_PATH + "ValidRequest_O_body.xml";
    private static final String VALID_REQUEST_AFSLUT_BODY = SAMPLES_PATH + "ValidRequest_Afslut_body.xml";

    private static final String ABONNENT_BESTILLING_IDENTIFIKATOR = "IndkomstOplysningKlassiskAbonnentHent_I_O.AbonnentBestillingIdentifikator";
    private static final String INDKOMST_OPLYSNING_VIRKSOMHED  = "IndkomstOplysningKlassiskAbonnentHent_O_O.IndkomstOplysningKlassiskAbonnentUddata.IndkomstOplysningVirksomhedSamling.IndkomstOplysningVirksomhed";
    
    private static String iokah_I_Url = toUrl("http", IC_HOST.load(), IC_REST_EXTERNAL_PORT.load(), EINDKOMST.ROOT + INDKOMST_OPLYSNING_KLASSISK_ABONNENT_HENT_I);
    private static String iokah_O_Url = toUrl("http", IC_HOST.load(), IC_REST_EXTERNAL_PORT.load(), EINDKOMST.ROOT + INDKOMST_OPLYSNING_KLASSISK_ABONNENT_HENT_O);
    private static String iokah_Afslut_Url = toUrl("http", IC_HOST.load(), IC_REST_EXTERNAL_PORT.load(), EINDKOMST.ROOT + INDKOMST_OPLYSNING_KLASSISK_ABONNENT_HENT_AFSLUT);

    @Test
    public void test_validRequest_I_shouldReturnAbonnentBestillingIdentifikator() throws Exception {
        given().log().all()
                .urlEncodingEnabled(true)
                .body(getResourceFileAsString(VALID_REQUEST_I_BODY))
                .when().contentType(APPLICATION_XML_UTF_8).post(iokah_I_Url)
                .then().log().all()
                .statusCode(SC_OK)
                .body(ABONNENT_BESTILLING_IDENTIFIKATOR, both(is(any(String.class))).and(not(isEmptyOrNullString())));
    }

    @Test
    public void test_validRequest_O_shouldReturnAbonnentStructurAndData() throws Exception {
        given().log().all()
                .urlEncodingEnabled(true)
                .body(getResourceFileAsString(VALID_REQUEST_O_BODY))
                .when().contentType(APPLICATION_XML_UTF_8).post(iokah_O_Url)
                .then().log().all()
                .statusCode(SC_OK)
                .body(INDKOMST_OPLYSNING_VIRKSOMHED, hasItem(notNullValue()));
    }

    @Test
    public void test_validRequest_Afslut_shouldResponseOK() throws Exception {
        given().log().all()
                .urlEncodingEnabled(true)
                .body(getResourceFileAsString(VALID_REQUEST_AFSLUT_BODY))
                .when().contentType(APPLICATION_XML_UTF_8).post(iokah_Afslut_Url)
                .then().log().all()
                .statusCode(SC_OK);
    }

    @Test
    public void testKlassiskAbonnentHent_malformedRequest_expect500code() throws Exception {
        String malformedRequest = "<ns:IndkomstOplysningKlassiskAbonnentHent_O_I xm";
        given().log().all()
                .body(malformedRequest)
                .when().contentType(APPLICATION_XML_UTF_8).post(iokah_O_Url)
                .then().log().all()
                .statusCode(500)
                .body(containsString("SAXParseException"));
    }

    //Copied from CommonURLs on 'develop' branch. For integration tests we have only master branch. After merging to master it needs to remove
    interface EINDKOMST {
        String ROOT = "/eindkomst";
        String INDKOMST_OPLYSNING_KLASSISK_ABONNENT_HENT_I = "/IndkomstOplysningKlassiskAbonnentHent_I";
        String INDKOMST_OPLYSNING_KLASSISK_ABONNENT_HENT_O = "/IndkomstOplysningKlassiskAbonnentHent_O";
        String INDKOMST_OPLYSNING_KLASSISK_ABONNENT_HENT_AFSLUT = "/IndkomstOplysningKlassiskAbonnentHent_Afslut";
    }
}
