package findus_pageobjects.fletning_af_satsversion;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class FletningAfSatsversionPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Fletning af satsversion", 5);

    public FletningAfSatsversionPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public FletningAfSatsversion_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new FletningAfSatsversion_PrimaerSubPage(this);
    }


    // endregion

}

