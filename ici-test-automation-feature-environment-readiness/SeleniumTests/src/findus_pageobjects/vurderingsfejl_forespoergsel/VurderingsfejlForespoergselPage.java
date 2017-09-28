package findus_pageobjects.vurderingsfejl_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class VurderingsfejlForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Vurderingsfejl forespørgsel", 5);

    public VurderingsfejlForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public VurderingsfejlForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new VurderingsfejlForespoergsel_PrimaerSubPage(this);
    }

    public VurderingsfejlForespoergsel_StiftelsesdatoVaerdisaettelsestypeSubPage selectSearchForStiftelsesdatoVaerdisaettelsestype() {
        multiQueryZoneFilters1.pick("Stiftelsesdato/ værdisættelsestype");

        return new VurderingsfejlForespoergsel_StiftelsesdatoVaerdisaettelsestypeSubPage(this);
    }

    public VurderingsfejlForespoergsel_IndkomstperiodeOgVurderingstypeSubPage selectSearchForIndkomstperiodeOgVurderingstype() {
        multiQueryZoneFilters1.pick("Indkomstperiode og vurderingstype");

        return new VurderingsfejlForespoergsel_IndkomstperiodeOgVurderingstypeSubPage(this);
    }

    public VurderingsfejlForespoergsel_AktivEksterntIdTypeVaerdiSubPage selectSearchForAktivEksterntIdTypeVaerdi() {
        multiQueryZoneFilters1.pick("Aktiv eksternt ID type / værdi");

        return new VurderingsfejlForespoergsel_AktivEksterntIdTypeVaerdiSubPage(this);
    }


    // endregion

}

