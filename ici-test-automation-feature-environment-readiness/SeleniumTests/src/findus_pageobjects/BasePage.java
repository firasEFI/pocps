package findus_pageobjects;

import org.openqa.selenium.NoAlertPresentException;

import findus_pageobjects.synchronization.PageSynchronizationException;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.utils.driver.Engine;

/**
 * Created by nielsjes on 17-08-2017.
 */
public abstract class BasePage {

    private Synchronizer synchronizer;

    public boolean isSynchronized() {
        return synchronizer.isSynchronized(Engine.getDriver());
    }

    protected BasePage(Synchronizer synchronizer) {
        if (synchronizer == null) {
            throw new IllegalArgumentException();
        }

        this.synchronizer = synchronizer;

        if (!this.isSynchronized())
            throw new PageSynchronizationException();
    }

    public BasePage acceptAlert() {
        try {
            Engine.getDriver().switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.err.println("There was no popup.");
        }

        return this;
    }
}
