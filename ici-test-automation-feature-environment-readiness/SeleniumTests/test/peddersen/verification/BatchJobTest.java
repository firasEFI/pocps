package peddersen.verification;

import org.testng.annotations.Test;

import peddersen.ScreenshotTakingTestContext;
import utils.batchJob.BatchJobRunner;
import utils.batchJob.BatchJobType;

public class BatchJobTest extends ScreenshotTakingTestContext {
    @Test
    public void main() {
        new BatchJobRunner(getPropertyProvider()).run(BatchJobType.C1_PEPL1);
    }
}
