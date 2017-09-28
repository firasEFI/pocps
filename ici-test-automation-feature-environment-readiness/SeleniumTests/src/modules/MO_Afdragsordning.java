package modules;

import java.text.ParseException;
import java.util.HashMap;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.pageobjects.elements.Button;
import icisel.testng.TestContext;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_Afdragsordning;
import utils.IciSelExpectedConditions;
import utils.tools.TO_Tools;

public class MO_Afdragsordning {
    /**
     * Funktion opretter inaktiv afdragsordning.
     * 
     * @param testContext
     *            = this
     * @param fordringsIDer
     *            = FordringsIDer på de fordringer der skal inkluderes i
     *            afdragsordningen
     * @param afdragsordningsfrekvens
     *            = "Hvert halve år", "Hvert kvartal", "Månedlig" eller "Årlig"
     * @param adressetype
     *            = "Opret adresse for afdragsordning" eller "Samme som
     *            skyldners kontaktadresse"
     * @param psrmDato
     *            = String med datoen i PSRM
     * @return = Afdragsordningens sagsID
     * @throws ParseException
     */
    public static String opretAktivAfdragsordning_Budget(TestContext testContext, String[] fordringsIDer,
            String afdragsordningsfrekvens, String adressetype, String psrmDato) throws ParseException {
        // Start med at oprette en inaktiv afdragsordningssag
        String sagsID = opretInaktivAfdragsordning_Budget(testContext, fordringsIDer, afdragsordningsfrekvens,
                adressetype);

        // Tryk paa "Opret Inaktiv"
        klikOpretInaktivAfdragsordning(testContext);

        // Tryk paa linket tilbage til afdragsordningen
        klikLinkInaktivAfdragsordning(testContext);

        // Tryk på knappen "Aktiver" og skift til nyt vindue
        String parentWindow = klikAktiverOgSkiftTilNytVindue(testContext);

        // Udfyld felter for første afdrag, klik gem og gå tilbage til
        // oprindeligt vindue
        udfyldSaetFoersteAfdragsdato(testContext, parentWindow, psrmDato);

        return sagsID;
    }

    /**
     * Funktion opretter inaktiv afdragsordning.
     * 
     * @param testContext
     *            = this
     * @param fordringsIDer
     *            = FordringsIDer på de fordringer der skal inkluderes i
     *            afdragsordningen
     * @param afdragsordningsfrekvens
     *            = "Hvert halve år", "Hvert kvartal", "Månedlig" eller "Årlig"
     * @param adressetype
     *            = "Opret adresse for afdragsordning" eller "Samme som
     *            skyldners kontaktadresse"
     * @return = Afdragsordningens sagsID
     */
    public static String opretInaktivAfdragsordning_Budget(TestContext testContext, String[] fordringsIDer,
            String afdragsordningsfrekvens, String adressetype) {
        // Gå til Opret Afdragsordningssag via konto-kontekst menuen i
        // dashboarded
        goToOpretAfdragsordningssag(testContext);

        // Her kan forekomme en alert, hvis der ligger en opgave på skyldner
        MO_Utilities.alertHandlerAccept(testContext);

        // Vælg type af afdragsordning
        pickTypeAfdragsordning(testContext, "Baseret på betalingsevne udfra budget");

        // Udfyld note for valg af type afdragsordning
        indtastNoteTypeAfdragsordning();

        // Vaelg fordring
        for (String fordringsID : fordringsIDer) {
            vaelgFordring(fordringsID);
        }

        // Vælg frekvens
        pickAfdragsordningsfrekvens(afdragsordningsfrekvens);

        // Screenshot
        testContext.doScreenshot();

        // Indtast beløbsnote
        indtastNoteAfdragsbeloeb();

        // Vaelg adressetype
        pickAdressetype(adressetype);

        // Indtast note for valg af adressetype
        indtastNoteAdresse();

        if (adressetype.equalsIgnoreCase("Opret adresse for afdragsordning")) {
            // Opret ny adresse for afdragsordning
            opretAdresseForAfdragsordning();
        }

        klikGem(testContext);
        testContext.waitForPageToBeLoaded();

        // print lidt output
        // Her har der været en fejl: timed out waiting for zonemapFrame_2
        String SagsID = PO_Afdragsordning.txt_SagsID.getText();
        System.out.println("Sags ID: " + SagsID);
        return SagsID;

        // KUN RELEVANT FOR NOGLE METODER
        // if (sAfdragsordning.equalsIgnoreCase("VLT") ||
        // sAfdragsordning.equalsIgnoreCase("ADS")){ //||
        // sAfdragsordning.equalsIgnoreCase("PAL")){ //"PAL" (tabeltraek)
        // autoudfylder nu her
        // //Indsaet afdragsbeloeb
        // PO_Afdragsordning.txt_Beloeb.sendKeys(sBeloeb);
        // }

        // if(sAfdragsordning.equalsIgnoreCase("ADS")){
        // PO_Afdragsordning.txt_DatoFoersteBetaling_Bobehandling.sendKeys(TO_Tools.getDagsDatoPlus_DDMMYYYY(driver,
        // 90));
        // }
    }

