package navigation.menu.fluent;

import navigation.menu.fluent.FluentMenu;
import icisel.pageobjects.elements.Menu;

public class SelvbetjeningMenu extends FluentMenu {

    public SelvbetjeningMenu(Menu menu) {
        super(menu);
    }

    public Menu serviceOpgave() {
        String subMenuText = "Serviceopgave";
        return getSubmenu(subMenuText);
    }

}
