package findus_pageobjects.opgavestyring;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class OpgavestyringWizardPage extends BaseWizardPage {

    public OpgavestyringWizardPage() {
        super("Opgavestyring");
    }

// region "PageElements"
    final Button btnGem = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='SAVE_BTN_LBL']"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));
    final Dropdown cboBegrundelse = new Dropdown(Frames.uiMap, By.id("completeReason"));
    final PageElement begrundelseTextArea = new PageElement(Frames.uiMap, By.id("completeDescription"));

    public void doWindowActionWithSave(String begrundelseskode, String beskrivelse) {
        cboBegrundelse.pick(begrundelseskode);
        begrundelseTextArea.sendKeys(beskrivelse);
        btnGem.click();
    }

// endregion

}

