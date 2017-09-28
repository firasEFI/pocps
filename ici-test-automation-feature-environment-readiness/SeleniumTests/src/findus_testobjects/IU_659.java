package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.opgave.OpgavePage;

public class IU_659 {

    BasePsrmPage basePsrmPage;

    public IU_659(BasePsrmPage basePsrmPage) {
        this.basePsrmPage = basePsrmPage;
    }

    public OpgavePage execute(String sag) {
        return basePsrmPage
                .partKontekstMenu()
                .gaaTilPartensForloeb()
                .aktiverOpgaveMedSag(sag);
    }
}