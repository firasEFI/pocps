package smoketests.smoke;

import icisel.utils.driver.LocalDriverManager;
import icisel.utils.driver.RetryAnalyzer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.smoketests._360degreeview._360PersonPage;
import pageobjects.smoketests._360degreeview._360TabPage;
import pageobjects.smoketests.processflows.AfdragsordningPage;
import pageobjects.smoketests.psrm_navigation.Navi;
import pageobjects.smoketests.uimaps.CreateAfdragsordningPage;
import pageobjects.smoketests.uimaps.CreateDemandLetterPage;
import pageobjects.smoketests.uimaps.CreateFeePage;
import utils.FrameType;

import java.util.NoSuchElementException;

/**
 * Created by asol on 24-05-2017.
 */
public class ProcessFlowTests {

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
    //@Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    @Test
    public void testT001_CreateDemandLetterProcess() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT001_CreateDemandLetterProcess");

        // Has to be a person with an existing claim
        String personFirstName = "Simon";
        String personLastName = "Steffensen";
        String personFullName = personLastName + ", " + personFirstName;

        Navi.open360DegreeSearch(driver);

        _360TabPage current_360TabPage = new _360TabPage(driver);

        current_360TabPage.selectDropdown(_360TabPage.DROP_PERSON);

        _360PersonPage current_360PersonPage = new _360PersonPage(driver);

        current_360PersonPage.search(personFullName);

