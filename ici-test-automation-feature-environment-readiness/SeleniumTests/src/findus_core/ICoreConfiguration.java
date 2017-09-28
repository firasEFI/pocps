package findus_core;

import java.time.Duration;

public interface ICoreConfiguration {
    Duration getBusyTimeout();
    Duration getPollingInterval();
}
