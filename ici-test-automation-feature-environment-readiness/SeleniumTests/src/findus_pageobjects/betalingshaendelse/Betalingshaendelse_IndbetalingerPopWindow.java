package findus_pageobjects.betalingshaendelse;

import findus_pageobjects.PopupWindow;
import findus_pageobjects.PopupWindowResolver;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class Betalingshaendelse_IndbetalingerPopWindow extends PopupWindow<Betalingshaendelse_IndbetalingerSubPage> {

    Button okKnap = new Button(Frames.defaultContent, By.id("PAY_TNDR$TNDR_CANCEL_SW"));
    Button annullerKnap = new Button(Frames.defaultContent, By.xpath(".//*[@id='row8_Main']/td[2]/input"));
    Dropdown aarsagDropdown = new Dropdown(Frames.defaultContent, By.xpath(".//*[@id='PAY_TNDR$CAN_RSN_CD2']"));


    public Betalingshaendelse_IndbetalingerPopWindow(Betalingshaendelse_IndbetalingerSubPage parentPage) {
        super(By.xpath(".//*[@id='PAY_TNDR$CAN_RSN_CD2']"), parentPage);
    }

    public Betalingshaendelse_IndbetalingerPopWindow(By elementToSyncronize, Betalingshaendelse_IndbetalingerSubPage betalingshaendelse_indbetalingerSubPage) {
        super(elementToSyncronize, betalingshaendelse_indbetalingerSubPage);
    }

    public Betalingshaendelse_IndbetalingerPopWindow aTest24Values() {
        aarsagDropdown.pick("DDR");
        return this;
    }

    public Betalingshaendelse_IndbetalingerSubPage activateOk() {
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());
        okKnap.click();
        resolver.waitForPopupToClose(10000);
        return this.parentPage;
    }

    public Betalingshaendelse_IndbetalingerSubPage activateAnnuller() {
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());
        this.annullerKnap.click();
        resolver.waitForPopupToClose(10000);
        return this.parentPage;
    }
}