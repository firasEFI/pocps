package dk.rim.is.abt.integration.healthcheck

import dk.rim.is.abt.util.BatchController
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

import static net.sf.ezmorph.test.ArrayAssertions.assertEquals

/**
 * Created by micw on 16-05-2017.
 */
@RunWith(value = Parameterized.class)
class CheckAllJobsTest {

    private static final String BATCH_TYPE = "INTEGRATION"

    @Parameterized.Parameter
    public String jobDescription

    @Parameterized.Parameters(name = "{index}: job - {0}")
    static Collection jobs() {
        ArrayList<String> jobs = new ArrayList<>()
        BatchController.getBatchList().each {
            if (it.batchType == BATCH_TYPE) {
                jobs.add(it.description)
            }
        }
        jobs
    }

    @Test
    void testJobAssertEquals() {
        def result = BatchController.runIntegrationJob(jobDescription)
        assertEquals("SUCCESS", result.executionStatus)
    }
}
