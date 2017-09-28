package peddersen.testconfig;

import org.testng.annotations.BeforeTest;

import icisel.testng.TestContext;
import utils.PropertyProviderImpl;

public class PeddersenTestContext extends TestContext {

    @BeforeTest
    public void initialize() {
        setPropertyProvider(new PropertyProviderImpl(this));

        doMaximizeWindow();
    }

}
