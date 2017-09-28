package dk.rim.is.ic.inttests.sap38;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by asol on 27-07-2017.
 */

//TODO this is just a copy of IntegrationTestSuite in order to execute by jenkins System Integration Tests
//TODO it needs to adjust later
@RunWith(Suite.class)
@Suite.SuiteClasses({
        FinansKontoBilagOpretRouteTest.class
})

public class MockTestSuite {
}
