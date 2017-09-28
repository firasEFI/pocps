package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblikPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_SagsbehandlingSubPage;

public class IU_246_AabnSagsoverblikketForSkylder {

    private BasePsrmPage page;

    public IU_246_AabnSagsoverblikketForSkylder(BasePsrmPage page) {
        this.page = page;
    }

    /**
     * Funktion går via konto kontekst menuen til skyldners sagsoverblik
     * @return _360GradersOverblikPage
     */
    public _360GradersOverblik_SagsbehandlingSubPage execute() {
            page
                .kontoKontekstMenu()
                .gaaTil360GradersOverblik()
                .getParentPage()
                .activateSagsbehandling();

        return new _360GradersOverblikPage().activateSagsbehandling();
    }
}