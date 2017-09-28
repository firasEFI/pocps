package findus_pageobjects;

import findus_pageobjects.synchronization.SynchronizeByElementPresent;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public abstract class PopupWindow<TParentPage> extends BasePage {

    protected final TParentPage parentPage;

    protected PopupWindow(By elementToSyncronize, TParentPage parentPage) {
        super(new SynchronizeByElementPresent(new PageElement(Frames.defaultContent, elementToSyncronize), 10));

        this.parentPage = parentPage;
    }
}
