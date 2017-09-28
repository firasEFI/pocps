package findus_testobjects;

import findus_pageobjects.opgave.Opgave_PrimaerSubPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblik_PrimaerSubPage;

public class IU_601 {

    private OpgaveOverblik_PrimaerSubPage page;

    public IU_601(OpgaveOverblik_PrimaerSubPage page) {
        this.page = page;
    }

    /**
     * Funktionen starter i opgave overblikket, hvor den klikker på knappen "Tildel" ud
     * for den ønskede opgave yderst til højre.
     *
     * Begrænsning: Metoden kan kun håndtere, at der er én synlig opgave efter filtrering
     *
     * @return Opgave_PrimaerSubPage (samme side)
     */
    public OpgaveOverblik_PrimaerSubPage execute(String opgaveId) {
        return page.activateOpgaveTildel(opgaveId);
    }
}