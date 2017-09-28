package navigation.menu.fluent;

import icisel.pageobjects.elements.Menu;
import icisel.pageobjects.frames.Frame;
import icisel.utils.driver.Engine;

public abstract class FluentMenu {
    private Menu menu;

    FluentMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu getSubmenu(String subMenuText) {
        return menu.pick(subMenuText);
    }

    public Menu getSubmenu(Frame frame, String subMenuText) {
        return menu.pick(Engine.getDriver(), frame, subMenuText);
    }

    protected Menu getMenu() {
        return menu;
    }
}
