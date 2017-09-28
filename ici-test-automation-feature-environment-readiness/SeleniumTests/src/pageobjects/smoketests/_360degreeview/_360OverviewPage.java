package pageobjects.smoketests._360degreeview;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

/**
 * Created by msl on 14-07-2017.
 */
public class _360OverviewPage {
    public PageElement partsoplysninger = new PageElement(Frames.tabMenu, By.id("C1360VMW_T_LBL"));
    public PageElement fordringer = new PageElement(Frames.tabMenu, By.id("C1360ACCT_T_LBL"));
    public PageElement sagsbehandling = new PageElement(Frames.tabMenu, By.id("C1360TXR_T_LBL"));
    public PageElement konto = new PageElement(Frames.tabMenu, By.id("C1360FIN_T_LBL"));
}
