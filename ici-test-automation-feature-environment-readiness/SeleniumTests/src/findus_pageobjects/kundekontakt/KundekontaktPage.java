package findus_pageobjects.kundekontakt;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class KundekontaktPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Kundekontakt", 5);

    public KundekontaktPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement logTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Log")));

    final PageElement karakteregenskaberTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Karakteregenskaber")));


    public Kundekontakt_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Kundekontakt_PrimaerSubPage(this);
    }

    public Kundekontakt_LogSubPage activateLog()
    {
        logTab.click();
        return new Kundekontakt_LogSubPage(this);
    }

    public Kundekontakt_KarakteregenskaberSubPage activateKarakteregenskaber()
    {
        karakteregenskaberTab.click();
        return new Kundekontakt_KarakteregenskaberSubPage(this);
    }


    // endregion

}

