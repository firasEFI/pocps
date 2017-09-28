package modules;

import java.text.ParseException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.Assert;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import icisel.testng.TestContext;
import pageobjects.generic.PO_Tilbagesend;
import utils.tools.TO_Tools;

public class MO_Tilbagesend {

    /**
     * Funktion tilbagesender fordring med årsagskoden fejl
     * 
     * @param testContext
     *            = this
     * @param fordringsID
     *            = fordringsID på fordring der ønskes tilbagesendt
     */
    public static void tilbagesendFordring_Fejl(TestContext testContext, String fordringsID) {
        // Gaa via konto kontekst menu til Tilbagesend fordring
        goToTilbagesendFordring(testContext);

        // Vaelg begrundelse for tilbagesendelse
        pickBegrundelseForTilbagesendelse("04");

        // Indtast fordringsID, indtast note, klik gem og siden afslut
        tilbagesendFordringSlut(testContext, fordringsID);
    }

    /**
     * Funktion tilbagesender fordring med årsagskoden afdragsordning
     * 
     * @param testContext
     *            = this
     * @param fordringsID
     *            = fordringsID på fordring der ønskes tilbagesendt
     * @param dageFraNu
     *            = Antallet af dage fra nu, som virkningsdatoen skal sættes til
     * @param psrmDato = String med datoen i PSRM
     * @throws ParseException 
     */
    public static void tilbagesendFordring_Afdragsordning(TestContext testContext, String fordringsID, int dageFraNu, String psrmDato) throws ParseException {
        goToTilbagesendFordring(testContext);

        // Vaelg begrundelse for tilbagesendelse
        pickBegrundelseForTilbagesendelse("01");

        // Indsæt virkningsdato
        indtastVirkningsdato(dageFraNu, psrmDato);

        // Indtast fordringsID, indtast note, klik gem og siden afslut
        tilbagesendFordringSlut(testContext, fordringsID);
    }

    /**
     * Funktion tilbagesender fordring med årsagskoden henstand
     * 
     * @param testContext
     *            = this
     * @param fordringsID
     *            = fordringsID på fordring der ønskes tilbagesendt
     * @param dageFraNu
     *            = Antallet af dage fra nu, som virkningsdatoen skal sættes til
     * @param psrmDato = String med datoen i PSRM
     * @throws ParseException 
     */
    public static void tilbagesendFordring_Henstand(TestContext testContext, String fordringsID, int dageFraNu, String psrmDato) throws ParseException {
        goToTilbagesendFordring(testContext);

        // Vaelg begrundelse for tilbagesendelse
        pickBegrundelseForTilbagesendelse("06");

        // Indsæt virkningsdato
        indtastVirkningsdato(dageFraNu, psrmDato);

        // Indtast fordringsID, indtast note, klik gem og siden afslut
        tilbagesendFordringSlut(testContext, fordringsID);
    }

    /**
     * Funktion tilbagesender fordring med årsagskoden "hæftelse forkert"
     * 
     * @param testContext
     *            = this
     * @param fordringsID
     *            = fordringsID på fordring der ønskes tilbagesendt
     */
    public static void tilbagesendFordring_HæftelseForkert(TestContext testContext, String fordringsID) {
        goToTilbagesendFordring(testContext);

        // Vaelg begrundelse for tilbagesendelse
        pickBegrundelseForTilbagesendelse("03");

        // Indtast fordringsID, indtast note, klik gem og siden afslut
        tilbagesendFordringSlut(testContext, fordringsID);
    }

    /**
     * Funktion tilbagesender fordring med årsagskoden "klage over fordring"
     * 
     * @param testContext
     *            = this
     * @param fordringsID
     *            = fordringsID på fordring der ønskes tilbagesendt
     * @param dageFraNu
     *            = Antallet af dage fra nu, som virkningsdatoen skal sættes til
     * @param psrmDato = String med datoen i PSRM
     * @throws ParseException 
     */
    public static void tilbagesendFordring_KlageOverFordring(TestContext testContext, String fordringsID,
            int dageFraNu, String psrmDato) throws ParseException {
        goToTilbagesendFordring(testContext);

        // Vaelg begrundelse for tilbagesendelse
        pickBegrundelseForTilbagesendelse("05");

        // Indsæt virkningsdato
        indtastVirkningsdato(dageFraNu, psrmDato);

        // Indtast fordringsID, indtast note, klik gem og siden afslut
        tilbagesendFordringSlut(testContext, fordringsID);
    }

