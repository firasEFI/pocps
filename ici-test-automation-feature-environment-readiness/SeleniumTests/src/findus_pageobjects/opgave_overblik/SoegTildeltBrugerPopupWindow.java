package findus_pageobjects.opgave_overblik;

import findus_datamodels.PsrmUserModel;
import findus_pageobjects.PopupWindow;
import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.Utils;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class SoegTildeltBrugerPopupWindow extends PopupWindow<OpgaveOverblik_PrimaerSubPage> {

    Button btnSoeg = new Button(Frames.defaultContent, By.id("anTLZ1Refresh"));
    Input txtBrugerID = new Input(Frames.defaultContent, By.id("filter1.F1"));

    protected SoegTildeltBrugerPopupWindow(OpgaveOverblik_PrimaerSubPage parentPage) {
        super(By.id("filter1.F1"), parentPage);
    }

    public SoegTildeltBrugerPopupWindow fillFormSoegBruger(PsrmUserModel user) {
        Utils.setTextBoxTextIfNotNull(txtBrugerID,user.getSagsbehanlderId());
        return this;
    }

    public OpgaveOverblik_PrimaerSubPage activateBtnSoeg(){
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());
        btnSoeg.click();
        resolver.waitForPopupToClose(10000);
        return parentPage;
    }
}