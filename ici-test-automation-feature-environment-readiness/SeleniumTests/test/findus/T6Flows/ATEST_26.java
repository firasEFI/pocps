package findus.T6Flows;

import findus.BaseTest;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_KontoSubPage;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;
import org.testng.annotations.Test;

/**
 * Created by nielsjes on 01-09-2017.
 */
public class ATEST_26 extends BaseTest {

    private BasePsrmPage step1Page;
    private BasePsrmPage step2Page;
    private BasePsrmPage step3Page;
    private _360GradersOverblik_KontoSubPage step4Page;
    private BasePsrmPage step7Page;
    private BasePsrmPage step6Page;
    private BasePsrmPage step8Page;
    private BasePsrmPage step14Page;

    @Test
    public void ATEST_26_step1(){
        //  Step 1: Log ind som systemadministrator
        step1Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler()); //TODO find admin user, and use instead

    }

    @Test(dependsOnMethods = {"ATEST_26_step1"})
    public void ATEST_26_step2(){
        //  Step 2: Start job som skal  indlæse forberedt M602 fil //TODO find fil og start batch job
        step1Page.logout();
    }

    @Test(dependsOnMethods = {"ATEST_26_step2"})
    public void ATEST_26_step3(){
        //  Step 3: Log ind som sagsbehandler (generel)
        step3Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler());
    }

    @Test(dependsOnMethods = {"ATEST_26_step3"})
    public void ATEST_26_step4(){
        // Step 4: IU-573 + IU-597 Slå skyldner NN6 op og naviger til skyldners kontoudtog og kontroller transaktioner.
        // TODO Kontroller at inddrivelsesrenterne er beregnet korrekt.
        // Søg på CPR-nummer: 0505751787
        step4Page = step3Page.menu()
                .a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(SkyldnereOgFordringsHavereSearchArgs.createSearchArgsForIdFromSkyldner(SkyldnerWarehouse.skylder_aTEST26()))
                .activateSoeg()
                .selectFirstSearchResultNavn()
                .activateKonto();
    }

    @Test(dependsOnMethods = {"ATEST_26_step4"})
    public void ATEST_26_step5(){
        //  Step 5: Klik på indbetalingen Testtrin 5 og 6 hører sammen.I trin 5 kommer man til billedet, mens man i trin 6 ser på de data der fremkommer.
        //TODO Kontroller dækningsrækkefølge og noter det beløb som er brugt til dækning for de forskellige fordringer.
        step4Page
                .activateFinansielleTransaktionerIndbetaling();
        step1Page.logout();

    }

    @Test(dependsOnMethods = {"ATEST_26_step5"})
    public void ATEST_26_step6(){
        //  Step 6: Log ind som betalingssagsbehandler
        step6Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler()); //FIXME betalingssagsbehandler
    }

    @Test(dependsOnMethods = {"ATEST_26_step6"})
    public void ATEST_26_step7(){
        // Step 7
        // TODO Batchjobbet GLASSIGN skal afvikles.
        step1Page.logout();
    }

    @Test(dependsOnMethods = {"ATEST_26_step7"})
    public void ATEST_26_step8(){
        // Step 8 Log ind som systemadministrator
        step8Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler()); //TODO find admin user, and use instead


    }

    @Test(dependsOnMethods = {"ATEST_26_step8"})
    public void ATEST_26_step9(){
        // Step 9
        // TODO Start job til at indlæse forberedt FINSTA fil
        step1Page.logout();

    }

    @Test(dependsOnMethods = {"ATEST_26_step9"})
    public void ATEST_26_step10(){
        //  Step 10: Log ind som betalingssagsbehandler
        step6Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler()); //FIXME betalingssagsbehandler
    }

    @Test(dependsOnMethods = {"ATEST_26_step10"})
    public void ATEST_26_step11(){
        //  Step 11:
        // TODO Udtræk afstemningsrapport vedrørende matchning af M602 og FINSTA fil for bankkonto 4069208371 for dagens dato
    }

    @Test(dependsOnMethods = {"ATEST_26_step11"})
    public void ATEST_26_step12(){
        //  Step 12: Kontroller rapport
        // TODO Det fremgår af rapporten, at summen af indbetalinger på den indlæste M602 fil og summen af den indlæste FINSTA fil matcher, ved at totalbeløbene stemmer overrens og er 1.850 kr.

    }

    @Test(dependsOnMethods = {"ATEST_26_step12"})
    public void ATEST_26_step13(){
        //  Step 13: Kontroller rapport
        // TODO Batchjobbet GLASSIGN skal afvikles.
        step1Page.logout();
    }

    @Test(dependsOnMethods = {"ATEST_26_step13"})
    public void ATEST_26_step14(){
        // Step 14 Log ind som systemadministrator
        step14Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler()); //TODO find admin user, and use instead

    }

    @Test(dependsOnMethods = {"ATEST_26_step14"})
    public void ATEST_26_step15(){
        // Step 15 TODO Start job som skal  indlæse forberedt M602 fil (samme fil som i step 2)
        // Filen kan ikke indlæses, da den allerede er indlæst tidligere

    }

    @Test(dependsOnMethods = {"ATEST_26_step15"})
    public void ATEST_26_step16(){
        // Step 16 TODO Start job med at indlæse forberedt FINSTA fil (samme fil som i step 10)
        // Filen kan ikke læses ind, da den allerede er indlæst tidligere

    }

}
