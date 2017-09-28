package findus_pageobjects.synchronization;

import icisel.utils.driver.patient.PatientWebDriver;
import org.openqa.selenium.*;

public interface Synchronizer {

    public boolean isSynchronized(PatientWebDriver driver);
}
