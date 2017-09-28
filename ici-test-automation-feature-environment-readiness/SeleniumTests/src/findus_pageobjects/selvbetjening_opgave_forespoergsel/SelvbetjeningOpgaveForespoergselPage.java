package findus_pageobjects.selvbetjening_opgave_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class SelvbetjeningOpgaveForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Selvbetjening opgave forespørgsel", 5);

    public SelvbetjeningOpgaveForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public SelvbetjeningOpgaveForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new SelvbetjeningOpgaveForespoergsel_PrimaerSubPage(this);
    }

    public SelvbetjeningOpgaveForespoergsel_BekraeftelseIdSubPage selectSearchForBekraeftelseId() {
        multiQueryZoneFilters1.pick("Bekræftelse ID");

        return new SelvbetjeningOpgaveForespoergsel_BekraeftelseIdSubPage(this);
    }

    public SelvbetjeningOpgaveForespoergsel_HoeringInfoOpgavetypeStatusDatoSubPage selectSearchForHoeringInfoOpgavetypeStatusDato() {
        multiQueryZoneFilters1.pick("Høring info/ Opgavetype/ status/ dato");

        return new SelvbetjeningOpgaveForespoergsel_HoeringInfoOpgavetypeStatusDatoSubPage(this);
    }

    public SelvbetjeningOpgaveForespoergsel_RelateretObjektOpgavetypeStatusSubPage selectSearchForRelateretObjektOpgavetypeStatus() {
        multiQueryZoneFilters1.pick("Relateret objekt / Opgavetype / Status");

        return new SelvbetjeningOpgaveForespoergsel_RelateretObjektOpgavetypeStatusSubPage(this);
    }

    public SelvbetjeningOpgaveForespoergsel_ServiceopgaveIdSubPage selectSearchForServiceopgaveId() {
        multiQueryZoneFilters1.pick("Serviceopgave ID");

        return new SelvbetjeningOpgaveForespoergsel_ServiceopgaveIdSubPage(this);
    }


    // endregion

}

