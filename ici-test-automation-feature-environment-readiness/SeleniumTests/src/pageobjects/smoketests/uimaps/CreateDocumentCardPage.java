package pageobjects.smoketests.uimaps;

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
 * Created by asol on 02-06-2017.
 */
public class CreateDocumentCardPage {

    private WebDriver driver;

    public CreateDocumentCardPage(WebDriver driver) {

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

    public WebElement journalisingId(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_journalisingId"));

    }

    public WebElement case0(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("case_0"));

    }

    public WebElement obligation0(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("obligation_0"));

    }

    public void clickSave(){

        Navi.patientlyClick(driver, By.id("SAVE_BTN_MP"));

    }

}
