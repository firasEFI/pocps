package findus_pageobjects.wizards.sag_for_ompostering_af_indbetaling;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class SagForOmposteringAfIndbetalingWizardPage extends BaseWizardPage {

    public SagForOmposteringAfIndbetalingWizardPage() {
        super("Sag for ompostering af indbetaling");
    }

// region "PageElements"
    final Button btnOk = new Button(Frames.uiMap, By.id("ok"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));
    final Input txtTotbalamntall = new Input(Frames.uiMap, By.id("totBalAmntAll"));
    final Dropdown cboAarsagForOmposteringAfIndbetaling = new Dropdown(Frames.uiMap, By.id("processFlowId"));

// endregion

}

