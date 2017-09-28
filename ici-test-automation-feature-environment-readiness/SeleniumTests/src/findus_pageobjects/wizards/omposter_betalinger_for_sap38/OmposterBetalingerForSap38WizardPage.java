package findus_pageobjects.wizards.omposter_betalinger_for_sap38;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class OmposterBetalingerForSap38WizardPage extends BaseWizardPage {

    public OmposterBetalingerForSap38WizardPage() {
        super("Omposter betalinger for SAP38");
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
    final Dropdown cboAarsag = new Dropdown(Frames.uiMap, By.id("boGroup_repostReasonC"));
    final Dropdown cboAarsagSec = new Dropdown(Frames.uiMap, By.id("boGroup_repostReasonCSec"));

// endregion

}

