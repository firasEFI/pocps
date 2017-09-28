package findus_pageobjects.wizards.valg_af_markering;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class ValgAfMarkeringWizardPage extends BaseWizardPage {

    public ValgAfMarkeringWizardPage() {
        super("Valg af markering");
    }

// region "PageElements"
    final Button btnOk = new Button(Frames.uiMap, By.id("ok"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));
    final Input txtTotbalamntall = new Input(Frames.uiMap, By.id("totBalAmntAll"));
    final Dropdown cboVaelgMarkeringstype = new Dropdown(Frames.uiMap, By.id("processFlowId"));

// endregion

}

