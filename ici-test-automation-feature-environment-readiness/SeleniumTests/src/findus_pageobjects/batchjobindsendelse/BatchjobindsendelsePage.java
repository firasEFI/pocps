package findus_pageobjects.batchjobindsendelse;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class BatchjobindsendelsePage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Batchjobindsendelse", 5);

    public BatchjobindsendelsePage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public Batchjobindsendelse_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Batchjobindsendelse_PrimaerSubPage(this);
    }


    // endregion

}

