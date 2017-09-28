package findus.T6Flows;

import findus.BaseTest;
import findus_controllers.ApplicationController;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.RoleMapWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects.MockSSOPortalPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_KontoSubPage;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;
import icisel.testng.PropertyProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import utils.PropertyProviderImpl;

public class ATEST_20 {

    BasePsrmPage step1Page;

    public ApplicationController applicationController;

    public LoginPage loginPage;

    public PropertyProvider propertyProvider;

    public ATEST_20() {
        propertyProvider = new PropertyProviderImpl();
        applicationController = new ApplicationController(propertyProvider);
        loginPage = applicationController.startAtLoginNormal(true);
    }

    @AfterClass
    public void Logout(){
        step5Page.getParentPage().logout();

    }

    @Test
    public void ATEST_20_step1(){
        // Step 1: login med sagsbehandler (Generel)
        step1Page = loginPage
                .LoginMedValidBruger(new PsrmUsersWarehouse().getGenerelSagsbehandler());
        //Step 1: login med sagsbehandler (Generel)
        //step1Page = new LoginPage().LoginMedValidBruger(new PsrmUsersWarehouse().getGenerelSagsbehandler());
    }

    @Test(dependsOnMethods = {"ATEST_20_step1"})
    public void ATEST_20_step2(){
        //Er lavet inden test starter
    }

    @Test(dependsOnMethods = {"ATEST_20_step2"})
    public void ATEST_20_step3(){
        //Er lavet inden test starter
    }

    @Test(dependsOnMethods = {"ATEST_20_step3"})
    public void ATEST_20_step4(){
        //Er lavet inden test starter
    }

    _360GradersOverblik_KontoSubPage step5Page;

    @Test(dependsOnMethods = {"ATEST_20_step4"})
    public void ATEST_20_step5(){

        //Step 5: Fremsøg skyldner ved at angive skyldners CPR-nr. i 360 graders søgningen og gå til skyldners konto oversigt.
        step5Page = step1Page
                .menu()
                .a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(SkyldnereOgFordringsHavereSearchArgs.createSearchArgsForIdFromSkyldner(SkyldnerWarehouse.getSkyldner_testPerson()))
                .activateSoeg()
                .selectFirstSearchResultNavn()
                .activateKonto();
    }
}
