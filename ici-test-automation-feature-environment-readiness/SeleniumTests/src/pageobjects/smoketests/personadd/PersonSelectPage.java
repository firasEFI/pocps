package pageobjects.smoketests.personadd;

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
 * Created by asol on 01-06-2017.
 */
public class PersonSelectPage {

    public final String AGENT = "DK-AGENT";
    public final String CLAIMANT = "DK-CLAIMANT";
    public final String INTERNAL = "DK-INTERNAL";
    public final String PERSON = "DK-PERSON";

    private WebDriver driver;

    public PersonSelectPage(WebDriver driver) {

        this.driver = driver;

        Navi.uiMap(driver);

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });
    }

    public Select personType(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("type"));

    }

    public void clickOk(){

        Navi.waitForDisplayedElement(FrameType.UI_MAP, driver, By.xpath("//input[@value='OK']"));

        Navi.patientlyClick(driver, By.xpath("//input[@value='OK']"));

    }

}
