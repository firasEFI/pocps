package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_SoegOpgave {

    //Elementer under opgave overblik, hvor en opgave fremsøges
    public static final Input input_OpgaveID = new Input(Frames.tabPage, By.id("toDoId"));

    public static final Input input_RelationTilID = new Input(Frames.tabPage, By.id("toDoRelationId"));

    public static final Button btn_Soeg = new Button(Frames.tabPage, By.id("anTLZ1Refresh"));
    
    public static final Link lnk_Opgavetype = new Link(Frames.tabPage, By.xpath(".//*[@id='dataExplorerTableBody1']//span[@title='Gå til Angiv opgave']"));
    
    public static final Button btn_TildelIOverblik = new Button(Frames.tabPage, By.xpath(".//*[@id='dataExplorerTableBody1']//span/input[@value='Tildel']"));
    
    public static final Button btn_AfslutOpgave = new Button(Frames.tabPage, By.xpath(".//*[@id='dataExplorerTableBody1']//span/input[@value='Afslut opgave']"));
    
    //Elementer efter fremsøgning af opgave og direkte klik på "Afslut opgave"
    public static final Dropdown drp_Begrundelse = new Dropdown(Frames.uiMap, By.id("completeReason"));
    
    public static final Input input_Beskrivelse = new Input(Frames.uiMap, By.id("completeDescription"));
    
    public static final Button btn_Gem = new Button(Frames.uiMap, By.xpath("html//input[@value='Gem']"));
    
    //Elementer inde på den tilgåede opgave
    public static final Button btn_TildelIOpgaven = new Button(Frames.tabPage, By.id("FORWARD_SW"));
    
    //Elementer efter klik på "Tildel" inde på den tilgåede opgave
    public static final Dropdown drp_SendTil = new Dropdown(Frames.defaultContent, By.id("TD_SEND_TO_FLG"));
    
    public static final Input input_Bruger = new Input(Frames.defaultContent, By.id("FWD_ASSIGNED_TO"));
    
    public static final Input input_Rolle = new Input(Frames.defaultContent, By.id("FWD_ROLE_ID"));
    
    public static final Input input_Detaljer = new Input(Frames.defaultContent, By.id("LOG_DETAILS"));
    
    public static final Button btn_OK = new Button(Frames.defaultContent, By.id("OK_BTTN"));
    
    public static final Input input_OpgaveIdSoegefelt = new Input(Frames.tabPage, By.id("TD_ENTRY_ID"));
    
    
    

    public static final PageElement txt_Status = new PageElement(Frames.zoneMapFrame_2, By.id("boStatus"));
}
