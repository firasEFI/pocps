package dk.rim.is.ic.inttests.dcs;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//TODO this is just a copy of IntegrationTestSuite in order to execute by jenkins System Integration Tests
//TODO it needs to adjust later
@RunWith(Suite.class)
@Suite.SuiteClasses({
        GetEntityInformationRouteTest.class,
        SingleSignOnSessionHentTest.class,
        TopNiveauProcesRolleGruppeSamlingHentTest.class
})
public class MockTestSuite {
}
