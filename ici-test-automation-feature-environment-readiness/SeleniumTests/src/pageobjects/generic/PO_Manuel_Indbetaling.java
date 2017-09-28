package pageobjects.generic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import icisel.modules.MO_FrameNavigation;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;

public class PO_Manuel_Indbetaling {

    private static List<WebElement> list = null;

    public static final Button btn_IndbetalingskildeID_Soeg = new Button(Frames.tabPage, By.id("IM_TNDR_CTL_ID"));

    public static final Button btn_SoegIndbetalingskontrolID = new Button(Frames.defaultContent,
            By.id("IM_DEP_CTL_ID"));

    public static final Dropdown drp_UdbudKildeType = new Dropdown(Frames.defaultContent, By.id("TNDR_SRCE_TYPE_FLG"));

    public static final Button btn_SoegIndbetaling = new Button(Frames.defaultContent, By.id("IM_Alt_altSearch"));

    public static final List<WebElement> lnk_Indbetalingskontrol(WebDriver driver) {
        MO_FrameNavigation.uiMap(driver);

        list = driver.findElements(By.xpath("//*[@id[contains(.,'SEARCH_RESULTS')]]"));
        return list;
    }

    public static final Button btn_Soeg_IndbetalingskildeSoegning = new Button(Frames.defaultContent,
            By.id("IM_Alter2_alter2Srch"));

    public static final Dropdown drp_AntalBetalingshaendelser = new Dropdown(Frames.tabPage, By.id("PEVT_MODE_FLG"));

    public static final Input txt_BetalerKontoID = new Input(Frames.QUICK_ADD_TNDR,
            By.id("QUICK_ADD_TNDR:0$PAYOR_ACCT_ID"));

    public static final Input txt_UdbudBeloeb = new Input(Frames.QUICK_ADD_TNDR, By.id("QUICK_ADD_TNDR:0$TENDER_AMT"));

    public static final Dropdown drp_Daekningsregel = new Dropdown(Frames.QUICK_ADD_PAY,
            By.id("QUICK_ADD_PAY:0$DST_RULE_CD"));

    public static final Input txt_Regelvaerdi_ACC = new Input(Frames.QUICK_ADD_PAY,
            By.id("QUICK_ADD_PAY:0$CHAR_VAL_FK1"));

    public static final Input txt_Regelvaerdi_OCR = new Input(Frames.QUICK_ADD_PAY,
            By.id("QUICK_ADD_PAY:0$ADHOC_CHAR_VAL"));

    public static final Input txt_Beloeb = new Input(Frames.QUICK_ADD_PAY, By.id("QUICK_ADD_PAY:0$PAY_AMT"));

    public static final Input txt_type = new Input(Frames.QUICK_ADD_PAY, By.id("QUICK_ADD_PAY:0$TENDER_TYPE_CD"));

    public static final Button btn_Opret = new Button(Frames.tabPage, By.id("CREATE_SW"));

}
