package findus_controllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Window;
import org.testng.annotations.Test;

import findus_pageobjects.MockSSOPortalPage;
import icisel.testng.PropertyProvider;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;

public class ApplicationControllerTest {

    PropertyProvider pp = mock(PropertyProvider.class);
    PatientWebDriver driver = mock(PatientWebDriver.class);

    TargetLocator targetLocator = mock(TargetLocator.class);
    Options options = mock(Options.class);
    Window window = mock(Window.class);

    /**
     * The purpose of this test is to ensure that the constructor that the
     * application controller goes to the page specified by
     * PropertyProvider.getUrl() when calling
     * {@link ApplicationController#startAtLoginSSO()}.
     */
    @Test(groups = "unit")
    public void test_StartAtLoginSso_Uses_PropertyProviderGetUrl() {
        // inject mock driver
        Engine.setDriver(driver);

        String dummyUrl = "foo";
        when(pp.getUrl()).thenReturn(dummyUrl);
        when(driver.getTitle()).thenReturn(MockSSOPortalPage.pageTitle);

        new ApplicationController(pp).startAtLoginSSO();

        Engine.closeDriver();

        verify(driver, times(1)).get(dummyUrl);
    }

    /**
     * The purpose of this test is to ensure that the constructor that the
     * application controller goes to the "?normal" login page corresponding to
     * the base url specified by PropertyProvider.getUrl() when calling
     * {@link ApplicationController#startAtLoginNormal()}.
     */
    @Test(groups = "unit")
    public void testNormalPageUrl() {
        Engine.setDriver(driver);

        when(driver.manage()).thenReturn(options);
        when(options.window()).thenReturn(window);

        when(pp.getUrl()).thenReturn("https://5.44.137.168:476/teste/cis.jsp");

        new ApplicationController(pp).startAtLoginNormal();

        verify(driver, times(1)).get("https://5.44.137.168:476/teste/loginPage.jsp?normal");
    }

}
