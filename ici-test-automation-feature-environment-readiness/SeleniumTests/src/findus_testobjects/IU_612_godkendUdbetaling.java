package findus_testobjects;

import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling;

public class IU_612_godkendUdbetaling {

    Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling sagsbehandlingsskridt_PrimaerIndividuelUdbetaling;

    public IU_612_godkendUdbetaling(Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling sagsbehandlingsskridt_PrimaerIndividuelUdbetaling) {
        this.sagsbehandlingsskridt_PrimaerIndividuelUdbetaling = sagsbehandlingsskridt_PrimaerIndividuelUdbetaling;
    }

    public Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling execute() {
        sagsbehandlingsskridt_PrimaerIndividuelUdbetaling.activateGodkend();
        return new Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling();
    }
}