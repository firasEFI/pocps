package navigation.menu.fluent;

import findus_pageobjects.rapporthistorik.RapporthistorikPage;
import icisel.pageobjects.elements.Menu;

public class OpgavestyringMenu extends FluentMenu {

    public OpgavestyringMenu(Menu menu) {
        super(menu);
    }

    public SoegTilfoejMenu entitetKorrektion() {
        String subMenuText = "Entitet korrektion";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu kundekontakt() {
        String subMenuText = "Kundekontakt";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu sagsbehandlingsskridt() {
        String subMenuText = "Sagsbehandlingsskridt";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu breve() {
        String subMenuText = "Breve";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public RapporthistorikPage rapporter() {
        String subMenuText = "Rapporter";
        getSubmenu(subMenuText);
        return new RapporthistorikPage();
    }

}
