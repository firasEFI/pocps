package findus_pageobjects.xai_overfoerselsproces;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class XaiOverfoerselsprocesPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("XAI overførselsproces", 5);

    public XaiOverfoerselsprocesPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement svarTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Svar")));


    public XaiOverfoerselsproces_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new XaiOverfoerselsproces_PrimaerSubPage(this);
    }

    public XaiOverfoerselsproces_SvarSubPage activateSvar()
    {
        svarTab.click();
        return new XaiOverfoerselsproces_SvarSubPage(this);
    }


    // endregion

}

