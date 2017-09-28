package findus_pageobjects.calculate_total_debt;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class CalculateTotalDebtPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Calculate Total Debt", 5);

    public CalculateTotalDebtPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public CalculateTotalDebt_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new CalculateTotalDebt_PrimaerSubPage(this);
    }


    // endregion

}

