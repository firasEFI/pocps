package findus_pageobjects.wizards.afdragsordning;

import static utils.IciSelExpectedConditions.elementIsDisabled;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import findus_pageobjects.Utils;
import findus_pageobjects.afdragsordning.AfdragsordningPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerAfdragsordningPage;
import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Checkbox;
import icisel.pageobjects.elements.DatePicker;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;

public class AfdragsordningWizardPage extends BaseWizardPage {

    public AfdragsordningWizardPage() {
        super("Afdragsordning");
    }

    //***Elements
    //Sagsbehandlingsskridt
    final Input txtAarsagOprettelseAfdOrdn = new Input(Frames.uiMap, By.id("boGroup_creationReasonNote"));
    final Dropdown cboVaelgAfdOrdnType = new Dropdown(Frames.uiMap, By.id("boGroup_payplanType"));
    final Input txtNoteValgTypeAfdOrdn = new Input(Frames.uiMap, By.id("boGroup_payplanTypeNote"));

    //Inkluderede påkrav
    final Input txtInklPaakrav = new Input(Frames.uiMap, By.id("boGroup_selectedDemandLetter"));

    //Inkluderede fordringer
    final Button btnVaelgAlt = new Button(Frames.uiMap, By.id("select_all_button"));
    final Button btnFravaelgAlt = new Button(Frames.uiMap, By.id("deselect_all_button"));
    final Button btnShowRelatedClaims = new Button(Frames.uiMap, By.id("show_related_claims"));
    final Dropdown cboBegrundFjAfdOrdn = new Dropdown(Frames.uiMap, By.id("boGroup_removeClaimReason"));
    final Input txtAndenAarsag = new Input(Frames.uiMap, By.id("boGroup_removeClaimReasonOther"));
    final Input txtNoteForAtTilfoejeForordning = new Input(Frames.uiMap, By.id("boGroup_addClaimReason"));

    //Detaljer for afdragsordning
    final Dropdown cboAfdOrdnFrekv = new Dropdown(Frames.uiMap, By.id("boGroup_frequency"));
    final Input txtAfdragsBeloeb = new Input(Frames.uiMap, By.id("boGroup_installmentAmount"));
    final Input txtNoteValgAfdrBeloeb = new Input(Frames.uiMap, By.id("boGroup_installmentAmountNote"));
    final Input txtDatoFoersteAfd = new Input(Frames.uiMap, By.id("boGroup_dateFirstInstallment"));
    final Input txtGyldighedsperiode = new Input(Frames.uiMap, By.id("boGroup_validityPeriodInCalD"));
    final Checkbox chkOprRykkerMnglBetMan = new Checkbox(Frames.uiMap, By.id("boGroup_createToDo"));

    //Adressetype
    final Dropdown cboAdresseType = new Dropdown(Frames.uiMap, By.id("boGroup_addressType"));
    final Input txtNoteForAdresse = new Input(Frames.uiMap, By.id("boGroup_addressTypeNote"));
    final Input txtSkyldNavn = new Input(Frames.uiMap, By.id("boGroup_debtorName"));
    final Input txtCONavn = new Input(Frames.uiMap, By.id("boGroup_coName"));
    final Input txtAdresse1 = new Input(Frames.uiMap, By.id("boGroup_address1"));
    final Input txtAdresse2 = new Input(Frames.uiMap, By.id("boGroup_address2"));
    final Input txtAdresse3 = new Input(Frames.uiMap, By.id("boGroup_address3"));
    final Input txtPostNr = new Input(Frames.uiMap, By.id("boGroup_postal"));

    //Afdrag i afdragsordning

    //Information om afdragsfri periode
    final DatePicker dateFra = new DatePicker(Frames.uiMap, By.id("fromDate_0"));
    final DatePicker dateTil = new DatePicker(Frames.uiMap, By.id("toDate_0"));
    final Input txtForAfdrFriPeriode = new Input(Frames.uiMap, By.id("boGroup_periodNote"));

