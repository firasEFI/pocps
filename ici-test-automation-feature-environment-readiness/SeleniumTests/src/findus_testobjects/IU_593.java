package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.sagsbehandlingsskridt.OpretAfgoerelsesbrevBrevParm;
import findus_pageobjects.wizards.afdragsordning.AfdragsordningWizardParm;
import findus_pageobjects.wizards.kundekontakt.Kundekontakt;

/**
 * Opret aktiv afdragsordning ud fra tabeltræk via afgørelsesbrev
 */
public class IU_593 {

    private BasePsrmPage page;

    public IU_593(BasePsrmPage page) {
        this.page = page;
    }

    public BasePsrmPage execute(AfdragsordningWizardParm afdragsordningWizardParm, OpretAfgoerelsesbrevBrevParm opretAfgoerelsesbrevBrevParm, Kundekontakt kundekontakt) {

        page
                .kontoKontekstMenu()
                .opretAfdragsordningssag()
                .fillForm(afdragsordningWizardParm)
                .activateVaelgAlt()
                .activateGem()
                .activateOpretInaktivAfdragsOrdning()
                .activateOpretAfgoerelsesbrevBrev()
                .fillForm(opretAfgoerelsesbrevBrevParm)
                .activateGem()
                .selectFirstBrevoplysninger()
                .activatePrimaer()
                .activateRediger()
                .fillForm(kundekontakt)
                .activateGem()
                .activateGenererUdkast()
                .activateSeUdkast()
                .activateGodkendUdkast()
                .activateSendTilAogD();

        return new BasePsrmPage();
    }
}
