package pageobjects.smoketests.payments;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by asol on 08-08-2017.
 */
public class PlacePaymentFromWaitingaccountPage {

    private WebDriver driver;

    public static final String EXCESS = "ACCT-EXCESS";     // Indbetal på skyldnerens konto for overskydende beløb
    public static final String PAYACC = "DK-PAYACC";       // Automatisk Dækket: Via skyldner konto
    public static final String PAYOCR = "DK-PAYOCR";      // Automatisk Dækket: Via OCR-linje

    public static final String OTH = "OTH"; // Anden

    public PlacePaymentFromWaitingaccountPage(WebDriver driver) {
        this.driver = driver;

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });

        Navi.uiMap(driver);
    }

    private WebElement accountID() {
        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_accountId"));
    }
    private WebElement paymentEventID() {
        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_paymentEventId"));
    }
    private WebElement effectiveDate() {
        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_effectiveDate"));
    }

    private WebElement debtorAccount() {
        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_debtorAcc"));
    }

    public WebElement otherReason() {
        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_transferReasonOther"));
    }

    public Select transferType() {
        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_transferType"));
    }

    public void searchAccountID(String accountID) {
        accountID().sendKeys(accountID);
    }

    public void searchPaymentEventID(String paymentEventID) {
        paymentEventID().sendKeys(paymentEventID);
    }

    public void chooseDebtorByID(String debtorAccountID) {
        debtorAccount().sendKeys(debtorAccountID);
    }

    public void selectDistributionRule(String rule) {
        Select dropdown = Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_distrRule"));
        dropdown.selectByValue(rule);
    }

    public void selectReason(String reason) {
        Select dropdown = Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_transferReason"));
        dropdown.selectByValue(reason);
    }




    public void clickSave() {
        Navi.patientlyClickAttempt(driver, FrameType.UI_MAP, By.xpath("//input[@class='oraButton oraDefault' and @oramdlabel='SAVE_BTN_LBL' and @value='Gem']")
                , FrameType.ZONE_MAP_FRAME_2, By.id("processFlowId"));
    }

    public void searchEffectiveDate(String effectiveDate) {
        // Make sure nothing is written in the field already.
        effectiveDate().clear();
        effectiveDate().sendKeys(effectiveDate);
    }

}
