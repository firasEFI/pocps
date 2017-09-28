package findus_pageobjects.wizards.opret_rentefritagelse_for_fordring_er;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class OpretRentefritagelseForFordringErWizardPage extends BaseWizardPage {

    public OpretRentefritagelseForFordringErWizardPage() {
        super("Opret rentefritagelse for fordring(-er)");
    }

// region "PageElements"
    final Button btnVaelgAlt = new Button(Frames.uiMap, By.id("select_all_button"));
    final Button btnFravaelgAlt = new Button(Frames.uiMap, By.id("deselect_all_button"));
    final Button btnOpretRentefritagelse = new Button(Frames.uiMap, By.id("ok"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));
    final Input txtChkvalerr = new Input(Frames.uiMap, By.id("chkValErr"));
    final Input txtSelectedobldesc = new Input(Frames.uiMap, By.id("selectedOblDesc"));
    final Input txtErrorexist = new Input(Frames.uiMap, By.id("errorExist"));
    final Input txtTotbalamntall = new Input(Frames.uiMap, By.id("totBalAmntAll"));
    final Input txtKonto = new Input(Frames.uiMap, By.id("accountIdInf"));
    final Input txtFritagelsensStartDato = new Input(Frames.uiMap, By.id("startDtIdInf"));
    final Input txtFritagelsensSlutdato = new Input(Frames.uiMap, By.id("endDtIdInf"));
    final Dropdown cboFritagelsesaarsag = new Dropdown(Frames.uiMap, By.id("suppressReason"));

// endregion

}

