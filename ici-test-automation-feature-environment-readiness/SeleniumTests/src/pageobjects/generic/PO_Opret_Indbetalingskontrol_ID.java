package pageobjects.generic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import icisel.modules.MO_FrameNavigation;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Checkbox;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_Opret_Indbetalingskontrol_ID {

    private static List<WebElement> list = null;

    public static final Dropdown drp_IndbetalingskildeType = new Dropdown(Frames.tabPage, By.id("TNDR_SRCE_TYPE_FLG"));

    public static final Button btn_IndbetalingskontrolIDSoeg = new Button(Frames.tabPage, By.id("IM_DEP_CTL_ID"));

    public static final Dropdown drp_Popup_IndbetalingskildeType = new Dropdown(Frames.defaultContent,
            By.id("TNDR_SRCE_TYPE_FLG"));

    public static final Dropdown drp_Popup_IndbetalingskontrolStatus = new Dropdown(Frames.defaultContent,
            By.id("DEP_CTL_STATUS_FLG"));

    public static final List<WebElement> pop_List(WebDriver driver) {
        MO_FrameNavigation.dataFrame(driver);

        list = driver.findElements(By.xpath("/html/body/span/div/table/tbody/tr"));
        return list;
    }

    public static final PageElement pop_KontrolID(int i) {
        return new PageElement(Frames.dataFrame, By.xpath("/html/body/span/div/table/tbody/tr[" + i + "]/td[6]"));
    }

    public static final PageElement pop_Bruger(int i) {
        return new PageElement(Frames.dataFrame, By.xpath("/html/body/span/div/table/tbody/tr[" + i + "]/td[4]"));
    }

    public static final Input input_Indbetalingskilde = new Input(Frames.tabPage, By.id("TNDR_SOURCE_CD"));

    public static final Input input_IndbetalingskontrolID = new Input(Frames.tabPage, By.id("DEP_CTL_ID"));

    public static final Checkbox chkbox_AlleBrugere = new Checkbox(Frames.tabPage, By.id("ALL_OPERATORS_SW"));

    public static final Input input_BrugerID = new Input(Frames.tabPage, By.id("USER_ID"));

}
