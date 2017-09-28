package pageobjects.generic;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

public class PO_Sagsbehandlingsskridt {

    public static final Button btn_Rediger(WebDriver driver) {
        Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.xpath(".//*[@id='EDIT' and @value='Rediger']"));
        return new Button(Frames.uiMap, By.xpath(".//*[@id='EDIT' and @value='Rediger']"));
    }

    public static final Button btn_Luk(WebDriver driver) {
        Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.xpath(".//*[@id='TRANSITION_1' and @value='Luk']"));
        return new Button(Frames.uiMap, By.xpath(".//*[@id='TRANSITION_1' and @value='Luk']"));
    }

    public static final Button btn_LogFane = new Button(Frames.tabMenu,
            By.xpath(".//*[@id='C1PFLLP_T_LBL']/table/tbody/tr[2]/td"));

    public static final Button btn_PrimaerFane = new Button(Frames.tabMenu,
            By.xpath(".//*[@id='MAIN_TLBL']/table/tbody/tr[2]/td"));

    public static final PageElement txt_DatoOprettet = new PageElement(Frames.zoneMapFrame_2,
            By.xpath(".//*[@id='dataExplorerTableBody1']/tr[contains(.,'Oprettet i status Kladde')]/td[2]"));

    public static final PageElement txt_Status = new PageElement(Frames.zoneMapFrame_2, By.xpath(".//*[@id='boStatus']"));
}


