package findus_pageobjects.konkurs_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class KonkursForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Konkurs forespørgsel", 5);

    public KonkursForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public KonkursForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new KonkursForespoergsel_PrimaerSubPage(this);
    }

    public KonkursForespoergsel_NavnPaaPartenOgIdTypePaaPartenVaerdiSubPage selectSearchForNavnPaaPartenOgIdTypePaaPartenVaerdi() {
        multiQueryZoneFilters1.pick("Navn på parten og ID type på parten / Værdi");

        return new KonkursForespoergsel_NavnPaaPartenOgIdTypePaaPartenVaerdiSubPage(this);
    }

    public KonkursForespoergsel_BegaeringdatoTypeStatusDomstolSubPage selectSearchForBegaeringdatoTypeStatusDomstol() {
        multiQueryZoneFilters1.pick("Begæringdato/ Type/ Status/Domstol");

        return new KonkursForespoergsel_BegaeringdatoTypeStatusDomstolSubPage(this);
    }

    public KonkursForespoergsel_EksternSagsidentifikationSubPage selectSearchForEksternSagsidentifikation() {
        multiQueryZoneFilters1.pick("Ekstern sagsidentifikation");

        return new KonkursForespoergsel_EksternSagsidentifikationSubPage(this);
    }

    public KonkursForespoergsel_IndeholdteFordringSubPage selectSearchForIndeholdteFordring() {
        multiQueryZoneFilters1.pick("Indeholdte fordring");

        return new KonkursForespoergsel_IndeholdteFordringSubPage(this);
    }

    public KonkursForespoergsel_SagsbehandlerSubPage selectSearchForSagsbehandler() {
        multiQueryZoneFilters1.pick("Sagsbehandler");

        return new KonkursForespoergsel_SagsbehandlerSubPage(this);
    }

    public KonkursForespoergsel_PartensIdSubPage selectSearchForPartensId() {
        multiQueryZoneFilters1.pick("Partens ID");

        return new KonkursForespoergsel_PartensIdSubPage(this);
    }

    public KonkursForespoergsel_KonkursIdSubPage selectSearchForKonkursId() {
        multiQueryZoneFilters1.pick("Konkurs ID");

        return new KonkursForespoergsel_KonkursIdSubPage(this);
    }


    // endregion

}

