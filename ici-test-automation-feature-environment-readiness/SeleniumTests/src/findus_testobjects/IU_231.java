package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblikPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblik_PrimaerSubPage;

public class IU_231 {

    private BasePsrmPage page;

    public IU_231(BasePsrmPage page) {
        this.page = page;
    }

    public OpgaveOverblik_PrimaerSubPage execute() {

        return page
                .menu()
                .opgave()
                .opgaveOverblik();
    }
}
