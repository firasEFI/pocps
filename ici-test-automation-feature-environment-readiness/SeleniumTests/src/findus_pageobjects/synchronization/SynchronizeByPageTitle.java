package findus_pageobjects.synchronization;

import com.google.common.base.Predicate;
import icisel.utils.driver.patient.PatientWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SynchronizeByPageTitle extends PageSynchronizer {

    private String title;

    public SynchronizeByPageTitle(String title, int timeOutInSeconds) {
        super(timeOutInSeconds);

        if(title == null)
            throw new IllegalArgumentException("title cannot be null");

        this.title = title;
    }

    @Override
    protected Predicate<PatientWebDriver> getSynchronization(PatientWebDriver driver) {
        return new Predicate<PatientWebDriver>() {
            @Override
            public boolean apply(PatientWebDriver input) {
                return ExpectedConditions.titleIs(title).apply(input);
            };
        };
    }
}
