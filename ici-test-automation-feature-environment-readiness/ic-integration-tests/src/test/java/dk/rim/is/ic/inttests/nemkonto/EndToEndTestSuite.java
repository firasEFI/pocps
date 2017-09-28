package dk.rim.is.ic.inttests.nemkonto;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        NemKontoScenarioTest.class,
        NemKontoE2EBatchTest.class
})
public class EndToEndTestSuite {
}
