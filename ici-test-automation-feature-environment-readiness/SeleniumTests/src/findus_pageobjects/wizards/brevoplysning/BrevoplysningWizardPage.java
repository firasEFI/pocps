package findus_pageobjects.wizards.brevoplysning;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.Utils;
import findus_pageobjects.kundekontakt.KundekontaktPage;
import findus_pageobjects.wizards.BaseWizardPage;
import findus_pageobjects.wizards.kundekontakt.KundekontaktWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class BrevoplysningWizardPage extends BaseWizardPage {

    public BrevoplysningWizardPage() {
        super("Brevoplysning");
    }

// region "PageElements"
    final Button btnOk = new Button(Frames.uiMap, By.id("ok"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));
    final Input txtPart = new Input(Frames.uiMap, By.id("personId"));
    final Dropdown cboKundekontakttype = new Dropdown(Frames.uiMap, By.id("contactType"));
    final Input txtSagsbehandlingsskridtId = new Input(Frames.uiMap, By.id("processFlowId"));
    final Dropdown cboInddrivelsesskridt = new Dropdown(Frames.uiMap, By.id("contactClass"));

// endregion

    public BrevoplysningWizardPage fillForm(BrevoplysningWizardPageParm parm){
        Utils.setTextBoxTextIfNotNull(txtPart, parm.getPart());
        Utils.setDropdownVisibleTextIfNotNull(cboInddrivelsesskridt, parm.getInddrivelsesskridt());
        Utils.setDropdownVisibleTextIfNotNull(cboKundekontakttype, parm.getKundekontakttype());
        return this;
    }

    public KundekontaktWizardPage activateOk(){
        btnOk.click();
        return new KundekontaktWizardPage();
    }

    public BasePsrmPage activateAnnuller(){
        btnAnnuller.click();
        return new BasePsrmPage();
    }
}

