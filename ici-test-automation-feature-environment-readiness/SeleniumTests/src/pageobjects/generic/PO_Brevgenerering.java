package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;

public class PO_Brevgenerering {

    public static final Dropdown drp_Inddrivelsesskridt = new Dropdown(Frames.uiMap, By.id("contactClass"));

    public static final Dropdown drp_Kundekontakttype = new Dropdown(Frames.uiMap, By.id("contactType"));

    public static final Button btn_OK = new Button(Frames.uiMap, By.id("ok"));

    public static final Input txt_LetterHeader = new Input(Frames.uiMap, By.id("boGroup_Fritekst1"));

    public static final Input txt_JournalNumber = new Input(Frames.uiMap, By.id("boGroup_Fritekst2"));

    public static final Input txt_LetterText = new Input(Frames.uiMap, By.id("boGroup_Fritekst3"));

    public static final Button btn_Gem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));

}
