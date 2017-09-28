package findus_pageobjects.xai_proceskontrol;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class XaiProceskontrolPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("XAI proceskontrol", 5);

    public XaiProceskontrolPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public XaiProceskontrol_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new XaiProceskontrol_PrimaerSubPage(this);
    }


    // endregion

}

