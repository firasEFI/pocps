package dk.rim.is.ic.inttests.captia;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import dk.rim.is.ic.inttests.DatabaseTester;
import dk.rim.is.ic.inttests.DatabaseTesterFactory;
import dk.rim.is.ic.inttests.Property;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Captia document scenario test.
 */
public class CaptiaDocumentScenarioTest {

    //run captia mocks

    private static final Logger LOG = LoggerFactory.getLogger(DokumentHentTest.class);

    private static final String SAMPLES_PATH = "build/resources/test/captia/dokumentScenario/";
    private static final String SAMPLES_SHORT_PATH = "captia/dokumentScenario/";

    private static final String CREATE_CASE_MESSAGE = SAMPLES_PATH + "CreateCase_envelope.xml";
    private static final String CREATE_DOCUMENT_MESSAGE = SAMPLES_SHORT_PATH + "CreateDocument_envelope.xml";
    private static final String READ_DOCUMENT_MESSAGE = SAMPLES_SHORT_PATH + "ReadDocument_envelope.xml";
    private static final String UPDATE_DOCUMENT_MESSAGE = SAMPLES_SHORT_PATH + "UpdateDocument_envelope.xml";

    private static String sagOpretUrl;
    private static String dokumentHentUrl;
    private static String dokumentOpdaterUrl;
    private static String dokumentMultiOpretUrl;

    private final String CASE_NUMBER_PARAM = "${Properties#SagNummer}";
    private final String DOCUMENT_NUMBER_PARAM = "${Properties#DokumentNummer}";
    private final String DOCUMENT_TITLE_PARAM = "${Properties#DokumentTitel}";
    private final String DOCUMENT_UUID_PARAM = "${Properties#DokumentUUID}";

    @Before
    public void setupDb() {
        DatabaseTester dbTester = DatabaseTesterFactory.createDbTester();
        dbTester.deleteEntities("DOCUMENT", "UUID='49705142-cb3f-4428-b507-c0235fc744be'");

        List<Map<String, String>> insertMapList = new ArrayList<>();
        Map<String, String> insertMap = new HashMap<>();
        insertMap.put("CREATEDATE", "SYSDATE");
        insertMap.put("DOCUMENT", "utl_raw.cast_to_raw('dGVzdA==')");
        insertMap.put("FILETYPE", "'pdf'");
        insertMap.put("UUID", "'49705142-cb3f-4428-b507-c0235fc744be'");
        insertMapList.add(insertMap);
        dbTester.bulkInsertEntities("DOCUMENT", insertMapList);
    }

    @BeforeClass
    public static void loadProperties() throws MalformedURLException {
        String protocol = Property.IC_SOAP_EXTERNAL_PROTOCOL.load();
        String host = Property.IC_HOST.load();
        String port = Property.IC_SOAP_EXTERNAL_INTERNALPORT.load();

        sagOpretUrl = toUrl(protocol, host, port, Property.IC_CAPTIA_SAGOPRET_PATH);
        dokumentHentUrl = toUrl(protocol, host, port, Property.IC_CAPTIA_DOKUMENTHENT_PATH);
        dokumentOpdaterUrl = toUrl(protocol, host, port, Property.IC_CAPTIA_DOKUMENTOPDATER_PATH);
        dokumentMultiOpretUrl = toUrl(protocol, host, port, Property.IC_CAPTIA_DOKUMENTMULTIOPRET_PATH);

        LOG.info("Using sagOpretUrl: " + sagOpretUrl);
        LOG.info("Using dokumentHentUrl: " + dokumentHentUrl);
        LOG.info("Using dokumentOpdaterUrl: " + dokumentOpdaterUrl);
        LOG.info("Using dokumentMultiOpretUrl: " + dokumentMultiOpretUrl);
    }

    @Test
    public void testDocumentCreationModification_validRequest_expect200code() throws Exception {
        String caseNumber = createNewCase();

        DocumentVO document = new DocumentVO();
        document.setDocumentTitle("DocumentTitle");
        document.setDocumentUUID("49705142-cb3f-4428-b507-c0235fc744be");
        document.setCaseNumber(caseNumber);

        String documentNumber = createDocument(document);
        document.setDocumentNumber(documentNumber);

        readDocumentAndValidate(document);

        //The same value because soapui mock returns static value
        document.setDocumentTitle("DocumentTitle2");
        updateDocument(document);

        readDocumentAndValidate(document);
    }

