package findus_pageobjects.wizards.vaelg_type_af_anden_aktoer;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class VaelgTypeAfAndenAktoerWizardPage extends BaseWizardPage {

    public VaelgTypeAfAndenAktoerWizardPage() {
        super("Vælg type af anden aktør");
    }

// region "PageElements"
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));
    final Button btnNaeste = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='F1_NEXT']"));
    final Dropdown cboTypeAfAndenAktoer = new Dropdown(Frames.uiMap, By.id("input_agentType"));

// endregion

}

