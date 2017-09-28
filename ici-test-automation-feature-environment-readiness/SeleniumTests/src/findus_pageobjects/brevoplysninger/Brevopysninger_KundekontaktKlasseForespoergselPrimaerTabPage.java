package findus_pageobjects.brevoplysninger;

import findus_datamodels.CprSkyldnerModel;
import findus_pageobjects.SubPage;
import findus_pageobjects.Utils;
import findus_pageobjects.WebList;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class Brevopysninger_KundekontaktKlasseForespoergselPrimaerTabPage extends SubPage<BrevoplysningerPage> {

    final Input txtPart = new Input(Frames.tabPage, By.id("personId"));
    final Button btnSoeg = new Button(Frames.tabPage, By.id("anTLZ1Refresh"));
    final WebList lstSoegeResultat = new WebList(Frames.tabPage, "dataExplorerTable1");

    public Brevopysninger_KundekontaktKlasseForespoergselPrimaerTabPage(BrevoplysningerPage parentPage) {
        super(parentPage);
    }

    public Brevopysninger_KundekontaktKlasseForespoergselPrimaerTabPage fillForm(CprSkyldnerModel cprSkyldnerModel){
        Utils.setTextBoxTextIfNotNull(txtPart, cprSkyldnerModel.getSkyldnerId());
        return this;
    }

    public Brevopysninger_KundekontaktKlasseForespoergselPrimaerTabPage activateSoeg(){
        btnSoeg.click();
        return this;
    }

    public Brevoplysninger_PrimaerSubPage activateSpecificCellKundekontaktID(int rowIndex){
        lstSoegeResultat.clickCell("Kundekontakt ID", rowIndex);
        return new Brevoplysninger_PrimaerSubPage(getParentPage());
    }

}
