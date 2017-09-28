package findus_testobjects;

import findus_pageobjects.opgave_overblik.OpgaveOverblik_PrimaerSubPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling;

public class IU_611_gaaTilIndividuelUdbetaling {

    OpgaveOverblik_PrimaerSubPage opgaveOverblik_primaerSubPage;

    public IU_611_gaaTilIndividuelUdbetaling(OpgaveOverblik_PrimaerSubPage opgaveOverblik_primaerSubPage) {
        this.opgaveOverblik_primaerSubPage = opgaveOverblik_primaerSubPage;
    }

    public Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling execute(String opgaveID) {
        return opgaveOverblik_primaerSubPage
                .selectOpgave(opgaveID)
                .gaaTilGodkendelsessiden();
    }

}