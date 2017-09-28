package pageobjects.generic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Checkbox;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_Afdragsordning {

    private static WebElement element = null;
    // private static List<WebElement> list = null;

    public static final Checkbox chk_Fordringer(String fordringsID) {
        return new Checkbox(Frames.uiMap,
                By.xpath(".//table[@id='myTable']//tr[contains(.," + fordringsID + ")]//input[@type='checkbox']"));
    }

    public static final Link lnk_FordringerInkluderet = new Link(Frames.zoneMapFrame_2,
            By.xpath(".//*[@id='obligationListLimited']/table/tbody/tr/td/a"));

    public static final Dropdown drp_Afdragsordning = new Dropdown(Frames.uiMap, By.id("boGroup_payplanType"));

    public static final Input txt_NoteAfdragsordning = new Input(Frames.uiMap, By.id("boGroup_payplanTypeNote"));

    public static final Input txt_NoteForAtTilfoejeFordring = new Input(Frames.uiMap, By.id("boGroup_addClaimReason"));

    public static final Dropdown drp_Afdragsordningsfrekvens = new Dropdown(Frames.uiMap, By.id("boGroup_frequency"));

    public static final Input txt_FordringID = new Input(Frames.uiMap, By.id("obligationId_0"));

    public static final Button btn_Search = new Button(Frames.uiMap, By.id("obligationId_search"));

    public static final Input txt_Beloeb = new Input(Frames.uiMap, By.id("boGroup_installmentAmount"));

    public static final Input txt_BeloebNote = new Input(Frames.uiMap, By.id("boGroup_installmentAmountNote"));

    public static final Input txt_Adresse = new Input(Frames.uiMap, By.id("boGroup_address1"));

    public static final Dropdown drp_Adressetype = new Dropdown(Frames.uiMap, By.id("boGroup_addressType"));

    public static final Button btn_Gem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));

    public static final Button btn_Gem_LukSag = new Button(Frames.defaultContent, By.id("SAVE_BTN_MP"));

    /**
     * Skift til nyt vindue skal udføres i testcase/byggeklods. Dette er endnu
     * ikke udført, derfor deprecated.
     * 
     * @deprecated
     */
    public static final Button btn_Annuller_LukSag = new Button(Frames.defaultContent, By.id("CANCEL_BTN_MP"));

    public static final Button btn_Dan = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));

    public static final Button btn_OpretInaktiv = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));

    public static final PageElement txt_Oprettelsestidspunkt = new PageElement(Frames.zoneMapFrame_2,
            By.id("creationDateTime"));

    public static final Button btn_AnnullerInaktiv = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_2"));

    public static final Button btn_AnnullerAktiv = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));

    public static final PageElement txt_Titellinje = new PageElement(Frames.main, By.id("ptitle"));

    public static final PageElement txt_StatusTidspunktAnnulleret = new PageElement(Frames.zoneMapFrame_2,
            By.id("statusDateTime"));

    public static final Link lnk_Afdragsordning = new Link(Frames.zoneMapFrame_2, By.id("inactivePayplan"));

    public static final PageElement lnk_Afdragsordning2 = new PageElement(Frames.tabPage, By.xpath(
            "//span['Sager']/ancestor::div[@class='portalZoneFull']//table//span[@title='Gaa til Sagsbehandlingsskridt' and text()[contains(.,'Aktiv')]]"));

    public static final Button btn_Aktiver = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_3"));

    /**
     * Dette webelement svarer til feltet i popup-boksen ved aktivering, IKKE
     * ved oprettelse af bobehandlingsafdragsordning.
     */
    public static final Input input_DatoFoersteBetaling = new Input(Frames.defaultContent,
            By.id("dateFirstInstallment"));

    public static final Input input_Aktiveringsnote = new Input(Frames.defaultContent, By.id("activationNote"));

    /**
     * Til brug ved bobehandlingsafdragsordning
     */
    public static final Input txt_DatoFoersteBetaling_Bobehandling = new Input(Frames.uiMap,
            By.xpath("//input[@id[contains(.,'boGroup_dateFirstInstallment')]]"));

    public static final Button btn_GemDato = new Button(Frames.defaultContent, By.id("SAVE_BTN_MP"));

    public static final PageElement txt_SagsID = new PageElement(Frames.zoneMapFrame_2, By.id("processFlowId"));

    public static final PageElement txt_Status = new PageElement(Frames.zoneMapFrame_2, By.id("boStatus"));

    public static final PageElement txt_status_afdrag = new PageElement(Frames.tabPage,
            By.xpath("/html/body/div[9]/div/table/tbody/tr[7]/td/div/table/tbody/tr/td[6]/span"));

    // TODO Skal der her oprettes et "tabel" object? - Jakob
    public static final PageElement tbl_Sager = new PageElement(Frames.tabPage,
            By.xpath("//span['sager']/ancestor::div[@class='portalZoneFull']//table"));

    // Tidligere fane_Primaer
    public static final Button btn_fanePrimaer = new Button(Frames.tabMenu,
            By.xpath("//*[text()[contains(.,'Primaer')]]"));

    public static final Button btn_Rediger = new Button(Frames.zoneMapFrame_2, By.id("EDIT"));

    public static final Button btn_LukSag = new Button(Frames.zoneMapFrame_2, By.xpath("//input[@value='Luk sag']"));

    public static final Button btn_TilfoejFordring = new Button(Frames.uiMap,
            By.xpath("//img[@class='imgPlus' and @onclick='oraAddGridRow(event);']"));

    public static final Button btn_SoegFordring = new Button(Frames.uiMap, By.xpath(
            "//table[@id='sasList']//tr[@id='aaa']//td[@class='oraNormal oraDisplayCell' and count(.//span)=1]//img[@onclick='oraOpenSearchImgClickEvent(event);']"));

    // Tidligere tabel_InkluderedeFordringer
    // TODO Skal der her oprettes et "tabel" object? - Jakob
    public static final PageElement tbl_InkluderedeFordringer = new PageElement(Frames.uiMap, By.id("sasList"));

    public static final PageElement txt_fejlBesked = new PageElement(Frames.uiMap, By.id("ERRMSG-TEXT-SPAN"));

    /**
     * Skift til nyt vindue skal udføres i testcase/byggeklods. Dette er endnu
     * ikke udført, derfor deprecated.
     * 
     * @deprecated
     */
    public static final PageElement txt_fejlBesked_LukSag = new PageElement(Frames.defaultContent,
            By.id("ERRMSG-TEXT-SPAN"));

    public static final Checkbox chk_SkylderErBlevetInformeret = new Checkbox(Frames.defaultContent,
            By.id("debtorInformed"));

    public static final Input txtArea_Lukningsbegrundelse = new Input(Frames.defaultContent,
            By.xpath("//textarea[@id='closeReason']"));

    public static final Button btn_Gem_OpretBobehandlingssag = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));

    public static final Input input_Titel_OpretBobehandlingssag = new Input(Frames.uiMap, By.id("boGroup_title"));

    public static final Input input_AArsag_OpretBobehandlingssag = new Input(Frames.uiMap, By.id("boGroup_reason"));

    public static final Input input_NoteForAdresse = new Input(Frames.uiMap, By.id("boGroup_addressTypeNote"));

    public static final Input input_SkyldnersNavn = new Input(Frames.uiMap, By.id("boGroup_debtorName"));

    public static final Input input_CONavn = new Input(Frames.uiMap, By.id("boGroup_coName"));

    public static final Input input_Adresse1 = new Input(Frames.uiMap, By.id("boGroup_address1"));

    public static final Input input_Adresse2 = new Input(Frames.uiMap, By.id("boGroup_address2"));

    public static final Input input_Adresse3 = new Input(Frames.uiMap, By.id("boGroup_address3"));

    public static final Input input_Postnummer = new Input(Frames.uiMap, By.id("boGroup_postal"));

    // Tidligere img_forstoerrelsesglas_OpretBobehandlingssag
    public static final Button btn_forstoerrelsesglas_OpretBobehandlingssag = new Button(Frames.uiMap,
            By.id("obligationId_search"));

    // OK knap i forbindelse med annullering af afdragsordning
    public static final Button btn_OK = new Button(Frames.defaultContent, By.xpath("html//input[@value='OK']"));

    public static final Input input_Lukningsbegrundelse = new Input(Frames.defaultContent, By.id("closeReason"));

}
