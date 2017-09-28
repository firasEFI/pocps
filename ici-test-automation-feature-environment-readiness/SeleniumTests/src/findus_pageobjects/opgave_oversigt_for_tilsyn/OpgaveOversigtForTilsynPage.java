package findus_pageobjects.opgave_oversigt_for_tilsyn;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class OpgaveOversigtForTilsynPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Opgave oversigt for tilsyn", 5);

    public OpgaveOversigtForTilsynPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public OpgaveOversigtForTilsyn_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new OpgaveOversigtForTilsyn_PrimaerSubPage(this);
    }


    // endregion

}

