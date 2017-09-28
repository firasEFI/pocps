package dk.rim.is.abt.psrm;

import dk.rim.is.abt.util.BatchController
import org.junit.*;

class C1PEPL3Test {
	private static final String JOB_DESCRIPTION = "Process C1-PEPL3 - Upload Payments";

	private static final String JOB_NAME = "C1PEPL3";

	@Test
	@Ignore
	//Ignored because of awaiting for logic implementation
	void testBatchReturnCode() {
		def jobReturnCode = BatchController.runPsrmJob(JOB_DESCRIPTION)
		assert jobReturnCode.executionStatus == "SUCCESS";
	}
}