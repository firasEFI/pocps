package findus.T6Flows;

import findus.BaseTest;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;
import findus_pageobjects.opgave.OpgaveFremrykParm;
import findus_pageobjects.opgave.Opgave_PrimaerSubPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblikPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblik_PrimaerSubPage;
import findus_pageobjects.sagsbehandlingsskridt.OpretAfgoerelsesbrevBrevParm;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerAfdragsordningPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerPaakravssagPage;
import findus_pageobjects.wizards.afdragsordning.AfdragsordningWizardParm;
import findus_pageobjects.wizards.kundekontakt.Kundekontakt;
import org.testng.annotations.Test;

public class ATEST_25 extends BaseTest {

    private BasePsrmPage step1Page;

    /**
     * Step: Log ind som sagsbehandler -
     * Data:
     * Result: Du er logget ind.
     */
    @Test
    public void ATEST_25_step1(){

        step1Page = new LoginPage()
                .LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler()); //change to betalingssagsbehandler
//        TODO Assert is logged in!!
    }

    private OpgaveOverblikPage step2Page;

    /**
     * Step: IU-231 Tilgå opgaverne om behandling af dækningsløs indbetaling fra skyldner NN6, som blev oprettet i VAL-1122 step 9 Vælg kontekstmenu for Menu
     * Data: IS-5883 AK 6.1 IS-9342 AK 1
     * Result: Opgaverne kan tilgås. Skærmbillede opgave overblik åbner.
     */
    @Test(dependsOnMethods = {"ATEST_25_step1"})
    public void ATEST_25_step2(){

        step2Page = step1Page.iu_231_gaaTilOpgaveOverblik.execute();
//        TODO Assert result
    }

    private Opgave_PrimaerSubPage step3Page;

    /**
     * Step: IU-596 og IU-601 Klik på den ønskede opgave vedr. behandling af dækningsløs indbetaling og tildel den til dig selv. Skærmbillede: Opgave overblik
     * Data:
     * Result: Skærmbillede Opgave åbner
     */
    @Test(dependsOnMethods = {"ATEST_25_step2"})
    public void ATEST_25_step3(){

//    TODO Slet, testdata til udvikling
        OpgaveFremrykParm parm = new OpgaveFremrykParm();
        parm.setBruger("IFK");
        parm.setDetaljer("test detaljer");
        parm.setRolle("DK-TEST");
        parm.setSendTil(OpgaveFremrykParm.TildelType.BRUGER);

//        End TODO
/*
        step3Page = step2Page
            .clickOpgave()
            .activatePrimaer()
            .activateOpgave_PrimærSubPageTildelPopupWindow()
            .fillFormFremrykOpgaveOK(parm)
            .activateOK();
//        TODO Assert result
*/
    }

    private BasePsrmPage step4Page;
    /**
     * Step: IU-593 Løs opgaverne ved at starte inddrivelse af fordringerne på ny gennem oprettelse af afdragsordningsag af type betalingsevne ud fra tabeltræk.
     * Data: IS-7330
     * Result: Skærmbillede for at oprette afdragsordningsag af type betalingsevne ud fra tabeltræk er åbent
     */
    @Test(dependsOnMethods = {"ATEST_25_step3"})
    public void ATEST_25_step4(){

//        TODO Slet, testdata til udvikling
        AfdragsordningWizardParm afdragsordningWizardParm = new AfdragsordningWizardParm();
        afdragsordningWizardParm.setVaelgAfdOrdnType(AfdragsordningWizardParm.VaelgAfdOrdnType.PAL);
        afdragsordningWizardParm.setNoteValgTypeAfdOrdn("note");
        afdragsordningWizardParm.setNoteValgAfdrBeloeb("note");
        afdragsordningWizardParm.setAdresseType(AfdragsordningWizardParm.AdresseType.DBD);

        OpretAfgoerelsesbrevBrevParm opretAfgoerelsesbrevBrevParm = new OpretAfgoerelsesbrevBrevParm();
        opretAfgoerelsesbrevBrevParm.setDatoFoersteAfd("01-10-2017");
        opretAfgoerelsesbrevBrevParm.setAktvAfdOrdnMedDetSamme(true);

        Kundekontakt kundekontakt = new Kundekontakt();
        kundekontakt.setTilfoejOcrLinje(true);

        step3Page.getParentPage().menu()
                .a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(SkyldnereOgFordringsHavereSearchArgs.createSearchArgsForIdFromSkyldner(SkyldnerWarehouse.getSkyldner_testPerson()))
                .activateSoeg()
                .selectFirstSearchResultNavn();

//        End TODO

        step5Page = step4Page.iu_593_opretAfdragsOrdning.execute(afdragsordningWizardParm, opretAfgoerelsesbrevBrevParm, kundekontakt);
//        TODO Assert result
    }

    private BasePsrmPage step5Page;

    /**
     * Step: IU-246 og IU-606 Du skal nu åbne skyldnerens afdragsordningssag.
     * Data: IS-5490: AK 4 IS-3958: AK 1 & 2
     * Result: Jeg forventer at få vist afdragsordningssagen.
     */
    @Test(dependsOnMethods = {"ATEST_25_step4"})
    public void ATEST_25_step5(){

        step6Page = step5Page.iu_246_aabnSagsoverblikketForSkylder.execute().iu_606_aabnSkyldnersSag.execute_Afdragsordning("");
//        TODO Assert result and fix parms in 606 execute
    }

    private Sagsbehandlingsskridt_PrimaerAfdragsordningPage step6Page;

    /**
     * Step: Du skal nu kontrollere, at sagen har status "Oprettet - inaktiv", og at velkomstbrevet med partshøring fremgår af listen over dokumentkort, der er tilknyttet sagen.
     * Data: IS-4042: AK 7.2 IS-7888: AK 1, 2, 2.1-2.7
     * Result: Jeg forventer, at afdragsordningssagen har status "oprettet - inaktiv".
     * Jeg forventer at kunne se følgende oplysninger om velkomstbrevet med partshøring:
     * - dokumentkortets titel
     * - det unikke journaliserings-ID-nummer, der er tildelt dokumentkortet i SKATs ESDH-system Workzone
     * - parter
     * - dokumenttype
     * - dato
     * - hvor mange filer, der er links til på dokumentkortet
     */
    @Test(dependsOnMethods = {"ATEST_25_step5"})
    public void ATEST_25_step6(){
//        step6Page
//        TODO Assert result
    }

    private BasePsrmPage step7Page;

    /**
     * Step: Du skal nu vælge at oprette et afgørelsesbrev og vælge skabelonen "afgørelse afdragsordning med betalingsevne tabel". (kontroller, at du kun har mulighed for at vælge denne skabelon).
     * Data: IS-4490: AK 1, 1.1, 3, 3, 7
     * Result: Jeg forventer at kunne vælge en skabelon for afgørelse af typen "afgørelse afdragsordning med evne tabel". Jeg forventer kun at kunne vælge skabelonen af typen "afgørelse afdragsordning med evne tabel".
     */
    @Test(dependsOnMethods = {"ATEST_25_step6"})
    public void ATEST_25_step7(){
//        TODO Assert result
    }

    private BasePsrmPage step8Page;

    /**
     * Step: Du skal nu kontrollere, at du bliver bedt om at angive en betalingsfrist for første afdrag
     * Data: IS-12232: AK 1 & 4
     * Result: Jeg forventer, at løsningen beder mig om at angive en betalingsfrist for første afdrag
     */
    @Test(dependsOnMethods = {"ATEST_25_step7"})
    public void ATEST_25_step8(){
//        TODO Assert result
    }

    private BasePsrmPage step9Page;

    /**
     * Step: Du skal nu angive den første betalingsdato som den 1. i førstkommende måned (og mindst 8 bankdage før den 1.)
     * Data: IS-12232: AK 5
     * Result: Jeg forventer, at jeg kan angive første betalingsdato til den 1. i førstkommende måned)
     */
    @Test(dependsOnMethods = {"ATEST_25_step8"})
    public void ATEST_25_step9(){
//        TODO Assert result
    }

    private BasePsrmPage ste10Page;

    /**
     * Step: Kontroller, at skyldner er valgt som default modtager og at skyldners adresse er valgt som default.
     * Data: IS-10019: AK 1 & 2
     * Result: Jeg forventer, at skyldner og skyldners adresse er udfyldt som default.
     */
    @Test(dependsOnMethods = {"ATEST_25_step9"})
    public void ATEST_25_step10(){
//        TODO Assert result
    }

    private BasePsrmPage step11Page;

    /**
     * Step: Kontroller, at der kan indtastes en fritekst i fristekstfeltet på brevet. Kontroller også i denne forbindelse, at der kan foretages linjeskift i friteksten, samt at der kan kopieres tekst ind i fritekstfeltet. Skriv: Afdragsordning er oprettet efter aftale med skyldner
     * Data: IS-13355: AK 4, 5
     * Result: Der kan indtastes fritekst i fritekstfeltet, der kan foretages linjeskift, og der kan kopieres tekst ind i fritekstfeltet.
     */
    @Test(dependsOnMethods = {"ATEST_25_step10"})
    public void ATEST_25_step11(){
//        TODO Assert result
    }

    private BasePsrmPage step12Page;

    /**
     * Step: Du skal nu vælge at se et preview af brevet. Gem preview eller tag et billede, da du vil få behov for at kunne krydsreferere med oplysningerne senere i testcasen. Se vedhæftede eksempel på brev til hjælp
     * Data: IS-10023: AK 1
     * Result: Jeg forventer at se et preview af brevet med følgende oplysninger
     * - afdragsfrekvens: månedlig
     * - afdragsbeløb: 1850 kroner
     * - dato for betaling af første afdrag: 15-08-2017
     * - sagsbehandlernavn
     * - afgørelsesID

     Jeg forventer, at der indgår forventede oplysninger og tekst jf. vedhæftede breveksempel
     */
    @Test(dependsOnMethods = {"ATEST_25_step11"})
    public void ATEST_25_step12(){
//        TODO Assert result
    }

    private BasePsrmPage step13Page;

    /**
     * Step: Påkravsbrev er vedhæftet som bilag til brevet
     * Data: IS-13355: AK 6
     * Result: Jeg forventer påkravsbrev er vedhæftet som bilag
     */
    @Test(dependsOnMethods = {"ATEST_25_step12"})
    public void ATEST_25_step13(){
//        TODO Assert result
    }

    private BasePsrmPage step14Page;

    /**
     * Step: Kontroller, at felterne er udfyldt i brevhovedet.
     * Data: IS-10023: AK 1 IS-13804: AK 2, 2.1, 2.2., 2.3, 2.4, 3, 3.1, 3.2
     * Result: Jeg forventer, at felterne er udfyldt i brevhovedet.
     */
    @Test(dependsOnMethods = {"ATEST_25_step13"})
    public void ATEST_25_step14(){
//        TODO Assert result
    }

    private BasePsrmPage step15Page;

    /**
     * Step: Du skal nu godkende brevet og sende det til A&D
     * Data: IS-10024: AK 1 & 2
     * Result: Jeg forventer at brevet sendes, når jeg godkender det.
     */
    @Test(dependsOnMethods = {"ATEST_25_step14"})
    public void ATEST_25_step15(){
//        TODO Assert result
    }

    private BasePsrmPage step16Page;

    /**
     * Step: (Sæt tiden frem indtil brevet er afsendt fra A&D og journaliseret i Workzone)
     * Data:
     * Result:
     */
    @Test(dependsOnMethods = {"ATEST_25_step15"})
    public void ATEST_25_step16(){
//        TODO Assert result
    }

    private BasePsrmPage step17Page;

    /**
     * Step: Du skal nu kontrollere, at det afsendte brev fremgår af listen over dokumenkort, der er tilknyttet sagen.
     * Data: IS-7888: AK 1 & 2
     * Result: Jeg forventer at kunne se det afsendte brev som et dokumentkort i listen over dokumentkort, der er tilknyttet sagen. Jeg forventer, at følgende oplysninger fremgår:
     * - dokumentkortets titel
     * - det unikke journaliserings-ID-nummer, der er tildelt brevet i SKATs ESDH-system Workzone
     * - parter
     * - dokumenttype
     * - dato
     * - hvor mange filer, der er links til på dokumentkortet
     */
    @Test(dependsOnMethods = {"ATEST_25_step16"})
    public void ATEST_25_step17(){
//        TODO Assert result
    }

    private BasePsrmPage step18Page;

    /**
     * Step: Du skal nu åbne dokumentkortet og kontrollere oplysningerne på dokumentkortet.
     * Data: IS-7089: AK 1 & 4 IS-8412: AK 1 & 2 IS-5389: 5
     * Result: Jeg forventer, at følgende oplysninger fremgår af dokumentkortet:
     * -Titel: "Brev: Afgørelse afdragsordning med evne tabel"
     * -Dokumentkortets ID: En talværdi
     * -Parter: Skyldner 050575-1787
     * -Dokumenttype: "Udgående"
     * -Dato: 05-08-2017
     * -Oprindelse: "ICI"
     * -Afdeling/kontor: "Personrestancer 10"
     * -Tilstand: "Arkiveret"
     * -Forsendelsesstatus: "Sendt via digital post"

     */
    @Test(dependsOnMethods = {"ATEST_25_step17"})
    public void ATEST_25_step18(){
//        TODO Assert result
    }

    private BasePsrmPage step19Page;

    /**
     * Step: Du skal nu kontrollere, at det afsendte brev vises som et link på dokumentkortet.
     * Data: IS-7089: AK 5
     * Result: Jeg forventer, at det afsendte brev vises som et link på dokumentkortet.
     */
    @Test(dependsOnMethods = {"ATEST_25_step18"})
    public void ATEST_25_step19(){
//        TODO Assert result
    }

    private BasePsrmPage step20Page;

    /**
     * Step: Du skal nu klikke på linket på dokumentkortet.
     * Data: IS-8413: AK 1
     * Result: Jeg forventer at det afsendte brev kan åbnes direkte fra dokumentkortet, der er tilknyttet fordringen (afhængig af browseropsætningen og filtype kan det være, at filen først downloades og derefter skal åbnes).
     */
    @Test(dependsOnMethods = {"ATEST_25_step19"})
    public void ATEST_25_step20(){
//        TODO Assert result
    }

    private BasePsrmPage step21Page;

    /**
     * Step: Kontroller, at brevet er identisk med det preview du fik vist i step 14.
     * Data: IS-8413: AK 1
     * Result: Jeg forventer, at det afsendte brev er identisk med det brev, jeg fik vist i preview.
     */
    @Test(dependsOnMethods = {"ATEST_25_step20"})
    public void ATEST_25_step21(){
//        TODO Assert result
    }

    private BasePsrmPage step22Page;

    /**
     * Step: Du skal nu forsøge at redigere i brevet.
     * Data: IS-4860: AK 1 & 2
     * Result: Jeg forventer, at jeg ikke kan redigere i brevet.
     */
    @Test(dependsOnMethods = {"ATEST_25_step21"})
    public void ATEST_25_step22(){
//        TODO Assert result
    }

    private BasePsrmPage step23Page;

    /**
     * Step: RELEASE 1.1 Du skal nu åbne afdragsordningssagen og kontrollere, at status er skiftet til "Aktiv" samt at afgørelses ID'et fremgår af sagen.
     * Data: IS-4490: AK 7 IS-7351: AK 1 IS-7343: AK 1
     * Result: Jeg forventer, at status på afdragsordningssagen er skiftet til aktiv, og at afgørelses ID'et fremgår af sagen.
     */
    @Test(dependsOnMethods = {"ATEST_25_step22"})
    public void ATEST_25_step23(){
//        TODO Assert result
    }

    private BasePsrmPage step24Page;

    /**
     * Step: Tilgå de ni opgaver én for én og afslut hver enkelt opgave med årsag "Løst" fra drop-down menu
     * Data: IS-5472 AK 1, 2, 2.2, 3-6
     * Result: Opgaverne er alle afsluttet og fremgår med status: "lukket".
     * Opgaverne vises også med følgende information:
     * -afslutningsdato: 05.08.2017
     * -hvilken sagsbehandler, der har afsluttet opgaven ved dennes W-nr.
     */
    @Test(dependsOnMethods = {"ATEST_25_step23"})
    public void ATEST_25_step24(){
//        TODO Assert result
    }
}
