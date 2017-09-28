package pageobjects.smoketests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

public class LoginPage {
    private static WebElement element = null;

    public static WebElement txtboxUserID(WebDriver driver){

        /*(new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("userId")).isDisplayed();
            }
        });


        element = driver.findElement(By.id("userId"));
        return element;*/
        return Navi.patientlyFindDisplayedElement(FrameType.DEFAULT_CONTENT, driver, By.id("userId"));

    }

    public static WebElement txtboxPassword(WebDriver driver){

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("password")).isDisplayed();
            }
        });


        element = driver.findElement(By.id("password"));
        return element;
    }

    public static WebElement btnLogin(WebDriver driver){

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("loginButton")).isDisplayed();
            }
        });


        element = driver.findElement(By.id("loginButton"));
        return element;
    }

    public static WebElement lnkLoginSSOLink(WebDriver driver){

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("superlink")).isDisplayed();
            }
        });


        element = driver.findElement(By.id("superlink"));
        return element;
    }

    public static WebElement drpUserSelector(WebDriver driver){

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("userPicker")).isDisplayed();
            }
        });


        element = driver.findElement(By.id("userPicker"));
        return element;
    }
}
