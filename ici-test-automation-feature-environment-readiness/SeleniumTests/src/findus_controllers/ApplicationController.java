package findus_controllers;

import static icisel.utils.driver.Engine.getDriver;

import org.openqa.selenium.WebDriver;

import findus_pageobjects.LoginPage;
import findus_pageobjects.MockSSOPortalPage;
import icisel.testng.PropertyProvider;
import icisel.utils.driver.Engine;

/**
 * Created by nielsjes on 16-08-2017.
 */
public class ApplicationController {

    PropertyProvider pp;

    public ApplicationController(PropertyProvider pp) {
        // This is Kung Fu Panda's preferred way of
        // specifying the URL to use.
        this.pp = pp;
    }

    /**
     * Gets a driver and goes to the login page.
     * 
     * @param maximize
     *            when set to {@code false}, do not maximize window. Default
     *            value is {@code true}.
     * @return the "Mock SSO Portal" login page
     * @see Engine
     */
    public MockSSOPortalPage startAtLoginSSO(boolean maximize) {
        goToPage(maximize, pp.getUrl());
        return new MockSSOPortalPage();
    }

    /**
     * Gets a driver, maximizes the window, and goes to the SSO login page. This
     * is the preferred method of logging in as per 13-09-2017.
     * 
     * @author mschioeler
     * @return the "Mock SSO Portal" login page
     * @see Engine
     */
    public MockSSOPortalPage startAtLoginSSO() {
        return startAtLoginSSO(true);
    }

    private void goToPage(boolean maximize, String url) {
        WebDriver driver = getDriver(); // static import
        driver.get(url);
        if (maximize) {
            driver.manage().window().maximize();
        }
    }

    /**
     * 
     * @param maximize
     *            when true, maximizes the window.
     * @return the "?normal" login page, with username + password
     *         authentication.
     */
    public LoginPage startAtLoginNormal(boolean maximize) {
        goToPage(maximize, normalPageUrl(pp.getUrl()));
        return new LoginPage();
    }

    private String normalPageUrl(String fullBaseUrl) {
        return fullBaseUrl.substring(0, fullBaseUrl.lastIndexOf('/') + 1) + "loginPage.jsp?normal";
    }

    /**
     * Get driver, maximize window and go to normal login page.
     * 
     * @return the "?normal" login page, with username + password
     *         authentication.
     */
    public LoginPage startAtLoginNormal() {
        return startAtLoginNormal(true);
    }

}
