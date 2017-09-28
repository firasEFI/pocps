package dk.rim.is.ic.inttests.aogd;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import dk.rim.is.common.CommonURLs;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.OffsetDateTime;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.*;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Integration tests of MeddelelseStatusMultiHentTest service.
 */
public class MeddelelseStatusMultiHentTest {

    //run mock http://localhost:50000/MeddelelseStatusMultiHentProxyService

    private static final Logger LOG = LoggerFactory.getLogger(MeddelelseMultiSendTest.class);

    private static final String SAMPLES_SHORT_PATH = "aogd/meddelelseStatusMultiHent/";
    private static final String PATH_PREFIX = "build/resources/test/";
    private static final String SAMPLES_PATH = PATH_PREFIX + SAMPLES_SHORT_PATH;

    private static final String AENDRETSTATUSVALG_REQUEST_BODY = SAMPLES_PATH + "AendretStatusValg_body.xml";
    private static final String BATCHVALG_REQUEST_BODY = SAMPLES_PATH + "BatchValgRequest_body.xml";
    private static final String EFMV_REQUEST_BODY = SAMPLES_PATH + "EnkeltFormatteretMeddelelseValgRequest_body.xml";
    private static final String ENKELTMEDDELELSEVALG_REQUEST_BODY = SAMPLES_PATH + "EnkeltMeddelelseValgRequest_body.xml";
    private static final String PARTIELBATCHVALG_REQUEST_BODY = SAMPLES_PATH + "PartielBatchValgRequest_body.xml";

    private static final String SOAP_ENVELOPE = "envelope/Soap_envelope.xml";
    private static final String BODY_PARAM = "{body}";

    private static final String EMPTY_REQUEST_BODY = SAMPLES_PATH + "Empty_body.xml";
    private static final String MALFORMED_REQUEST_BODY = SAMPLES_PATH + "MalformedRequest_body.xml";

    private final String BATCH_ID_PARAM = "${Properties#MeddelelseBatchID}";
    private final String MESSAGE_ID_PARAM = "${Properties#MeddelelseID}";
    private final String FMESSAGE_ID_PARAM = "${Properties#FormateretMeddelelseID}";
    private final String START_REF_PARAM = "${Properties#StartRef}";
    private final String END_REF_PARAM = "${Properties#SlutRef}";
    private final String DATE_FROM_PARAM = "${Properties#DatoFra}";
    private final String DATE_TO_PARAM = "${Properties#DatoTil}";

    private static String meddelelseStatusMultiHentRestUrl;
    private static String meddelelseStatusMultiHentSoapUrl;

    @BeforeClass
    public static void loadProperties() {
        meddelelseStatusMultiHentRestUrl = toUrl("http", IC_HOST.load(), IC_REST_EXTERNAL_PORT.load(), CommonURLs.AOGD.ROOT + CommonURLs.AOGD.MEDDELELSE_STATUS_MULTI_HENT);
        meddelelseStatusMultiHentSoapUrl = toUrl("http", IC_HOST, IC_SOAP_EXTERNAL_INTERNALPORT, IC_AOGD_MEDDELELSESTATUSMULTIHENT_KLADDE_PATH);

        LOG.info("Using meddelelseStatusMultiHentRestUrl: " + meddelelseStatusMultiHentRestUrl);
        LOG.info("Using meddelelseStatusMultiHentSoapUrl: " + meddelelseStatusMultiHentSoapUrl);
    }

    @Test
    public void testMeddelelseStatus_emptyMessage_expect500code() throws Exception {
        given().log().all()
                .body(new File(EMPTY_REQUEST_BODY))
                .when().contentType(APPLICATION_XML_UTF_8).post(meddelelseStatusMultiHentRestUrl)
                .then().log().all()
                .statusCode(400);
    }

    @Test
    public void testMeddelelseStatus_validAendretStatusValgRequest_expect200code() throws Exception {
        testValidRequest(AENDRETSTATUSVALG_REQUEST_BODY);
    }

    @Test
    public void testMeddelelseStatus_validBatchValgRequest_expect200code() throws Exception {
        testValidRequest(BATCHVALG_REQUEST_BODY);
    }

    @Test
    public void testMeddelelseStatus_validEfmvRequest_expect200code() throws Exception {
        testValidRequest(EFMV_REQUEST_BODY);
    }

    @Test
    public void testMeddelelseStatus_validEnkeltMeddelelseValgRequest_expect200code() throws Exception {
        testValidRequest(ENKELTMEDDELELSEVALG_REQUEST_BODY);
    }

    @Test
    public void testMeddelelseStatus_validPartielBatchValgRequest_expect200code() throws Exception {
        testValidRequest(PARTIELBATCHVALG_REQUEST_BODY);
    }

