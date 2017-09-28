package navigation.menu.fluent;

import navigation.menu.fluent.FluentMenu;
import navigation.menu.fluent.SoegTilfoejMenu;
import icisel.pageobjects.elements.Menu;

public class SatserMenu extends FluentMenu {

    public SatserMenu(Menu menu) {
        super(menu);
    }

    public Menu fletningAfSatstidsplan() {
        String subMenuText = "Fletning af satstidsplan";
        return getSubmenu(subMenuText);
    }

    public Menu fletningAfSatsversion() {
        String subMenuText = "Fletning af satsversion";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu satsFaktor() {
        String subMenuText = "Satsfaktor";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu satsfaktorvaerdi() {
        String subMenuText = "Satsfaktorværdi";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu satsKomponent() {
        String subMenuText = "Satskomponent";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu satsKontrol() {
        String subMenuText = "Satskontrol";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu satsTidsplan() {
        String subMenuText = "Satstidsplan";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu satsVersion() {
        String subMenuText = "Satsversion";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

}
