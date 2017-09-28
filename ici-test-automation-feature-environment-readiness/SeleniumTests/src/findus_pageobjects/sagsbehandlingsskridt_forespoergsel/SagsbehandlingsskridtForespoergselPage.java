package findus_pageobjects.sagsbehandlingsskridt_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class SagsbehandlingsskridtForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Sagsbehandlingsskridt forespørgsel", 5);

    public SagsbehandlingsskridtForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public SagsbehandlingsskridtForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new SagsbehandlingsskridtForespoergsel_PrimaerSubPage(this);
    }

    public SagsbehandlingsskridtForespoergsel_NavnSubPage selectSearchForNavn() {
        multiQueryZoneFilters1.pick("Navn");

        return new SagsbehandlingsskridtForespoergsel_NavnSubPage(this);
    }

    public SagsbehandlingsskridtForespoergsel_PartensIdSubPage selectSearchForPartensId() {
        multiQueryZoneFilters1.pick("Partens ID");

        return new SagsbehandlingsskridtForespoergsel_PartensIdSubPage(this);
    }

    public SagsbehandlingsskridtForespoergsel_KontoIdSubPage selectSearchForKontoId() {
        multiQueryZoneFilters1.pick("Konto ID");

        return new SagsbehandlingsskridtForespoergsel_KontoIdSubPage(this);
    }

    public SagsbehandlingsskridtForespoergsel_FordringshaverrelationIdSubPage selectSearchForFordringshaverrelationId() {
        multiQueryZoneFilters1.pick("Fordringshaverrelation ID");

        return new SagsbehandlingsskridtForespoergsel_FordringshaverrelationIdSubPage(this);
    }

    public SagsbehandlingsskridtForespoergsel_FordringsIdSubPage selectSearchForFordringsId() {
        multiQueryZoneFilters1.pick("Fordrings ID");

        return new SagsbehandlingsskridtForespoergsel_FordringsIdSubPage(this);
    }

    public SagsbehandlingsskridtForespoergsel_SagsbehandlingsskridtIdSubPage selectSearchForSagsbehandlingsskridtId() {
        multiQueryZoneFilters1.pick("Sagsbehandlingsskridt ID");

        return new SagsbehandlingsskridtForespoergsel_SagsbehandlingsskridtIdSubPage(this);
    }


    // endregion

}

