package findus_pageobjects.Brevoplysninger_soeg;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.Tab;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class BrevoplysningerSoegPage extends BasePsrmPage {

    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Brevoplysninger", 5);

    final Tab primaerTab = new Tab(Frames.tabPage, "Primær");

    protected BrevoplysningerSoegPage() {
        super(synchronizer);
    }

    public BrevoplysningerSoeg_PrimaerSubPage activatePrimaer()
    {
        primaerTab.ensureSelected();

        return new BrevoplysningerSoeg_PrimaerSubPage(this);
    }
}
