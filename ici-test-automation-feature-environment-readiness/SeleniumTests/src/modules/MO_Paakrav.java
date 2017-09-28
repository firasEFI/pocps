package modules;

import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Predicate;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import icisel.taxobjects.Fordring;
import icisel.testng.TestContext;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_Opret_Paakrav;
import utils.IciSelExpectedConditions;

public class MO_Paakrav {

    /**
     * Funktion opretter en påkravssag med diverse fordringer og påkravstype,
     * opretter velkomstbrev og sender påkravet.
     * 
     * @param tildelGebyr
     *            TODO
     * 
     */
    public static String opretPaakrav(TestContext testContext, PaakravstypeOption PaakravstypeOption,
            List<Fordring> fordringer, TypeAfBrevOption TypeAfBrevOption, Boolean tildelGebyr) {
        System.out.println("Opretter paakrav...");

        PatientWebDriver driver = testContext.getDriver();

        // Opret selve paakravssagen
        String sagsID = opretPaakravssag(testContext, PaakravstypeOption, fordringer);

        opretVelkomstbrev(testContext, TypeAfBrevOption, tildelGebyr);

        WebDriverWait wait = gaaTilBrev(testContext, driver);

        genererBrevUdkast(testContext);

        seBrevUdkast(testContext, driver);

        godkendBrevUdkast(testContext);

        sendBrevTilAogD(testContext, wait);

        // Returner SagsID som string
        return sagsID;

    }

    /**
     * Funktion opretter en påkravssag (men sender det ikke) med et vilkårligt
     * antal fordringer tilknyttet og af en given påkravstype.
     * 
     * Startbetingelse: Skyldneren er fremsøgt og dashboarded er tilgængeligt
     * Exit: Påkravssagens overblik Begrænsning:
     * 
     * @param testContext
     *            = testContext
     * @param PaakravstypeOption
     *            = PaakravstypeOption.[valgmuligheder]
     * @param fordringsIDer
     *            = liste af fordringsID der ønskes tilknyttet til påkravssagen
     * @return SagsID som String
     */
    public static String opretPaakravssag(TestContext testContext, PaakravstypeOption PaakravstypeOption,
            List<Fordring> fordringer) {
        // Gå til opret påkravssag i konto kontekst menuen
        goToOpretPaakravssag(testContext);

        // Håndter eventuel alert
        MO_Utilities.alertHandlerAccept(testContext);

        // Vælg påkravstype
        pickPaakravstype(testContext, PaakravstypeOption);

        // Vælg fordringer der skal inkluderes i påkravssagen
        for (Fordring fordring : fordringer) {
            vaelgFordring(testContext.getDriver(), fordring.getsFordringsID());
        }

        // Klik på "Opret"
        klikOpret(testContext);

        // Hent sagsID
        String sagsID = PO_Opret_Paakrav.txt_SagsID.getText();
        System.out.println("Sags ID: " + sagsID);
        return sagsID;
    }

    /**
     * Funktion tilføjer en anden aktør til en given påkravssag udpeget via
     * sagsID
     * 
     * Startbetingelse: Inde på påkravssagens overblik Exit: Den valgte
     * påkravssags overblik Begræsning:
     * 
     * @param testContext
     *            = testContext
     * @param sagsID
     *            = SagsID på sagen, hvortil der skal tilføjes en anden aktør
     * @param typeAfAndenAktoer
     *            = Typen af anden aktør der skal tilføjes
     * @param cprAndenAktoer
     *            = Cpr som string for den anden aktør
     * @param AndenAktoerOption
     *            = AndenAktoerOption."Typen af anden aktør der ønskes"
     * @return String med teksten for repræsentationsomfangsfeltet
     */
    public static String tilknytAndenAktoer(TestContext testContext, String sagsID,
            TypeAfAndenAktoerOption TypeAfAndenAktoerOption, String cprAndenAktoer,
            AndenAktoerOption AndenAktoerOption) {
        // På sagen, klik nu på linket til "Tilføj anden aktør" i bunden
        klikTilfoejAndenAktoer(testContext);

        // Vælg typen af anden aktør
        pickTypeAfAndenAktør(testContext, TypeAfAndenAktoerOption);

        // Klik "næste"
        klikNaeste(testContext);

        // Udfyld CPR
        indtastCpr(cprAndenAktoer);

        // Udfyld repraesentationsomfang
        String repraesentationsomfangTekst = "Test af repræsentationsomfangs-tekstfelt";
        indtastRepraesentationsomfang(repraesentationsomfangTekst);

        // Vælg type af anden aktør
        pickAndenAktoer(AndenAktoerOption);

        // Tryk på "Gem"
        klikGem(testContext, repraesentationsomfangTekst);

        return repraesentationsomfangTekst;
    }

