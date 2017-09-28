package findus_pageobjects.opgaveoversigt;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class OpgaveoversigtPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Opgaveoversigt", 5);

    public OpgaveoversigtPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public Opgaveoversigt_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Opgaveoversigt_PrimaerSubPage(this);
    }


    // endregion

}

