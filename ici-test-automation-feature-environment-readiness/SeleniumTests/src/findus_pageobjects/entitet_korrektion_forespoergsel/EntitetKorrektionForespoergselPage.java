package findus_pageobjects.entitet_korrektion_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class EntitetKorrektionForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Entitet korrektion forespørgsel", 5);

    public EntitetKorrektionForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public EntitetKorrektionForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new EntitetKorrektionForespoergsel_PrimaerSubPage(this);
    }

    public EntitetKorrektionForespoergsel_StiftelsesdatoTypeStatusSubPage selectSearchForStiftelsesdatoTypeStatus() {
        multiQueryZoneFilters1.pick("Stiftelsesdato/ type/ status");

        return new EntitetKorrektionForespoergsel_StiftelsesdatoTypeStatusSubPage(this);
    }

    public EntitetKorrektionForespoergsel_EntitetKorrektionIdSubPage selectSearchForEntitetKorrektionId() {
        multiQueryZoneFilters1.pick("Entitet korrektion ID");

        return new EntitetKorrektionForespoergsel_EntitetKorrektionIdSubPage(this);
    }


    // endregion

}

