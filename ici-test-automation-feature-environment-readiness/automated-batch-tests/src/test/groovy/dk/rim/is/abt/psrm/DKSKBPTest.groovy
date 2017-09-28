package dk.rim.is.abt.psrm;

import dk.rim.is.abt.util.BatchController
import org.junit.Ignore;
import org.junit.Test;

class DKSKBPTest {
	private static final String JOB_DESCRIPTION = "SKBPayments";

	private static final String JOB_NAME = "DKSKBP";

	@Test
	@Ignore
	//Ignored: awaiting for logic implementation
	void testBatchReturnCode() {
		def jobReturnCode = BatchController.runPsrmJob(JOB_DESCRIPTION)
		assert jobReturnCode.executionStatus == "SUCCESS";
	}
}