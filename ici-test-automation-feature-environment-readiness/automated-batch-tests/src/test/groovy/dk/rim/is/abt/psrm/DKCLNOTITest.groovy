package dk.rim.is.abt.psrm;

import dk.rim.is.abt.util.BatchController
import org.junit.Ignore;
import org.junit.Test;

class DKCLNOTITest {
	private static final String JOB_DESCRIPTION = "Generate claimants notifications";

	private static final String JOB_NAME = "DKCLNOTI";

	@Test
	@Ignore
	//Ignored: awaiting for logic implementation
	void testBatchReturnCode() {
		def jobReturnCode = BatchController.runPsrmJob(JOB_DESCRIPTION)
		assert jobReturnCode.executionStatus == "SUCCESS";
	}
}