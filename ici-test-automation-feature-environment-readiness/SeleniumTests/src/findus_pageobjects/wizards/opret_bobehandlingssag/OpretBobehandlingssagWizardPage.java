package findus_pageobjects.wizards.opret_bobehandlingssag;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class OpretBobehandlingssagWizardPage extends BaseWizardPage {

    public OpretBobehandlingssagWizardPage() {
        super("Opret bobehandlingssag");
    }

// region "PageElements"
    final Button btnVaelgAlt = new Button(Frames.uiMap, By.id("select_all_button"));
    final Button btnFravaelgAlt = new Button(Frames.uiMap, By.id("deselect_all_button"));
    final Button btnShowRelatedClaims = new Button(Frames.uiMap, By.id("show_related_claims"));
    final Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));

// endregion

}

