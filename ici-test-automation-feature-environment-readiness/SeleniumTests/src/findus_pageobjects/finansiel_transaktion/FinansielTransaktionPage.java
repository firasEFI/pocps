package findus_pageobjects.finansiel_transaktion;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class FinansielTransaktionPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Finansiel transaktion", 5);

    public FinansielTransaktionPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement ftProcesTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("FT-proces")));

    final PageElement karakteregenskaberTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Karakteregenskaber")));


    public FinansielTransaktion_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new FinansielTransaktion_PrimaerSubPage(this);
    }

    public FinansielTransaktion_FtProcesSubPage activateFtProces()
    {
        ftProcesTab.click();
        return new FinansielTransaktion_FtProcesSubPage(this);
    }

    public FinansielTransaktion_KarakteregenskaberSubPage activateKarakteregenskaber()
    {
        karakteregenskaberTab.click();
        return new FinansielTransaktion_KarakteregenskaberSubPage(this);
    }


    // endregion

}

