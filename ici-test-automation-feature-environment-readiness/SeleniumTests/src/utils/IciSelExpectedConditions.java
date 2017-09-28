package utils;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import icisel.pageobjects.elements.PageElement;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;

/**
 * Implementation of ExpectedCondition that waits until the element is not empty
 *
 */
public class IciSelExpectedConditions {

    /**
     * Clicks the specified {@code WebElement} until 
     * it makes the number of windows increase by exactly 1, 
     * then switches to this window.
     * @return the handle of the original window
     */
    public static ExpectedCondition<String> clickingMakesWindowAppearAndThenSwitchToIt(WebElement element) {
        return new ExpectedCondition<String>() {
            PatientWebDriver driver = Engine.getDriver();
            Collection<String> originalWindowHandles = driver.getWindowHandles();
            int originalNumWindows = originalWindowHandles.size();
            String originalWindowHandle = driver.getWindowHandle();
            
            @Override
            public String apply(WebDriver input) {
                element.click();
                if (driver.getWindowHandles().size() == originalNumWindows + 1) {
                    Collection<String> handles = driver.getWindowHandles();
                    // only leave the new window handles
                    handles.removeAll(originalWindowHandles);
                    // switch to the new window
                    driver.switchTo().window(handles.iterator().next());
                    return originalWindowHandle;
                } else {
                    // keep "waiting"
                    return null;
                }
            }
        };
        
    }

    public static ExpectedCondition<Boolean> ExpectedNotEmpty(final By locator) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = driver.findElement(locator).getAttribute("value");
                    return elementText != null && !"".equals(elementText.trim());
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }
        };
    }

    /**
     * 
     * @param element
     *            the element with text to consider
     * @param expectedStrings
     *            one or more strings that are accepted
     * @return if the element contains one of the expected strings
     */
    public static ExpectedCondition<Boolean> elementContainsTextIgnoreCase(final PageElement element,
            final String... expectedStrings) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver input) {
                String actualText = element.getText().toLowerCase();
                for (String expectedText : expectedStrings) {
                    if (actualText.contains(expectedText.toLowerCase())) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public static ExpectedCondition<Boolean> ExpectedAlertAccept() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    Alert alert = driver.switchTo().alert();
                    alert.accept();

                    return true;
                } catch (NoAlertPresentException e) {
                    return null;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> windowIsAvailableAndSwitchToIt(final String windowHandle) {
        return new ExpectedCondition<Boolean>() {
            PatientWebDriver driver = Engine.getDriver();

            @Override
            public Boolean apply(WebDriver input) {
                driver.switchTo().window(windowHandle);
                String handle = driver.getWindowHandle();
                return (handle != null && handle.equals(windowHandle));

            }

        };
    }

    public static ExpectedCondition<Boolean> windowIsNot(final String windowHandle) {
        return new ExpectedCondition<Boolean>() {
            PatientWebDriver driver = Engine.getDriver();

            @Override
            public Boolean apply(WebDriver input) {
                Set<String> handles = driver.getWindowHandles();
                for (String h : handles) {
                    if (areDifferentWindows(h, windowHandle)) {
                        driver.switchTo().window(h);

                        // verify that window has changed
                        String handle = driver.getWindowHandle();
                        return (areDifferentWindows(handle, windowHandle));
                    }
                }
                return false;

            }

            private Boolean areDifferentWindows(String found, String expectedNot) {
                return (found != null && !found.equals(expectedNot));
            }

        };
    }

    public static ExpectedCondition<Boolean> elementAttributeEquals(WebElement element, String attribute, String expectedValue) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver input) {
                return Objects.equals(element.getAttribute(attribute), expectedValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> elementIsDisabled(WebElement element) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver input) {
                return !element.isEnabled();
            }
        };
    }

}
