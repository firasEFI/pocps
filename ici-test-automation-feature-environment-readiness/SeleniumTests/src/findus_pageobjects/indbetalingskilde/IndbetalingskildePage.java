package findus_pageobjects.indbetalingskilde;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class IndbetalingskildePage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Indbetalingskilde", 5);

    public IndbetalingskildePage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement indbetalingerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Indbetalinger")));

    final PageElement aflevereInsTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Aflevere ins")));

    final PageElement fejlTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Fejl")));

    final PageElement karakteregenskaberTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Karakteregenskaber")));


    public Indbetalingskilde_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Indbetalingskilde_PrimaerSubPage(this);
    }

    public Indbetalingskilde_IndbetalingerSubPage activateIndbetalinger()
    {
        indbetalingerTab.click();
        return new Indbetalingskilde_IndbetalingerSubPage(this);
    }

    public Indbetalingskilde_AflevereInsSubPage activateAflevereIns()
    {
        aflevereInsTab.click();
        return new Indbetalingskilde_AflevereInsSubPage(this);
    }

    public Indbetalingskilde_FejlSubPage activateFejl()
    {
        fejlTab.click();
        return new Indbetalingskilde_FejlSubPage(this);
    }

    public Indbetalingskilde_KarakteregenskaberSubPage activateKarakteregenskaber()
    {
        karakteregenskaberTab.click();
        return new Indbetalingskilde_KarakteregenskaberSubPage(this);
    }


    // endregion

}

