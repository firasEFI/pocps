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
 * Created by asol on 18-07-2017.
 */
public class AgentWithCPRPage {

    private WebDriver driver;

    public AgentWithCPRPage(WebDriver driver) {

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

    public WebElement cprId(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("cprId"));

    }

    public WebElement representationLimitation(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("representationLimitation"));

    }

    public Select relationType(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("relationType"));

    }

    public WebElement phoneNumber(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("phoneNumber"));

    }

    public WebElement mobilePhoneNumber(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("mobilePhoneNumber"));

    }

    public WebElement emailAddress(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("emailAddress"));

    }

    public WebElement debtor_0(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("debtor_0"));

    }

    public void clickDebtorSearch() {

        Navi.patientlyClick(driver, By.id("debtor_search"));

    }

    public WebElement case_0(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("case_0"));

    }

    public void clickCaseSearch() {

        Navi.patientlyClick(driver, By.id("case_search"));

    }

    public void clickSave() {

        Navi.patientlyClickAttempt(driver, FrameType.UI_MAP, By.id("SAVE_BTN_MP"),
                FrameType.TAB_PAGE, By.xpath("/html/body/div[3]/span/table/tbody/tr/td[5]/a"));

    }

}
