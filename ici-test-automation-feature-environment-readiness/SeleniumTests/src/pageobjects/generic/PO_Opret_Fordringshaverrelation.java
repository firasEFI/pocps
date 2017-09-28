package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_Opret_Fordringshaverrelation {

    public static final Dropdown drp_Fordringshaver = new Dropdown(Frames.uiMap, By.id("taxType"));

    public static final Button btn_OK = new Button(Frames.uiMap, By.id("ok"));

    public static final Button btn_Gem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));

    public static final PageElement txt_Note = new PageElement(Frames.uiMap, By.id("ERRMSG-TEXT-SPAN"));

}
