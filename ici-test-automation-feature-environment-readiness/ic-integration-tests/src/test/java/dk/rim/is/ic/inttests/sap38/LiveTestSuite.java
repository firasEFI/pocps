package dk.rim.is.ic.inttests.sap38;

/**
 * Created by asol on 27-07-2017.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//TODO this is just a copy of IntegrationTestSuite in order to execute by jenkins System Integration Tests
//TODO it needs to adjust later
@RunWith(Suite.class)
@Suite.SuiteClasses({
    FinansKontoBilagOpretRouteTest.class
})
public class LiveTestSuite {
}
