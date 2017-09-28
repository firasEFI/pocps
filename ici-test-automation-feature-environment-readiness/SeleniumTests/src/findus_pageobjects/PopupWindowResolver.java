package findus_pageobjects;

import icisel.utils.driver.Engine;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.*;

public class PopupWindowResolver<TPopupWindow extends PopupWindow> {

    private WebDriver driver;
    private Set<String> windowHandles;

    /**
     * Instantiate before invoking action that opens new popup.
     * @param driver
     */
    public PopupWindowResolver(WebDriver driver) {
        if(driver == null)
            throw new IllegalArgumentException("driver");

        this.driver = driver;

        this.updateWindowHandles();
    }

    public boolean checkForNewWindowAndSwitch() {
        int cachedWindowHandlesCount = windowHandles.size();
        int currentWindowsHandlesCount = this.driver.getWindowHandles().size();

        if(cachedWindowHandlesCount != currentWindowsHandlesCount)
            this.updateWindowHandles();

        if(cachedWindowHandlesCount < currentWindowsHandlesCount) {
            swithToLatestOpenedWindow();

            return true;
        } else
            return false;
    }

    public boolean checkForClosedWindowAndSwitch() {
        int cachedWindowHandlesCount = windowHandles.size();
        int currentWindowsHandlesCount = this.driver.getWindowHandles().size();

        if(cachedWindowHandlesCount != currentWindowsHandlesCount)
            this.updateWindowHandles();

        if(cachedWindowHandlesCount > currentWindowsHandlesCount) {
            swithToLatestOpenedWindow();

            return true;
        } else
            return false;
    }

    public TPopupWindow waitForPopup(GetPopupWindow<TPopupWindow> getWindow) {
        return this.waitForPopup(getWindow, 10000);
    }

    public TPopupWindow waitForPopup(GetPopupWindow<TPopupWindow> getWindow, int timeoutMilliseconds) {
        if(getWindow == null)
            throw new IllegalArgumentException("getWindow cannot be null");
        if (timeoutMilliseconds < 100 || timeoutMilliseconds > 60000)
            throw new IllegalArgumentException("timeoutMilliseconds must be between 100 and 60000");

        LocalDateTime timeout = LocalDateTime.now().plus(timeoutMilliseconds, ChronoField.MILLI_OF_DAY.getBaseUnit());

        do {
            if(this.checkForNewWindowAndSwitch())
                return getWindow.GetWindow();
        } while(timeout.isBefore(LocalDateTime.now()));

        throw new TimeoutException("No new windows were opened within the time limit");
    }

    public void waitForPopupToClose() {
        this.waitForPopupToClose(10000);
    }

    public void waitForPopupToClose(int timeoutMilliseconds) {
        if (timeoutMilliseconds < 100 || timeoutMilliseconds > 60000)
            throw new IllegalArgumentException("timeoutMilliseconds must be between 100 and 60000");
        if(this.windowHandles.size() == 1)
            throw new IllegalStateException("Only 1 window handle cached. This could be because there is only 1 window open, or a window was opened after the last caching of window handles. In this case try calling updateWindowHandles() before making this call");

        LocalDateTime timeout = LocalDateTime.now().plus(timeoutMilliseconds, ChronoField.MILLI_OF_DAY.getBaseUnit());

        do {
            if(this.checkForClosedWindowAndSwitch())
                return;
        } while(timeout.isBefore(LocalDateTime.now()));

        throw new TimeoutException("No windows were closed within the time limit");
    }

    private <T> T getLastItem(Collection<T> coll) {
        if(coll == null)
            throw new IllegalArgumentException("coll cannot be null");

        Iterator<T> iterator = coll.iterator();

        T last = null;

        while (iterator.hasNext())
            last = iterator.next();

        return last;
    }

    private void updateWindowHandles() {
        this.windowHandles = driver.getWindowHandles();
    }

    private void swithToLatestOpenedWindow() {
        this.driver.switchTo().window(getLastItem(this.windowHandles));
    }

    public interface GetPopupWindow<TPopupWindow> {
        TPopupWindow GetWindow();
    }
}
