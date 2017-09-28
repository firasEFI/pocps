package findus_pageobjects.transaktion_til_hoering;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class TransaktionTilHoeringPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Transaktion til høring", 5);

    public TransaktionTilHoeringPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public TransaktionTilHoering_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new TransaktionTilHoering_PrimaerSubPage(this);
    }


    // endregion

}

