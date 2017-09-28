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
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_Opret_Paakrav {
    public static final Dropdown drp_Inddrivelsesskridt = new Dropdown(Frames.uiMap, By.id("collCaseType"));

    // NOTE: the table id keep on changing between id=obligations and id=myTable. So for now avoid using this function and use the OraList one below instead.
    public static final List<WebElement> table_Fordringer(WebDriver driver) {
        return driver.findElements(By.xpath(".//table[@id='obligations' or @id='myTable']/tbody/tr"));
    }
    
    public static final List<WebElement> table_FordringerByOraListAttribute(WebDriver driver) {
        return driver.findElements(By.xpath(".//table[@oralist='claimsInformationList']/tbody/tr"));
    }
    
    public static final Checkbox chkbox_fordring = new Checkbox(Frames.uiMap, By.xpath("span/input[@type='checkbox']"));
    
    public static final PageElement text_fordringId = new PageElement(Frames.uiMap, By.xpath("span[@orafield='saId' or @orafield='fordringsId']"));
    
    public static final PageElement text_claims = new PageElement(Frames.uiMap, By.xpath("span[@orafield='claimInfo']"));

    public static final Button btn_Opret = new Button(Frames.uiMap, By.id("ok"));

    public static final Button btn_OpretVelkomstbrev = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));

    public static final Dropdown drp_TypeAfBrev = new Dropdown(Frames.defaultContent, By.id("select_customer_type"));

    public static final Dropdown drp_Paakravstype = new Dropdown(Frames.uiMap, By.id("demandType"));
    
    public static final Dropdown drp_Modtager = new Dropdown(Frames.uiMap, By.id("boGroup_recipientPersonId"));

    public static final Dropdown drp_Addresse = new Dropdown(Frames.uiMap, By.id("boGroup_recipientAddressId"));

    public static final Button btn_VaelgAlt = new Button(Frames.uiMap, By.id("select_all_button"));

    public static final Button btn_OK = new Button(Frames.defaultContent, By.id("okButton"));

    public static final Checkbox chkbox_Gebyr = new Checkbox(Frames.defaultContent, By.id("includeFee"));

    public static final Input input_AarsagTilGebyrfritagelse = new Input(Frames.defaultContent, By.id("reasonExcludeFee"));
    
    public static final PageElement txt_Titellinje = new PageElement(Frames.main, By.id("ptitle"));
    
    public static final Link lnk_Paakrav_Brev = new Link(Frames.zoneMapFrame_2,
            By.xpath("//div[*[text()='Påkrav']]//a[@class='normal oraLink']")); // link
                                                                                // hvor
                                                                                // der
                                                                                // står
                                                                                // "Påkrav"

    public static final Link lnk_Paakrav = new Link(Frames.tabPage, By
            .xpath("//tbody[@id='dataExplorerTableBody9']//tr[contains(.,'86557681214730')]//a[contains(.,'Påkrav')]"));

    public static final Button btn_Rediger = new Button(Frames.zoneMapFrame_1, By.xpath("//input[@oramdlabel='C1_EDIT_LBL']"));

    public static final Dropdown drp_Recipient = new Dropdown(Frames.uiMap, By.id("boGroup_recipientPersonId"));

    // Blank field of dropdown "drp_Adress"
    public static final PageElement txt_BlankRecipientOption = new PageElement(Frames.uiMap,
            By.xpath("//*[@id='boGroup_recipientPersonId']/option[1]"));

    public static final Dropdown drp_Adress = new Dropdown(Frames.uiMap, By.id("boGroup_recipientAddressId"));

    // Blank field of dropdown "drp_Adress"
    public static final PageElement txt_BlankAdressOption = new PageElement(Frames.uiMap,
            By.xpath("//*[@id='boGroup_recipientAddressId']/option[1]"));

    public static final Checkbox chkbox_AogD = new Checkbox(Frames.uiMap, By.id("boGroup_digital"));

    public static final Checkbox chkbox_Tilfoej_OCR_linje = new Checkbox(Frames.uiMap, By.id("boGroup_addOCRLine"));

    public static final Button btn_Gem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));

    public static final Checkbox chk_Til_Gaeld_overblik = new Checkbox(Frames.uiMap, By.id("boGroup_addDebtOverview"));

    public static final Button btn_GenererUdkast = new Button(Frames.zoneMapFrame_1,
            By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr/td[5]/input"));
    
    public static final PageElement text_primaer_header = new PageElement(Frames.zoneMapFrame_1, By.xpath("//span[@orafield='customerContactInfo']"));

    public static final PageElement btn_SeUdkast = new PageElement(Frames.zoneMapFrame_1,
            By.xpath("//span[@oramdlabel='DK_VIEW_DRAFT_LBL']"));

    public static final Button btn_GodkendUdkast= new Button(Frames.zoneMapFrame_1,
            By.xpath("//input[@value='Godkend udkast']"));

    public static final Button btn_SendTilAOgD = new Button(Frames.zoneMapFrame_1,
            By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr/td[5]/input"));

    public static final Button btn_SendtTilSkyldner = new Button(Frames.zoneMapFrame_1,
            By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr/td[5]/input"));

    public static final Link lnk_Paakravssag = new Link(Frames.uiMap,
            By.xpath("/html/body/div[9]/div/table/tbody/tr[7]/td/div/table/tbody/tr/td[3]/a/span"));

    public static final Link lnk_OCRLinje = new Link(Frames.tabPage,
            By.xpath("/html/body/div[1]/div/table/tbody/tr[5]/td/div/table/tbody/tr[3]/td[6]/a/span"));

    public static final PageElement txt_OCRIdentificerNummer = new PageElement(Frames.tabPage, By.id("OCR-line_id"));

    public static final PageElement txt_Fordringstekst(WebDriver driver, int i) {
        return new PageElement(Frames.uiMap,
                By.xpath("/html/body/table[3]/tbody/tr[8]/td/table/tbody[" + i + "]/tr/td[2]/span[2]"));
    }

    public static final PageElement txt_ModtagerAfPaakrav = new PageElement(Frames.zoneMapFrame_2,
            By.id("recipientPersonId"));

    public static final PageElement txt_ModtagerAdresseAfPaakrav = new PageElement(Frames.zoneMapFrame_2,
            By.id("recipientAddressId"));

    public static final PageElement txt_SagsID = new PageElement(Frames.zoneMapFrame_2, By.id("processFlowId"));

    public static final Link lnk_FjernFordringFraPaakrav = new Link(Frames.zoneMapFrame_2, By.id("processFlowId"));

    

    

    /**
     * Returner tabel med inkluderede fordringer fra siden
     * "sagsbehandlingsskridt", som frekommer, naar man gaar ind paa en sag (fx
     * et paakrav).
     */
    public static final List<WebElement> table_InkluderedeFordringer(WebDriver driver) {
        MO_FrameNavigation.zoneMapFrame_2(driver);

        WebElement inkluderedeFordringerDiv = driver.findElement(By.id("obligationListLimited"));

        return inkluderedeFordringerDiv.findElements(By.xpath(".//tbody//a"));
    }

    //Elementer på sagsbehandlingsskridtet (inde på sagen)
    public static final Link lnk_TilfoejAndenAktoer = new Link(Frames.tabPage, By.xpath(".//a[text()='Tilføj anden aktør']"));
    
    public static final Link lnk_TilknyttetFordring(String fordringsID){
        return new Link(Frames.tabPage, By.xpath(".//*[@id='obligationListLimited']//tr[contains(.,'"+fordringsID+"')]/td/a"));     
    }

    
    //Elementer i forbindelse med tilføjelse af anden aktør
    public static final List<WebElement> table_andenAktoer(WebDriver driver) {
        MO_FrameNavigation.tabPage(driver);

        WebElement andenAktoerTableBody = driver.findElement(By.id("dataExplorerTableBody8"));
        return andenAktoerTableBody.findElements(By.xpath("./tr"));
    }
    
    public static final Dropdown drp_TypeAfAndenAktoer = new Dropdown(Frames.uiMap, By.id("input_agentType"));
    
    public static final Button btn_Naeste_typeAfAndenAktoer = new Button(Frames.uiMap, By.xpath("//input[@value='Næste']"));
    
    public static final Input input_Cpr = new Input(Frames.uiMap, By.id("cprId"));
    
    public static final Input input_Repraesentationsomfang = new Input(Frames.uiMap, By.id("representationLimitation"));

    public static final Dropdown drp_AndenAktoer = new Dropdown(Frames.uiMap, By.id("relationType"));

    public static final Button btn_GemAndenAktoer = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    
    public static final PageElement txt_Repraesentationsomfang_Sagsoverblik(String text) {
        return new PageElement(Frames.tabPage, By.xpath(".//*[@id='dataExplorerTableBody8']//span[text()='"+text+"']"));
    }
    
    
    //Elementer i forbindelse med fjernelse af en anden aktør
    public static final Button btn_FjernRelation(String text){
        return new Button(Frames.tabPage, By.xpath(".//*[@id='dataExplorerTableBody8']/tr[td/span[text()='"+text+"']]//.//img[@title='Fjern relation']"));
    }

    public static final Dropdown drp_BegrundelseForFjernelseAfRelation = new Dropdown(Frames.uiMap, By.id("removeReason"));

    public static final Input input_Begrundelsesnote = new Input(Frames.uiMap, By.id("reason"));

    public static final Button btn_Bekraeft = new Button(Frames.uiMap, By.id("CONFIRM_BTN_MP"));
    
}
