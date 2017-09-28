package navigation.menu.fluent;

import navigation.menu.fluent.FluentMenu;
import navigation.menu.fluent.SoegTilfoejMenu;
import icisel.pageobjects.elements.Menu;

public class AktivMenu extends FluentMenu {

    public AktivMenu(Menu menu) {
        super(menu);
    }

    public SoegTilfoejMenu aktiv() {
        String subMenuText = "Aktiv";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu vurderingsFejl() {
        String subMenuText = "Vurderingsfejl";
        return getSubmenu(subMenuText);
    }

}
