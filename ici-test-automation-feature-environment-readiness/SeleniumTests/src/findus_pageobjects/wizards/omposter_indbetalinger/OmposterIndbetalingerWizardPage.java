package findus_pageobjects.wizards.omposter_indbetalinger;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class OmposterIndbetalingerWizardPage extends BaseWizardPage {

    public OmposterIndbetalingerWizardPage() {
        super("Omposter indbetalinger");
    }

// region "PageElements"
    final Button btnOmpostérIndbetaling = new Button(Frames.uiMap, By.id("ok"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));
    final Input txtChkvalerr = new Input(Frames.uiMap, By.id("chkValErr"));
    final Input txtKonto = new Input(Frames.uiMap, By.id("accountIdInf"));
    final Input txtBetalingshaendelsesId = new Input(Frames.uiMap, By.id("accountIdInf"));
    final Input txtBetalingsbeloeb = new Input(Frames.uiMap, By.id("payAmntInf"));
    final Input txtSendToManualDistributionAmount = new Input(Frames.uiMap, By.id("nonDistrAmountInf"));
    final Input txtSap38Amount = new Input(Frames.uiMap, By.id("sap38AmountInf"));
    final Input txtUdbetalingsbeloeb = new Input(Frames.uiMap, By.id("payoutAmountInf"));
    final Input txtIbanNummer = new Input(Frames.uiMap, By.id("ibanNumInf"));
    final Input txtSwiftKode = new Input(Frames.uiMap, By.id("swiftCodeInf"));
    final Input txtAlternativDanskKonto = new Input(Frames.uiMap, By.id("altDanAccInf"));
    final Dropdown cboAarsag = new Dropdown(Frames.uiMap, By.id("repostReason"));
    final Dropdown cboTilfoejAndenKonto = new Dropdown(Frames.uiMap, By.id("otherAccount"));

// endregion

}

