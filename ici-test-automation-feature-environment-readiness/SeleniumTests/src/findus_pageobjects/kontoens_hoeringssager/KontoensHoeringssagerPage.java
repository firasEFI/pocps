package findus_pageobjects.kontoens_hoeringssager;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class KontoensHoeringssagerPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Kontoens høringssager", 5);

    public KontoensHoeringssagerPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public KontoensHoeringssager_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new KontoensHoeringssager_PrimaerSubPage(this);
    }


    // endregion

}