    /**
     * Funktion udfylder dato for første afdrag som 60 dage frem i tiden,
     * udfylder aktiveringsnote og trykker så gem i vindue efter der er trykket
     * "Aktiver"
     * 
     * @param testContext
     *            = testContext
     * @param parentWindow
     *            = String med gemt id på oprindeligt vindue fra før der blev
     *            trykket "Aktiver"
     * @param psrmDato
     *            = String med datoen i PSRM
     * @throws ParseException
     */
    public static void udfyldSaetFoersteAfdragsdato(TestContext testContext, String parentWindow, String psrmDato)
            throws ParseException {
        // indtast dato for første afdrag
        indtastDatoForFoersteBetaling(testContext, psrmDato, 60);

        // indtast aktiveringsnote
        indtastAktiveringsNote();

        // Klik gem og gå til oprindeligt vindue
        klikGemFørsteAfdragsdato(testContext, parentWindow);
    }

    /**
     * Funktion udfylder dato for første afdrag, aktiveringsnote og trykker så
     * gem i vindue efter der er trykket "Aktiver"
     * 
     * @param testContext
     *            = testContext
     * @param parentWindow
     *            = String med gemt id på oprindeligt vindue fra før der blev
     *            trykket "Aktiver"
     * @param dageTilFoersteAfdrag
     *            = Antal dage fra systemets dato til første afdrag skal finde
     *            sted
     * @param psrmDato
     *            = String med datoen i PSRM
     * @throws ParseException
     */
    public static void udfyldSaetFoersteAfdragsdato(TestContext testContext, String parentWindow,
            int dageTilFoersteAfdrag, String psrmDato) throws ParseException {
        // indtast dato for første afdrag
        indtastDatoForFoersteBetaling(testContext, psrmDato, dageTilFoersteAfdrag);

        // indtast aktiveringsnote
        indtastAktiveringsNote();

        // Klik gem og gå til oprindeligt vindue
        klikGemFørsteAfdragsdato(testContext, parentWindow);
    }

    /**
     * Funktion går til "Opret afdragsordningssag" i konto kontekst menuen
     * 
     * @param testContext
     *            = testContext
     */
    private static void goToOpretAfdragsordningssag(TestContext testContext) {
        MenuNavigator.kontoKontekst().opretAfdragsordningssag();

        testContext.waitForPageToBeLoaded();
        PO_Afdragsordning.drp_Afdragsordning.waitFor();
        TO_Tools.sleep(1000);
    }

