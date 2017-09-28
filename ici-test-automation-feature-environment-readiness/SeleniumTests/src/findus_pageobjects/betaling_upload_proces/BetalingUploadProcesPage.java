package findus_pageobjects.betaling_upload_proces;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class BetalingUploadProcesPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Betaling upload proces", 5);

    public BetalingUploadProcesPage(){
        super(synchronizer);
    }
    final PageElement indbetalingsdetaljeTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Indbetalingsdetalje")));

    final PageElement betalingsraadTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Betalingsråd")));


    public BetalingUploadProces_IndbetalingsdetaljeSubPage activateIndbetalingsdetalje()
    {
        indbetalingsdetaljeTab.click();
        return new BetalingUploadProces_IndbetalingsdetaljeSubPage(this);
    }

    public BetalingUploadProces_BetalingsraadSubPage activateBetalingsraad()
    {
        betalingsraadTab.click();
        return new BetalingUploadProces_BetalingsraadSubPage(this);
    }


    // endregion

}

