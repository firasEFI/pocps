package findus_pageobjects;

import com.google.common.base.Predicate;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.IFrame;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class Tab {

    PageElement tabElement;

    public Tab(IFrame locationOfElementFrame, String locateTabFromValue) {
        tabElement = new PageElement(locationOfElementFrame, By.xpath(createTabLocator(locateTabFromValue)));
    }

    public void ensureSelected() {
        FluentWait<WebDriver> waitForActiveTab = new FluentWait<WebDriver>(Engine.getDriver())
                .pollingEvery(250, TimeUnit.MILLISECONDS)
                .withTimeout(8000, TimeUnit.MILLISECONDS);

        waitForActiveTab
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(WebDriver input) {
                        if(!isSelected())
                            tabElement.click();

                        return isSelected();
                    }
                });
    }

    private String createTabLocator(String title) {
        return String.format("//tr[@id = 'tabRow']/td/table/tbody/tr/td[@role= 'tab' and text() = '%s']", title);
    }

    private boolean isSelected() {
        return tabElement.getAttribute("class").equals("activeTab");
    }
}
