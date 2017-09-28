package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.frames.Frames;

public class PO_Login_Side {

    public static final Input txtbox_BrugerKode = new Input(Frames.defaultContent, By.id("userId"));

    public static final Input txtbox_password = new Input(Frames.defaultContent, By.id("password"));

    public static final Button btn_Login = new Button(Frames.defaultContent, By.id("loginButton"));

    public static final Link lnk_Login_SSO_link = new Link(Frames.defaultContent, By.id("superlink"));

    public static final Dropdown drp_User_Selector = new Dropdown(Frames.defaultContent, By.id("userPicker"));
}
