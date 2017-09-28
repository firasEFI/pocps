package mocks;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frame;
import icisel.utils.driver.patient.PatientWebDriver;

public class PatientWebDriverMock implements PatientWebDriver {

    @Override
    public void get(String url) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getCurrentUrl() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPageSource() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public void quit() {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<String> getWindowHandles() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getWindowHandle() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TargetLocator switchTo() {
        // TODO Auto-generated method stub
        return new TargetLocator() {

            @Override
            public WebDriver window(String nameOrHandle) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public WebDriver parentFrame() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public WebDriver frame(WebElement frameElement) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public WebDriver frame(String nameOrId) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public WebDriver frame(int index) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public WebDriver defaultContent() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Alert alert() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public WebElement activeElement() {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }

    @Override
    public Navigation navigate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Options manage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCurrentFrame(Frame frame) {
        // TODO Auto-generated method stub

    }

    @Override
    public Frame getCurrentFrame() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WebElement findElementPatiently(By by) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WebElement findElementPatiently(PageElement po) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<WebElement> findElementsPatiently(By by) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<WebElement> findElementsPatiently(PageElement po) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WebElement findElementPatiently(By by, long timeout, long pollPeriod) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WebElement findElementPatiently(PageElement po, long timeout, long pollPeriod) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<WebElement> findElementsPatiently(By by, long timeout, long pollPeriod) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<WebElement> findElementsPatiently(PageElement po, long timeout, long pollPeriod) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WebElement findElement(PageElement po) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void goTo(Frame frame) {
        // TODO Auto-generated method stub

    }

    @Override
    public long getTimeout() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setTimeout(long timeout) {
        // TODO Auto-generated method stub

    }

    @Override
    public long getPollPeriod() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setPollPeriod(long pollPeriod) {
        // TODO Auto-generated method stub

    }

    @Override
    public WebDriverWait pause() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void acceptAlert() {
        // TODO Auto-generated method stub

    }

    @Override
    public void closeNonactiveWindows() {
        // TODO Auto-generated method stub

    }

}
