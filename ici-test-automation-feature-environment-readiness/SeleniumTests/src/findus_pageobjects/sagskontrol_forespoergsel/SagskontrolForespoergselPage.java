package findus_pageobjects.sagskontrol_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class SagskontrolForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Sagskontrol forespørgsel", 5);

    public SagskontrolForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public SagskontrolForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new SagskontrolForespoergsel_PrimaerSubPage(this);
    }

    public SagskontrolForespoergsel_StiftelsesdatoHaendelsestypeTypeStatusSubPage selectSearchForStiftelsesdatoHaendelsestypeTypeStatus() {
        multiQueryZoneFilters1.pick("Stiftelsesdato/ hændelsestype/ type/ status");

        return new SagskontrolForespoergsel_StiftelsesdatoHaendelsestypeTypeStatusSubPage(this);
    }

    public SagskontrolForespoergsel_ForfaldenIdKontrolSubPage selectSearchForForfaldenIdKontrol() {
        multiQueryZoneFilters1.pick("Forfalden ID-kontrol");

        return new SagskontrolForespoergsel_ForfaldenIdKontrolSubPage(this);
    }


    // endregion

}

