package findus_pageobjects.bruger;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class BrugerPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Bruger", 5);

    public BrugerPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement opgaverollerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Opgaveroller")));

    final PageElement adgangssikkerhedTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Adgangssikkerhed")));

    final PageElement portalPraeferencerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Portal præferencer")));

    final PageElement bogmaerkerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Bogmærker")));

    final PageElement favoritLinkTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Favorit link")));

    final PageElement favoritScriptsTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Favorit-scripts")));

    final PageElement karakteregenskaberTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Karakteregenskaber")));

    final PageElement diverseTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Diverse")));


    public Bruger_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Bruger_PrimaerSubPage(this);
    }

    public Bruger_OpgaverollerSubPage activateOpgaveroller()
    {
        opgaverollerTab.click();
        return new Bruger_OpgaverollerSubPage(this);
    }

    public Bruger_AdgangssikkerhedSubPage activateAdgangssikkerhed()
    {
        adgangssikkerhedTab.click();
        return new Bruger_AdgangssikkerhedSubPage(this);
    }

    public Bruger_PortalPraeferencerSubPage activatePortalPraeferencer()
    {
        portalPraeferencerTab.click();
        return new Bruger_PortalPraeferencerSubPage(this);
    }

    public Bruger_BogmaerkerSubPage activateBogmaerker()
    {
        bogmaerkerTab.click();
        return new Bruger_BogmaerkerSubPage(this);
    }

    public Bruger_FavoritLinkSubPage activateFavoritLink()
    {
        favoritLinkTab.click();
        return new Bruger_FavoritLinkSubPage(this);
    }

    public Bruger_FavoritScriptsSubPage activateFavoritScripts()
    {
        favoritScriptsTab.click();
        return new Bruger_FavoritScriptsSubPage(this);
    }

    public Bruger_KarakteregenskaberSubPage activateKarakteregenskaber()
    {
        karakteregenskaberTab.click();
        return new Bruger_KarakteregenskaberSubPage(this);
    }

    public Bruger_DiverseSubPage activateDiverse()
    {
        diverseTab.click();
        return new Bruger_DiverseSubPage(this);
    }


    // endregion

}

