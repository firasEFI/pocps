package findus_pageobjects.opgave_overblik_for_tilsyn;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class OpgaveOverblikForTilsynPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Opgave overblik for tilsyn", 5);

    public OpgaveOverblikForTilsynPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public OpgaveOverblikForTilsyn_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new OpgaveOverblikForTilsyn_PrimaerSubPage(this);
    }


    // endregion

}

