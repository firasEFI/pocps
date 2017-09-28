package findus_testobjects;

import findus_pageobjects._360_graders_overblik._360GradersOverblikPage;
import findus_pageobjects.overblik_over_betalinger.OverblikOverBetalingerPage;

public class IU_598 {


    private _360GradersOverblikPage gradersOverblikPage;

    public IU_598(_360GradersOverblikPage gradersOverblikPage) {
        this.gradersOverblikPage = gradersOverblikPage;
    }

    public OverblikOverBetalingerPage execute(String lookupColumnHeader, String lookupValue, String clickColumnHeader) {
        return gradersOverblikPage
                .activateSagsbehandling()
                .klikPaaPilenPaaLinjenBetalingsHaendelsenForKontoenDerefterLinket(lookupColumnHeader, lookupValue, clickColumnHeader)
                .activateAndGoToOverblikOverBetaling();
    }
}