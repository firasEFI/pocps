package findus_pageobjects.partsfritagelser;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class PartsfritagelserPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Partsfritagelser", 5);

    public PartsfritagelserPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public Partsfritagelser_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Partsfritagelser_PrimaerSubPage(this);
    }

    public Partsfritagelser_PartensIdSubPage selectSearchForPartensId() {
        multiQueryZoneFilters1.pick("Partens ID");

        return new Partsfritagelser_PartensIdSubPage(this);
    }

    public Partsfritagelser_KontoIdSubPage selectSearchForKontoId() {
        multiQueryZoneFilters1.pick("Konto ID");

        return new Partsfritagelser_KontoIdSubPage(this);
    }

    public Partsfritagelser_FordringshaverrelationIdSubPage selectSearchForFordringshaverrelationId() {
        multiQueryZoneFilters1.pick("Fordringshaverrelation ID");

        return new Partsfritagelser_FordringshaverrelationIdSubPage(this);
    }

    public Partsfritagelser_FordringsIdSubPage selectSearchForFordringsId() {
        multiQueryZoneFilters1.pick("Fordrings ID");

        return new Partsfritagelser_FordringsIdSubPage(this);
    }

    public Partsfritagelser_FritagelsesIdSubPage selectSearchForFritagelsesId() {
        multiQueryZoneFilters1.pick("Fritagelses ID");

        return new Partsfritagelser_FritagelsesIdSubPage(this);
    }


    // endregion

}

