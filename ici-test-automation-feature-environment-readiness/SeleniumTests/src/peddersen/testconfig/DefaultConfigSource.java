package peddersen.testconfig;

import java.util.HashMap;
import java.util.Map;

import org.apache.deltaspike.core.spi.config.ConfigSource;

public class DefaultConfigSource implements ConfigSource {
    private static final int LOW_PRIO = 0;
    private final Map<String, String> properties;

    public DefaultConfigSource() {
        this.properties = new HashMap<>();
        this.properties.put("icisel.baseurl", "https://5.44.137.168:476/teste/loginPage.jsp?normal");
        this.properties.put("icisel.baseurl.sso", "https://5.44.137.168:476/ouaf/loginPage.jsp");
        this.properties.put("icisel.default.username", "IFK");
        this.properties.put("icisel.default.password", "netcompany-123");
        this.properties.put("icisel.screenshotspath", "screenshots/");
        this.properties.put("icisel.useSSO", "false");
    }

    @Override
    public int getOrdinal() {
        // very low prio - should only load if everything else fails!
        return LOW_PRIO;
    }

    @Override
    public Map<String, String> getProperties() {
        return this.properties;
    }

    @Override
    public String getPropertyValue(String string) {
        return this.properties.get(string);
    }

    @Override
    public String getConfigName() {
        return "DeltaSpike default property configuration";
    }

    @Override
    public boolean isScannable() {
        return false;
    }
}

