package pageobjects.smoketests.reactivatedebtor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by asol on 01-06-2017.
 */
public class ReactivateDebtorPage {

    private WebDriver driver;

    public ReactivateDebtorPage(WebDriver driver) {

        this.driver = driver;

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });

        Navi.uiMap(driver);
    }

    public WebElement reactivationReason(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("ReactivationReason"));

    }

    public void clickReactivate(){

        //test using page is currently not working change the last by.id.

        Navi.patientlyClickAttempt(driver, FrameType.UI_MAP, By.xpath("//input[@oramdlabel='ACTV_BTN_LBL']"), FrameType.ZONE_MAP_FRAME_1, By.xpath("/html/body/div[2]/div[3]/div[1]/label"));

    }

}
