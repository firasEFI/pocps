package smoketests.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import icisel.utils.driver.LocalDriverManager;
import pageobjects.smoketests.psrm_navigation.Navi;

/**
 * Created by asol on 19-05-2017.
 */
public class LoginTests {

    /**
     * Step 1: Log in to PSRM.
     * Final step: Log out of PSRM.
     */
    @Test
    public void testLogin() {

        final WebDriver driver = LocalDriverManager.getDriver();

        Navi.loginPSRM(driver);

        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage").findElement(By.id("row1_headSect")).getText().toLowerCase().contains("user id owner");
            }
        });

        Navi.logoutPSRM(driver);

    }

}
