package dk.rim.is.ic.inttests.captia;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DokumentHentTest.class,
        DokumentMultiOpretTest.class,
        DokumentMultiOpretRestTest.class,
        DokumentOpdaterTest.class,
        DPDokumentOpretTest.class,
        SagOpdaterTest.class,
        SagOpretTest.class
})
public class MockTestSuite {
}
