package findus_datamodelWarehouses;

import findus_datamodels.PsrmUserModel;

public class PsrmUsersWarehouse {

    public static PsrmUserModel getGenerelSagsbehandler() {
        PsrmUserModel generelSagsbehandler = new PsrmUserModel();

        generelSagsbehandler.setSagsbehanlderId("IFK");
        generelSagsbehandler.setPassword("netcompany-123");

        return generelSagsbehandler;
    }

    public static PsrmUserModel getGodkenderSagsbehandler() {
        PsrmUserModel godkenderSagsbehandler = new PsrmUserModel();

        godkenderSagsbehandler.setSagsbehanlderId("GEB");
        godkenderSagsbehandler.setPassword("netcompany-123");

        return godkenderSagsbehandler;
    }

    public static PsrmUserModel getSystemAdministrator() {
        PsrmUserModel systemAdministrator = new PsrmUserModel();

        systemAdministrator.setSagsbehanlderId("SYSADM02");
        systemAdministrator.setPassword("netcompany-123");

        return systemAdministrator;
    }
}
