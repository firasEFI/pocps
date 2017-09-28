package findus_pageobjects.synchronization;

import com.google.common.base.Predicate;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.patient.PatientWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SynchronizeByElementPresent extends PageSynchronizer {

    private PageElement referenceElement;
    private long timeOutInMilliSeconds;

    public SynchronizeByElementPresent(PageElement referenceElement, int timeOutInSeconds) {
        super(timeOutInSeconds);

        if(referenceElement == null)
            throw new IllegalArgumentException("elementLocator cannot be null");

        this.referenceElement = referenceElement;
    }

    @Override
    protected Predicate<PatientWebDriver> getSynchronization(PatientWebDriver driver) {
        return new Predicate<PatientWebDriver>() {
            @Override
            public boolean apply(PatientWebDriver input) {
                referenceElement.waitFor();

                return true;
            };
        };
    }
}
