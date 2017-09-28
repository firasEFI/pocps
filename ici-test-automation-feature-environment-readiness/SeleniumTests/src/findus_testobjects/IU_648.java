package findus_testobjects;

import findus_datamodels.PsrmUserModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblik_PrimaerSubPage;

/**
 1	Klik på fanen Menu	 	Rullemenu vises
 2	Klik på punktet Opgave	 	Rullemenu med underpunkter vises
 3	Klik på underpunktet Opgave overblik	 	Billedet Opgave overblik
 4	Tryk på forstørrelsesglas ved feltet Tildelt til bruger	 	Ny popup vindue åbner
 5	Søg på BrugerID	 	Den ønskede sagsbehandler står nu i feltet Tildelt bruger og popup vinduet er automatisk lukket ned
 6	Tryk på knappen Søg	 	Opgaver tildelt den valgte sagsbehandler vises
 */
public class IU_648 {

    BasePsrmPage basePsrmPage;

    public IU_648(BasePsrmPage basePsrmPage) {
        this.basePsrmPage = basePsrmPage;
    }

    public OpgaveOverblik_PrimaerSubPage execute (PsrmUserModel userModel) {
        return basePsrmPage
                .menu()
                .opgave()
                .opgaveOverblik()
                .activateSoegTildeltBruger()
                .fillFormSoegBruger(userModel)
                .activateBtnSoeg()
                .activateSoeg();
    }
}
