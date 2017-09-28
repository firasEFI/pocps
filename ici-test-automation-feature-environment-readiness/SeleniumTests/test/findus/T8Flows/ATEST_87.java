package findus.T8Flows;

import findus.BaseTest;
import findus_datamodelWarehouses.AfskrivningsWarehouse;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_datamodels.BeregnetBetalingsevne;
import findus_datamodels.GrundlagForBetalingsevneAarligModel;
import findus_datamodels.PsrmUserModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects.brevoplysninger.Brevoplysninger_PrimaerSubPage;
import findus_pageobjects.opgave.OpgaveFremrykParm;
import findus_pageobjects.oversigt_over_daekninger.OversigtOverDaekningerPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerAfdragsordningPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerOcrLinjePage;
import findus_pageobjects.wizards.brevoplysning.BrevoplysningWizardPageParm;
import findus_pageobjects.wizards.fordringer.FordringerWizardPageParm;
import findus_pageobjects.wizards.kundekontakt.Kundekontakt;
import org.testng.annotations.Test;
import utils.batchJob.BatchJobType;
import utils.tools.TO_Tools;

public class ATEST_87 extends BaseTest {

    private final PsrmUserModel psrmUser;

    public ATEST_87() {
        this.psrmUser = PsrmUsersWarehouse.getGenerelSagsbehandler(); //TODO Skal være VIP
    }

    public ATEST_87(PsrmUserModel psrmUser) {
        this.psrmUser = psrmUser;
    }

    BasePsrmPage step1Page;
    @Test
    public void ATEST_87_step1(){
        System.out.println("Step1");
        step1Page = new LoginPage()
                .LoginMedValidBruger(this.psrmUser);
    }

    OversigtOverDaekningerPage step2Page;
    @Test(dependsOnMethods = {"ATEST_87_step1"})
    public void ATEST_87_step2(){
        System.out.println("Step2");
        //TODO Delete test data

        int rowIndex = 0;
        //IU_641
        step2Page = step1Page
                .menu()
                .opgave()
                .opgaveOverblikForTilsyn()
                .activateSøgTildelBruger()
                .fillForm(this.psrmUser)
                .activateSoegExpectOneResult()
                .activateSoeg()
                .selectOpgaveByIndex(rowIndex)
                .activateLinkTilDetaljer_OversigtOverDakninger();
        //TODO assert
    }

    BasePsrmPage step3_4Page;
    @Test(dependsOnMethods = {"ATEST_87_step2"})
    public void ATEST_87_step3_4(){
        System.out.println("Step3_4");
        //IU_528
        GrundlagForBetalingsevneAarligModel input = new GrundlagForBetalingsevneAarligModel();
        step3_4Page = step2Page
                .partKontekstMenu()
                .tilfoejBetalingsevne()
                .continueWithGrundlagForBetalingsevne()
                .fillForm(input)
                .activateGem();
        //TODO assert
    }

    BasePsrmPage step5Page;
    @Test(dependsOnMethods = {"ATEST_87_step3_4"})
    public void ATEST_87_step5(){
        System.out.println("Step5");
        //IU_529
        BeregnetBetalingsevne input = new BeregnetBetalingsevne();
        step5Page = step3_4Page
                .partKontekstMenu()
                .tilfoejBetalingsevne()
                .continueWithBeregnetBetalingsevne()
                .fillForm(input)
                .activateGem();

//        TODO assert
    }

    Sagsbehandlingsskridt_PrimaerAfdragsordningPage step6_7_8Page;
    @Test(dependsOnMethods = {"ATEST_87_step5"})
    public void ATEST_87_step6_7_8(){
        System.out.println("Step6_7_8");
        //TODO Delete test data
        step6_7_8Page = step5Page
                .iu_527_opretAfdragsordning
                .execute(AfskrivningsWarehouse.val_afskrivingsflow)
                .getParentPage() //IU_670 her fra
                .kontoKontekstMenu()
                .gaaTil360GradersOverblik()
                .getParentPage()
                .activateSagsbehandling()
                .selectFirstAfdragsordningssag();
        //TODO assert
    }

    Sagsbehandlingsskridt_PrimaerOcrLinjePage step9Page;
    @Test(dependsOnMethods = {"ATEST_87_step6_7_8"})
    public void ATEST_87_step9(){
        System.out.println("Step9");
        //TODO Delete test data
        OpgaveFremrykParm parm = new OpgaveFremrykParm();
        parm.setBruger(new PsrmUsersWarehouse().getGenerelSagsbehandler().getSagsbehanlderId());
        parm.setDetaljer("Detaljer til SB");
        //IU_638
        step9Page = step6_7_8Page
                .getParentPage()
                .kontoKontekstMenu()
                .gaaTil360GradersOverblik()
                .getParentPage()
                .activateSagsbehandling()
                .expandOverblikOCRlinjerAccordion()
                .activateSpecificLinkTilOCRSagsbehandlingsskridt(0);
//        TODO assert
    }

