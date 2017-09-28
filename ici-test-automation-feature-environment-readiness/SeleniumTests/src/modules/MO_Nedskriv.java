package modules;

import java.text.ParseException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.testng.TestContext;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_Nedskriv;
import utils.IciSelExpectedConditions;
import utils.tools.TO_Tools;

public class MO_Nedskriv {

    /**
     * Funktionen nedskriver en fordring via regulering gennem konto-kontekst
     * menuen ned til et ønsket resterende beløb
     * 
     * @param testContext
     *            = testContext
     * @param interntHovedfordringsID
     *            = String med hovedfordringsID for den fordring der ønskes
     *            nedskrevet. Kan være identisk med fordringen selv hvis denne
     *            er en hovedfordring
     * @param interntFordringsID
     *            = String med fordringsID for den eksakte fordring som der
     *            ønskes nedskrevet
     * @param resterendeBeloeb
     *            = String med beløb, der skal være resterende på fordringen
     *            efter nedskrivning
     */
    public static void nedskrivFordring_Regulering(TestContext testContext, String interntHovedfordringsID,
            String interntFordringsID, String resterendeBeloeb) {
        // Gaa via konto kontekst menu til "Opret nedskrivning"
        goToNedskriv(testContext);

        // Indsæt begrundelse for nedskrivelse
        pickBegrundelseForNedskrivning(testContext, "Regulering");

        // Indsæt hovedfordringsID, note og fremsøg fordring
        generelUdfyldelse(testContext, interntHovedfordringsID);

        // Indsæt resterende beloeb efter nedskrivning
        indtastResterendeBeloeb(testContext, resterendeBeloeb);

        // Tryk på Gem
        klikGem(testContext);

        // TO_Tools.sleep(5000);
        //
        // PO_Nedskriv.btn_Create_Letter.click();
        //
        // testContext.waitForPageToBeLoaded();
        //
        // // Screenshot
        // testContext.doScreenshot();
    }

    /**
     * Funktionen nedskriver en fordring via indbetaling gennem konto-kontekst
     * menuen med et ønsket indbetalt beløb
     * 
     * @param testContext
     *            = testContext
     * @param psrmDato
     *            = Datoen i PSRM. Skal hentes i testcasen forud for via
     *            MO_Utilities.getPsrmDateTime
     * @param dageFraNu
     *            = Antal dage fra systemtiden, som virkningsdatoen skal sættes
     *            til
     * @param interntHovedfordringsID
     *            = String med hovedfordringsID for den fordring der ønskes
     *            nedskrevet. Kan være identisk med fordringen selv hvis denne
     *            er en hovedfordring
     * @param interntFordringsID
     *            = String med fordringsID for den eksakte fordring som der
     *            ønskes nedskrevet
     * @param nedskrivningsbeloeb
     *            = String med beløb, der skal være resterende på fordringen
     *            efter nedskrivning
     * @throws ParseException
     */
    public static void nedskrivFordring_Indbetaling(TestContext testContext, String psrmDato, int dageFraNu,
            String interntHovedfordringsID, String interntFordringsID, String nedskrivningsbeloeb)
            throws ParseException {
        // Gaa via konto kontekst menu til "Opret nedskrivning"
        goToNedskriv(testContext);

        // Indsæt begrundelse for nedskrivelse
        pickBegrundelseForNedskrivning(testContext, "Indbetaling");

        // Udfyld note, virkningsdato, fordring og nedskrivningsbeløb før der
        // trykkes gem
        udfyldelseIndbetalingOgModregning(testContext, psrmDato, dageFraNu, interntHovedfordringsID,
                nedskrivningsbeloeb);
    }

    /**
     * Funktionen nedskriver en fordring via modregning gennem konto-kontekst
     * menuen med et ønsket indbetalt beløb
     * 
     * @param testContext
     *            = testContext
     * @param psrmDato
     *            = Datoen i PSRM. Skal hentes i testcasen forud for via
     *            MO_Utilities.getPsrmDateTime
     * @param dageFraNu
     *            = Antal dage fra systemtiden, som virkningsdatoen skal sættes
     *            til
     * @param interntHovedfordringsID
     *            = String med hovedfordringsID for den fordring der ønskes
     *            nedskrevet. Kan være identisk med fordringen selv hvis denne
     *            er en hovedfordring
     * @param interntFordringsID
     *            = String med fordringsID for den eksakte fordring som der
     *            ønskes nedskrevet
     * @param nedskrivningsbeloeb
     *            = String med beløb, der skal være resterende på fordringen
     *            efter nedskrivning
     * @throws ParseException
     */
    public static void nedskrivFordring_Modregning(TestContext testContext, String psrmDato, int dageFraNu,
            String interntHovedfordringsID, String interntFordringsID, String nedskrivningsbeloeb) throws ParseException {
        // Gaa via konto kontekst menu til "Opret nedskrivning"
        goToNedskriv(testContext);

        // Indsæt begrundelse for nedskrivelse
        pickBegrundelseForNedskrivning(testContext, "Modregning");

        // Udfyld note, virkningsdato, fordring og nedskrivningsbeløb før der trykkes gem
        udfyldelseIndbetalingOgModregning(testContext, psrmDato, dageFraNu, interntHovedfordringsID, nedskrivningsbeloeb);
    }

