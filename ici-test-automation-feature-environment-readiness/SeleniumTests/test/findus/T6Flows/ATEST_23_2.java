package findus.T6Flows;

import findus.BaseTest;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;
import org.testng.annotations.Test;

/**
 * Created by nielsjes on 23-08-2017.
 */
public class ATEST_23_2 extends BaseTest {

    BasePsrmPage step1Page;

    @Test//(dependsOnMethods = {"ATEST_23_1.VAL_1428_step6"})
    public void ATEST_23_step7(){

        //Step 7: TIME TRAVEL + 8 dage todo delete this later
        step1Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGodkenderSagsbehandler());

    }

    @Test(dependsOnMethods = {"ATEST_23_step7"})
    public void ATEST_23_step8(){

        //Step 8: Fremsøg skyldner og kontrollér, at påkravsagen automatisk er lukket med status "lukket"
        step1Page
                .menu()
                .a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(SkyldnereOgFordringsHavereSearchArgs.createSearchArgsForIdFromSkyldner(SkyldnerWarehouse.getSkyldner_testPerson()))
                .activateSoeg()
                .selectFirstSearchResultNavn()
                .iu_246_aabnSagsoverblikketForSkylder.execute()
                .iu_606_aabnSkyldnersSag.execute_Paakrav("")
        ;
    }
}
