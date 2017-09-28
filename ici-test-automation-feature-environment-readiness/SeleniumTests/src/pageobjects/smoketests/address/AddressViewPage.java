package pageobjects.smoketests.address;

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
 * Created by asol on 31-05-2017.
 */
public class AddressViewPage {

    private WebDriver driver;

    public AddressViewPage(WebDriver driver) {

        this.driver = driver;

        Navi.zoneMapFrame_1(driver);

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                //boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1;// && b2;
            }
        });
    }

    /**
     * Not sure if below methods should be public.
     */
    public WebElement activeField(){

        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("status"));

    }

    public WebElement addressField1(){

        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("address1"));

    }

    public WebElement addressField2(){

        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("address2"));

    }

    public WebElement cityField(){

        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("city"));

    }

    public WebElement postalField(){

        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("postal"));

    }

    public void verifyFields(final String status, final String address1, final String city, final String postal){

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = activeField().getText().equals(status);
                boolean b2 = addressField1().getText().equals(address1);
                boolean b3 = cityField().getText().equals(city);
                boolean b4 = postalField().getText().equals(postal);
                return b1 && b2 && b3 && b4;
            }
        });

    }

}
