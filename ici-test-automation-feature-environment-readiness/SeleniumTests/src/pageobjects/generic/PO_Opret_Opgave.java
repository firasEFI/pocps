package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

/**
 * Page objects for Opret Opgave manuel (menu-\>opgaver-\>opret opgave manuelt)
 * 
 * @author mschioeler
 *
 */
public class PO_Opret_Opgave {

    public static final Dropdown drp_Opgavekategori = new Dropdown(Frames.uiMap, By.id("opgaveCategory"));

    public static final Dropdown drp_Opgavetype = new Dropdown(Frames.uiMap, By.id("opgaveType"));

    public static final Input input_Beskrivelse = new Input(Frames.uiMap, By.id("description"));

    public static final Dropdown drp_Prioritet = new Dropdown(Frames.uiMap, By.id("toDoPriority"));

    public static final Input input_TidligsteSlutdato = new Input(Frames.uiMap, By.id("toDoFirstDeadline"));

    public static final Input input_Frist = new Input(Frames.uiMap, By.id("toDoLastDeadline"));

    public static final Input input_Sag = new Input(Frames.uiMap, By.id("case_0"));

    public static final Dropdown drp_SendTil = new Dropdown(Frames.uiMap, By.id("assignedTo"));

    public static final Input input_TildeltSagsbehandler = new Input(Frames.uiMap, By.id("assignedToWorker"));
    
    public static final Input input_TildeltGruppe = new Input(Frames.uiMap, By.id("assignedToGroup"));

    public static final Input input_Besked = new Input(Frames.uiMap, By.id("message"));

    public static final Button btn_Gem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    
    public static final PageElement txt_TopbarText = new PageElement(Frames.uiMap, By.xpath("//span[text()='Visning af oprettet opgave']"));

    public static final Button btn_luk = new Button(Frames.main, By.id("scriptClose2"));
    
    public static final Input input_FordringsID = new Input(Frames.uiMap, By.id("claim_0"));
    
    public static final Input input_PartID = new Input(Frames.uiMap, By.id("person_0"));
    
    public static final Input input_SagsID = new Input(Frames.uiMap, By.id("case_0"));
    
    public static final PageElement txt_InkluderetIdType = new PageElement(Frames.uiMap, By.xpath(".//th[@id[contains(.,'gridTbl_h_')]]"));
    
    public static final PageElement txt_OpgaveID = new PageElement(Frames.uiMap, By.xpath(".//*[@id='relationTable']//td[@orafield='todoId']"));
}
