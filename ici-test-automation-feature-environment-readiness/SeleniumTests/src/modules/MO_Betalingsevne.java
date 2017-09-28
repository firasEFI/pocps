package modules;

import java.text.ParseException;

import org.openqa.selenium.Keys;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.testng.TestContext;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_Betalingsevne;
import utils.tools.TO_Tools;

public class MO_Betalingsevne {

    /**
     * Funktionen beregner skyldners betalingsevne efter budget
     * 
     * @param testContext = this
     * @param betalingsevneBudget = Beløb for skyldners betalingsevne efter budget
     * @param betalingsevneTabeltraek = Beløb for skyldners betalingsevne efter tabeltræk
     * @param psrmDato = String med datoen i PSRM
     * @throws ParseException 
     */
    public static void beregnBetalingsevne_Budget(TestContext testContext, String betalingsevneBudget,
            String betalingsevneTabeltraek, String psrmDato) throws ParseException {
        gaaTilFormular_BeregnBetalingsevne(testContext);

        udfyldFormular_Budget_BeregnBetalingsevne(testContext, betalingsevneBudget, betalingsevneTabeltraek, psrmDato);

        testContext.doScreenshot();
    }

    /**
     * Funktion beregner betalingsevne for kunden ud fra oplysninger i excelark.
     * Kraever at en person er fremsoegt og valgt.
     * 
     * @param driver
     *            = driver
     * @param sBetalingsevne
     *            = Angives i excelark. Se valgmuligheder der i
     *            H_Betalingsevne_Afdrag
     * @param sBeloebBudget
     *            = Angives i excelark. Se valgmuligheder der i
     *            H_Betalingsevne_Afdrag
     * @param sBeloebTabel
     *            = Angives i excelark hvis inddrivelsesskridt er tabeltraek. Se
     *            valgmuligheder der i H_Betalingsevne_Afdrag
     * @param sInddrivelsesskridt
     *            = Angives i excelark. Se valgmuligheder der i
     *            H_Betalingsevne_Afdrag
     * @param sBeregnetBetalingsevneDato
     * @param sModtagelsesDatoForBudget
     * @param sGyldigFra
     * @param sGyldigTil
     */
    public static void beregnBetalingsevne_Budget(TestContext testContext, String betalingsevneBudget,
            String betalingsevneTabeltraek, String beregnetBetalingsevneDato, String modtagelsesdatoForBudget,
            String gyldighedsperiodeFra, String gyldighedsperiodeTil) {
        gaaTilFormular_BeregnBetalingsevne(testContext);

        udfyldFormular_Budget_BeregnBetalingsevne(testContext, betalingsevneBudget, betalingsevneTabeltraek,
                beregnetBetalingsevneDato, modtagelsesdatoForBudget, gyldighedsperiodeFra, gyldighedsperiodeTil);
    }

    public static void beregnBetalingsevne_Tabeltraek(TestContext testContext,
            String betalingsevneTabeltraek, String psrmDato) throws ParseException {
        gaaTilFormular_BeregnBetalingsevne(testContext);

        udfyldFormular_Tabeltraek_BeregnBetalingsevne(testContext, betalingsevneTabeltraek, psrmDato);

        testContext.doScreenshot();
    }
    
    /**
     * IU-236 - Registrering af beregnet betalingsevne - tabeltræk
     * 
     * Funktion går via partsmenuen til tilføjelse af betalingsevne, hvor der oprettes en 
     * betalingsevne på baggrund af tabeltræk.
     * 
     * Entry: Skyldner er fremsøgt og dashboarded er tilgængeligt
     * Exit: Sagsbehandlingsoversigt for den oprettede betalingsevne
     * 
     * @param testContext = testContext
     * @param betalingsevneTabeltraek = String med beløb for betalingsevne baseret på tabeltræk
     * @param psrmDato = String med PSRMs dato
     * @param dageFraNu = Int med dage fra PSRMs dato som ønskes lagt til datoen.
     * @throws ParseException
     */
    public static void beregnBetalingsevne_Tabeltraek(TestContext testContext,
            String betalingsevneTabeltraek, String psrmDato, int dageFraNu) throws ParseException {
        gaaTilFormular_BeregnBetalingsevne(testContext);

        udfyldFormular_Tabeltraek_BeregnBetalingsevne(testContext, betalingsevneTabeltraek, psrmDato, dageFraNu);

        testContext.doScreenshot();
    }
    
