package modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import icisel.pageobjects.PO_Navigation;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.PageElement;
import icisel.utils.TO_Tools;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Module class of useful waiting methods.
 * 
 * @author mschioeler created on 06-08-2017
 *
 */
public class MO_Wait {

    /**
     * Replaces icisel.utils.driver.patient.Patient.ventTilHoejreMenuErLoadet();
     */
    public static void waitForRightMenu() {
        int timeout = (int) (Engine.getDriver().getTimeout() / 1000);
        PO_Navigation.btn_OpgaveoversigtOpdater.waitForElementToBeClickable(timeout);
    }

    public static ExpectedCondition<Boolean> inputHasVisibleText(final Input element) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver arg0) {
                return element.getInputText().length() > 0;
            }
        };
    }
    
    

    public static ExpectedCondition<Boolean> textEquals(final PageElement element, final String text) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver arg0) {
                return element.getText().equals(text);
            }
        };
    }

    public static ExpectedCondition<Boolean> windowPopsUpOnClick(final PageElement element) {
        return new ExpectedCondition<Boolean>() {
            PatientWebDriver driver2 = Engine.getDriver();
            int pollPeriod = (int) driver2.getPollPeriod();
            int initialNumWindows = driver2.getWindowHandles().size();

            @Override
            public Boolean apply(WebDriver arg0) {
                element.click();
                TO_Tools.sleep(pollPeriod);
                return driver2.getWindowHandles().size() > initialNumWindows;
            }
        };
    }

    public static ExpectedCondition<WebElement> presenceOfElement(final PageElement ele) {
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver input) {
                // TODO: frame switch part og findElement(PageElement), i.e. goTo(frame),
                // is still patie
                return Engine.getDriver().findElement(ele);
            }
        };
    }

}
