package utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ElementAttributeEqualsTest {

    /**
     * This tests that the expected condition correctly asserts that the element
     * has a specific value
     */
    @Test
    public void testTrue() {
        String expectedAttribute = "attribute";
        String expectedValue = "value";

        WebDriver driver = mock(WebDriver.class);

        WebElement element = mock(WebElement.class);
        when(element.getAttribute(expectedAttribute)).thenReturn(expectedValue);

        assert IciSelExpectedConditions.elementAttributeEquals(element, expectedAttribute, expectedValue).apply(driver);
    }

    /**
     * This tests that the expected condition correctly asserts that the element
     * does not have a specific value
     */
    @Test
    public void testFalse() {
        String expectedAttribute = "attribute";
        String expectedValue = "value";

        WebDriver driver = mock(WebDriver.class);

        WebElement element = mock(WebElement.class);
        when(element.getAttribute(expectedAttribute)).thenReturn("something unexpected");

        assert !IciSelExpectedConditions.elementAttributeEquals(element, expectedAttribute, expectedValue).apply(driver);
    }

    /**
     * This tests that the expected condition correctly asserts that the element
     * has null as its attribute value
     */
    @Test
    public void testTrueWhenExpectingNull() {
        String expectedAttribute = "attribute";

        WebDriver driver = mock(WebDriver.class);

        WebElement element = mock(WebElement.class);
        when(element.getAttribute(expectedAttribute)).thenReturn(null);

        assert IciSelExpectedConditions.elementAttributeEquals(element, expectedAttribute, null).apply(driver);
    }
}