    /**
     * Funktion tilgår skærmen for beregning af betalingsevnen via menuen for
     * tilføjelse af betalingsevne. Startbetingelse: Skyldner er fremsøgt og
     * valgt
     */
    public static void gaaTilFormular_BeregnBetalingsevne(TestContext testContext) {
        // Gå til Tilføj betalingsevne via part kontekst menuen i dashboarded
        goToTilfoejBetalingsevne(testContext);

        // Vaelg registrering af betalingsevne
        pickBeregnetBetalingsevne();

        // Klik på OK
        klikOK(testContext);
    }

    /**
     * Funktion tilgår skærmen for grundlag for betalingsevne via menuen for
     * tilføjelse af betalingsevne. Startbetingelse: Skyldner er fremsøgt og
     * valgt
     */
    private static void gaaTilFormular_GrundlagForBetalingsevne(TestContext testContext) {
        // Gå til Tilføj betalingsevne via part kontekst menuen i dashboarded
        goToTilfoejBetalingsevne(testContext);

        // Vaelg registrering af betalingsevne
        pickGrundlagForBetalingsevne();

        // Klik på OK
        klikOK(testContext);
    }

    /**
     * Funktionen udfylder formularen for beregning af betalingsevne med Budget
     * som anvendt inddrivelsesskridt med de angivne værdier
     * 
     * @param driver
     * @param betalingsevneBudget
     *            = Månedligt betalingsevne baseret på budget - OPTIONAL (kan
     *            sættes til "")
     * @param betalingsevneTabeltraek
     *            = Månedligt betalingsevne baseret på tabeltræk
     * @param beregnetBetalingsevneDato
     *            = Beregnet betalingsevne dato
     * @param modtagelsesdatoForBudget
     *            = Modtagelsesdato for budget
     * @param gyldighedsperiodeFra
     *            = Budgettets gyldighedsperiode fra
     * @param gyldighedsperiodeTil
     *            = Budgettets gyldighedsperiode til
     * @return
     */
    public static String udfyldFormular_Budget_BeregnBetalingsevne(TestContext testContext, String betalingsevneBudget,
            String betalingsevneTabeltraek, String beregnetBetalingsevneDato, String modtagelsesdatoForBudget,
            String gyldighedsperiodeFra, String gyldighedsperiodeTil) {

        // Indtast månedlig betalingsevne baseret på tabeltræk
        indtastBetalingsevneBudget(betalingsevneBudget);

        // Indtast månedlig betalingsevne baseret på tabeltræk
        indtastBetalingsevneTabeltraek(betalingsevneTabeltraek);

        // Vaelg Budget som anvendt inddrivelsesskridt
        pickBudget();

        // Indtast datoer
        indtastBeregnetBetalingsevneDato(beregnetBetalingsevneDato);
        indtastModtagelsesdatoForBudget(testContext, modtagelsesdatoForBudget);
        indtastGyldighedsperiodeFra(testContext, gyldighedsperiodeFra);
        indtastGyldighedsperiodeTil(testContext, gyldighedsperiodeTil);

        // Tryk paa Gem
        klikGem(testContext);

        String fejl = getFejlTekst(testContext);

        return fejl;
    }

