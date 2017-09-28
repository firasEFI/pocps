package pageobjects.smoketests._360degreeview;

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
 * Created by msl on 24-05-2017.
 */
public class _360TabPage {

    private WebDriver driver;

    public static final String DROP_PERSON = "DK-360QRYQ1";     // Debtors and Claimants< DK-360QRYQ1
    public static final String DROP_CASE = "DK-360QRYQ6";       // Case DK-360QRYQ6
    public static final String DROP_AGENT = "DK-360QRYQ7";      // Agents DK-360QRYQ7
    public static final String DROP_DOCUMENT = "DK-360QRYQ8";   // Document DK-360QRYQ8
    public static final String DROP_CLAIM = "DK-360QRYQ9";      // Claim DK-360QRYQ7

    public _360TabPage(WebDriver driver) {
        this.driver = driver;

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });

        Navi.tabPage(driver);

    }

    public void selectDropdown(String selection) {

        Select dropdown = Navi.patientlySelectDisplayedElement(FrameType.TAB_PAGE, driver, By.id("multiQueryZoneFilters1"));
        dropdown.selectByValue(selection);
    }
}
