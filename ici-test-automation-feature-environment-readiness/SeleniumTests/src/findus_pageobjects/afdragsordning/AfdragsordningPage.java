package findus_pageobjects.afdragsordning;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class AfdragsordningPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Afdragsordning", 5);

    public AfdragsordningPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement historikTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Historik")));

    final PageElement karakteregenskaberTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Karakteregenskaber")));


    public Afdragsordning_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Afdragsordning_PrimaerSubPage(this);
    }

    public Afdragsordning_HistorikSubPage activateHistorik()
    {
        historikTab.click();
        return new Afdragsordning_HistorikSubPage(this);
    }

    public Afdragsordning_KarakteregenskaberSubPage activateKarakteregenskaber()
    {
        karakteregenskaberTab.click();
        return new Afdragsordning_KarakteregenskaberSubPage(this);
    }


    // endregion

}

