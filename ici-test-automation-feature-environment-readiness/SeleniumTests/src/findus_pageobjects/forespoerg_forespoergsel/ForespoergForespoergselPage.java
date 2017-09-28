package findus_pageobjects.forespoerg_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class ForespoergForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Forespørg forespørgsel", 5);

    public ForespoergForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public ForespoergForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new ForespoergForespoergsel_PrimaerSubPage(this);
    }


    // endregion

}

