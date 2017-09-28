package findus_pageobjects;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class PopupWindowResolverTests {

    private WebDriver driverMock;
    private WebDriver.TargetLocator targetLocatorMock;

    @BeforeMethod
    public void MethodSetup() {
        this.driverMock = mock(WebDriver.class);
        this.targetLocatorMock = mock(WebDriver.TargetLocator.class);

        when(this.driverMock.switchTo()).thenReturn(this.targetLocatorMock);
    }

    @Test(groups = "unit", expectedExceptions = IllegalArgumentException.class)
    public void instantiate_WithNullWebDriver_ShouldThrow() {
        PopupWindowResolver<PopupWindow> resolver = new PopupWindowResolver<PopupWindow>(null);
    }

    @Test(groups = "unit")
    public void checkForNewWindowAndSwitch_NewWindowOpened_ShouldReturnTrueAndSwitchToNewWindow() {
        Set<String> windowHandles = new HashSet<>();
        windowHandles.add("windowHandle1");

        when(this.driverMock.getWindowHandles()).thenAnswer(new Answer<Set<String>>() {
            @Override
            public Set<String> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new HashSet<>(windowHandles);
            }
        });

        PopupWindowResolver<PopupWindow> resolver = new PopupWindowResolver<PopupWindow>(this.driverMock);

        windowHandles.add("windowHandle2");

        Assert.assertTrue(resolver.checkForNewWindowAndSwitch());
        verify(this.targetLocatorMock, times(1)).window("windowHandle2");
    }

    @Test(groups = "unit")
    public void checkForNewWindowAndSwitch_NewWindowNotOpened_ShouldReturnFalseAndNotSwitch() {
        Set<String> windowHandles = new HashSet<>();
        windowHandles.add("windowHandle1");

        when(this.driverMock.getWindowHandles()).thenReturn(windowHandles);

        PopupWindowResolver<PopupWindow> resolver = new PopupWindowResolver<PopupWindow>(this.driverMock);

        Assert.assertFalse(resolver.checkForNewWindowAndSwitch());
        verify(this.targetLocatorMock, never()).window(anyString());
    }

    @Test(groups = "unit")
    public void checkForClosedWindowAndSwitch_WindowClosed_ShouldReturnTrueAndSwitch() {
        Set<String> windowHandles = new HashSet<>();
        windowHandles.add("windowHandle1");
        windowHandles.add("windowHandle2");

        when(this.driverMock.getWindowHandles()).thenAnswer(new Answer<Set<String>>() {
            @Override
            public Set<String> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new HashSet<>(windowHandles);
            }
        });

        PopupWindowResolver<PopupWindow> resolver = new PopupWindowResolver<PopupWindow>(this.driverMock);

        windowHandles.remove("windowHandle2");

        Assert.assertTrue(resolver.checkForClosedWindowAndSwitch());
        verify(this.targetLocatorMock, times(1)).window("windowHandle1");
    }

    @Test(groups = "unit")
    public void checkForClosedWindowAndSwitch_NewWindowNotOpened_ShouldReturnFalseAndNotSwitch() {
        Set<String> windowHandles = new HashSet<>();
        windowHandles.add("windowHandle1");
        windowHandles.add("windowHandle2");

        when(this.driverMock.getWindowHandles()).thenReturn(windowHandles);

        PopupWindowResolver<PopupWindow> resolver = new PopupWindowResolver<PopupWindow>(this.driverMock);

        Assert.assertFalse(resolver.checkForNewWindowAndSwitch());
        verify(this.targetLocatorMock, never()).window(anyString());
    }

    @Test(groups = "unit")
    public void waitForPopup_NewWindowOpenedInstantly_ShouldReturnPageObject() {
        Set<String> windowHandles = new HashSet<>();
        windowHandles.add("windowHandle1");

        when(this.driverMock.getWindowHandles()).thenAnswer(new Answer<Set<String>>() {
            @Override
            public Set<String> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new HashSet<>(windowHandles);
            }
        });

        PopupWindowResolver<PopupWindow> resolver = new PopupWindowResolver<PopupWindow>(this.driverMock);

        windowHandles.add("windowHandle2");

        PopupWindowResolver.GetPopupWindow<PopupWindow> getWindowMock = mock(PopupWindowResolver.GetPopupWindow.class);

        resolver.waitForPopup(getWindowMock);

        verify(getWindowMock, times(1)).GetWindow();
    }

    @Test(groups = "unit")
    public void waitForPopupToClose_WindowClosedInstantly_ShouldSwitch() {
        Set<String> windowHandles = new HashSet<>();
        windowHandles.add("windowHandle1");
        windowHandles.add("windowHandle2");

        when(this.driverMock.getWindowHandles()).thenAnswer(new Answer<Set<String>>() {
            @Override
            public Set<String> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new HashSet<>(windowHandles);
            }
        });

        PopupWindowResolver<PopupWindow> resolver = new PopupWindowResolver<PopupWindow>(this.driverMock);

        windowHandles.remove("windowHandle2");

        resolver.waitForPopupToClose();
    }
}
