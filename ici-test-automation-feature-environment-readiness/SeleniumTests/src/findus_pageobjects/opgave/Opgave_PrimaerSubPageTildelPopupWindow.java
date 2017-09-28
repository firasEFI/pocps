package findus_pageobjects.opgave;

import findus_datamodels.PsrmUserModel;
import findus_pageobjects.PopupWindow;
import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.Utils;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frame;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class Opgave_PrimaerSubPageTildelPopupWindow extends PopupWindow<Opgave_PrimaerSubPage> {

    private Button btnSoegBruger = new Button(Frames.defaultContent, By.id("IM_FWD_ASSIGNED_TO"));
    private Button btnOk = new Button(Frames.defaultContent, By.id("OK_BTTN"));
    private Button btnAnuller = new Button(Frames.defaultContent, By.id("CANCEL_BTTN"));
    private Dropdown cboSendTil = new Dropdown(Frames.defaultContent, By.id("TD_SEND_TO_FLG"));
    private Input txtBruger = new Input(Frames.defaultContent, By.id("FWD_ASSIGNED_TO"));
    private Input txtRolle = new Input(Frames.defaultContent, By.id("FWD_ROLE_ID"));
    private Input txtDetaljer = new Input(Frames.defaultContent, By.id("LOG_DETAILS"));
    private final String bruger = "Bruger";
    private final String rolle = "Rolle";

    protected Opgave_PrimaerSubPageTildelPopupWindow(Opgave_PrimaerSubPage parentPage) {
        super(By.id("LOG_DETAILS"), parentPage);
    }

    public Opgave_PrimaerSubPageTildelPopupWindow_SoegUserPopupWindow activateSoegPopupWindow(PsrmUserModel userModel){
        PopupWindowResolver<Opgave_PrimaerSubPageTildelPopupWindow_SoegUserPopupWindow> popupResolver = new PopupWindowResolver<>(Engine.getDriver());

        btnSoegBruger.click();

        Opgave_PrimaerSubPageTildelPopupWindow_SoegUserPopupWindow popup = popupResolver.waitForPopup(new PopupWindowResolver.GetPopupWindow<Opgave_PrimaerSubPageTildelPopupWindow_SoegUserPopupWindow>() {
            @Override
            public Opgave_PrimaerSubPageTildelPopupWindow_SoegUserPopupWindow GetWindow() {
                return new Opgave_PrimaerSubPageTildelPopupWindow_SoegUserPopupWindow(Opgave_PrimaerSubPageTildelPopupWindow.this);
            }
        },10000);

        return popup;
    }

    public Opgave_PrimaerSubPageTildelPopupWindow fillFormFremrykOpgaveOK(OpgaveFremrykParm opgaveFremrykParm) {

        Utils.setDropdownVisibleTextIfNotNull(this.cboSendTil, opgaveFremrykParm.getSendTil());
        Utils.setTextBoxTextIfNotNull(txtBruger,opgaveFremrykParm.getBruger());
        Utils.setTextBoxTextIfNotNull(txtRolle, opgaveFremrykParm.getRolle());
        Utils.setTextBoxTextIfNotNull(txtDetaljer, opgaveFremrykParm.getDetaljer());
        return this;
    }

    public Opgave_PrimaerSubPage activateOK(){
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());
        btnOk.click();
        resolver.waitForPopupToClose(10000);
        return parentPage;
    }

    public Opgave_PrimaerSubPage activateAnnuller() {
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());
        this.btnAnuller.click();
        resolver.waitForPopupToClose(10000);
        return this.parentPage;
    }
}
