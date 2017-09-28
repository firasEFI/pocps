package findus_pageobjects.wizards.opret_relation_til_firma_med_cvr;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class OpretRelationTilFirmaMedCvrWizardPage extends BaseWizardPage {

    public OpretRelationTilFirmaMedCvrWizardPage() {
        super("Opret relation til firma med CVR");
    }

// region "PageElements"
    final Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));

// endregion

}

