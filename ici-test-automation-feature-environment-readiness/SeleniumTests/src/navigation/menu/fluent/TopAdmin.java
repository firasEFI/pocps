package navigation.menu.fluent;

import navigation.menu.fluent.FluentMenu;
import navigation.menu.fluent.SoegTilfoejMenu;
import icisel.pageobjects.elements.Menu;

public class TopAdmin extends FluentMenu {

    TopAdmin(Menu menu) {
        super(menu);
    }

    public SoegTilfoejMenu bank() {
        String subMenuText = "Bank";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }
}
