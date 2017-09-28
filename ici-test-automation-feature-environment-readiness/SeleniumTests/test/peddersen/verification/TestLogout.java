package peddersen.verification;

import org.junit.Test;

import findus_controllers.ApplicationController;
import icisel.utils.driver.Engine;
import productabstractions.RoleMapImpl;
import utils.PropertyProviderImpl;

public class TestLogout {
    @Test
    public void test() {
        new ApplicationController(new PropertyProviderImpl()).startAtLoginSSO(false)
                .login(new RoleMapImpl("110110111"))
                .logout();
        Engine.closeDriver();
    }

}
