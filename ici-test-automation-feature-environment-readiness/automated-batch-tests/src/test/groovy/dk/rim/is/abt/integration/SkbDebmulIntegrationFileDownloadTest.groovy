package dk.rim.is.abt.integration

import dk.rim.is.abt.dao.cisadm.IntegrationFileEntity
import dk.rim.is.abt.util.FtpUploader
import dk.rim.is.abt.util.GenericDao
import org.apache.deltaspike.core.api.config.ConfigResolver
import org.apache.http.Consts
import org.apache.http.entity.ContentType
import org.junit.After
import org.junit.Test

import java.sql.SQLException
import java.text.SimpleDateFormat
import java.time.LocalDateTime

import static dk.rim.is.abt.util.SpringContext.buildDao
import static io.restassured.RestAssured.given
import static org.assertj.core.api.Assertions.assertThat

/**
 * Created by aso on 12-05-2017.
 */
class SkbDebmulIntegrationFileDownloadTest {

    private static final HTTP_FTP_USER = ConfigResolver.getPropertyValue("testutil.user")
    private static final HTTP_FTP_PASS = ConfigResolver.getPropertyValue("testutil.password")

    private static final MA_HOST = ConfigResolver.getPropertyValue("ic.external.host")
    private static final MA_PORT = ConfigResolver.getPropertyValue("ic.external.port")

    GenericDao<IntegrationFileEntity> fileDao = buildDao(IntegrationFileEntity.class);
    String filename = ""
    String mockFilename = ""

    @Test
    void runTest() {
        filename = "dummy.xml"

        mockFilename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" +filename

        new FtpUploader().ftpgwUpload(getClass().getResourceAsStream("/" + filename), mockFilename);
        notifyBatch("Nets/INDBetalingsanmodningerTraekListeSendService", mockFilename)
        try {
            synchronized(this){
                wait(5000)
            }
        } catch (InterruptedException e) {
        }
        verifyFileInPSRMDB(mockFilename)
    }

    /**
     *
     */
    @After
    void cleanUp() throws SQLException {
        fileDao.delete({p->p.filename.equals(mockFilename)})
    }

    /**
     * We want to tell Batch that there is a file ready for import/work
     * @param component
     * @param name
     * @param filename
     */
    private static notifyBatch(String component, String filename) {
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://skat.dk/begrebsmodel/2009/01/15/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ns:StyretFiloverførselModtagAnmod_I revision=\"?\">\n" +
                "         <ns:Kontekst>\n" +
                "            <!--You may enter ANY elements at this point-->\n" +
                "            <tsk:HovedOplysninger xmlns:tsk=\"http://skat.dk/begrebsmodel/xml/schemas/kontekst/2007/05/31/\">" +
                "               <tsk:TransaktionsID>52ea66ca-4778-4877-b2a2-0b99748eb4f0</tsk:TransaktionsID>" +
                "               <tsk:TransaktionsTid>" + LocalDateTime.now().toString().substring(0, 19) + "</tsk:TransaktionsTid>" +
                "            </tsk:HovedOplysninger>" +
                "         </ns:Kontekst>\n" +
                "         <ns:StyretFiloverførselServiceQName>INDKontoudtogOplysningListeModtagService</ns:StyretFiloverførselServiceQName>\n" +
                "         <ns:StyretFiloverførselBeskedQName>StyretFiloverførselModtagAnmod_I</ns:StyretFiloverførselBeskedQName>\n" +
                "         <ns:StyretFiloverførselDownloadURL>dwcsc/" + filename  + "</ns:StyretFiloverførselDownloadURL>\n" +
                "         <ns:StyretFiloverførselBrugerNavn>" + HTTP_FTP_USER + "</ns:StyretFiloverførselBrugerNavn>\n" +
                "         <ns:StyretFiloverførselPassword>" + HTTP_FTP_PASS + "</ns:StyretFiloverførselPassword>\n" +
                "      </ns:StyretFiloverførselModtagAnmod_I>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>"
        // Send data

        given().log().all()
                .body(xml)
                .when().contentType(ContentType.APPLICATION_XML.withCharset(Consts.UTF_8).toString())
                .post( "http://" + MA_HOST + ":" + MA_PORT + "/" + component)
                .then().log().all()
                .statusCode(200)

    }

    /**
     * We want to check that the file is actually in PSRM, this will signify a success.
     * @param filename
     */
    void verifyFileInPSRMDB(String filename) {
        List<IntegrationFileEntity> fileList = fileDao.getBy({ p -> p.filename.equals(filename)})

        assertThat(fileList).hasSize(1);
    }
}
