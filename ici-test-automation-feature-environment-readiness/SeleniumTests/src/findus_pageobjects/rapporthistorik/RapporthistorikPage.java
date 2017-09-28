package findus_pageobjects.rapporthistorik;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class RapporthistorikPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Rapporter", 5);

    public RapporthistorikPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public Rapporthistorik_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Rapporthistorik_PrimaerSubPage(this);
    }

    public Rapporthistorik_PrimaerSubPage vaelgBalanceOfStatement() {
        return new Rapporthistorik_PrimaerSubPage(this).chooseBalanceofStatementOfAccount();
    }

    public Rapporthistorik_PrimaerSubPage vaelgCremul_Finsta() {
        return new Rapporthistorik_PrimaerSubPage(this).chooseCremul_Finsta();
    }

    public Rapporthistorik_PrimaerSubPage vaelgDebmul_Finsta() {
        return new Rapporthistorik_PrimaerSubPage(this).chooseDebmul_Finsta();
    }

    // endregion

}