    private String createNewCase() {
        return given().log().all()
                    .body(new File(CREATE_CASE_MESSAGE))
                    .when().contentType(APPLICATION_XML_UTF_8).post(sagOpretUrl)
                    .then().log().all()
                    .statusCode(200)
                    .body("Envelope.Body.SagOpret_O.SagNummer", notNullValue())
                    .extract().path("Envelope.Body.SagOpret_O.SagNummer");
    }

    private String createDocument(DocumentVO document) throws IOException {
        URL messageTemplateUrl = Resources.getResource(CREATE_DOCUMENT_MESSAGE);
        String messageTemplate = Resources.toString(messageTemplateUrl, Charsets.UTF_8);

        String message = messageTemplate.replace(CASE_NUMBER_PARAM, document.getCaseNumber());
        message = message.replace(DOCUMENT_TITLE_PARAM, document.getDocumentTitle());
        message = message.replace(DOCUMENT_UUID_PARAM, document.getDocumentUUID());

        return given().log().all()
                .body(message)
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentMultiOpretUrl)
                .then().log().all()
                .statusCode(200)
                .body("Envelope.Body.DokumentMultiOpret_O.Sag.SagNummer", equalTo(document.getCaseNumber()))
                .body("Envelope.Body.DokumentMultiOpret_O.Sag.DokumentListe.Dokument[0].DokumentNummer", notNullValue())
                .extract().path("Envelope.Body.DokumentMultiOpret_O.Sag.DokumentListe.Dokument[0].DokumentNummer");
    }

    private void readDocumentAndValidate(DocumentVO document) throws IOException {
        URL messageTemplateUrl = Resources.getResource(READ_DOCUMENT_MESSAGE);
        String messageTemplate = Resources.toString(messageTemplateUrl, Charsets.UTF_8);
        String message = messageTemplate.replace(DOCUMENT_NUMBER_PARAM, document.getDocumentNumber());

        given().log().all()
                .body(message)
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentHentUrl)
                .then().log().all()
                .statusCode(200)
                .body("Envelope.Body.DokumentHent_O.Dokument.DokumentMetadata.DokumentNummer", equalTo(document.getDocumentNumber()))
                .body("Envelope.Body.DokumentHent_O.Dokument.DokumentMetadata.DokumentTitel", equalTo(document.getDocumentTitle()))
                .body("Envelope.Body.DokumentHent_O.Dokument.DokumentMetadata.DokumentUUID", equalTo(document.getDocumentUUID()));
    }

    private void updateDocument(DocumentVO document) throws IOException {
        URL messageTemplateUrl = Resources.getResource(UPDATE_DOCUMENT_MESSAGE);
        String messageTemplate = Resources.toString(messageTemplateUrl, Charsets.UTF_8);

        String message = messageTemplate.replace(DOCUMENT_NUMBER_PARAM, document.getDocumentNumber());
        message = message.replace(DOCUMENT_TITLE_PARAM, document.getDocumentTitle());

        given().log().all()
                .body(message)
                .when().contentType(APPLICATION_XML_UTF_8).post(dokumentOpdaterUrl)
                .then().log().all()
                .statusCode(200)
                .body("Envelope.Body.DokumentOpdater_O", notNullValue());
    }

    private static class DocumentVO {

        private String caseNumber;
        private String documentNumber;
        private String documentTitle;
        private String documentUUID;

        String getCaseNumber() {
            return caseNumber;
        }

        void setCaseNumber(String caseNumber) {
            this.caseNumber = caseNumber;
        }

        String getDocumentNumber() {
            return documentNumber;
        }

        void setDocumentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
        }

        String getDocumentTitle() {
            return documentTitle;
        }

        void setDocumentTitle(String documentTitle) {
            this.documentTitle = documentTitle;
        }

        public String getDocumentUUID() {
            return documentUUID;
        }

        public void setDocumentUUID(String documentUUID) {
            this.documentUUID = documentUUID;
        }
    }

}
