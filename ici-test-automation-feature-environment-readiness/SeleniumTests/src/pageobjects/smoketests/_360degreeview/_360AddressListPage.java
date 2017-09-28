package pageobjects.smoketests._360degreeview;

import java.util.List;

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
 * Created by asol on 31-05-2017.
 */
public class _360AddressListPage {

    private WebDriver driver;

    public _360AddressListPage(WebDriver driver) {
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

    public List<WebElement> columnsOfRowInTableAt(List<WebElement> elements, int index) {
        List<WebElement> columns = elements.get(index).findElements(By.tagName("td"));
        return columns;
    }

    public WebElement getAddress(List<WebElement> columns) {
        return columns.get(3);
    }


    public void findAddressInTable(String address) {
        List<WebElement> table = table();
        boolean addressNotFound = true;
        int i = 0;

        while (addressNotFound && i != table.size()) {
            String addressFromTable = getAddress(columnsOfRowInTableAt(table, i)).getText();
            if (addressFromTable.equals(address)) {
                getAddress(columnsOfRowInTableAt(table, i)).click();
                addressNotFound = false;
            }
            else {
                if (i <= table.size()) {
                    i++;
                } else {
                    break;
                }
            }
        }
    }
}
