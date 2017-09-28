package findus_pageobjects.aaben_klage_for_konto_eller_part;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class AabenKlageForKontoEllerPartPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Åben klage for konto eller part", 5);

    public AabenKlageForKontoEllerPartPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));


    public AabenKlageForKontoEllerPart_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new AabenKlageForKontoEllerPart_PrimaerSubPage(this);
    }


    // endregion

}

