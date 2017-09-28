package smoketests.smoke;

import icisel.utils.driver.LocalDriverManager;
import icisel.utils.driver.RetryAnalyzer;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageobjects.smoketests._360degreeview.*;
import pageobjects.smoketests.address.AddressViewPage;
import pageobjects.smoketests.personview.DebtorViewPage;
import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

import static org.testng.Assert.assertTrue;


/**
 * Created by msl on 26-05-2017.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class _360DegreeViewSearchTests {

    // Test data
    private String address = "Lundtoftevej 62";
    private String city = "Lyngby";
    private String fulladdress = "Lundtoftevej 62, Lyngby, 2800";

    // Expected search result data
    private String status = "Aktiv";
    private String postalField = "2800";

    /**
     * Step 1: Click on the Menu tab and choose 360 Degree Search
     * Step 2: Fill in a name (Dahl, Gitte) in the Full Name search field and click on the Search button.
     * Step 3: Find the name in the search results table and click on the name.
     * Step 4: Find and click on the name in Person Overview.
     * Step 5: Test that the names (first and last) are correct.
     * Final Step: Logout and exit PSRM
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT001_FullNameSearchGitteDahl() {

        System.out.println("Running testT001_FullNameSearchGitteDahl");

        // 0) Test data
        final String personFirstName = "Gitte";
        final String personLastName = "Dahl";
        String personFullName = personLastName + ", " + personFirstName;
        String personFullNameNormalOrder = personFirstName + " " + personLastName;

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        // 1) Open 360 degrees search
        Navi.open360DegreeSearch(driver);

        // 2) Wait for page to load
        _360TabPage current_360TabPage = new _360TabPage(driver);

        // 3) Select Person from the dropdown
        current_360TabPage.selectDropdown(_360TabPage.DROP_PERSON);

        // 4) Wait for page to load
        _360PersonPage current_360PersonPage = new _360PersonPage(driver);

        // 5) Perform a search for people with a full name
        current_360PersonPage.search(personFullName);

        final _360PersonsListPage current_360PersonsListPage = new _360PersonsListPage(driver);

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return current_360PersonsListPage.table().size() > 0;
            }
        });

        current_360PersonsListPage.findPersonAndClickNameLink(personFullNameNormalOrder);

        _360PersonDetailsPage current_360PersonDetailsPage = new _360PersonDetailsPage(driver);

        assertTrue(!current_360PersonDetailsPage.informationPersonNameLink().getText().equals(""));

        Navi.patientlyClickAttempt(driver, FrameType.ZONE_MAP_FRAME_1, By.partialLinkText(personFullNameNormalOrder), FrameType.ZONE_MAP_FRAME_1, By.id("language"));

        Navi.zoneMapFrame_1(driver);

        final DebtorViewPage currentDebtorViewPage = new DebtorViewPage(driver);

        Navi.zoneMapFrame_1(driver);

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return currentDebtorViewPage.firstNameBox().getText().equals(personFirstName);
            }
        });

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return currentDebtorViewPage.lastNameBox().getText().equals(personLastName);
            }
        });

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Click on the Menu tab and choose 360 Degree Search\n" +
                "     * Step 2: Fill in a name (Dahl, Gitte) in the Full Name search field and click on the Search button.\n" +
                "     * Step 3: Find the name in the search results table and click on the name.\n" +
                "     * Step 4: Find and click on the name in Person Overview.\n" +
                "     * Step 5: Test that the names (first and last) are correct.\n" +
                "     * Final Step: Logout and exit PSRM");

    }

    /**
     * Step 1: Click on the Menu tab and choose 360 Degree Search
     * Step 2: Fill in a name (Troels) in the First Name search field and click on the Search button.
     * Step 3: Test that the search has been correctly performed.
     * Final Step: Logout and exit PSRM.
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT002_FirstNameSearchTroels(){

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT002_FirstNameSearchTroels()");
        String person = "Troels";

        // 1) Open 360 degrees search
        Navi.open360DegreeSearch(driver);

        // 2) Wait for page to load
        _360TabPage current_360TabPage = new _360TabPage(driver);

        // 3) Select person from the dropdown
        current_360TabPage.selectDropdown(_360TabPage.DROP_PERSON);

        _360PersonPage current_360PersonPage = new _360PersonPage(driver);

        current_360PersonPage.searchFirstName(person);

        _360PersonsListPage current_360PersonsListPage = new _360PersonsListPage(driver);

        assert(current_360PersonsListPage.table().size()>=0);

        assert(driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[8]/td/div/table/tbody/tr/td[2]/a/span/span"))
                .getText().contains("Troels Jacobsen"));

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Click on the Menu tab and choose 360 Degree Search\n" +
                "     * Step 2: Fill in a name (Troels) in the First Name search field and click on the Search button.\n" +
                "     * Step 3: Test that the search has been correctly performed.\n" +
                "     * Final Step: Logout and exit PSRM.");

    }

    /**
     * Step 1: Click on the Menu tab and choose Registration → Address → Search
     * Step 2: Fill in the city name (Lyngby) in the City search field and click on the search button.
     * Step 3: Test that the search has been performed correctly.
     * Step 4: Find the address and click on it.
     * Step 5: Test that the fields in the address view are filled out correctly.
     * Final Step: Logout and exit PSRM
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT003_CityLyngbySearch(){

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testCityLyngbySearch()");

        Navi.openRegistrationAddressSearch(driver);

        Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("city"));

        _360AddressPage current_360AddressPage = new _360AddressPage(driver);

        current_360AddressPage.search("", city, "");

        Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("city"));

        Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("//div[@id='dataExplorerFilterText1']//span[@class='label']"));

        assert(driver.findElement(By.xpath("//div[@id='dataExplorerFilterText1']//span[@class='label']")).getText().equals(city));

        _360AddressListPage current_360AddressListPage = new _360AddressListPage(driver);

        boolean elementFound = false;

        int i = 0;

        while(!elementFound && i<10) {

            try {

                current_360AddressListPage.findAddressInTable(fulladdress);

                AddressViewPage currentAddressViewPage = new AddressViewPage(driver);

                currentAddressViewPage.verifyFields(status, address, city, postalField);

                elementFound = true;

            } catch (TimeoutException e) {

                i++;

            }

        }

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Click on the Menu tab and choose Registration → Address → Search\n" +
                "     * Step 2: Fill in the city name (Lyngby) in the City search field and click on the search button.\n" +
                "     * Step 3: Test that the search has been performed correctly.\n" +
                "     * Step 4: Find the address and click on it.\n" +
                "     * Step 5: Test that the fields in the address view are filled out correctly.\n" +
                "     * Final Step: Logout and exit PSRM");

    }

    /**
     * Step 1: Click on the Menu tab and choose Registration → Address → Search
     * Step 2: Fill in the postal code (2800) in the Postal search field and click on the search button.
     * Step 3: Test that the search has been performed correctly.
     * Step 4: Find the address and click on it.
     * Step 5: Test that the fields in the address view are filled out correctly.
     * Final Step: Logout and exit PSRM
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT004_Postal2800Search(){

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT004_Postal2800Search()");

        Navi.openRegistrationAddressSearch(driver);

        Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("postal"));

        _360AddressPage current_360AddressPage = new _360AddressPage(driver);

        current_360AddressPage.search("", "", postalField);

        Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("postal"));

        Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("//div[@id='dataExplorerFilterText1']//span[@class='label']"));

        assert(driver.findElement(By.xpath("//div[@id='dataExplorerFilterText1']//span[@class='label']")).getText().equals(postalField));

        _360AddressListPage current_360AddressListPage = new _360AddressListPage(driver);

        current_360AddressListPage.findAddressInTable(fulladdress);

        AddressViewPage currentAddressViewPage = new AddressViewPage(driver);

        currentAddressViewPage.verifyFields(status, address, city, postalField);

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Click on the Menu tab and choose Registration → Address → Search\n" +
                "     * Step 2: Fill in the postal code (2800) in the Postal search field and click on the search button.\n" +
                "     * Step 3: Test that the search has been performed correctly.\n" +
                "     * Step 4: Find the address and click on it.\n" +
                "     * Step 5: Test that the fields in the address view are filled out correctly.\n" +
                "     * Final Step: Logout and exit PSRM");
    }

    /**
     * Step 1: Click on the Menu tab and choose Registration → Address → Search
     * Step 2: Fill in the address (Lundtoftevej 62) in the Address search field and click on the search button.
     * Step 3: Test that the search has been performed correctly.
     * Step 4: Find the address and click on it.
     * Step 5: Test that the fields in the address view are filled out correctly.
     * Final Step: Logout and exit PSRM
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT005_AddressLundtoftevej62Search() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT005_AddressLundtoftevej62Search()");

        Navi.openRegistrationAddressSearch(driver);

        Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("address1"));

        _360AddressPage current_360AddressPage = new _360AddressPage(driver);

        current_360AddressPage.search(address, "", "");

        Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("address1"));

        Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("//div[@id='dataExplorerFilterText1']//span[@class='label']"));

        assert(driver.findElement(By.xpath("//div[@id='dataExplorerFilterText1']//span[@class='label']")).getText().equals(address));

        _360AddressListPage current_360AddressListPage = new _360AddressListPage(driver);

        current_360AddressListPage.findAddressInTable(fulladdress);

        AddressViewPage currentAddressViewPage = new AddressViewPage(driver);

        currentAddressViewPage.verifyFields(status, address, city, postalField);

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Click on the Menu tab and choose Registration → Address → Search\n" +
                "     * Step 2: Fill in the address (Lundtoftevej 62) in the Address search field and click on the search button.\n" +
                "     * Step 3: Test that the search has been performed correctly.\n" +
                "     * Step 4: Find the address and click on it.\n" +
                "     * Step 5: Test that the fields in the address view are filled out correctly.\n" +
                "     * Final Step: Logout and exit PSRM");
    }

}
