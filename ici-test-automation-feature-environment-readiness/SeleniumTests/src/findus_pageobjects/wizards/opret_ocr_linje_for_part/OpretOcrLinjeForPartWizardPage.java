package findus_pageobjects.wizards.opret_ocr_linje_for_part;

import findus_pageobjects.sagsbehandlingsskridt.SagsbehandlingsskridtPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerOcrLinjePage;
import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class OpretOcrLinjeForPartWizardPage extends BaseWizardPage {

    public OpretOcrLinjeForPartWizardPage() {
        super("Opret OCR-linje for part");
    }

// region "PageElements"
    final Button btnVaelgAlt = new Button(Frames.uiMap, By.id("select_all_button"));
    final Button btnFravaelgAlt = new Button(Frames.uiMap, By.id("deselect_all_button"));
    final Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));
    final Input dateTextField = new Input(Frames.uiMap, By.id("effectiveDate"));
    
    
    public Sagsbehandlingsskridt_PrimaerOcrLinjePage createOCRLineWithPayDate(String payDate) {
    	dateTextField.sendKeys(payDate);

    	btnGem.click();

    	return new Sagsbehandlingsskridt_PrimaerOcrLinjePage();
    }
    
// endregion

}

