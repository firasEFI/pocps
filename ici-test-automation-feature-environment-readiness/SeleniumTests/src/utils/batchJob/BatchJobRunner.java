package utils.batchJob;

import static utils.batchJob.BatchJobType.C1_PEPL1;
import static utils.batchJob.BatchJobType.C1_PEPL2;
import static utils.batchJob.BatchJobType.C1_TXFRM;
import static utils.batchJob.BatchJobType.DKCLRSP;
import static utils.batchJob.BatchJobType.DKNP;
import static utils.batchJob.BatchJobType.DKSKBO;
import static utils.batchJob.BatchJobType.DKSKBP;
import static utils.batchJob.BatchJobType.DKSKBT;
import static utils.batchJob.BatchJobType.Retskraftsvurdering;
import static utils.batchJob.BatchJobType.Routing;
import static utils.batchJob.BatchJobType.SendToPSRM;
import static utils.batchJob.BatchJobType.SkyldnerValidation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.frames.Frames;
import icisel.testng.PropertyProvider;
import icisel.testng.TestContext;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;
import utils.PropertyProviderImpl;
import utils.betaling.BetalingsfilOvertype;
import utils.tools.TO_Tools;

public class BatchJobRunner {
    PropertyProvider propertyProvider;

    public BatchJobRunner(PropertyProvider propertyProvider) {
        super();
        this.propertyProvider = propertyProvider;
    }

    public static BatchJobRunner CreateWithDefaultPropertyProvider() {
        return new BatchJobRunner(new PropertyProviderImpl());
    }

    private int delayBetweenRefreshesMilliseconds = 1000;

    private int timeoutPerBatchJobMilliseconds = 5 * 60 * 1000;

    private final Button searchBatchjob = new Button(Frames.defaultContent, By.xpath("//button[@type='submit']"));

    TestContext testContext;

    /**
     * Runs a series of batch jobs in a new window, then closes this window and
     * switches back to the original window, if any.
     * 
     * @param jobs
     *            choose batchjob type e.g. DKSKBP. If multiple arguments are
     *            specified, the jobs will be run in series.
     */
    public void run(BatchJobType... jobs) {
        PatientWebDriver originalBrowser = null;
        if (Engine.hasDriver()) {
            originalBrowser = Engine.getDriver();
        }

        Engine.setDriver(Engine.newDriver());

        // batchJobs overview page
        String batchJobOverviewUrl = getBawUrl() + "/batchJobs";

        for (BatchJobType job : jobs) {
            Engine.getDriver().get(batchJobOverviewUrl);

            String xpath = String.format("//tr[td[text()='%s'] and td[text()='%s']]//*[text()='Execute']",
                    job.getName(),
                    job.getDescription());
            Button executeBatchjob = new Button(Frames.defaultContent, By.xpath(xpath));

            executeBatchjob.click();
            searchBatchjob.click();
            verifyBatchStatus(timeoutPerBatchJobMilliseconds, job);
        }

        Engine.closeDriver();

        if (originalBrowser != null) {
            Engine.setDriver(originalBrowser);
        }

    }

    private void verifyBatchStatus(long timeoutInMillIseconds, BatchJobType job) {
        verifyBatchStatus(timeoutInMillIseconds, job, delayBetweenRefreshesMilliseconds);
    }

    private void verifyBatchStatus(long timeoutInMilliseconds, BatchJobType job,
            int delayBeforeRefreshingMilliseconds) {
        PatientWebDriver driver = Engine.getDriver();
        long t0 = System.currentTimeMillis();

        boolean isDone = false;

        String jobName = job.name();

        while (!isDone && System.currentTimeMillis() - t0 < timeoutInMilliseconds) {
            // level1-array
            List<ArrayList<String>> jobStatuses = new ArrayList<>();

            // level2-arrays
            ArrayList<String> statuses = new ArrayList<String>();
            ArrayList<String> jobs = new ArrayList<String>();
            // add to level2-arrays
            for (WebElement statusField : getStatusesAsWebElements(driver)) {
                statuses.add(statusField.getText());
            }
            // add to level2-arrays
            for (WebElement jobField : getJobsAsWebElements(driver)) {
                jobs.add(jobField.getText());
            }
            // add to level1-array
            jobStatuses.add(jobs);
            jobStatuses.add(statuses);


            RuntimeException e = new RuntimeException("Batch job (" + jobName + ") failed");

            if (isAnyJobs("FAILURE", job, jobStatuses)) {
                throw e;
            } else if (isAnyJobs("STARTING", job, jobStatuses)) {
                TO_Tools.sleep(delayBeforeRefreshingMilliseconds);
                driver.get(driver.getCurrentUrl()); // refresh
            } else if (isAnyJobs("STARTED", job, jobStatuses)) {
                TO_Tools.sleep(delayBeforeRefreshingMilliseconds);
                driver.get(driver.getCurrentUrl()); // refresh
            } else if (areAllSuccess(job, jobStatuses)) {
                // System.out.println("All batch jobs were successful");
                System.out.println("Batch job (" + jobName + ") was successful");
                isDone = true;
            } else {
                System.err.println("Something unexpected occured (Batchjob: " + jobName + "). Status was:");
                for (String status : statuses) {
                    System.err.println(status);
                }
                throw e;
            }
        }
    }
    