    /**
     * Funktion vælger hvilken type afdragsordning der vælges i dropdown'en
     * "Type af afdragsordning"
     * 
     * @param testContext
     *            = testContext
     * @param typeAfAfdragsordning
     *            = Skriv teksten for muligheden, fx "Baseret på betalingsevne
     *            udfra budget"
     */
    private static void pickTypeAfdragsordning(TestContext testContext, String typeAfAfdragsordning) {
        testContext.waitForPageToBeLoaded();

        // Map alle mulighder
        HashMap<String, String> typeAfdragsordning = new HashMap<String, String>();
        typeAfdragsordning.put("Baseret på betalingsevne udfra budget", "PAB");
        typeAfdragsordning.put("Baseret på betalingsevne udfra tabeltræk", "PAL");
        typeAfdragsordning.put("Baseret på gældens størrelse", "SZD");
        typeAfdragsordning.put("Baseret på kulance aftale", "FRN");
        typeAfdragsordning.put("Baseret på sikkerhedsstillelse", "COL");
        typeAfdragsordning.put("Bobehandling", "ADS");
        typeAfdragsordning.put("Frivillig afdragsordning", "VLT");

        PO_Afdragsordning.drp_Afdragsordning.pick(typeAfdragsordning.get(typeAfAfdragsordning));
    }

    /**
     * Funktion indsætter en note for typen af afdragsordning
     */
    private static void indtastNoteTypeAfdragsordning() {
        PO_Afdragsordning.txt_NoteAfdragsordning.sendKeys("test_typeAfdragsordning");
    }

    /**
     * Funktion vælger hvilken fordring der skal inkluderes i afdragsordningen
     * 
     * @param fordringsID
     *            = indsæt fordringsID'et på den ønskede fordring som en string
     */
    private static void vaelgFordring(String fordringsID) {
        try {
            PO_Afdragsordning.chk_Fordringer(fordringsID).click();
        } catch (Exception e) {
            System.out.println("Fordring med fordringsID " + fordringsID + " er ikke på skyldner!");
        }
    }

    /**
     * Funktion vælger afdragsfrekvensen i dropdown'en "Afdragsordningsfrekvens"
     * 
     * @param afdragsordningsfrekvens
     *            = "Hvert halve år", "Hvert kvartal", "Månedlig" eller "Årlig"
     */
    private static void pickAfdragsordningsfrekvens(String afdragsordningsfrekvens) {
        // Map alle mulighder
        HashMap<String, String> frekvens_til_value_map = new HashMap<String, String>();
        frekvens_til_value_map.put("Hvert halve år", "HFY");
        frekvens_til_value_map.put("Hvert kvartal", "QUR");
        frekvens_til_value_map.put("Månedlig", "MON");
        frekvens_til_value_map.put("Årlig", "YRL");

        PO_Afdragsordning.drp_Afdragsordningsfrekvens.pick(frekvens_til_value_map.get(afdragsordningsfrekvens));
    }

    /**
     * Funktion indsætter note om afdragsbeløbet
     */
    private static void indtastNoteAfdragsbeloeb() {
        PO_Afdragsordning.txt_BeloebNote.sendKeys("test_Beloebsnote");
    }

    /**
     * Funktion vælger adressetype i dropdownen "Adressetype"
     * 
     * @param adressetype
     *            = "Opret adresse for afdragsordning" eller "Samme som
     *            skyldners kontaktadresse"
     */
    private static void pickAdressetype(String adressetype) {
        // Map alle mulighder
        HashMap<String, String> adressetype_til_value_map = new HashMap<String, String>();
        adressetype_til_value_map.put("Opret adresse for afdragsordning", "CSA");
        adressetype_til_value_map.put("Samme som skyldners kontaktadresse", "DBD");

        PO_Afdragsordning.drp_Adressetype.pick(adressetype_til_value_map.get(adressetype));
    }

    /**
     * Funktion indtaster note om den valgte adressetype
     */
    private static void indtastNoteAdresse() {
        PO_Afdragsordning.input_NoteForAdresse.sendKeys("test_Adressenote");
    }

    /**
     * Funktion indtaster skyldners navn for ny adresse
     */
    private static void indtastSkyldnersNavn() {
        PO_Afdragsordning.input_SkyldnersNavn.sendKeys("test_SkyldnersNavn");
    }

