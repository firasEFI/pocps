package findus_pageobjects.partens_konkurser;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class PartensKonkurserPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Partens konkurser", 5);

    public PartensKonkurserPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public PartensKonkurser_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new PartensKonkurser_PrimaerSubPage(this);
    }


    // endregion

}

