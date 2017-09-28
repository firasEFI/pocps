package findus_pageobjects.fordringshaverrelation;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class FordringshaverrelationPage extends BasePsrmPage {
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Fordringshaverrelation", 5);
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));
    final PageElement logTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Log")));

    public FordringshaverrelationPage(){
        super(synchronizer);
    }

    public Fordringshaverrelation_PrimaerSubPage activatePrimaerTab()
    {
        primaerTab.click();
        return new Fordringshaverrelation_PrimaerSubPage(this);
    }

    public Fordringshaverrelation_LogSubPage activateLogTab()
    {
        logTab.click();
        return new Fordringshaverrelation_LogSubPage(this);
    }
}
