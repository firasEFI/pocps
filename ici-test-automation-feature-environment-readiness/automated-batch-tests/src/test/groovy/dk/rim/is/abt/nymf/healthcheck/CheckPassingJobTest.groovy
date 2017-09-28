package dk.rim.is.abt.nymf.healthcheck

import dk.rim.is.abt.util.BatchController
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

import static net.sf.ezmorph.test.ArrayAssertions.assertEquals

@RunWith(value = Parameterized.class)
public class CheckPassingJobTest {
    private static final String BATCH_TYPE = "INTEGRATION"

    @Parameterized.Parameter
    public String jobDescription

    @Parameterized.Parameters(name = "{index}: job - {0}")
    static Collection jobs() {
        ["SendToPSRM", "SendToExMF", "Routing", "SkylderValidation"]
    }

    @Test
    void testJobAssertEquals() {
        def result = BatchController.runNyMFJob(jobDescription)
        assertEquals("SUCCESS", result.executionStatus)
    }
}
