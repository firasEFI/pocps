package findus_pageobjects.sagsproces;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class SagsprocesPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Sagsproces", 5);

    public SagsprocesPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement haendelserTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Hændelser")));

    final PageElement logTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Log")));


    public Sagsproces_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Sagsproces_PrimaerSubPage(this);
    }

    public Sagsproces_HaendelserSubPage activateHaendelser()
    {
        haendelserTab.click();
        return new Sagsproces_HaendelserSubPage(this);
    }

    public Sagsproces_LogSubPage activateLog()
    {
        logTab.click();
        return new Sagsproces_LogSubPage(this);
    }


    // endregion

}

