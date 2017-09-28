package findus_pageobjects.partsforloeb;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.WebList;
import findus_pageobjects.opgave.OpgavePage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class PartsforloebPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Partsforløb", 5);

    WebList lstPartsforloeb = new WebList(Frames.tabPage, "dataExplorerTable1");

    public PartsforloebPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public Partsforloeb_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Partsforloeb_PrimaerSubPage(this);
    }

    public OpgavePage activateGodkendUdbetalingLigmedEllerOverBeloebsGraensen() {
        lstPartsforloeb.clickCell("Sag", "Godkend udbetaling lig med eller over beløbsgrænsen, Oprettet", "Sag");
        return new OpgavePage();
    }

    public OpgavePage aktiverOpgaveMedSag(String titlePaaSag) {
        lstPartsforloeb.clickCell("Sag", titlePaaSag, "Sag");
        return new OpgavePage();
    }

    // endregion

}

