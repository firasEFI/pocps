package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.frames.Frames;

public class PO_Note {

    public static final Input txt_Titel = new Input(Frames.zoneMapFrame_1, By.id("title"));

    public static final Input txt_Beskrivelse = new Input(Frames.zoneMapFrame_1, By.id("text"));

    public static final Link lnk_Skyldner = new Link(Frames.zoneMapFrame_1,
            By.xpath("/html/body/div[2]/div[2]/div[1]/div/table/tbody/tr/td[1]/a"));

    public static final Input txt_SkylderID = new Input(Frames.zoneMapFrame_1,
            By.xpath("/html/body/div[4]/div[1]/table/tbody/tr/td[3]"));

}
