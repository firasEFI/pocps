package findus_pageobjects.adresseforespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class AdresseforespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Adresseforespørgsel", 5);

    public AdresseforespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public Adresseforespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Adresseforespoergsel_PrimaerSubPage(this);
    }

    public Adresseforespoergsel_AdresseByStatPostnummerSubPage selectSearchForAdresseByStatPostnummer() {
        multiQueryZoneFilters1.pick("Adresse, by, stat, postnummer");

        return new Adresseforespoergsel_AdresseByStatPostnummerSubPage(this);
    }

    public Adresseforespoergsel_AdresseIdSubPage selectSearchForAdresseId() {
        multiQueryZoneFilters1.pick("Adresse ID");

        return new Adresseforespoergsel_AdresseIdSubPage(this);
    }


    // endregion

}

