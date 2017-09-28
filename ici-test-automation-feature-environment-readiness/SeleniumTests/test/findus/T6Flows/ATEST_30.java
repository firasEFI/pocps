package findus.T6Flows;

import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblikPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_KontoSubPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_SagsbehandlingSubPage;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;
import org.testng.annotations.Test;

/**
 * Created by nielsjes on 01-09-2017.
 */
public class ATEST_30 {

    private BasePsrmPage step0Page;
    private _360GradersOverblikPage step1Page;
    private _360GradersOverblik_SagsbehandlingSubPage step2Page;

    @Test
    public void ATEST_26_step0(){
        //  Step 0: Log ind som sagsbehandler (generel)
        step0Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler());
    }

    @Test(dependsOnMethods = {"ATEST_30_step0"})
    public void ATEST_30_step1(){
        // Step 1: Du skal begynde med at fremsøge skyldner NN ved at søge på skyldner NN6's CPR-nummer
        step1Page = step0Page.menu()
                .a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(SkyldnereOgFordringsHavereSearchArgs.createSearchArgsForIdFromSkyldner(SkyldnerWarehouse.skylder_aTEST26()))
                .activateSoeg()
                .selectFirstSearchResultNavn();
    }

    @Test(dependsOnMethods = {"ATEST_30_step1"})
    public void ATEST_30_step2(){
        // Step 2: Du skal nu åbne skyldnerens sagsoverblik.
        step2Page = step1Page.activateSagsbehandling();
    }

    @Test(dependsOnMethods = {"ATEST_30_step2"})
    public void ATEST_30_step3(){
        // Step 3: Du skal nu åbne skyldnerens afdragsordningssag.
        step2Page.selectFirstAfdragsordningssag();
    }

}
