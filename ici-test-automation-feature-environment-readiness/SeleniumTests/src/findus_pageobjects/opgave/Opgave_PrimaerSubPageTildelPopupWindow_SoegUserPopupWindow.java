package findus_pageobjects.opgave;

import findus_datamodels.PsrmUserModel;
import findus_pageobjects.PopupWindow;
import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.Utils;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class Opgave_PrimaerSubPageTildelPopupWindow_SoegUserPopupWindow extends PopupWindow<Opgave_PrimaerSubPageTildelPopupWindow> {
    protected Opgave_PrimaerSubPageTildelPopupWindow_SoegUserPopupWindow(Opgave_PrimaerSubPageTildelPopupWindow parentPage) {
        super(By.id("FIRST_NAME"), parentPage);
    }

    Button btnSoegBruger = new Button(Frames.defaultContent, By.id("IM_MAIN_SRCH"));
    Button btnSoegNavn = new Button(Frames.defaultContent, By.id("IM_ALT_SRCH"));
    Input txtBruger = new Input(Frames.defaultContent, By.id("USER_ID"));
    Input txtEfternavn = new Input(Frames.defaultContent, By.id("LAST_NAME"));
    Input txtFornavn = new Input(Frames.defaultContent, By.id("FIRST_NAME"));

    public Opgave_PrimaerSubPageTildelPopupWindow_SoegUserPopupWindow fillForm(PsrmUserModel userModel){
        Utils.setTextBoxTextIfNotNull(txtBruger, userModel.getSagsbehanlderId());
        Utils.setTextBoxTextIfNotNull(txtEfternavn, userModel.getEfternavn());
        Utils.setTextBoxTextIfNotNull(txtFornavn, userModel.getFornavn());
        return this;
    }

    //Lukker selv hvis der kun er én bruger med det id/navn
    public Opgave_PrimaerSubPageTildelPopupWindow activateSoegBruger(){
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());
        btnSoegBruger.click();
        resolver.waitForPopupToClose(10000);
        return this.parentPage;
    }

    //Lukker selv hvis der kun er én bruger med det id/navn
    public Opgave_PrimaerSubPageTildelPopupWindow activateSoegNavn(){
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());
        btnSoegNavn.click();
        resolver.waitForPopupToClose(10000);
        return this.parentPage;
    }
}
