package modules;

import org.openqa.selenium.Keys;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.testng.TestContext;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_Opret_Paakrav;
import pageobjects.generic.PO_SoegOpgave;
import utils.tools.TO_Tools;

public class MO_SoegOpgave {

    public static void soegOpgaveViaOpgaveID(TestContext testContext, String opgaveID) throws InterruptedException {
        // Gå til opgaveoverblikket, indtast opgaveID og klik på søg
        fremsoegOpgaveViaOpgaveID(testContext, opgaveID);
    }

    /**
     * Funktion fremsøger en opgave i opgaveoverblikket via opgavens ID,
     * hvorefter den tilgår opgaven via linket fundet her.
     * 
     * @param testContext
     *            = testContext
     * @param opgaveID
     *            = opgavens ID angivet som String
     */
    public static void soegOgGaaTilOpgaveViaOpgaveID(TestContext testContext, String opgaveID) {
        // Gå til opgaveoverblikket, indtast opgaveID og klik på søg
        fremsoegOpgaveViaOpgaveID(testContext, opgaveID);

        // Tilgå opgaven via linket
        gaaTilFremsoegtOpgave(testContext, opgaveID);
    }

    /**
     * Funktion fremsøger en opgave i opgaveoverblikket via opgavens ID,
     * hvorefter den tildeler opgaven til sagsbehandleren selv via knappen
     * "Tildel" ude til højre.
     * 
     * @param testContext
     *            = testContext
     * @param opgaveID
     *            = opgavens ID angivet som String
     */
    public static void soegOgTildelOpgaveFraOverblikViaOpgaveID(TestContext testContext, String opgaveID) {
        // Gå til opgaveoverblikket, indtast opgaveID og klik på søg
        fremsoegOpgaveViaOpgaveID(testContext, opgaveID);

        // Klik på knappen "Tildel" ud for den fremsøgte opgave
        klikTildelFraOverblik(testContext);
    }

    /**
     * Funktion fremsøger en opgave i opgaveoverblikket via opgavens ID,
     * hvorefter den går ind på opgaven og derfra tildeler opgaven til en
     * valgfri bruger eller rolle.
     * 
     * @param testContext
     *            = testContext
     * @param opgaveID
     *            = opgavens ID angivet som String
     */
    public static void soegOgTildelOpgaveFraOpgavenViaOpgaveID(TestContext testContext, String opgaveID, String sendTil,
            String tildelTil) {
        // Fremsøg opgave via opgaveID og gå til opgaven
        soegOgGaaTilOpgaveViaOpgaveID(testContext, opgaveID);

        // Klik på Tildel knappen
        String parentWindow = klikTildelFraOpgavenOgSkiftTilNytVindue(testContext);

        // Vælg om opgaven skal tildeles en rolle eller en bruger
        pickSendTil(testContext, sendTil);

        // Indsæt bruger/rolle som opgaven skal sendes til
        if (sendTil.equalsIgnoreCase("Bruger")) {
            indtastBruger(tildelTil);
        } else if (sendTil.equalsIgnoreCase("Rolle")) {
            indtastRolle(tildelTil);
        }

        // Indtast Detaljer-note
        indtastDetaljer();

        // Klik OK
        klikOK(testContext, parentWindow);
    }

    /**
     * Funktion fremsøger en opgave i opgaveoverblikket via opgavens ID,
     * hvorefter den afslutter opgaven via knappen "Afslut opgave" ude til
     * højre.
     * 
     * @param testContext
     *            = testContext
     * @param opgaveID
     *            = opgavens ID angivet som String
     * @param begrundelse
     *            = Skriv begrundelsen direkte som den står i PSRM (fx "Andet")
     */
    public static void soegOgAfslutOpgaveViaOpgaveID(TestContext testContext, String opgaveID, String begrundelse) {
        // Gå til opgaveoverblikket, indtast opgaveID og klik på søg
        fremsoegOpgaveViaOpgaveID(testContext, opgaveID);

        // Klik på knappen "Afslut opgave" ud for den fremsøgte opgave
        klikAfslutOpgave(testContext);

        // Vælg begrundelse for at afslutte opgave
        pickBegrundelse(testContext, begrundelse);

        // Indtast beskrivelse
        indtastBeskrivelse();

        // Klik på Gem
        klikGem(testContext);
    }

    public static void fremsoegOpgaveViaOpgaveID(TestContext testContext, String opgaveID) {
        // Gaa gennem topmenuen > Opgave > Opgave overblik
        gotoSoegOpgave(testContext);

        // Indtast opgaveID
        indtastOpgaveID(opgaveID);

        // Klik på Søg
        klikSoeg(testContext);
    }

    /**
     * IU-231 - Gå til opgave overblik
     * 
     * Funktion går via topmenuen til opgave > opgave overblik
     * 
     * Entry: Siden skal være loaded og topmenuen tilgængelig
     * Exit: Opgaveoverblikket
     * 
     * @param testContext = testContext
     */
    public static void gotoSoegOpgave(TestContext testContext) {
        // Gå via topmenuen til Opgave og siden Opgave overblik
        MenuNavigator.menu().opgave().opgaveOverblik();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();
    }