    /**
     * Funktion starter inde på en given påkravssag og fjerner herfra en
     * tilføjet anden aktør ud fra kendskab til teksten i
     * repræsentationsomgfangsfeltet.
     * 
     * Startbetingelse: Inde på en given påkravssags overblik Exit: Samme
     * påkravssags overblik Begrænsning: Metoden vælger hvilken anden aktør der
     * skal fjernes ud fra teksten i repræsentationsomfangs-feltet. Hvis flere
     * af disse er identiske vil den vælge den første af disse.
     * 
     * @param testContext
     *            = testContext
     * @param repraesentationsomfangTekst
     *            = teksten i repræsentationsomfangsfeltet for den anden aktør
     *            der ønskes fjernet
     * @param BegrundelseForFjernelseAfRelationOption
     *            = BegrundelseForFjernelseAfRelationOption.[den option der
     *            ønskes]
     */
    public static void fjernAndenAktoer(TestContext testContext, String repraesentationsomfangTekst,
            BegrundelseForFjernelseAfRelationOption BegrundelseForFjernelseAfRelationOption) {
        // Klik på ikonet for at fjerne en anden aktør
        klikFjernRelation(testContext, repraesentationsomfangTekst);

        // Vælg begrundelse for fjernelse af relation
        pickBegrundelseForFjernelseAfRelation(BegrundelseForFjernelseAfRelationOption);

        // Indtast begrundelsesnote
        indtastBegrundelsesNote();

        // Klik på bekræft
        klikBekraeft(testContext);
    }

    private static Predicate<PageElement> untilWindowAvailable(final String title) {
        return new Predicate<PageElement>() {
            @Override
            public boolean apply(PageElement p) {
                if (p == null) {
                    throw new RuntimeException("PageElement is null");
                }

                return p.getText().toLowerCase(Locale.ENGLISH).contains(title.toLowerCase(Locale.ENGLISH));
            }
        };
    }

    public static void opretVelkomstbrev(TestContext testContext, TypeAfBrevOption TypeAfBrevOption,
            Boolean tildelGebyr) {
        String parentWindow = PO_Opret_Paakrav.btn_OpretVelkomstbrev.clickJSElementAndSwitchToNewWindow();

        // Vælg type af brev
        pickTypeAfBrev(testContext, TypeAfBrevOption);

        // Screenshot
        testContext.doScreenshot();

        // Tjek om valg af gebyr følger ønsket
        if (!PO_Opret_Paakrav.chkbox_Gebyr.isSelected() == tildelGebyr) {
            klikGebyr(testContext);
        }

        // Hvis gebyr fravælges, giv da note
        if (!PO_Opret_Paakrav.chkbox_Gebyr.isSelected()) {
            indtastAarsagTilGebyrfritagelse();
        }

        // Tryk paa OK
        klikOK(testContext, parentWindow);

        testContext.getDriver().switchTo().window(parentWindow);
    }

    public static void goToOpretPaakravssag(TestContext testContext) {
        // Gaa til "Opret paakrav" i konto kontekst menuen
        MenuNavigator.kontoKontekst().opretPaakravssag();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();

        synchronizeWizardTitle(testContext, "Opret påkravssag");
    }

