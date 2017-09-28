package findus_pageobjects.rapportafgivelse;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class RapportafgivelsePage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Rapportafgivelse", 5);

    public RapportafgivelsePage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public Rapportafgivelse_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Rapportafgivelse_PrimaerSubPage(this);
    }


    // endregion

}

