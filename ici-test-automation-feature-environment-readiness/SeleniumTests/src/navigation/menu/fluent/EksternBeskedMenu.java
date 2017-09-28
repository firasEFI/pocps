package navigation.menu.fluent;

import navigation.menu.fluent.FluentMenu;
import navigation.menu.fluent.SoegTilfoejMenu;
import icisel.pageobjects.elements.Menu;

public class EksternBeskedMenu extends FluentMenu {

    public EksternBeskedMenu(Menu menu) {
        super(menu);
    }

    public Menu udgaaendeBesked() {
        String subMenuText = "Udgående besked";
        return getSubmenu(subMenuText);
    }

    public Menu xaiDynamiskUpload() {
        String subMenuText = "XAI dynamisk upload";
        return getSubmenu(subMenuText);
    }

    public Menu xaiProces() {
        String subMenuText = "XAI proces";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu xaiProcessKontrol() {
        String subMenuText = "XAI proceskontrol";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

}
