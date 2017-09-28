package pageobjects.generic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import icisel.modules.MO_FrameNavigation;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_OCR {

    private static List<WebElement> list = null;

    public static final Input txt_Betalingsfrist = new Input(Frames.uiMap, By.id("effectiveDate"));

    public static final Button btn_Gem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));

    public static final Dropdown drp_Inkluder = new Dropdown(Frames.uiMap, By.id("shouldCover"));

    public static final Button btn_SagsIDSoeg = new Button(Frames.uiMap, By.id("caseId_search"));

    public static final List<WebElement> chkbox_Fordring(WebDriver driver) {
        MO_FrameNavigation.uiMap(driver);

        list = driver.findElements(By.xpath("//*[@id[contains(.,'checkbox')]]"));
        return list;
    }

    public static final Input txt_OCRLinje = new Input(Frames.zoneMapFrame_2, By.id("OCR-line_id"));

    public static final PageElement txt_Fordringstekst(String fordringsID) {
        return new PageElement(Frames.uiMap,
                By.xpath(".//table[@id='obligations']//tr[contains(.," + fordringsID + ")]//input[@type='checkbox']"));
    }

    public static final Input input_SagsID = new Input(Frames.uiMap, By.id("caseId"));

    public static final Input txt_OprettetBetalingsfrist = new Input(Frames.zoneMapFrame_2,
            By.id("OCR-line_paymentDay"));
}