    final Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));


    //***Methods
    public AfdragsordningPage activateAnnuler (){
        btnAnnuller.click();
        return new AfdragsordningPage();
    }

    public AfdragsordningWizardPage fillForm(AfdragsordningWizardParm parm){

        //Sagsbehandlingsskridt
        Utils.setTextBoxTextIfNotNull(txtAarsagOprettelseAfdOrdn, parm.getAarsagOprettelseAfdOrdn());
        Utils.setDropdownVisibleTextIfNotNull(cboVaelgAfdOrdnType, parm.getVaelgAfdOrdnType());
        Utils.setTextBoxTextIfNotNull(txtNoteValgTypeAfdOrdn, parm.getNoteValgTypeAfdOrdn());

        //Inkluderede påkrav
        Utils.setTextBoxTextIfNotNull(txtInklPaakrav, parm.getInklPaakrav());

        //Inkluderede fordringer
        Utils.setDropdownVisibleTextIfNotNull(cboVaelgAfdOrdnType, parm.getBegrundFjAfdOrdn());
        // We have to wait for a afdragsbeloeb to auto-fill here, which
        // makes the field become disable
        Engine.getDriver().pause().until(elementIsDisabled(txtAfdragsBeloeb));

        Utils.setTextBoxTextIfNotNull(txtAndenAarsag, parm.getAndenAarsag());
        Utils.setTextBoxTextIfNotNull(txtNoteForAtTilfoejeForordning, parm.getNoteForAtTilfoejeForordning());

        //Detaljer for afdragsordning
        Utils.setDropdownVisibleTextIfNotNull(cboVaelgAfdOrdnType, parm.getAfdOrdnFrekv());
        Utils.setTextBoxTextIfNotNull(txtAarsagOprettelseAfdOrdn, parm.getAarsagOprettelseAfdOrdn());
        Utils.setTextBoxTextIfNotNull(txtNoteValgAfdrBeloeb, parm.getNoteValgAfdrBeloeb());
        Utils.setTextBoxTextIfNotNull(txtDatoFoersteAfd, parm.getDatoFoersteAfd());
        Utils.setTextBoxTextIfNotNull(txtGyldighedsperiode, parm.getGyldighedsperiode());
        Utils.setCheckboxCheckedIfNotNull(chkOprRykkerMnglBetMan, parm.isOprRykkerMnglBetMan());

        //Adressetype
        Utils.setDropdownVisibleTextIfNotNull(cboAdresseType, parm.getAdresseType());
        Utils.setTextBoxTextIfNotNull(txtNoteForAdresse, parm.getNoteForAdresse());
        Utils.setTextBoxTextIfNotNull(txtSkyldNavn, parm.getSkyldNavn());
        Utils.setTextBoxTextIfNotNull(txtCONavn, parm.getCoNavn());
        Utils.setTextBoxTextIfNotNull(txtAdresse1, parm.getAdresse1());
        Utils.setTextBoxTextIfNotNull(txtAdresse2, parm.getAdresse2());
        Utils.setTextBoxTextIfNotNull(txtAdresse3, parm.getAdresse3());
        Utils.setTextBoxTextIfNotNull(txtPostNr, parm.getPostNr());

        //Information om afdragsfri periode
        Utils.setTextBoxTextIfNotNull(txtAarsagOprettelseAfdOrdn, parm.getDateFra());
        Utils.setTextBoxTextIfNotNull(txtAarsagOprettelseAfdOrdn, parm.getDateTil());
        Utils.setTextBoxTextIfNotNull(txtAarsagOprettelseAfdOrdn, parm.getForAfdrFriPeriode());

        return this;
    }

    public AfdragsordningWizardPage activateVaelgAlt(){
        Engine.getDriver().pause().until(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver input) {
                btnVaelgAlt.click();
                return new Checkbox(Frames.uiMap, By.id("selected_0")).isSelected();
            }

        });

        return this;
    }

    public AfdragsordningWizardPage activateFravaelgAlt(){
        btnFravaelgAlt.click();
        return this;
    }

    public AfdragsordningWizardPage activateShowRelatedClaims(){
        btnShowRelatedClaims.click();
        return this;
    }

    public Sagsbehandlingsskridt_PrimaerAfdragsordningPage activateGem(){
        btnGem.click();
        return new Sagsbehandlingsskridt_PrimaerAfdragsordningPage();
    }
}

