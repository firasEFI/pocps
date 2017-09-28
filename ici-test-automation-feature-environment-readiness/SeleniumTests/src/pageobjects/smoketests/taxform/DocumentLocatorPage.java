package pageobjects.smoketests.taxform;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by asol on 10-07-2017.
 */
public class DocumentLocatorPage {

    private WebDriver driver;

    public DocumentLocatorPage(WebDriver driver) {

        this.driver = driver;

        Navi.tabPage(driver);

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });
    }

    public Select multiQueryZoneFilters(){

        return Navi.patientlySelectDisplayedElement(FrameType.TAB_PAGE, driver, By.id("multiQueryZoneFilters1"));

    }

    public WebElement filter(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("filter1.F1"));

    }

    public void clickSearch(){

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.id("anTLZ1Refresh"), FrameType.TAB_PAGE, By.id("dataExplorerDisplay1"));

    }

}
