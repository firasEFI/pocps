package pageobjects.smoketests._360degreeview;

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
 * Created by asol on 26-05-2017.
 */
public class _360PersonDetailsPage {

    private WebDriver driver;

    public _360PersonDetailsPage(WebDriver driver) {
        this.driver = driver;

        Navi.zoneMapFrame_1(driver);

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });

    }

    public WebElement cprNumber() {

        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("personInfo_idnumber"));

    }

    public  WebElement debtorID(){

        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("personInfo_personId"));

    }

    public WebElement informationPersonNameLink(){

        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("personInfo_name"));

    }

    public WebElement addAgentLink(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver,
                By.xpath("/html/body/div[3]/span/table/tbody/tr/td[5]/a"));

    }

    public void informationPersonNameLinkContains(final String personFullName) {
        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return informationPersonNameLink().getText().toLowerCase().contains(personFullName.toLowerCase());
            }
        });
    }

    public void cprNumberContains(final String cprNr) {
        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return cprNumber().getText().contains(cprNr);
            }
        });
    }

    public void clickAddAgent(){

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE,
                By.xpath("/html/body/div[3]/span/table/tbody/tr/td[5]/a"), FrameType.UI_MAP, By.id("input_agentType"));

    }

}
