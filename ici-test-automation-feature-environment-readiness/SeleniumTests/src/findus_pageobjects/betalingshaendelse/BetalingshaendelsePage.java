package findus_pageobjects.betalingshaendelse;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.overblik_over_betalinger.OverblikOverBetalingerPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class BetalingshaendelsePage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Betalingshændelse", 5);

    public BetalingshaendelsePage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));
    final PageElement indbetalingerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Indbetalinger")));
    final PageElement betalingshændelsesKontotekstmenu = new PageElement(Frames.tabPage, By.id("IM_PayEvtInfo_pevtCtx"));
    final PageElement overblikOverBetaling = new PageElement(Frames.main, By.id("CI_CONTEXTPAYEVENT_subMenuItem0x2"));

    public Betalingshaendelse_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Betalingshaendelse_PrimaerSubPage(this);
    }

    public Betalingshaendelse_IndbetalingerSubPage activateIndbetalinger()
    {
        indbetalingerTab.click();
        return new Betalingshaendelse_IndbetalingerSubPage(this);
    }

    public OverblikOverBetalingerPage activateAndGoToOverblikOverBetaling(){
        betalingshændelsesKontotekstmenu.click();
        overblikOverBetaling.click();

        return new OverblikOverBetalingerPage();

    }


    // endregion

}

