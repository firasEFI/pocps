package findus_pageobjects.balance_for_fordringer;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class BalanceForFordringerPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Balance for fordringer", 5);

    public BalanceForFordringerPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public BalanceForFordringer_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new BalanceForFordringer_PrimaerSubPage(this);
    }


    // endregion

}

