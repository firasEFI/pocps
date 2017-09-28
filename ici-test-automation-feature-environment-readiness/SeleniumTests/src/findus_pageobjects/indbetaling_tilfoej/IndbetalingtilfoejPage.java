package findus_pageobjects.indbetaling_tilfoej;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class IndbetalingtilfoejPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Indbetaling (tilføj)", 5);

    public IndbetalingtilfoejPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public Indbetalingtilfoej_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Indbetalingtilfoej_PrimaerSubPage(this);
    }


    // endregion

}

