package pageobjects.smoketests._360degreeview;

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
 * Created by msl on 24-05-2017.
 */

public class _360AddressPage {

    private WebDriver driver;

    public _360AddressPage(WebDriver driver) {
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

    public WebElement addressField(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("address1"));

    }

    public WebElement cityField(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("city"));

    }

    public WebElement postalField(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("postal"));

    }

    public WebElement refreshSearch(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("anTLZ1Refresh"));

    }

    public void search(String addressQuery, String cityQuery, String postalQuery) {
        addressField().sendKeys(addressQuery);
        cityField().sendKeys(cityQuery);
        postalField().sendKeys(postalQuery);
        refreshSearch().click();
    }
}
