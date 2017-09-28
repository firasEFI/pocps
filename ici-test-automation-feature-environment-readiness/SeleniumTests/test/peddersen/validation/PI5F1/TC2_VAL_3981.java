package peddersen.validation.PI5F1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.testng.annotations.Test;
import findus_controllers.ApplicationController;
import findus_datamodelWarehouses.RoleMapWarehouse;
import findus_datamodels.BeregnetBetalingsevne;
import findus_datamodels.BetalingsevneEnums;
import findus_datamodels.CprSkyldnerModel;
import findus_datamodels.GrundlagForBetalingsevneAarligModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_KontoSubPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_PartsoplysningerSubPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblikPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerPaakravssagPage;
import findus_pageobjects.wizards.opret_paakravssag.OpretPaakravssagWizardParm;
import findus_pageobjects.wizards.opret_paakravssag.OpretPaakravssagWizardParm.Inddrivelsesskridt;
import findus_pageobjects.wizards.opret_paakravssag.OpretPaakravssagWizardParm.Paakravstype;
import icisel.taxobjects.Fordring;
import icisel.testng.PropertyProvider;
import peddersen.BaseTest_Peddersen;
import utils.PropertyProviderImpl;
import utils.tools.TO_Tools;

public class TC2_VAL_3981 extends BaseTest_Peddersen {
    PropertyProvider pp = new PropertyProviderImpl();

    String cprNummer = "0505562631"; //Skyldner type A

    //Fordringsdata
    String HF1_beloeb = "800";
    String GHF1_beloeb = "100";
    String HF2_beloeb = "1200";

    List<Fordring> fordringer = new ArrayList<>();

//    @BeforeMethod
//    public void setup() {
//        // OPSÆTNING
//        Engine.getDriver().setTimeout(15000);
//
//        //Fordringer
//        Fordring HF1 = new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, cprNummer, HF1_beloeb);
//        Fordring GHF1 = new Fordring(Fordringstype.DR_GEBYR_OPKRAEVNING, cprNummer, GHF1_beloeb);
//        Fordring HF2 = new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, cprNummer, HF2_beloeb);
// 
//        BasePsrmPage prestepPage = new ApplicationController(pp).startAtLoginNormal().LoginMedValidBruger(new PsrmUsersWarehouse().getGenerelSagsbehandler());
//        MO_Fordring.opretFordring(this, HF1);
//        MO_Fordring.opretFordring(this, GHF1);
//        MO_Fordring.opretFordring(this, HF2);
//        //        doLogout();
//        fordringer.add(HF1);
//        fordringer.add(GHF1);
//        fordringer.add(HF2);
//
//    }

    @Test(groups = { "PI5F1" }, invocationCount = 1)
    public void main() throws Exception {  

//Step 1: Timetravel til tidspunkt for VAL-3979 (Start af testflow) + 3 dage

        

//Step 2: Indlæs tabel fra Datawarehouse med de oplysninger, der er angivet i testdata



//Step 3: Log ind med rollen "sagsbehandler - generel"
        // Login som sagsbehandler
        BasePsrmPage step3Page = new ApplicationController(pp).startAtLoginSSO().login(RoleMapWarehouse.SAGSBEHANDLER_GENEREL);        


//Midlertidigt step: Fremsøg skyldner //FIXME
        CprSkyldnerModel skyldner = new CprSkyldnerModel();
        skyldner.setCprNummer(cprNummer);

        _360GradersOverblik_PartsoplysningerSubPage midlStep1Page = step3Page.iu_213_fremsoegSkyldner.execute(skyldner);
        

//Alternativt step: Anskaf psrmDato
        _360GradersOverblik_KontoSubPage altStep1Page = midlStep1Page.getParentPage().iu_661_fremsoegSystemdatoViaSkyldnersKontoFane.execute();
        Date psrmDato = altStep1Page.getPsrmDate();


//Step 4: Du skal nu tilgå opgaveoversigten og kontrollere, at der er oprettet en opgave med opgavetypen "Send påkrav"
//IU-231 og IU-596
        //Først gås der til opgaveoverblikket, hvorefter søgekriterierne indtastes og opgaven fremsøges
        //TODO Erstat altStep1Page.getParentPage() med step3Page
        OpgaveOverblikPage step4Page = altStep1Page.getParentPage().iu_231_gaaTilOpgaveOverblik.execute();
        
        
        


//Step 5: Du skal nu åbne opgaven og kontrollere oplysningerne om opgaven

        

//Step 6: Registrer grundlaget for betalingsevne efter tabeltræk så betalingsevnen bliver 900 kr./måned - IU-235
        //Definer værdier til udfyldelse i grundlaget:     //TODO Opdater værdier så de passer med 900   
        GrundlagForBetalingsevneAarligModel grundlag = new GrundlagForBetalingsevneAarligModel();
        grundlag.setBruttoindkomst(197439d);
        grundlag.setNettoindkomst(189440d);
        grundlag.setAar(GrundlagForBetalingsevneAarligModel.Aar._2016);
        grundlag.setAfdrag(BetalingsevneEnums.Afdrag._12);
        grundlag.setBeregnetAarligAfdrag(22733d);
        grundlag.setAarsopgørelsesnr(1);
        grundlag.setForsoegerPligt(false);

        //Registrerer grundlaget via IU-235 //TODO Erstat altStep1Page.getParentPage() med step5Page
        BasePsrmPage step6Page = altStep1Page.getParentPage().iu_235_registreringAfGrundlagForSkyldnersBetalingsevne_AarligIndkomst.execute(grundlag);


//Step 7: Registrer den beregnede betalingsevne på 900 kr./måned - IU-236
        //Definer værdier til udfyldelse i betalingsevnen:
        BeregnetBetalingsevne betalingsevne = new BeregnetBetalingsevne();
        betalingsevne.setMaanedligtBetalingsevneTabelTraek(900d);
        betalingsevne.setAnvendIInddrivelsesskridt(BeregnetBetalingsevne.AnvendIInddrivelsesskridt.TABELTRAEK);
        betalingsevne.setBeregnetBetalingsevneDato(TO_Tools.getPsrmDatoPlus_DDMMYYYY_Calendar(psrmDato, 0));

        //Registrer betalingsevnen via IU-236: //TODO Erstat altStep1Page.getParentPage() med step6Page
        BasePsrmPage step7Page = altStep1Page.getParentPage().iu_236_registreringAfBeregnetBetalingsevne_Tabeltraek.execute(betalingsevne);


//Step 8: Gå til skærmbillede for oprettelse af en påkravssag og opret en påkravssag. Tilføj HF1, GHF1, og HF2 
        //til påkravssagen - IU-227
        //Definering af værdier for påkravssagen samt de inkluderede fordringer
        OpretPaakravssagWizardParm paakravssag = new OpretPaakravssagWizardParm();
        paakravssag.setInddrivelsesskridt(Inddrivelsesskridt.Paakrav);
        paakravssag.setPaakravstype(Paakravstype.Med_partshoering);
        paakravssag.getFordringer().addAll(fordringer); //TODO Opdater fordringsID'er

        //Oprettelse af påkravssagen via IU-227: 
        Sagsbehandlingsskridt_PrimaerPaakravssagPage step8Page = step7Page.iu_227_danVelkomstbrevForKundeMedPartshoering.execute(paakravssag);

        
//Step 9: Opret og send et velkomstbrev med parthøring - IU-647 


    }
}
