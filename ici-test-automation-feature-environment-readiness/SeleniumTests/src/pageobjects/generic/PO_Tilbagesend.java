package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_Tilbagesend {

    public static final Input input_Konto = new Input(Frames.uiMap, By.id("boGroup_accountId"));

    public static final Dropdown drp_Begrundelse = new Dropdown(Frames.uiMap, By.id("boGroup_sendBackReason"));

    public static final Input input_Virkningsdato = new Input(Frames.uiMap, By.id("boGroup_effdate"));

    public static final Button btn_Soeg_FordringsID = new Button(Frames.uiMap, By.id("boGroup_obligationId_search"));

    public static final Input input_FordringsID = new Input(Frames.uiMap, By.id("boGroup_obligationId"));

    public static final Dropdown drp_TilbagesendRelateredeFordringer = new Dropdown(Frames.uiMap,
            By.id("boGroup_sendBackRelated"));

    public static final Input input_Note = new Input(Frames.uiMap, By.id("boGroup_sendBackNote"));

    public static final Button btn_Gem = new Button(Frames.uiMap,
            By.xpath("/html/body/table[2]/tbody/tr[13]/td/table/tbody/tr/td/input[1]"));

    public static final Button btn_Afslut = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));

    public static final PageElement txt_Status = new PageElement(Frames.zoneMapFrame_2, By.id("boStatus"));

}
