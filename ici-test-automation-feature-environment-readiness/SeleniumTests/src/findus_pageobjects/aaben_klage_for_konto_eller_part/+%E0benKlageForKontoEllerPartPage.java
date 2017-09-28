package findus_pageobjects.aaben_klage_for_konto_eller_part;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.SubPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class ÅbenKlageForKontoEllerPartPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Åben klage for konto eller part", 5);

    public ÅbenKlageForKontoEllerPartPage(){
        super(synchronizer);
    }
    PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public ÅbenKlageForKontoEllerPart_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new ÅbenKlageForKontoEllerPart_PrimaerSubPage(this);
    }


    // endregion

}

