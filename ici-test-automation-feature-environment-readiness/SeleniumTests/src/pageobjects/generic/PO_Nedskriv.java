package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_Nedskriv {

    public static final Input txt_ActionId = new Input(Frames.uiMap, By.id("boGroup_fordring_aktionsId_asCurrent"));

    public static final Input txt_FordringshaverID = new Input(Frames.uiMap,
            By.id("boGroup_fordring_fordringhaverID_asCurrent"));

    public static final Input txt_FordringsID = new Input(Frames.uiMap,
            By.id("boGroup_fordring_fordringsId_asCurrent"));

    public static final Input input_Modtagelsesdato = new Input(Frames.uiMap,
            By.id("boGroup_fordring_modtagelseDato_asCurrent"));

    public static final Dropdown drp_AArsagskode = new Dropdown(Frames.uiMap, By.id("aarsagKode_asCurrent_0"));

    public static final Input txt_Begrundelse = new Input(Frames.uiMap, By.id("aarsagBegr_asCurrent_0"));

    public static final Input txt_AArsagstekst = new Input(Frames.uiMap, By.id("aarsagTekst_asCurrent_0"));

    public static final Button btn_ValiderForm = new Button(Frames.uiMap,
            By.xpath("/html/body/div[1]/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/input[1]"));

    // public static final Button btn_Gem = new Button(Frames.uiMap,
    // By.xpath("/html/body/div[1]/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/input[2]"));

    public static final Input txt_Valutakode = new Input(Frames.uiMap, By.id("valutaKode_asCurrent_0"));

    public static final Input txt_Beloeb = new Input(Frames.uiMap, By.id("beloeb_asCurrent_0"));

    public static final Input txt_BeloebDKK = new Input(Frames.uiMap, By.id("beloebDkk_asCurrent_0"));

    public static final Dropdown drp_begrundelse = new Dropdown(Frames.uiMap, By.id("writeDownReason"));

    public static final Input input_Hovedfordring = new Input(Frames.uiMap, By.id("mainClaimId"));

    public static final Input txt_note_for_nedskrivning = new Input(Frames.uiMap, By.id("writeDownNote"));

    public static final Input input_Fordring = new Input(Frames.uiMap, By.id("obligationId_0"));

    public static final Button btn_Search = new Button(Frames.uiMap, By.id("obligationId_search"));

    public static final Input input_Nedskrivningsbeløb = new Input(Frames.uiMap, By.id("writeOffAmount_0"));

    public static final Input input_ResterendeBeloeb = new Input(Frames.uiMap, By.id("finalAmount_0"));

    public static final Button btn_Gem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));

    public static final Button btn_Create_Letter = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));

    public static final PageElement txt_SagsID = new PageElement(Frames.zoneMapFrame_2, By.id("processFlowId"));

    public static final Input input_Virkningsdato = new Input(Frames.uiMap, By.id("effectiveDate_0"));

}
