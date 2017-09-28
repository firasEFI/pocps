package findus.T6Flows;

import java.util.Date;

import org.testng.annotations.Test;

import findus.BaseTest;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_datamodels.RapportSearchModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_SagsbehandlingSubPage;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;
import findus_pageobjects.betalingshaendelse.Betalingshaendelse_IndbetalingerPopWindow;
import findus_pageobjects.betalingshaendelse.Betalingshaendelse_IndbetalingerSubPage;
import findus_pageobjects.partsforloeb.PartsforloebPage;
import findus_pageobjects.rapporthistorik.Rapporthistorik_PrimaerSubPage;
import icisel.utils.driver.Engine;

public class ATEST_24 extends BaseTest {

    private BasePsrmPage step1Page;
    private _360GradersOverblik_SagsbehandlingSubPage step2Page;
    private Betalingshaendelse_IndbetalingerPopWindow step3Page;
    private Betalingshaendelse_IndbetalingerSubPage step4Page;
    private Rapporthistorik_PrimaerSubPage step7Page;
    private BasePsrmPage step8Page;
    private PartsforloebPage step9Page;

    @Test
    public void ATEST_24_step1() {
        // Step 1, jira issue: Log ind som betalingssagsbehandler med
        // betalingssagsbehandler
        step1Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler());
    }

    @Test(dependsOnMethods = { "ATEST_24_step1" })
    public void ATEST_24_step2() {
        /*
         * Step 2, Tilgå opgave (AFKLARES I VAL-709) *ELLER* _Hvis der ikke
         * findes en opgave, find da indbetalingen med transaktionskode
         * "Tilbagefør betaling" og fortsæt til step 3 Step 2, efter snak med
         * manualtester, skåret ud i pap: Måden man kommer til at gå ind på
         * opgave er ikke helt sikkert lige nu. måske via Menu -> Opgave ->
         * Opgave overblik. Hvis det IKKE er opgave, kan indbetaling findes via
         * søg person frem -> sagsbehandling -> betalingshændelser for kontoen
         */

        // OpgaveOverblikPage opgaveOverblikPage =
        // step1Page.menu().opgave().opgaveOverblik();
        // Hvis opgave ^^ ELLERS vv

        step2Page = step1Page
                .menu()
                .a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(SkyldnereOgFordringsHavereSearchArgs
                        .createSearchArgsForIdFromSkyldner(SkyldnerWarehouse.getSkyldner_testPerson()))
                .activateSoeg()
                .selectFirstSearchResultNavn()
                .activateSagsbehandling();
    }

    @Test(dependsOnMethods = { "ATEST_24_step2" })
    public void ATEST_24_step3() {
        /*
         * Step 3, jira issue: Tilgå skærmbillede, hvor du kan angive årsag til
         * ophævelse af dækning. Notér det samlede beløb, som indbetalingen har
         * dækket. Notér også evt. inddrivelsesrenter for sig selv. Angiv, at
         * årsagen til ophævelsen af dækningen på alle tre fordringer er, at
         * indbetalingen er dækningsløs. Step 3, efter snak med manualtester,
         * skåret ud i pap: Findes via indbetaling/sagsbehandling -> Tabbet
         * indbetalinger (Betalingshændelser for kontoen) -> tryk på en række ->
         * Tryk på knap Indbetalings handling -> allernederst tryk på Annuller
         * ved Indbetalings handling -> nu kan du angive begrundelse.
         */
        step3Page = step2Page.gaaTilSideBetalingshaendelseSubsideIndbetalinger()
                .activateAnnullerKnap()
                .aTest24Values();
    }

    @Test(dependsOnMethods = {"ATEST_24_step3"})
    public void ATEST_24_step4(){
        /*Step 4, jira issue: Ophæv dækningen på samtlige fordringer.
        Step 4, efter snak med manualtester, skåret ud i pap: tryk ok på popup fra step 3. */

        step4Page = step3Page.activateOk();
    }

    @Test(dependsOnMethods = { "ATEST_24_step4" })
    public void ATEST_24_step5() {
        /*
         * Step 5, jira issue: Navigér til udbetalingen og kontrollér, at der
         * maskinelt er oprettet en markering om oprettelse af modkrav i SAP38
         * samt en årsag til markeringen Step 5, efter snak med manualtester,
         * skåret ud i pap: Udbetaling: søg person frem -> sagsbehandling ->
         * Udbetalinger
         */
        step4Page.getParentPage()
                .kontoKontekstMenu()
                .gaaTil360GradersOverblik()
                .getParentPage()
                .activateSagsbehandling()
                .gaaTilUdbetalingOgKontrollerKorrektStatus("Afsluttet");
    }

    @Test(dependsOnMethods = { "ATEST_24_step5" })
    public void ATEST_24_step6() {
        /*
         * Step 6, jira issue: Generer udtræk frem til d.d. med information til
         * SAP-38 Step 6, efter snak med manualtester, skåret ud i pap: - pas
         * for nu. Fang/Find en der har testet på SAP-38
         */
    }

    @Test(dependsOnMethods = { "ATEST_24_step6" })
    public void ATEST_24_step7() {
        /*
         * Step 7, jira issue: Udtræk rapport vedrørende Kontospecifikationer
         * for følgende konti: -3667 -3970 -3974 -4970 -4974 -4684 -4665 -3977
         * -4977 -3982 -4982 for dagens dato. Step 7, efter snak med
         * manualtester, skåret ud i pap: Excel fil ting (lavet i ATEST_22/23)
         */
        step7Page = step4Page.getParentPage().menu().opgavestyring().rapporter().activatePrimaer()
                .chooseBalanceofStatementOfAccount();
        step7Page.udfyldOgVisRapport(new RapportSearchModel.RapportSearchModelBuilder()
                .setBogfoeringsDatoFra(new Date()).setBogfoeringsDatoTil(new Date())
                .setKontoNummer("3667, 3970, 3974, 4970, 4974, 4684, 4665, 3977, 4977, 3982, 4982")
                .createRapportSearchModel());
    }

    @Test(dependsOnMethods = { "ATEST_24_step7" })
    public void ATEST_24_step8() {
        /*
         * Step 8, jira issue: Log ind som sagsbehandler Step 8, efter snak med
         * manualtester, skåret ud i pap: Log in igen
         */

        new BasePsrmPage().logout();
        Engine.getDriver().navigate().to(System.getenv("PSRM_LOGIN_URL"));
        step8Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler());
    }

    @Test(dependsOnMethods = { "ATEST_24_step8" })
    public void ATEST_24_step9() {
        /*
         * Step 9, jira issue: Navigér til ni opgaver om
         * "Dækningsløs betaling på fordring". Step 9, efter snak med
         * manualtester, skåret ud i pap: De burde ligge under partens forløb
         * (dvs. find person frem også kan du finde dem via hamburgermenu -> se
         * ATEST_22/23 for hvordan det gøres)
         */

        step9Page = step8Page
                .menu()
                .a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(SkyldnereOgFordringsHavereSearchArgs
                        .createSearchArgsForIdFromSkyldner(SkyldnerWarehouse.getSkyldner_testPerson()))
                .activateSoeg()
                .selectFirstSearchResultNavn()
                .partKontekstMenu()
                .gaaTilPartensForloeb();
    }

    @Test(dependsOnMethods = { "ATEST_24_step9" })
    public void ATEST_24_step10() {
        /*
         * Step 10, jira issue: RELEASE 1.1 Find og åben den i PSRM gemte
         * udligningsunderretning. Kontroller, at den indholder de felter, som
         * den skal, og med rigtig information (vær specielt oprmæksom på de
         * informationer, der er markeret i fed). Gem udligningsunderretningen
         * og sammenlign med den udligningsunderretning, du gemte i VAL-1427
         * Step 10, efter snak med manualtester, skåret ud i pap: Det ikke os
         * der kan gøre dette på nuværende tidspunkt.
         */
    }
}