package pageobjects.smoketests.payments;

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
 * Created by msl on 12-07-2017.
 */
public class TodoOverviewPage {

    private WebDriver driver;

    public TodoOverviewPage(WebDriver driver) {
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


    public void searchAndClickTodo(String todoType, String relationToID) {
        List<WebElement> table = table();
        int j = 1;

        // The loop has to iterate with 2 every time, as there's a row between each to-do in the table.
        for (int i = 0; i < table.size(); i+=2) {
            List<WebElement> columnsOfRow = columnsOfRowInTableAt(table, i);

            String rowTodoType = columnsOfRow.get(7).getText();
            String rowRelationshipToID = columnsOfRow.get(11).getText();

            if (rowTodoType.equals(todoType) && rowRelationshipToID.equals(relationToID)) {
                // The correct link has now been found and j indicated the row the link is placed in. With j the xpath
                // is created and the link is clicked.
                String xpath = "/html/body/div[1]/div/table/tbody/tr[5]/td/div/table/tbody/tr[" + j + "]/td[6]/a/span";
                Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.xpath(xpath), FrameType.TAB_PAGE, By.id("TD_TYPE_CD"));
                break;
            }

            j++;
        }
    }
}
