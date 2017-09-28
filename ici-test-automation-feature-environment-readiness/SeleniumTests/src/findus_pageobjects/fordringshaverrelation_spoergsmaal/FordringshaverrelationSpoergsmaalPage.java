package findus_pageobjects.fordringshaverrelation_spoergsmaal;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class FordringshaverrelationSpoergsmaalPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Fordringshaverrelation spørgsmål", 5);

    public FordringshaverrelationSpoergsmaalPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public FordringshaverrelationSpoergsmaal_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new FordringshaverrelationSpoergsmaal_PrimaerSubPage(this);
    }

    public FordringshaverrelationSpoergsmaal_NavnPaaPartenOgIdTypePaaPartenVaerdiSubPage selectSearchForNavnPaaPartenOgIdTypePaaPartenVaerdi() {
        multiQueryZoneFilters1.pick("Navn på parten og ID type på parten / Værdi");

        return new FordringshaverrelationSpoergsmaal_NavnPaaPartenOgIdTypePaaPartenVaerdiSubPage(this);
    }

    public FordringshaverrelationSpoergsmaal_KontoSubPage selectSearchForKonto() {
        multiQueryZoneFilters1.pick("Konto");

        return new FordringshaverrelationSpoergsmaal_KontoSubPage(this);
    }

    public FordringshaverrelationSpoergsmaal_EksterntIdSubPage selectSearchForEksterntId() {
        multiQueryZoneFilters1.pick("Eksternt ID");

        return new FordringshaverrelationSpoergsmaal_EksterntIdSubPage(this);
    }

    public FordringshaverrelationSpoergsmaal_AktivIdSubPage selectSearchForAktivId() {
        multiQueryZoneFilters1.pick("Aktiv ID");

        return new FordringshaverrelationSpoergsmaal_AktivIdSubPage(this);
    }

    public FordringshaverrelationSpoergsmaal_FordringshaverrelationIdSubPage selectSearchForFordringshaverrelationId() {
        multiQueryZoneFilters1.pick("Fordringshaverrelation ID");

        return new FordringshaverrelationSpoergsmaal_FordringshaverrelationIdSubPage(this);
    }


    // endregion

}

