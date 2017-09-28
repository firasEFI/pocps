package findus_pageobjects.konto;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class KontoPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Konto", 5);

    public KontoPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement autobetalingTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Autobetaling")));

    final PageElement parterTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Parter")));

    final PageElement opkraevningsbeskederTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Opkrævningsbeskeder")));

    final PageElement inddrivelserTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Inddrivelser")));

    final PageElement karakteregenskaberTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Karakteregenskaber")));

    final PageElement notifikationerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Notifikationer")));


    public Konto_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Konto_PrimaerSubPage(this);
    }

    public Konto_AutobetalingSubPage activateAutobetaling()
    {
        autobetalingTab.click();
        return new Konto_AutobetalingSubPage(this);
    }

    public Konto_ParterSubPage activateParter()
    {
        parterTab.click();
        return new Konto_ParterSubPage(this);
    }

    public Konto_OpkraevningsbeskederSubPage activateOpkraevningsbeskeder()
    {
        opkraevningsbeskederTab.click();
        return new Konto_OpkraevningsbeskederSubPage(this);
    }

    public Konto_InddrivelserSubPage activateInddrivelser()
    {
        inddrivelserTab.click();
        return new Konto_InddrivelserSubPage(this);
    }

    public Konto_KarakteregenskaberSubPage activateKarakteregenskaber()
    {
        karakteregenskaberTab.click();
        return new Konto_KarakteregenskaberSubPage(this);
    }

    public Konto_NotifikationerSubPage activateNotifikationer()
    {
        notifikationerTab.click();
        return new Konto_NotifikationerSubPage(this);
    }


    // endregion

}

