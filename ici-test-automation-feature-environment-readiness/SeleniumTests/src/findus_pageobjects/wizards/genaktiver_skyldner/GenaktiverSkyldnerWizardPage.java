package findus_pageobjects.wizards.genaktiver_skyldner;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class GenaktiverSkyldnerWizardPage extends BaseWizardPage {

    public GenaktiverSkyldnerWizardPage() {
        super("Genaktiver skyldner");
    }

// region "PageElements"
    final Button btnAktivér = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='ACTV_BTN_LBL']"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));

// endregion

}

