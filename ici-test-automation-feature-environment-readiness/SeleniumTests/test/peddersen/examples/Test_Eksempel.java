package peddersen.examples;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.pageobjects.PO_Navigation;
import icisel.testng.SmartAssert;
import icisel.testng.TestContext;
import icisel.utils.TO_Tools;
import icisel.utils.driver.patient.Patient;
import pageobjects.generic.PO_Slaa_Kunde_Op;

/**
 * @author Jakob Rahr Bork Jensen
 *
 */
public class Test_Eksempel extends TestContext {

    @AfterMethod
    public void afterMethod() {
        TO_Tools.sleep(1000);
    }

    @Test(groups = { "OpretFordring" }, invocationCount = 5)
    public void main() throws Exception {
        String sKundeNummer = "0803734279";
        String sIdType = "CPR";

        // MENUs
        MenuNavigator.menu().a360GradersSoegning();

        waitForPageToBeLoaded();

        // Indtast kundens CPR-nummer i feltet ID nummer
        PO_Slaa_Kunde_Op.input_IdNummer.sendKeys(sKundeNummer);

        // Vælg CPR som ID type
        PO_Slaa_Kunde_Op.drp_IdType.pick(sIdType);

        // Tryk på søg
        PO_Slaa_Kunde_Op.btn_Soeg.waitFor();
        PO_Slaa_Kunde_Op.btn_Soeg.click();

        // Vent på at søgningen afsluttes ved at vente på det element der ønskes
        // benyttet næste gang.
        PO_Slaa_Kunde_Op.lnk_Kunde.waitFor();

        // Tryk på linket til kunden
        PO_Slaa_Kunde_Op.lnk_Kunde.click();

        // Vent på at kunden loades
        Patient.ventTilHoejreMenuErLoadet(getDriver());

        // Lav boolean test af CPR-nummer
        Boolean test_CPR = PO_Navigation.txt_KundeOverblikPartsoplysningerCPR.getText().equalsIgnoreCase("080373-4279");

        // Valider at CPR-nummeret stemmer
        SmartAssert.assertTrue(this, test_CPR,
                "Den fremsøgte kundes CPR-nummer matcher ikke det oprindelige CPR-nummer");

    }

    public String brugerArk() {
        return "Jakob";
    }
}