        Navi.tabPage(driver);

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//tbody[@id='dataExplorerTableBody1']//td[@class='grid paddedCell explorerGrid nowrap reg cursorDefault clickable']")).isDisplayed();
            }
        });

        WebElement nameLink = driver.findElement(By.xpath("//tbody[@id='dataExplorerTableBody1']//td[@class='grid paddedCell explorerGrid nowrap reg cursorDefault clickable']"));

        nameLink.click();

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage").switchTo().frame("zoneMapFrame_1").findElement(By.id("personInfo_name")).isDisplayed();
            }
        });

        Navi.dashboard(driver);

        Navi.openCreateDemandLetter(driver);

        CreateDemandLetterPage currentCreateDemandLetterPage = new CreateDemandLetterPage(driver);

        //Navi.patientlyClickAttempt();
        currentCreateDemandLetterPage.isSelected().click();

        currentCreateDemandLetterPage.demandLetterType().selectByValue("FDM");

        currentCreateDemandLetterPage.clickCreateCollectionCase();

        try{

            (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage").switchTo().frame("zoneMapFrame_2").findElement(By.id("boStatus")).isDisplayed();
                }            });

            Navi.logoutPSRM(driver);

        }catch(TimeoutException ex){

            driver.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("uiMap");

            System.out.println(driver.findElement(By.xpath("//span[@class='oraErrorText clickable' and @oraerrorvar='ERRMSG-TEXT']")).getText());
            //Write some nonsense to fail
            org.testng.Assert.fail("testT001_CreateDemandLetterProcessFailed");
            Navi.logoutPSRM(driver);

        }catch (NoSuchElementException e){

            (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage").switchTo().frame("zoneMapFrame_2").findElement(By.id("boStatus")).isDisplayed();
                }
            });

            Navi.logoutPSRM(driver);

        }

    }


    /**
     * Step 1: Click on the Menu tab and choose 360 Degree Search.
     * Step 2: Go to Full Name and type in a full name (Steffensen, Simon).
     * Step 3: Click on the name appearing from the search results.
     * Step 4: Go to Current Context on the bar on the far right, and click on Show Account Context (Show Account Context is the three lines right below the three lines next to the displayed name (Steffensen, Simon)).
     * Step 5: In the bar appearing after clicking on Show Account Context go to Create Fee and click on it.
     * Step 6: In the new window click on the magnifying glass next to the field called Case.
     * Step 7: In the dropdown called claim type select "Tillaegsafgift".
     * Step 8: In the field called Amount write in an integer (42).
     * Step 9: Click on Save.
     * Step 10: Make sure the fee has been created correctly.
     * Final Step: Logout and exit PSRM.
     */
    //@Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    @Test
    public void testT002_CreateFee(){

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT002_CreateFee");

        // Has to be a person with an existing claim
        String personFirstName = "Simon";
        String personLastName = "Steffensen";
        String personFullName = personLastName + ", " + personFirstName;

        Navi.open360DegreeSearch(driver);

        _360TabPage current_360TabPage = new _360TabPage(driver);

        current_360TabPage.selectDropdown(_360TabPage.DROP_PERSON);

        _360PersonPage current_360PersonPage = new _360PersonPage(driver);

        current_360PersonPage.search(personFullName);

        Navi.tabPage(driver);

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//tbody[@id='dataExplorerTableBody1']//td[@class='grid paddedCell explorerGrid nowrap reg cursorDefault clickable']")).isDisplayed();
            }
        });

        WebElement nameLink = driver.findElement(By.xpath("//tbody[@id='dataExplorerTableBody1']//td[@class='grid paddedCell explorerGrid nowrap reg cursorDefault clickable']"));

        nameLink.click();

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage")
                        .switchTo().frame("zoneMapFrame_1").findElement(By.id("personInfo_name")).isDisplayed();
            }        });

        Navi.openCreateFee(driver);

        CreateFeePage currentCreateFeePage = new CreateFeePage(driver);

        currentCreateFeePage.claimType().selectByValue("ADD");

        currentCreateFeePage.clickCaseSearch();

        currentCreateFeePage.amount().sendKeys("42");

        currentCreateFeePage.clickSave();

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("CLOSE_BTN_MP")).isDisplayed();
            }
        });

        WebElement closeBTN = driver.findElement(By.id("CLOSE_BTN_MP"));

        closeBTN.click();

        Navi.logoutPSRM(driver);

        }



    /**
     * Step 1: Login to PSRM.
     * Step 2: Click on ”Menu”
     * Step 3: Choose ”360 Graders Soegning”
     * Step 4: In the field ”Fulde Navn” write in a full name (Steffensen, Simon).
     * Step 5: Click on the button “Soeg”.
      * Step 6: Click on the name link resulting from the search.
     * Step 7: On the toolbar on the far right go to the box called “Indevaerende kontekst”, click on “Vis konto kontekst” (“Vis konto kontekst” is the three lines right below the three lines next to the displayed name (Steffensen, Simon)).
     * Step 8: In the bar appearing after clicking “Vis konto kontekst” go to “Opret afdragsordningssag” and click on it.
     * Step 9: In the new window appearing in the field called “Type af afdragsordning” select “Frivillig afdragsordning”.
     * Step 10: In the field called “Note for valg af type afdragsordning” write in some text (asdf).
     * Step 11: Under the section called “Inkluderede fordringer” click on the magnifying glass next to the text field for “Fordring”.
     * Step 12: In the field called “Begrundelse for fjernelse af fordring” select ”Andet”.
     * Step 13: In the field called “Anden aarsag” write in some text (asdf).
     * Step 14: In the field called “Note for at tilfoeje fordring” write in some text (asdf).
     * Step 15: Under the section called “Detaljer for afdragsordning” go to the field called “Afdragsordningsfrekvens” select “Maanedlig”.
      * Step 16: In the field called “Afdragsbeloeb” write in an amount (450).
     * Step 17: In the field called “Note for valg af afdragsbeloeb” write in some text (asdf).
     * Step 18: Press “Gem”.
     * Step 19: Make sure that the afdragsordning has been correctly created.
     * Final step: Logout of PSRM.
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    public void testT003_CreateAfdragsordning(){

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        System.out.println("Running testT003_CreateAfdragsordning");


        String monthlyPaymentability = "450";

        // Has to be a person with an existing claim
        String personFirstName = "Simon";
        String personLastName = "Steffensen";
        String personFullName = personLastName + ", " + personFirstName;
        String selTest = "SELENIUM TEST";
        final String personFullNameRightOrder = personFirstName + " " +personLastName;

        Navi.open360DegreeSearch(driver);

        _360TabPage current_360TabPage = new _360TabPage(driver);

        current_360TabPage.selectDropdown(_360TabPage.DROP_PERSON);

        _360PersonPage current_360PersonPage = new _360PersonPage(driver);

        current_360PersonPage.search(personFullName);

        Navi.tabPage(driver);

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return Navi.findElementsAtTabPage(d, By.linkText(personFullNameRightOrder)).size()>0;
            }
        });

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.linkText(personFullNameRightOrder), FrameType.ZONE_MAP_FRAME_1, By.id("personInfo_idnumber"));

        Navi.openCreateAfdragsordning(driver);

        CreateAfdragsordningPage currentCreateAfdragsordningPage = new CreateAfdragsordningPage(driver);

        currentCreateAfdragsordningPage.creationReasonNote().sendKeys(selTest);

        currentCreateAfdragsordningPage.payplanType().selectByValue("VLT");

        currentCreateAfdragsordningPage.payplanTypeNote().sendKeys(selTest);

        currentCreateAfdragsordningPage.selected_0();

        currentCreateAfdragsordningPage.removeClaimReason().selectByValue("OTH");

        currentCreateAfdragsordningPage.removeClaimReasonOther().sendKeys(selTest);

        currentCreateAfdragsordningPage.addClaimReason().sendKeys(selTest);

        currentCreateAfdragsordningPage.frequency().selectByValue("MON");

        currentCreateAfdragsordningPage.installmentAmount().sendKeys(monthlyPaymentability);

        currentCreateAfdragsordningPage.installmentAmountNote().sendKeys(selTest);

        currentCreateAfdragsordningPage.addressType().selectByValue("DBD");

        currentCreateAfdragsordningPage.fromDate_0().sendKeys("01-01-2020");

        currentCreateAfdragsordningPage.toDate_0().sendKeys("01-02-2020");

        currentCreateAfdragsordningPage.periodNote().sendKeys(selTest);

        currentCreateAfdragsordningPage.clickGem();

        AfdragsordningPage currentAfdragsordningPage = new AfdragsordningPage(driver);

        Assert.assertTrue(currentAfdragsordningPage.checkAfdragsordning(personFullNameRightOrder, personFullName, monthlyPaymentability));

        Navi.logoutPSRM(driver);

    }

    

}
