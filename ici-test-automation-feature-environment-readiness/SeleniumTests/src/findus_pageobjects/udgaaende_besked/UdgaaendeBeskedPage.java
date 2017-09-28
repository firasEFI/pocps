package findus_pageobjects.udgaaende_besked;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class UdgaaendeBeskedPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Udgående besked", 5);

    public UdgaaendeBeskedPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement beskedTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Besked")));

    final PageElement svarTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Svar")));


    public UdgaaendeBesked_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new UdgaaendeBesked_PrimaerSubPage(this);
    }

    public UdgaaendeBesked_BeskedSubPage activateBesked()
    {
        beskedTab.click();
        return new UdgaaendeBesked_BeskedSubPage(this);
    }

    public UdgaaendeBesked_SvarSubPage activateSvar()
    {
        svarTab.click();
        return new UdgaaendeBesked_SvarSubPage(this);
    }


    // endregion

}

