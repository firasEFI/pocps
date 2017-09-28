package findus_pageobjects.wizards.tilbagesend_fordring;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class TilbagesendFordringWizardPage extends BaseWizardPage {

    public TilbagesendFordringWizardPage() {
        super("Tilbagesend fordring");
    }

// region "PageElements"
    final Button btnGem = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='SAVE_BTN_LBL']"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));
    final Input txtAction = new Input(Frames.uiMap, By.id("action"));
    final Input txtSagsbehandlingsskridtId = new Input(Frames.uiMap, By.id("boGroup_processFlowId"));
    final Input txtSagsbehandlingsskridtType = new Input(Frames.uiMap, By.id("boGroup_processFlowType"));
    final Input txtStatus = new Input(Frames.uiMap, By.id("boGroup_boStatus"));
    final Input txtOprettelsestidspunkt = new Input(Frames.uiMap, By.id("boGroup_creationDateTime"));
    final Input txtBruger = new Input(Frames.uiMap, By.id("boGroup_user"));
    final Input txtKonto = new Input(Frames.uiMap, By.id("boGroup_accountId"));
    final Input txtVirkningsdato = new Input(Frames.uiMap, By.id("boGroup_effdate"));
    final Input txtFordring = new Input(Frames.uiMap, By.id("boGroup_obligationId"));
    final Dropdown cboBegrundelseForTilbagesendelse = new Dropdown(Frames.uiMap, By.id("boGroup_sendBackReason"));
    final Dropdown cboTilbagesendRelateredeFordringer = new Dropdown(Frames.uiMap, By.id("boGroup_sendBackRelated"));

// endregion

}

