package pageobjects.smoketests.uimaps;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by asol on 21-06-2017.
 */
public class CreateAfdragsordningPage {

    private WebDriver driver;

    public CreateAfdragsordningPage(WebDriver driver) {

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

    public WebElement creationReasonNote() {

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_creationReasonNote"));

    }

    public Select payplanType(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_payplanType"));

    }


    public WebElement payplanTypeNote(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_payplanTypeNote"));

    }

    public WebElement selectedDemandLetter(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_selectedDemandLetter"));

    }

    public void selectedDemandLetter_search(){

        Navi.patientlyClick(driver, By.id("boGroup_selectedDemandLetter_search"));

    }

    public void selected_0() {

        Navi.patientlyClick(driver, By.id("selected_0"));

    }

    public Select removeClaimReason(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_removeClaimReason"));

    }

    public WebElement removeClaimReasonOther(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_removeClaimReasonOther"));

    }

    public WebElement addClaimReason(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_addClaimReason"));

    }

    public Select frequency(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_frequency"));

    }

    public WebElement installmentAmount(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_installmentAmount"));

    }

    public WebElement installmentAmountNote(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_installmentAmountNote"));

    }

    public Select addressType(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_addressType"));

    }

    public WebElement addressTypeNote(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_addressTypeNote"));

    }

    public WebElement debtorName(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_debtorName"));

    }

    public WebElement coName(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_coName"));

    }

    public WebElement address1(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_address1"));

    }

    public WebElement address2(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_address2"));

    }

    public WebElement address3(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_address3"));

    }

    public WebElement postal(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_postal"));

    }

    public WebElement fromDate_0(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("fromDate_0"));

    }

    public WebElement toDate_0(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("toDate_0"));

    }

    public WebElement periodNote(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("boGroup_periodNote"));

    }

    public void clickGem() {
        Navi.patientlyClickAttempt(driver, FrameType.UI_MAP, By.id("SAVE_BTN_MP"), FrameType.ZONE_MAP_FRAME_2, By.id("processFlowId"));
    }
}

