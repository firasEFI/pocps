package pageobjects.generic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import icisel.modules.MO_FrameNavigation;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.elements.Menu;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_Navigation {

    private static WebElement element = null;
    private static List<WebElement> list = null;

    public static final Menu menu_visKontoKontekst_Menu = new Menu(Frames.dashboard,
            By.xpath("//*[@title='Vis konto kontekst']"));

    public static final Menu menu_visPartKontekst_Menu = new Menu(Frames.dashboard,
            By.xpath("//*[@title='Vis kontekst menu for parten']"));

    public static final Menu btn_Menu = new Menu(Frames.main, By.xpath("//*[@id='IM_menuButton']"));

    public static final Menu btn_Admin = new Menu(Frames.main, By.xpath("//*[@id='IM_adminButton']"));

    public static final PageElement tabel_KundePersonRelateretKontoSoeg = new PageElement(Frames.main,
            By.id("goToAccount"));

    // Tidligere Tilbage
    public static final Button btn_Tilbage = new Button(Frames.main, By.id("IM_GOBACK"));

    public static final Button btn_PrimaerFane = new Button(Frames.main,
            By.xpath("/html/body/table/tbody/tr/td[1]/table/tbody/tr[2]/td"));

    public static final Button btn_LogFane = new Button(Frames.tabMenu,
            By.xpath("/html/body/table/tbody/tr/td[2]/table/tbody/tr[2]/td"));

    public static final Link lnk_Kontonummer = new Link(Frames.dashboard,
            By.xpath("//a[contains(@onclick,'ACCT_ID')]"));

    public static final Button btn_Gem = new Button(Frames.main, By.id("IM_SAVE"));

    public static final Button btn_KundeOverblikFanePartsoplysninger = new Button(Frames.tabMenu,
            By.id("C1360VMW_T_LBL"));

    public static final Button btn_KundeOverblikFaneFordringer = new Button(Frames.tabMenu, By.id("C1360ACCT_T_LBL"));

    public static final Button btn_KundeOverblikFaneSagsbehandling = new Button(Frames.tabMenu,
            By.id("C1360TXR_T_LBL"));

    public static final Button btn_KundeOverblikFaneKonto = new Button(Frames.tabMenu, By.id("C1360FIN_T_LBL"));

    public static final PageElement txt_KundeOverblikPartsoplysningerCPR = new PageElement(Frames.zoneMapFrame_1,
            By.xpath("//span[@id='personInfo_idnumber']"));

    public static final Button btn_KundeOverblikFordringerVisAlle = new Button(Frames.tabPage,
            By.xpath("/html/body/div[2]/span/table/tbody/tr/td[5]/table[1]/tbody/tr/td[2]/a"));

    public static final Button btn_KundeOverblikFordringerVis0Saldo = new Button(Frames.tabPage,
            By.xpath("/html/body/div[2]/span/table/tbody/tr/td[5]/table[2]/tbody/tr/td[2]/a"));

    public static final List<WebElement> lst_Sager(WebDriver driver) {
        MO_FrameNavigation.tabPage(driver);

        list = driver.findElements(By.xpath("/html/body/div[9]/div/table/tbody/tr[7]/td/div/table/tbody/tr"));
        return list;
    }

    public static final PageElement txt_SagerInformation(int i) {
        return new PageElement(Frames.tabPage,
                By.xpath("/html/body/div[9]/div/table/tbody/tr[7]/td/div/table/tbody/tr[" + i + "]/td[4]"));
    }

    public static final PageElement txt_SagerSagsbetingelse(int i) {
        return new PageElement(Frames.tabPage,
                By.xpath("/html/body/div[9]/div/table/tbody/tr[7]/td/div/table/tbody/tr[" + i + "]/td[5]"));
    }

    public static final Button btn_PartsoplysningerNavn = new Button(Frames.zoneMapFrame_1,
            By.xpath("/html/body/div[2]/div[1]/div[5]/span/img"));

    public static final List<WebElement> lnk_Fordring(WebDriver driver) {
        driver.switchTo().defaultContent();

        list = driver.findElements(By.xpath("//*[@class[contains(.,'firstCol')]]"));
        return list;
    }

    public static final PageElement txt_Fordringstekst(int i) {
        return new PageElement(Frames.defaultContent,
                By.xpath("/html/body/table/tbody/tr[7]/td/div/table/tbody/tr[" + i + "]/td[6]/span"));
    }

    public static final Button btn_Opdater = new Button(Frames.main, By.id("IM_REFRESH"));

    public static final Button btn_OpgaveoversigtOpdater = new Button(Frames.dashboard,
            By.xpath(".//button[@onclick='refreshToDoUserSummary()']"));

    public static final PageElement txt_Sagstekst(int i) {
        return new PageElement(Frames.defaultContent,
                By.xpath("/html/body/table/tbody/tr[5]/td/div/table/tbody/tr[" + i + "]/td[2]/span"));
    }

    public static final List<WebElement> lnk_SagsbehandlingNoter(WebDriver driver) {
        MO_FrameNavigation.tabPage(driver);

        list = driver.findElements(By.xpath(".//span[contains(text(), 'Note vedhaeftet')]"));
        return list;
    }

    public static final Link lnk_SagsbehandlingNoteTitel(int i) {
        return new Link(Frames.tabPage,
                By.xpath("/html/body/div[8]/div/table/tbody/tr[5]/td/div/table/tbody/tr[" + i + "]/td[3]"));
    }

    public static final Link lnk_menu_script_todo = new Link(Frames.dashboard,
            By.xpath("//a[contains(text(),'To do BPA script to call the UI-map')]"));

    public static final Link sky1 = new Link(Frames.dashboard,
            By.xpath("/html/body/table/tbody/tr/td[2]/div/div[11]/div/table/tbody/tr[67]/td[2]/a"));

    public static final Button visPartFra360GradersOverblik = new Button(Frames.zoneMapFrame_1,
            By.xpath("/html/body/div/div[1]/span/a"));

    /**
     * Returnerer "Aktuel Saldo" fra 360 graders overblik, fanen "fordringer"
     * 
     * @param driver
     * @return webelement med tekst, der svarer til saldo
     */
    public static final PageElement txt_AktuelSaldo = new PageElement(Frames.zoneMapFrame_1,
            By.xpath("/html/body/div/div[3]/span"));

    /**
     * Gaar til fordringshaver angivet ved xpath (oeverste?)
     * 
     * @param driver
     * @return
     */
    public static final Link htxt_GaaTilFordringshaverRelation = new Link(Frames.zoneMapFrame_1,
            By.xpath("/html/body/div[2]/div/table/tbody/tr[7]/td/div/table/tbody/tr/td[5]/a/span"));

    // Tidligere table_dataExplorerTableBody3
    public static final PageElement tbl_dataExplorerTableBody3 = new Link(Frames.tabPage,
            By.id("dataExplorerTableBody3"));

    public static final List<WebElement> tr_dataExplorerTable3(WebDriver driver) {
        MO_FrameNavigation.tabPage(driver);
        List<WebElement> tablerows = tr_getTableRowsByIdAsList(driver, "dataExplorerTable3");
        return tablerows;
    }

    /**
     * returns table row elements identified by the table id
     */
    public static final List<WebElement> tr_getTableRowsByIdAsList(WebDriver driver, String id) {
        return driver.findElements(By.xpath("id('" + id + "')/tbody/tr"));
    }

    public static final Link lnk_KundeOverblikPartsoplysningerNavn = new Link(Frames.zoneMapFrame_1,
            By.xpath("//span[@id='personInfo_name']/a"));

    public static final Link lnk_FordringsID(String fordringsID) {
        return new Link(Frames.defaultContent,
                By.xpath(".//*[@id='dataExplorerTableBody1']//td/span[text()=" + fordringsID + "]"));
    }

    public static final Menu menu_userMenu = new Menu(Frames.main, By.id("youAreLoggedInAsSpan"));

}
