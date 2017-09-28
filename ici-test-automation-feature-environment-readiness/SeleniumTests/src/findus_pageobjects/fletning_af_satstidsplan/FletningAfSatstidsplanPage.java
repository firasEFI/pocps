package findus_pageobjects.fletning_af_satstidsplan;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class FletningAfSatstidsplanPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Fletning af satstidsplan", 5);

    public FletningAfSatstidsplanPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public FletningAfSatstidsplan_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new FletningAfSatstidsplan_PrimaerSubPage(this);
    }


    // endregion

}