    public static void indtastOpgaveID(String opgaveID) {
        // Indtast opgaveID
        PO_SoegOpgave.input_OpgaveID.sendKeys(opgaveID);
    }

    public static void klikSoeg(TestContext testContext) {
        // Klik på Søg
        PO_SoegOpgave.btn_Soeg.click();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();

        // Screenshot
        testContext.doScreenshot();
    }

    public static void gaaTilFremsoegtOpgave(TestContext testContext, String opgaveID) {
        // Klik på opgavens link
        PO_SoegOpgave.lnk_Opgavetype.click();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();
        Engine.getDriver().pause().until(MO_Wait.inputHasVisibleText(PO_SoegOpgave.input_OpgaveIdSoegefelt));
    }

    /**
     * Funktion klikker på knappen "Tildel" ud for den fremsøgte opgave i
     * opgaveoverblikket. Knappen tildeler opgaven til sagsbehandleren selv.
     * 
     * @param testContext
     *            = testContext
     */
    public static void klikTildelFraOverblik(TestContext testContext) {
        // Klik på knappen "Tildel" ude til højre
        PO_SoegOpgave.btn_TildelIOverblik.click();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();

        // Vent til linket til at tilgå opgaven indeholder teksten "Aktiv"
        MO_Utilities.ventTilFeltIndeholderTekst(PO_SoegOpgave.lnk_Opgavetype, "Aktiv");

        // Screenshot
        testContext.doScreenshot();
    }

    public static void klikAfslutOpgave(TestContext testContext) {
        // Klik på knappen "Afslut opgave" ude til højre
        PO_SoegOpgave.btn_AfslutOpgave.click();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();
    }

    public static void pickBegrundelse(TestContext testContext, String begrundelse) {
        // Vælg begrundelse for afslutning af opgaven
        PO_SoegOpgave.drp_Begrundelse.pickByVisibleText(begrundelse);

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();
    }

    public static void indtastBeskrivelse() {
        // Indtast beskrivelse
        PO_SoegOpgave.input_Beskrivelse.sendKeys("Test af beskrivelsesfelt");
    }

    public static void klikGem(TestContext testContext) {
        testContext.doScreenshot();

        // Klik gem
        PO_SoegOpgave.btn_Gem.click();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();

        // Vent på at linket til opgaven indeholder teksten "Afsluttet" før der
        // gås videre
        MO_Utilities.ventTilFeltIndeholderTekst(PO_SoegOpgave.lnk_Opgavetype, "Afsluttet");

        // Screenshot
        testContext.doScreenshot();
    }

    public static String klikTildelFraOpgavenOgSkiftTilNytVindue(TestContext testContext) {
        // Klik på Tildel knappen og skift til det nye vindue
        String parentWindow = PO_SoegOpgave.btn_TildelIOpgaven.clickJSElementAndSwitchToNewWindow();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();

        return parentWindow;
    }

    public static void pickSendTil(TestContext testContext, String sendTil) {
        // Vælg hvilken gruppe opgaven skal tildeles til
        PO_SoegOpgave.drp_SendTil.pickByVisibleText(sendTil);

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();
    }

    public static void indtastBruger(String bruger) {
        // Indtast brugerens initialer efter at feltet er clearet
        PO_SoegOpgave.input_Bruger.clear();
        PO_SoegOpgave.input_Bruger.sendKeys(bruger);

        // Tryk på TAB for at komme ud af feltet
        TO_Tools.trykKnap(Keys.TAB);
    }

    public static void indtastRolle(String rolle) {
        // Indtast brugerens initialer efter at feltet er clearet
        PO_SoegOpgave.input_Rolle.clear();
        PO_SoegOpgave.input_Rolle.sendKeys(rolle);

        // Tryk på TAB for at komme ud af feltet
        TO_Tools.trykKnap(Keys.TAB);
    }

    public static void indtastDetaljer() {
        // Indtast detalje-note
        PO_SoegOpgave.input_Detaljer.sendKeys("Test detalje-note");
    }

    public static void klikOK(TestContext testContext, String parentWindow) {
        PatientWebDriver driver = testContext.getDriver();

        // Screenshot
        testContext.doScreenshot();

        // Klik på OK
        PO_SoegOpgave.btn_OK.click();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();

        // Gaa tIlbage til hovedvindue
        driver.switchTo().window(parentWindow);

        // Vent på at siden er helt loaded
        // MO_Utilities.ventTilFeltIndeholderTekst(PO_Afdragsordning.txt_Titellinje,
        // "Status before Payplan Activation");
    }

    public static void soegOpgave(TestContext testContext, String relationTilId) {
        MenuNavigator.menu().opgave().opgaveOverblik();

    }

}
