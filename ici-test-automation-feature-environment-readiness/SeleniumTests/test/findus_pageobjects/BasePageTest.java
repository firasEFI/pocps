package findus_pageobjects;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import findus_pageobjects.synchronization.PageSynchronizationException;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;

public class BasePageTest {

    Synchronizer sync = mock(Synchronizer.class);
    PatientWebDriver driver = mock(PatientWebDriver.class);

    /**
     * The purpose of this test is to make sure that
     * <ul>
     * <li>{@link BasePage} constructor calls synchronizer</li>
     * <li>when synchronization is positive, control flow is passed on</li>
     * </ul>
     */
    @Test(groups = "unit")
    public void testBasePageSynchronization_positive() {
        Engine.setDriver(driver);

        when(sync.isSynchronized(any(PatientWebDriver.class))).thenReturn(true);

        new BasePage(sync) {
        };

        Engine.closeDriver();
    }

    /**
     * This tests makes sure that, upon construction of a {@link BasePage}, a
     * {@link PageSynchronizationException} is thrown if
     * {@link BasePage#isSynchronized()} returns {@code false}.
     */
    @Test(groups = "unit", expectedExceptions = PageSynchronizationException.class)
    public void testBasePageSynchronization_negative() {
        Engine.setDriver(driver);

        when(sync.isSynchronized(any(PatientWebDriver.class))).thenReturn(false);

        new BasePage(sync) {
        };

        Engine.closeDriver();
    }

}
