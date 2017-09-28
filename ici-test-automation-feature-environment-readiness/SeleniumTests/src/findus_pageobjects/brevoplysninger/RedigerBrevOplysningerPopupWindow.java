package findus_pageobjects.brevoplysninger;

import findus_pageobjects.PopupWindow;
import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.Utils;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Checkbox;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

//public class RedigerBrevOplysningerPopupWindow extends PopupWindow<BrevoplysningerPage> {
//
//
//    final Dropdown cboModtagerAdresse = new Dropdown(Frames.uiMap, By.id("boGroup_recipientAddressId"));
//    final Checkbox chkTilfoejOCR = new Checkbox(Frames.uiMap, By.id("boGroup_addOCRLine"));
//
//    final Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
//    final Button btnAnnuller = new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));
//
//    public RedigerBrevOplysningerPopupWindow(BrevoplysningerPage parentPage) {
//        super(By.id("boGroup_addOCRLine"), parentPage);
//    }
//
//    public RedigerBrevOplysningerPopupWindow fillForm(RedigerBrevOplysningerPopupWindowParm parm) {
//
//        Utils.setCheckboxCheckedIfNotNull(chkTilfoejOCR, parm.TilfoejOCR());
//        return this;
//    }
//
//    public String getModtagerAdresse(){
//
//        return cboModtagerAdresse.getText();
//    }
//
//    public Brevoplysninger_PrimaerSubPage activateGem() {
//        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());
//
//        btnGem.click();
//
//        resolver.waitForPopupToClose(10000);
//
//        return new Brevoplysninger_PrimaerSubPage(parentPage);
//    }
//
//    public BrevoplysningerPage activateAnnuller() {
//        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());
//
//        btnAnnuller.click();
//
//        resolver.waitForPopupToClose(10000);
//
//        return new BrevoplysningerPage();
//    }
//}