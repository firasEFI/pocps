package findus_testobjects;

import findus_pageobjects.opgave_overblik.OpgaveOverblikPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblik_PrimaerSubPage;
import findus_pageobjects.opgavestyring.OpgavestyringWizardPage;

public class IU_613_lukOpgaveManuelt {
    OpgaveOverblik_PrimaerSubPage opgaveOverblik_primaerSubPage;

    public IU_613_lukOpgaveManuelt(OpgaveOverblik_PrimaerSubPage opgaveOverblik_primaerSubPage) {
        this.opgaveOverblik_primaerSubPage = opgaveOverblik_primaerSubPage;
    }

    public OpgaveOverblik_PrimaerSubPage execute(String begrundelseskode, String beskrivelse) {
        OpgavestyringWizardPage opgavestyringWizardPage = new OpgavestyringWizardPage();
        opgavestyringWizardPage.doWindowActionWithSave(begrundelseskode, beskrivelse);
        return opgaveOverblik_primaerSubPage;
    }
}