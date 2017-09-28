package findus_pageobjects.formularkontrol_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class FormularkontrolForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Formularkontrol forespørgsel", 5);

    public FormularkontrolForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public FormularkontrolForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new FormularkontrolForespoergsel_PrimaerSubPage(this);
    }

    public FormularkontrolForespoergsel_StiftelsesdatoTypeStatusSubPage selectSearchForStiftelsesdatoTypeStatus() {
        multiQueryZoneFilters1.pick("Stiftelsesdato/ type/ status");

        return new FormularkontrolForespoergsel_StiftelsesdatoTypeStatusSubPage(this);
    }

    public FormularkontrolForespoergsel_FormularkontrolIdSubPage selectSearchForFormularkontrolId() {
        multiQueryZoneFilters1.pick("Formularkontrol ID");

        return new FormularkontrolForespoergsel_FormularkontrolIdSubPage(this);
    }


    // endregion

}