    /**
     * Funktion indtaster C/O navn for ny adresse
     */
    private static void indtastCONavn() {
        PO_Afdragsordning.input_CONavn.sendKeys("test_CONavn");
    }

    /**
     * Funktion indtaster adresse for ny adresse
     */
    private static void indtastAdresse1() {
        PO_Afdragsordning.input_Adresse1.sendKeys("test_Adresse1");
    }

    /**
     * Funktion indtaster postnummer for ny adresse
     */
    private static void indtastPostnummer() {
        PO_Afdragsordning.input_Postnummer.sendKeys("1234");
    }

    /**
     * Funktion opretter ny adresse for afdragsordning ved at indtaste værdier i
     * alle obligatoriske felter herfor
     */
    private static void opretAdresseForAfdragsordning() {
        // Indtast note for adresse
        indtastNoteAdresse();

        // Indtast skyldners navn
        indtastSkyldnersNavn();

        // Indtast CO navn
        indtastCONavn();

        // Indtast adresse1
        indtastAdresse1();

        // Indtast postnummer
        indtastPostnummer();
    }

    /**
     * Funktion klikker på knappen "Gem" i bunden af oprettelsen af
     * afdragsordningen og venter herefter til at siden er loaded.
     * 
     * @param testContext
     */
    public static void klikGem(TestContext testContext) {
        // Vent på at Gem knap er klar
        PO_Afdragsordning.btn_Gem.waitFor();

        // Screenshot
        testContext.doScreenshot();

        // Tryk paa Gem
        PO_Afdragsordning.btn_Gem.click();

        // Vent på at ny side (formular) indlæses
        testContext.waitForPageToBeLoaded();
        MO_Wait.waitForRightMenu();
    }

    /**
     * Funktion klikker på knappen "Opret inaktiv afdragsordning" når man er
     * inde på selve sagen efter oprettelse
     * 
     * @param testContext
     *            = testContext
     */
    public static void klikOpretInaktivAfdragsordning(TestContext testContext) {
        // Vent på at knappen er tilgængelig
        MO_Utilities.ventTilFeltIndeholderTekst(PO_Afdragsordning.txt_Status, "Sag oprettet");
        PO_Afdragsordning.btn_OpretInaktiv.waitFor();

        // Screenshot
        testContext.doScreenshot();

        // Tryk paa Gem
        PO_Afdragsordning.btn_OpretInaktiv.clickUntilDisabled();

        // Vent på at ny side (formular) indlæses
        testContext.waitForPageToBeLoaded();
    }

    /**
     * Funktion klikker på linket til den inaktive afdragsordningssag efter at
     * man har klikket for at oprette den inaktive afdragsordningssag
     * 
     * @param testContext
     *            = testContext
     */
    public static void klikLinkInaktivAfdragsordning(TestContext testContext) {
        // Vent på at titlen siger, at afdragsordningen er oprettet
        MO_Utilities.ventTilFeltIndeholderTekst(PO_Afdragsordning.txt_Titellinje, "Oprettet (inaktiv)");

        // Screenshot
        testContext.doScreenshot();

        // Tryk paa linket tilbage til afdragsordningen
        PO_Afdragsordning.lnk_Afdragsordning.click();

        // Vent på at ny side (formular) indlæses
        testContext.waitForPageToBeLoaded();
    }

