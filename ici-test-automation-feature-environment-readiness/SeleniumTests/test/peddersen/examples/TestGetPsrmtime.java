package peddersen.examples;

import java.text.ParseException;

import org.testng.annotations.Test;

import icisel.testng.TestContext;
import icisel.utils.driver.Engine;
import modules.MO_Utilities;
import utils.PropertyProviderImpl;

public class TestGetPsrmtime extends TestContext {

    @Test
    public void testPsrmTime() throws ParseException {
        setPropertyProvider(new PropertyProviderImpl(this));
        doMaximizeWindow();
        doLogin();

        System.out.println(MO_Utilities.getPsrmDateTime(this));
        doScreenshot();

        // TODO: test dato atuomatisk

        doLogout();
        Engine.closeDriver();
    }

}
