package dk.rim.is.abt.psrm

import dk.rim.is.abt.util.BatchController
import org.junit.*;

class C1PEPL1Test {
	private static final String JOB_DESCRIPTION = "Process C1-PEPL1 - Upload Payments";

	private static final String JOB_NAME = "C1PEPL1";

	@Test
	@Ignore
	//Ignored because of awaiting for logic implementation
	void testBatchReturnCode() {
		def jobReturnCode = BatchController.runPsrmJob(JOB_DESCRIPTION)
		assert jobReturnCode.executionStatus == "SUCCESS";
	}
}