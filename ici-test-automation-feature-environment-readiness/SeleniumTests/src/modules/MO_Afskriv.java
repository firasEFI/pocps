package modules;

import org.openqa.selenium.WebDriverException;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.taxobjects.Fordring;
import icisel.testng.TestContext;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_Afskriv;
import pageobjects.generic.PO_Navigation;
import pageobjects.generic.PO_SoegOpgave;

public class MO_Afskriv {
    /**
     * Kræver efterfølgende godkendelse vha.
     * {@link MO_Afskriv#godkendAfskrivning()}.
     * 
     * @return ID på Afskrivningssagen
     */
    public static String opretAfskrivningOgSendTilGodkendelse(TestContext testContext,
            Afskrivningsmulighed afskrivningsmulighed, int dividendeProcent, Afskrivningsaarsag afskrivningsaarsag,
            Fordring fordring) {

        PatientWebDriver driver = testContext.getDriver();

        testContext.doScreenshot();

        // forudsætter at at vi er på 360 graders overblik for
        // Gaa til Opret afskrivning i konto kontekst menuen

        gaaTilOpretAfskrivning(testContext);


        testContext.doScreenshot();

        vaelgAfskrivningsmulighed(afskrivningsmulighed);

        testContext.doScreenshot();
        // Hvis afskrivningsmulighed er procent, skal der indtastes dividende
        // procent

        switch (afskrivningsmulighed) {
        case PROCENT:
            udfyldDividendeProcent(dividendeProcent);
            break;
        default:
            // Spring over, hvis afskrivningsmuligheden ikke er Procent
            break;
        }

        testContext.doScreenshot();

        // Virkningsdato bliver autoudfyldt (11-08-2017)

        vaelgAfskrivningsaarsag(afskrivningsaarsag);

        testContext.doScreenshot();

        // TO_Tools.sleep(1000);

        vaelgFordringTilAfskrivning(fordring);

        testContext.doScreenshot();

        // Tryk paa Opret
        trykOpret(testContext);

        // Tryk paa Send til godkendelse
        sendTilGodkendelse();

        testContext.doScreenshot();

        PO_Afskriv.btn_Godkend.waitFor();

        String sagsId = PO_Afskriv.txt_SagsId.getText();
        testContext.doScreenshot();
        return sagsId;
    }

    public static void trykOpret(TestContext testContext) {
        testContext.waitForPageToBeLoaded();
        PO_Afskriv.btn_Opret.clickAttemptUsingJS();
    }

    public static void sendTilGodkendelse() {
        try {
            PO_Afskriv.btn_SendTilGodkendelse.click();
        } catch (WebDriverException e) {
            // sometimes the page does not load correctly
            PO_Navigation.btn_Opdater.click();
            PO_Afskriv.btn_SendTilGodkendelse.click();
        }
    }

    public static void godkendAfskrivning(TestContext testContext, String afskrivningsSagsId)
            throws InterruptedException {

        MenuNavigator.menu().opgave().opgaveOverblik();

        PO_SoegOpgave.input_RelationTilID.sendKeys(afskrivningsSagsId);

        testContext.doScreenshot();

        PO_SoegOpgave.btn_Soeg.click();

        // TO_Tools.sleep(3000);
        testContext.doScreenshot();

        PO_Afskriv.lnk_Opgave.waitingFor(PO_Afskriv.lnk_LinkTilDetaljer).click();

        testContext.waitForPageToBeLoaded();

        testContext.doScreenshot();

        PO_Afskriv.lnk_LinkTilDetaljer.waitingFor(PO_Afskriv.btn_Godkend).click();

        testContext.doScreenshot();

        PO_Afskriv.btn_Godkend.clickUntilDisabled();

        // Wait until Status is "Godkendt"
        Engine.getDriver().pause().until(MO_Wait.textEquals(PO_SoegOpgave.txt_Status, "Godkendt"));
        testContext.doScreenshot();
    }

    public static void vaelgFordringTilAfskrivning(Fordring fordring) {
        PatientWebDriver driver = Engine.getDriver();

        String parentWindow = PO_Afskriv.btn_FordringSoeg.clickJSElementAndSwitchToNewWindow();

        // fordring.getsInternId skal være sat (fx i opretfordring)
        PO_Afskriv.btn_FordringMedID(fordring.getsInternId()).click();

        // Skift tilbage til hovedvindue
        driver.switchTo().window(parentWindow);
    }

    public static void indtastVirkningsdato(String virkningsdato) {
        // Sendkeys uden selv-validering
        PO_Afskriv.input_Virkningsdato.getWebElement(Engine.getDriver()).sendKeys(virkningsdato);
    }

    private static void udfyldDividendeProcent(int dividendeProcent) {
        PO_Afskriv.input_DividendeProcent.sendKeys(Integer.toString(dividendeProcent));

    }

    public static void vaelgAfskrivningsaarsag(Afskrivningsaarsag afskrivningsaarsag) {
        PO_Afskriv.drp_Afskrivningsaarsag.pick(afskrivningsaarsag.getValue());
    }

    public static void vaelgAfskrivningsmulighed(Afskrivningsmulighed afskrivningsmulighed) {
        PO_Afskriv.drp_Afskrivningsmulighed.pick(afskrivningsmulighed.getValue());
    }

    public static void gaaTilOpretAfskrivning(TestContext testContext) {
        MenuNavigator.kontoKontekst().opretAfskrivning();
    }

    public interface DropdownOption {
        public String getValue();

        public String getVisibleText();
    }

    public static enum Afskrivningsmulighed implements DropdownOption {
        BLANK(""),
        FAST_BELOEB("FIX"),
        PROCENT("PRC");

        public String value;

        private Afskrivningsmulighed(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String getVisibleText() {
            // TODO Auto-generated method stub
            return null;
        }
    }

    public static enum Afskrivningsaarsag implements DropdownOption {
        BLANK(""),
        ANDET("OTH"),
        DOED("DTH"),
        EFTERGIVELSE("REM"),
        FEJLAGTIG_PAALIGNET("WAT"),
        FORAELDELSE(
                "OTD"),
        GAELDSSANERING(
                "DBS"),
        KONKURS("BNR"),
        KREDITORORDNING("CRA"),
        REKONSTRUKTION("RCS"),
        TVANGSOPLOESNING("COD");

        public String value;

        private Afskrivningsaarsag(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String getVisibleText() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
