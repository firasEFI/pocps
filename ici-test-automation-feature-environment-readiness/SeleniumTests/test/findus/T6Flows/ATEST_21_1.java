package findus.T6Flows;

import java.util.Calendar;
import findus_pageobjects.wizards.kundekontakt.KundekontaktWizardPageParm;
import findus_controllers.ApplicationController;
import findus_pageobjects.MockSSOPortalPage;
import findus_pageobjects.wizards.kundekontakt.Kundekontakt;
import icisel.testng.PropertyProvider;
import icisel.utils.driver.Engine;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import findus.BaseTest;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_datamodels.BeregnetBetalingsevne;
import findus_datamodels.BetalingsevneEnums;
import findus_datamodels.GrundlagForBetalingsevneAarligModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblikPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_SagsbehandlingSubPage;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;
import findus_pageobjects.brevoplysninger.BrevoplysningerPage;
import findus_pageobjects.brevoplysninger.Brevoplysninger_PrimaerSubPage;
import findus_pageobjects.sagsbehandlingsskridt.OpretVelkomstEllerPaakravsBrevPopupWindow;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerPaakravssagPage;
import findus_pageobjects.wizards.kundekontakt.KundekontaktWizardPage;
import findus_pageobjects.wizards.opret_paakravssag.OpretPaakravssagWizardPage;
import utils.PropertyProviderImpl;
import utils.batchJob.BatchJobType;

/**
 * Created by nielsjes on 23-08-2017.
 */
public class ATEST_21_1 {

    public ApplicationController applicationController;

    public LoginPage loginPage;

    public PropertyProvider propertyProvider;

    public  ATEST_21_1() {

        propertyProvider = new PropertyProviderImpl();
        applicationController = new ApplicationController(propertyProvider);
        loginPage = applicationController.startAtLoginNormal(true);
    }

    @AfterClass
    public void Logout(){
        step21Page.getParentPage().logout();

    }

    @Test
    public void ATEST_21_1_step1() {

        // Step 1: login med sagsbehandler (Generel)
        step1Page = loginPage.LoginMedValidBruger(new PsrmUsersWarehouse().getGodkenderSagsbehandler());
    }

    BasePsrmPage step1Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step1" })
    public void ATEST_21_1_step2() {

        // Step 2: Fremsøg skyldner ved at angive skyldners CPR-nr. i 360
        // graders søgningen.
        // TODO: Benyt IU-573
        step2Page = step1Page.iu_573_fremsoegSkyldnerVisKontoOverblik.execute(SkyldnereOgFordringsHavereSearchArgs
                .createSearchArgsForIdFromSkyldner(SkyldnerWarehouse.getSkyldner_testPerson())).getParentPage(); // TODO
                                                                                                                 // not
                                                                                                                 // right
                                                                                                                 // IU
                                                                                                                 // -
                                                                                                                 // goes
                                                                                                                 // to
                                                                                                                 // far,
                                                                                                                 // no
                                                                                                                 // need
                                                                                                                 // to
                                                                                                                 // press
                                                                                                                 // konto.

    }

    // TODO: Assert - Skyldner bliver vist på resultatlisten, og jeg kan trykke
    // mig ind på skyldners objekt.

    _360GradersOverblikPage step2Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step2" })
    public void ATEST_21_1_step3() {
        // Step 3: Vælg at du vil oprette en påkravssag med betalingsevne
        // (partshøring)
        step3Page = step2Page
                .kontoKontekstMenu()
                .opretPaakravssag()
                .selectPaakravSomInddrivelsesSkridt()
                .selectPaakravsTypeMedPartshoring()
                .activateVaelgAlt()
                .activateOpretExpectFail()
                .activateAnnuller();

        // TODO: Assert: Jeg forventer, at jeg *ikke* kan vælge at oprette en
        // påkravssag (-skrivelse) med partshøring, da der ikke er registreret
        // en betalingsevne på skyldner
    }

