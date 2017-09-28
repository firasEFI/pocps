package findus_pageobjects.synchronization;

import icisel.utils.driver.patient.PatientWebDriver;

/**
 * Synchronizer that always returns {@code isSynchronized = true}
 * 
 * @author mschioeler
 *
 */
public class PassiveSynchronizer implements Synchronizer {

    @Override
    public boolean isSynchronized(PatientWebDriver driver) {
        return true;
    }

}
