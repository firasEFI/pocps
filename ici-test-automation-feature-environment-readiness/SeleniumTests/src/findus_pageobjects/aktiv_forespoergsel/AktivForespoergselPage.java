package findus_pageobjects.aktiv_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class AktivForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Aktiv forespørgsel", 5);

    public AktivForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public AktivForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new AktivForespoergsel_PrimaerSubPage(this);
    }

    public AktivForespoergsel_NavnSubPage selectSearchForNavn() {
        multiQueryZoneFilters1.pick("Navn");

        return new AktivForespoergsel_NavnSubPage(this);
    }

    public AktivForespoergsel_AdresseSubPage selectSearchForAdresse() {
        multiQueryZoneFilters1.pick("Adresse");

        return new AktivForespoergsel_AdresseSubPage(this);
    }

    public AktivForespoergsel_AktivIdSubPage selectSearchForAktivId() {
        multiQueryZoneFilters1.pick("Aktiv ID");

        return new AktivForespoergsel_AktivIdSubPage(this);
    }


    // endregion

}