    /**
     * Funktion tilbagesender fordring med årsagskoden modregning
     * 
     * @param testContext
     *            = this
     * @param fordringsID
     *            = fordringsID på fordring der ønskes tilbagesendt
     * @param dageFraNu
     *            = Antallet af dage fra nu, som virkningsdatoen skal sættes til
     * @param tilbagesendRelateredeFordringer
     *            = Muligheder: "Ja" eller "Nej"
     * @param psrmDato = String med datoen i PSRM
     * @throws ParseException 
     */
    public static void tilbagesendFordring_Modregning(TestContext testContext, String fordringsID, int dageFraNu,
            String tilbagesendRelateredeFordringer, String psrmDato) throws ParseException {
        goToTilbagesendFordring(testContext);

        // Vaelg begrundelse for tilbagesendelse
        pickBegrundelseForTilbagesendelse("06");

        // Indsæt virkningsdato
        indtastVirkningsdato(dageFraNu, psrmDato);

        // Vælg om relaterede fordringer skal sendes tilbage
        pickTilbagesendRelateredeFordringer(tilbagesendRelateredeFordringer);

        // Indtast fordringsID, indtast note, klik gem og siden afslut
        tilbagesendFordringSlut(testContext, fordringsID);
    }

    public static void tilbagesendFordringSlut(TestContext testContext, String fordringsID) {
        // Indtast fordringsID
        indtastFordringsID(fordringsID);

        // Indsæt note
        indtastNoteForTilbagesendelse();

        // Tryk paa Gem
        klikGem(testContext);

        // Screenshot
        testContext.doScreenshot();

        // Tryk paa afslut
        klikAfslut(testContext);

        // Screenshot
        testContext.doScreenshot();
    }

    public static void goToTilbagesendFordring(TestContext testContext) {
        MenuNavigator.kontoKontekst().tilbagesendFordring();

        testContext.waitForPageToBeLoaded();
    }

    /**
     * @param begrundelse
     *            Muligheder: "01" = Afdragsordning, "02" = Modregning, "03" =
     *            Hæftelse forkert, "04" = Fejl, "05" = Klage over fordring,
     *            "06" = Henstand
     */
    public static void pickBegrundelseForTilbagesendelse(String begrundelse) {
        PO_Tilbagesend.drp_Begrundelse.pick(begrundelse);
    }

    public static void indtastVirkningsdato(int dageFraNu, String psrmDato) throws ParseException {
        // Indsæt virkningsdato
        PO_Tilbagesend.input_Virkningsdato.sendKeys(TO_Tools.getPsrmDatoPlus_DDMMYYYY(psrmDato, dageFraNu));
    }

    public static void indtastFordringsID(String fordringsID) {
        PO_Tilbagesend.input_FordringsID.sendKeys(fordringsID);
    }

    public static void indtastNoteForTilbagesendelse() {
        PO_Tilbagesend.input_Note.sendKeys("Test note for tilbagesendelse");
    }

    public static void pickTilbagesendRelateredeFordringer(String svar) {
        // Map alle mulighder
        HashMap<String, String> muligheder = new HashMap<String, String>();
        muligheder.put("Ja", "Y");
        muligheder.put("Nej", "N");

        PO_Tilbagesend.drp_TilbagesendRelateredeFordringer.pick(muligheder.get(svar));

    }

    public static void klikGem(TestContext testContext) {
        // Vent
        PO_Tilbagesend.btn_Gem.waitFor();

        // Screenshot
        testContext.doScreenshot();

        // Tryk paa Gem
        PO_Tilbagesend.btn_Gem.click();

        // Vent på at ny side (formular) indlæses
        testContext.waitForPageToBeLoaded();
    }

    public static void klikAfslut(TestContext testContext) {
        // Vent
        PO_Tilbagesend.btn_Afslut.waitFor();

        // Screenshot
        testContext.doScreenshot();

        PO_Tilbagesend.btn_Afslut.click();

        // Vent på at ny side (formular) indlæses
        testContext.waitForPageToBeLoaded();
        
        // wait for text "pending" in span
        MO_Utilities.ventTilFeltIndeholderTekst(new PageElement(Frames.zoneMapFrame_2, By.xpath("//span[@id='boStatus']")), "Gennemført");
    }

}