    /**
     * Tilbage på den inaktive afdragsordningssag klikker denne funktion på
     * knappen "Aktiver", hvorefter der skiftes til det nye vindue, hvor
     * eventuelle alerts håndteres
     * 
     * @param testContext
     *            = testContext
     * @return the parent window
     */
    public static String klikAktiverOgSkiftTilNytVindue(TestContext testContext) {
        // Vent på at titlen siger, at afdragsordningen er oprettet
        MO_Utilities.ventTilFeltIndeholderTekst(PO_Afdragsordning.txt_Titellinje, "Oprettet - inaktiv");

        // Screenshot
        testContext.doScreenshot();

        // Klik på aktiver knappen og skift til det nye vindue
        String parentWindow = testContext.getDriver().getWindowHandle();
        PO_Afdragsordning.btn_Aktiver.clickElementAndSwitchToNewWindow();

        WebDriverWait wait = new WebDriverWait(testContext.getDriver(), 10);
        wait.until(IciSelExpectedConditions.ExpectedAlertAccept());
        wait.until(IciSelExpectedConditions.ExpectedAlertAccept());

        return parentWindow;
    }

    /**
     * Funktion indtaster dato for første betaling på afdragsordningen i nyt
     * vindue der kommer frem efter at der trykkes "Aktiver"
     * 
     * @param testContext
     *            = testContext
     * @param psrmDato
     *            = String med datoen i PSRM.
     * @param dageTilFoersteBetaling
     *            = Antallet af dage mellem systemets dato og den første
     *            indbetaling
     * @throws ParseException
     */
    public static void indtastDatoForFoersteBetaling(TestContext testContext, String psrmDato,
            int dageTilFoersteBetaling) throws ParseException {
        // Indtast dato for foerste betaling
        PO_Afdragsordning.input_DatoFoersteBetaling
                .sendKeys(TO_Tools.getPsrmDatoPlus_DDMMYYYY(psrmDato, dageTilFoersteBetaling));

        testContext.waitForPageToBeLoaded();
    }

    /**
     * Funktion indtaster aktiveringsnote i nyt vindue der kommer frem efter at
     * der trykkes "Aktiver"
     */
    public static void indtastAktiveringsNote() {
        PO_Afdragsordning.input_Aktiveringsnote.sendKeys("test_Aktiveringsnote");
    }

    /**
     * Funktion klikker på gem i nyt vindue der kommer frem efter at der trykkes
     * "Aktiver" og vender derefter tilbage til det forrige vindue
     * 
     * @param testContext
     *            = testContext
     * @param parentWindow
     *            = String med gemt id på oprindeligt vindue fra før der blev
     *            trykket "Aktiver"
     */
    public static void klikGemFørsteAfdragsdato(TestContext testContext, String parentWindow) {
        PatientWebDriver driver = testContext.getDriver();

        // Vent
        PO_Afdragsordning.btn_GemDato.waitFor();

        // Screenshot
        testContext.doScreenshot();

        // Tryk paa Gem dato
        PO_Afdragsordning.btn_GemDato.click();

        // Vent
        testContext.waitForPageToBeLoaded();

        // Gaa tilbage til hovedvindue
        driver.pause().until(IciSelExpectedConditions.windowIsAvailableAndSwitchToIt(parentWindow));

        // Vent på at siden er helt loaded
        MO_Utilities.ventTilFeltIndeholderTekst(PO_Afdragsordning.txt_Titellinje, "Status before Payplan Activation");
    }

    /**
     * Funktionen starter i skyldnerens sagssoverblik, hvorfra den klikker sig
     * ind på den ønskede sag og siden på knappen "Annuller"
     * 
     * @param testContext
     *            = testContext
     * @param sagsID
     *            = Sagens sagsID
     * @param sagsStadie
     *            = Muligheder: "Ny" eller "Oprettet" (dækker inaktiv og aktiv)
     */
    public static void lukAfdragssordningssag(TestContext testContext, String sagsID, String sagsStadie) {
        // Tjek at argumentet "sagsStadie" er angivet korrekt
        Assert.assertTrue(sagsStadie.equalsIgnoreCase("Ny") || sagsStadie.equalsIgnoreCase("Oprettet"),
                "Argumentet 'sagsStadie' er ikke angivet korrekt. Muligheder: 'Ny' eller 'Oprettet'");

        // Klik på sagsID'et i skyldnerens sagsoverblik
        MO_Navigation360GradersOverblik.klikSagsID(testContext, sagsID);

        // Tryk på "Annuller"
        if (sagsStadie.equalsIgnoreCase("Ny"))
            annullerNyAfdragsordningssag(testContext);
        else if (sagsStadie.equalsIgnoreCase("Oprettet"))
            annullerOprettetAfdragsordningssag(testContext);
    }

