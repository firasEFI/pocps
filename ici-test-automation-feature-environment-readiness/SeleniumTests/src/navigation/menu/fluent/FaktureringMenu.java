package navigation.menu.fluent;

import navigation.menu.fluent.FluentMenu;
import navigation.menu.fluent.SoegTilfoejMenu;
import icisel.pageobjects.elements.Menu;

public class FaktureringMenu extends FluentMenu {

    public FaktureringMenu(Menu menu) {
        super(menu);
    }

    public SoegTilfoejMenu opkraevning() {
        String subMenuText = "Opkrævning";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }
}
