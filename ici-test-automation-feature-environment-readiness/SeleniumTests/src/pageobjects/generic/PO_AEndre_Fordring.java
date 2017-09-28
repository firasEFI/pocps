package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_AEndre_Fordring {

    public static final Input input_Fordring = new Input(Frames.uiMap, By.id("obligationId"));

    public static final Input input_Justeringstype = new Input(Frames.uiMap, By.id("adjustmentType"));

    public static final Button btn_OK = new Button(Frames.uiMap, By.xpath("/html/body/div[2]/div/input[1]"));

    public static final Input input_Beloeb = new Input(Frames.uiMap, By.id("boGroup_adjustmentAmount"));

    public static final PageElement txt_KarakteregenskabVaerdi = new PageElement(Frames.uiMap,
            By.id("adhocCharValue_0"));

    public static final Button btn_Gem = new Button(Frames.uiMap, By.id("SAVE_BTN_FRG"));

    public static final Button btn_Generer = new Button(Frames.zoneMapFrame_1, By.id("OTHER_2"));

    public static final Input input_Virkningsdato = new Input(Frames.uiMap, By.id("bsGroup_input_arrearsDate"));

    public static final Button btn_Beregn = new Button(Frames.uiMap, By.xpath("/html/body/div[2]/div/input[1]"));

}
