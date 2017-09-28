package findus_pageobjects.wizards.kundekontakt;

import findus_pageobjects.Utils;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.brevoplysninger.Brevoplysninger_PrimaerSubPage;
import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Checkbox;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class KundekontaktWizardPage<TReturnPage> extends BaseWizardPage<TReturnPage> {

    //Primær
    //Modtager information
    final Dropdown cboModtager = new Dropdown(Frames.uiMap, By.id("boGroup_recipientPersonId"));
    final Checkbox chkDigitalPost = new Checkbox(Frames.uiMap, By.id("boGroup_digital"));
    final Dropdown cboModtagerAdresse = new Dropdown(Frames.uiMap, By.id("boGroup_recipientAddressId"));
    final Input txtAtt = new Input(Frames.uiMap, By.id("boGroup_att"));

    //Vedhæftninger
    //Valgmuligheder
    final Checkbox chkTilfoejGaeldsoverblik = new Checkbox(Frames.uiMap, By.id("boGroup_addDebtOverview"));
    final Checkbox chkTilfoejOcrLinje = new Checkbox(Frames.uiMap, By.id("boGroup_addOCRLine"));
    final Input txtOCRLinje = new Input(Frames.uiMap, By.id("boGroup_ocrLine"));
    final Button btnSoegOCRlinje = new Button(Frames.uiMap, By.id("boGroup_ocrLine_search"));

    //Information for forbladet
    final Input txtForbladOverskrift = new Input(Frames.uiMap, By.id("boGroup_coverLetterHeadline"));
    final Input txtTekstTilForblad = new Input(Frames.uiMap, By.id("boGroup_coverLetterFreeText"));

    //Fritekst
    final Input txtOevrigeOplysninger = new Input(Frames.uiMap, By.id("boGroup_Fritekst1"));
    final Input txtOverskrift = new Input(Frames.uiMap, By.xpath(Utils.createXPathForLabelText("Input", "Overskrift")));
    final Input txtAndenAktoerJournalNr = new Input(Frames.uiMap, By.xpath(Utils.createXPathForLabelText("Input", "Anden aktør journal nr.")));
    final Input txtFritekst = new Input(Frames.uiMap, By.xpath(Utils.createXPathForLabelText("Input", "Fritekst")));

    final Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));

    public boolean canSetDigitalPost() {
        return this.chkDigitalPost.isEnabled();
    }

    public boolean canSetOevrigeOplysninger() {
        return this.txtOevrigeOplysninger.isEnabled();
    }

    public boolean canSetFritekst() {
        return this.txtFritekst.isEnabled();
    }

    public KundekontaktWizardPage() {
        super("Kundekontakt");
    }

    public KundekontaktWizardPage<TReturnPage> fillForm(Kundekontakt kundekontakt) {
        Utils.setDropdownVisibleTextIfNotNull(this.cboModtager, kundekontakt.getModtager());
        Utils.setCheckboxCheckedIfNotNull(this.chkDigitalPost, kundekontakt.getDigitalPost());
        Utils.setDropdownVisibleTextIfNotNull(this.cboModtagerAdresse, kundekontakt.getModtagerAdresse());
        Utils.setTextBoxTextIfNotNull(this.txtAtt, kundekontakt.getAtt());

        Utils.setCheckboxCheckedIfNotNull(this.chkTilfoejGaeldsoverblik, kundekontakt.getTilfoejGaeldsoverblik());
        Utils.setCheckboxCheckedIfNotNull(this.chkTilfoejOcrLinje, kundekontakt.getTilfoejOcrLinje());
        Utils.setTextBoxTextIfNotNull(this.txtOCRLinje, kundekontakt.getOcrLinje());

        Utils.setTextBoxTextIfNotNull(this.txtForbladOverskrift, kundekontakt.getForbladOverskrift());
        Utils.setTextBoxTextIfNotNull(this.txtTekstTilForblad, kundekontakt.getTekstTilForblad());

        Utils.setTextBoxTextIfNotNull(this.txtOverskrift, kundekontakt.getFritekstOverskrift());
        Utils.setTextBoxTextIfNotNull(this.txtAndenAktoerJournalNr, kundekontakt.getFritekstAndenAktoerJournalNr());
        Utils.setTextBoxTextIfNotNull(this.txtFritekst, kundekontakt.getFritekst());
        Utils.setTextBoxTextIfNotNull(this.txtOevrigeOplysninger, kundekontakt.getFritekstOevrigeOplysninger());

        return this;
    }

    public Kundekontakt getFormData() {
        Kundekontakt kundekontakt = new Kundekontakt();

        kundekontakt.setModtager(cboModtager.getText());
        kundekontakt.setDigitalPost(chkDigitalPost.isSelected());
        kundekontakt.setModtagerAdresse(cboModtagerAdresse.getText());
        kundekontakt.setAtt(txtAtt.getText());

        if(this.chkTilfoejGaeldsoverblik.isDisplayed())
            kundekontakt.setTilfoejGaeldsoverblik(this.chkTilfoejGaeldsoverblik.isSelected());

        if(this.chkTilfoejOcrLinje.isDisplayed())
            kundekontakt.setTilfoejOcrLinje(this.chkTilfoejOcrLinje.isSelected());

        if(this.txtOCRLinje.isDisplayed())
            kundekontakt.setOcrLinje(this.txtOCRLinje.getText());

        kundekontakt.setForbladOverskrift(this.txtForbladOverskrift.getText());
        kundekontakt.setTekstTilForblad(this.txtTekstTilForblad.getText());

        if(this.txtOverskrift.isDisplayed())
            kundekontakt.setFritekstOverskrift(this.txtOverskrift.getText());

        if(this.txtAndenAktoerJournalNr.isDisplayed())
            kundekontakt.setFritekstAndenAktoerJournalNr(this.txtAndenAktoerJournalNr.getText());

        if(this.txtFritekst.isDisplayed())
            kundekontakt.setFritekst(this.txtFritekst.getText());

        if(this.txtOevrigeOplysninger.isDisplayed())
            kundekontakt.setFritekstOevrigeOplysninger(this.txtOevrigeOplysninger.getText());

        return kundekontakt;
    }

    public Brevoplysninger_PrimaerSubPage activateGem() {
        this.btnGem.click();

        return new Brevoplysninger_PrimaerSubPage();
    }

    public BasePsrmPage activateAnnuller() {
        this.btnAnnuller.click();

        return new BasePsrmPage();
    }

    public KundekontaktWizardPage_PopupWindow activateSoegOCRLinje(){
        PopupWindowResolver<KundekontaktWizardPage_PopupWindow> popupResolver = new PopupWindowResolver<>(Engine.getDriver());

        btnSoegOCRlinje.click();

        KundekontaktWizardPage_PopupWindow popup = popupResolver.waitForPopup(new PopupWindowResolver.GetPopupWindow<KundekontaktWizardPage_PopupWindow>() {
            @Override
            public KundekontaktWizardPage_PopupWindow GetWindow() {
                return new KundekontaktWizardPage_PopupWindow(KundekontaktWizardPage.this);
            }
        },10000);

        return popup;
    }
}
