package dk.rim.is.abt.psrm;

import dk.rim.is.abt.util.BatchController
import org.junit.Ignore;
import org.junit.Test;

class C1TXFRMTest {
	private static final String JOB_DESCRIPTION = "Tax Form Deferred Monitor";

	private static final String JOB_NAME = "C1TXFRM";

	@Test
	@Ignore
	//Ignored: awaiting for logic implementation
	void testBatchReturnCode() {
		def jobReturnCode = BatchController.runPsrmJob(JOB_DESCRIPTION)
		assert jobReturnCode.executionStatus == "SUCCESS";
	}
}