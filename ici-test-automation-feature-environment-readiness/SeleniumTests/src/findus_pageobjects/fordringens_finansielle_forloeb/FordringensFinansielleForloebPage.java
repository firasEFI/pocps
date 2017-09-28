package findus_pageobjects.fordringens_finansielle_forloeb;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class FordringensFinansielleForloebPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Fordringens finansielle forløb", 5);

    public FordringensFinansielleForloebPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public FordringensFinansielleForloeb_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new FordringensFinansielleForloeb_PrimaerSubPage(this);
    }


    // endregion

}

