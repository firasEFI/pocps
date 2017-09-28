package navigation.menu.fluent;

import findus_pageobjects.MockSSOPortalPage;
import icisel.pageobjects.elements.Menu;
import icisel.pageobjects.frames.Frame;
import icisel.pageobjects.frames.Frames;

public class UserMenu extends FluentMenu {
    public final Frame subMenuFrame = Frames.main;

    public UserMenu(Menu menu) {
        super(menu);
    }

    public Menu praeferencer() {
        String subMenuText = "Præferencer";
        return getSubmenu(subMenuText);
    }

    public MockSSOPortalPage logUd() {
        String subMenuText = "Log ud";
        return new MockSSOPortalPage();
    }
}
