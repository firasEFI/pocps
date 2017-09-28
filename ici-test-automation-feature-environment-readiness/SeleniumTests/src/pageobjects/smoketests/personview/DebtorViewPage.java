package pageobjects.smoketests.personview;

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
 * Created by asol on 30-05-2017.
 */
public class DebtorViewPage {

    private WebDriver driver;

    public DebtorViewPage(WebDriver driver) {

        this.driver = driver;

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });

        Navi.zoneMapFrame_1(driver);

    }

    public WebElement personId(){

        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1,
                driver, By.id("debtorId"));

    }

    public WebElement firstNameBox(){

        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1,
                driver, By.xpath("//td[@class='oraNormal oraDisplayCell oraDefault' and @orafield='firstName' and @oraerrorelement='firstName']"));

    }

    public WebElement lastNameBox(){

        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1,
                driver, By.xpath("//td[@class='oraNormal oraDisplayCell oraDefault' and @orafield='lastName']"));

    }

    public WebElement idNumberBox(){

        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1,
                driver, By.xpath("//td[@class='oraNormal oraDisplayCell oraDefault' and @orafield='personIdNumber' and @oraerrorelement='personIdNumber']"));

    }

}
