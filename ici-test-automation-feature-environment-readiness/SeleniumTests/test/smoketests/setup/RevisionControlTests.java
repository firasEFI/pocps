package smoketests.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import icisel.utils.driver.LocalDriverManager;
import icisel.utils.driver.RetryAnalyzer;
import pageobjects.smoketests.psrm_navigation.Navi;

/**
 * Created by asol on 19-05-2017.
 */
public class RevisionControlTests {

    /**
     * Step 1: Click on the Admin tab and choose B → Business Object → Search.
     * Step 2: In the new window write Account - Add Account and Relationships in the Description field, and click on the Search button.
     * Step 3: Move to the dashboard on the far right. Under the zone called Revision Control click on Check Out.
     * Step 4: Wait for the dashboard to refresh. Under the zone called Revision Control click on Revert.
     * Step 5: Click OK in the new window.
     * Final Step: Logout and exit PSRM.
     */
    @Test(priority = -2147483648, skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test(priority = -2147483648)
    public void enableTurboMode(){

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();
        //final WebDriverScreenshotListener webDriverScreenshotListener = new WebDriverScreenshotListener();
        //driver.register(webDriverScreenshotListener);

        Navi.loginPSRM(driver);

        String boString = "Account - Add Account and Relationships";

        Navi.openFBusinessObjectSearch(driver);

        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return Navi.getHandleToWindow("Forretningsobjekt søgning", d).findElement(By.id("BUS_OBJ_CD")).isDisplayed();
            }
        });

        driver.findElement(By.id("DESCR")).sendKeys(boString);

        driver.findElement(By.id("IM_DESCR")).click();

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return Navi.getHandleToWindow("Forretningsobjekt", d).switchTo()
                        .defaultContent().switchTo().frame("main").switchTo().frame("tabPage").findElement(By.id("BUS_OBJ_CD")).isDisplayed();
            }
        });

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("dashboard").switchTo().frame("zoneMapFrame_106").findElement(By.id("button1_0")).isDisplayed();
            }
        });

        driver.findElement(By.id("button1_0")).click();

        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return Navi.getHandleToWindow("Forretningsobjekt", d).switchTo()
                        .defaultContent().switchTo().frame("main").switchTo().frame("tabPage").findElement(By.id("BUS_OBJ_CD")).isDisplayed();
            }
        });

        (new WebDriverWait(driver, 30)).ignoring(WebDriverException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("dashboard").switchTo().frame("zoneMapFrame_106")
                        .findElement(By.id("statusInfo")).getText().equals("Checked out by Automatic Tester, Geb (GEB).");
            }
        });

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("dashboard").switchTo().frame("zoneMapFrame_106").findElement(By.id("button1_1")).isDisplayed();
            }
        });

        driver.findElement(By.id("button1_1")).click();

        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return Navi.getHandleToWindow("Bekræft revisionshandling", d).findElement(By.xpath("//input[@class='oraButton oraDefault' and @value='OK']")).isDisplayed();
            }
        });

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement click = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='oraButton oraDefault' and @value='OK']")));

        click.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return Navi.getHandleToWindow("Forretningsobjekt", d).switchTo()
                        .defaultContent().switchTo().frame("main").switchTo().frame("dashboard").findElement(By.id("title_heading_102")).isDisplayed();
            }
        });


        Navi.logoutPSRM(driver);

    }

}