    Brevoplysninger_PrimaerSubPage step10til15Page;
    @Test(dependsOnMethods = {"ATEST_87_step9"})
    public void ATEST_87_step10til15(){
        System.out.println("Step10til15");
        //TODO Delete testdata
        BrevoplysningWizardPageParm parm = new BrevoplysningWizardPageParm();
        parm.setInddrivelsesskridt(BrevoplysningWizardPageParm.Inddrivelsesskridt.FTXT);
        parm.setKundekontakttype(BrevoplysningWizardPageParm.Kundekontakttype.FRITEKSTBREV);

        Kundekontakt kontakt = new Kundekontakt();
        kontakt.setFritekstOverskrift("Testdata overskrift");
        kontakt.setFritekst("Testdata fritekst");
        kontakt.setTilfoejGaeldsoverblik(true);
        kontakt.setTilfoejOcrLinje(true);

        int rowIndex = 0;

        FordringerWizardPageParm parmb = new FordringerWizardPageParm();
        parmb.setBetalingsfrist("01012018");

        step10til15Page = step9Page
                .getParentPage() //IU_657
                .partKontekstMenu()
                .brevoplysning()
                .tilfoej()
                .fillForm(parm)
                .activateOk()
                .fillForm(kontakt)
                .activateSoegOCRLinje()
                .fillForm(SkyldnerWarehouse.getSkyldner_test87())
                .activateSoegNavnWithResultSet()
                .selectSpecificResult(rowIndex)
                .activateSoegNavn()
                .activateGem()
                .activateHaandterFordringer()
                .fillForm(parmb)
                .activateGem()
                .activateGenererUdkast()
                .activateSeUdkast()
                .activateGodkendUdkast()
                .activateSendTilAogD();
    }

    Brevoplysninger_PrimaerSubPage step16Page;
    @Test(dependsOnMethods = {"ATEST_87_step10til15"})
    public void ATEST_87_step16() {
        System.out.println("Step16");
        //IU_681
        step16Page = step10til15Page
                .runBatchJob(BatchJobType.AogDSendLetters, propertyProvider)
                .runBatchJob(BatchJobType.AogDRequestStatus, propertyProvider)
                .runBatchJob(BatchJobType.SaveSentLetters, propertyProvider);
        //TODO Assert
    }

    Brevoplysninger_PrimaerSubPage step17Page;
    @Test(dependsOnMethods = {"ATEST_87_step16"})
    public void ATEST_87_step17() {
        System.out.println("Step17");
        //IU_672
        int rowIndex = 0;
        step17Page = step16Page
                .getParentPage()
                .partKontekstMenu()
                .brevoplysning()
                .soeg()
                .fillForm(SkyldnerWarehouse.getSkyldner_test87())
                .activateSoeg()
                .activateSpecificCellKundekontaktID(rowIndex);
        //TODO Assert
    }

    Brevoplysninger_PrimaerSubPage step18Page;
    @Test(dependsOnMethods = {"ATEST_87_step17"})
    public void ATEST_87_step18() {
        System.out.println("Step18");
        //IU_683
        step18Page = step17Page
                .runBatchJob(BatchJobType.AogDRequestStatus, propertyProvider);
        //TODO Assert
    }

    Brevoplysninger_PrimaerSubPage step19Page;
    @Test(dependsOnMethods = {"ATEST_87_step18"})
    public void ATEST_87_step19() {
        System.out.println("Step19");
        //IU_672
        int rowIndex = 0;
        step19Page = step18Page
                .getParentPage()
                .partKontekstMenu()
                .brevoplysning()
                .soeg()
                .fillForm(SkyldnerWarehouse.getSkyldner_test87())
                .activateSoeg()
                .activateSpecificCellKundekontaktID(rowIndex);
        //TODO Assert
    }

    Brevoplysninger_PrimaerSubPage step20Page;
    @Test(dependsOnMethods = {"ATEST_87_step19"})
    public void ATEST_87_step20() {
        System.out.println("Step20");
        //IU_684
        step20Page = step19Page
                .runBatchJob(BatchJobType.SaveSentLetters, propertyProvider);
        TO_Tools.sleep(900000);  //Vent 15minutter inden næste trin må køres
        step20Page
                .runBatchJob(BatchJobType.DKRSCFN_JournalizeNotes, propertyProvider);
        //TODO Assert
    }

    Brevoplysninger_PrimaerSubPage step21Page;
    @Test(dependsOnMethods = {"ATEST_87_step20"})
    public void ATEST_87_step21() {
        System.out.println("Step21");
        //TODO Assert Kontroller, at der ikke maskinelt er angivet en partshøringsfrist - Jeg forventer ikke, at der er registreret en partshøringsfrist
    }

    Brevoplysninger_PrimaerSubPage step22Page;
    @Test(dependsOnMethods = {"ATEST_87_step21"})
    public void ATEST_87_step22() {
        System.out.println("Step22");
        //Vælg, at du manuelt vil registrere en partshøringsfrist. Registrer fristen til den 11. juli 2017
        //TODO Assert Jeg forventer, at jeg kan registrere en partshøringsfrist, og at jeg kan registrere den stil den 11. juli 2017
    }
}