package findus_testobjects;

import findus_pageobjects._360_graders_overblik._360GradersOverblik_KontoSubPage;

public class IU_616 {

    _360GradersOverblik_KontoSubPage gradersOverblik_kontoSubPage;

    public IU_616(_360GradersOverblik_KontoSubPage gradersOverblik_kontoSubPage) {
        this.gradersOverblik_kontoSubPage = gradersOverblik_kontoSubPage;
    }

    public _360GradersOverblik_KontoSubPage execute() {
        gradersOverblik_kontoSubPage.activateFinansielleTransaktioner();
        return new _360GradersOverblik_KontoSubPage();
    }
}
