package findus_pageobjects.wizards.indtaegtsfoering;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class IndtaegtsfoeringWizardPage extends BaseWizardPage {

    public IndtaegtsfoeringWizardPage() {
        super("Indtægtsføring");
    }

// region "PageElements"
    final Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));

// endregion

}

