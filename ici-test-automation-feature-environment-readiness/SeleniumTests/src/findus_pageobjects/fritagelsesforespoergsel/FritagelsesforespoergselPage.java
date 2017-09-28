package findus_pageobjects.fritagelsesforespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class FritagelsesforespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Fritagelsesforespørgsel", 5);

    public FritagelsesforespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public Fritagelsesforespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Fritagelsesforespoergsel_PrimaerSubPage(this);
    }

    public Fritagelsesforespoergsel_PartensIdSubPage selectSearchForPartensId() {
        multiQueryZoneFilters1.pick("Partens ID");

        return new Fritagelsesforespoergsel_PartensIdSubPage(this);
    }

    public Fritagelsesforespoergsel_KontoIdSubPage selectSearchForKontoId() {
        multiQueryZoneFilters1.pick("Konto ID");

        return new Fritagelsesforespoergsel_KontoIdSubPage(this);
    }

    public Fritagelsesforespoergsel_FordringshaverrelationIdSubPage selectSearchForFordringshaverrelationId() {
        multiQueryZoneFilters1.pick("Fordringshaverrelation ID");

        return new Fritagelsesforespoergsel_FordringshaverrelationIdSubPage(this);
    }

    public Fritagelsesforespoergsel_FordringsIdSubPage selectSearchForFordringsId() {
        multiQueryZoneFilters1.pick("Fordrings ID");

        return new Fritagelsesforespoergsel_FordringsIdSubPage(this);
    }

    public Fritagelsesforespoergsel_FritagelsesIdSubPage selectSearchForFritagelsesId() {
        multiQueryZoneFilters1.pick("Fritagelses ID");

        return new Fritagelsesforespoergsel_FritagelsesIdSubPage(this);
    }


    // endregion

}

