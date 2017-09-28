package peddersen;

import java.util.Date;

import findus_controllers.ApplicationController;
import findus_pageobjects.MockSSOPortalPage;
import icisel.testng.PropertyProvider;
import utils.PropertyProviderImpl;

/**
 * Created by Jakob Jensen on 18-09-2017.
 */
public class BaseTest_Peddersen extends ScreenshotTakingTestContext {

    public ApplicationController applicationController;

    public MockSSOPortalPage loginPage;
    
    public String testCaseName;
    
    public BaseTest_Peddersen() {
        applicationController = new ApplicationController(new PropertyProviderImpl());
//        loginPage = applicationController.startAtLoginSSO(true);
        testCaseName = getClass().getSimpleName();
    }
}
    
