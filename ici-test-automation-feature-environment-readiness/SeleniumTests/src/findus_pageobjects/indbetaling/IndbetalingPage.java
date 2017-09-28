package findus_pageobjects.indbetaling;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class IndbetalingPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Indbetaling", 5);

    public IndbetalingPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement betalingsdaekningTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Betalingsdækning")));

    final PageElement manuelDaekningTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Manuel dækning")));

    final PageElement karakteregenskaberTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Karakteregenskaber")));


    public Indbetaling_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Indbetaling_PrimaerSubPage(this);
    }

    public Indbetaling_BetalingsdaekningSubPage activateBetalingsdaekning()
    {
        betalingsdaekningTab.click();
        return new Indbetaling_BetalingsdaekningSubPage(this);
    }

    public Indbetaling_ManuelDaekningSubPage activateManuelDaekning()
    {
        manuelDaekningTab.click();
        return new Indbetaling_ManuelDaekningSubPage(this);
    }

    public Indbetaling_KarakteregenskaberSubPage activateKarakteregenskaber()
    {
        karakteregenskaberTab.click();
        return new Indbetaling_KarakteregenskaberSubPage(this);
    }

    public boolean betalingsHarStatus(String status) {
        return betalingsdaekningTab.getText().equalsIgnoreCase(status);
    }


    // endregion

}

