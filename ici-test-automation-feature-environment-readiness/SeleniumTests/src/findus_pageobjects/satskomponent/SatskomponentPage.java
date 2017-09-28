package findus_pageobjects.satskomponent;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class SatskomponentPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Satskomponent", 5);

    public SatskomponentPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement krydsreferenceTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Krydsreference")));

    final PageElement hbDaekningTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("HB dækning")));

    final PageElement karakteregenskaberTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Karakteregenskaber")));

    final PageElement berettigelseTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Berettigelse")));


    public Satskomponent_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Satskomponent_PrimaerSubPage(this);
    }

    public Satskomponent_KrydsreferenceSubPage activateKrydsreference()
    {
        krydsreferenceTab.click();
        return new Satskomponent_KrydsreferenceSubPage(this);
    }

    public Satskomponent_HbDaekningSubPage activateHbDaekning()
    {
        hbDaekningTab.click();
        return new Satskomponent_HbDaekningSubPage(this);
    }

    public Satskomponent_KarakteregenskaberSubPage activateKarakteregenskaber()
    {
        karakteregenskaberTab.click();
        return new Satskomponent_KarakteregenskaberSubPage(this);
    }

    public Satskomponent_BerettigelseSubPage activateBerettigelse()
    {
        berettigelseTab.click();
        return new Satskomponent_BerettigelseSubPage(this);
    }


    // endregion

}

