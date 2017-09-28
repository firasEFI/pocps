package findus_testobjects;

import findus_pageobjects._360_graders_overblik._360GradersOverblikPage;
import findus_pageobjects.information.InformationPage_PrimaerSubPage;

public class IU_688 {

    _360GradersOverblikPage gradersOverblikPage;

    public IU_688(_360GradersOverblikPage gradersOverblikPage) {
        this.gradersOverblikPage = gradersOverblikPage;
    }

    public InformationPage_PrimaerSubPage execute (String noteId) {
        return gradersOverblikPage
                .activateSagsbehandling()
                .klikPaaModkravDaekningsloesBetaling(noteId);
    }
}