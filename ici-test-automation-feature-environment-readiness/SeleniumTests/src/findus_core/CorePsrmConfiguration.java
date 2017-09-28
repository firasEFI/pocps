package findus_core;

import java.util.HashMap;
import java.time.Duration;

/**
 * The purpose of this configuration is to expose a set of good defaults when automating the PSRM Application.
 * Expecially timeout values should be contained in this class to prevent hardcode implementations of such.
 *
 * The configuration closs is available all over the application as a singleton.
 */
public class CorePsrmConfiguration implements ICoreConfiguration {
    private static CorePsrmConfiguration instance;

    public static CorePsrmConfiguration getInstance() {
        if (instance == null)
            instance = new CorePsrmConfiguration();
        return instance;
    }


    private final HashMap<String, Object> defaults = new HashMap<String, Object>();

    private CorePsrmConfiguration()
    {
        defaults.put("BusyTimeoutInMills", (long)30000);
        defaults.put("PollingIntervalInMillis", (long)250);
    }

    /**
     * The default busy timeout, use this when waiting for something to respond or synchronize
     * The timeout should be rather patient.
     * @return
     */
    @Override
    public Duration getBusyTimeout()
    {
        return Duration.ofMillis((long)defaults.get("BusyTimeoutInMills"));
    }

    /**
     * The polling interval will mostly be used in cases where we are waiting for to get a certain state.
     * @return
     */
    @Override
    public Duration getPollingInterval()
    {
        return Duration.ofMillis((long)defaults.get("PollingIntervalInMillis"));
    }
}
