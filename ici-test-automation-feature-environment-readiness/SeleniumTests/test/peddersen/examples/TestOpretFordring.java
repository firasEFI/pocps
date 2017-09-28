package peddersen.examples;

import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import findus_datamodelWarehouses.SkyldnerWarehouse;
import icisel.taxobjects.Fordring;
import icisel.taxobjects.Fordringstype;
import icisel.testng.TestContext;
import icisel.utils.driver.Engine;
import modules.MO_Fordring;
import utils.PropertyProviderImpl;

public class TestOpretFordring extends TestContext {

    @BeforeTest
    public void initializeSettings() {
        setPropertyProvider(new PropertyProviderImpl(this));
    }

    @Test(invocationCount = 2)
    public void testOpretFordring() {
        String sKundeNummer = SkyldnerWarehouse.skyldner_Val3780().getCprNummer();
        String sBeloeb = "10000";

        doMaximizeWindow();
        doLogin();
        // fordring har tilfaeldigt FordringsID
        Fordring fordring = new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, sKundeNummer, sBeloeb);
        fordring.setDageTilModtagelsesDato(1000);

        try {
            MO_Fordring.opretFordring(this, fordring);
        } catch (WebDriverException e) {
            // forlad det synkende skib!
            getDriver().get("about:blank");
            doLogin();
            throw e;
        }

    }

    @AfterMethod
    public void failureHandler(ITestResult result) {
        doScreenshotIfIsFailure(result);
    }

    @AfterTest
    public void close() {
        Engine.closeDriver();
    }

}
