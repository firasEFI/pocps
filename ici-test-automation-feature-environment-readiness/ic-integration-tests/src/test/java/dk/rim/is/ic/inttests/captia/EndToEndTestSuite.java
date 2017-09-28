package dk.rim.is.ic.inttests.captia;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CaptiaCaseScenarioTest.class,
        CaptiaDocumentScenarioTest.class
})
public class EndToEndTestSuite {
}
