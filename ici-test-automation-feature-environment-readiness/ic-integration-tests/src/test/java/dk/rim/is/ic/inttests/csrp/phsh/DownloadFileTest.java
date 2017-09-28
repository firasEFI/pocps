package dk.rim.is.ic.inttests.csrp.phsh;

import dk.rim.is.common.CommonURLs;
import dk.rim.is.ic.inttests.Property;
import dk.rim.is.ic.inttests.UrlUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * Checks if sending downloadFile REST request to IC works as expected.
 */
public class DownloadFileTest {

    //Before test run ftp server:
    //SKMICI/Utilities/ftp-server-mock/target>java -jar FtpServerMock.jar -f / -l C:\Tmp\FtpDir -p 10021 -u user -up pass

    private static String downloadFileUrl;

    @BeforeClass
    public static void loadProperties() {
        String host = Property.IC_HOST.load();
        String port = Property.IC_REST_EXTERNAL_PORT.load();
        String path = CommonURLs.CSRP.ROOT + CommonURLs.CSRP.DOWNLOAD_FILE;
        downloadFileUrl = UrlUtils.toUrl("http", host, port, path);
    }

    @Test
    public void downloadFile_fileNotExists_status500() {
        given().body("{ \"filename\" : \"testFile.xml\" }")
            .when()
            .post(downloadFileUrl)
            .then()
            .statusCode(500);
    }
}
