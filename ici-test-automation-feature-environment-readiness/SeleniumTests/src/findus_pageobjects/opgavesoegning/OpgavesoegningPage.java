package findus_pageobjects.opgavesoegning;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class OpgavesoegningPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Opgavesøgning", 5);

    public OpgavesoegningPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public Opgavesoegning_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Opgavesoegning_PrimaerSubPage(this);
    }


    // endregion

}

