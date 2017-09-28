package navigation.menu.fluent;

import navigation.menu.fluent.FluentMenu;
import icisel.pageobjects.elements.Menu;

public class HistorikMenu extends FluentMenu {

    public HistorikMenu(Menu menu) {
        super(menu);
    }

    public Menu transaktionTilHoering() {
        String subMenuText = "Transaktion til høring";
        return getSubmenu(subMenuText);
    }

}
