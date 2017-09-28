package dk.rim.is.abt.psrm;

import dk.rim.is.abt.util.BatchController;
import org.junit.*;

class C1PFLMTest {
	private static final String JOB_DESCRIPTION = "Create to do when balance is zero - Suppression case";

	private static final String JOB_NAME = "C1PFLM";

	@Test
	@Ignore
	//Ignored: awaiting for logic implementation
	void testBatchReturnCode() {
		def jobReturnCode = BatchController.runPsrmJob(JOB_DESCRIPTION)
		assert jobReturnCode.executionStatus == "SUCCESS";
	}
}