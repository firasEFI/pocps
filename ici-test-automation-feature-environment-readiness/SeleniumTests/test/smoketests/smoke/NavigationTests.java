package smoketests.smoke;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import icisel.utils.driver.LocalDriverManager;
import icisel.utils.driver.RetryAnalyzer;
import pageobjects.smoketests._360degreeview._360AgentPage;
import pageobjects.smoketests._360degreeview._360CasePage;
import pageobjects.smoketests._360degreeview._360ClaimPage;
import pageobjects.smoketests._360degreeview._360DocumentPage;
import pageobjects.smoketests._360degreeview._360PersonPage;
import pageobjects.smoketests._360degreeview._360TabPage;
import pageobjects.smoketests.psrm_navigation.Navi;

/**
 * Created by asol on 30-05-2017.
 */
public class NavigationTests {

    /**
     * Step 1: Click on the Menu tab and choose 360 Degree Search
     * Step 2: Test that Search By: Debtors and Claimants appears as it should.
     * Step 3: Test that Search By: Case works as it should.
     * Step 4: Test that Search By: Agents works as it should.
     * Step 5: Test that Search By: Document works as it should.
     * Step 6: Test that Search By: Claim works as it should.
     * Final Step: Logout and exit PSRM
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT001_360DegreeSearchAvailability() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT001_360DegreeSearchAvailability()");

        Navi.open360DegreeSearch(driver);

        _360TabPage current_360TabPage = new _360TabPage(driver);

        current_360TabPage.selectDropdown(_360TabPage.DROP_PERSON);

        _360PersonPage current_360PersonPage = new _360PersonPage(driver);

        final WebElement idNumberField = current_360PersonPage.idNumberField();

        (new WebDriverWait(driver, Navi.LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return idNumberField.isDisplayed();
            }
        });

        current_360TabPage.selectDropdown(_360TabPage.DROP_CASE);

        final _360CasePage current_360CasePage = new _360CasePage(driver);

        (new WebDriverWait(driver, Navi.LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return current_360CasePage.caseIDField().isDisplayed();
            }
        });

        final WebElement caseTypeField = current_360CasePage.caseTypeField();

        final WebElement createdFormField = current_360CasePage.createdFormField();

        final WebElement caseIDField = current_360CasePage.caseIDField();

        final WebElement searchButton = current_360CasePage.searchButton();

        (new WebDriverWait(driver, Navi.LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = caseTypeField.isDisplayed();
                boolean b2 = createdFormField.isDisplayed();
                boolean b3 = caseIDField.isDisplayed();
                boolean b4 = searchButton.isEnabled();
                return b1 && b2 && b3 && b4;
            }
        });

        current_360TabPage.selectDropdown(_360TabPage.DROP_AGENT);

        final _360AgentPage current_360AgentPage = new _360AgentPage(driver);

        (new WebDriverWait(driver, Navi.LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return current_360AgentPage.fullNameField().isDisplayed();
            }
        });

        current_360TabPage.selectDropdown(_360TabPage.DROP_DOCUMENT);

        final _360DocumentPage current_360DocumentPage = new _360DocumentPage(driver);

        final WebElement debtorId = current_360DocumentPage.debtorId();

        final WebElement documentId = current_360DocumentPage.documentId();

        (new WebDriverWait(driver, Navi.LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = debtorId.isDisplayed();
                boolean b2 = documentId.isDisplayed();
                return b1 && b2;
            }
        });

        final _360ClaimPage current_360ClaimPage = new _360ClaimPage(driver);

        final WebElement debtorId1 = current_360ClaimPage.debtorId();

        final WebElement obligationId = current_360ClaimPage.obligationId();

        (new WebDriverWait(driver, Navi.LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = debtorId1.isDisplayed();
                boolean b2 = obligationId.isDisplayed();
                return b1 && b2;
            }
        });

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Click on the Menu tab and choose 360 Degree Search\n" +
                "     * Step 2: Test that Search By: Debtors and Claimants appears as it should.\n" +
                "     * Step 3: Test that Search By: Case works as it should.\n" +
                "     * Step 4: Test that Search By: Agents works as it should.\n" +
                "     * Step 5: Test that Search By: Document works as it should.\n" +
                "     * Step 6: Test that Search By: Claim works as it should.\n" +
                "     * Final Step: Logout and exit PSRM");

    }

    /**
     * Step 1: Click on the Menu tab and wait to test if it works as it should.
     * Step 2: Click on the Home tab and wait to test if it works as it should.
     * Step 3: Click on the Menu tab and choose My Preferences. Test that it works as it should.
     * Step 4: Click on the Menu tab and choose 360 Degree Search. Test that it works as it should.
     * Step 5: Click on the History tab.
     * Step 6: Assert three elements in the history list.
     * Step 7: Go three pages back in history (By clicking on the left arrow to the left of the history tab).
     * Step 8: Go two pages forward in history (By clicking on the right arrow to the right of the history tab).
     * Step 9: Click on the Menu tab and choose My Preferences.
     * Step 10: Compare the username in my preferences and XML config. They should be the same.
     * Final Step: Logout and exit PSRM.
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT002_ToolBarAvailability() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT002_ToolBarAvailability()");

        Navi.openToolbarMenu(driver);

        Navi.openToolBarHome(driver);

        Navi.openAdminMenu(driver);

        Navi.openMyPreferences(driver);

        Navi.tabPage(LocalDriverManager.getDriver());

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("FIRST_NAME")).isDisplayed();
            }
        });

        Navi.open360DegreeSearch(driver);

        Navi.tabPage(driver);

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("fullName")).isDisplayed();
            }
        });

        Navi.clickToolBarHistory(driver);

        Navi.clickToolbarHistoryGoBack(driver);

        Navi.clickToolbarHistoryGoBack(driver);

        Navi.clickToolbarHistoryGoBack(driver);

        Navi.clickToolbarHistoryGoForward(driver);

        Navi.clickToolbarHistoryGoForward(driver);

        Navi.tabPage(driver);

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("FIRST_NAME")).isDisplayed();
            }
        });

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Click on the Menu tab and wait to test if it works as it should.\n" +
                "     * Step 2: Click on the Home tab and wait to test if it works as it should.\n" +
                "     * Step 3: Click on the Menu tab and choose My Preferences. Test that it works as it should.\n" +
                "     * Step 4: Click on the Menu tab and choose 360 Degree Search. Test that it works as it should.\n" +
                "     * Step 5: Click on the History tab.\n" +
                "     * Step 6: Assert three elements in the history list.\n" +
                "     * Step 7: Go three pages back in history (By clicking on the left arrow to the left of the history tab).\n" +
                "     * Step 8: Go two pages forward in history (By clicking on the right arrow to the right of the history tab).\n" +
                "     * Step 9: Click on the Menu tab and choose My Preferences.\n" +
                "     * Step 10: Compare the username in my preferences and XML config. They should be the same.\n" +
                "     * Final Step: Logout and exit PSRM.");

    }

}
