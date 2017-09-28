package findus_pageobjects.betaling_indbetalings_soegning;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class BetalingIndbetalingsSoegningPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Betaling/indbetalings søgning", 5);

    public BetalingIndbetalingsSoegningPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public BetalingIndbetalingsSoegning_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new BetalingIndbetalingsSoegning_PrimaerSubPage(this);
    }


    // endregion

}

