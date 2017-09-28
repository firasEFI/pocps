package findus.T6Flows;

import java.io.IOException;
import java.util.Date;

import findus_pageobjects._360_graders_overblik._360GradersOverblikPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_PartsoplysningerSubPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerOcrLinjePage;
import modules.MO_Indbetaling;
import org.testng.annotations.Test;

import findus.BaseTest;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_datamodels.RapportSearchModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_KontoSubPage;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;
import findus_pageobjects.opgave.OpgavePage;
import findus_pageobjects.overblik_over_betalinger.OverblikOverBetalingerPage;
import findus_pageobjects.rapporthistorik.RapporthistorikPage;
import utils.PropertyProviderImpl;
import utils.batchJob.BatchJobRunner;
import utils.batchJob.BatchJobType;
import utils.betaling.BetalingsfilType;

/**
 * Created by nielsjes on 23-08-2017.
 */
public class ATEST_22 extends BaseTest {

    private BasePsrmPage step1Page;
    private _360GradersOverblik_KontoSubPage step2Page;
    private OverblikOverBetalingerPage step34Page;
    private _360GradersOverblik_KontoSubPage step5Page_kontoSubside;
    private OverblikOverBetalingerPage step6Page;
    private BasePsrmPage step7Page;
    private RapporthistorikPage step8Page;
    private OpgavePage step9Page;

//    @Test
    public void ATEST_22_step0() throws IOException {
        //Indbetaling fra NETS på OCR-linje, der er udsendt til skyldner NN6
        //beløb skal være 4300,61 kr., således at der er 500 kr. i overskydende beløb, som skal til udbetaling
        //indbetalingsdato skal være den 25. juli 2017

        step1Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler());

        _360GradersOverblik_PartsoplysningerSubPage _360overblik = step1Page
                .menu()
                .a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(SkyldnereOgFordringsHavereSearchArgs.createSearchArgsForIdFromSkyldner(SkyldnerWarehouse.skylder_aTEST22()))
                .activateSoeg()
                .selectFirstSearchResultNavn()
                .activatePartsoplysninger();

        String currentDebt = _360overblik.getCurrentTaxDebt();

        Sagsbehandlingsskridt_PrimaerOcrLinjePage sagsbehandlingsPage = _360overblik.getParentPage().kontoKontekstMenu().opretOcrLinje().createOCRLineWithPayDate("26-09-2017");

    	MO_Indbetaling.opretIndbetaling(BetalingsfilType.M602_BETALINGSSERVICE, currentDebtPlusValueIsPayment(currentDebt, 1000.01), new Date(), sagsbehandlingsPage.getIDnumberOfOCRLine(), new PropertyProviderImpl());
    }

    @Test // (dependsOnMethods = {"ATEST_22_step0"})
    public void ATEST_22_step1() {
        // Step 1: login med sagsbehandler (Generel)
        step1Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler());
    }

    @Test(dependsOnMethods = { "ATEST_22_step1" })
    public void ATEST_22_step2() {
        // Step 2: Søg op skyldner NN og naviger til skyldners kontoudtog og
        // kontroller transaktioner
        step2Page = step1Page.iu_573_fremsoegSkyldnerVisKontoOverblik
                .execute(SkyldnereOgFordringsHavereSearchArgs.createSearchArgsForIdFromSkyldner(SkyldnerWarehouse
                        .getSkyldner_testPerson())).iu_616_fremsoegKontoudtogForFordringshaverFraFanenKonto.execute();
    }

    @Test(dependsOnMethods = { "ATEST_22_step2" })
    public void ATEST_22_step3_and_step4() {
        // Step 3: Klik på indbetalingen
        // TODO how is the controlling part of this step 4 to be conducted? ->
        // Asserts at some point later
        //step34Page = step2Page.iu_598_findIndbetalingPaaSkylderenPaa360GradersOverblikFanenSagsbehandling.execute();
    }

    @Test(dependsOnMethods = { "ATEST_22_step3_and_step4" })
    public void ATEST_22_step5() {
        // Step 5: Naviger tilbage til skyldner NN's kontoudtog
        step5Page_kontoSubside = step34Page.iu_646_vendTilbageTil360GraderPaaKundeViaDashboardFindKontoUdtog.execute();
    }

    @Test(dependsOnMethods = { "ATEST_22_step5" })
    public void ATEST_22_step6() {
        // Step 6: Klik på udbetalingen

     // TODO: Needs to updated with correct parameters
        step6Page = step5Page_kontoSubside.iu_600_findUdbetalingPaaSkyldnerenPaa360GradersOverblikFanenSagsbehandling
                .execute("", "", "");
    }

    @Test(dependsOnMethods = { "ATEST_22_step6" })
    public void ATEST_22_step7() {
        // Step 7: Log ind som betalingssagsbehandler med godkenderrolle

        new BasePsrmPage().logout(); // FIXME page pattern?
        step7Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGodkenderSagsbehandler());
    }

    @Test(dependsOnMethods = { "ATEST_22_step7" })
    public void ATEST_22_step8() {
        // Step 8: Træk rapport for Kontospecifikationer for følgende konti:
        // -3080 for dagens dato.

        new BatchJobRunner(propertyProvider).run(BatchJobType.GLASSIGN);

        RapportSearchModel rapportSearchModel = new RapportSearchModel.RapportSearchModelBuilder()
                .setBogfoeringsDatoFra(new Date())
                .setBogfoeringsDatoTil(new Date())
                .setKontoNummer("3080").createRapportSearchModel();

        step8Page = step7Page.iu_607_traekRapportForKontospecifikationer.execute(rapportSearchModel);

    }

    @Test(dependsOnMethods = { "ATEST_22_step8" })
    public void ATEST_22_step9() {
        // Step 9: Kontroller at opgave er oprettet
        step9Page = step8Page
                .menu()
                .a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(SkyldnereOgFordringsHavereSearchArgs
                        .createSearchArgsForIdFromSkyldner(SkyldnerWarehouse.skylder_aTEST22()))
                .activateSoeg()
                .selectFirstSearchResultNavn().iu_605_kontrollerAtOpgaveVedroerendeGodkendelseAfUdbetalingErOprettet
                        .execute();
    }

    @Test(dependsOnMethods = { "ATEST_22_step9" })
    public void ATEST_22_step10() {

        // Step 10: Find og åben den i PSRM gemte udligningsunderretning.
        // Kontroller, at den indholder de felter, som den skal,
        // og med rigtig information (vær specielt oprmæksom på de
        // informationer, der er markeret i fed). //TODO

    }

    private double currentDebtPlusValueIsPayment(String currentTaxDebt, double d) {
        currentTaxDebt = currentTaxDebt.substring(0, currentTaxDebt.length()-3);
        currentTaxDebt = currentTaxDebt.replace("-", "");
        currentTaxDebt = currentTaxDebt.replace(".", "");
        currentTaxDebt = currentTaxDebt.replace(",", ".");
        return Double.valueOf(currentTaxDebt) + d;
    }
}