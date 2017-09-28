package findus_pageobjects.wizards.omposter_indbetaling_daekningsloes;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class OmposterIndbetalingDaekningsloesWizardPage extends BaseWizardPage {

    public OmposterIndbetalingDaekningsloesWizardPage() {
        super("Omposter indbetaling (dækningsløs)");
    }

// region "PageElements"
    final Button btnGem = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='SAVE_BTN_LBL']"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));
    final Input txtAction = new Input(Frames.uiMap, By.id("action"));
    final Input txtSagsbehandlingsskridtId = new Input(Frames.uiMap, By.id("boGroup_processFlowId"));
    final Input txtSagsbehandlingsskridtType = new Input(Frames.uiMap, By.id("boGroup_processFlowType"));
    final Input txtForretningsobjekt = new Input(Frames.uiMap, By.id("boGroup_bo"));
    final Input txtStatus = new Input(Frames.uiMap, By.id("boGroup_boStatus"));
    final Input txtOprettelsestidspunkt = new Input(Frames.uiMap, By.id("boGroup_creationDateTime"));
    final Input txtKonto = new Input(Frames.uiMap, By.id("boGroup_accountId"));
    final Input txtBetalingshaendelsesId = new Input(Frames.uiMap, By.id("boGroup_paymentEventId"));
    final Input txtBetalingsbeloeb = new Input(Frames.uiMap, By.id("boGroup_payAmount"));
    final Input txtOmposteringsbeloeb = new Input(Frames.uiMap, By.id("boGroup_repPayAmount"));
    final Input txtDaekningsloestBeloeb = new Input(Frames.uiMap, By.id("boGroup_nonSufAmount"));
    final Dropdown cboAarsag = new Dropdown(Frames.uiMap, By.id("boGroup_repostReason"));

// endregion

}

