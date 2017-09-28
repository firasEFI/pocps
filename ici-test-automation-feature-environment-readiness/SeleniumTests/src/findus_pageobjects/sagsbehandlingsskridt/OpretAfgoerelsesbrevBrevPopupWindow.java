package findus_pageobjects.sagsbehandlingsskridt;

import findus_pageobjects.PopupWindow;
import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.Utils;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Checkbox;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class OpretAfgoerelsesbrevBrevPopupWindow extends PopupWindow<Sagsbehandlingsskridt_PrimaerAfdragsordningPage> {

    final Input txtDatoFoersteAfd = new Input(Frames.defaultContent, By.id("dateFirstInstallment"));
    final Checkbox chkAktvAfdOrdnMedDetSamme = new Checkbox(Frames.defaultContent, By.id("selectImmediatelyPP"));
    final Input txtAktiveringsNote = new Input(Frames.defaultContent, By.id("activationNote"));
    final Button btnGem = new Button(Frames.defaultContent, By.id("SAVE_BTN_MP"));
    final Button btnAnnuller = new Button(Frames.defaultContent, By.id("CANCEL_BTN_MP"));

    public OpretAfgoerelsesbrevBrevPopupWindow(Sagsbehandlingsskridt_PrimaerAfdragsordningPage parentPage) {
        super(By.id("includeFee"), parentPage);
    }

    public OpretAfgoerelsesbrevBrevPopupWindow  fillForm(OpretAfgoerelsesbrevBrevParm parm) {
        Utils.setTextBoxTextIfNotNull(txtDatoFoersteAfd, parm.getDatoFoersteAfd());
        Utils.setCheckboxCheckedIfNotNull(chkAktvAfdOrdnMedDetSamme, parm.isAktvAfdOrdnMedDetSamme());
        Utils.setTextBoxTextIfNotNull(txtAktiveringsNote, parm.getAktiveringsNote());

        return this;
    }

    public Sagsbehandlingsskridt_LogTabPage activateGem() {
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());

        this.btnGem.click();

        resolver.waitForPopupToClose(10000);

        return new Sagsbehandlingsskridt_LogTabPage(this.parentPage.getParentPage());
    }

    public Sagsbehandlingsskridt_PrimaerAfdragsordningPage activateAnnuller() {
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());

        this.btnAnnuller.click();

        resolver.waitForPopupToClose(10000);

        return new Sagsbehandlingsskridt_PrimaerAfdragsordningPage();
    }
}
