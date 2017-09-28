package findus_pageobjects.wizards.opret_udlaegssag;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class OpretUdlaegssagWizardPage extends BaseWizardPage {

    public OpretUdlaegssagWizardPage() {
        super("Opret udlægssag");
    }

// region "PageElements"
    final Button btnVaelgAlt = new Button(Frames.uiMap, By.id("select_all_button"));
    final Button btnFravaelgAlt = new Button(Frames.uiMap, By.id("deselect_all_button"));
    final Button btnShowRelatedClaims = new Button(Frames.uiMap, By.id("show_related_claims"));
    final Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));

// endregion

}

