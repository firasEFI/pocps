package smoketests.smoke;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.testng.TestContext;
import utils.PropertyProviderImpl;

/**
 * Created by asol on 12-07-2017.
 */
public class PaymentICISelTests extends TestContext {

    @BeforeTest
    public void setup() {
        setPropertyProvider(new PropertyProviderImpl());
    }

    @Test
    public void testT001_ThirdPartyPayment() {

        doLogin();

        MenuNavigator.menu().opgave().opgaveOverblik();

        doLogout();
    }

}
