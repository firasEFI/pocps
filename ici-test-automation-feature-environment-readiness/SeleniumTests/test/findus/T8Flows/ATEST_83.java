package findus.T8Flows;

import findus.BaseTest;
import findus_controllers.ApplicationController;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.RoleMapWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_datamodels.PsrmUserModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_FordringerSubPage;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;
import findus_pageobjects.fordringshaverrelation.Fordringshaverrelation_PrimaerSubPage;
import findus_pageobjects.fordringsoverblik.Fordringsoverblik_PrimaerSubPage;
import findus_pageobjects.part.PartPage;
import findus_pageobjects.part.Part_PrimaerSubPage;
import icisel.testng.PropertyProvider;
import org.testng.annotations.Test;
import utils.PropertyProviderImpl;

/**
 * Steps
 * 1 - Indsendelse af fordringer (VAL-1121)
 * 2 - Log ind med generel sagsbehandler
 * 3 - Fremsøg skyldner, tilgå skyldner overblik
 *      IU-573 - Fremsøg skyldner
 *      IU-624 - Klik på fanen partsoplysninger (tag screenshot) klik på part-navn (tag screenshot)
 *      IU-625 - Gå til 360 overblik fordringer, klik på fordringshaverrealtioner for en specifik fordringshaver (tag screenshot)
 * 4 - Kan/Skal ikke automatiseres (CSR-P)
 * 5/6/7 - IU-625 - Klik på part/skyldner for at se part primær tab
 * 8/9 - IU-650 -
 * 10 - Log ud.
 */
public class ATEST_83 {

    public ApplicationController applicationController;
    public LoginPage loginPage;
    public PropertyProvider propertyProvider;

    public ATEST_83() {
        propertyProvider = new PropertyProviderImpl();
        applicationController = new ApplicationController(propertyProvider);
        loginPage = applicationController.startAtLoginNormal(true);
    }


    @Test
    public void ATEST_83_step1(){
        System.out.println("Step1");
    }

    BasePsrmPage step2Page;
    @Test(dependsOnMethods = {"ATEST_83_step1"})
    public void ATEST_83_step2(){
        System.out.println("Step2");
        //Step 1: login med sagsbehandler (Generel)
        step2Page = applicationController.startAtLoginSSO(true).login(RoleMapWarehouse.SAGSBEHANDLER_GENEREL);
        //TODO
    }

//    @Test
//    public void ATEST_83_step1(){
//        System.out.println("Step1");
//    }
//
//    BasePsrmPage step2Page;
//    @Test(dependsOnMethods = {"ATEST_83_step1"})
//    public void ATEST_83_step2(){
//        System.out.println("Step2");
//        //Step 1: login med sagsbehandler (Generel)
//        step2Page = new LoginPage()
//                .LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler());
//        //TODO
//    }

    Part_PrimaerSubPage step3Page;
    @Test(dependsOnMethods = {"ATEST_83_step2"})
    public void ATEST_83_step3(){
        System.out.println("Step3");
        SkyldnereOgFordringsHavereSearchArgs args = SkyldnereOgFordringsHavereSearchArgs.createSearchArgsForIdFromSkyldner(SkyldnerWarehouse.getSkyldner_test83());
        step3Page = step2Page
                .menu()
                .a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(args)
                .activateSoeg()
                .selectNavn(args)
                .gaaTilNavnPage();
        //TODO assert
    }

    //Ikke udførbar for nuværende
    @Test(dependsOnMethods = {"ATEST_83_step3"})
    public void ATEST_83_step4(){
        System.out.println("Step4");
        //TODO
    }

    _360GradersOverblik_FordringerSubPage step5_6_7Page;
    @Test(dependsOnMethods = {"ATEST_83_step4"})
    public void ATEST_83_step5_6_7() throws InterruptedException {
        System.out.println("Step5-6-7");
        step5_6_7Page = step3Page
                .getParentPage()
                .kontoKontekstMenu() //IU_625
                .gaaTil360GradersOverblik();
    }

    Fordringsoverblik_PrimaerSubPage step8Page;
    @Test(dependsOnMethods = {"ATEST_83_step5_6_7"})
    public void ATEST_83_step8(){
        System.out.println("Step8");
        //TODO Delete test data
        int rowIndex = 1; //1-n

        step8Page = step5_6_7Page
                .selectSpecificFordringshavervifte(rowIndex)
                .activateFordring();
    }

    Fordringsoverblik_PrimaerSubPage step9Page;
    @Test(dependsOnMethods = {"ATEST_83_step8"})
    public void ATEST_83_step9(){
        System.out.println("Step9");
        //TODO Delete test data
        int rowIndex = 1;

        step9Page = new BasePsrmPage()
                .kontoKontekstMenu()
                .gaaTil360GradersOverblik()
                .selectSpecificFordringshavervifte(rowIndex)
                .activateFordring();
    }

    @Test(dependsOnMethods = {"ATEST_83_step9"})
    public void ATEST_83_step10(){
        System.out.println("Step10");

        step9Page.getParentPage().logout();
    }
}