    BasePsrmPage step3Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step3" })
    public void ATEST_21_1_step4() {
        // Point of no return, user is unusable!
        // Step 4: Registrering af grundlag for skyldners betalingsevne

        GrundlagForBetalingsevneAarligModel grundlag = new GrundlagForBetalingsevneAarligModel();
        grundlag.setWebServiceCallMethod(GrundlagForBetalingsevneAarligModel.WebServiceCallMethod._manuelIndtastning);
        grundlag.setBruttoindkomst(197439d);
        grundlag.setNettoindkomst(189440d);
        grundlag.setAar(GrundlagForBetalingsevneAarligModel.Aar._2016);
        grundlag.setAfdrag(BetalingsevneEnums.Afdrag._12);
        grundlag.setBeregnetAarligAfdrag(22733d);
        grundlag.setAarsopgørelsesnr(1);
        grundlag.setForsoegerPligt(false);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        step4Page = step3Page.iu_235_registreringAfGrundlagForSkyldnersBetalingsevne_AarligIndkomst.execute(grundlag);
        SkyldnerWarehouse.skyldnerUnuseable();

        // TODO: Assert: Skærmbíllede: Sagsbehandlingsskridt: Grundlag for
        // betalingsevne,Aktiv åbner
    }

    BasePsrmPage step4Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step4" })
    public void ATEST_21_1_step5() {

        BeregnetBetalingsevne betalingsevne = new BeregnetBetalingsevne();

        betalingsevne.setMaanedligtBetalingsevneTabelTraek(1000d);
        betalingsevne.setAnvendIInddrivelsesskridt(BeregnetBetalingsevne.AnvendIInddrivelsesskridt.TABELTRAEK);
        betalingsevne.setBeregnetBetalingsevneDato(Calendar.getInstance());
        // Step 5: Registrering af beregnet betalingsevne
        step5Page = step4Page.iu_236_registreringAfBeregnetBetalingsevne_Tabeltraek.execute(betalingsevne);

        // TODO: Assert: Skærmbilledet Grundlag for betalingsevne åbner.
    }

    BasePsrmPage step5Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step5" })
    public void ATEST_21_1_step6() {
        // Step 6: Vælg at du vil oprette en påkravssag med betalingsevne
        // (partshøring)
        // TODO: IU-591 Vælg at du vil oprette en påkravssag med betalingsevne
        // (partshøring)
        step6Page = step5Page
                .kontoKontekstMenu()
                .opretPaakravssag();

        // TODO: Assert: Skærmbillede Opret påkravssag åbner
    }

    OpretPaakravssagWizardPage step6Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step6" })
    public void ATEST_21_1_step7() {

        // Step 7: Vælg påkravstype: Påkrav med partshøring og tryk på Vælg alt
        // (for alle fordringer) og tryk på knappen Opret
        // TODO: IU-591
        step7Page = step6Page
                .selectPaakravSomInddrivelsesSkridt()
                .selectPaakravsTypeMedPartshoring()
                .activateVaelgAlt()
                .activateOpret();

        // TODO: Assert: Jeg kan oprette en påkravssag omfattende de ønskede
        // fordringer. Returnerer til skærmbilledet Sagsbehandlingsskridt
    }

    Sagsbehandlingsskridt_PrimaerPaakravssagPage step7Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step7" })
    public void ATEST_21_1_step8() {
        // step8: Tryk på knappen Opret velkomst/påkravsbrev. Popup vindue åbner
        // - vælg korrekt type (påkravsbrev - med parthøring)
        // TODO: IU-589 Tryk på knappen Opret velkomst/påkravsbrev. Popup vindue
        // åbner - vælg korrekt type (påkravsbrev - med parthøring)
        step8Page = step7Page
                .activateOpretVelkomstOgPaakravsbrev()
                .fillFormUdenGebyr(OpretVelkomstEllerPaakravsBrevPopupWindow.TypeAfBrev.VELKOMSTBREV_MED_PARTSHOERING,
                        "test årsag")
                .activateOk();

        // TODO: Assert: Skærmbillede for sagsbehandlingsskridt Påkrav oprettet
        // åbner
    }

    Sagsbehandlingsskridt_PrimaerPaakravssagPage step8Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step8" })
    public void ATEST_21_1_step9() {
        // step9: Via skærmbillede Sagsbehandlingsskridt, Påkrav oprettet
        // trykkes på blå tekst ud for Påkrav ( SKAT inddrivelse Test person -
        // påkravsbrev med partshøring - under behandling)
        step9Page = step8Page.activateGaaTilPaakravsbrev();

        // TODO: Assert: Jeg forventer, at skærmbillede for brevoplysninger
        // åbner
    }

    Brevoplysninger_PrimaerSubPage step9Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step9" })
    public void ATEST_21_1_step10() {
        // step10: Tryk på knappen Rediger
        step10Page = step9Page
                .activateRediger();

        // TODO: Assert: Skærmbillede for kundekontakt åbner
    }