    public static void generelUdfyldelse(TestContext testContext, String interntHovedfordringsID) {
        // Indsæt Hovedfordring
        indtastHovedfordring(interntHovedfordringsID);

        // Indsæt note for nedskrivning
        indtastNoteForNedskrivning();

        // Klik på søgeikonet for at finde en fordring
        klikSøgeikonFordring(testContext);
    }

    public static void udfyldelseIndbetalingOgModregning(TestContext testContext, String psrmDato, int dageFraNu,
            String interntHovedfordringsID, String nedskrivningsbeloeb) throws ParseException {
        // Indtast virkningsdato
        indtastVirkningsdato(psrmDato, dageFraNu);

        // Indsæt hovedfordringsID, note og fremsøg fordring
        generelUdfyldelse(testContext, interntHovedfordringsID);

        // Indsæt resterende beloeb efter nedskrivning
        indtastNedskrivningsbeloeb(testContext, nedskrivningsbeloeb);

        // Tryk på Gem
        klikGem(testContext);
    }

    public static void goToNedskriv(TestContext testContext) {
        // Gå gennem konto kontekst menuen til opret nedskriv
        MenuNavigator.kontoKontekst().opretNedskrivning();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();
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
    public static void pickBegrundelseForNedskrivning(TestContext testContext, String begrundelse) {
        testContext.waitForPageToBeLoaded();

        // Map alle mulighder
        HashMap<String, String> begrundelseForNedskrivning = new HashMap<String, String>();
        begrundelseForNedskrivning.put("Indbetaling", "PMT");
        begrundelseForNedskrivning.put("Modregning", "OFS");
        begrundelseForNedskrivning.put("Regulering", "RGL");

        PO_Nedskriv.drp_begrundelse.pick(begrundelseForNedskrivning.get(begrundelse));
    }

    public static void indtastHovedfordring(String interntHovedfordringsID) {
        // Indtast hovedfordringsID'et - skal være det interne ID.
        PO_Nedskriv.input_Hovedfordring.sendKeys(interntHovedfordringsID);
    }

    public static void indtastNoteForNedskrivning() {
        // Indtast note
        PO_Nedskriv.txt_note_for_nedskrivning.sendKeys("Test note for nedskrivning");
    }

    public static void indtastVirkningsdato(String psrmDato, int dageFraNu) throws ParseException {
        PO_Nedskriv.input_Virkningsdato.sendKeys(TO_Tools.getPsrmDatoPlus_DDMMYYYY(psrmDato, dageFraNu));
    }

    // TODO Lav så metoden kan vælge såfremt der er flere underfordringer
    public static void klikSøgeikonFordring(TestContext testContext) {
        // Klik på søgeikonet ud for Fordringsfeltet
        PO_Nedskriv.btn_Search.click();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();
    }

    public static void indtastNedskrivningsbeloeb(TestContext testContext, String nedskrivningsbeloeb) {
        PatientWebDriver driver = testContext.getDriver();

        // Indtast resterende beløb
        PO_Nedskriv.input_Nedskrivningsbeløb.sendKeys(nedskrivningsbeloeb);

        // Tryk "TAB" for at komme ud af tekstfeltet

        TO_Tools.trykKnap(Keys.TAB);

        //vent på at finalAmount_0 input bliver opdateret
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(IciSelExpectedConditions.ExpectedNotEmpty(By.id("finalAmount_0")));
    }

    public static void indtastResterendeBeloeb(TestContext testContext, String resterendeBeloeb) {
        PatientWebDriver driver = testContext.getDriver();

        // Indtast resterende beløb
        PO_Nedskriv.input_ResterendeBeloeb.sendKeys(resterendeBeloeb);

        // Tryk "TAB" for at komme ud af tekstfeltet
        TO_Tools.trykKnap(Keys.TAB);
    }

    public static void klikGem(TestContext testContext) {
        // Klik Gem
        PO_Nedskriv.btn_Gem.click();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();

        PO_Nedskriv.txt_SagsID.waitFor();
        System.out.println("SagsID: " + PO_Nedskriv.txt_SagsID.getText());
    }

}
