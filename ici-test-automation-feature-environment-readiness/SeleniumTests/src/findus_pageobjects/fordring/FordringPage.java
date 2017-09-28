package findus_pageobjects.fordring;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class FordringPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Fordring", 5);

    public FordringPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement satsInfoTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Sats info")));

    final PageElement karakTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Karak.")));

    final PageElement genberegnBetalingerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Genberegn betalinger")));

    final PageElement divTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Div")));


    public Fordring_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Fordring_PrimaerSubPage(this);
    }

    public Fordring_SatsInfoSubPage activateSatsInfo()
    {
        satsInfoTab.click();
        return new Fordring_SatsInfoSubPage(this);
    }

    public Fordring_KarakSubPage activateKarak()
    {
        karakTab.click();
        return new Fordring_KarakSubPage(this);
    }

    public Fordring_GenberegnBetalingerSubPage activateGenberegnBetalinger()
    {
        genberegnBetalingerTab.click();
        return new Fordring_GenberegnBetalingerSubPage(this);
    }

    public Fordring_DivSubPage activateDiv()
    {
        divTab.click();
        return new Fordring_DivSubPage(this);
    }


    // endregion

}

