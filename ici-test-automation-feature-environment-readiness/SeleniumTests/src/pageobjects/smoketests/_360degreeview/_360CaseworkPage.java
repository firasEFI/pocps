package pageobjects.smoketests._360degreeview;

import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

import java.util.List;

/**
 * Created by msl on 11-07-2017.
 */
public class _360CaseworkPage {

    /*
     * Public fields for ICI-Sel based Selenium tests.
     */

    // Zone elements.
    public PageElement sager = new PageElement(Frames.tabPage, By.id("title_heading_1"));
    public PageElement noter = new PageElement(Frames.tabPage, By.id("title_heading_2"));
    public PageElement dokumentkort = new PageElement(Frames.tabPage, By.id("title_heading_4"));
    public PageElement overblikOverOCRLinjerRelateretTilSkyldner = new PageElement(Frames.tabPage, By.id("title_heading_6"));
    public PageElement betalingshaendelserForKontoen = new PageElement(Frames.tabPage, By.id("title_heading_7"));
    public PageElement udbetalinger = new PageElement(Frames.tabPage, By.id("title_heading_8"));
    public PageElement overblikOverMarkeredeFordringer = new PageElement(Frames.tabPage, By.id("title_heading_9"));
    public PageElement rentefritagedeFordringerForKontoen = new PageElement(Frames.tabPage, By.id("title_heading_10"));

    // Context menu elements.
    public PageElement overblikOverBetaling = new PageElement(Frames.main, By.id("CI_CONTEXTPAYEVENT_subMenuItem0x2"));

    private WebDriver driver;
    private String paymentsForAccountsID = "title_heading_7";
    private String overviewOfPaymentID = "CI_CONTEXTPAYEVENT_subMenuItem0x2";

    public _360CaseworkPage(WebDriver driver) {
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

    public void clickPaymentsForAccount() {

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.id(paymentsForAccountsID)
                , FrameType.TAB_PAGE, By.xpath("/html/body/div[7]/div/table/tbody/tr[3]/td/div/span"));
    }

    public void clickContextForPayment(int paymentNumber) {
        String showContextForPaymentXpath = "/html/body/div[7]/div/table/tbody/tr[5]/td/div/table/tbody/tr[" + paymentNumber + "]/td[3]/img";

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE, By.xpath(showContextForPaymentXpath)
                , FrameType.MAIN, By.id(overviewOfPaymentID));
    }

    public void clickOverviewOfPayment() {
        Navi.patientlyClickAttempt(driver, FrameType.MAIN, By.id(overviewOfPaymentID)
                , FrameType.ZONE_MAP_FRAME_1, By.id("personId"));
    }

    public List<WebElement> table() {

        WebElement element;
        List<WebElement> elements;

        element = Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("dataExplorerTableBody7"));
        elements = element.findElements(By.tagName("tr"));
        return elements;
    }

    private List<WebElement> columnsOfRowInTableAt(List<WebElement> elements, int index) {
        List<WebElement> columns = elements.get(index).findElements(By.tagName("td"));
        return columns;
    }

    public void searchAndClickContextForPayment(String effectiveDate, String amount, String personFullName) {
        List<WebElement> table = table();

        int j = 1; // Used to keep track of what context menu to click
        // The loop has to iterate with 2 every time, as there's a row between each to-do in the table.
        for (int i = 0; i < table.size(); i+=2) {
            List<WebElement> columnsOfRow = columnsOfRowInTableAt(table, i);

            String rowEffectiveDate = columnsOfRow.get(3).getText();
            String rowPaymentID = columnsOfRow.get(4).getText();
            String rowAmount = columnsOfRow.get(5).getText();

            if (rowEffectiveDate.equals(effectiveDate) && rowPaymentID.contains(effectiveDate) && rowPaymentID.contains(amount) && rowPaymentID.contains(personFullName) && rowAmount.contains(amount)) {
                clickContextForPayment(j);
                break;
            }

            j++;
        }
    }

    public void searchAndClickContextForPayment(String amount, String personFullName) {
        List<WebElement> table = table();

        int j = 1; // Used to keep track of what context menu to click?
        // The loop has to iterate with 2 every time, as there's a row between each to-do in the table.
        for (int i = 0; i < table.size(); i+=2) {
            List<WebElement> columnsOfRow = columnsOfRowInTableAt(table, i);

            String rowPaymentID = columnsOfRow.get(4).getText();
            String rowAmount = columnsOfRow.get(5).getText();

            if (rowPaymentID.contains(amount) && rowPaymentID.contains(personFullName) && rowAmount.contains(amount)) {
                clickContextForPayment(j);
                break;
            }

            j++;
        }
    }
}
