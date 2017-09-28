package findus.T8Flows;

import findus.BaseTest;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects.opgave.OpgaveFremrykParm;
import findus_pageobjects.opgave.Opgave_PrimaerSubPage;
import findus_pageobjects.opgave_overblik_for_tilsyn.OpgaveOverblikForTilsyn_PrimaerSubPageParm;
import findus_pageobjects.wizards.opret_opgave_manuelt.OpretOpgaveManueltWizardPageParm;
import org.testng.annotations.Test;

/**
 * 1 - log ind som sysadm
 * 2 - IU-639 Opret manuelt en opgave på skylder N8 med opgavekategori ”Udvælge skyldnere” og opgavetitel ”Opret afdragsordning”
 * 3 - Log ind som funktionsleder uden VIP-rolle Grundet descoping af roller kan det være nødvendigt at logge ind med en anden rolle
 * 4 - IU-642 Gå til opgavelisten og  tjek, at du ikke kan se en opgave oprettet for skyldner N8
 * 5 - Log ud og log ind som - funktionsleder (med VIP-adgang)
 * 6/7 - IU-643 Testtrin 6 og 7 hører sammen.
 *      Via Testtrin 6 kommer man til oversigt over opgaver vedrørende en specifik skyldner og vælger opgaven Opret afdragsordning,
 *      mens man i Testtrin 7, kontrollerer at opgaven indeholder de rigtige data.
 *          -Gå til opgavelisten og  tjek, at der er oprettet en opgave, der fremgår af din opgaveoversigt
 *          -Tjek at følgende oplysninger fremgår om opgaven:
 *          -Tidligste frist 0
 *          -Seneste frist 14
 *          -Status: oprettet
 *          -Opgavekategori:  Udvælge skyldner
 *          -Opgavetype: Opret afdragsordning
 *          -Prioritet 30
 * 8/9 - IU-644 Via Testtrin 8 tildeler man til en opgave til en specifik sagsbehandler,
 *      mens man i Trin 9, kontrollerer at opgaven har fået status Aktiv.
 *      Vælg opgaven, der er oprettet for skyldner N8 og tildel den til den sagsbehandlerrolle (VIP), som du selv kan logge ind som.
 *      N8 har CPR-nummer: 050573-1239
 *      Tjek at status på den tildelte opgave nu er ændret til "aktiv"
 * 10 - log ud
 */
public class ATEST_86 extends BaseTest {

    BasePsrmPage step1Page;
    @Test
    public void ATEST_86_step1(){
        System.out.println("Step1");
        step1Page = new LoginPage()
                .LoginMedValidBruger(PsrmUsersWarehouse.getSystemAdministrator());
    }

    BasePsrmPage step2Page;
    @Test(dependsOnMethods = {"ATEST_86_step1"})
    public void ATEST_86_step2() {
        //TODO delete testdata
        System.out.println("Step2");
        OpretOpgaveManueltWizardPageParm parm = new OpretOpgaveManueltWizardPageParm();
        parm.setVaelgOpgavekategori(OpretOpgaveManueltWizardPageParm.VaelgOpgavekategori.CHOOSEDEBTORS);
        parm.setVaelgOpgavetype(OpretOpgaveManueltWizardPageParm.VaelgOpgavetype.DK_CRTPP);
        //IU_639
        step2Page = step1Page
                .menu()
                .opgave()
                .opretOpgaveManuelt()
                .fillForm(parm, SkyldnerWarehouse.getSkyldner_test86())
                .activateGem();
        //TODO
    }

    BasePsrmPage step3Page;
    @Test(dependsOnMethods = {"ATEST_86_step2"})
    public void ATEST_86_step3(){
        System.out.println("Step3");
        step2Page.logout();
        step3Page = new LoginPage()
                .LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler()); //TODO skal være funktionsleder uden VIP
        //TODO assert
    }

    BasePsrmPage step4Page;
    @Test(dependsOnMethods = {"ATEST_86_step3"})
    public void ATEST_86_step4(){
        System.out.println("Step4");
        OpgaveOverblikForTilsyn_PrimaerSubPageParm parm = new OpgaveOverblikForTilsyn_PrimaerSubPageParm();
        parm.setRelationTilID(SkyldnerWarehouse.getSkyldner_test86().getSkyldnerId());
        //IU_642
        step3Page
                .menu()
                .opgave()
                .opgaveOverblikForTilsyn()
                .fillForm(parm)
                .activateSoeg();

//        TODO assert "Jeg kan ikke se, at der er oprettet en opgave på skyldner N8"
    }

    BasePsrmPage step5Page;
    @Test(dependsOnMethods = {"ATEST_86_step4"})
    public void ATEST_86_step5(){
        System.out.println("Step5");
        step3Page.logout();
        step5Page = new LoginPage()
                .LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler()); //TODO skal være funktionsleder med VIP
        //TODO assert log in lykkes
    }

    Opgave_PrimaerSubPage step6_7Page;
    @Test(dependsOnMethods = {"ATEST_86_step5"})
    public void ATEST_86_step6_7(){
        System.out.println("Step6_7");
        //TODO Delete test data
        OpgaveOverblikForTilsyn_PrimaerSubPageParm parm = new OpgaveOverblikForTilsyn_PrimaerSubPageParm();
        parm.setRelationTilID(SkyldnerWarehouse.getSkyldner_test86().getSkyldnerId());
        int rowIndex = 0;
        //IU_643
        step6_7Page = step5Page
                .menu()
                .opgave()
                .opgaveOverblikForTilsyn()
                .fillForm(parm)
                .activateSoeg()
                .selectOpgaveByIndex(rowIndex);
        //TODO assert
    }

    Opgave_PrimaerSubPage step8_9Page;
    @Test(dependsOnMethods = {"ATEST_86_step6_7"})
    public void ATEST_86_step8_9(){
        System.out.println("Step8_9");
        //TODO Delete test data
        OpgaveFremrykParm parm = new OpgaveFremrykParm();
        parm.setBruger(PsrmUsersWarehouse.getGenerelSagsbehandler().getSagsbehanlderId());
        parm.setDetaljer("Detaljer til SB");
        //IU_644
        step8_9Page = step6_7Page
                .activateOpgave_PrimærSubPageTildelPopupWindow()
                .fillFormFremrykOpgaveOK(parm)
                .activateOK();
//        TODO assert
    }

    @Test(dependsOnMethods = {"ATEST_86_step8_9"})
    public void ATEST_86_step10(){
        System.out.println("Step10");
        step8_9Page.getParentPage().logout();
    }
}
