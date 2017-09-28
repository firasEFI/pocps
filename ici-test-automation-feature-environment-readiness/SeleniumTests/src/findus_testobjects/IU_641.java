package findus_testobjects;

import findus_datamodels.PsrmUserModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.opgave.Opgave_PrimaerSubPage;

/**
 1	Klik på fanen Menu	 	Rullemenu vises
 2	Klik på punktet Opgave	 	Rullemenu med underpunkter vises
 3	Klik på underpunktet Opgave overblik for tilsyn	 	Billedet Opgave overblik for tilsyn vises

 4	Klik på "forstørrelsesglas" under punktet Tildelt til bruger	 	Billedet Brugersøgning vises **popup

 5	Indtast Bruger ID, eller Efternavn og Fornavn og klik Søg	 	Bruger ID opdateres i feltet Tildelt til bruger  ** popup lukker

 6	Klik på Søg	 	Opgaver tildelt den fremsøgte sagsbehandler vises
 7	Vis opgaven ved at klikke på den blå linje under punktet Opgavetype
 */

public class IU_641 {

    BasePsrmPage basePsrmPage;

    public IU_641(BasePsrmPage basePsrmPage) {
        this.basePsrmPage = basePsrmPage;
    }

    public Opgave_PrimaerSubPage execute (PsrmUserModel user, int rowIndex) {
        return basePsrmPage
                .menu()
                .opgave()
                .opgaveOverblikForTilsyn()
                .activateSøgTildelBruger()
                .fillForm(user)
                .activateSoegExpectOneResult()
                .activateSoeg()
                .selectOpgaveByIndex(rowIndex);
    }
}
