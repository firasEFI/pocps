package findus_pageobjects.indsaml_og_send_forespoerg_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class IndsamlOgSendForespoergForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Indsaml og send forespørg forespørgsel", 5);

    public IndsamlOgSendForespoergForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public IndsamlOgSendForespoergForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new IndsamlOgSendForespoergForespoergsel_PrimaerSubPage(this);
    }


    // endregion

}