    public static void synchronizeWizardTitle(TestContext testContext, String expectedWizardTitle) {
        PageElement wizardTitle = new PageElement(Frames.uiMap, By.className("oraPageTitle"));

        testContext.getDriver().pause()
                .until(IciSelExpectedConditions.elementContainsTextIgnoreCase(wizardTitle, expectedWizardTitle));
    }

    public static void pickInddrivelsesskridt() {
        // Vaelg inddrivelsesskridt
        PO_Opret_Paakrav.drp_Inddrivelsesskridt.pick("DEML");
    }

    public static void pickPaakravstype(TestContext testContext, PaakravstypeOption option) {
        // Vælg hvilken type af anden aktør der skal tilknyttes ud fra den
        // synlige tekst i PSRM
        testContext.waitForPageToBeLoaded();

        PO_Opret_Paakrav.drp_Paakravstype.pick(option.value);

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();
    }

    public static enum PaakravstypeOption {
        Påkrav_med_partshøring("FDP"),
        Påkrav_uden_partshøring("FDM");
        String value;

        private PaakravstypeOption(String value) {
            this.value = value;
        }
    }

    /**
     * Funktion vælger hvilken fordring der skal inkluderes i påkravssagen
     * 
     * @param fordringsID
     *            = indsæt fordringsID'et på den ønskede fordring som en string
     */
    private static void vaelgFordring(WebDriver driver, String fordringsId) {
        try {
            List<WebElement> fordringRows = PO_Opret_Paakrav.table_Fordringer(driver);

            System.out.println("Søger fordring med id: " + fordringsId);

            // click all matching the fordringsId - first iterate rows
            for (WebElement row : fordringRows) {
                if (row.getText().contains(fordringsId)) {
                    List<WebElement> tds = row.findElements(By.xpath("td"));
                    for (WebElement td : tds) {
                        // should just be one checkbox per line, but to prevent
                        // element not found exception we return a list
                        List<WebElement> checkboxes = td.findElements(PO_Opret_Paakrav.chkbox_fordring.by);
                        for (WebElement checkbox : checkboxes) {
                            System.out.println("Fordring fundet, markerer checkbox...");
                            checkbox.click();
                            continue;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Fordring med fordring Id: " + fordringsId + " er ikke på skyldner!");
            e.printStackTrace();
        }
    }

    public static void klikOpret(TestContext testContext) {
        // Screenshot
        testContext.doScreenshot();

        // Klik paa opret
        PO_Opret_Paakrav.btn_Opret.click();

        // Vent på at side er loaded
        testContext.waitForPageToBeLoaded();
        MO_Wait.waitForRightMenu();
    }

    public static void pickTypeAfBrev(TestContext testContext, TypeAfBrevOption option) {
        // Vælg hvilken type af anden aktør der skal tilknyttes ud fra den
        // synlige tekst i PSRM
        PO_Opret_Paakrav.drp_TypeAfBrev.pick(option.value);

        // Vent på at siden er loaded
        try {
            testContext.waitForPageToBeLoaded();
        } catch (UnhandledAlertException e) {
            // sometimes we get a alert box with text: "Letters have already
            // been sent to the debtor.): Letters have already been sent to the
            // debtor." - just ignore it
            // System.out.println("Got UnhandledAlertException - proceed
            // anyway");
        }
    }

    public static enum TypeAfBrevOption {
        Påkravsbrev_uden_partshøring("IND0704AFD01"),
        Velkomstbrev_uden_partshøring("IND0705AFD02");
        String value;

        private TypeAfBrevOption(String value) {
            this.value = value;
        }
    }

    public static void klikGebyr(TestContext testContext) {
        // Klik på checkboxen for valg af gebyr
        PO_Opret_Paakrav.chkbox_Gebyr.click();

        // Vent til siden er loaded
        testContext.waitForPageToBeLoaded();
    }

    public static void indtastAarsagTilGebyrfritagelse() {
        PO_Opret_Paakrav.input_AarsagTilGebyrfritagelse.sendKeys("Test af årsagsfelt");
    }

    public static void klikOK(TestContext testContext, String parentWindow) {
        PatientWebDriver driver = testContext.getDriver();

        // Klik på checkboxen for valg af gebyr
        PO_Opret_Paakrav.btn_OK.click();

        // Gaa tilbage til hovedvindue
        driver.switchTo().window(parentWindow);

        // Vent på at siden er helt loaded
        MO_Utilities.ventTilFeltIndeholderTekst(PO_Opret_Paakrav.txt_Titellinje, "Påkrav oprettet");
    }

    public static WebDriverWait gaaTilBrev(TestContext testContext, PatientWebDriver driver) {
        // Tryk paa linket ved "Paakrav"

        PO_Opret_Paakrav.lnk_Paakrav_Brev.click();

        // Screenshot
        testContext.doScreenshot();

        PO_Opret_Paakrav.text_primaer_header.waitUntilTextContains("Under behandling");

        testContext.waitForPageToBeLoaded();
        PO_Opret_Paakrav.btn_Rediger.clickAttemptUsingJS();

        Predicate<WebDriver> test = new Predicate<WebDriver>() {
            Select selectModtager = new Select(PO_Opret_Paakrav.drp_Modtager);
            Select selectAddresse = new Select(PO_Opret_Paakrav.drp_Addresse);

            @Override
            public boolean apply(WebDriver arg0) {
                return selectModtager.getOptions().size() > 1 && selectAddresse.getOptions().size() > 1;
            }
        };

        WebDriverWait wait = (new WebDriverWait(driver, driver.getTimeout() / 1000, driver.getPollPeriod()));
        wait.until(test);
        PO_Opret_Paakrav.chkbox_Tilfoej_OCR_linje.click();

        // Screenshot
        testContext.doScreenshot();

        // Klik paa Gem
        PO_Opret_Paakrav.btn_Gem.click();
        return wait;
    }

    public static void sendBrevTilAogD(TestContext testContext, WebDriverWait wait) {
        // Klik paa Send til A & D
        wait.until(new Predicate<WebDriver>() {

            @Override
            public boolean apply(WebDriver arg0) {
                return PO_Opret_Paakrav.btn_SendTilAOgD.isEnabled();
            }

        });
        PO_Opret_Paakrav.btn_SendTilAOgD.clickAttemptUsingJS();

        // Screenshot
        testContext.doScreenshot();
    }

    public static void godkendBrevUdkast(TestContext testContext) {
        // Klik paa Godkend udkast
        PO_Opret_Paakrav.btn_GodkendUdkast.clickAttemptUsingJS();

        // Screenshot
        testContext.doScreenshot();
    }

    public static void genererBrevUdkast(TestContext testContext) {
        // Vent
        PO_Opret_Paakrav.btn_GenererUdkast.waitFor();

        // Screenshot
        testContext.doScreenshot();

        // Klik paa Generer Udkast
        PO_Opret_Paakrav.btn_GenererUdkast.clickAttemptUsingJS();

        PO_Opret_Paakrav.text_primaer_header.waitUntilTextContains("Udkast genereret");
        System.out.println("Udkast genereret");
    }

    public static void seBrevUdkast(TestContext testContext, PatientWebDriver driver) {
        // Klik paa "Se udkast"
        String parentWindow = PO_Opret_Paakrav.btn_SeUdkast.clickJSElementAndSwitchToNewWindow();

        // just wait for page to be loaded (weird error)
        driver.findElementPatiently(By.xpath("//body"));

        // Screenshot
        testContext.doScreenshot();

        // Luk nyt vindue
        driver.close();

        // Gaa tilbage til hovedvindue
        driver.switchTo().window(parentWindow);
    }

    public static void klikTilfoejAndenAktoer(TestContext testContext) {
        // Klik på linket med teksten "Tilføj anden aktør"
        PO_Opret_Paakrav.lnk_TilfoejAndenAktoer.clickAttemptUsingJS();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();
    }

    public static void pickTypeAfAndenAktør(TestContext testContext, TypeAfAndenAktoerOption option) {
        // Vælg hvilken type af anden aktør der skal tilknyttes ud fra den
        // synlige tekst i PSRM
        PO_Opret_Paakrav.drp_TypeAfAndenAktoer.pick(option.value);

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();
    }

    public static enum TypeAfAndenAktoerOption {
        Anden_aktør_uden_ID("CNID"),
        Eksisterende_anden_aktoer_med_system_ID("EXIS"),
        Firma_med_CVR("CWID"),
        Part_med_CPR("PWID");
        String value;

        private TypeAfAndenAktoerOption(String value) {
            this.value = value;
        }
    }

    public static void klikNaeste(TestContext testContext) {
        // Klik på knappen Næste
        PO_Opret_Paakrav.btn_Naeste_typeAfAndenAktoer.click();

        // Vent på at siden loader
        testContext.waitForPageToBeLoaded();
    }

    public static void indtastCpr(String cpr) {
        // Indtast skyldnerens Cpr nummer
        PO_Opret_Paakrav.input_Cpr.sendKeys(cpr);
    }

    public static void indtastRepraesentationsomfang(String repraesentationsomfangTekst) {
        // Indtast note for repræsentationsomfang
        PO_Opret_Paakrav.input_Repraesentationsomfang.sendKeys(repraesentationsomfangTekst);
    }

    public static void pickAndenAktoer(AndenAktoerOption option) {
        PO_Opret_Paakrav.drp_AndenAktoer.pick(option.value);
    }

    public static enum AndenAktoerOption {
        Advokat("ATTO"),
        Anden_aktør("AGEN"),
        Revisor("ACCT"),
        Værge("GUAR");
        String value;

        private AndenAktoerOption(String value) {
            this.value = value;
        }
    }

    public static void klikGem(TestContext testContext, String repraesentationsomfangTekst) {
        // Klik på "Gem"
        PO_Opret_Paakrav.btn_GemAndenAktoer.click();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();
        MO_Utilities.ventTilFeltIndeholderTekst(
                PO_Opret_Paakrav.txt_Repraesentationsomfang_Sagsoverblik(repraesentationsomfangTekst),
                repraesentationsomfangTekst);
    }

    /**
     * Funktion fjerner en tilføjet anden aktør. Denne metode kan kun håndtere
     * at der er tilføjet præcis 1 anden aktør.
     * 
     * @param repraesentationsomfangTekst
     */
    public static void klikFjernRelation(TestContext testContext, String repraesentationsomfangTekst) {
        // Klik på ikonet for at fjerne en anden aktør (relation) i bunden af
        // påkravssagen
        PO_Opret_Paakrav.btn_FjernRelation(repraesentationsomfangTekst).click();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();
    }

    public static void pickBegrundelseForFjernelseAfRelation(BegrundelseForFjernelseAfRelationOption option) {
        // Vælg begrundelse for fjernelse af relationen
        PO_Opret_Paakrav.drp_BegrundelseForFjernelseAfRelation.pick(option.value);
    }

    public static enum BegrundelseForFjernelseAfRelationOption {
        Andet("OTHE"),
        Er_ikke_laengere_repraesentativ("NORE");
        String value;

        private BegrundelseForFjernelseAfRelationOption(String value) {
            this.value = value;
        }
    }

    public static void indtastBegrundelsesNote() {
        // Indtast begrundelsesnote
        PO_Opret_Paakrav.input_Begrundelsesnote.sendKeys("Test af begrundelsesnote");
    }

    public static void klikBekraeft(TestContext testContext) {
        // Klik på "Bekræft"
        PO_Opret_Paakrav.btn_Bekraeft.click();

        // Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();
    }

    public static String getFordringsDetaljerFraSagen(String fordringsID) {
        String fordringsTekst = PO_Opret_Paakrav.lnk_TilknyttetFordring(fordringsID).getText();

        return fordringsTekst;
    }

    public static void assertFordringsSaldoFremgaarAfSagsoverblik(String fordringsID) {
        String fordringsTekst = getFordringsDetaljerFraSagen(fordringsID);
        String endelse = fordringsTekst.substring(fordringsTekst.length() - 3);
        Assert.assertEquals("kr.", endelse);
    }

}