    /**
     * Funktionen udfylder formularen med default datoer, der er korrigeret ud
     * fra systemets dags dato med en gyldighedsperiode på 100 dage frem.
     * @param betalingsevneBudget
     *            = Månedligt betalingsevne baseret på budget
     * @param betalingsevneTabeltraek
     *            = Månedligt betalingsevne baseret på tabeltræk
     * @param psrmDato = String med datoen i PSRM
     * @param driver
     * @throws ParseException 
     */
    public static void udfyldFormular_Budget_BeregnBetalingsevne(TestContext testContext, String betalingsevneBudget, String betalingsevneTabeltraek, String psrmDato) throws ParseException {
        // Definer datoer ud fra dags dato
        String beregnetBetalingsevneDato = TO_Tools.getPsrmDatoPlus_DDMMYYYY(psrmDato, 0);
        String modtagelsesdatoForBudget = beregnetBetalingsevneDato;
        int gyldigFra = 0; // dage i forhold til dags dato, i gyldighedsperiode
                           // fra, default
        String gyldighedsperiodeFra = TO_Tools.getDagsDatoPlus_DDMMYYYY(gyldigFra);

        // periode skal være mindre en ét år
        String gyldighedsperiodeTil = TO_Tools.getDagsDatoPlus_DDMMYYYY(gyldigFra + 100);

        // udfyld formular med default datoer
        udfyldFormular_Budget_BeregnBetalingsevne(testContext, betalingsevneBudget, betalingsevneTabeltraek, beregnetBetalingsevneDato, modtagelsesdatoForBudget, gyldighedsperiodeFra, gyldighedsperiodeTil);
    }

    /**
     * Funktionen udfylder formularen med default dato for beregnet
     * betalingsevne pr. dags dato.
     * @param betalingsevneTabeltraek
     *            = Månedligt betalingsevne baseret på tabeltræk
     * @param psrmDato
     *            = Beregnet betalingsevne dato
     * @param dageFraNu TODO
     * @param driver
     * @throws ParseException 
     */
    public static String udfyldFormular_Tabeltraek_BeregnBetalingsevne(TestContext testContext,
            String betalingsevneTabeltraek, String psrmDato, int dageFraNu) throws ParseException {

        // Indtast månedlig betalingsevne baseret på tabeltræk
        indtastBetalingsevneTabeltraek(betalingsevneTabeltraek);

        // Vaelg Budget som anvendt inddrivelsesskridt
        pickTabeltraek();

        // Indtast dato for beregnet betalingsevne
        indtastBeregnetBetalingsevneDato(TO_Tools.getPsrmDatoPlus_DDMMYYYY(psrmDato, dageFraNu));

        // Tryk paa Gem
        klikGem(testContext);

        String fejl = getFejlTekst(testContext);

        return fejl;
    }

    /**
     * Funktionen udfylder formularen med default dato for beregnet
     * betalingsevne pr. dags dato.
     * 
     * @param driver
     * @param betalingsevneBudget
     *            = Månedligt betalingsevne baseret på budget
     * @param betalingsevneTabeltraek
     *            = Månedligt betalingsevne baseret på tabeltræk
     * @throws ParseException 
     */
    public static void udfyldFormular_Tabeltraek_BeregnBetalingsevne(TestContext testContext, String betalingsevneTabeltraek, String psrmDato) throws ParseException {
        // udfyld formular med default datoer ud fra PSRMs dags dato
        udfyldFormular_Tabeltraek_BeregnBetalingsevne(testContext, betalingsevneTabeltraek, psrmDato, 0);
    }

    public static void goToTilfoejBetalingsevne(TestContext testContext) {
        MenuNavigator.partKontekst().tilfoejBetalingsevne();

        testContext.waitForPageToBeLoaded();
    }

    private static void pickBeregnetBetalingsevne() {
        PO_Betalingsevne.drp_Betalingsevne.pick("CALCPAYABILITY");
    }

    private static void pickGrundlagForBetalingsevne() {
        PO_Betalingsevne.drp_Betalingsevne.pick("PAYMENTABILITY");
    }

    private static void klikOK(TestContext testContext) {
        // Vent
        PO_Betalingsevne.btn_OK.waitFor();

        // Screenshot
        testContext.doScreenshot();

        // Tryk paa OK
        PO_Betalingsevne.btn_OK.click();

        // Vent på at ny side (formular) indlæses
        testContext.waitForPageToBeLoaded();
    }

    private static void indtastBetalingsevneBudget(String betalingsevneBudget) {
        // Ryd feltet i tilfælde af at der står et beløb
        PO_Betalingsevne.txt_MaanedBetalBudget.clear();
        // Indsæt ønsket beløb
        PO_Betalingsevne.txt_MaanedBetalBudget.sendKeys(betalingsevneBudget);
    }

