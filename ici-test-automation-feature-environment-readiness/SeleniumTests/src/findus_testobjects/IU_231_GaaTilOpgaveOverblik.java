package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblikPage;

public class IU_231_GaaTilOpgaveOverblik {

    private BasePsrmPage page;

    public IU_231_GaaTilOpgaveOverblik(BasePsrmPage page) {
        this.page = page;
    }

    /**
     * Funktion går via topmenuen til opgaveoverblikket
     * @return OpgaveOverblikPage
     */
    public OpgaveOverblikPage execute() {

        page
                .menu()
                .opgave()
                .opgaveOverblik();

        return new OpgaveOverblikPage();
    }
}
