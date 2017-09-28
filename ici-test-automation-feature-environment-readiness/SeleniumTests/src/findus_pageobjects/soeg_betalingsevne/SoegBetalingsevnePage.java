package findus_pageobjects.soeg_betalingsevne;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class SoegBetalingsevnePage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Søg betalingsevne", 5);

    public SoegBetalingsevnePage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public SoegBetalingsevne_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new SoegBetalingsevne_PrimaerSubPage(this);
    }

    public SoegBetalingsevne_SoegBetalingsevneSubPage selectSearchForSoegBetalingsevne() {
        multiQueryZoneFilters1.pick("Søg betalingsevne");

        return new SoegBetalingsevne_SoegBetalingsevneSubPage(this);
    }

    public SoegBetalingsevne_BeregnetBetalingsevneSubPage selectSearchForBeregnetBetalingsevne() {
        multiQueryZoneFilters1.pick("Beregnet betalingsevne");

        return new SoegBetalingsevne_BeregnetBetalingsevneSubPage(this);
    }


    // endregion

}

