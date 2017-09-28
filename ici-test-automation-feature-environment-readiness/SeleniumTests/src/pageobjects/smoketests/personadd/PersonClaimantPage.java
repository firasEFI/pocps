package pageobjects.smoketests.personadd;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by asol on 01-06-2017.
 */
public class PersonClaimantPage {

    private WebDriver driver;

    public PersonClaimantPage(WebDriver driver) {

        this.driver = driver;

        Navi.uiMap(driver);

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });
    }

    public WebElement changeReason(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_changeReason"));

    }

    public Select language(){

        Navi.waitForDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_language"));

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return  d.findElements(By.cssSelector("#boGroup_language option")).size() > 1;
            }
        });

        Navi.waitForDisplayedElement(FrameType.UI_MAP, driver, By.xpath("//select[@id='boGroup_language']/option[@value='ENG']"));

        return new Select(driver.findElement(By.id("boGroup_language")));
    }

    public WebElement name(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("entityName_0"));

    }

    public void isPrimaryId(){

        Navi.patientlyClick(driver, By.id("isPrimaryId_0"));

    }

    public Select idType(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("idType_0"));

    }

    public WebElement personIdNumber(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("personIdNumber_0"));

    }

    public Select phoneType(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("phoneType_0"));

    }

    public WebElement phoneNumber(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("phone_0"));

    }

    public WebElement phoneNumberExtension(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("extension_0"));

    }

    public WebElement startDate(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("startDate_0"));

    }

    public WebElement endDate(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("endDate_0"));

    }

    public Select addressType(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("addressType_0"));

    }

    public WebElement addressId(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("addressId_0"));

    }

    public Select addressPriority(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("addressPriority_0"));

    }

    public Select deliverable(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("deliverable_0"));

    }

    public WebElement emailAddress(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_emailAddress"));

    }

    public Select agreementType(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("agreeType_0"));

    }

    public Select claimantType(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_claimType"));

    }

    public Select paymentType(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_payType"));

    }

    public Select systemAgreement(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_sysAgree"));

    }

    public Select currencyCode(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_currency"));

    }

    public Select allowedToWriteDown(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_allowWriteDown"));

    }

    public Select allowedToCreateRecoveryClaims(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_allowCreateRec"));

    }

    public Select allowedToCreateOffsetClaims(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_allowCreateOffset"));

    }

    public Select allowedToCreateTransports(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_allowCreateTrans"));

    }

    public Select allowedWithdraw(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_allowWithdraw"));

    }

    public Select allowWriteUpFromWriteDownAdjustment(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_allowWriteUpFromWriteDownAdjustment"));

    }

    public Select allowWriteUpCancellationWriteDownPayment(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_allowWriteUpCancellationWriteDownPayment"));

    }

    public Select allowWriteDownCancellationWriteUpAdjustment(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_allowWriteDownCancellationWriteUpAdjustment"));

    }

    public Select allowWriteDownCancellationWriteUpPayment(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_allowWriteDownCancellationWriteUpPayment"));

    }

    public Select allowIncorrectMainReport(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_allowIncorrectMainReport"));

    }

    public Select allowWriteUpAdjustment(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_allowWriteUpAdjustment"));

    }

    public Select allowResubmittningClaims(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_allowResubmittningClaims"));

    }

    public WebElement systemReporter(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_systemReporter"));

    }

    public Select settlementFrequency(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_settlFreq"));

    }

    public Select settlementMethod(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_settlMeth"));

    }

    public void clickSave(){

        Navi.patientlyClick(driver, By.id("SAVE_BTN_MP"));

    }

}
