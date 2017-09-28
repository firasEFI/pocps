package modules;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.testng.TestContext;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_OCR;
import utils.tools.TO_Tools;

public class MO_OcrLinje {
    /**
     * Funktion opretter en OCR linje paa skyldner, enten direkte til konto, paa
     * fordring eller paa sag
     * 
     * @param testContext
     *            = testContext
     * @param psrmDato
     *            = datoen i psrm, skal gives ud fra getPsrmDatoTime i starten
     *            af flowet
     * @param fordringsIDer
     *            = String[] liste af fordringsIDer. Skriv på formen "String[]
     *            fordringsListe = {fordringsID-1, fordringsID-2};"
     * @param dageFraNu
     *            = Antal dage, betalingsfristen skal være fra systemets dags
     *            dato
     * @return = OCR identifikator som string
     * @throws ParseException
     */
    public static String OpretOcrFordring(TestContext testContext, String psrmDato, String[] fordringsIDer,
            int dageFraNu) throws ParseException {
        // Gå til Opret OCR linje, indsæt betalingsfrist og vælg at inkludere en
        // fordring i OCR-linjen
        opretOcrStart(testContext, psrmDato, dageFraNu, "Fordring");

        // Vælg fordringer i listen der skal tilknyttes til OCR linjen
        for (String fordringsID : fordringsIDer) {
            vaelgFordring(fordringsID);
        }

        // Klik gem og hent dernæst OCR-linjens identifier
        String OCRNummer = opretOcrSlut(testContext);

        return OCRNummer;
    }

    /**
     * Funktion opretter en OCR-linje på en skyldners konto
     * 
     * @param testContext
     *            = this
     * @param psrmDato
     *            = datoen i psrm, skal gives ud fra getPsrmDatoTime i starten
     *            af flowet
     * @param dageFraNu
     *            = Antal dage, betalingsfristen skal være fra dags dato.
     * @return = OCR-linjens identifier
     * @throws ParseException
     */
    public static String OpretOcrKonto(TestContext testContext, String psrmDato, int dageFraNu) throws ParseException {
        // Gå til Opret OCR linje, indsæt betalingsfrist og vælg at inkludere en
        // fordring i OCR-linjen
        opretOcrStart(testContext, psrmDato, dageFraNu, "Konto");

        // Klik gem og hent dernæst OCR-linjens identifier
        String OCRNummer = opretOcrSlut(testContext);

        return OCRNummer;
    }

    /**
     * Funktion opretter en OCR-linje på en defineret sag på en skyldner
     * 
     * @param testContext
     *            = this
     * @param psrmDato
     *            = datoen i psrm, skal gives ud fra getPsrmDatoTime i starten
     *            af flowet
     * @param sagsID
     *            = SagsID'et på sagen OCR-linjen skal knyttes til
     * @param dageFraNu
     *            = Antal dage, betalingsfristen skal være fra dags dato.
     * @return = OCR-linjens identifier
     * @throws ParseException
     */
    public static String OpretOcrSag(TestContext testContext, String psrmDato, String sagsID, int dageFraNu)
            throws ParseException {
        // Gå til Opret OCR linje, indsæt betalingsfrist og vælg at inkludere en
        // fordring i OCR-linjen
        opretOcrStart(testContext, psrmDato, dageFraNu, "Sag");

        // vaelgSag(testContext, sagsID);
        indtastSagsID(testContext, sagsID);

        // Klik gem og hent dernæst OCR-linjens identifier
        String OCRNummer = opretOcrSlut(testContext);

        return OCRNummer;
    }

    private static void opretOcrStart(TestContext testContext, String psrmDato, int dageFraNu, String inkluder)
            throws ParseException {
        // Gaa via konto kontest menu til Opret OCR linje
        goToOpretOcrLinje(testContext);

        // Indsaet betalingsfrist
        indtastBetalingsfrist(psrmDato, dageFraNu);

        // Vælg af OCR-linjen skal tilknyttes til en fordring
        pickInkluder(inkluder);
    }

