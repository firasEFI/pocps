package peddersen;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import icisel.testng.TestContext;
import icisel.utils.driver.Engine;
import utils.PropertyProviderImpl;

public class ScreenshotTakingTestContext extends TestContext {
        
    @BeforeClass(alwaysRun = true)
    public void setPropertyProvider() {
        printInitiated();
        this.setPropertyProvider(new PropertyProviderImpl(this));
    }

    @AfterMethod(alwaysRun = true)
    public void failureHandler(ITestResult result) {
        printDone(result);

        doScreenshotIfIsFailure(result);
        if (result.isSuccess()) {
            Engine.closeDriver();
        }
    }

    private void printDone(ITestResult result) {
        System.out.printf("Valideringstestcase %s er %s.%n", getName(), status(result));

    }

    /**
     * @see ITestResult#getStatus()
     * @param result
     *            teststatus
     * @return teststatus i danske ord
     */
    private String status(ITestResult result) {

        switch (result.getStatus()) {
        case -1:
            return "oprettet";
        case 1:
            return "bestået";
        case 2:
            return "fejlet";
        case 3:
            return "skipped";
        case 16:
            return "started";
        default:
            return "ukendt";
        }
    }

    private void printInitiated() {
        System.out.printf("Påbegynder valideringstestcase %s.%n", getName());
    }

    private String getName() {
        return this.getClass().getSimpleName();
    }
}
