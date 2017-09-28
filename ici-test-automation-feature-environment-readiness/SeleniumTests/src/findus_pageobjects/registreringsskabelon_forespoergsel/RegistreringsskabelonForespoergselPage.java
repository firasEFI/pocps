package findus_pageobjects.registreringsskabelon_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class RegistreringsskabelonForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Registreringsskabelon forespørgsel", 5);

    public RegistreringsskabelonForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public RegistreringsskabelonForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new RegistreringsskabelonForespoergsel_PrimaerSubPage(this);
    }

    public RegistreringsskabelonForespoergsel_PartSubPage selectSearchForPart() {
        multiQueryZoneFilters1.pick("Part");

        return new RegistreringsskabelonForespoergsel_PartSubPage(this);
    }

    public RegistreringsskabelonForespoergsel_ModtagelsesdatoFormulartypeStatusSubPage selectSearchForModtagelsesdatoFormulartypeStatus() {
        multiQueryZoneFilters1.pick("Modtagelsesdato / Formulartype / Status");

        return new RegistreringsskabelonForespoergsel_ModtagelsesdatoFormulartypeStatusSubPage(this);
    }

    public RegistreringsskabelonForespoergsel_AdresseSubPage selectSearchForAdresse() {
        multiQueryZoneFilters1.pick("Adresse");

        return new RegistreringsskabelonForespoergsel_AdresseSubPage(this);
    }

    public RegistreringsskabelonForespoergsel_EksterntUdformAfBatchIdSidehovedForBatchformularerSubPage selectSearchForEksterntUdformAfBatchIdSidehovedForBatchformularer() {
        multiQueryZoneFilters1.pick("Eksternt udform. af batch ID / Sidehoved for batchformularer");

        return new RegistreringsskabelonForespoergsel_EksterntUdformAfBatchIdSidehovedForBatchformularerSubPage(this);
    }

    public RegistreringsskabelonForespoergsel_DokumentLocatorSubPage selectSearchForDokumentLocator() {
        multiQueryZoneFilters1.pick("Dokument locator");

        return new RegistreringsskabelonForespoergsel_DokumentLocatorSubPage(this);
    }


    // endregion

}

