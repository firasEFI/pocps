package dk.rim.is.ic.inttests.common;

import dk.rim.is.common.CommonURLs;
import dk.rim.is.ic.inttests.DatabaseTester;
import dk.rim.is.ic.inttests.Property;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_JSON;
import static dk.rim.is.ic.inttests.DatabaseTesterFactory.createDbTester;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Integration test for AsynchronousFileTransfer, downloadFile endpoint.
 */
public class AsynchronousFileDownloadTest {
    //Before test, run ftp server:
    //SKMICI/Utilities/ftp-server-mock/target>java -jar FtpServerMock.jar -f / -l C:\Tmp\FtpDir -p 10021 -u user -up pass
    //put sample.txt (name configurable in deltaspike) in ftp directory

    private static final Logger LOG = LoggerFactory.getLogger(AsynchronousFileDownloadTest.class);

    private static final String FTP_USER = "user";
    private static final String FTP_PASSWORD = "pass";
    private static final String INTEGRATION_FILE_TABLE = "CISADM.INTEGRATION_FILE";
    private static final String INCOMING_NETS_FILETYPE = "NETS_602";

    private static String asynchFileDownloadUrl;
    private static String ftpUrl;
    private static String ftpTestFile;

    @BeforeClass
    public static void loadProperties() {
        String host = Property.IC_HOST.load();
        String port = Property.IC_REST_EXTERNAL_PORT.load();
        String path = CommonURLs.ASYNC_FILE.IC_DOWNLOAD + "/";
        String ftphost = Property.FTP_HOST.load();
        String ftpPort = Property.FTP_PORT.load();
        asynchFileDownloadUrl = toUrl("http", host, port, path);
        ftpUrl = "ftp://" + ftphost + ":" + ftpPort;
        ftpTestFile = Property.FTP_SAMPLE_FILE.load();
        LOG.info("Using asynchFileDownloadUrl: " + asynchFileDownloadUrl);
    }

    @Test
    public void downloadFile_emptyMessage_expect500code() throws Exception {
        DatabaseTester dbTester = createDbTester();
        assertTrue(dbTester.tableExists(INTEGRATION_FILE_TABLE));
        int integrationFileCount = dbTester.countEntities(INTEGRATION_FILE_TABLE);

        given().log().all()
                .body("{}")
                .when().contentType(APPLICATION_JSON).post(asynchFileDownloadUrl)
                .then().log().all()
                .statusCode(500);

        assertThat(dbTester.countEntities(INTEGRATION_FILE_TABLE), is(integrationFileCount));
    }

    @Test
    public void downloadFile_malformedRequest_expect500code() throws Exception {
        DatabaseTester dbTester = createDbTester();
        assertTrue(dbTester.tableExists(INTEGRATION_FILE_TABLE));
        int integrationFileCount = dbTester.countEntities(INTEGRATION_FILE_TABLE);

        String downloadUrl = ftpUrl + "/" + ftpTestFile;
        String malformedRequest = "{ \"serviceQName\": \"testserviceqname\",\n" +
                "  \"beskedQName\": \"testbeskedqname\",\n" +
                "  \"downloadURL\": \"" + downloadUrl + "\",\n" +
                "  \"userName\": \"" + FTP_USER + "\",\n";
        given().log().all()
                .body(malformedRequest)
                .when().contentType(APPLICATION_JSON).post(asynchFileDownloadUrl)
                .then().log().all()
                .statusCode(500);

        assertThat(dbTester.countEntities(INTEGRATION_FILE_TABLE), is(integrationFileCount));
    }

    @Test
    public void downloadFile_whenValid_thenStatusCode200() {
        DatabaseTester dbTester = createDbTester();
        assertTrue(dbTester.tableExists(INTEGRATION_FILE_TABLE));
        int integrationFileCount = dbTester.countEntities(INTEGRATION_FILE_TABLE);

        String downloadUrl = ftpUrl + "/" + ftpTestFile;
        String request = "{ \"serviceQName\": \"testserviceqname\",\n" +
                "\"beskedQName\": \"testbeskedqname\",\n" +
                "\"downloadURL\": \"" + downloadUrl + "\",\n" +
                "\"userName\": \"" + FTP_USER + "\",\n" +
                "\"password\": \"" + FTP_PASSWORD + "\",\n" +
                "\"dataFileTypeName\": \"" + INCOMING_NETS_FILETYPE + "\" }";
        given().log().all()
                .urlEncodingEnabled(true)
                .body(request)
                .when().contentType(APPLICATION_JSON).post(asynchFileDownloadUrl)
                .then().log().all()
                .statusCode(200);

        assertThat(dbTester.countEntities(INTEGRATION_FILE_TABLE), is(integrationFileCount + 1));
    }
}
