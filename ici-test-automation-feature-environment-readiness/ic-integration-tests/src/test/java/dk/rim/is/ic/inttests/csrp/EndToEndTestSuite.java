package dk.rim.is.ic.inttests.csrp;

import dk.rim.is.ic.inttests.csrp.phsh.DownloadFileTest;
import dk.rim.is.ic.inttests.csrp.phsh.RetrieveFileListTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DownloadFileTest.class, // This cannot be tested within integration tests because we need info on what to download
        RetrieveFileListTest.class, // This cannot be tested within integration tests because we need info on what to delete
})
public class EndToEndTestSuite {
}
