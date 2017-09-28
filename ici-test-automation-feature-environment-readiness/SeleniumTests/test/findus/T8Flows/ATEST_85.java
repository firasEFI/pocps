package findus.T8Flows;

import findus.BaseTest;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.RoleMapWarehouse;
import findus_datamodels.CprSkyldnerModel;
import findus_datamodels.OpgaveModel;
import findus_datamodels.PsrmUserModel;
import findus_datamodels.Sag;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.Brevoplysninger_soeg.BrevoplysningerSearchArgs;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_SagsbehandlingSubPage;
import findus_pageobjects.brevoplysninger.Brevoplysninger_PrimaerSubPage;
import findus_pageobjects.opgave.Opgave_PrimaerSubPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblik_PrimaerSubPage;
import findus_pageobjects.opgave_overblik.OpgaveSearchArgs;
import findus_pageobjects.sagsbehandlingsskridt.OpretVelkomstEllerPaakravsBrevPopupWindow;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerPaakravssagPage;
import findus_pageobjects.wizards.kundekontakt.Kundekontakt;
import findus_pageobjects.wizards.kundekontakt.KundekontaktWizardPage;
import findus_pageobjects.wizards.opret_paakravssag.OpretPaakravssagWizardParm;
import icisel.taxobjects.Fordring;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ATEST_85 extends BaseTest {

    private final PsrmUserModel user;
    private final CprSkyldnerModel skyldner;
    private final OpgaveModel opgave;
    private final Fordring hf1Politiboede;
    private final Fordring hf2Politiboede;
    private Sag paakravssag;

    public ATEST_85() {
        this.user = new PsrmUsersWarehouse().getGodkenderSagsbehandler();
        this.opgave = new OpgaveModel();
        this.skyldner = new CprSkyldnerModel();
        this.hf1Politiboede = new Fordring();
        this.hf2Politiboede = new Fordring();
    }

    public ATEST_85(PsrmUserModel user, CprSkyldnerModel skyldner, OpgaveModel opgave, Fordring hf1Politiboede, Fordring hf2Politiboede) {
        this.user = user;
        this.skyldner = skyldner;
        this.opgave = opgave;
        this.hf1Politiboede = hf1Politiboede;
        this.hf2Politiboede = hf2Politiboede;
    }

    @Test
    public void executeTest() {
        //Step 1
        BasePsrmPage startside = super.applicationController
                .startAtLoginSSO()
                .login(RoleMapWarehouse.SAGSBEHANDLER_GENEREL);


        //Step 2
        OpgaveSearchArgs opgavesøging = new OpgaveSearchArgs();
        opgavesøging.setOpgaveID(opgave.getOpgaveId());

        OpgaveOverblik_PrimaerSubPage opgaveFremsøgtOgTildelt = startside.iu_231_gaaTilOpgaveOverblik.execute()
                .iu_596_fremsøgOpgaveIOpgaveOverblikket.execute(opgavesøging)
                .iu_601.execute(this.opgave.getOpgaveId());


        //Step 3
        Opgave_PrimaerSubPage opgaveValgt = opgaveFremsøgtOgTildelt.getParentPage().iu_641.execute(user, 0);


        //Step 4
        OpretPaakravssagWizardParm opretPaakravssagParm = new OpretPaakravssagWizardParm();

        opretPaakravssagParm.setInddrivelsesskridt(OpretPaakravssagWizardParm.Inddrivelsesskridt.Paakrav);
        opretPaakravssagParm.setPaakravstype(OpretPaakravssagWizardParm.Paakravstype.Uden_partshoering);
        opretPaakravssagParm.getFordringer().add(this.hf1Politiboede);
        opretPaakravssagParm.getFordringer().add(this.hf2Politiboede);

        Sagsbehandlingsskridt_PrimaerPaakravssagPage paakravssagOprettet = opgaveValgt
                .getParentPage().iu_227_danVelkomstbrevForKundeMedPartshoering.execute(opretPaakravssagParm);


        //Step 5
        _360GradersOverblik_SagsbehandlingSubPage skyldnerSagsoverblik = paakravssagOprettet.getParentPage()
                .iu_246_aabnSagsoverblikketForSkylder.execute();


        //Step 6
        Assert.assertTrue(skyldnerSagsoverblik.containsSag(paakravssag.getSagsId()));


        //Step 7
        Sag sagFraSagsoverblik = skyldnerSagsoverblik.getSag(paakravssag.getSagsId());

        Assert.assertEquals(sagFraSagsoverblik.getOprettelsesdato(), paakravssag.getOprettelsesdato());


        //Step 8
        Sagsbehandlingsskridt_PrimaerPaakravssagPage paakravssagSide = skyldnerSagsoverblik
                .iu_606_aabnSkyldnersSag.execute_Paakrav(this.paakravssag.getSagsId());


        //Step 9
        Assert.assertTrue(paakravssagSide.containsFordring(this.hf1Politiboede.getsFordringsID()));
        Assert.assertTrue(paakravssagSide.containsFordring(this.hf2Politiboede.getsFordringsID()));


        //Step 10
        OpretVelkomstEllerPaakravsBrevPopupWindow opretPaakravSide = paakravssagSide.activateOpretVelkomstOgPaakravsbrev();


        //Step 11
        Assert.assertTrue(opretPaakravSide.canSetGebyr());

        //Step 12
        KundekontaktWizardPage<Brevoplysninger_PrimaerSubPage> kundekontaktSide = opretPaakravSide.activateOk()
                .activateGaaTilPaakravsbrev()
                .activateRediger();

        Assert.assertTrue(kundekontaktSide.canSetDigitalPost());

        //Step 13
        Kundekontakt kundekontakt = kundekontaktSide.getFormData();

        Assert.assertEquals(kundekontakt.getModtager(), "Part - " + skyldner.getFuldeNavn());


        //Step 14
        Assert.assertTrue(kundekontaktSide.canSetOevrigeOplysninger());

        kundekontakt.setFritekstOevrigeOplysninger("Betaling af din gæld");

        Brevoplysninger_PrimaerSubPage brevoplysningerSide = kundekontaktSide.fillForm(kundekontakt)
                .activateGem();

        //Step 15
        brevoplysningerSide.activateGenererUdkast()
                .activateSeUdkast();

        //Tjek preview???


        //Step 16
        //Tjek preview???


        //Step 17
        //Tjek preview???


        //Step 18
        brevoplysningerSide.activateGodkendUdkast()
                .activateSendTilAogD();

        //Step 19
        BrevoplysningerSearchArgs breveSearchArgs = new BrevoplysningerSearchArgs();
        breveSearchArgs.setPart("");

        brevoplysningerSide.getParentPage().iu_720.execute(breveSearchArgs);


        //Step 20


        //Step 21


        //Step 22


        //Step 23


        //Step 24


        //Step 25


    }
}
