package pageobjects.smoketests.processflows;

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
 * Created by asol on 11-07-2017.
 */
public class HearingCasePage {

    private WebDriver driver;

    public HearingCasePage(WebDriver driver) {
        System.out.println("Contructor entered");
        this.driver = driver;

        Navi.zoneMapFrame_2(driver);

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });
    }

    public void clickApprove() {
        Navi.waitForDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.id("TRANSITION_1"));

        Navi.patientlyClick(driver, By.id("TRANSITION_1"));
    }

    public WebElement status() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.id("boStatus"));
    }

}
