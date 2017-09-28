package findus_pageobjects.konto_finansielle_forloeb;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class KontoFinansielleForloebPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Konto finansielle forløb", 5);

    public KontoFinansielleForloebPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public KontoFinansielleForloeb_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new KontoFinansielleForloeb_PrimaerSubPage(this);
    }


    // endregion

}

