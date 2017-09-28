package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_TimeTravel {

    public static final PageElement txt_UserID = new PageElement(Frames.defaultContent, By.id("USER_ID"));

    public static final Button btn_Soeg = new Button(Frames.defaultContent, By.id("IM_mainSrch_userMnSrch"));

    public static final Button btn_KarakteregenskaberFane = new Button(Frames.tabMenu,
            By.xpath("/html/body/table/tbody/tr/td[8]/table/tbody/tr[2]/td"));

    public static final Dropdown drp_Karakteregenskabstype = new Dropdown(Frames.USER_CHAR_GRID,
            By.id("USER_CHAR:0$CHAR_TYPE_CD"));

    public static final PageElement txt_Raekkefoelge = new PageElement(Frames.USER_CHAR_GRID,
            By.id("USER_CHAR:0$SEQ_NUM"));

    public static final PageElement txt_KarakteregenskabVaerdi = new PageElement(Frames.USER_CHAR_GRID,
            By.id("USER_CHAR:0$CHAR_VAL"));
}
