package pageobjects.smoketests.taxrole;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by asol on 31-05-2017.
 */
public class TaxRolePage {

    private WebDriver driver;

    public TaxRolePage(final WebDriver driver) {

        this.driver = driver;

        Navi.tabPage(driver);

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 40)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });
    }

    /**
     * Not sure if below methods should be public.
     */
    public WebElement cases(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("zoneHeader1"));

    }

    public WebElement notesRelatedtoDebtor(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("zoneHeader2"));

    }

    public WebElement documentCardsRelatedtoDebtor(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("zoneHeader4"));

    }

    public WebElement ocrLinesRelatedtoDebtor(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("zoneHeader6"));

    }

    public WebElement accountPaymentEvents(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("zoneHeader7"));

    }

    public WebElement payouts(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("zoneHeader8"));

    }

    public WebElement retrieveSuspenseHistory(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("zoneHeader9"));

    }

    public WebElement suppressionClaimsforAccount(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("zoneHeader10"));

    }

    public void clickAddNote(){

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE,
                By.xpath("//span[@id='zoneHeader2']//a[@class='oraZoneHeaderLink' and not(@disabled) and text() = 'Tilføj']"),
                FrameType.UI_MAP, By.id("boGroup_category"));

    }

    public void clickAddRelatedDocumentCard(){

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE,
                By.xpath("//span[@id='zoneHeader4']//table[@class='zoneHeader']//a[@class='oraZoneHeaderLink' and not(@disabled) and text() = 'Tilføj']"),
                FrameType.UI_MAP, By.id("boGroup_journalisingId"));

    }

    public void waitForLoadedPage(){

        Navi.tabPage(driver);

        (new WebDriverWait(driver, 60)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = cases().isDisplayed();
                boolean b2 = notesRelatedtoDebtor().isDisplayed();
                boolean b3 = documentCardsRelatedtoDebtor().isDisplayed();
                boolean b4 = ocrLinesRelatedtoDebtor().isDisplayed();
                boolean b5 = accountPaymentEvents().isDisplayed();
                boolean b6 = payouts().isDisplayed();
                boolean b7 = retrieveSuspenseHistory().isDisplayed();
                boolean b8 = suppressionClaimsforAccount().isDisplayed();
                return b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8;
            }
        });

    }

}
