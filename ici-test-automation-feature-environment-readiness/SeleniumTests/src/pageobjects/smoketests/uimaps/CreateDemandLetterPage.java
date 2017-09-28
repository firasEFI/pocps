package pageobjects.smoketests.uimaps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by asol on 02-06-2017.
 */
public class CreateDemandLetterPage {

    private WebDriver driver;

    public CreateDemandLetterPage(WebDriver driver) {

        this.driver = driver;

        //final JavascriptExecutor executor = (JavascriptExecutor) LocalDriverManager.getDriver();

        /*(new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });*/

        Navi.uiMap(driver);

    }

    public Select collectionStep(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("collCaseType"));

    }

    public Select demandLetterType(){

        return Navi.patientlySelectDisplayedElement(FrameType.UI_MAP, driver, By.id("demandType"));

    }

    public WebElement isSelected(){

        return Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("selected_0"));

    }

    public void clickCreateCollectionCase(){

        Navi.patientlyClick(driver, By.id("ok"));

    }

}
