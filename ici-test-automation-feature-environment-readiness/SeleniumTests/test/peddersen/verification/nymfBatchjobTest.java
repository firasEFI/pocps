package peddersen.verification;

import org.testng.annotations.Test;

import modules.MO_360GradersSoegning;
import peddersen.ScreenshotTakingTestContext;
import utils.batchJob.BatchJobRunner;
import utils.betaling.BetalingsfilType;

public class nymfBatchjobTest extends ScreenshotTakingTestContext {

    @Test
    public void test() {
        
        doMaximizeWindow();
        doLogin();
        
        BatchJobRunner batch = new BatchJobRunner(this.getPropertyProvider());
        batch.runNymfBatchJob();
        
        MO_360GradersSoegning.soegKundeViaCPR(this, "0803734279");
        
        doLogout();
        
        
    }
    
}