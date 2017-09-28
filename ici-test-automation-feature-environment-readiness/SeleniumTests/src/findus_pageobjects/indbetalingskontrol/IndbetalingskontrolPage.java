package findus_pageobjects.indbetalingskontrol;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class IndbetalingskontrolPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Indbetalingskontrol", 5);

    public IndbetalingskontrolPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement indbetalingskildeTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Indbetalingskilde")));

    final PageElement tenderDepositTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Tender Deposit")));

    final PageElement aflevereInsTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Aflevere ins")));


    public Indbetalingskontrol_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Indbetalingskontrol_PrimaerSubPage(this);
    }

    public Indbetalingskontrol_IndbetalingskildeSubPage activateIndbetalingskilde()
    {
        indbetalingskildeTab.click();
        return new Indbetalingskontrol_IndbetalingskildeSubPage(this);
    }

    public Indbetalingskontrol_TenderDepositSubPage activateTenderDeposit()
    {
        tenderDepositTab.click();
        return new Indbetalingskontrol_TenderDepositSubPage(this);
    }

    public Indbetalingskontrol_AflevereInsSubPage activateAflevereIns()
    {
        aflevereInsTab.click();
        return new Indbetalingskontrol_AflevereInsSubPage(this);
    }


    // endregion

}

