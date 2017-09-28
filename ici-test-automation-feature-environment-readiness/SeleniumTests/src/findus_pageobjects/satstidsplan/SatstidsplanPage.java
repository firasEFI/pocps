package findus_pageobjects.satstidsplan;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class SatstidsplanPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Satstidsplan", 5);

    public SatstidsplanPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement rqRegelTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("RQ regel")));

    final PageElement opkraevningsbeskederTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Opkrævningsbeskeder")));


    public Satstidsplan_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Satstidsplan_PrimaerSubPage(this);
    }

    public Satstidsplan_RqRegelSubPage activateRqRegel()
    {
        rqRegelTab.click();
        return new Satstidsplan_RqRegelSubPage(this);
    }

    public Satstidsplan_OpkraevningsbeskederSubPage activateOpkraevningsbeskeder()
    {
        opkraevningsbeskederTab.click();
        return new Satstidsplan_OpkraevningsbeskederSubPage(this);
    }


    // endregion

}

