package dk.rim.is.ic.inttests.aogd.includebatch;

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

import static dk.rim.is.ic.inttests.DatabaseTesterFactory.createDbTester;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Integration test for check letter status integration batch.
 */
public class CheckLetterStatusTest {

    private static final Logger LOG = LoggerFactory.getLogger(MeddelelseMultiSendTest.class);

    private static String checkLetterStatusUrl;
    private static DatabaseTester dbTester = createDbTester();

    private static void loadProperties() {
        String host = Property.BATCH_REST_HOST.load();
        String restPort = Property.BATCH_REST_PORT.load();

        checkLetterStatusUrl = toUrl("http", host, restPort, "/integration-batch/rest/checkLetterStatus/startJobs");

        LOG.info("Using checkLetterStatusUrl: " + checkLetterStatusUrl);
    }

    @BeforeClass
    public static void setup() {
        loadProperties();

        assertTrue(dbTester.tableExists("BREV"));
        dbTester.deleteEntities("BREV", "BREV_ID = 935342");

        List<Map<String, String>> insertMapList = new LinkedList<>();

        Map<String, String> insertMap = new HashMap<>();
        insertMap.put("STATUS_SEND", "'ToSend'");
        insertMap.put("STATUS_PRODUKTION", "'OPRETTET'");
        insertMap.put("VERSION", "1");
        insertMap.put("BREV_ID", "935342");
        insertMap.put("BATCH_ID", "935342");
        insertMap.put("DATO_OPRETTELSE", "TO_TIMESTAMP('2014-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF')");
        insertMapList.add(insertMap);

        dbTester.bulkInsertEntities("BREV", insertMapList);
    }

    @Test
    public void checkLetterStatus_startProcessing_verifyStatusesInDb() throws Exception {
        assertBrevProductionStatus("935342", "OPRETTET");

        given().log().all()
                .when().post(checkLetterStatusUrl)
                .then().statusCode(200);

        Thread.sleep(5000); //Wait for asynchronous job finish

        assertBrevProductionStatus("935342", "Produktion fejl");
    }

    private void assertBrevProductionStatus(String brevId, String expectedStatus) {
        List<Map<String, Object>> brevs = dbTester.select("BREV", "STATUS_PRODUKTION", "BREV_ID = " + brevId);
        assertThat(brevs.size(), is(1));
        Map<String, Object> brev = brevs.iterator().next();
        String productionStatus = (String) brev.get("STATUS_PRODUKTION");
        assertThat(productionStatus, is(expectedStatus));
    }
}