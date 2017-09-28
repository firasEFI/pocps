package findus_pageobjects.synchronization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Predicate;

import icisel.utils.driver.patient.PatientWebDriver;

public abstract class PageSynchronizer implements Synchronizer {

    private int timeOutInSeconds;

    protected Collection<Class<? extends Throwable>> getIgnoredExeptions() {
        ArrayList<Class<? extends Throwable>> ignoredExceptions = new ArrayList<>();

        ignoredExceptions.add(NoSuchElementException.class);

        return ignoredExceptions;
    }

    protected PageSynchronizer(int timeOutInSeconds) {
        if (timeOutInSeconds < 0 || timeOutInSeconds > 600)
            throw new IllegalArgumentException("timeOutInSeconds must be between 0 and 600");

        this.timeOutInSeconds = timeOutInSeconds;
    }

    @Override
    public boolean isSynchronized(PatientWebDriver driver) {
        if (driver == null)
            throw new IllegalArgumentException("driver cannot be null");

        // TODO: er sat midlertidigt i skammekrogen
        // Alert alert = null;
        //
        // try {
        // alert = ExpectedConditions.alertIsPresent().apply(driver);
        // } catch (NoAlertPresentException ex) {
        // // Do nothing
        // }
        //
        // if (alert != null && alert.getText().startsWith("You have unsaved
        // changes"))
        // alert.accept();

        try {
            new FluentWait<PatientWebDriver>(driver)
                    .pollingEvery(100, TimeUnit.MILLISECONDS)
                    .ignoreAll(getIgnoredExeptions())
                    .withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
                    .until(getSynchronization(driver));

            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    protected abstract Predicate<PatientWebDriver> getSynchronization(PatientWebDriver driver);
}
