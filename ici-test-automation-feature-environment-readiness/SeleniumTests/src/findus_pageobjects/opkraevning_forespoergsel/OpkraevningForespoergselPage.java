package findus_pageobjects.opkraevning_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class OpkraevningForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Opkrævning forespørgsel", 5);

    public OpkraevningForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public OpkraevningForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new OpkraevningForespoergsel_PrimaerSubPage(this);
    }

    public OpkraevningForespoergsel_AktivIdSubPage selectSearchForAktivId() {
        multiQueryZoneFilters1.pick("Aktiv ID");

        return new OpkraevningForespoergsel_AktivIdSubPage(this);
    }

    public OpkraevningForespoergsel_FordringshaverrelationIdSubPage selectSearchForFordringshaverrelationId() {
        multiQueryZoneFilters1.pick("Fordringshaverrelation ID");

        return new OpkraevningForespoergsel_FordringshaverrelationIdSubPage(this);
    }

    public OpkraevningForespoergsel_OpkraevningIdSubPage selectSearchForOpkraevningId() {
        multiQueryZoneFilters1.pick("Opkrævning ID");

        return new OpkraevningForespoergsel_OpkraevningIdSubPage(this);
    }


    // endregion

}

