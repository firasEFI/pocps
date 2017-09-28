package pageobjects.generic;

import java.util.List;

//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import icisel.modules.MO_FrameNavigation;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_360GradersOverblik {

    private static List<WebElement> list = null;

    public static final Link lnk_Konto_FordringsID = new Link(Frames.tabPage, By.xpath("idType"));

    public static final List<WebElement> lnk_Konto_FordringsID(WebDriver driver) {
        MO_FrameNavigation.tabPage(driver);

        list = driver.findElements(
                By.xpath("//tr[td/span[contains(.,'Oprettelse af') and (contains(.,'fordring') or contains(.,'opkrævningsrenter') or contains(.,'opkrævningsgebyr'))]]//span[@title='Gå til Fordringsoverblik']"));
        return list;
    }

    public static final List<WebElement> lnk_Konto_Haeftelse(WebDriver driver) {
        MO_FrameNavigation.tabPage(driver);

        list = driver.findElements(By.xpath("//tr[td/span[contains(.,'Oprettelse af') and (contains(.,'fordring') or contains(.,'opkrævningsrenter') or contains(.,'opkrævningsgebyr'))]]//td[7]"));
        return list;
    }
    
    public static final List<WebElement> lnk_Konto_Oversigt(WebDriver driver) {
        MO_FrameNavigation.tabPage(driver);
        
        WebDriverWait waitingDriver = new WebDriverWait(driver, 30);        
        waitingDriver.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataExplorerTableBody4")));

        return driver.findElements(By.xpath("//tbody[@id='dataExplorerTableBody4']/tr"));
    }

    public static final Button btn_Partsoplysningsfane = new Button(Frames.tabMenu,
            By.xpath(".//*[@id='C1360VMW_T_LBL']/table/tbody/tr[2]/td"));

    public static final Button btn_Fordringsfane = new Button(Frames.tabMenu,
            By.xpath(".//*[@id='C1360ACCT_T_LBL']/table/tbody/tr[2]/td"));

    public static final Button btn_Sagsbehandlingsfane = new Button(Frames.tabMenu,
            By.xpath(".//*[@id='C1360TXR_T_LBL']/table/tbody/tr[2]/td"));

    public static final Button btn_Kontofane = new Button(Frames.tabMenu,
            By.xpath(".//*[@id='C1360FIN_T_LBL']/table/tbody/tr[2]/td"));

    public static final Button btn_Sagsbehandling_ExpandOverblikOcr = new Button(Frames.tabPage, By.id("expand_6"));

    public static final Button btn_Sagsbehandling_OverblikOcrTopText = new Button(Frames.tabPage,
            By.id("dataExplorerFilterText6"));

    public static final Link lnk_Sagsbehandling_SagsID(String sagsID) {
        return new Link(Frames.tabPage, By.xpath(".//table[@summary='Sager']//span[text()='" + sagsID + "']"));
    }
    
    public static final PageElement txt_Sagsbehandling_Oprettelsestidspunkt(String sagsID){
        return new PageElement(Frames.tabPage, By.xpath(".//*[@id='dataExplorerTableBody1']/tr[td//span[contains(.,'"+sagsID+"')]]/td[2]"));
    }
    
    public static final PageElement txt_Sagsbehandling_SagerRaekkefoelge(String sagsID){
        return new PageElement(Frames.tabPage, By.xpath(".//*[@id='dataExplorerTableBody1']/tr[td//span[contains(.,'"+sagsID+"')]]/td[1]"));
    }

}
