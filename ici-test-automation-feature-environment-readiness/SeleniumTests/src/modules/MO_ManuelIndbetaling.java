package modules;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.pageobjects.PO_Navigation;
import icisel.testng.TestContext;
import icisel.utils.TO_Tools;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_Manuel_Indbetaling;
import pageobjects.generic.PO_Opret_Indbetalingskontrol_ID;

public class MO_ManuelIndbetaling {

    /**
     * @param sIndbetalingskildeType
     *            = Angives i excelark. Se valgmuligheder der i
     *            TC_Opret_Indbetalingskontrol_ID
     * @param sIndbetalingskilde
     *            = Angives i excelark. Se valgmuligheder der i
     *            TC_Opret_Indbetalingskontrol_ID
     * @param sIndbetalingskontrolStatus
     *            = Angives i excelark. Se valgmuligheder der i
     *            TC_Opret_Indbetalingskontrol_ID
     */
    public static void OpretIndbetalingskontrolID(TestContext testContext, String sIndbetalingskildeType,
            String sIndbetalingskilde, String sIndbetalingskontrolStatus) {
        PatientWebDriver driver = testContext.getDriver();

        // Gaa via topmenu: Indbetalinger > Indbetalingskontrol > Tilfoej
        MenuNavigator.menu().indbetalinger().indbetalingsKontrol().tilfoej();

        // Vent
        TO_Tools.sleep(1000);

        // Vaelg Indbetalingskilde type
        Select aSelect0 = new Select(PO_Opret_Indbetalingskontrol_ID.drp_IndbetalingskildeType.getWebElement(driver));
        aSelect0.selectByValue(sIndbetalingskildeType);

        // Vent
        TO_Tools.sleep(50);

        // Tryk paa Gem i toppen
        PO_Navigation.btn_Gem.click();
        ;

        // Screenshot
        testContext.doScreenshot();

        // Vent
        TO_Tools.sleep(3000);

        // Gaa via topmenu: Indbetalinger > Indbetalingskilde > Tilfoej
        MenuNavigator.menu().indbetalinger().indbetalingsKilde().tilfoej();

        // Vent
        TO_Tools.sleep(1500);

        // Indsaet WSS som indbetalingskilde
        PO_Opret_Indbetalingskontrol_ID.input_Indbetalingskilde.sendKeys(sIndbetalingskilde);

        // Hent kontrolID og gem det
        String kontrolID = MO_ManuelIndbetaling.HentIndbetalingsKontrolID(testContext, sIndbetalingskildeType,
                sIndbetalingskontrolStatus,
                PO_Opret_Indbetalingskontrol_ID.btn_IndbetalingskontrolIDSoeg.getWebElement(driver));

        // Opret text til oversigtsfil
        String text = sIndbetalingskilde + ", " + kontrolID;

        // Skriv text i oversigtsfil
        TO_Tools.printToFile(
                "C:\\Users\\jakobrjensen\\Desktop\\workspace\\ICI\\src\\testdata\\IndbetalingskontrolIDer.txt", text);

        // Vent
        TO_Tools.sleep(500);

        // Marker checkboxen "Alle brugere"
        PO_Opret_Indbetalingskontrol_ID.chkbox_AlleBrugere.click();

        // Slet tekst i "Bruger ID"
        PO_Opret_Indbetalingskontrol_ID.input_BrugerID.clear();

        // Vent
        TO_Tools.sleep(50);

        // Tryk paa Gem
        PO_Navigation.btn_Gem.click();

        // Screenshot
        testContext.doScreenshot();
    }

    /**
     * Funktion opretter en manuel indbetaling paa skyldner, hvor der ikke
     * allerede er oprettet et indbetalingskontrolID. Indbetalingen sker som
     * kontant.
     * 
     * @param sIndbetalingskildeType
     *            = Valg af indbetalingskildeType. Muligheder: ADHC, APAY, LOCK,
     *            CASH
     * @param sIndbetalingskontrolStatus
     *            = Valg af indbetalingskontrol status. Saettes som regel til
     *            Open (10).
     * @param sBeloeb
     *            = Indbetalingsbeloebet
     * @param sDaekningsregel
     *            = Valg af daekningsregel. Muligheder: Konto: DK-PAYACC, OCR:
     *            DK-PAYOCR
     * @param OCRNummer
     *            = OCR-identifier saafremt OCR vaelges som daekningsregel
     */
    public static void ManuelIndbetaling(TestContext testContext, String sIndbetalingskildeType,
            String sIndbetalingskontrolStatus, String sBeloeb, String sDaekningsregel, String OCRNummer) {

        MO_ManuelIndbetaling.ManuelIndbetaling(testContext, sIndbetalingskildeType, sIndbetalingskontrolStatus, sBeloeb,
                sDaekningsregel, OCRNummer, false, "CASH");

    }

