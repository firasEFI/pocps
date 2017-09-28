package findus_pageobjects.opgave;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class OpgavePage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Opgave", 5);

    public OpgavePage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement noeglerVaerdierTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Nøgler/værdier")));

    public Opgave_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Opgave_PrimaerSubPage(this);
    }

    public Opgave_NoeglerVaerdierSubPage activateNoeglerVaerdier()
    {
        noeglerVaerdierTab.click();
        return new Opgave_NoeglerVaerdierSubPage(this);
    }
    // endregion
}

