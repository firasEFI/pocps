package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.opgave.OpgavePage;

public class IU_605 {

    BasePsrmPage basePsrmPage;

    public IU_605(BasePsrmPage basePsrmPage) {
        this.basePsrmPage = basePsrmPage;
    }

    public OpgavePage execute() {
        return basePsrmPage
                .partKontekstMenu()
                .gaaTilPartensForloeb()
                .activateGodkendUdbetalingLigmedEllerOverBeloebsGraensen();
    }
}