    /**
     * Funktionen starter inde på den inaktive afdragsordningssag, hvor den
     * trykker på knappen "annuller" og derefter venter på og tjekker at
     * annulleringen har fundet sted.
     */
    public static void annullerNyAfdragsordningssag(TestContext testContext) {
        annullerAfdragsordningssag(testContext, PO_Afdragsordning.btn_AnnullerInaktiv);
    }

    /**
     * Funktionen starter inde på den aktive afdragsordningssag, hvor den
     * trykker på knappen "annuller" og derefter venter på og tjekker at
     * annulleringen har fundet sted.
     */
    public static void annullerOprettetAfdragsordningssag(TestContext testContext) {
        annullerAfdragsordningssag(testContext, PO_Afdragsordning.btn_AnnullerAktiv);
    }

    /**
     * Funktionen indeholder indmaden der bruges til at annullere en
     * afdragsordningssag, hvor det blot specificeres hvilken annulleringsknap
     * der skal bruges. Funktionen starter inde på afdragsordningssagen.
     * 
     * @param testContext
     *            = testContext
     * @param btn_Luk
     *            = Knappen der skal anvendes til at annullere afdragsordningen
     */
    private static String annullerAfdragsordningssag(TestContext testContext, Button btn_Luk) {
        PatientWebDriver driver = Engine.getDriver();

        // Vent på at knappen er tilgængelig
        PO_Afdragsordning.txt_Oprettelsestidspunkt.waitFor();

        // Tryk på annuller knappen og skift til nyt vindue
        String parentWindow = driver.getWindowHandle();
        btn_Luk.clickAttemptUsingJS();

        // Vent på at side er loaded
        testContext.waitForPageToBeLoaded();

        // Luk afdragsordningssag manuelt
        lukSagManuelt(driver, "test", true);

        // Vent på at titellinjen indeholder teksten "Sag annulleret"
        driver.pause().until(IciSelExpectedConditions.elementContainsTextIgnoreCase(PO_Afdragsordning.txt_Titellinje,
                "Sag lukket", "Sag annulleret"));

        return parentWindow;
    }

    /**
     * Funktion klikker OK til at annullere afdragsordningssagen, hvorefter den
     * går tilbage til hovedvinduet
     * 
     * @param testContext
     *            = textContext
     * @param parentWindow
     *            = String med hovedvinduets credentials
     */
    public static void klikOK(TestContext testContext, String parentWindow) {
        PatientWebDriver driver = testContext.getDriver();

        // Vent
        PO_Afdragsordning.btn_OK.waitFor();

        // Screenshot
        testContext.doScreenshot();

        // Tryk paa Gem dato
        PO_Afdragsordning.btn_OK.click();

        // Gaa tilbage til hovedvindue
        driver.switchTo().window(parentWindow);
    }

    public static void lukSagManuelt(PatientWebDriver driver, String lukningsbegrundelse,
            boolean skyldnerErBlevetInformeret) {
        String parentWindow = driver.getWindowHandle();

        // Skift til ny vindue
        driver.pause().until(IciSelExpectedConditions.windowIsNot(parentWindow));

        // Udfyld lukningsårsag
        PO_Afdragsordning.input_Lukningsbegrundelse.sendKeys(lukningsbegrundelse);

        // Angiv at skylder er blevet iformeret
        if (skyldnerErBlevetInformeret) {
            PO_Afdragsordning.chk_SkylderErBlevetInformeret.click();
        }

        // Tryk gem
        PO_Afdragsordning.btn_Gem_LukSag.click();

        // Skift tilbage til hovedvindue
        driver.switchTo().window(parentWindow);

    }

}