    private void testValidRequest(String requestFilePath) throws IOException {
        URL requestUrl = Resources.getResource(requestFilePath.replace(PATH_PREFIX, ""));
        String request = Resources.toString(requestUrl, Charsets.UTF_8);

        request = request.replace(BATCH_ID_PARAM,"1479985306");
        request = request.replace(MESSAGE_ID_PARAM,"23803665");
        request = request.replace(FMESSAGE_ID_PARAM,"811651");
        request = request.replace(START_REF_PARAM,"98");
        request = request.replace(END_REF_PARAM,"100");
        request = request.replace(DATE_FROM_PARAM,"2016-11-23T13:41:28.000+01:00");
        request = request.replace(DATE_TO_PARAM,"2016-11-25T13:41:28.000+01:00");
        String meddelelseOprettetDatoTid = given().log().all()
                .body(request)
                .when().contentType(APPLICATION_XML_UTF_8).post(meddelelseStatusMultiHentRestUrl)
                .then().log().all()
                .statusCode(200)
                .contentType(APPLICATION_XML_UTF_8)
                .body("MeddelelseStatusMultiHent_O.FagsystemNavn", equalTo(expectedFagsystemNavn()))
                .body("MeddelelseStatusMultiHent_O.MeddelelseListe.Meddelelse.size()", equalTo(1))
                .extract().path("MeddelelseStatusMultiHent_O.MeddelelseListe.Meddelelse.MeddelelseOprettetDatoTid");
        OffsetDateTime expectedDate = OffsetDateTime.parse(expectedDateString());
        Assert.assertTrue(OffsetDateTime.parse(meddelelseOprettetDatoTid).isEqual(expectedDate));
    }

    //------------------- SOAP tests ------------------------

    @Test
    public void testMeddelelseStatusSoap_emptyMessage_expect500code() throws Exception {
        String message = getMessage(EMPTY_REQUEST_BODY);
        given().log().all()
                .body(message)
                .when().contentType(APPLICATION_XML_UTF_8).post(meddelelseStatusMultiHentSoapUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void testMeddelelseStatusSoap_malformedRequest_expect500code() throws Exception {
        String message = getMessage(MALFORMED_REQUEST_BODY);
        given().log().all()
                .body(message)
                .when().contentType(APPLICATION_XML_UTF_8).post(meddelelseStatusMultiHentSoapUrl)
                .then().log().all()
                .statusCode(500)
                .body(containsString("Could not parse the XML stream"));
    }

    @Test
    public void testMeddelelseStatusSoap_validAendretStatusValgRequest_expect200code() throws Exception {
        testValidSoapRequest(AENDRETSTATUSVALG_REQUEST_BODY);
    }

    @Test
    public void testMeddelelseStatusSoap_validBatchValgRequest_expect200code() throws Exception {
        testValidSoapRequest(BATCHVALG_REQUEST_BODY);
    }

    @Test
    public void testMeddelelseStatusSoap_validEfmvRequest_expect200code() throws Exception {
        testValidSoapRequest(EFMV_REQUEST_BODY);
    }

    @Test
    public void testMeddelelseStatusSoap_validEnkeltMeddelelseValgRequest_expect200code() throws Exception {
        testValidSoapRequest(ENKELTMEDDELELSEVALG_REQUEST_BODY);
    }

    @Test
    public void testMeddelelseStatusSoap_validPartielBatchValgRequest_expect200code() throws Exception {
        testValidSoapRequest(PARTIELBATCHVALG_REQUEST_BODY);
    }

    private void testValidSoapRequest(String requestBodyFilePath) throws IOException {
        String message = getMessage(requestBodyFilePath);
        String meddelelseOprettetDatoTid = given().log().all()
                .body(message)
                .when().contentType(APPLICATION_XML_UTF_8).post(meddelelseStatusMultiHentSoapUrl)
                .then().log().all()
                .statusCode(200)
                .body("Envelope.Body.MeddelelseStatusMultiHent_O.FagsystemNavn", equalTo(expectedFagsystemNavn()))
                .body("Envelope.Body.MeddelelseStatusMultiHent_O.MeddelelseListe.Meddelelse.size()", equalTo(1))
                .extract().path("Envelope.Body.MeddelelseStatusMultiHent_O.MeddelelseListe.Meddelelse.MeddelelseOprettetDatoTid");
        OffsetDateTime expectedDate = OffsetDateTime.parse(expectedDateString());
        Assert.assertTrue(OffsetDateTime.parse(meddelelseOprettetDatoTid).isEqual(expectedDate));
    }

    protected String expectedFagsystemNavn() {
        return "IND";
    }

    protected String expectedDateString() {
        return "2017-05-18T13:06:11.000+02:00";
    }

    private String getMessage(String requestBodyFilePath) throws IOException {
        URL soapEnvelopeUrl = Resources.getResource(SOAP_ENVELOPE);
        String soapEnvelopeTemplate = Resources.toString(soapEnvelopeUrl, Charsets.UTF_8);

        URL messageBodyUrl = Resources.getResource(requestBodyFilePath.replace(PATH_PREFIX, ""));
        String messageBody = Resources.toString(messageBodyUrl, Charsets.UTF_8);
        messageBody = messageBody.replace(BATCH_ID_PARAM,"1479985306");
        messageBody = messageBody.replace(MESSAGE_ID_PARAM,"23803665");
        messageBody = messageBody.replace(FMESSAGE_ID_PARAM,"811651");
        messageBody = messageBody.replace(START_REF_PARAM,"98");
        messageBody = messageBody.replace(END_REF_PARAM,"100");
        messageBody = messageBody.replace(DATE_FROM_PARAM,"2016-11-23T13:41:28.000+01:00");
        messageBody = messageBody.replace(DATE_TO_PARAM,"2016-11-25T13:41:28.000+01:00");

        return soapEnvelopeTemplate.replace(BODY_PARAM, messageBody);
    }

}
