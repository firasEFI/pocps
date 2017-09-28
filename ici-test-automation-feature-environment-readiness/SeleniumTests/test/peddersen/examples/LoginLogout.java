package peddersen.examples;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import icisel.testng.TestContext;
import icisel.utils.driver.Engine;
import utils.PropertyProviderImpl;

public class LoginLogout extends TestContext {

    @BeforeClass
    private void beforeClass() {
        // set the property provider for the context
        this.setPropertyProvider(new PropertyProviderImpl(this));
    }

    @Test(groups = { "Dummy" })
    public void main() {
        // Login to PSRM
        doLogin();

        // Maximize window
        doMaximizeWindow();

        doLogout();
    }
    
    
    @AfterMethod(alwaysRun = true)
    public void closeDriver(ITestResult result) {
        doScreenshotIfIsFailure(result);
        Engine.closeDriver();
    }
    
    @AfterClass(alwaysRun = true)
    public void closeEngine() {
        Engine.stop();
    }
}
