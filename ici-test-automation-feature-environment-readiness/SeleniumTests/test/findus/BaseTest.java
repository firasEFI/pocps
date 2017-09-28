package findus;

import findus_controllers.ApplicationController;
import findus_pageobjects.LoginPage;
import findus_pageobjects.MockSSOPortalPage;
import icisel.testng.PropertyProvider;
import utils.PropertyProviderImpl;

/**
 * Created by nielsjes on 17-08-2017.
 */
public class BaseTest {

    public ApplicationController applicationController;

    public PropertyProvider propertyProvider;

    public BaseTest() {
        propertyProvider = new PropertyProviderImpl();
        applicationController = new ApplicationController(propertyProvider);
        System.out.println("Active propertyProvider content:\n" + propertyProvider.toString());
    }
}
