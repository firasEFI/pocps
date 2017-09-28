package navigation.menu.fluent;

import findus_pageobjects.opgave.Opgave_PrimaerSubPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblikPage;
import findus_pageobjects.opgave_overblik.OpgaveOverblik_PrimaerSubPage;
import findus_pageobjects.opgave_overblik_for_tilsyn.OpgaveOverblikForTilsynPage;
import findus_pageobjects.opgave_overblik_for_tilsyn.OpgaveOverblikForTilsyn_PrimaerSubPage;
import findus_pageobjects.wizards.opret_opgave_manuelt.OpretOpgaveManueltWizardPage;
import icisel.pageobjects.elements.Menu;

public class OpgaveMenu extends FluentMenu {

    public OpgaveMenu(Menu menu) {
        super(menu);
    }

    public Menu brugerOverSigtForTilsyn() {
        String subMenuText = "Brugeroversigt for tilsyn";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu opgave() {
        String subMenuText = "Opgave";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu opgaveForTilsyn() {
        String subMenuText = "Opgave for tilsyn";
        return getSubmenu(subMenuText);
    }

    public Menu opgaveListe() {
        String subMenuText = "Opgaveliste";
        return getSubmenu(subMenuText);
    }

    public Menu opgaveResume() {
        String subMenuText = "Opgaveresumé";
        return getSubmenu(subMenuText);
    }

    public Menu opgaveResumeForTilsyn() {
        String subMenuText = "Opgaveresumé for tilsyn";
        return getSubmenu(subMenuText);
    }

    public Menu opgaveSoegning() {
        String subMenuText = "Opgavesøgning";
        return getSubmenu(subMenuText);
    }

    public OpgaveOverblik_PrimaerSubPage opgaveOverblik() {
        String subMenuText = "Opgave overblik";
        getSubmenu(subMenuText);
        return new OpgaveOverblik_PrimaerSubPage(new OpgaveOverblikPage());
    }

    public OpgaveOverblikForTilsyn_PrimaerSubPage opgaveOverblikForTilsyn() {
        String subMenuText = "Opgave overblik for tilsyn";
        getSubmenu(subMenuText);
        return new OpgaveOverblikForTilsyn_PrimaerSubPage(new OpgaveOverblikForTilsynPage());
    }

    public OpretOpgaveManueltWizardPage opretOpgaveManuelt() {
        String subMenuText = "Opret opgave manuelt";
        getSubmenu(subMenuText);
        return new OpretOpgaveManueltWizardPage();
    }

}
