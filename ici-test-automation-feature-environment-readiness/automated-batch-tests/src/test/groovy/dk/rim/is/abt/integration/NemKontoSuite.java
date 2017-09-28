package dk.rim.is.abt.integration;

import dk.rim.is.abt.integration.nemKonto.NemKontoExportPaymentsTest;
import dk.rim.is.abt.integration.nemKonto.NemKontoReceiveReceiptsTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by micw on 14-06-2017.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        NemKontoExportPaymentsTest.class,
        NemKontoReceiveReceiptsTest.class
})

public class NemKontoSuite {
}
