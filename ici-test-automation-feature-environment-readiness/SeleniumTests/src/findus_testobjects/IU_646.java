package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_KontoSubPage;

public class IU_646 {

    BasePsrmPage basePsrmPage;

    public IU_646(BasePsrmPage basePsrmPage) {
        this.basePsrmPage = basePsrmPage;
    }

    public _360GradersOverblik_KontoSubPage execute () {
        basePsrmPage
                .kontoKontekstMenu()
                .gaaTil360GradersOverblik()
                .getParentPage()
                .activateKonto()
                .activateFinansielleTransaktioner();
        return new _360GradersOverblik_KontoSubPage();
    }
}