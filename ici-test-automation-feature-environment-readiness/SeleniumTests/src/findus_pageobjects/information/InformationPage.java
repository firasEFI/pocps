package findus_pageobjects.information;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class InformationPage extends BasePsrmPage {

    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Information", 5);
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    public InformationPage() {
        super(synchronizer);
    }

    public InformationPage_PrimaerSubPage activatePrimaer() {
        primaerTab.click();
        return new InformationPage_PrimaerSubPage(this);
    }
}