package pageobjects.smoketests.uimaps;

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
 * Created by asol on 02-06-2017.
 */
public class CreateFeePage {

    private WebDriver driver;

    public CreateFeePage(WebDriver driver) {

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

    public WebElement accountTextBox(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("accountId"));

    }

    public void clickAccountSearch(){

        Navi.patientlyClick(driver, By.id("accountId_search"));

    }

    public WebElement caseTextBox(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("collectionRelation"));

    }

    public void clickCaseSearch(){

        Navi.patientlyClick(driver, By.id("collectionRelation_search"));

    }


    public Select claimType(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("claimType"));

    }

    public WebElement amount(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("amount"));

    }

    public WebElement effectiveDate(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("effectiveDate"));

    }

    public WebElement dateOfIncorporation(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("dateOfIncorporation"));

    }

    public WebElement lastTimelyPaymentDate(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("lastTimelyPaymentDate"));

    }

    public WebElement courtOrder(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("courtOrder"));

    }

    public WebElement settlementDate(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("settlement"));

    }

    public WebElement claimantsDescription(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("claimantsDescription"));

    }

    public WebElement noteText(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("noteText"));

    }

    public void clickSave() {

        Navi.patientlyClick(driver, By.id("SAVE_BTN_MP"));

    }

}
