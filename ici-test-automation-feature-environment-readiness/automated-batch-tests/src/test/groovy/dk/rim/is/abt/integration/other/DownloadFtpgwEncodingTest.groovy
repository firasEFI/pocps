package dk.rim.is.abt.integration.other

import dk.rim.is.abt.util.FtpUploader
import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.junit.Test

import java.time.LocalDateTime;


public class DownloadFtpgwEncodingTest {
    private static final HTTP_FTP_USER = ConfigResolver.getPropertyValue("testutil.user")
    private static final HTTP_FTP_PASS = ConfigResolver.getPropertyValue("testutil.password")

    private static final MA_HOST = ConfigResolver.getPropertyValue("ic.external.host")
    private static final MA_PORT = ConfigResolver.getPropertyValue("ic.external.port")

    @Test
    public void runTest() {
        String filename = "SKB_FILE.txt"
        new FtpUploader().ftpgwUpload(getClass().getResourceAsStream("/file/skb/" + filename), filename);
        notifyBatch("Nets/INDBetalingsanmodningerTraekListeSendService", filename)
       // verifyFileInPSRMDB(filename)
    }

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
                "         <ns:StyretFiloverførselDownloadURL>" + filename  + "</ns:StyretFiloverførselDownloadURL>\n" +
                "         <ns:StyretFiloverførselBrugerNavn>" + HTTP_FTP_USER + "</ns:StyretFiloverførselBrugerNavn>\n" +
                "         <ns:StyretFiloverførselPassword>" + HTTP_FTP_PASS + "</ns:StyretFiloverførselPassword>\n" +
                "      </ns:StyretFiloverførselModtagAnmod_I>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>"
        // Send data
        def batchUrl = "http://" + MA_HOST + ":" + MA_PORT + "/" + component;
        URL url = new URL(batchUrl);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "text/xml");
        conn.setRequestProperty( "Content-Type", "charset=utf-8");
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(xml);
        wr.close();

        // Get the response
        String response;
        InputStream responseStream;
        try {
            responseStream = conn.getInputStream();
        } catch (IOException e) {
            if (conn.getResponseCode() == 500) {
                responseStream = conn.getErrorStream();
            } else throw e;
        }
        response = responseStream.getText("utf-8");
        responseStream.close();
        if (response.contains("<faultcode>") || response.contains("<faultstring>")) {
            System.out.println(response)
            //if this failed, we might as well just end the test here with a fail.
            throw new IllegalArgumentException();
        }
    }
}