    private static String opretOcrSlut(TestContext testContext) {
        // Klik på gem
        klikGem(testContext);

        // Find og gem OCR nummer
        String OCRNummer = getOcrIdentifier(testContext);

        return OCRNummer;
    }

    private static void goToOpretOcrLinje(TestContext testContext) {
        MenuNavigator.kontoKontekst().opretOcrLinje();

        testContext.waitForPageToBeLoaded();
    }

    private static void indtastBetalingsfrist(String psrmDato, int dageFraNu) throws ParseException {
        // Indsaet dato for betalingsfrist ud fra dags dato plus @dageFraNu
        PO_OCR.txt_Betalingsfrist.sendKeys(TO_Tools.getPsrmDatoPlus_DDMMYYYY(psrmDato, dageFraNu));
    }

    private static void pickInkluder(String inkluder) {
        // Map alle mulighder
        HashMap<String, String> inkluderMap = new HashMap<String, String>();
        inkluderMap.put("Fordring", "OBLG");
        inkluderMap.put("Konto", "ACCT");
        inkluderMap.put("Sag", "CASE");
        inkluderMap.put("", "");

        PO_OCR.drp_Inkluder.pick(inkluderMap.get(inkluder));
    }

    private static List<WebElement> vaelgFordringListe(TestContext testContext) {
        PatientWebDriver driver = testContext.getDriver();
        // Lav liste over fordringer
        List<WebElement> fordringer = PO_OCR.chkbox_Fordring(driver);

        return fordringer;
    }

    private static void vaelgFordring(String fordringsID) {

        try {
            PO_OCR.txt_Fordringstekst(fordringsID).click();
        } catch (Exception e) {
            System.out.println("Fordring med fordringsID " + fordringsID + " er ikke på skyldner!");
        }
    }

    private static void vaelgAlleFordringer(TestContext testContext, String fordringsID,
            List<WebElement> fordringsListe) {
        // Vælg alle fordringer på listen
        for (int i = 0; i < fordringsListe.size(); i++)
            fordringsListe.get(i).click();
    }

    private static void vaelgSag(TestContext testContext, String sagsID) {
        // Klik paa soegeikon ud for Sags ID
        MO_Utilities.VaelgSag(testContext, PO_OCR.btn_SagsIDSoeg, sagsID);

        // OBSERVATION: Hvis steps direkte op til er skyldners 360 grader,
        // fordringsfanen og vis alle fordringer + vis 0 fordringer, saa virker
        // sagssoegningen ikke foerste gang.
        if (PO_OCR.input_SagsID.getText().isEmpty())
            MO_Utilities.VaelgSag(testContext, PO_OCR.btn_SagsIDSoeg, sagsID);
    }

    private static void indtastSagsID(TestContext testContext, String sagsID) {
        PatientWebDriver driver = testContext.getDriver();

        // Indtast sagsID'et i sagsID feltet
        PO_OCR.input_SagsID.sendKeys(sagsID);

        // Tryk TAB for at siden registrerer sagsID'et
        TO_Tools.trykKnap(Keys.TAB);
    }

    private static void klikGem(TestContext testContext) {
        // Vent på at gem er klar
        PO_OCR.btn_Gem.waitFor();

        // Screenshot
        testContext.doScreenshot();

        // Tryk paa Gem
        PO_OCR.btn_Gem.click();

        // Vent på at ny side indlæses
        testContext.waitForPageToBeLoaded();
    }

    private static String getOcrIdentifier(TestContext testContext) {
        // Screenshot
        testContext.doScreenshot();

        // Gem og returner OCR linje
        String OCRNummer = PO_OCR.txt_OCRLinje.getText();
        System.out.println("OCR linje: " + OCRNummer);

        return OCRNummer;
    }
}
