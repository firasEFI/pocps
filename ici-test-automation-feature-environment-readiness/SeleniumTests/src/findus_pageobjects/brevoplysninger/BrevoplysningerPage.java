package findus_pageobjects.brevoplysninger;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class BrevoplysningerPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Brevoplysninger", 5);

    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));
    final PageElement getBrevStatus = new PageElement(Frames.zoneMapFrame_2, By.id("boStatus"));

    public BrevoplysningerPage(){
        super(synchronizer);
    }

    public Brevoplysninger_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Brevoplysninger_PrimaerSubPage(this);
    }
    // endregion
}

