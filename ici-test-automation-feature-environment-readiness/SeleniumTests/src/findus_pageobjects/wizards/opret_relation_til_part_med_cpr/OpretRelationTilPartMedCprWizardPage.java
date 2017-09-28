package findus_pageobjects.wizards.opret_relation_til_part_med_cpr;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class OpretRelationTilPartMedCprWizardPage extends BaseWizardPage {

    public OpretRelationTilPartMedCprWizardPage() {
        super("Opret relation til part med CPR");
    }

// region "PageElements"
    final Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));

// endregion

}

