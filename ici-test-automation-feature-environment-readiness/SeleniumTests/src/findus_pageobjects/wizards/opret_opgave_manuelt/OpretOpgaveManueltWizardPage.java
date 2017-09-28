package findus_pageobjects.wizards.opret_opgave_manuelt;

import findus_datamodels.SkyldnerModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.Utils;
import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class OpretOpgaveManueltWizardPage extends BaseWizardPage {

    public OpretOpgaveManueltWizardPage() {
        super("Opret opgave");
    }

    //***Elements
    //Opret opgave
    final Dropdown cboVaelgOpgavekategori = new Dropdown(Frames.uiMap, By.id("opgaveCategory"));
    final Dropdown cboVaelgOpgavetype = new Dropdown(Frames.uiMap, By.id("opgaveType"));
    final Dropdown cboVaelgPrioritet = new Dropdown(Frames.uiMap, By.id("toDoPriority"));
    final Input txtBeskrivelse = new Input(Frames.uiMap, By.id("description"));
    final Input txtTidligsteSlutdato = new Input(Frames.uiMap, By.id("toDoFirstDeadline"));
    final Input txtFrist = new Input(Frames.uiMap, By.id("toDoLastDeadline"));

    //Parter
    final Button btnSoegPart = new Button(Frames.uiMap, By.id("person_search"));

    //Tildelt til
    final Dropdown cboVaelgSendTil = new Dropdown(Frames.uiMap, By.id("assignedTo"));
    final Input txtTildeltSagsbehandler = new Input(Frames.uiMap, By.id("assignedToWorker"));
    final Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));


    //***Methods
    public BasePsrmPage activateAnnuler (){
        btnAnnuller.click();
        return new BasePsrmPage();
    }

    public OpretOpgaveManueltWizardPage fillForm(OpretOpgaveManueltWizardPageParm parm, SkyldnerModel skyldner) {

        //Opret opgave
        Utils.setDropdownVisibleTextIfNotNull(cboVaelgOpgavekategori, parm.getVaelgOpgavekategori());
        Utils.setDropdownVisibleTextIfNotNull(cboVaelgOpgavetype, parm.getVaelgOpgavetype());

        //Parter
        activateOpretOpgaveManueltWizardPagePopupWindow().fillFormSoegBugerActivateSoeg(skyldner);

        //Tildelt til
        Utils.setDropdownVisibleTextIfNotNull(cboVaelgSendTil, parm.getVaelgSendTil());
        Utils.setTextBoxTextIfNotNull(txtTildeltSagsbehandler, parm.getTildeltSagsbehandler());

        btnGem.click(); //Push to "Tab" last input tildelt sagsbehandler

        return this;
    }

    public BasePsrmPage activateGem(){
        btnGem.click();
        return new BasePsrmPage();
    }

    public OpretOpgaveManueltWizardPage_SoegPartPopupWindow activateOpretOpgaveManueltWizardPagePopupWindow() {
        PopupWindowResolver<OpretOpgaveManueltWizardPage_SoegPartPopupWindow> popupResolver = new PopupWindowResolver<>(Engine.getDriver());

        btnSoegPart.click();

        OpretOpgaveManueltWizardPage_SoegPartPopupWindow popup = popupResolver.waitForPopup(new PopupWindowResolver.GetPopupWindow<OpretOpgaveManueltWizardPage_SoegPartPopupWindow>() {
            @Override
            public OpretOpgaveManueltWizardPage_SoegPartPopupWindow GetWindow() {
                return new OpretOpgaveManueltWizardPage_SoegPartPopupWindow(OpretOpgaveManueltWizardPage.this);
            }
        },10000);

        return popup;
    }


}
