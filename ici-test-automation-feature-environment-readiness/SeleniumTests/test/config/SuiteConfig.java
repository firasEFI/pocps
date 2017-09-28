package config;

import org.testng.annotations.AfterSuite;

import icisel.utils.driver.Engine;
import utils.PropertyProviderImpl;

public class SuiteConfig {
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        boolean doQuit = new PropertyProviderImpl().getQuitAllDriversAfterSuite();
        if (doQuit) Engine.stop();
    }
}
