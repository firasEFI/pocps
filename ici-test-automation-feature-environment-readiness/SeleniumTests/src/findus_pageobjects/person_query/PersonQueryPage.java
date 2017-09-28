package findus_pageobjects.person_query;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class PersonQueryPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Person Query", 5);

    public PersonQueryPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public PersonQuery_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new PersonQuery_PrimaerSubPage(this);
    }

    public PersonQuery_NavnTypeSubPage selectSearchForNavnType() {
        multiQueryZoneFilters1.pick("Navn / type");

        return new PersonQuery_NavnTypeSubPage(this);
    }

    public PersonQuery_IdTypePaaPartenIdNummerSubPage selectSearchForIdTypePaaPartenIdNummer() {
        multiQueryZoneFilters1.pick("ID type på parten / ID nummer");

        return new PersonQuery_IdTypePaaPartenIdNummerSubPage(this);
    }

    public PersonQuery_PartensIdSubPage selectSearchForPartensId() {
        multiQueryZoneFilters1.pick("Partens ID");

        return new PersonQuery_PartensIdSubPage(this);
    }


    // endregion

}

