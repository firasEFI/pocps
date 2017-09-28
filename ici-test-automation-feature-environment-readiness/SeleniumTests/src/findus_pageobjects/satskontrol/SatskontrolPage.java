package findus_pageobjects.satskontrol;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class SatskontrolPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Satskontrol", 5);

    public SatskontrolPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final PageElement resultatBeregnlinjerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Resultat - beregn.linjer")));

    final PageElement resultatRqDetaljerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Resultat - RQ detaljer")));

    final PageElement resultaterBeregningsdetaljerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Resultater - beregningsdetaljer")));

    final PageElement resultatKarakteregenskaberTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Resultat - karakteregenskaber")));


    public Satskontrol_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new Satskontrol_PrimaerSubPage(this);
    }

    public Satskontrol_ResultatBeregnlinjerSubPage activateResultatBeregnlinjer()
    {
        resultatBeregnlinjerTab.click();
        return new Satskontrol_ResultatBeregnlinjerSubPage(this);
    }

    public Satskontrol_ResultatRqDetaljerSubPage activateResultatRqDetaljer()
    {
        resultatRqDetaljerTab.click();
        return new Satskontrol_ResultatRqDetaljerSubPage(this);
    }

    public Satskontrol_ResultaterBeregningsdetaljerSubPage activateResultaterBeregningsdetaljer()
    {
        resultaterBeregningsdetaljerTab.click();
        return new Satskontrol_ResultaterBeregningsdetaljerSubPage(this);
    }

    public Satskontrol_ResultatKarakteregenskaberSubPage activateResultatKarakteregenskaber()
    {
        resultatKarakteregenskaberTab.click();
        return new Satskontrol_ResultatKarakteregenskaberSubPage(this);
    }


    // endregion

}

