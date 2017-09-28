package findus_pageobjects.oversigt_over_daekninger;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class OversigtOverDaekningerPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Oversigt over dækninger", 5);

    public OversigtOverDaekningerPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement karakteregenskaberTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Karakteregenskaber")));


    public OversigtOverDaekninger_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new OversigtOverDaekninger_PrimaerSubPage(this);
    }

    public OversigtOverDaekninger_KarakteregenskaberSubPage activateKarakteregenskaber()
    {
        karakteregenskaberTab.click();
        return new OversigtOverDaekninger_KarakteregenskaberSubPage(this);
    }


    // endregion

}

