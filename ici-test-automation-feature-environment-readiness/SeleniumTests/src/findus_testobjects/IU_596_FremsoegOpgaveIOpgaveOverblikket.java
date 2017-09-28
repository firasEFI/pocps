package findus_testobjects;

import findus_pageobjects.opgave_overblik.OpgaveOverblikPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblik_PrimaerSubPage;
import findus_pageobjects.opgave_overblik.OpgaveSearchArgs;

/**
 * @author Jakob Rahr Bork Jensen
 *
 */
public class IU_596_FremsoegOpgaveIOpgaveOverblikket {
    
    private OpgaveOverblikPage page;

    public IU_596_FremsoegOpgaveIOpgaveOverblikket(OpgaveOverblikPage page) {
        this.page = page;
    }

    public OpgaveOverblik_PrimaerSubPage execute(OpgaveSearchArgs parm) {
        return page
                .activatePrimaer()
                .fillForm(parm)
                .activateSoeg();
    }
}