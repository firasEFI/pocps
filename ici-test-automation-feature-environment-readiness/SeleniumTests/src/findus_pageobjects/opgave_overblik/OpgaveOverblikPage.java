package findus_pageobjects.opgave_overblik;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import findus_testobjects.IU_596_FremsoegOpgaveIOpgaveOverblikket;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class OpgaveOverblikPage extends BasePsrmPage {

    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Opgave overblik", 5);

    public OpgaveOverblikPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    public OpgaveOverblik_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new OpgaveOverblik_PrimaerSubPage(this);
    }
    
    public final IU_596_FremsoegOpgaveIOpgaveOverblikket iu_596_fremsøgOpgaveIOpgaveOverblikket = new IU_596_FremsoegOpgaveIOpgaveOverblikket(this);
}

