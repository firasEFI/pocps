package pageobjects.smoketests.processflows;

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
 * Created by msl on 07-07-2017.
 */
public class AfdragsordningPage {
    private WebDriver driver;

    public AfdragsordningPage(WebDriver driver) {
        System.out.println("Contructor entered");
        this.driver = driver;

        Navi.zoneMapFrame_2(driver);

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });
    }

    public WebElement part() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.xpath("/html/body/div[3]/div[1]/span/a"));
    }

    private WebElement konto() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.xpath("/html/body/div[3]/div/span/a"));
    }

    private WebElement installmentAmountSuggestion() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.id("installmentAmountSuggestion"));
    }

    private WebElement installmentAmount() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.id("installmentAmount"));
    }

    private WebElement paymentAbilityBudget() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.id("paymentAbilityBudget"));
    }

    private WebElement paymentAbilityTableLookup() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.id("paymentAbilityTableLookup"));
    }

    public boolean checkAfdragsordning(String personFullNameRightOrder, String personFullName, String monthlyPaymentAbility) {
        return part().getText().equals(personFullNameRightOrder)
                && installmentAmountSuggestion().getText().contains(monthlyPaymentAbility)
                && installmentAmount().getText().contains(monthlyPaymentAbility)
                && paymentAbilityBudget().getText().contains(monthlyPaymentAbility)
                && paymentAbilityTableLookup().getText().contains(monthlyPaymentAbility);
    }


}
