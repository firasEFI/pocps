package pageobjects.smoketests._360degreeview;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by msl on 24-05-2017.
 */
public class _360PersonsListPage {

    private WebDriver driver;

    public _360PersonsListPage(WebDriver driver) {
        this.driver = driver;

        Navi.tabPage(driver);

        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        (new WebDriverWait(driver, 20)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean b1 = executor.executeScript("return document.readyState").equals("complete");
                boolean b2 = (Boolean) executor.executeScript("return jQuery.active == 0");
                return b1 && b2;
            }
        });
    }

    /**
     *
     * @return @elements which is a list of WebElement, i.e. every row in the table.
     */
    public List<WebElement> table() {

        WebElement element;
        List<WebElement> elements;

        element = Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("dataExplorerTableBody1"));
        elements = element.findElements(By.tagName("tr"));
        return elements;
    }

    private List<WebElement> columnsOfRowInTableAt(List<WebElement> elements, int index) {
        List<WebElement> columns = elements.get(index).findElements(By.tagName("td"));
        return columns;
    }

    private WebElement getName(List<WebElement> columns) {
        return columns.get(3);
    }

    public void findPersonAndClickNameLink(String personFullName) {
        boolean personNotFound = true;
        int i = 0;

        // This solution uses the personFullName with By.linkText() to search for the person.
        while(personNotFound && i<5) {
            try {
                Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.linkText(personFullName), FrameType.ZONE_MAP_FRAME_1, By.id("personInfo_idnumber"));

                personNotFound = false;
            } catch (TimeoutException e) {
                i++;
            } catch (NoSuchElementException e){
                i++;
            }
        }
    }
}
