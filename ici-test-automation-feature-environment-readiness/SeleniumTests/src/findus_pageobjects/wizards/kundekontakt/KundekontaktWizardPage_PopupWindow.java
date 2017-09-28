package findus_pageobjects.wizards.kundekontakt;

import findus_datamodels.CprSkyldnerModel;
import findus_datamodels.SkyldnerModel;
import findus_pageobjects.PopupWindow;
import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.Utils;
import findus_pageobjects.WebList;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class KundekontaktWizardPage_PopupWindow extends PopupWindow<KundekontaktWizardPage> {

    private Button btnSoeg = new Button(Frames.defaultContent, By.id("anTLZ1Refresh"));
    private Input txtPartensNavn = new Input(Frames.defaultContent, By.id("entityName"));
    private WebList lstResult = new WebList(Frames.defaultContent, "dataExplorerTable1");

    protected KundekontaktWizardPage_PopupWindow(KundekontaktWizardPage parentPage) {
        super(By.id("entityName"), parentPage);
    }

    public KundekontaktWizardPage_PopupWindow fillForm(CprSkyldnerModel skyldner){
        Utils.setTextBoxTextIfNotNull(txtPartensNavn, skyldner.getEfternavn()+", "+skyldner.getFornavn());
        return this;
    }

    public KundekontaktWizardPage_PopupWindow selectSpecificResult(int rowIndex){
        lstResult.clickCell("Partens navn", rowIndex);
        return this;
    }

    public KundekontaktWizardPage_PopupWindow activateSoegNavnWithResultSet(){
        btnSoeg.click();
        return this;
    }

    public KundekontaktWizardPage activateSoegNavn(){
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());
        btnSoeg.click();
        resolver.waitForPopupToClose(10000);
        return this.parentPage;
    }
}
