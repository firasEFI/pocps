package findus_pageobjects.satsfaktorvaerdi;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class SatsfaktorvaerdiPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Satsfaktorværdi", 5);

    public SatsfaktorvaerdiPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement hbDaekningTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("HB dækning")));


    public Satsfaktorvaerdi_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Satsfaktorvaerdi_PrimaerSubPage(this);
    }

    public Satsfaktorvaerdi_HbDaekningSubPage activateHbDaekning()
    {
        hbDaekningTab.click();
        return new Satsfaktorvaerdi_HbDaekningSubPage(this);
    }


    // endregion

}

