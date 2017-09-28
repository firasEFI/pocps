package dk.rim.is.ic.inttests.common;

import dk.rim.is.common.CommonURLs;
import dk.rim.is.ic.inttests.DatabaseTester;
import dk.rim.is.ic.inttests.Property;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_JSON;
import static dk.rim.is.ic.inttests.DatabaseTesterFactory.createDbTester;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

/**
 * Integration test for AsynchronousFileTransfer, uploadFile endpoint.
 */
public class AsynchronousFileUploadTest {
    //Before test, run ftp server:
    //SKMICI/Utilities/ftp-server-mock/target>java -jar FtpServerMock.jar -f / -l C:\Tmp\FtpDir -p 10021 -u user -up pass

    private static final Logger LOG = LoggerFactory.getLogger(AsynchronousFileUploadTest.class);

    private static final String FTP_USER = "user";
    private static final String FTP_PASSWORD = "pass";
    private static final String INTEGRATION_FILE_TABLE = "CISADM.INTEGRATION_FILE";
    private static final String TEST_FILE_UUID = "test-uuid";

    private static String asynchFileUploadUrl;
    private static String ftpUrl;

    private static DatabaseTester dbTester = createDbTester();

    @BeforeClass
    public static void loadProperties() {
        String host = Property.IC_HOST.load();
        String port = Property.IC_REST_EXTERNAL_PORT.load();
        String path = CommonURLs.NETS.ROOT + CommonURLs.NETS.IC_UPLOAD;
        String ftpHost = Property.FTP_HOST.load();
        String ftpPort = Property.FTP_PORT.load();
        asynchFileUploadUrl = toUrl("http", host, port, path);
        ftpUrl = "ftp://" + ftpHost + ":" + ftpPort;
        LOG.info("Using asynchFileUploadUrl: " + asynchFileUploadUrl);
    }

    @BeforeClass
    public static void setUp() {
        assertTrue(dbTester.tableExists(INTEGRATION_FILE_TABLE));
        dbTester.deleteEntities(INTEGRATION_FILE_TABLE, "UUID='" + TEST_FILE_UUID + "'");

        List<Map<String, String>> insertMapList = new LinkedList<>();

        Map<String, String> insertMap = new HashMap<>();
        insertMap.put("UUID", "'" + TEST_FILE_UUID + "'");
        insertMap.put("VERSION", "'1'");
        insertMap.put("INTEGRATION_FILE_TYPE_ID", "1");
        insertMap.put("RAW_FILE_CONTENTS", "RAWTOHEX('Test')");
        insertMap.put("FILENAME", "'uploadTestFile.txt'");
        insertMap.put("STEP", "' '");
        insertMap.put("STATUS", "'GENERATED_BY_PSRM'");
        insertMap.put("STATUS_TIME", "TO_TIMESTAMP('2014-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF')");
        insertMap.put("STATUS_BY", "' '");
        insertMap.put("FILE_GENERATED_TIME", "TO_TIMESTAMP('2014-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF')");
        insertMap.put("CREATED_TIME", "TO_TIMESTAMP('2014-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF')");
        insertMap.put("CREATED_BY", "'TEST'");
        insertMapList.add(insertMap);

        dbTester.bulkInsertEntities(INTEGRATION_FILE_TABLE, insertMapList);
    }

    @Test
    public void uploadFile_emptyMessage_expect500code() throws Exception {
        given().log().all()
                .body("{}")
                .when().contentType(APPLICATION_JSON).post(asynchFileUploadUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void uploadFile_malformedRequest_expect500code() throws Exception {
        String malformedRequest = "{ \"serviceQName\": \"testserviceqname\",\n" +
                "  \"beskedQName\": \"testbeskedqname\",\n" +
                "  \"uploadURL\": \"" + ftpUrl + "\",\n" +
                "  \"userName\": \"" + FTP_USER + "\",\n";
        given().log().all()
                .body(malformedRequest)
                .when().contentType(APPLICATION_JSON).post(asynchFileUploadUrl)
                .then().log().all()
                .statusCode(500);
    }

    @Test
    public void uploadFile_whenValid_thenStatusCode200() {
        assertTrue(dbTester.tableExists(INTEGRATION_FILE_TABLE));

        String request = "{ \"serviceQName\": \"testserviceqname\",\n" +
                "\"beskedQName\": \"testbeskedqname\",\n" +
                "\"uploadURL\": \"" + ftpUrl + "\",\n" +
                "\"userName\": \"" + FTP_USER + "\",\n" +
                "\"password\": \"" + FTP_PASSWORD + "\",\n" +
                "\"dataFileUuid\": \"" + TEST_FILE_UUID + "\" }";
        given().log().all()
                .urlEncodingEnabled(true)
                .body(request)
                .when().contentType(APPLICATION_JSON).post(asynchFileUploadUrl)
                .then().log().all()
                .statusCode(200);
    }
}