    private static void indtastBetalingsevneTabeltraek(String betalingsevneTabeltraek) {
        // Ryd feltet i tilfælde af at der står et beløb
        PO_Betalingsevne.txt_MaanedBetalTabeltraek.clear();
        // Indsæt ønsket beløb
        PO_Betalingsevne.txt_MaanedBetalTabeltraek.sendKeys(betalingsevneTabeltraek);
    }

    private static void pickBudget() {
        PO_Betalingsevne.drp_AnvendInddrivelsesskridt.pick("BDG");
    }

    private static void pickTabeltraek() {
        PO_Betalingsevne.drp_AnvendInddrivelsesskridt.pick("LUP");
    }

    private static void indtastBeregnetBetalingsevneDato(String beregnetBetalingsevneDato) {
        // Ryd feltet i tilfælde af at der står et beløb
        PO_Betalingsevne.txt_BeregnetBetalingsevneDato.clear();
        // Indsæt ønsket beløb
        PO_Betalingsevne.txt_BeregnetBetalingsevneDato.sendKeys(beregnetBetalingsevneDato);
    }

    private static void indtastModtagelsesdatoForBudget(TestContext testContext, String modtagelsesdatoForBudget) {
        PatientWebDriver driver = testContext.getDriver();
        // Ryd feltet i tilfælde af at der står et beløb
        PO_Betalingsevne.txt_ModtagelsesdatoBudget.clear();
        // Indsæt ønsket beløb
        PO_Betalingsevne.txt_ModtagelsesdatoBudget.sendKeys(modtagelsesdatoForBudget);
        // Tryk på TAB
        TO_Tools.trykKnap(Keys.TAB);
    }

    private static void indtastGyldighedsperiodeFra(TestContext testContext, String gyldighedsperiodeFra) {
        // Udkommenteret da feltet for nu udfyldes automatisk
        // // this field has readonly attribute set - so do a brute force
        // clearing of it
        // PO_Betalingsevne.txt_GyldighedsperiodeFra.clearForced();
        // PO_Betalingsevne.picker_GyldighedsperiodeFra.pick(gyldighedsperiodeFra);

        // Vent på at Gyldighedsperiode Til feltet er blevet automatisk udfyldt
        testContext.waitForPageToBeLoaded();
    }

    private static void indtastGyldighedsperiodeTil(TestContext testContext, String gyldighedsperiodeTil) {
        // Udkommenteret da feltet for nu udfyldes automatisk
        // PO_Betalingsevne.txt_GyldighedsperiodeTil.clearForced();
        // PO_Betalingsevne.picker_GyldighedsperiodeTil.pick(gyldighedsperiodeTil);

        // Vent på at Gyldighedsperiode Til feltet er blevet automatisk udfyldt
        testContext.waitForPageToBeLoaded();
    }

    private static void klikGem(TestContext testContext) {
        // Vent
        PO_Betalingsevne.btn_Gem.waitFor();

        // Screenshot
        testContext.doScreenshot();

        // Tryk paa Gem
        PO_Betalingsevne.btn_Gem.click();

        // Vent på at ny side (formular) indlæses
        testContext.waitForPageToBeLoaded();
    }
    
    private static String getFejlTekst(TestContext testContext){
        String fejl = null;
        try {
            PO_Betalingsevne.txt_Fejlbesked.waitFor();

            // TODO Implementer det også for tabeltræk-versionen af udfyld
            // formular
            // Screenshot
            testContext.doScreenshot();

            // OBSERVATION - hvis betalingsevne allerede er registreret vises
            // dette som en fejl. Vinduet lukkes for ikke at give problemer
            // fremadrettet.

            MO_Utilities.ventTilFeltIndeholderTekst(PO_Betalingsevne.txt_Fejlbesked,
                    "Der er registreret en beregnet betalingsevne");
            fejl = PO_Betalingsevne.txt_Fejlbesked.getText();
            if (fejl.contains("Der er registreret en beregnet betalingsevne")) {
                PO_Betalingsevne.btn_Annuller.clickElementUntilNotPresent();
                System.out.println("Lukker vindue");
            } else {
                System.out.println("Der bliver ikke trykket på Luk");
            }
        } catch (Exception e) {
            // Element ikke fundet, da der ikke allerede er oprettet en
            // betalingsevne
        }
        
        return fejl;
    }
    
}