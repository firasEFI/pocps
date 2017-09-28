package findus_testobjects;

import findus_pageobjects.opgave.OpgaveFremrykParm;
import findus_pageobjects.opgave.Opgave_PrimaerSubPage;

public class IU_644 {

    Opgave_PrimaerSubPage opgavePage;

    public IU_644(Opgave_PrimaerSubPage opgavePage) {
        this.opgavePage = opgavePage;
    }

    public void execute(OpgaveFremrykParm opgaveFremrykParm) {
        opgavePage
            .activateOpgave_PrimærSubPageTildelPopupWindow()
            .fillFormFremrykOpgaveOK(opgaveFremrykParm)
            .activateOK();
    }
}