package findus_pageobjects.inddrivelsesmyndighed_henvisning;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class InddrivelsesmyndighedHenvisningPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Inddrivelsesmyndighed henvisning", 5);

    public InddrivelsesmyndighedHenvisningPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public InddrivelsesmyndighedHenvisning_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new InddrivelsesmyndighedHenvisning_PrimaerSubPage(this);
    }


    // endregion

}

