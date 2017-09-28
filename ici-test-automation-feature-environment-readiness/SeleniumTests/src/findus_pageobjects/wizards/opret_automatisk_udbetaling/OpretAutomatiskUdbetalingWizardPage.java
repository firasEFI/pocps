package findus_pageobjects.wizards.opret_automatisk_udbetaling;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class OpretAutomatiskUdbetalingWizardPage extends BaseWizardPage {

    public OpretAutomatiskUdbetalingWizardPage() {
        super("Opret automatisk udbetaling");
    }

// region "PageElements"
    final Button btnOpretUdbetaling = new Button(Frames.uiMap, By.id("ok"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));
    final Input txtChkvalerr = new Input(Frames.uiMap, By.id("chkValErr"));

// endregion

}

