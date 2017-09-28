package findus_pageobjects.batchkoerselstrae;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class BatchkoerselstraePage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Batchkørselstræ", 5);

    public BatchkoerselstraePage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement koerselKontrolTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Kørsel kontrol")));


    public Batchkoerselstrae_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Batchkoerselstrae_PrimaerSubPage(this);
    }

    public Batchkoerselstrae_KoerselKontrolSubPage activateKoerselKontrol()
    {
        koerselKontrolTab.click();
        return new Batchkoerselstrae_KoerselKontrolSubPage(this);
    }


    // endregion

}

