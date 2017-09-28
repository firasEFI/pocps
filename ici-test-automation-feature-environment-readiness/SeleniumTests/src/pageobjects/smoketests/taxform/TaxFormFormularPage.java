package pageobjects.smoketests.taxform;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by asol on 11-07-2017.
 */
public class TaxFormFormularPage {

    private WebDriver driver;

    public TaxFormFormularPage(WebDriver driver) {

        this.driver = driver;

        Navi.tabMenu(driver);

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });
    }

    public void clickPrimaryFrame(final WebDriver driver) {

        //waitForEnabledElement(FrameType.TAB_MENU, driver, By.id("C1360TXR_T_LBL"));
        (new WebDriverWait(driver, Navi.LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return Navi.findElementAtTabMenu(d, By.xpath("/html/body/table/tbody/tr/td[1]/table/tbody/tr[2]/td")).isDisplayed();
            }
        });

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<5) {

            try {
                Navi.patientlyClickAttempt(driver, FrameType.TAB_MENU, By.xpath("/html/body/table/tbody/tr/td[1]/table/tbody/tr[2]/td"), FrameType.ZONE_MAP_FRAME_4, By.id("documentLocator"));

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            }

        }

    }

    public void clickError(final WebDriver driver) {

        //waitForEnabledElement(FrameType.TAB_MENU, driver, By.id("C1360TXR_T_LBL"));
        (new WebDriverWait(driver, Navi.LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return Navi.findElementAtTabMenu(d, By.xpath("/html/body/table/tbody/tr/td[2]/table/tbody/tr[2]/td")).isDisplayed();
            }
        });

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<5) {

            try {
                Navi.patientlyClickAttempt(driver, FrameType.TAB_MENU, By.xpath("/html/body/table/tbody/tr/td[2]/table/tbody/tr[2]/td"), FrameType.TAB_PAGE, By.id("filter1.F1"));

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            }

        }

    }

    public void clickVersions(final WebDriver driver) {

        //waitForEnabledElement(FrameType.TAB_MENU, driver, By.id("C1360TXR_T_LBL"));
        (new WebDriverWait(driver, Navi.LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return Navi.findElementAtTabMenu(d, By.xpath("/html/body/table/tbody/tr/td[3]/table/tbody/tr[2]/td")).isDisplayed();
            }
        });

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<5) {

            try {
                Navi.patientlyClickAttempt(driver, FrameType.TAB_MENU, By.xpath("/html/body/table/tbody/tr/td[3]/table/tbody/tr[2]/td"), FrameType.TAB_PAGE, By.id("filter1.F1"));

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            }

        }

    }

    public void clickLog(final WebDriver driver) {

        //waitForEnabledElement(FrameType.TAB_MENU, driver, By.id("C1360TXR_T_LBL"));
        (new WebDriverWait(driver, Navi.LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return Navi.findElementAtTabMenu(d, By.xpath("/html/body/table/tbody/tr/td[4]/table/tbody/tr[2]/td")).isDisplayed();
            }
        });

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<5) {

            try {
                Navi.patientlyClickAttempt(driver, FrameType.TAB_MENU, By.xpath("/html/body/table/tbody/tr/td[4]/table/tbody/tr[2]/td"), FrameType.TAB_PAGE, By.id("dataExplorerTableBody1"));

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            }

        }

    }

}
