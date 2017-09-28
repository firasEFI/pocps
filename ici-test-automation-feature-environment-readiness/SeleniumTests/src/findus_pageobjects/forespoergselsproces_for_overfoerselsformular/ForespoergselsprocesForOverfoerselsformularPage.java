package findus_pageobjects.forespoergselsproces_for_overfoerselsformular;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class ForespoergselsprocesForOverfoerselsformularPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Forespørgselsproces for overførselsformular", 5);

    public ForespoergselsprocesForOverfoerselsformularPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public ForespoergselsprocesForOverfoerselsformular_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new ForespoergselsprocesForOverfoerselsformular_PrimaerSubPage(this);
    }

    public ForespoergselsprocesForOverfoerselsformular_DataTypeStatusSubPage selectSearchForDataTypeStatus() {
        multiQueryZoneFilters1.pick("Data/ type/ status");

        return new ForespoergselsprocesForOverfoerselsformular_DataTypeStatusSubPage(this);
    }

    public ForespoergselsprocesForOverfoerselsformular_EksterntBatchIdSidehovedForBatchformularerSubPage selectSearchForEksterntBatchIdSidehovedForBatchformularer() {
        multiQueryZoneFilters1.pick("Eksternt batch ID / Sidehoved for batchformularer");

        return new ForespoergselsprocesForOverfoerselsformular_EksterntBatchIdSidehovedForBatchformularerSubPage(this);
    }


    // endregion

}

