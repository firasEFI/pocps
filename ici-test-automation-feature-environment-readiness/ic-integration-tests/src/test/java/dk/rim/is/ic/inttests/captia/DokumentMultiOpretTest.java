package dk.rim.is.ic.inttests.captia;

import dk.rim.is.ic.inttests.DatabaseTester;
import dk.rim.is.ic.inttests.DatabaseTesterFactory;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.IC_CAPTIA_DOKUMENTMULTIOPRET_BESKEDHVISVIRUS_PATH;
import static dk.rim.is.ic.inttests.Property.IC_CAPTIA_DOKUMENTMULTIOPRET_PATH;
import static dk.rim.is.ic.inttests.Property.IC_HOST;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_INTERNALPORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class DokumentMultiOpretTest {

    private static final String SAMPLES_PATH = "build/resources/test/captia/dokumentmultiopret/";

    private static final String VALID_REQUEST_ENVELOPE = SAMPLES_PATH + "ValidRequest_envelope.xml";
    private static final String EMPTY_REQUEST_ENVELOPE = SAMPLES_PATH + "Empty_envelope.xml";
    private static final String MALFORMED_REQUEST_ENVELOPE = SAMPLES_PATH + "MalformedRequest.xml";
    private static final String INFECTED_REQUEST_ENVELOPE = SAMPLES_PATH + "InfectedRequest_envelope.xml";

    private static String dokumentMultiOpretUrl;
    private static String dokumentMultiOpretBeskedHvisVirusUrl;

    @BeforeClass
    public static void loadProperties() {
        dokumentMultiOpretUrl = toUrl(IC_SOAP_EXTERNAL_PROTOCOL, IC_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_CAPTIA_DOKUMENTMULTIOPRET_PATH);
        dokumentMultiOpretBeskedHvisVirusUrl = toUrl(IC_SOAP_EXTERNAL_PROTOCOL, IC_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_CAPTIA_DOKUMENTMULTIOPRET_BESKEDHVISVIRUS_PATH);
    }

    @BeforeClass
    static public void setupDb() {
        DatabaseTester dbTester = DatabaseTesterFactory.createDbTester();
        dbTester.deleteEntities("DOCUMENT", "UUID='49705142-cb3f-4428-b507-c0235fc744be'");

        List<Map<String, String>> insertMapList = new ArrayList<>();
        Map<String, String> insertMap = new HashMap<>();
        insertMap.put("CREATEDATE", "SYSDATE");
        insertMap.put("DOCUMENT", "'123'");
        insertMap.put("FILETYPE", "1");
        insertMap.put("UUID", "'49705142-cb3f-4428-b507-c0235fc744be'");
        insertMapList.add(insertMap);
        dbTester.bulkInsertEntities("DOCUMENT", insertMapList);
    }

    @Test
    public void testDokumentMultiOpret_emptyMessage_expect500code() throws Exception {
        given().log().all()
                .body(new File(EMPTY_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentMultiOpretUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void testDokumentMultiOpret_malformedRequest_expect500code() throws Exception {
        given().log().all()
                .body(new File(MALFORMED_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentMultiOpretUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("Could not parse the XML stream"));
    }

    // TODO: 16-02-2017 TOMB please verify response - valid response (with error inside) gets intercepted by camel and transformed into exception with code 500
    @Test
    public void testDokumentMultiOpret_validRequest_unknownProfileName_expect500code() throws Exception {
        given().log().all()
                .body(new File(VALID_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentMultiOpretUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("DokumentProfilNavn ukendt"));
    }

    @Test
    public void testDokumentMultiOpret_beskedHvisVirus_infectedRequest_expectErrorResponse() throws Exception {
        given().log().all()
                .body(new File(INFECTED_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentMultiOpretBeskedHvisVirusUrl)
                .then().log().all()
                .statusCode(200)
                .body(containsString("Operation did not complete successfully because the file contains a virus."));
    }

    @Test
    public void testDokumentMultiOpret_beskedHvisVirus_validRequest_unknownProfileName_expect500code() throws Exception {
        given().log().all()
                .body(new File(VALID_REQUEST_ENVELOPE))
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentMultiOpretBeskedHvisVirusUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("DokumentProfilNavn ukendt"));
    }
}
