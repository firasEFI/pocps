package navigation.menu.fluent;

import navigation.menu.fluent.FluentMenu;
import navigation.menu.fluent.SoegTilfoejMenu;
import icisel.pageobjects.elements.Menu;

public class RegistreringMenu extends FluentMenu {

    public RegistreringMenu(Menu menu) {
        super(menu);
    }

    public SoegTilfoejMenu adresse() {
        String subMenuText = "Adresse";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu fordring() {
        String subMenuText = "Fordring";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu fordringsHaverRelation() {
        String subMenuText = "Fordringshaverrelation";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu konto() {
        String subMenuText = "Konto";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu part() {
        String subMenuText = "Part";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

}
