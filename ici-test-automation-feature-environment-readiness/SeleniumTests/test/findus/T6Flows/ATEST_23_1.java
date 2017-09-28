package findus.T6Flows;

import findus.BaseTest;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;
import findus_pageobjects.indbetaling.IndbetalingPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblikPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblik_PrimaerSubPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling;
import org.testng.annotations.Test;

/**
 * Created by nielsjes on 23-08-2017.
 */
public class ATEST_23_1 extends BaseTest {

    BasePsrmPage step1Page;
    private OpgaveOverblik_PrimaerSubPage step2Page;
    private Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling step3Page;
    private Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling step4Page;
    private IndbetalingPage step5Page;

    @Test
    public void ATEST_23_step1(){
        //Step 1: Log ind som betalingssagsbehandler med godkenderrolle
        step1Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGodkenderSagsbehandler());

    }

    @Test(dependsOnMethods = {"ATEST_23_step1"})
    public void ATEST_23_step2(){
        //Step 2: Klik på opgaven vedrørende individuel godkendelse af udbetaling

        //step2Page = step1Page.iu_231_gaaTilOpgaveOverblik.execute().iu_596_findOpgave.execute();

    }

    @Test(dependsOnMethods = {"ATEST_23_step2"})
    public void ATEST_23_step3(){

        //Step 3: Klik videre fra opgaven ind til skærmbilledet, hvor det er muligt at godkende eller afvise udbetalingen
        step3Page = step2Page.iu_611_gaaTilIndividuelUdbetaling.execute("93213832939025");

    }
    
    @Test(dependsOnMethods = {"ATEST_23_step3"})
    public void ATEST_23_step4(){

        //Step 4: Kontroller at beløbet er korrekt og godkend udbetalingen
        step4Page = step3Page.iu_612_godkendUdbetaling.execute();
    }

    @Test(dependsOnMethods = {"ATEST_23_step4"})
    public void ATEST_23_step5(){

        //Step 5: Klik på udbetalingen og kontroller, at den har den korrekte status todo wont work anymore, we have not searched for a person, hvor skal vi finde dette henne nu?
        step5Page = step4Page
                .getParentPage()
                .menu()
                .a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(SkyldnereOgFordringsHavereSearchArgs.createSearchArgsForIdFromSkyldner(SkyldnerWarehouse.getSkyldner_Val1332()))
                .activateSoeg()
                .selectFirstSearchResultNavn()
                .activateSagsbehandling()
                .gaaTilUdbetalingOgKontrollerKorrektStatus("Bogført");
    }

    @Test(dependsOnMethods = {"ATEST_23_step5"})
    public void ATEST_23_step6() {

        //Step 6: Afslut opgaven manuelt. Vælg årsag 'Løst' til afslutning af opgaven fra drop-down menu.
        //Efter kommentar fra manuel tester: top menu -> opgave -> opgave overblik -> i tabellen skal man kører så meget til højre som man nu kan -> Afslut opgave knappen
        step5Page
                .menu()
                .opgave()
                .opgaveOverblik()
                .trykPaaAfslutOpgaven("93213832939025")
                .iu_613_lukOpgaveManuelt.execute("CMP", null);
    }

}
