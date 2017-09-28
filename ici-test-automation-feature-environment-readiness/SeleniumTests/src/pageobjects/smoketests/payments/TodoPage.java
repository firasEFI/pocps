package pageobjects.smoketests.payments;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by msl on 12-07-2017.
 */
public class TodoPage {

    /*
     * Public fields for ICI-Sel based Selenium tests.
     */
    public PageElement opgavetype = new PageElement(Frames.tabPage, By.id("TD_TYPE_CD"));
    public PageElement rolle = new PageElement(Frames.tabPage, By.id("ROLE_ID"));
    public PageElement linkTilDetaljer = new PageElement(Frames.tabPage, By.id("FULL_MSG"));
    public PageElement status = new PageElement(Frames.tabPage, By.id("ENTRY_STATUS_FLG"));
    public PageElement beskrivelse = new PageElement(Frames.tabPage, By.id("COMMENTS"));

    private WebDriver driver;

    public TodoPage(WebDriver driver) {
        this.driver = driver;

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });

        Navi.tabPage(driver);
    }

    public WebElement todoTypeID() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("TD_TYPE_CD"));
    }
    public WebElement role() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("ROLE_ID"));
    }
    public WebElement comments() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("COMMENTS"));
    }

    public void clickLinkForDetails() {
        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.id("FULL_MSG"), FrameType.TAB_PAGE, By.id("EXT_SOURCE_ID"));
    }
}
