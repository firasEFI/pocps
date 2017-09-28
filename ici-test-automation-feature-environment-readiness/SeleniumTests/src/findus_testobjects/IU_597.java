package findus_testobjects;

import findus_pageobjects.BasePsrmPage;

public class IU_597 {

    BasePsrmPage basePsrmPage;

    public IU_597(BasePsrmPage basePsrmPage) {
        this.basePsrmPage = basePsrmPage;
    }

    public void execute() {
        basePsrmPage.
            kontoKontekstMenu()
            .gaaTilKonto()
            .activateFinansielleTransaktioner();
    }
}