package findus_pageobjects.wizards.fordringer;

import findus_pageobjects.Utils;
import findus_pageobjects.brevoplysninger.BrevoplysningerPage;
import findus_pageobjects.brevoplysninger.Brevoplysninger_PrimaerSubPage;
import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class FordringerWizardPage extends BaseWizardPage {

    final Input txtBetalingsfrist = new Input(Frames.uiMap, By.id("effectiveDate"));
    final Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));

    public FordringerWizardPage() {
        super("Fordringer");
    }

    public FordringerWizardPage fillForm(FordringerWizardPageParm parm){
        Utils.setTextBoxTextIfNotNull(txtBetalingsfrist, parm.getBetalingsfrist());
        return this;
    }

    public Brevoplysninger_PrimaerSubPage activateGem(){
        btnGem.click();
        return new Brevoplysninger_PrimaerSubPage();
    }

    public Brevoplysninger_PrimaerSubPage activateAnnuller(){
        btnAnnuller.click();
        return new Brevoplysninger_PrimaerSubPage();
    }
}
