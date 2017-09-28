package smoketests.smoke;

import icisel.utils.driver.LocalDriverManager;
import icisel.utils.driver.RetryAnalyzer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageobjects.smoketests._360degreeview.*;
import pageobjects.smoketests.address.AddressAddPage;
import pageobjects.smoketests.personadd.PersonClaimantPage;
import pageobjects.smoketests.personadd.PersonSelectPage;
import pageobjects.smoketests.psrm_navigation.Navi;
import pageobjects.smoketests.reactivatedebtor.ReactivateDebtorPage;
import pageobjects.smoketests.taxrole.TaxRolePage;
import pageobjects.smoketests.uimaps.AgentWithCPRPage;
import pageobjects.smoketests.uimaps.CreateDocumentCardPage;
import pageobjects.smoketests.uimaps.SelectAddAgentPage;
import utils.FrameType;

/**
 * Created by asol on 24-05-2017.
 */
public class PSRMGeneralTests{

    /**
     * Step 1: Click on the Menu tab and choose Registration → Address → Add
     * Step 2: In the new window choose Status: Active.
     * Step 3: In the same window fill in the city (GebCity) in the City field.
     * Step 4: In the same window fill in the postal code (GebPostal) in the Postal field.
     * Step 5: Click Save and wait for the error message: "Missing Address".
     * Step 6: In the same window fill in the address (GebAddress) in the Address field.
     * Final Step: Logout and exit PSRM.
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT001_AddNewAddress() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT001_AddNewAddress");

        String status = "C1AC";
        String address = "GebAddress";
        String city = "GebCity";
        String postal = "GebPostal";

        Navi.waitForReadyStateComplete(driver);

        Navi.registrationAddAddress(driver);

        AddressAddPage currentAddressAddPage = new AddressAddPage(driver);

        currentAddressAddPage.status().selectByValue(status);

        currentAddressAddPage.city().sendKeys(city);

        currentAddressAddPage.postal().sendKeys(postal);

        // Address has not been entered and it is not possible to save the address
        currentAddressAddPage.clickSave(true);

        currentAddressAddPage.printErrorMessage();

        currentAddressAddPage.address1().sendKeys(address);

        // Address has been entered and it is possible to save the address
        currentAddressAddPage.clickSave(false);

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Click on the Menu tab and choose Registration → Address → Add\n" +
                "     * Step 2: In the new window choose Status: Active.\n" +
                "     * Step 3: In the same window fill in the city (GebCity) in the City field.\n" +
                "     * Step 4: In the same window fill in the postal code (GebPostal) in the Postal field.\n" +
                "     * Step 5: Click Save and wait for the error message: \"Missing Address\".\n" +
                "     * Step 6: In the same window fill in the address (GebAddress) in the Address field.\n" +
                "     * Final Step: Logout and exit PSRM.");

    }

    /**
     * Step 1: Click on the Menu tab and choose Registration → Person → Add
     * Step 2: In the field called Person Type select Claimant and click on the Ok button.
     * Step 3: In the field called Language select English.
     * Step 4: In the field called Name under Person Names write in any name (Gurli Gertsen)
     * Step 5: In the field called Primary ID under Primary IDs click on the check box.
     * Step 6: In the field called ID Type under Primary IDs select FordringshaverID.
     * Step 7: In the field called ID Number under Primary IDs write any number (1234)
     * Step 8: Under Primary IDs go to the far left and click on the Plus.
     * Step 9: In the new fields appearing go to ID Type and select CVR-Number.
     * Step 10: In the new field called ID Number write in a specific ID Number (19552101)
     * Step 11: In the field called Phone Type under Person Phones select Mobile Phone.
     * Step 12: In the field called Phone Number under Person Phones write in any 8-digit number (99999999)
     * Step 13: In the field called Start Date under Person Addresses write any date (01-03-2017) prior to the current date.
     * Step 14: In the field called Address Type under Person Addresses select Current.
     * Step 15: Click on the search button next to the field called Address ID under Person Addresses.
     * Step 16: In the new window go to the field called Address and write %, then click on the Search button.
     * Step 17: In the table with addresses now appearing choose any address (The top one)
     * Step 18: In the field called Address Priority under Person Addresses select Preferred for Correspondence.
     * Step 19: In the field called Deliverable under Person Addresses select Yes.
     * Step 20: In the field called Email Address under Contact Information write in any Email (gebgebgeb@geb.com)
     * Step 21: Scroll down to Agreement types.
     * Step 22: In the field called Agreement Type under Agreement types select Claimant agreement.
     * Step 23: In the field called Claimant Type under Agreement types select Other public authority.
     * Step 24:In the field called Payment Type under Agreement types select External.
     * Step 25: In the field called System agreement under Agreement types select No.
     * Step 26: In the field called Currency under Agreement types select Danish Kroner.
     * Step 27: In ALL the fields from Allowed to Write down to Allowed Resubmitting Claims select Yes (Remember to select Yes in ALL the fields).
     * Step 28: In the field called System Reporter write in a specific string (16356441).
     * Step 29: Scroll down to Bank Details.
     * Step 30: In the field called Settlement Frequens under Bank Details select Monthly.
     * Step 31: In the field called Settlement Method under Bank Details select Nem Konto.
     * Step 32: Scroll down to the Save button and click on it. Make sure no error messages are displayed.
     * Step 33: Make sure the newly registered person is added correctly.
     * Final Step: Logout and exit PSRM.
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT002_AddNewClaimant() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT002_AddNewClaimant");

        String name = "Geb Claimant";
        String fordringshaverIDNumber = "1234";
        String CVRNumber = "19552101";
        String phoneNumber = "99999999";
        String startDate = "01-03-2017";
        String eMail = "geb@testing.com";
        String systemReporter = "16356441";
        String addressId = "00000200000020";

        Navi.openRegistrationPersonAdd(driver);

        PersonSelectPage currentPersonSelectPage = new PersonSelectPage(driver);

        currentPersonSelectPage.personType().selectByValue(currentPersonSelectPage.CLAIMANT);

        currentPersonSelectPage.clickOk();

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("boGroup_changeReason")).isDisplayed();
            }
        });

        PersonClaimantPage currentPersonClaimantPage = new PersonClaimantPage(driver);

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("/html/body/div[4]/div[2]/span/select/option[3]")).getText().equals("English");
            }
        });

        System.out.println(driver.findElement(By.xpath("/html/body/div[4]/div[2]/span/select/option[3]")).getText());

        currentPersonClaimantPage.changeReason().sendKeys("SELENIUM");

        currentPersonClaimantPage.language().selectByValue("ENG");

        currentPersonClaimantPage.name().sendKeys(name);

        currentPersonClaimantPage.isPrimaryId();

        currentPersonClaimantPage.idType().selectByVisibleText("Fordringshaver ID");

        currentPersonClaimantPage.personIdNumber().sendKeys(fordringshaverIDNumber);

        Navi.patientlyClick(driver, By.xpath("//table[@id='displayPersonIdNumbers']//img[@class='imgPlus' and @title='Tilføj']"));

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("idType_1")).isDisplayed();
            }
        });

        Select idType1 = new Select(driver.findElement(By.id("idType_1")));

        idType1.selectByValue("CVR");

        WebElement idNumber1 = driver.findElement(By.id("personIdNumber_1"));

        idNumber1.sendKeys(CVRNumber);

        currentPersonClaimantPage.phoneType().selectByValue("MOBILE");

        currentPersonClaimantPage.phoneNumber().sendKeys(phoneNumber);

        currentPersonClaimantPage.startDate().sendKeys(startDate);

        currentPersonClaimantPage.addressType().selectByValue("DK-CURRENT");

        currentPersonClaimantPage.addressId().sendKeys(addressId);

        currentPersonClaimantPage.addressPriority().selectByValue("10");

        currentPersonClaimantPage.deliverable().selectByValue("C1YS");

        currentPersonClaimantPage.emailAddress().sendKeys(eMail);

        currentPersonClaimantPage.agreementType().selectByValue("CLAG");

        currentPersonClaimantPage.claimantType().selectByValue("T04");

        currentPersonClaimantPage.paymentType().selectByValue("EX");

        currentPersonClaimantPage.systemAgreement().selectByValue("N");

        currentPersonClaimantPage.currencyCode().selectByValue("DKK");

        currentPersonClaimantPage.allowedToWriteDown().selectByValue("Y");

        currentPersonClaimantPage.allowedToCreateRecoveryClaims().selectByValue("Y");

        currentPersonClaimantPage.allowedToCreateOffsetClaims().selectByValue("Y");

        currentPersonClaimantPage.allowedToCreateTransports().selectByValue("Y");

        currentPersonClaimantPage.allowedWithdraw().selectByValue("Y");

        currentPersonClaimantPage.allowWriteUpFromWriteDownAdjustment().selectByValue("Y");

        currentPersonClaimantPage.allowWriteUpCancellationWriteDownPayment().selectByValue("Y");

        currentPersonClaimantPage.allowWriteDownCancellationWriteUpAdjustment().selectByValue("Y");

        currentPersonClaimantPage.allowWriteDownCancellationWriteUpPayment().selectByValue("Y");

        currentPersonClaimantPage.allowIncorrectMainReport().selectByValue("Y");

        currentPersonClaimantPage.allowWriteUpAdjustment().selectByValue("Y");

        currentPersonClaimantPage.allowResubmittningClaims().selectByValue("Y");

        currentPersonClaimantPage.systemReporter().sendKeys(systemReporter);

        currentPersonClaimantPage.settlementFrequency().selectByValue("MONT");

        currentPersonClaimantPage.settlementMethod().selectByValue("NEMK");

        currentPersonClaimantPage.clickSave();

        try{

            driver.findElement(By.id("ERRMSG-TEXT-SPAN")).getText().equals(null);

            (new WebDriverWait(driver, 10)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage").switchTo().frame("zoneMapFrame_1")
                            .findElement(By.xpath("//td[@class='oraNormal oraDisplayCell oraDefault' and @oraerrorelement='entityName']")).getText().equals("Geb Claimant");
                }
            });

            Navi.logoutPSRM(driver);

        }catch(TimeoutException ex){

            driver.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("uiMap");

            System.out.println(driver.findElement(By.id("ERRMSG-TEXT-SPAN")).getText());

            org.testng.Assert.fail("testT002_AddNewClaimantFailed");

            Navi.logoutPSRM(driver);

        }

        System.out.println("     * Step 1: Click on the Menu tab and choose Registration → Person → Add\n" +
                "     * Step 2: In the field called Person Type select Claimant and click on the Ok button.\n" +
                "     * Step 3: In the field called Language select English.\n" +
                "     * Step 4: In the field called Name under Person Names write in any name (Gurli Gertsen)\n" +
                "     * Step 5: In the field called Primary ID under Primary IDs click on the check box.\n" +
                "     * Step 6: In the field called ID Type under Primary IDs select FordringshaverID.\n" +
                "     * Step 7: In the field called ID Number under Primary IDs write any number (1234)\n" +
                "     * Step 8: Under Primary IDs go to the far left and click on the Plus.\n" +
                "     * Step 9: In the new fields appearing go to ID Type and select CVR-Number.\n" +
                "     * Step 10: In the new field called ID Number write in a specific ID Number (19552101)\n" +
                "     * Step 11: In the field called Phone Type under Person Phones select Mobile Phone.\n" +
                "     * Step 12: In the field called Phone Number under Person Phones write in any 8-digit number (99999999)\n" +
                "     * Step 13: In the field called Start Date under Person Addresses write any date (01-03-2017) prior to the current date.\n" +
                "     * Step 14: In the field called Address Type under Person Addresses select Current.\n" +
                "     * Step 15: Click on the search button next to the field called Address ID under Person Addresses.\n" +
                "     * Step 16: In the new window go to the field called Address and write %, then click on the Search button.\n" +
                "     * Step 17: In the table with addresses now appearing choose any address (The top one)\n" +
                "     * Step 18: In the field called Address Priority under Person Addresses select Preferred for Correspondence.\n" +
                "     * Step 19: In the field called Deliverable under Person Addresses select Yes.\n" +
                "     * Step 20: In the field called Email Address under Contact Information write in any Email (gebgebgeb@geb.com)\n" +
                "     * Step 21: Scroll down to Agreement types.\n" +
                "     * Step 22: In the field called Agreement Type under Agreement types select Claimant agreement.\n" +
                "     * Step 23: In the field called Claimant Type under Agreement types select Other public authority.\n" +
                "     * Step 24:In the field called Payment Type under Agreement types select External.\n" +
                "     * Step 25: In the field called System agreement under Agreement types select No.\n" +
                "     * Step 26: In the field called Currency under Agreement types select Danish Kroner.\n" +
                "     * Step 27: In ALL the fields from Allowed to Write down to Allowed Resubmitting Claims select Yes (Remember to select Yes in ALL the fields).\n" +
                "     * Step 28: In the field called System Reporter write in a specific string (16356441).\n" +
                "     * Step 29: Scroll down to Bank Details.\n" +
                "     * Step 30: In the field called Settlement Frequens under Bank Details select Monthly.\n" +
                "     * Step 31: In the field called Settlement Method under Bank Details select Nem Konto.\n" +
                "     * Step 32: Scroll down to the Save button and click on it. Make sure no error messages are displayed.\n" +
                "     * Step 33: Make sure the newly registered person is added correctly.\n" +
                "     * Final Step: Logout and exit PSRM.");


    }

    /**
     * Step 1: Click on the Menu tab and choose 360 Degree Search.
     * Step 2: Go to ID Type and select CPR. Go to the ID Number field and write a CPR Number (2411826033)
     * Step 3: Wait for the search results to appear, and click on the name (Kaas, Esben).
     * Step 4: Click on the tax role tab.
     * Step 5: On the Notes Related to Debtor line click Add.
     * Step 6: In the new window go to the Title field and write a title (Geb TEST titel!.).
     * Step 7: In the same window go to the Text field and fill in the text (Geb TEST tekst!.)
     * Step 8: In the same window select a Category (Other) and click on the Save button.
     * Step 9: Sort the Notes by clicking on Note ID.
     * Step 10: Select the Note on the top of the table by clicking on the Note ID of the Note.
     * Step 11: Check that the Note contains all the correct values.
     * Final Step: Logout and exit PSRM.
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT003_AddNewNote() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT003_AddNewNote");

        // 0) Setup smoketests data
        String category = _360PersonDetailsAddNotePage.OTHER;
        String category_note = _360PersonDetailsAddNotePage.OTHER_NOTE;
        String title = "Geb TEST titel!. 123";
        String text = "Geb TEST tekst!. 123";
        String cpr_number = "2411826033"; // Asger Rasmussen

        Navi.open360DegreeSearchPage(driver);

        _360PersonPage currentpage = new _360PersonPage(driver);

        currentpage.searchForID(_360PersonPage.ID_TYPE_CPR, cpr_number);

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return Navi.findElementsAtTabPage(d, By.linkText("Asger Rasmussen")).size()>0;
            }
        });

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.linkText("Asger Rasmussen"), FrameType.ZONE_MAP_FRAME_1, By.id("personInfo_idnumber"));

        _360PersonDetailsPage current_360personDetailsPage = new _360PersonDetailsPage(driver);

        current_360personDetailsPage.cprNumber();

        Navi.openTaxRole(driver);

        TaxRolePage currentTaxRolePage = new TaxRolePage(driver);

        currentTaxRolePage.waitForLoadedPage();

        Navi.waitForDisplayedElement(FrameType.DASHBOARD, driver, By.id("title_heading_214"));

        currentTaxRolePage.clickAddNote();

        _360PersonDetailsAddNotePage currentpage_360PersonDetailsAddNotePage = new _360PersonDetailsAddNotePage(driver);

        currentpage_360PersonDetailsAddNotePage.categoryInput().selectByValue(category);

        currentpage_360PersonDetailsAddNotePage.titleInput().sendKeys(title);

        currentpage_360PersonDetailsAddNotePage.textAreaInput().sendKeys(text);

        Navi.patientlyClickAttempt(driver, FrameType.UI_MAP, By.id("SAVE_BTN_MP"), FrameType.TAB_PAGE, By.id("dataExplorerTableBody2"));

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Click on the Menu tab and choose 360 Degree Search.\n" +
                "     * Step 2: Go to ID Type and select CPR. Go to the ID Number field and write a CPR Number (2411826033)\n" +
                "     * Step 3: Wait for the search results to appear, and click on the name (Kaas, Esben).\n" +
                "     * Step 4: Click on the tax role tab.\n" +
                "     * Step 5: On the Notes Related to Debtor line click Add.\n" +
                "     * Step 6: In the new window go to the Title field and write a title (Geb TEST titel!.).\n" +
                "     * Step 7: In the same window go to the Text field and fill in the text (Geb TEST tekst!.)\n" +
                "     * Step 8: In the same window select a Category (Other) and click on the Save button.\n" +
                "     * Step 9: Sort the Notes by clicking on Note ID.\n" +
                "     * Step 10: Select the Note on the top of the table by clicking on the Note ID of the Note.\n" +
                "     * Step 11: Check that the Note contains all the correct values.\n" +
                "     * Final Step: Logout and exit PSRM.");
    }

    /**
     * Step 1: Click on the Menu tab and choose 360 Degree Search.
     * Step 2: Go to ID Type and select CPR. Go to the ID Number field and write a CPR Number (2612689066)
     * Step 3: Wait for the search results to appear, and click on the name (Dam, Jesper)
     * Step 4: On the Related agents line click on Add Agent.
     * Step 5: In the new window select Person with CPR and click Next.
     * Step 6: In the field called CPR Number fill in a CPR Number (2612689066).
     * Step 7: Fill out the field called Representation Limitation with This is a geb test!
     * Step 8: Under Agent Type select Agent.
     * Step 9: In the field called Home Phone fill in a home phone number (99887766).
     * Step 10: In the field called Mobile Phone fill in a mobile phone number (55443322).
     * Step 11: In the field called Contact Email fill in a contact email (test@email.com).
     * Step 12: Click on the Save button.
     * Step 13: Check that the Agent has been added correctly.
     * Step 14: Click on the added Agent to check that you can acces the Agents personal page.
     * Final Step: Logout and exit PSRM.
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT004_AddRelatedAgent() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT005_AddRelatedAgent()");
        String cpr_number = "2612689066"; // Jesper Dam
        String cpr_number_agent = "0356453573";
        String testRepreLim = "This is a geb smoketests!";

        Navi.open360DegreeSearch(driver);

        _360TabPage current_360TabPage = new _360TabPage(driver);

        current_360TabPage.selectDropdown(current_360TabPage.DROP_PERSON);

        _360PersonPage current_360PersonPage = new _360PersonPage(driver);

        current_360PersonPage.searchForID(_360PersonPage.ID_TYPE_CPR, cpr_number);

        final _360PersonsListPage current_360PersonsListPage = new _360PersonsListPage(driver);

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return current_360PersonsListPage.table().size() > 0;
            }
        });

        current_360PersonsListPage.findPersonAndClickNameLink("Jesper Dam");

        _360PersonDetailsPage current_360PersonDetailsPage = new _360PersonDetailsPage(driver);

        current_360PersonDetailsPage.clickAddAgent();

        SelectAddAgentPage current_SelectAddAgentPage = new SelectAddAgentPage(driver);

        current_SelectAddAgentPage.agentType().selectByValue("PWID");

        current_SelectAddAgentPage.clickNextPartWithCPR();

        AgentWithCPRPage current_AgentWithCPRPage = new AgentWithCPRPage(driver);

        current_AgentWithCPRPage.cprId().sendKeys(cpr_number_agent);

        current_AgentWithCPRPage.representationLimitation().sendKeys(testRepreLim);

        current_AgentWithCPRPage.relationType().selectByValue("AGEN");

        current_AgentWithCPRPage.phoneNumber().sendKeys("99887766");

        current_AgentWithCPRPage.mobilePhoneNumber().sendKeys("55443322");

        current_AgentWithCPRPage.emailAddress().sendKeys("smoketests@email.com");

        try {

            current_AgentWithCPRPage.clickSave();

        } catch(TimeoutException e){

            String errorMessage = Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("ERRMSG-TEXT-SPAN")).getText();
            System.out.println(errorMessage);

            org.testng.Assert.fail("testT004_AddRelatedAgent failed with Errormessage " + errorMessage);

        }
        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Click on the Menu tab and choose 360 Degree Search.\n" +
                "     * Step 2: Go to ID Type and select CPR. Go to the ID Number field and write a CPR Number (2612689066)\n" +
                "     * Step 3: Wait for the search results to appear, and click on the name (Dam, Jesper)\n" +
                "     * Step 4: On the Related agents line click on Add Agent.\n" +
                "     * Step 5: In the new window select Person with CPR and click Next.\n" +
                "     * Step 6: In the field called CPR Number fill in a CPR Number (2612689066).\n" +
                "     * Step 7: Fill out the field called Representation Limitation with This is a geb test!\n" +
                "     * Step 8: Under Agent Type select Agent.\n" +
                "     * Step 9: In the field called Home Phone fill in a home phone number (99887766).\n" +
                "     * Step 10: In the field called Mobile Phone fill in a mobile phone number (55443322).\n" +
                "     * Step 11: In the field called Contact Email fill in a contact email (test@email.com).\n" +
                "     * Step 12: Click on the Save button.\n" +
                "     * Step 13: Check that the Agent has been added correctly.\n" +
                "     * Step 14: Click on the added Agent to check that you can acces the Agents personal page.\n" +
                "     * Final Step: Logout and exit PSRM.");

    }

    /**
     * Step 1: Click on the Menu tab and choose 360 Degree Search.
     * Step 2: Go to Full Name and type in a full name (Dahl, Gitte).
     * Step 3: Click on the name appearing from the search results.
     * Step 4: Check that the page has loaded correctly and that the correct name is displayed.
     * Step 5: Click on the Account tab and verify that everything has loaded correctly and that the correct name is displayed.
     * Step 6: Click on the Tax Role tab and verify that everything has loaded correctly and that the correct name is displayed.
     * Step 7: Click on the Financial Information tab and verify that everything has loaded correctly and that the correct name is displayed.
     * Final Step: Logout and exit PSRM.
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT005_PersonOverview() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT005_PersonOverview()");
        // 0) Test data
        final String personCPRNr = "050574-9855";
        final String personFirstName = "Gitte";
        String personLastName = "Dahl";
        final String personFullName;
        personFullName = personLastName + ", " + personFirstName;
        final String personFullNameNormalOrder;
        personFullNameNormalOrder = personFirstName + " " + personLastName;

        Navi.open360DegreeSearch(driver);

        _360TabPage current_360TabPage = new _360TabPage(driver);

        current_360TabPage.selectDropdown(_360TabPage.DROP_PERSON);

        _360PersonPage current_360PersonPage = new _360PersonPage(driver);

        current_360PersonPage.search(personFullName);

        _360PersonsListPage current_360PersonsListPage = new _360PersonsListPage(driver);

        current_360PersonsListPage.findPersonAndClickNameLink(personFullNameNormalOrder);

        _360PersonDetailsPage current_360PersonDetailsPage = new _360PersonDetailsPage(driver);

        current_360PersonDetailsPage.informationPersonNameLink();

        current_360PersonDetailsPage.informationPersonNameLinkContains(personFullNameNormalOrder.toLowerCase());

        current_360PersonDetailsPage.cprNumberContains(personCPRNr);

        Navi.openAccount(driver);

        final _360AccountDetailsPage current_360AccountDetailsPage = new _360AccountDetailsPage(driver);

        current_360AccountDetailsPage.personIdContains(personFullNameNormalOrder.toLowerCase());

        Navi.openTaxRole(driver);

        TaxRolePage currentTaxRolePage = new TaxRolePage(driver);

        currentTaxRolePage.waitForLoadedPage();

        Navi.openFinancialInformation(driver);

        final _360FinancialInformationPage current_360FinancialInformationPage = new _360FinancialInformationPage(driver);

        current_360FinancialInformationPage.personIdContains(personFullNameNormalOrder.toLowerCase());

        current_360FinancialInformationPage.financialHistoryZone();

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Click on the Menu tab and choose 360 Degree Search.\n" +
                "     * Step 2: Go to Full Name and type in a full name (Dahl, Gitte).\n" +
                "     * Step 3: Click on the name appearing from the search results.\n" +
                "     * Step 4: Check that the page has loaded correctly and that the correct name is displayed.\n" +
                "     * Step 5: Click on the Account tab and verify that everything has loaded correctly and that the correct name is displayed.\n" +
                "     * Step 6: Click on the Tax Role tab and verify that everything has loaded correctly and that the correct name is displayed.\n" +
                "     * Step 7: Click on the Financial Information tab and verify that everything has loaded correctly and that the correct name is displayed.\n" +
                "     * Final Step: Logout and exit PSRM.");

    }

    /**
     * Step 1: Click on the Menu tab and choose 360 Degree Search.
     * Step 2: Wait for the full view to be shown and go to the check box called Include Inactive and click on it.
     * Step 3: Go to the field called Full Name and type in the full name of an inactive debtor (Hansen, Hans).
     * Step 4: Click on the name appearing from the search results.
     * Step 5: Go to Current Context on the bar on the far right, and click on Show Account Context (Show Account Context is the three lines right below the three lines next to the displayed name (Hansen, Hans)).
     * Step 6: In the bar appearing after clicking on Show Account Context go to Reactivate Debtor and click on it.
     * Step 7: In the field called Reactivation Reason fill in a reactivation reason (gfreag)
     * Step 8: Go to the Save button and click on it. Make sure no error messages are displayed.
     * Final Step: Logout and exit PSRM.
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT006_ReactivateDebtor() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT006_ReactivateDebtor");

        String personFullName = "Hansen, Hans";
        String personCPR = "2010693253";
        String reactivationReason = "gfreag";

        Navi.open360DegreeSearch(driver);

        _360TabPage current_360TabPage = new _360TabPage(driver);

        current_360TabPage.selectDropdown(_360TabPage.DROP_PERSON);

        _360PersonPage current_360PersonPage = new _360PersonPage(driver);

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.id("includeInactive"), FrameType.TAB_PAGE, By.id("idValue"));

        current_360PersonPage.searchForID(_360PersonPage.ID_TYPE_CPR, personCPR);

        Navi.tabPage(driver);

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.xpath("//tbody[@id='dataExplorerTableBody1']//td[@class='grid paddedCell explorerGrid nowrap reg cursorDefault clickable']"),
                FrameType.ZONE_MAP_FRAME_1, By.id("personInfo_name"));

        Navi.openReactivateDebtor(driver);

        ReactivateDebtorPage currentReactivateDebtorPage = new ReactivateDebtorPage(driver);

        currentReactivateDebtorPage.reactivationReason().sendKeys(reactivationReason);

        currentReactivateDebtorPage.clickReactivate();

        try{

            driver.findElement(By.id("ERRMSG-TEXT-SPAN")).getText().equals(null);

            (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage").switchTo().frame("zoneMapFrame_1")
                            .findElement(By.xpath("/html/body/div[2]/div[3]/div[1]/label")).isDisplayed();
                }
            });

            Navi.logoutPSRM(driver);

        }catch(TimeoutException ex){

            Navi.uiMap(driver);

            System.out.println(driver.findElement(By.id("ERRMSG-TEXT-SPAN")).getText());
            //Write some nonsense to fail
            org.testng.Assert.fail("testT006_ReactivateDebtorFailed");

            Navi.logoutPSRM(driver);

        }catch(NoSuchElementException e){

            (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage").switchTo().frame("zoneMapFrame_1")
                            .findElement(By.xpath("/html/body/div[2]/div[3]/div[1]/label")).isDisplayed();
                }
            });

            Navi.logoutPSRM(driver);

        }

        System.out.println("     * Step 1: Click on the Menu tab and choose 360 Degree Search.\n" +
                "     * Step 2: Wait for the full view to be shown and go to the check box called Include Inactive and click on it.\n" +
                "     * Step 3: Go to the field called Full Name and type in the full name of an inactive debtor (Hansen, Hans).\n" +
                "     * Step 4: Click on the name appearing from the search results.\n" +
                "     * Step 5: Go to Current Context on the bar on the far right, and click on Show Account Context (Show Account Context is the three lines right below the three lines next to the displayed name (Hansen, Hans)).\n" +
                "     * Step 6: In the bar appearing after clicking on Show Account Context go to Reactivate Debtor and click on it.\n" +
                "     * Step 7: In the field called Reactivation Reason fill in a reactivation reason (gfreag)\n" +
                "     * Step 8: Go to the Save button and click on it. Make sure no error messages are displayed.\n" +
                "     * Final Step: Logout and exit PSRM.");

    }

    /**
     * Step 1: Click on the Menu tab and choose 360 Degree Search.
     * Step 2: Go to Full Name and type in a full name (Steffensen, Simon).
     * Step 3: Click on the name appearing from the search results.
     * Step 4: Go to Current Context on the bar on the far right, and click on Show Account Context (Show Account Context is the three lines right below the three lines next to the displayed name (Steffensen, Simon)).
     * Step 5: In the bar appearing after clicking on Show Account Context go to Create Demand Letter Process and click on it.
     * Step 6: Go to the checkbox under Selected? and select it, then click on Create Collection Case.
     * Step 7: Make sure that no error messages are present.
     * Final Step: Logout and exit PSRM.
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT007_AddRelatedDocumentCard() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT007_AddRelatedDocumentCard");
        //Has to be a person with an existing claim
        String personFirstName = "Simon";
        String personLastName = "Steffensen";
        final String personFullName = personLastName + ", " + personFirstName;
        final String personFullNameNormalOrder = personFirstName + " " + personLastName;
        String inputID = "20170101";

        Navi.open360DegreeSearchPage(driver);

        _360PersonPage currentpage = new _360PersonPage(driver);

        currentpage.search(personFullName);

        final _360PersonsListPage current_360PersonsListPage = new _360PersonsListPage(driver);

        current_360PersonsListPage.findPersonAndClickNameLink(personFullNameNormalOrder);

        _360PersonDetailsPage current_360personDetailsPage = new _360PersonDetailsPage(driver);

        current_360personDetailsPage.cprNumber();

        Navi.tabMenu(driver);

        Navi.openTaxRole(driver);

        Navi.tabPage(driver);

        TaxRolePage currentTaxRolePage = new TaxRolePage(driver);

        currentTaxRolePage.waitForLoadedPage();

        Navi.waitForDisplayedElement(FrameType.DASHBOARD, driver, By.id("title_heading_214"));

        Navi.tabPage(driver);

        currentTaxRolePage.clickAddRelatedDocumentCard();

        CreateDocumentCardPage currentCreateDocumentCardPage = new CreateDocumentCardPage(driver);

        currentCreateDocumentCardPage.journalisingId().sendKeys(inputID);

        currentCreateDocumentCardPage.clickSave();

        try{

            Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.xpath("/html/body/table/tbody/tr/td/a/span"));

            assert(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/a/span")).getText().isEmpty());

            System.out.println(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/a/span")).getText());

            System.out.println(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/a/span")).getAttribute("innerHTML"));

            currentTaxRolePage = new TaxRolePage(driver);

            currentTaxRolePage.waitForLoadedPage();

            Navi.logoutPSRM(driver);

        }catch(AssertionError ex){

            Navi.uiMap(driver);
            //driver.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("uiMap");

            System.out.println(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/a/span")).getText());
            //Write some nonsense to fail
            org.testng.Assert.fail("testT007_AddRelatedDocumentCardFailed");

            Navi.logoutPSRM(driver);

        }catch(TimeoutException ex){

            Navi.logoutPSRM(driver);

        }

        System.out.println("     * Step 1: Click on the Menu tab and choose 360 Degree Search.\n" +
                "     * Step 2: Go to Full Name and type in a full name (Steffensen, Simon).\n" +
                "     * Step 3: Click on the name appearing from the search results.\n" +
                "     * Step 4: Go to Current Context on the bar on the far right, and click on Show Account Context (Show Account Context is the three lines right below the three lines next to the displayed name (Steffensen, Simon)).\n" +
                "     * Step 5: In the bar appearing after clicking on Show Account Context go to Create Demand Letter Process and click on it.\n" +
                "     * Step 6: Go to the checkbox under Selected? and select it, then click on Create Collection Case.\n" +
                "     * Step 7: Make sure that no error messages are present.\n" +
                "     * Final Step: Logout and exit PSRM.");

    }

}
