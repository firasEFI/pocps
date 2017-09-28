package findus_testobjects;

import findus_pageobjects.opgave.OpgaveFremrykParm;
import findus_pageobjects.opgave.Opgave_PrimaerSubPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblik_PrimaerSubPage;

/**
 1	Klik på den ønskede opgave under kolonnen Opgavetype	Skærmbilledet Opgave åbner
 2	Tryk på knappen Tildel	Nyt popup vindue "Fremryk opgave" åbner
 3	Klik på luppen ved feltet Bruger	Nyt popup vindue "Brugere til opgavetype roller" åbner
 4	Søg sagsbehandler enten ved at angive Efternavn, Fornavn eller begge dele	Popup vinduet "Brugere til opgavetype roller" viser en liste over alle match	Er der kun 1 match bliver sagsbehandlers navn automatisk overført til Popup vinduet "Fremryk opgave"
 5	Udfyld feltet Detaljer og klik på knappen OK	Skærmbilledet Opgave åbner og man kan se, at opgaven er tildelt sagsbehandleren
 */
public class IU_602 {

    OpgaveOverblik_PrimaerSubPage opgaveOverblik_primaerSubPage;

    public IU_602(OpgaveOverblik_PrimaerSubPage opgaveOverblik_primaerSubPage) {
        this.opgaveOverblik_primaerSubPage = opgaveOverblik_primaerSubPage;
    }

    public Opgave_PrimaerSubPage execute (String opgaveID, OpgaveFremrykParm parm) {
        return opgaveOverblik_primaerSubPage
                .selectOpgave(opgaveID)
                .activateOpgave_PrimærSubPageTildelPopupWindow()
                .fillFormFremrykOpgaveOK(parm)
                .activateOK();
    }
}
