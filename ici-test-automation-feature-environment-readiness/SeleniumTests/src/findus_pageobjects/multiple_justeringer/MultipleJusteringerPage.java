package findus_pageobjects.multiple_justeringer;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class MultipleJusteringerPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Multiple justeringer", 5);

    public MultipleJusteringerPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public MultipleJusteringer_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new MultipleJusteringer_PrimaerSubPage(this);
    }


    // endregion

}

