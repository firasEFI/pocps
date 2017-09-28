package findus_testobjects;

import findus_pageobjects._360_graders_overblik._360GradersOverblik_KontoSubPage;
import findus_pageobjects.overblik_over_betalinger.OverblikOverBetalingerPage;

public class IU_600 {

    private _360GradersOverblik_KontoSubPage _360gradersOverblik_KontoSubPage;

    public IU_600(_360GradersOverblik_KontoSubPage _360gradersOverblik_KontoSubPage) {
        this._360gradersOverblik_KontoSubPage = _360gradersOverblik_KontoSubPage;
    }

    public OverblikOverBetalingerPage execute(String lookupColumnHeader, String lookupValue, String clickColumnHeader) {
        _360gradersOverblik_KontoSubPage
            .getParentPage()
            .activateSagsbehandling()
            .klikPaaPilenPaaLinjenBetalingsHaendelsenForKontoenDerefterLinket(lookupColumnHeader, lookupValue, clickColumnHeader)
            .activateAndGoToOverblikOverBetaling();
        return new OverblikOverBetalingerPage();
    }
}