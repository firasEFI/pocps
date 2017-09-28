package dk.rim.is.ic.inttests.aogd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * Created by maho on 19.06.2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        FormateretMeddelelseIndholdMultiHentTest.class,
        MeddelelseMultiSendEkspresTest.class,
        MeddelelseMultiSendTest.class,
        MeddelelseStatusMultiHentLiveTest.class
})
public class LiveTestSuite {
}
