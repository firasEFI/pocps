package peddersen.examples;

import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.testng.TestContext;
import icisel.utils.driver.Engine;
import modules.MO_Utilities;
import utils.PropertyProviderImpl;

public class _360FromBatchTest extends TestContext {
    @BeforeMethod
    public void initialize() {
        setPropertyProvider(new PropertyProviderImpl(this));
        // OPSÆTNING
        Engine.getDriver().setTimeout(15000);
        doMaximizeWindow();
    }

    @Test(groups = { "Testcase" }, invocationCount = 100)
    public void main() throws Exception {
        doLogin();

        // Hent PSRM datoen
        String psrmDato = MO_Utilities.getPsrmDateTime(this)[0];
        Assert.assertNotNull(psrmDato);

        // Her er blevet observeret fejl

        MenuNavigator.menu().a360GradersSoegning();
    }

    @AfterMethod
    public void screenshot(ITestResult result) {
        doScreenshotIfIsFailure(result);
    }
}
