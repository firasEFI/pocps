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
 * Created by asol on 29-05-2017.
 */
public class _360FinancialInformationPage {

    private WebDriver driver;

    public _360FinancialInformationPage(WebDriver driver) {
        this.driver = driver;

        driver.switchTo().defaultContent();

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(this.driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });
    }

    public WebElement personId(){

        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.xpath("//span[@orafield='commonBalanceDetails/personId']"));

    }

    public WebElement financialHistoryZone(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("zoneHeader3"));

    }

    public void personIdContains(final String personFullName) {
        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return personId().getText().toLowerCase().contains(personFullName.toLowerCase());
            }
        });
    }
}
