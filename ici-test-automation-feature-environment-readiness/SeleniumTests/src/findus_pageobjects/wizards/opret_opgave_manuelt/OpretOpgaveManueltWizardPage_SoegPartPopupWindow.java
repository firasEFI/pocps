package findus_pageobjects.wizards.opret_opgave_manuelt;

import findus_datamodels.SkyldnerModel;
import findus_pageobjects.PopupWindow;
import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.Utils;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class OpretOpgaveManueltWizardPage_SoegPartPopupWindow extends PopupWindow<OpretOpgaveManueltWizardPage> {

    Button btnSoeg = new Button(Frames.defaultContent, By.id("anTLZ1Refresh"));
    Input txtSkyldnerCPR = new Input(Frames.defaultContent, By.id("cprNumberValue"));

    protected OpretOpgaveManueltWizardPage_SoegPartPopupWindow(OpretOpgaveManueltWizardPage parentPage) {
        super(By.id("cprNumberValue"), parentPage);
    }

    public OpretOpgaveManueltWizardPage fillFormSoegBugerActivateSoeg(SkyldnerModel skyldner) {
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());
        Utils.setTextBoxTextIfNotNull(txtSkyldnerCPR,skyldner.getSkyldnerId());
        btnSoeg.click();
        resolver.waitForPopupToClose(10000);
        return parentPage;
    }
}
