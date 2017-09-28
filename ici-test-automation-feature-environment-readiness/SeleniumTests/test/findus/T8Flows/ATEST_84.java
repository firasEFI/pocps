package findus.T8Flows;

import findus.BaseTest;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_FordringerSubPage;
import findus_pageobjects.fordringshaverrelation.Fordringshaverrelation_PrimaerSubPage;
import findus_pageobjects.opgave.OpgaveFremrykParm;
import findus_pageobjects.opgave.Opgave_PrimaerSubPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblik_PrimaerSubPage;
import findus_pageobjects.part.PartPage;
import findus_pageobjects.wizards.opret_opgave_manuelt.OpretOpgaveManueltWizardPageParm;
import org.testng.annotations.Test;

/**
 1	log ind
         Log ind som - funktionsleder med VIP-rolle
         Grundet rolledescoping kan det være nødvendigt at logge ind med en anden rolle for at gennemføre dette trin
         (Log ind som Sagsbehandler generel. Er heller ikke testet i oprindelig VAL-2235. Steppet står som N/A))
         Log ind lykkes
 2 IU_645
         IU-645
         Opret manuelt en opgave på skylder N8 med opgavekategori ”Udvælge skyldnere” og opgavetitel ”Send påkrav”
         IS-17575 Ak 1
         Jeg kan manuelt oprette en opgave med opgavekategori ”Udvælge skyldnere” og opgavetitel ”Send påkrav”
 3 IU-648
         IU-648
         Gå til opgavelisten og tjek at opgaven er oprettet og maskinelt tildelt dit team og derfor fremgår af din opgaveoversigt
         OBS: Der kan pt. ikke tildeles opgaver maskinelt eller til et specifikt team men kun til en Rolle eller sagsbehandler. Dette er heller ikke testet i den oprindelige VAL-2235. Steppet står som N/A
         Jeg har derfor i step 3 valgt at tildele opgaven til en sagsbehandler
         (IS-5370 AK.1)
         Tjek at der er oprettet 1 opgave i opgavelisten
 4 tjek
         Tjek at opgaven har en opgavekategori og en opgavetype
         IS-17575 AK 1.2 og AK 1.1
         Opgavekategorien er ”Udvælge skyldnere” og opgavetitlen er ”Send påkrav”
 5 tjek
         Tjek at du kan se en status ud fra opgaven og at statussen er "Oprettet"
         (IS-5370 AK 1.6 og IS-11639 AK 1)
         Statussen er angivet som "oprettet" på opgaven
 6 IU_602
         IU-602
         Vælg opgaven og tildel den til en sagsbehandler i dit team fra opgaveoversigten
         (IS-5366 AK.6)
         Opgaven kan tildeles en sagsbehandler direkte fra opgaveoversigten og w.nr. på denne sagsbehandler fremgår nu af oversigten
 7 tjek
         Tjek at statussen på den tildelte opgave nu er ændret til "aktiv"
         (IS-5370 AK 1.6 og IS-11639 AK 2)
         Statussen er ændret til "aktiv" på opgaven, da opgaven nu er tildelt en sagsbehandler
 8***Jeg er allerede på opgaven??
         Gå ind på opgaven ***Jeg er allerede på opgaven??
         (IS-5371)
         Jeg kan komme ind på den valgte opgave
 9
         Tjek at opgaven er oprettet med prioritet, beskrivelse, relateret objekt, rolle, tidligst frist og senest frist.
         (IS-5162 AK. 2.3-2.8)
         Opgaven er oprettet med prioritet, beskrivlse, relateret objekt, rolle, tidligst frist og senest frist.
 10	Log ud
     Log ud
     Jeg kan logge ud
 */
public class ATEST_84 extends BaseTest {

    BasePsrmPage step1Page;
    @Test
    public void ATEST_84_step1(){
        System.out.println("Step1");
        //Step 1: login med sagsbehandler (Generel)
        step1Page = new LoginPage()
                .LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler());
        //TODO assert
    }

    BasePsrmPage step2Page;
    @Test(dependsOnMethods = {"ATEST_84_step1"})
    public void ATEST_84_step2(){
        System.out.println("Step2");
        //TODO Delete test data
        OpretOpgaveManueltWizardPageParm parm = new OpretOpgaveManueltWizardPageParm();

        parm.setVaelgOpgavekategori(OpretOpgaveManueltWizardPageParm.VaelgOpgavekategori.CHOOSEDEBTORS);
        parm.setVaelgOpgavetype(OpretOpgaveManueltWizardPageParm.VaelgOpgavetype.DK_CRTFD);
        parm.setVaelgSendTil(OpretOpgaveManueltWizardPageParm.VaelgSendTil.SNDU);
        parm.setTildeltSagsbehandler("ACH");

        step2Page = step1Page.iu_645
                .execute(parm, SkyldnerWarehouse.getSkyldner_test84());
//        TODO
    }

    OpgaveOverblik_PrimaerSubPage step3Page;
    @Test(dependsOnMethods = {"ATEST_84_step2"})
    public void ATEST_84_step3(){
        System.out.println("Step3");
        step3Page = step2Page.iu_648.execute(PsrmUsersWarehouse.getGenerelSagsbehandler());
//        TODO assert
    }

    BasePsrmPage step4Page;
    @Test(dependsOnMethods = {"ATEST_84_step3"})
    public void ATEST_84_step4(){
        System.out.println("Step4");
//        TODO assert
    }

    BasePsrmPage step5Page;
    @Test(dependsOnMethods = {"ATEST_84_step4"})
    public void ATEST_84_step5(){
        System.out.println("Step5");
//        TODO assert
    }

    Opgave_PrimaerSubPage step6Page;
    @Test(dependsOnMethods = {"ATEST_84_step5"})
    public void ATEST_84_step6(){
        System.out.println("Step6");
        OpgaveFremrykParm parm = new OpgaveFremrykParm();
        step6Page = step3Page.iu_602.execute("", parm);
//        TODO
    }

    BasePsrmPage step7Page;
    @Test(dependsOnMethods = {"ATEST_84_step6"})
    public void ATEST_84_step7(){
        System.out.println("Step7");
//        TODO
    }

    Fordringshaverrelation_PrimaerSubPage step8Page;
    @Test(dependsOnMethods = {"ATEST_84_step7"})
    public void ATEST_84_step8(){
        System.out.println("Step8");
//        TODO assert
    }

    BasePsrmPage step9Page;
    @Test(dependsOnMethods = {"ATEST_84_step8"})
    public void ATEST_84_step9(){
        System.out.println("Step9");
//        TODO assert
    }

    @Test(dependsOnMethods = {"ATEST_84_step9"})
    public void ATEST_84_step10(){
        System.out.println("Step10");
        step6Page.getParentPage().logout();
//        TODO assert
    }
}
