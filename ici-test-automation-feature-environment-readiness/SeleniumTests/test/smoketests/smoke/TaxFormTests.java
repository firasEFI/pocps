package smoketests.smoke;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import icisel.utils.driver.LocalDriverManager;
import icisel.utils.driver.RetryAnalyzer;
import pageobjects.smoketests.processflows.HearingCasePage;
import pageobjects.smoketests.psrm_navigation.Navi;
import pageobjects.smoketests.taxform.DocumentLocatorPage;
import pageobjects.smoketests.taxform.PartPage;
import pageobjects.smoketests.taxform.TaxFormFormularPage;
import utils.FrameType;

/**
 * Created by asol on 10-07-2017.
 */
public class TaxFormTests {

    /**
     * Step 1: Login to PSRM. Step 2: Click on Menu -> Formularer ->
     * Fordringsformular -> Soeg Step 3: In the field called “Soeg efter” select
     * “Dokument locator” Step 4: Search for a relevant document locator
     * (125448613) Step 5: Click on the link resulting from the search results.
     * Step 6: Click on the tab called “Log” Step 7: In the column called “Sag”
     * find the link called “Hearing Case Process Flow” and click on it. Step 8:
     * Make sure a document card is attached. Step 9: Click on approve. Step 10:
     * Make sure the status has changed to approved. Final Step: Logout of PSRM.
     * 
     */
    @Test(skipFailedInvocations = true, retryAnalyzer = RetryAnalyzer.class)
    // @Test
    public void testT001_HearingCase() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        // final WebDriverScreenshotListener webDriverScreenshotListener = new
        // WebDriverScreenshotListener();
        // driver.register(webDriverScreenshotListener);

        String documentLocator = "12344321";// "002000000015";

        Navi.loginPSRM(driver);

        Navi.openTaxFormSearch(driver);

        PartPage current_PartPage = new PartPage(driver);

        current_PartPage.multiQueryZoneFilters().selectByValue("C1-TXFRMQ7");

        DocumentLocatorPage current_DocumentLocatorPage = new DocumentLocatorPage(driver);

        current_DocumentLocatorPage.filter().sendKeys(documentLocator);

        current_DocumentLocatorPage.clickSearch();

        // /html/body/div[1]/div/table/tbody/tr[5]/td/div/table/tbody/tr[3]/td[6]/a/span

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE,
                By.xpath("/html/body/div[1]/div/table/tbody/tr[8]/td/div/table/tbody/tr/td[4]/a/span"),
                FrameType.ZONE_MAP_FRAME_4, By.id("statusDescription"));

        TaxFormFormularPage current_TaxFormFormularPage = new TaxFormFormularPage(driver);

        current_TaxFormFormularPage.clickLog(driver);

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE,
                By.xpath("/html/body/div[1]/div/table/tbody/tr[5]/td/div/table/tbody/tr[3]/td[6]/a/span"),
                FrameType.ZONE_MAP_FRAME_2, By.id("boStatus"));

        Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver,
                By.xpath("/html/body/div[5]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[2]"));

        Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver,
                By.xpath("/html/body/div[8]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[3]"));

        final HearingCasePage current_HearingCasePage = new HearingCasePage(driver);

        current_HearingCasePage.clickApprove();

        (new WebDriverWait(driver, Navi.LONGWAITTIME)).ignoring(StaleElementReferenceException.class)
                .until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return current_HearingCasePage.status().getText().equals("Godkendt");
                    }
                });

        assert (current_HearingCasePage.status().getText().equals("Godkendt"));

        System.out.println(current_HearingCasePage.status().getText());

        Navi.logoutPSRM(driver);

    }

}
