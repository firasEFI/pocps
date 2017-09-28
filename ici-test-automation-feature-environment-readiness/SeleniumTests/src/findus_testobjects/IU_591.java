package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.brevoplysninger.Brevoplysninger_PrimaerSubPage;
import findus_pageobjects.sagsbehandlingsskridt.OpretVelkomstEllerPaakravsBrevPopupWindow;
import findus_pageobjects.wizards.kundekontakt.Kundekontakt;
import icisel.testng.PropertyProvider;
import utils.PropertyProviderImpl;
import utils.batchJob.BatchJobType;

public class IU_591 {
    PropertyProvider pp = new PropertyProviderImpl();

    BasePsrmPage basePsrmPage;

    public IU_591(BasePsrmPage basePsrmPage) {
        this.basePsrmPage = basePsrmPage;
    }

    public Brevoplysninger_PrimaerSubPage execute(Kundekontakt info) {
        return basePsrmPage
                .kontoKontekstMenu()
                .opretPaakravssag()
                .selectPaakravSomInddrivelsesSkridt()
                .selectPaakravsTypeMedPartshoring()
                .activateVaelgAlt()
                .activateOpret()
                .activateOpretVelkomstOgPaakravsbrev()
                .fillFormUdenGebyr(OpretVelkomstEllerPaakravsBrevPopupWindow.TypeAfBrev.VELKOMSTBREV_MED_PARTSHOERING,"test årsag")
                .activateOk()
                .activateGaaTilPaakravsbrev()
                .activateRediger()
                .fillForm(info)
                .activateGem()
                .activateGenererUdkast()
                .activateGodkendUdkast()
                .activateSeUdkast()
                .activateGodkendUdkast()
                .activateSendTilAogD()
                .getParentPage()
                .kontoKontekstMenu()
                .gaaTil360GradersOverblik()
                .getParentPage()
                .activateSagsbehandling()
                .selectFirstPaakravssag()
                .activateGaaTilPaakravsbrev()
                .activateSeUdkast()
                .runBatchJob(BatchJobType.AogDSendLetters, pp)
                .runBatchJob(BatchJobType.AogDRequestStatus, pp)
                .runBatchJob(BatchJobType.SaveSentLetters, pp);
    }
}