    /**
     * Funktion opretter en indbetaling paa skyldners konto
     * 
     * @param sIndbetalingskildeType
     *            = Angives i excelark. Se valgmuligheder der i H_Indbetaling
     * @param sIndbetalingskontrolStatus
     *            = Angives i excelark. Se valgmuligheder der i H_Indbetaling
     * @param sBeloeb
     *            = Angives i excelark. Komma kan anvendes ved brug af
     *            almindeligt komma
     * @param sDaekningsregel
     *            = Angives i excelark. Se valgmuligheder der i H_Indbetaling
     * @param OCRNummer
     *            = OCR nummer oprettet i PSRM. Kan returneres af OpretOCR
     *            metoden
     * @param indbetalingsKontrolIDalreadyExists
     *            = Boolean der siger om indbetalingskontrolID allerede
     *            eksisterer
     * @param sIndbetalingsType
     *            = Valg af betalingsform
     */
    public static void ManuelIndbetaling(TestContext testContext, String sIndbetalingskildeType,
            String sIndbetalingskontrolStatus, String sBeloeb, String sDaekningsregel, String OCRNummer,
            boolean indbetalingsKontrolIDalreadyExists, String sIndbetalingsType) {
        System.out.println("Opretter betaling");

        PatientWebDriver driver = testContext.getDriver();

        // Gaa til Konto for at faa kontonr
        String konto = MO_Utilities.hentKontonummer();

        String Regelvaerdi = null;
        // Hent OCR linje hvis noedvendigt
        if (sDaekningsregel.equalsIgnoreCase("DK-PAYACC"))
            Regelvaerdi = konto;
        else if (sDaekningsregel.equalsIgnoreCase("DK-PAYOCR"))
            Regelvaerdi = OCRNummer;
        else {
            System.out.println("Ugyldig daekningsregel valgt!");
        }

        // Hovedmenu: vaelg manuel indbetaling
        MenuNavigator.menu().indbetalinger().manuelIndbetalingTilfoej();

        // Vent
        TO_Tools.sleep(3000);

        // Gem oprindeligt vindue
        String parentWindow = driver.getWindowHandle();

        if (!indbetalingsKontrolIDalreadyExists) {
            // Klik paa soegeikonet ud for Indbetalingskilde ID - AABNER NYT
            // VINDUE
            PO_Manuel_Indbetaling.btn_IndbetalingskildeID_Soeg.click();

            // Vent
            TO_Tools.sleep(1000);

            // Skift til nyt vindue
            String subWindow1 = null;
            Set<String> handles = driver.getWindowHandles(); // get all window
            // handles
            Iterator<String> iterator = handles.iterator();
            while (iterator.hasNext()) {
                subWindow1 = iterator.next();
            }
            driver.switchTo().window(subWindow1); // switch to popup window

            TO_Tools.sleep(50);

            MO_ManuelIndbetaling.HentIndbetalingsKontrolID(testContext, sIndbetalingskildeType,
                    sIndbetalingskontrolStatus,
                    PO_Manuel_Indbetaling.btn_SoegIndbetalingskontrolID.getWebElement(driver));

            // Klik paa den nederste soeg knap (ud for Indbetalingskontrol ID)
            PO_Manuel_Indbetaling.btn_Soeg_IndbetalingskildeSoegning.click();

            // Gaa tilbage til hovedvindue
            driver.switchTo().window(parentWindow);
        }
        // Vent
        TO_Tools.sleep(800);

        // Vaelg antal betalingshaendelser
        // MULT = Multiple
        // SNGL = Single
        Select aSelect = new Select(PO_Manuel_Indbetaling.drp_AntalBetalingshaendelser.getWebElement(driver));
        aSelect.selectByValue("SNGL");

        // Vent
        TO_Tools.sleep(2000);

        // Indtast kontonummer paa skyldner
        PO_Manuel_Indbetaling.txt_BetalerKontoID.sendKeys(konto);

        // Tryk TAB paa tastaturet
        driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.TAB));

        // Vent
        TO_Tools.sleep(1250);

        // Ryd og indsaet samlet beloeb til overfoersel
        PO_Manuel_Indbetaling.txt_UdbudBeloeb.clear();
        PO_Manuel_Indbetaling.txt_UdbudBeloeb.sendKeys(sBeloeb);

        // Tryk TAB paa tastaturet
        driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.TAB));

        // Vent
        TO_Tools.sleep(1000);

        // Vaelg daekningsregel
        Select aSelect4 = new Select(PO_Manuel_Indbetaling.drp_Daekningsregel.getWebElement(driver));
        aSelect4.selectByValue(sDaekningsregel);

        // Vent
        TO_Tools.sleep(2000);

        // Indsaet regelvaerdi (Kontonummer / OCR linje)
        if (sDaekningsregel.equalsIgnoreCase("DK-PAYACC"))
            PO_Manuel_Indbetaling.txt_Regelvaerdi_ACC.sendKeys(Regelvaerdi);
        else if (sDaekningsregel.equalsIgnoreCase("DK-PAYOCR"))
            PO_Manuel_Indbetaling.txt_Regelvaerdi_OCR.sendKeys(Regelvaerdi);

        // Vent
        TO_Tools.sleep(3000);

        // Tryk TAB paa tastaturet
        driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.TAB));

        // Vent
        TO_Tools.sleep(2500);

        // Ryd og indsaet beloeb til indbetaling paa konto / OCR linje
        PO_Manuel_Indbetaling.txt_Beloeb.clear();
        PO_Manuel_Indbetaling.txt_Beloeb.sendKeys(sBeloeb);

        // Tryk TAB paa tastaturet
        driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.TAB));

        // Vaelg IndbetalingsType
        // Select aSelect5 = new Select(PO_Manuel_Indbetaling.txt_type(driver));
        // aSelect5.selectByVisibleText("Kontant");
        // aSelect5.selectByValue(sIndbetalingsType);
        // aSelect5.selectByValue(sIndbetalingsType);

        // Vent
        TO_Tools.sleep(250);

        // Opret indbetaling
        PO_Manuel_Indbetaling.btn_Opret.click();

        // Vent
        TO_Tools.sleep(3000);

        // Screenshot
        testContext.doScreenshot();

        // Vent
        TO_Tools.sleep(250);
    }

    /**
     * Funktion henter indbetalingskontrol ID'et genereret af din egen bruger
     * 
     * @param sIndbetalingskildeType
     *            = Angives i excelark. Se valgmuligheder der i
     *            TC_Opret_Indbetalingskontrol_ID
     * @param sIndbetalingskontrolStatus
     *            = Angives i excelark. Se valgmuligheder der i
     *            TC_Opret_Indbetalingskontrol_ID
     * @param element
     *            = Det element (soegeknap) som der trykkes paa for at finde
     *            kontrol ID'et
     * @return = returnerer indbetalingskontrol ID'et som string
     */
    public static String HentIndbetalingsKontrolID(TestContext testContext, String sIndbetalingskildeType,
            String sIndbetalingskontrolStatus, WebElement element) {
        PatientWebDriver driver = testContext.getDriver();

        // Gem oprindeligt vindue
        String parentWindow = driver.getWindowHandle();

        // Klik paa soegeknappen ud for Indbetalingskontrol ID tekstfeltet
        element.click();

        // Vent
        TO_Tools.sleep(1000);

        // Skift til nyt vindue
        String subWindow = null;
        Set<String> handles = driver.getWindowHandles(); // get all window
        // handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) {
            subWindow = iterator.next();
        }
        driver.switchTo().window(subWindow); // switch to popup window

        // Vent
        TO_Tools.sleep(1000);

        // Vaelg indbetalingskilde type i popup vinduet
        Select aSelect1 = new Select(
                PO_Opret_Indbetalingskontrol_ID.drp_Popup_IndbetalingskildeType.getWebElement(driver));
        aSelect1.selectByValue(sIndbetalingskildeType);

        // Vaelg indbetalingskontrol status i popup vinduet
        Select aSelect2 = new Select(
                PO_Opret_Indbetalingskontrol_ID.drp_Popup_IndbetalingskontrolStatus.getWebElement(driver));
        aSelect2.selectByValue(sIndbetalingskontrolStatus);

        // Vent
        TO_Tools.sleep(3000);
        // Tryk paa soeg
        // PO_Opret_Indbetalingskontrol_ID.pop_Soeg(driver).click();

        // Vent
        TO_Tools.sleep(3000);

        PO_Manuel_Indbetaling.btn_Soeg_IndbetalingskildeSoegning.click();
        // PO_Opret_Indbetalingskontrol_ID.pop_Soeg_Indbetalingskontrol_ID(driver).click();
        // Duplicate

        // Hent alle vaerdier ud af tabelkolonne for Bruger i en liste

        int rowCount = PO_Opret_Indbetalingskontrol_ID.pop_List(driver).size();

        Boolean found = false;

        String ID = null;

        String username = testContext.getPropertyProvider().getUsername();
        // Gaa alle vaerdier igennem i Bruger-kolonnen
        for (int i = 1; i <= rowCount; i++) {
            String sValue = null;
            sValue = PO_Opret_Indbetalingskontrol_ID.pop_Bruger(i).getText().trim();
            // Hvis Brugeren matcher brugerID'et paa brugeren der har lavet
            // kontrol ID'et, gaa videre.

            if (sValue.equalsIgnoreCase(username)) {
                ID = PO_Opret_Indbetalingskontrol_ID.pop_KontrolID(i).getText().trim();
                PO_Opret_Indbetalingskontrol_ID.pop_KontrolID(i).click();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Ingen indbetalingskontrol ID oprettet af " + username);
        }

        // Vent
        TO_Tools.sleep(250);

        // Gaa tilbage til hovedvindue
        driver.switchTo().window(parentWindow);

        return ID;
    }

}
