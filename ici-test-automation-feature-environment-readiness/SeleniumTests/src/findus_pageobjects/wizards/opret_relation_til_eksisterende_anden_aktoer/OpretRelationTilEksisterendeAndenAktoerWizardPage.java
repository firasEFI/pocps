package findus_pageobjects.wizards.opret_relation_til_eksisterende_anden_aktoer;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class OpretRelationTilEksisterendeAndenAktoerWizardPage extends BaseWizardPage {

    public OpretRelationTilEksisterendeAndenAktoerWizardPage() {
        super("Opret relation til eksisterende anden aktør");
    }

// region "PageElements"
    final Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));

// endregion

}