    KundekontaktWizardPage step10Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step10" })
    public void ATEST_21_1_step11() {
        // step11: Kontroller at skyldners modtageradresse vises
        // TODO check om modtageradresse er rigtig
        step10Page.getFormData().getModtagerAdresse();

        step11Page = step10Page;

        // TODO: Assert: Skyldners modtageradresse vises
    }

    KundekontaktWizardPage step11Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step11" })
    public void ATEST_21_1_step12() {
        // step12: Vælg at du vil sende brevet ved fysisk forsendelse ved at
        // fjerne flueben ud for feltet Digital post. Nyt popup vindue åbner -
        // du skal bekræfte, at du gerne vil sende brevet ved fysisk forsendelse
        // - sig OK, popup vindue lukker ned.
        // Tryk på Gem
        Kundekontakt kundekontakt = new Kundekontakt();
        kundekontakt.setAtt("testAddresse");
        kundekontakt.setDigitalPost(false);

        step12Page = step11Page.fillForm(kundekontakt).activateGem().getParentPage();

        // TODO: Assert: Jeg kan vælge, at sende brevet ved fysisk forsendelse
        // efter at jeg har bekræftet dette i et nyt popup vindue. Skærmbillede
        // Brevoplysninger åbner
    }

    BrevoplysningerPage step12Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step12" })
    public void ATEST_21_1_step13() {
        // step13: Tryk på knappen Generer udkast
        step13Page = step12Page.activatePrimaer().activateGenererUdkast();

        // TODO: Assert: Skærmbillede for Brevoplysninger åbner
    }

    Brevoplysninger_PrimaerSubPage step13Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step13" })
    public void ATEST_21_1_step14() {
        // step14: Tryk på Godkend udkast.
        // TODO check popup window = "Du skal se udkastet, før det kan
        // godkendes."
        step14Page = step13Page.activateGodkendUdkast();

        // TODO: Assert: Det er ikke muligt at godkende udkast, da du ikke har
        // set et preview af brevet inden godkendelse
    }

    Brevoplysninger_PrimaerSubPage step14Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step14" })
    public void ATEST_21_1_step15() {
        // step15: Vælg at du vil se et preview (udkast) af påkravsskrivelsen
        // (ny browser med åbner: Der er et problem med dette websteds
        // sikkerhedscertifikat) Vælg Continue to this website (not
        // recommended)= udkast vises i browseren
        // Luk browseren ned. Du er fortsat på skræmbilledet Brevoplysninger
        // TODO check om udkast er blevet lavet, ellers loop til den er
        // færdig..?
        step15Page = step14Page.activateSeUdkast();

        // TODO Assert:
        /*
         * Kontroller, at brevskabelonen er korrekt udfyldt på baggrund af data
         * i PSRM. Du skal blandt andet kunne se følgende oplysninger:
         * 
         * Teksten vedr. forsørgerpligt:
         * "Vi har lagt til grund, at du ikke har forsørgerpligt over for børn"
         * Teksten vedr. gæld til inddrivelse:
         * "Du kan se hvilken gæld, det drejer sig om i den vedlagte opgørelse. Gælden er pr. opgjort til *. inklusive renter og gebyrer.*"
         * 
         * Kontroller, at følgende felter i brevskabelonen er korrekt udfyldt:
         * Opgørelsesdato: PartshøringFristDato: * AfdragBeløb: .
         * SamletSimuleretRenteBeløb: (inklusiv opkrævningsrenter)
         * FordringSumSamletInklusivSimuleretRenteBeløb: * OCR-linje: samme som
         * den, der er knyttet til påkravssag
         * 
         * skærmbillede Brevoplysninger
         */
    }

    Brevoplysninger_PrimaerSubPage step15Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step15" })
    public void ATEST_21_1_step16() {
        // step16: man kan godt godkende selvom man ikke har set det færdige
        // udkast?
        // TODO: IU-589
        step16Page = step15Page
                .activateGodkendUdkast()
                .activateSendTilAogD();

        // TODO: Assert: Skærmbillede Brevoplysninger åbner med ny status =
        // Sendt til AogD
    }

