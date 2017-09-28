package dk.rim.is.abt.psrm;

import dk.rim.is.abt.util.BatchController
import org.junit.Ignore;
import org.junit.Test;

class DKDROCTDTest {
	private static final String JOB_DESCRIPTION = "ObligationsReachingLimitionDate";

	private static final String JOB_NAME = "DKDROCTD";

	@Test
	@Ignore
	//Ignored: awaiting for logic implementation
	void testBatchReturnCode() {
		def jobReturnCode = BatchController.runPsrmJob(JOB_DESCRIPTION)
		assert jobReturnCode.executionStatus == "SUCCESS";
	}
}