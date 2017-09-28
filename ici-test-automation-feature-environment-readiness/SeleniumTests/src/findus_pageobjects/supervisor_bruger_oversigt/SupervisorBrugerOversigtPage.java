package findus_pageobjects.supervisor_bruger_oversigt;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class SupervisorBrugerOversigtPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Supervisor bruger oversigt", 5);

    public SupervisorBrugerOversigtPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public SupervisorBrugerOversigt_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new SupervisorBrugerOversigt_PrimaerSubPage(this);
    }


    // endregion

}

