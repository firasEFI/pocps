package findus_pageobjects.nedskrivningsforespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class NedskrivningsforespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Nedskrivningsforespørgsel", 5);

    public NedskrivningsforespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public Nedskrivningsforespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Nedskrivningsforespoergsel_PrimaerSubPage(this);
    }

    public Nedskrivningsforespoergsel_NavnPaaPartFordringsrelationNedskrivningstypeSubPage selectSearchForNavnPaaPartFordringsrelationNedskrivningstype() {
        multiQueryZoneFilters1.pick("Navn på part / Fordringsrelation / Nedskrivningstype");

        return new Nedskrivningsforespoergsel_NavnPaaPartFordringsrelationNedskrivningstypeSubPage(this);
    }

    public Nedskrivningsforespoergsel_KontoIdFordringshaverNedskrivningstypeSubPage selectSearchForKontoIdFordringshaverNedskrivningstype() {
        multiQueryZoneFilters1.pick("Konto ID / fordringshaver /  nedskrivningstype");

        return new Nedskrivningsforespoergsel_KontoIdFordringshaverNedskrivningstypeSubPage(this);
    }

    public Nedskrivningsforespoergsel_NedskrivningIdSubPage selectSearchForNedskrivningId() {
        multiQueryZoneFilters1.pick("Nedskrivning ID");

        return new Nedskrivningsforespoergsel_NedskrivningIdSubPage(this);
    }


    // endregion

}

