package peddersen.examples;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import icisel.testng.TestContext;
import icisel.utils.driver.Engine;
import modules.MO_OpretOpgave;
import modules.MO_Utilities;
import utils.PropertyProviderImpl;
import utils.tools.TO_Tools;

/**
 * @author Jakob Rahr Bork Jensen
 *
 */
public class Sandkasse extends TestContext {

    @AfterMethod
    public void afterMethod() {
        TO_Tools.sleep(1000);
    }

    @BeforeMethod
    public void initialize() {
        setPropertyProvider(new PropertyProviderImpl(this));
    }

    @Test(groups = { "OpretFordring" }, invocationCount = 1)
    public void main() throws Exception {
        Engine.getDriver().setTimeout(15000);

        String sKundeNummer = "0505601000";

        doMaximizeWindow();

        doLogin();
        
        String psrmDato = MO_Utilities.getPsrmDateTime(this)[0];

        //opret opgave manuelt
        MO_OpretOpgave.opretOpgaveManuelt_OverskrivFelter(this, "Udvælge Skyldnere", "Send påkrav", "21", "0100000007", "Rolle", "DK-CASWGEN", "Prioritet 60", "Ny beskrivelse", 5, psrmDato);
     

    }

    public String brugerArk() {
        return "Jakob";
    }
}
