package dk.rim.is.ic.inttests.aogd;

import dk.rim.is.ic.inttests.aogd.includebatch.CheckLetterStatusTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CheckLetterStatusTest.class,
        MeddelelseMultiSendTest.class
})
public class EndToEndTestSuite {
}
