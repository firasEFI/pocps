package pageobjects.smoketests._360degreeview;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by asol on 26-05-2017.
 */
public class _360PersonDetailsAddNotePage {

    private WebDriver driver;

    public static final String INTERNAL = "INTE";
    public static final String OTHER = "OTHE";
    public static final String PHONE = "PHON";
    public static final String SYSTEM = "SYS";
    public static final String OTHER_NOTE = "Other";

    public _360PersonDetailsAddNotePage(WebDriver driver) {
        this.driver = driver;

        try {
            final JavascriptExecutor executor = (JavascriptExecutor) driver;

            (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                    boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                    return b1 && b2;
                }
            });

            Navi.uiMap(driver);
        }catch(WebDriverException e){
            Navi.uiMap(driver);
        }
    }

    public  Select categoryInput() {
        /*(new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("uiMap")
                        .findElements(By.id("boGroup_category")).size() > 0;
            }
        });

        return new Select(driver.findElement(By.id("boGroup_category")));*/

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_category"));

    }

    public WebElement titleInput() {

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_title"));

    }

    public WebElement textAreaInput() {

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.xpath("//textarea[@id='boGroup_text']"));

    }

    public WebElement saveInput() {

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.xpath("SAVE_BTN_MP"));

    }
}

