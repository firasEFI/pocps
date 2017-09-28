package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.frames.Frames;

public class PO_Indbetalingsvaerktoej {

    public static final Input input_file = new Input(Frames.defaultContent, By.id("file"));

    public static final Button btn_submit = new Button(Frames.defaultContent, By.id("submit"));

    
}
