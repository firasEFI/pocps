package findus_pageobjects.forespoergsel_paa_inddrivelsessag;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class ForespoergselPaaInddrivelsessagPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Forespørgsel på inddrivelsessag", 5);

    public ForespoergselPaaInddrivelsessagPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public ForespoergselPaaInddrivelsessag_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new ForespoergselPaaInddrivelsessag_PrimaerSubPage(this);
    }

    public ForespoergselPaaInddrivelsessag_NavnSubPage selectSearchForNavn() {
        multiQueryZoneFilters1.pick("Navn");

        return new ForespoergselPaaInddrivelsessag_NavnSubPage(this);
    }

    public ForespoergselPaaInddrivelsessag_InddrivelsessagIdSubPage selectSearchForInddrivelsessagId() {
        multiQueryZoneFilters1.pick("Inddrivelsessag ID");

        return new ForespoergselPaaInddrivelsessag_InddrivelsessagIdSubPage(this);
    }

    public ForespoergselPaaInddrivelsessag_PartensIdStiftelsesdatoSubPage selectSearchForPartensIdStiftelsesdato() {
        multiQueryZoneFilters1.pick("Partens ID / Stiftelsesdato");

        return new ForespoergselPaaInddrivelsessag_PartensIdStiftelsesdatoSubPage(this);
    }


    // endregion

}

