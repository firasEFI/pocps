package utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.mockito.Mockito;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.Test;

import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;

public class ClickingMakesWindowAppearAndThenSwitchToItTest {
    int timesButtonClicked = 0;


    @Test(groups = "unit")
    public void testSwitchesToCorrectWindow() {
        PatientWebDriver driver = mock(PatientWebDriver.class);
        Engine.setDriver(driver);

        TargetLocator targetLocator = mock(TargetLocator.class);

        when(driver.getWindowHandle()).thenReturn("A");
        WebElement element = mock(WebElement.class);
        when(driver.switchTo()).thenReturn(targetLocator);

        Set<String> mockedHandles = new HashSet<String>();
        mockedHandles.add("A");
        mockedHandles.add("B");
        when(driver.getWindowHandles()).thenReturn(mockedHandles);

        ExpectedCondition<String> cond = IciSelExpectedConditions.clickingMakesWindowAppearAndThenSwitchToIt(element);

        cond.apply(driver);
        verify(element, times(1)).click();
        cond.apply(driver);
        verify(element,times(2)).click();
        // new window opens
        
        Set<String> mockedHandles2 = new HashSet<String>();
        mockedHandles2.addAll(mockedHandles);
        mockedHandles2.add("C");
        when(driver.getWindowHandles()).thenReturn(mockedHandles2);
        cond.apply(driver);
        
        // Make sure window was switched to
        Mockito.verify(targetLocator).window("C");
        Mockito.verify(element, Mockito.times(3)).click();

        Engine.closeDriver();


    }
}
