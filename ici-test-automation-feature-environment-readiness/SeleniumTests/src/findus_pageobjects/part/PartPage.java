package findus_pageobjects.part;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class PartPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Part", 5);

    public PartPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement relationerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Relationer")));


    public Part_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Part_PrimaerSubPage(this);
    }

    public Part_RelationerSubPage activateRelationer()
    {
        relationerTab.click();
        return new Part_RelationerSubPage(this);
    }


    // endregion

}

