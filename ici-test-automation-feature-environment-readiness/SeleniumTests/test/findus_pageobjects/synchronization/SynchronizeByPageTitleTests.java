package findus_pageobjects.synchronization;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import icisel.utils.driver.patient.PatientWebDriver;

public class SynchronizeByPageTitleTests {

    @Test(groups = "unit", expectedExceptions = IllegalArgumentException.class)
    public void instantiate_LessThanLowerBoundTimeoutInSeconds_ShoudlThrow() {
        SynchronizeByPageTitle sync = new SynchronizeByPageTitle("Title", -1);
    }

    @Test(groups = "unit", expectedExceptions = IllegalArgumentException.class)
    public void instantiate_MoreThanLowerBoundTimeoutInSeconds_ShoudlThrow() {
        SynchronizeByPageTitle sync = new SynchronizeByPageTitle("Title", 601);
    }

    @Test(groups = "unit", expectedExceptions = IllegalArgumentException.class)
    public void instantiate_WithNullTitle_ShoudlThrow() {
        SynchronizeByPageTitle sync = new SynchronizeByPageTitle(null, 10);
    }

    @Test(groups = "unit")
    public void synchronize_LowerBoundTimeoutReturnsCorrectTitleAtFirstRequest_ShouldReturnTrue() {
        String pageTitle = "CorrectTitle";

        PatientWebDriver webDriverMock = mock(PatientWebDriver.class);

        when(webDriverMock.switchTo()).thenReturn(mock(WebDriver.TargetLocator.class));
        when(webDriverMock.getTitle()).thenReturn(pageTitle);

        SynchronizeByPageTitle sync = new SynchronizeByPageTitle(pageTitle, 0);
        boolean isSynced = sync.isSynchronized(webDriverMock);

        Assert.assertTrue(isSynced);
    }

    @Test(groups = "unit")
    public void syncronize_UpperBoundTimeoutReturnsCorrectTitleWithinTimeLimit_ShouldReturnTrue() {
        Calendar timeout = Calendar.getInstance();
        timeout.add(Calendar.SECOND, 1);
        String pageTitle = "CorrectTitle";

        PatientWebDriver webDriverMock = mock(PatientWebDriver.class);

        Answer<String> answerCorrectTitleNearTimeout = new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                Calendar now = Calendar.getInstance();

                if (now.after(timeout))
                    return pageTitle;
                else
                    return "WrongTitle";
            }
        };

        when(webDriverMock.switchTo()).thenReturn(mock(WebDriver.TargetLocator.class));
        when(webDriverMock.getTitle()).thenAnswer(answerCorrectTitleNearTimeout);

        SynchronizeByPageTitle sync = new SynchronizeByPageTitle(pageTitle, 2);
        boolean isSynced = sync.isSynchronized(webDriverMock);

        Assert.assertTrue(isSynced);
    }

    @Test(groups = "unit")
    public void synchronize_ExceedingTimout_ShouldReturnFalse() {
        String pageTitle = "CorrectTitle";

        PatientWebDriver webDriverMock = mock(PatientWebDriver.class);

        when(webDriverMock.switchTo()).thenReturn(mock(WebDriver.TargetLocator.class));
        when(webDriverMock.getTitle()).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                return "WrongTitle";
            }
        });

        SynchronizeByPageTitle sync = new SynchronizeByPageTitle(pageTitle, 0);
        boolean isSynced = sync.isSynchronized(webDriverMock);

        Assert.assertFalse(isSynced);
    }
}
