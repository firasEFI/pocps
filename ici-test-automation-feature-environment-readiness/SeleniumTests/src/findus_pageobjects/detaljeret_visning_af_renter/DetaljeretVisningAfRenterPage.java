package findus_pageobjects.detaljeret_visning_af_renter;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class DetaljeretVisningAfRenterPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Detaljeret visning af renter", 5);

    public DetaljeretVisningAfRenterPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public DetaljeretVisningAfRenter_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new DetaljeretVisningAfRenter_PrimaerSubPage(this);
    }


    // endregion

}

