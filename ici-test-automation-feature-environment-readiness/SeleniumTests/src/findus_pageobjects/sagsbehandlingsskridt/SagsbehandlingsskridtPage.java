package findus_pageobjects.sagsbehandlingsskridt;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.GenericInstantiator;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

import java.lang.reflect.ParameterizedType;

public abstract class SagsbehandlingsskridtPage<TPrimaerTabPage> extends BasePsrmPage {
    // region "auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Sagsbehandlingsskridt", 5);

    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));
    final PageElement logTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Log")));
    private final Button godkendButton = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));
    final PageElement udbetalingsBeloebLabel = new PageElement(Frames.zoneMapFrame_2, By.id("indProcedure_obligationBalance"));

    private final GenericInstantiator<TPrimaerTabPage> primaerTabPageInstantiator;

    protected SagsbehandlingsskridtPage() {
        super(synchronizer);

        Class<TPrimaerTabPage> primaerTabType = (Class<TPrimaerTabPage>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        this.primaerTabPageInstantiator = new GenericInstantiator<TPrimaerTabPage>(primaerTabType) {};
    }
    // endregion

    // region "tabs"
    public Sagsbehandlingsskridt_LogTabPage activateLogTab()
    {
        super.ensureSelectedTab(logTab);

        return new Sagsbehandlingsskridt_LogTabPage(this);
    }

    public TPrimaerTabPage activatePrimaerTab()
    {
        super.ensureSelectedTab(primaerTab);

        return this.primaerTabPageInstantiator.create();
    }
    
    public String getIDnumberOfOCRLine() {
    	return new PageElement(Frames.zoneMapFrame_2, By.id("OCR-line_id")).getText();
    }

    // endregion
}
