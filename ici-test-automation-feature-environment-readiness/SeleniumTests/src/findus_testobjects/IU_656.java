package findus_testobjects;

import findus_pageobjects.sagsbehandlingsskridt.OpretVelkomstEllerPaakravsBrevPopupWindow;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerPaakravssagPage;

public class IU_656 {

    private final Sagsbehandlingsskridt_PrimaerPaakravssagPage page;

    public IU_656(Sagsbehandlingsskridt_PrimaerPaakravssagPage page) {
        this.page = page;
    }

    public void execute() {
        this.page
                .activateOpretVelkomstOgPaakravsbrev()
                .fillFormUdenGebyr(OpretVelkomstEllerPaakravsBrevPopupWindow.TypeAfBrev.PAAKRAVSBREV_UDEN_PARTSHOERING, "Auto test")
                .activateOk()
                .activateGaaTilPaakravsbrev()
                .activateGenererUdkast();
    }
}
