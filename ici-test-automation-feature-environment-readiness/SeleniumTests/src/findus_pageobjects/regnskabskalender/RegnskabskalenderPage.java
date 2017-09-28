package findus_pageobjects.regnskabskalender;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class RegnskabskalenderPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Regnskabskalender", 5);

    public RegnskabskalenderPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public Regnskabskalender_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Regnskabskalender_PrimaerSubPage(this);
    }


    // endregion

}

