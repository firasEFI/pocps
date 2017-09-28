package findus_pageobjects.sidehoved_for_batch_formularer_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class SidehovedForBatchFormularerForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Sidehoved for batch formularer forespørgsel", 5);

    public SidehovedForBatchFormularerForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public SidehovedForBatchFormularerForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new SidehovedForBatchFormularerForespoergsel_PrimaerSubPage(this);
    }

    public SidehovedForBatchFormularerForespoergsel_StiftelsesdatoStatusFormularkildeSubPage selectSearchForStiftelsesdatoStatusFormularkilde() {
        multiQueryZoneFilters1.pick("Stiftelsesdato/status/ formularkilde");

        return new SidehovedForBatchFormularerForespoergsel_StiftelsesdatoStatusFormularkildeSubPage(this);
    }

    public SidehovedForBatchFormularerForespoergsel_EksterntUdformningAfBatchIdSubPage selectSearchForEksterntUdformningAfBatchId() {
        multiQueryZoneFilters1.pick("Eksternt udformning af batch ID");

        return new SidehovedForBatchFormularerForespoergsel_EksterntUdformningAfBatchIdSubPage(this);
    }

    public SidehovedForBatchFormularerForespoergsel_SidehovedForBatchFormularerIdSubPage selectSearchForSidehovedForBatchFormularerId() {
        multiQueryZoneFilters1.pick("Sidehoved for batch formularer ID");

        return new SidehovedForBatchFormularerForespoergsel_SidehovedForBatchFormularerIdSubPage(this);
    }

    public SidehovedForBatchFormularerForespoergsel_SamletBetalingsbeloebSubPage selectSearchForSamletBetalingsbeloeb() {
        multiQueryZoneFilters1.pick("Samlet betalingsbeløb");

        return new SidehovedForBatchFormularerForespoergsel_SamletBetalingsbeloebSubPage(this);
    }


    // endregion

}

