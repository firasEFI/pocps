package findus_pageobjects.hoeringssag_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class HoeringssagForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Høringssag forespørgsel", 5);

    public HoeringssagForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public HoeringssagForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new HoeringssagForespoergsel_PrimaerSubPage(this);
    }

    public HoeringssagForespoergsel_NavnPaaPartenOgIdTypePaaPartenVaerdiSubPage selectSearchForNavnPaaPartenOgIdTypePaaPartenVaerdi() {
        multiQueryZoneFilters1.pick("Navn på parten og ID type på parten / Værdi");

        return new HoeringssagForespoergsel_NavnPaaPartenOgIdTypePaaPartenVaerdiSubPage(this);
    }

    public HoeringssagForespoergsel_DataomfangHoeringssagstypeOgStatusSubPage selectSearchForDataomfangHoeringssagstypeOgStatus() {
        multiQueryZoneFilters1.pick("Dataomfang, høringssagstype og status");

        return new HoeringssagForespoergsel_DataomfangHoeringssagstypeOgStatusSubPage(this);
    }

    public HoeringssagForespoergsel_HoertFordringIdSubPage selectSearchForHoertFordringId() {
        multiQueryZoneFilters1.pick("Hørt fordring ID");

        return new HoeringssagForespoergsel_HoertFordringIdSubPage(this);
    }

    public HoeringssagForespoergsel_SagsbehandlerSubPage selectSearchForSagsbehandler() {
        multiQueryZoneFilters1.pick("Sagsbehandler");

        return new HoeringssagForespoergsel_SagsbehandlerSubPage(this);
    }

    public HoeringssagForespoergsel_PrimaerKontoIdSubPage selectSearchForPrimaerKontoId() {
        multiQueryZoneFilters1.pick("Primær konto ID");

        return new HoeringssagForespoergsel_PrimaerKontoIdSubPage(this);
    }

    public HoeringssagForespoergsel_HoeringssagIdSubPage selectSearchForHoeringssagId() {
        multiQueryZoneFilters1.pick("Høringssag ID");

        return new HoeringssagForespoergsel_HoeringssagIdSubPage(this);
    }


    // endregion

}

