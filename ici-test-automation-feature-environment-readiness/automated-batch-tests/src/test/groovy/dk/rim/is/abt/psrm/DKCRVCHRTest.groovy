package dk.rim.is.abt.psrm;

import dk.rim.is.abt.util.BatchController
import org.junit.Ignore;
import org.junit.Test;

class DKCRVCHRTest {
	private static final String JOB_DESCRIPTION = "DKCRVCHR - Create Vouchers";

	private static final String JOB_NAME = "DKCRVCHR";

	@Test
	@Ignore
	//Ignored: awaiting for logic implementation
	void testBatchReturnCode() {
		def jobReturnCode = BatchController.runPsrmJob(JOB_DESCRIPTION)
		assert jobReturnCode.executionStatus == "SUCCESS";
	}
}