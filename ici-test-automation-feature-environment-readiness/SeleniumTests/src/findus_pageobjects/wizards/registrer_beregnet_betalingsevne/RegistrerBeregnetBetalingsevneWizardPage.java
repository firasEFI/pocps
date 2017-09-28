package findus_pageobjects.wizards.registrer_beregnet_betalingsevne;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class RegistrerBeregnetBetalingsevneWizardPage extends BaseWizardPage {

    public RegistrerBeregnetBetalingsevneWizardPage() {
        super("Registrer beregnet betalingsevne");
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
    final Input txtStartDato = new Input(Frames.uiMap, By.id("boGroup_startDate"));
    final Input txtSlutDato = new Input(Frames.uiMap, By.id("boGroup_endDate"));
    final Input txtKonto = new Input(Frames.uiMap, By.id("boGroup_accountId"));
    final Input txtMaanedligtBetalingsevneBaseretPaaBudget = new Input(Frames.uiMap, By.id("boGroup_calcPayAbility_mounthPayAbility"));
    final Input txtBogroupcalcpayabilityabilitycomment = new Input(Frames.uiMap, By.id("boGroup_calcPayAbility_abilityComment"));
    final Input txtMaanedligBetalingsevneBaseretPaaTabeltraek = new Input(Frames.uiMap, By.id("boGroup_calcPayAbility_mounthPayAbLookup"));
    final Input txtBogroupcalcpayabilityabilcommentlookup = new Input(Frames.uiMap, By.id("boGroup_calcPayAbility_abilCommentLookup"));
    final Input txtBeregnetBetalingsevneDato = new Input(Frames.uiMap, By.id("boGroup_calcPayAbility_payAbilityDate"));
    final Input txtModtagelsesdatoForBudget = new Input(Frames.uiMap, By.id("boGroup_calcPayAbility_budgetReceiveDate"));
    final Input txtBudgettetsGyldighedsperiodeFra = new Input(Frames.uiMap, By.id("boGroup_validityfrom"));
    final Input txtBudgettetsGyldighedsperiodeTil = new Input(Frames.uiMap, By.id("boGroup_validityTo"));
    final Dropdown cboAnvendIInddrivelsesskridt = new Dropdown(Frames.uiMap, By.id("boGroup_calcPayAbility_useInCallStep"));

// endregion

}

