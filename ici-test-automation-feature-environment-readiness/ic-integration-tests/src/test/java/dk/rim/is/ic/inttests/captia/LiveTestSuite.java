package dk.rim.is.ic.inttests.captia;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * Created by maho on 01.06.2017.
 */
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
public class LiveTestSuite {
}
