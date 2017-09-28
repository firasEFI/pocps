package dk.rim.is.ic.inttests.csrp;


import dk.rim.is.ic.inttests.csrp.psmh.PersonStamoplysningerMultiHentAccessTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PersonStamoplysningerMultiHentAccessTest.class
        //TODO it's needed to implement integration test for PersonHaendelseSamlingHentRoute downloadFile and retrieveFileList (no direct integration with csrp but their ftp server instead)
})
public class LiveTestSuite {
}
