package pageobjects.smoketests.uimaps;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by asol on 18-07-2017.
 */
public class SelectAddAgentPage {

    private WebDriver driver;

    public SelectAddAgentPage(WebDriver driver) {

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

    public Select agentType() {

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("input_agentType"));

    }

    public void clickNextPartWithCPR() {

        Navi.patientlyClickAttempt(driver, FrameType.UI_MAP,
                By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr/td/input[2]"), FrameType.UI_MAP, By.id("cprId"));

    }

}
