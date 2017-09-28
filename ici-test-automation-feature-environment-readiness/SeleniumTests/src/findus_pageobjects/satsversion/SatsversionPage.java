package findus_pageobjects.satsversion;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class SatsversionPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Satsversion", 5);

    public SatsversionPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement opkraevningsprintTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Opkrævningsprint")));


    public Satsversion_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Satsversion_PrimaerSubPage(this);
    }

    public Satsversion_OpkraevningsprintSubPage activateOpkraevningsprint()
    {
        opkraevningsprintTab.click();
        return new Satsversion_OpkraevningsprintSubPage(this);
    }


    // endregion

}

