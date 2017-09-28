package peddersen;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.PropertyProviderImpl;
import utils.tools.TO_Tools;

/**
 * @author Jakob Rahr Bork Jensen
 *
 */
public class Sandkasse extends ScreenshotTakingTestContext {

    @AfterMethod
    public void afterMethod() {
        TO_Tools.sleep(1000);
    }

    @BeforeMethod
    public void initialize() {
        setPropertyProvider(new PropertyProviderImpl(this));
    }

    @Test()
    public void test() {
        // your test goes here
    }
}