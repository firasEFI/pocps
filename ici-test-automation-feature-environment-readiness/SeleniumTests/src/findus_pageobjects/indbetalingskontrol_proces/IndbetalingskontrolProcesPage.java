package findus_pageobjects.indbetalingskontrol_proces;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class IndbetalingskontrolProcesPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Indbetalingskontrol proces", 5);

    public IndbetalingskontrolProcesPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement indbetalingskildeProcesTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Indbetalingskilde proces")));


    public IndbetalingskontrolProces_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new IndbetalingskontrolProces_PrimaerSubPage(this);
    }

    public IndbetalingskontrolProces_IndbetalingskildeProcesSubPage activateIndbetalingskildeProces()
    {
        indbetalingskildeProcesTab.click();
        return new IndbetalingskontrolProces_IndbetalingskildeProcesSubPage(this);
    }


    // endregion

}

