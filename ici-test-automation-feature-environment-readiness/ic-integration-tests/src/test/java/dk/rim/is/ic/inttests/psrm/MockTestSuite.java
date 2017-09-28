package dk.rim.is.ic.inttests.psrm;

import dk.rim.is.ic.inttests.psrm.documentinfo.DocumentInformationTest;
import dk.rim.is.ic.inttests.psrm.documentupdate.DocumentUpdateTest;
import dk.rim.is.ic.inttests.psrm.notifyinfo.NotifyInfoTest;
import dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimNegativeTest;
import dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimWithStatusCheckTest;
import dk.rim.is.ic.inttests.psrm.receiveclaim.ReceiveClaimWithoutStatusCheckTest;
import dk.rim.is.ic.inttests.psrm.receivehoering.ReceiveHoeringTest;
import dk.rim.is.ic.inttests.psrm.retrieveall.RetrieveAllCprTest;
import dk.rim.is.ic.inttests.psrm.statusupdate.StatusUpdateTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//TODO this is just a copy of IntegrationTestSuite in order to execute by jenkins System Integration Tests
//TODO it needs to adjust later
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DocumentInformationTest.class,
        DocumentUpdateTest.class,
        RetrieveAllCprTest.class,
        StatusUpdateTest.class,
        ReceiveClaimWithStatusCheckTest.class,
        ReceiveClaimWithoutStatusCheckTest.class,
        ReceiveClaimNegativeTest.class,
        ReceiveHoeringTest.class,
        NotifyInfoTest.class
})
public class MockTestSuite {
}
