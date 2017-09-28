package navigation.menu.fluent;

import findus_pageobjects.brevoplysninger.BrevoplysningerPage;
import findus_pageobjects.brevoplysninger.Brevoplysninger_PrimaerSubPage;
import findus_pageobjects.brevoplysninger.Brevopysninger_KundekontaktKlasseForespoergselPrimaerTabPage;
import findus_pageobjects.wizards.brevoplysning.BrevoplysningWizardPage;
import icisel.pageobjects.elements.Menu;

public class SoegTilfoejMenu extends FluentMenu {

    SoegTilfoejMenu(Menu menu) {
        super(menu);
    }

    public Brevopysninger_KundekontaktKlasseForespoergselPrimaerTabPage soeg() {
        String subMenuText = "Søg";
        return new Brevopysninger_KundekontaktKlasseForespoergselPrimaerTabPage(new BrevoplysningerPage());
    }

    public BrevoplysningWizardPage tilfoej() {
        String subMenuText = "Tilføj";
        return new BrevoplysningWizardPage();
    }

}
