package dk.rim.is.ic.inttests.timesimulation;

import dk.rim.is.clock.TimeTravelDateProvider;
import dk.rim.is.ic.timesimulation.TimeSimulationDateResponseMessage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static dk.rim.is.ic.inttests.Property.IC_INTERNAL_HOST;
import static dk.rim.is.ic.inttests.Property.IC_EXTERNAL_HOST;
import static dk.rim.is.ic.inttests.Property.IC_REST_EXTERNAL_PORT;
import static dk.rim.is.ic.inttests.Property.IC_REST_INTERNAL_PORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_EXTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_INTERNAL_PROTOCOL;
import static io.restassured.RestAssured.given;

public class TimeSimulationTests {

    private final static String TEST_DATE_TIME_STRING = "2015/10/21";
    private final static String BAD_FORMAT_DATE_TIME_STRING = "2000/21";
    private final static String IMPOSSIBLE_DATE_TIME_STRING = "2000/13/24";

    private static TimeSimulationEndpoints icInternalEndpoints =
            TimeSimulationEndpoints.createEndpoints(IC_SOAP_INTERNAL_PROTOCOL, IC_INTERNAL_HOST, IC_REST_INTERNAL_PORT);
    private static TimeSimulationEndpoints icExternalEndpoints =
            TimeSimulationEndpoints.createEndpoints(IC_SOAP_EXTERNAL_PROTOCOL, IC_EXTERNAL_HOST, IC_REST_EXTERNAL_PORT);

    private static String icInternalEnvironmentDateString;
    private static String icExternalEnvironmentDateString;

    @BeforeClass
    public static void saveEnvironmentDate() {
        icInternalEnvironmentDateString = testGetDate(icInternalEndpoints);
        icExternalEnvironmentDateString = testGetDate(icExternalEndpoints);
    }

    @AfterClass
    public static void restoreEnvironmentDate() {
        String currentDateString = LocalDate.now().format(TimeTravelDateProvider.FORMATTER);

        if (!icInternalEnvironmentDateString.contains(currentDateString)) {
            testSetDate(icInternalEndpoints, icInternalEnvironmentDateString);
        } else {
            testClearDate(icInternalEndpoints);
        }

        if (!icExternalEnvironmentDateString.contains(currentDateString)) {
            testSetDate(icExternalEndpoints, icExternalEnvironmentDateString);
        } else {
            testClearDate(icExternalEndpoints);
        }
    }

    @Test
    public void testICInternalTimeSimulationFlow() throws Exception {
        testTimeSimulationFlow(icInternalEndpoints);
    }

    @Test
    public void testICInternalTimeSimulation_malformedDate() throws Exception {
        testTimeSimulation_incorrectDate(icInternalEndpoints, BAD_FORMAT_DATE_TIME_STRING);
    }

    @Test
    public void testICInternalTimeSimulation_impossibleDate() throws Exception {
        testTimeSimulation_incorrectDate(icInternalEndpoints, IMPOSSIBLE_DATE_TIME_STRING);
    }

    @Test
    public void testICExternalTimeSimulationFlow() throws Exception {
        testTimeSimulationFlow(icExternalEndpoints);
    }

    @Test
    public void testICExternalTimeSimulation_malformedDate() throws Exception {
        testTimeSimulation_incorrectDate(icExternalEndpoints, BAD_FORMAT_DATE_TIME_STRING);
    }

    @Test
    public void testICExternalTimeSimulation_impossibleDate() throws Exception {
        testTimeSimulation_incorrectDate(icExternalEndpoints, IMPOSSIBLE_DATE_TIME_STRING);
    }

    private static void testTimeSimulationFlow(TimeSimulationEndpoints endpoints) {
        String currentDateString = LocalDate.now().format(TimeTravelDateProvider.FORMATTER);
        String environmentDateString = testGetDate(endpoints);

        // send set date request and verify it has been set
        testSetDate(endpoints, TEST_DATE_TIME_STRING);
        testCheckDate(endpoints, TEST_DATE_TIME_STRING);

        // clear date
        testClearDate(endpoints);
        testCheckDate(endpoints, currentDateString);

        // if custom date was set before the test, set it again
        if (!environmentDateString.contains(currentDateString)) {
            testSetDate(endpoints, environmentDateString);
        }
    }

    private static void testTimeSimulation_incorrectDate(TimeSimulationEndpoints endpoints, String testDateString) {
        String environmentDateString = testGetDate(endpoints);

        // send set date request with incorrect date and verify date didn't change
        testSetIncorrectDate(endpoints, testDateString);
        testCheckDate(endpoints, environmentDateString);
    }

    private static void testSetDate(TimeSimulationEndpoints endpoints, String dateString) {
        String setDateUrl = endpoints.getSetDateUrl() + "?date=" + dateString;
        given().log().all()
                .when().post(setDateUrl)
                .then().log().all()
                .statusCode(200);
    }

    private static void testSetIncorrectDate(TimeSimulationEndpoints endpoints, String dateString) {
        String setDateUrl = endpoints.getSetDateUrl() + "?date=" + dateString;
        given().log().all()
                .when().post(setDateUrl)
                .then().log().all()
                .statusCode(500);
    }

    private static void testCheckDate(TimeSimulationEndpoints endpoints, String expectedDateString) {
        String dateString = testGetDate(endpoints);
        Assert.assertTrue(dateString.contains(expectedDateString));
    }

    private static String testGetDate(TimeSimulationEndpoints endpoints) {
        TimeSimulationDateResponseMessage responseMessage =
                given().log().all()
                .when().get(endpoints.getGetDateUrl())
                .then().log().all()
                .statusCode(200)
                .extract().response().as(TimeSimulationDateResponseMessage.class);

        LocalDateTime localDateTime = LocalDateTime.parse(responseMessage.getDate());

        return localDateTime.format(TimeTravelDateProvider.FORMATTER);
    }

    private static void testClearDate(TimeSimulationEndpoints endpoints) {
        given().log().all()
                .when().put(endpoints.getClearDateUrl())
                .then().log().all()
                .statusCode(200);
    }
}
