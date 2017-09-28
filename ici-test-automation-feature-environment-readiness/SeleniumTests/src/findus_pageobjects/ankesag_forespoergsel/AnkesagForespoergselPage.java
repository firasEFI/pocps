package findus_pageobjects.ankesag_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class AnkesagForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Ankesag forespørgsel", 5);

    public AnkesagForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public AnkesagForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new AnkesagForespoergsel_PrimaerSubPage(this);
    }

    public AnkesagForespoergsel_NavnPaaPartenOgIdTypePaaPartenVaerdiSubPage selectSearchForNavnPaaPartenOgIdTypePaaPartenVaerdi() {
        multiQueryZoneFilters1.pick("Navn på parten og ID type på parten / Værdi");

        return new AnkesagForespoergsel_NavnPaaPartenOgIdTypePaaPartenVaerdiSubPage(this);
    }

    public AnkesagForespoergsel_AnkesagDatoAnkesagstypeOgStatusSubPage selectSearchForAnkesagDatoAnkesagstypeOgStatus() {
        multiQueryZoneFilters1.pick("Ankesag dato, ankesagstype, og status");

        return new AnkesagForespoergsel_AnkesagDatoAnkesagstypeOgStatusSubPage(this);
    }

    public AnkesagForespoergsel_FritagedFordringSubPage selectSearchForFritagedFordring() {
        multiQueryZoneFilters1.pick("Fritaged fordring");

        return new AnkesagForespoergsel_FritagedFordringSubPage(this);
    }

    public AnkesagForespoergsel_AnkesagRelateretObjektSubPage selectSearchForAnkesagRelateretObjekt() {
        multiQueryZoneFilters1.pick("Ankesag relateret objekt");

        return new AnkesagForespoergsel_AnkesagRelateretObjektSubPage(this);
    }

    public AnkesagForespoergsel_ReviewInformationSubPage selectSearchForReviewInformation() {
        multiQueryZoneFilters1.pick("Review information");

        return new AnkesagForespoergsel_ReviewInformationSubPage(this);
    }

    public AnkesagForespoergsel_AnkesagIdSubPage selectSearchForAnkesagId() {
        multiQueryZoneFilters1.pick("Ankesag ID");

        return new AnkesagForespoergsel_AnkesagIdSubPage(this);
    }


    // endregion

}

