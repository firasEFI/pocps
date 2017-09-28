package peddersen.examples;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.testng.TestContext;
import utils.PropertyProviderImpl;
import utils.betaling.BetalingsfilOvertype;

/**
 * The most basic test imaginable that covers login, some PSRM operation, and
 * logout. This is not a test of PSRM as much as it is a test of the automation
 * tool.
 * 
 * @author Morten
 *
 */
public class TestBasic extends TestContext {

    @BeforeTest
    public void initializeSettings() {
        WebDriver driver = getDriver();
        doMaximizeWindow();

        setPropertyProvider(new PropertyProviderImpl(this));
        
    }

    @Test(invocationCount = 2)
    public void login_Menu_logout() {
        doLogin();
        MenuNavigator.menu().a360GradersSoegning();
        doLogout();
    }

}
