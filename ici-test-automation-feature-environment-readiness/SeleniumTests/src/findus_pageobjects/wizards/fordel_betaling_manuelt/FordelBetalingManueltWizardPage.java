package findus_pageobjects.wizards.fordel_betaling_manuelt;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class FordelBetalingManueltWizardPage extends BaseWizardPage {

    public FordelBetalingManueltWizardPage() {
        super("Fordel betaling manuelt");
    }

// region "PageElements"
    final Button btnFordelBetaling = new Button(Frames.uiMap, By.id("ok"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));
    final Input txtChkvalerr = new Input(Frames.uiMap, By.id("chkValErr"));
    final Input txtKonto = new Input(Frames.uiMap, By.id("accountIdInf"));
    final Input txtBetalingsId = new Input(Frames.uiMap, By.id("accountIdInf"));
    final Input txtBetalingsbeloeb = new Input(Frames.uiMap, By.id("payAmntInf"));
    final Input txtBetalingsId_2 = new Input(Frames.uiMap, By.id("paymentEventIdInf"));
    final Input txtSendToManualDistributionAmount = new Input(Frames.uiMap, By.id("nonDistrAmountInf"));
    final Input txtSap38Amount = new Input(Frames.uiMap, By.id("sap38AmountInf"));
    final Input txtUdbetalingsbeloeb = new Input(Frames.uiMap, By.id("payoutAmountInf"));
    final Input txtIbanNummer = new Input(Frames.uiMap, By.id("ibanNumInf"));
    final Input txtSwiftKode = new Input(Frames.uiMap, By.id("swiftCodeInf"));
    final Input txtAlternativDanskKonto = new Input(Frames.uiMap, By.id("altDanAccInf"));
    final Dropdown cboTilfoejAndenKonto = new Dropdown(Frames.uiMap, By.id("otherAccount"));

// endregion

}

