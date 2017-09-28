package findus_pageobjects.finansielle_transaktioner_paa_betaling;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class FinansielleTransaktionerPaaBetalingPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Finansielle transaktioner på betaling", 5);

    public FinansielleTransaktionerPaaBetalingPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public FinansielleTransaktionerPaaBetaling_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new FinansielleTransaktionerPaaBetaling_PrimaerSubPage(this);
    }


    // endregion

}

