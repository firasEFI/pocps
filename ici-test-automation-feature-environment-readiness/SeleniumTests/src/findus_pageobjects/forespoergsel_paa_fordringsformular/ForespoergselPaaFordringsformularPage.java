package findus_pageobjects.forespoergsel_paa_fordringsformular;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class ForespoergselPaaFordringsformularPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Forespørgsel på fordringsformular", 5);

    public ForespoergselPaaFordringsformularPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public ForespoergselPaaFordringsformular_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new ForespoergselPaaFordringsformular_PrimaerSubPage(this);
    }

    public ForespoergselPaaFordringsformular_PartSubPage selectSearchForPart() {
        multiQueryZoneFilters1.pick("Part");

        return new ForespoergselPaaFordringsformular_PartSubPage(this);
    }

    public ForespoergselPaaFordringsformular_ModtagelsesdatoTypeStatusArkiveringstypeSubPage selectSearchForModtagelsesdatoTypeStatusArkiveringstype() {
        multiQueryZoneFilters1.pick("Modtagelsesdato / Type / Status / Arkiveringstype");

        return new ForespoergselPaaFordringsformular_ModtagelsesdatoTypeStatusArkiveringstypeSubPage(this);
    }

    public ForespoergselPaaFordringsformular_AdresseSubPage selectSearchForAdresse() {
        multiQueryZoneFilters1.pick("Adresse");

        return new ForespoergselPaaFordringsformular_AdresseSubPage(this);
    }

    public ForespoergselPaaFordringsformular_EksterntBatchIdSidehovedForBatchformularerSubPage selectSearchForEksterntBatchIdSidehovedForBatchformularer() {
        multiQueryZoneFilters1.pick("Eksternt batch ID / Sidehoved for batchformularer");

        return new ForespoergselPaaFordringsformular_EksterntBatchIdSidehovedForBatchformularerSubPage(this);
    }

    public ForespoergselPaaFordringsformular_DokumentLocatorSubPage selectSearchForDokumentLocator() {
        multiQueryZoneFilters1.pick("Dokument locator");

        return new ForespoergselPaaFordringsformular_DokumentLocatorSubPage(this);
    }


    // endregion

}