    Brevoplysninger_PrimaerSubPage step16Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step16" })
    public void ATEST_21_1_step17() {
        // step17: Gå til skyldners 360 graders overblik, (Vis konto kontekst i
        // dashboardet), vælg fanen sagsbehandling og vælg påkravssag under
        // Zonen Sager.
        // man kan godt godkende selvom man ikke har set det færdige udkast?
        // TODO check om sag har status = 'Sendt til A&D'
        // TODO check om første sag er den rigtige
        step17Page = step16Page.getParentPage()
                .kontoKontekstMenu()
                .gaaTil360GradersOverblik()
                .getParentPage()
                .activateSagsbehandling();

        // TODO: Assert: Skærmbillede Sagsbehandlingsskridt Påkrav, påkrav
        // oprettet åbner
    }

    _360GradersOverblik_SagsbehandlingSubPage step17Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step17" })
    public void ATEST_21_1_step18() {
        // step18: Tjek at brevet har følgende status: - 'Sendt til A&D'
        step18Page = step17Page
                .selectFirstPaakravssag();

        // TODO: Assert: Jeg forventer, at jeg kan se, at brevet har status
        // 'Sendt til A&D'
    }

    Sagsbehandlingsskridt_PrimaerPaakravssagPage step18Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step18" })
    public void ATEST_21_1_step19() {
        // step19: Tryk på linjen ud for Påkrav, som indeholder sendt til A&D
        step19Page = step18Page
                .activateGaaTilPaakravsbrev();

        // TODO: Assert: Skærmbilledet Brevoplysninger åbner
    }

    Brevoplysninger_PrimaerSubPage step19Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step19" })
    public void ATEST_21_1_step20() {

        step20Page = step19Page
                .activateSeUdkast();

        // TODO: Assert: Jeg forventer, at jeg kan se det dannede brev, og at
        // det ser rigtigt ud i sin helhed.
        // Jeg forventer at jeg ikke kan redigere eller slette i preview af
        // brevet.
    }

    Brevoplysninger_PrimaerSubPage step20Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step20" })
    public void ATEST_21_1_step21() {
        // TODO Kør batch AogDSendLetters, AogDRequestStatus og SaveSentLetters
        step21Page = step20Page
                .runBatchJob(BatchJobType.AogDSendLetters, propertyProvider)
                .runBatchJob(BatchJobType.AogDRequestStatus, propertyProvider)
                .runBatchJob(BatchJobType.SaveSentLetters, propertyProvider);
        // TODO: Assert: Jeg forventer at brev får status: Sendt til skyldner i
        // Skærmbilledet Brevooplyninger
    }

    Brevoplysninger_PrimaerSubPage step21Page;

    @Test(dependsOnMethods = { "ATEST_21_1_step21" })
    public void ATEST_21_1_step22() {
        // TODO Kontroller, at brevet har fået status 'Sendt via fysisk post'

        // TODO: Assert: Jeg forventer, at brevet har status 'Sendt via fysisk
        // post'
    }

    @Test(dependsOnMethods = { "ATEST_21_1_step22" })
    public void ATEST_21_1_step23() {
        // TODO Kontroller, at påkravssagen har fået status 'påkravsskrivelse
        // afsendt'

        // TODO: Assert: Jeg forventer, at påkravssagen har fået status
        // 'påkravsskrivelse afsendt'
    }

    @Test(dependsOnMethods = { "ATEST_21_1_step23" })
    public void ATEST_21_1_step24() {
        // TODO Gå ind på skyldners sagsoverblik Skærmbillede: 360 graders
        // overblik - fanen Sagbehandling

        // TODO: Assert: eg kan få vist sagsoverblikket
    }

    @Test(dependsOnMethods = { "ATEST_21_1_step24" })
    public void ATEST_21_1_step25() {
        // Tjek at du kan se påkravssagen, du lige har oprettet under Zonen
        // Sager

        // TODO: Assert: Påkravssagen fremgår af sagsoverblikket
    }

    @Test(dependsOnMethods = { "ATEST_21_1_step25" })
    public void ATEST_21_1_step26() {
        // TODO Tjek at du kan se datoen for påkravssagens oprettelse - dvs.
        // d.d. og at det fremgår, at sagstypen er "påkrav"

        // TODO:Assert: Jeg kan se, at påkravssagen er oprettet i dag og
        // sagstypen "påkrav" fremgår.
    }

    @Test(dependsOnMethods = { "ATEST_21_1_step26" })
    public void ATEST_21_1_step27() {
        // TODO Tjek det er muligt at klikke på påkravssagen og dermed få vist
        // hvilke fordringer der er omfattet af sagen med fordrings-ID

        // TODO: Assert: Fordringerne fremgår med fordrings-ID i nyt
        // skærmbillede
    }

}
