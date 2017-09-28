package findus_pageobjects.wizards.grundlag_for_betalingsevne;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class GrundlagForBetalingsevneWizardPage extends BaseWizardPage {

    public GrundlagForBetalingsevneWizardPage() {
        super("Grundlag for betalingsevne");
    }

    Dropdown processFlowId = new Dropdown(Frames.uiMap, By.id("processFlowId"));
    Button btnOk = new Button(Frames.uiMap, By.id("ok"));


    public RegistrerBeregnetBetalingsevnePage continueWithBeregnetBetalingsevne() {
        processFlowId.pick("CALCPAYABILITY");

        btnOk.click();

        return new RegistrerBeregnetBetalingsevnePage();
    }

    public RegistrerGrundlagForBetalingsevnePage continueWithGrundlagForBetalingsevne() {
        processFlowId.pick("PAYMENTABILITY");
       
        btnOk.click();

        return new RegistrerGrundlagForBetalingsevnePage();
    }
}