    private static boolean areAllJobs(String expected, BatchJobType jobType, List<ArrayList<String>> jobs) {
        for (int i = 0; i < jobs.get(0).size(); i++) {
            String jobName = jobs.get(0).get(i);
            String statusText = jobs.get(1).get(i);
            if (!expected.equals(statusText) && jobName.equals(jobType.getName())) {
                return false;
            }
         }
        
        return true;
    }

    private static boolean areAllSuccess(BatchJobType jobType, List<ArrayList<String>> jobs) {
        for (int i = 0; i < jobs.get(0).size(); i++) {
            String jobName = jobs.get(0).get(i);
            String statusText = jobs.get(1).get(i);
            if (!statusText.contains("SUCCESS") && jobName.equals(jobType.getName())) {
                return false;
            }
         }
        
        return true;
    }
        
      

    private static boolean isAnyJobs(String testString, BatchJobType jobType, List<ArrayList<String>> jobs) {
        for (int i = 0; i < jobs.get(0).size(); i++) {
            String jobName = jobs.get(0).get(i);
            String statusText = jobs.get(1).get(i);
            if (testString.equals(statusText) && jobName.equals(jobType.getName())) {
                return true;
            }

        }
        return false;
    }

    private String getBawUrl() {
        String psrmUrl = propertyProvider.getUrl();

        String usernamePassword = "baw_user:netcompany-123@";
        String bawUrlEnd = "/batch-administration-website";

        int divider = 8;
        String bawUrl = TO_Tools.parseDomain(psrmUrl).substring(0, divider) + usernamePassword
                + TO_Tools.parseDomain(psrmUrl).substring(divider) + bawUrlEnd;
        return bawUrl;
    }

    /**
     * Runs all batch jobs associated with Ind- and Udbetaling
     */
    public void runBetalingsBatchJob(BetalingsfilOvertype type) {
        switch (type) {
        case CREMUL:
            run(DKSKBP, C1_PEPL1, C1_PEPL2);
            break;
        case M602:
            run(DKNP, C1_PEPL1, C1_PEPL2);
            break;
        case FINSTA:
            run(BatchJobType.DKSKBT);
            break;
        default:
            System.out.println("Case ikke implementeret");
            break;
        }
    }

    /**
     * Runs all batch jobs associated with Retursvar
     */
    public void runRetursvarBatchJob() {
        run(DKCLRSP, DKSKBO, DKSKBT);
    }

    /**
     * Runs all batch jobs associated with NyMF
     */
    public void runNymfBatchJob() {
        run(SkyldnerValidation, Retskraftsvurdering, Routing, SendToPSRM, C1_TXFRM);
    }

    public int getDelayBetweenRefreshesMilliseconds() {
        return delayBetweenRefreshesMilliseconds;
    }

    public void setDelayBetweenRefreshesMilliseconds(int delay) {
        this.delayBetweenRefreshesMilliseconds = delay;
    }

    public int getTimeoutPerBatchJobMilliseconds() {
        return timeoutPerBatchJobMilliseconds;
    }

    public void setTimeoutPerBatchJobMilliseconds(int timeout) {
        this.timeoutPerBatchJobMilliseconds = timeout;
    }

    private static List<WebElement> getStatusesAsWebElements(PatientWebDriver driver) {
        return driver.findElements(By.xpath("//table[@class='table table-bordered']/tbody/tr/td[6]"));
    }

    private static List<WebElement> getJobsAsWebElements(PatientWebDriver driver) {
        return driver.findElements(By.xpath("//table[@class='table table-bordered']/tbody/tr/td[2]"));
    }
}
