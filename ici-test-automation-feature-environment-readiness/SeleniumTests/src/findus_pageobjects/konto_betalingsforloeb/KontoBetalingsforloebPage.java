package findus_pageobjects.konto_betalingsforloeb;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class KontoBetalingsforloebPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Konto betalingsforløb", 5);

    public KontoBetalingsforloebPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public KontoBetalingsforloeb_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new KontoBetalingsforloeb_PrimaerSubPage(this);
    }


    // endregion

}

