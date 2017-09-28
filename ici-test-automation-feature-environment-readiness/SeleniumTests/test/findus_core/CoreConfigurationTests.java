package findus_core;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class CoreConfigurationTests {

    CorePsrmConfiguration configuration;

    @BeforeClass
    public void setupFixture()
    {
        configuration = CorePsrmConfiguration.getInstance();
    }

    /**
     * We are testing that all properties can be read without failing.
     * The most usual failure will be convertion errors when converting from Object to other class type in
     * the configuration class
     */
    @Test(groups = "unit")
    public void canReadAllProperties()
    {
        Duration pollingInterval = configuration.getPollingInterval();
        Duration busyTimeout = configuration.getBusyTimeout();
    }

}
