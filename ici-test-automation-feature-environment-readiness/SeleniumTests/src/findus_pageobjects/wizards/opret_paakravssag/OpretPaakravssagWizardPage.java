package findus_pageobjects.wizards.opret_paakravssag;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.Utils;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerPaakravssagPage;
import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import icisel.taxobjects.Fordring;
import org.openqa.selenium.By;

public class OpretPaakravssagWizardPage extends BaseWizardPage {

    //Toppen
    final Dropdown cboInddrivelsesskridt = new Dropdown(Frames.uiMap, By.id("collCaseType"));
    final Dropdown cboPaakravstype = new Dropdown(Frames.uiMap, By.id("demandType"));
    
    //Inkluderede fordringer
    final Button btnVaelgAlt = new Button(Frames.uiMap, By.xpath("//div[@id = \"claimsInformationList\"]/table/tbody/tr/td/input[@id= \"select_all_button\"]")); //multiple of same ids exist
    final Button btnFravaelgAlt = new Button(Frames.uiMap, By.id("deselect_all_button"));
    final Button btnShowRelatedClaims = new Button(Frames.uiMap, By.id("show_related_claims"));
    final Checkbox chk_Fordring(String fordringsID) {
        return new Checkbox(Frames.uiMap,
                By.xpath(".//table[@id='myTable']//tr[contains(.," + fordringsID + ")]//input[@type='checkbox']"));
    }

    //Bunden
    final Button btnOpret = new Button(Frames.uiMap, By.id("ok"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@value='Annuller']"));
    final Input txtChkvalerr = new Input(Frames.uiMap, By.id("chkValErr"));
    final Input txtSelectedobldesc = new Input(Frames.uiMap, By.id("selectedOblDesc"));
    final Input txtErrorexist = new Input(Frames.uiMap, By.id("errorExist"));
    final Input txtTotbalamntall = new Input(Frames.uiMap, By.id("totBalAmntAll"));
    final Input txtKonto = new Input(Frames.uiMap, By.id("accountIdInf"));

// endregion

    public OpretPaakravssagWizardPage() {
        super("Opret påkravssag");
    }

    public OpretPaakravssagWizardPage fillForm(OpretPaakravssagWizardParm parm){
        //Toppen
        Utils.setDropdownVisibleTextIfNotNull(cboPaakravstype, parm.getPaakravstype());
        Utils.setDropdownVisibleTextIfNotNull(cboInddrivelsesskridt, parm.getInddrivelsesskridt());

        for (Fordring fordring : parm.getFordringer())
            chk_Fordring(fordring.getsFordringsID()).click();

        return this;
    }
    
    public OpretPaakravssagWizardPage selectPaakravSomInddrivelsesSkridt(){
        cboInddrivelsesskridt.pick("DEML");

        return this;
    }

    public OpretPaakravssagWizardPage selectPaakravsTypeMedPartshoring(){
        cboPaakravstype.pick("FDP");

        return this;
    }

    public OpretPaakravssagWizardPage selectPaakravsTypeUdenPartshoring(){
        cboPaakravstype.pick("FDM");

        return this;
    }

    public Sagsbehandlingsskridt_PrimaerPaakravssagPage activateOpret(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        btnOpret.click();

        return new Sagsbehandlingsskridt_PrimaerPaakravssagPage();
    }

    public OpretPaakravssagWizardPage activateVaelgAlt() {
        btnVaelgAlt.click();
        return this;
    }

    public OpretPaakravssagWizardPage activateFravaelgAlt() {
        btnFravaelgAlt.click();

        return this;
    }

    public OpretPaakravssagWizardPage activateOpretExpectFail(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        btnOpret.click();

        return this;
    }

    public BasePsrmPage activateAnnuller(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        btnAnnuller.clickElementUntilNotPresent();

        return new BasePsrmPage();
    }

}

