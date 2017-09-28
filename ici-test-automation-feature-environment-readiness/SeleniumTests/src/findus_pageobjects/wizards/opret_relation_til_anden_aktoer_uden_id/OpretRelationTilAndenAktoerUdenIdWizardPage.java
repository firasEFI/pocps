package findus_pageobjects.wizards.opret_relation_til_anden_aktoer_uden_id;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class OpretRelationTilAndenAktoerUdenIdWizardPage extends BaseWizardPage {

    public OpretRelationTilAndenAktoerUdenIdWizardPage() {
        super("Opret relation til anden aktør uden ID");
    }

// region "PageElements"
    final Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));

// endregion

}

