package utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.apache.deltaspike.core.spi.config.ConfigSource;

import icisel.testng.PropertyProvider;
import icisel.testng.TestContext;
import peddersen.testconfig.DefaultConfigSource;

/**
 * Created by jemk on 02-06-2017.
 */
public class PropertyProviderImpl implements PropertyProvider {
    private boolean iciselInUse = false;

    public PropertyProviderImpl() {
    }
    
    public PropertyProviderImpl(TestContext testContext) {
        if (testContext != null) {
            this.iciselInUse = true;
            
            List<ConfigSource> configSources = new ArrayList<>();
            configSources.add(new DefaultConfigSource());
            
            ConfigResolver.addConfigSources(configSources);
        }

        System.out.println(this.toString());
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("iciselInUse: %s\n", iciselInUse));
        stringBuilder.append(String.format("getUrl     : %s\n", getUrl()));
        stringBuilder.append(String.format("getUrlSSO  : %s\n", getUrlSSO()));
        stringBuilder.append(String.format("getUsername: %s\n", getUsername()));
        stringBuilder.append(String.format("getLoginUrl: %s\n", getLoginUrl()));

        return stringBuilder.toString();
    }

    public String getUrl() {
        if (iciselInUse) {
            return ConfigResolver.getPropertyValue("icisel.baseurl");
        } else {
            return ConfigResolver.getPropertyValue("selenium.baseurl");
        }
    }

    public String getUrlSSO() {
        if (iciselInUse) {
            return ConfigResolver.getPropertyValue("icisel.baseurl.sso");
        } else {
            return ConfigResolver.getPropertyValue("selenium.baseurl.sso");
        }
    }

    public String getUsername() {
        if (iciselInUse) {
            return ConfigResolver.getPropertyValue("icisel.default.username");
        } else {
            return ConfigResolver.getPropertyValue("selenium.username");
        }
    }

    public String getPassword() {
        if (iciselInUse) {
            return ConfigResolver.getPropertyValue("icisel.default.password");
        } else {
            return ConfigResolver.getPropertyValue("selenium.password");
        }
    }

    public String getLoginUrl() {
        if (iciselInUse) {
            return getUrl();
        } else {
            return ConfigResolver.getPropertyValue("selenium.loginurl");
        }
    }

    public boolean getQuitAllDriversAfterSuite() {
        String asString;
        
        if (iciselInUse) {
            asString = ConfigResolver.getPropertyValue("icisel.quitAllDriversAfterSuite");
        } else {
            asString = ConfigResolver.getPropertyValue("selenium.quitAllDriversAfterSuite");
        }
           
        return asString.equals("true");
    }

    public String getFireFoxPath() {
        return ConfigResolver.getPropertyValue("icisel.firefoxpath");
    }

    public String getScreenShotsPath() {
        return ConfigResolver.getPropertyValue("icisel.screenshotspath");
    }

    public String getSagsbehandlerUserName() {
        return ConfigResolver.getPropertyValue("icisel.sagsbehandler.username");
    }

    public String getSagsbehandlerPassword() {
        return ConfigResolver.getPropertyValue("icisel.sagsbehandler.password");
    }

    public String getAdminUserName() {
        return ConfigResolver.getPropertyValue("icisel.admin.username");
    }

    public String getAdminPassword() {
        return ConfigResolver.getPropertyValue("icisel.admin.password");
    }

    public boolean getUseSSO() {
        String asString;
        if (iciselInUse) {
            asString = ConfigResolver.getPropertyValue("icisel.useSSO");
        } else {
            asString = ConfigResolver.getPropertyValue("selenium.useSSO");
        }
        
        if (asString == null) {
            System.err.println(
                    "Warning: Did not find any value for icisel.useSSO in property file. Defaulted to 'false'.");
            return false;
        }
        return asString.equals("true");
    }
    
    public String getWsOioHost() {
        return ConfigResolver.getPropertyValue("icisel.ws.oio.host");
    }
    
    public String getBatchRestHost() {
        return ConfigResolver.getPropertyValue("icisel.batch.host");
    }

    public String getBatchPsrmHost() {
        return ConfigResolver.getPropertyValue("icisel.batch.psrm.host");
    }

    public String getDbUser() {
        return ConfigResolver.getPropertyValue("icisel.nymf.db.user");
    }

    public String getDbPassword() {
        return ConfigResolver.getPropertyValue("icisel.nymf.db.password");
    }

    public String getDbDriver() {
        return ConfigResolver.getPropertyValue("icisel.nymf.db.driver");
    }

    public String getDbServer() {
        return ConfigResolver.getPropertyValue("icisel.nymf.db.server");
    }

    public String getDbPort() {
        return ConfigResolver.getPropertyValue("icisel.nymf.db.port");
    }

    public String getDbName() {
        return ConfigResolver.getPropertyValue("icisel.nymf.db.name");
    }

    // @Override
    // public String getUrlSSO() {
    // return ConfigResolver.getPropertyValue("selenium.baseurl.sso");
    // }
}
