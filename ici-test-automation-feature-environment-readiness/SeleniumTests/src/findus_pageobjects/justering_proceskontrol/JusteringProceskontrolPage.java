package findus_pageobjects.justering_proceskontrol;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class JusteringProceskontrolPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Justering proceskontrol", 5);

    public JusteringProceskontrolPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public JusteringProceskontrol_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new JusteringProceskontrol_PrimaerSubPage(this);
    }


    // endregion

}

