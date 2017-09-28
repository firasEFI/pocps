package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.frames.Frames;

public class PO_Logout {

    public static final Button btn_ProfilBrugernavn = new Button(Frames.main, By.id("youAreLoggedInAsSpan"));

    public static final Button btn_LogUd = new Button(Frames.main, By.id("Log ud"));
}
