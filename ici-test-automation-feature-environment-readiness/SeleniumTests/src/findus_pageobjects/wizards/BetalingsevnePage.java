package findus_pageobjects.wizards;

import findus_pageobjects.BasePsrmPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

/**
 * Created by nielsjes on 24-08-2017.
 */
public class BetalingsevnePage extends BasePsrmPage {

    Dropdown processFlowId = new Dropdown(Frames.uiMap, By.id("processFlowId"));
    Button okButton = new Button(Frames.uiMap, By.id("ok"));


    public BetalingsevnePage selectBeregnetBetalingsevneAndActivateOk(){
        processFlowId.pick("CALCPAYABILITY");
        okButton.click();
        return this;
    }

    public BetalingsevnePage selectGrundlagForBetalingsevneAndActivateOk(){
        processFlowId.pick("PAYMENTABILITY");
        okButton.click();
        return this;
    }






}
