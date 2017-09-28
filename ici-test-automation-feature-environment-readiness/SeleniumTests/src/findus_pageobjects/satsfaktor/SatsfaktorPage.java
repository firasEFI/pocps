package findus_pageobjects.satsfaktor;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class SatsfaktorPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Satsfaktor", 5);

    public SatsfaktorPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement satsfaktorKarakteregenskaberTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Satsfaktor karakteregenskaber")));


    public Satsfaktor_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Satsfaktor_PrimaerSubPage(this);
    }

    public Satsfaktor_SatsfaktorKarakteregenskaberSubPage activateSatsfaktorKarakteregenskaber()
    {
        satsfaktorKarakteregenskaberTab.click();
        return new Satsfaktor_SatsfaktorKarakteregenskaberSubPage(this);
    }


    // endregion

}

