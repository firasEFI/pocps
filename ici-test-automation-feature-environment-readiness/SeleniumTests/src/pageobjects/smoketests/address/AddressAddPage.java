package pageobjects.smoketests.address;

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
 * Created by asol on 01-06-2017.
 */
public class AddressAddPage {

    private WebDriver driver;

    public AddressAddPage(WebDriver driver) {

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

    /**
     * Not sure if below methods should be public.
     */
    public Select status(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_status"));

    }

    public Select country(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_country"));
    }

    public WebElement postal(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_postal"));

    }

    public WebElement address1(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_address1"));

    }

    public WebElement address2(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_address2"));

    }

    public WebElement address3(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_address3"));

    }

    public WebElement address4(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_address4"));

    }

    public WebElement city(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_city"));

    }

    public WebElement municipalCode(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_cmMunicipalCode"));

    }

    public void clickSave(Boolean errorIsExpected){

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("SAVE_BTN_MP")).isDisplayed();
            }
        });

        if (errorIsExpected) {
            Navi.patientlyClickAttempt(driver, FrameType.UI_MAP, By.id("SAVE_BTN_MP"), FrameType.UI_MAP, By.id("ERRMSG-TEXT-SPAN"));
        } else {
            Navi.patientlyClickAttempt(driver, FrameType.UI_MAP, By.id("SAVE_BTN_MP"), FrameType.ZONE_MAP_FRAME_1, By.id("address1"));
        }
    }

    public void printErrorMessage() {
        System.out.println(driver.findElement(By.id("ERRMSG-TEXT-SPAN")).getText());
    }
}
