package navigation.menu.fluent;

import navigation.menu.fluent.FluentMenu;
import navigation.menu.fluent.SoegTilfoejMenu;
import icisel.pageobjects.elements.Menu;

public class SagsbehandlingMenu extends FluentMenu {

    public SagsbehandlingMenu(Menu menu) {
        super(menu);
    }

    public SoegTilfoejMenu afdragsOrdning() {
        String subMenuText = "Afdragsordning";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu ankesag() {
        String subMenuText = "Ankesag";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu hoeringssag() {
        String subMenuText = "Høringssag";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu inddrivelsesmyndighedHenvisning() {
        String subMenuText = "Inddrivelsesmyndighed henvisning";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu inddrivelsessag() {
        String subMenuText = "Inddrivelsessag";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu konkurs() {
        String subMenuText = "Konkurs";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu renteFritagelse() {
        String subMenuText = "Rentefritagelse";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu sagsKontrol() {
        String subMenuText = "Sagskontrol";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu sagsProces() {
        String subMenuText = "Sagsproces";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

}
