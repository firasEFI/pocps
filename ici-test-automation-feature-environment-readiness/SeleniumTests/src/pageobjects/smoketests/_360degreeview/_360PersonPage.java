package pageobjects.smoketests._360degreeview;

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
 * Created by msl on 24-05-2017.
 */

public class _360PersonPage {

    public static final String ID_TYPE_CPR = "CPR";     // Debtors and Claimants< DK-360QRYQ1

    private WebDriver driver;

    public _360PersonPage(WebDriver driver) {
        this.driver = driver;

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });

        Navi.tabPage(driver);
    }

    public WebElement fullNameField(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("fullName"));

    }

    private WebElement firstNameField(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("firstName"));

    }

    private WebElement lastNameField(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("lastName"));

    }

    private WebElement debtorIdField() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("internalPersonId"));
    }

    private Select idTypeField(){

        return Navi.patientlySelectDisplayedElement(FrameType.TAB_PAGE, driver, By.id("idType"));

    }

    public WebElement idNumberField(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("idValue"));

    }

    private WebElement taxTypeField(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("taxType"));

    }

    private WebElement refreshSearch(){

        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("anTLZ1Refresh"));

    }

    public void search(String fullNameQuery) {

        Navi.tabPage(driver);

        fullNameField().clear();
        fullNameField().sendKeys(fullNameQuery);
        //refreshSearch().click();
        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.id("anTLZ1Refresh"), FrameType.TAB_PAGE,
                By.xpath("//tbody[@id='dataExplorerTableBody1']//td[@class='grid paddedCell explorerGrid nowrap reg cursorDefault clickable']"));
    }

    public void searchFirstName(String firstNameQuery) {
        firstNameField().sendKeys(firstNameQuery);
        //refreshSearch().click();
        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.id("anTLZ1Refresh"), FrameType.TAB_PAGE,
                By.xpath("//tbody[@id='dataExplorerTableBody1']//td[@class='grid paddedCell explorerGrid nowrap reg cursorDefault clickable']"));
    }

    public void searchForID(String idType, String idNumberQuery) {
        idTypeField().selectByValue(idType);
        idNumberField().sendKeys(idNumberQuery);
        //refreshSearch().click();
        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.id("anTLZ1Refresh"), FrameType.TAB_PAGE,
                By.xpath("//tbody[@id='dataExplorerTableBody1']//td[@class='grid paddedCell explorerGrid nowrap reg cursorDefault clickable']"));
    }

    public void searchForDebtorID(String debtorID) {
        debtorIdField().sendKeys(debtorID);
        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.id("anTLZ1Refresh"), FrameType.TAB_PAGE,
                By.xpath("//tbody[@id='dataExplorerTableBody1']//td[@class='grid paddedCell explorerGrid nowrap reg cursorDefault clickable']"));
    }
}
