package findus_pageobjects.overbetalingsproces_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class OverbetalingsprocesForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Overbetalingsproces forespørgsel", 5);

    public OverbetalingsprocesForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public OverbetalingsprocesForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new OverbetalingsprocesForespoergsel_PrimaerSubPage(this);
    }

    public OverbetalingsprocesForespoergsel_StiftelsesdatoTypeStatusSubPage selectSearchForStiftelsesdatoTypeStatus() {
        multiQueryZoneFilters1.pick("Stiftelsesdato/ type/ status");

        return new OverbetalingsprocesForespoergsel_StiftelsesdatoTypeStatusSubPage(this);
    }

    public OverbetalingsprocesForespoergsel_NavnPaaPartIdTypeIdRefusionsbeloebSubPage selectSearchForNavnPaaPartIdTypeIdRefusionsbeloeb() {
        multiQueryZoneFilters1.pick("Navn på part / ID type / ID / Refusionsbeløb");

        return new OverbetalingsprocesForespoergsel_NavnPaaPartIdTypeIdRefusionsbeloebSubPage(this);
    }

    public OverbetalingsprocesForespoergsel_KontoIdTypeStatusSubPage selectSearchForKontoIdTypeStatus() {
        multiQueryZoneFilters1.pick("Konto ID / type / status");

        return new OverbetalingsprocesForespoergsel_KontoIdTypeStatusSubPage(this);
    }

    public OverbetalingsprocesForespoergsel_OverbetalingsprocesIdSubPage selectSearchForOverbetalingsprocesId() {
        multiQueryZoneFilters1.pick("Overbetalingsproces ID");

        return new OverbetalingsprocesForespoergsel_OverbetalingsprocesIdSubPage(this);
    }


    // endregion

}

