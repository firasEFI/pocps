package findus_pageobjects.refusionskontrol_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class RefusionskontrolForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Refusionskontrol forespørgsel", 5);

    public RefusionskontrolForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public RefusionskontrolForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new RefusionskontrolForespoergsel_PrimaerSubPage(this);
    }

    public RefusionskontrolForespoergsel_StiftelsesdatoTypeStatusSubPage selectSearchForStiftelsesdatoTypeStatus() {
        multiQueryZoneFilters1.pick("Stiftelsesdato/ type/ status");

        return new RefusionskontrolForespoergsel_StiftelsesdatoTypeStatusSubPage(this);
    }

    public RefusionskontrolForespoergsel_RefusionKontrolIdSubPage selectSearchForRefusionKontrolId() {
        multiQueryZoneFilters1.pick("Refusion kontrol ID");

        return new RefusionskontrolForespoergsel_RefusionKontrolIdSubPage(this);
    }


    // endregion

}

