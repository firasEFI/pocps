package findus_pageobjects;

import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frame;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebList {

    private final Frame frame;
    private final String tableId;

    public WebList(Frame frame, String tableId) {
        if(tableId == null)
            throw new IllegalArgumentException("tableId cannot be null");

        this.frame = frame;
        this.tableId = tableId;
    }

    public void clickCell(String columnHeader, int rowIndex) {
        this.clickCell("Rækkefølge", ((Integer)(rowIndex + 1)).toString(), columnHeader);
    }
    public void clickCell(String columnHeader, String cellValue) {
        this.clickCell(columnHeader, cellValue, columnHeader);
    }
    public void clickCell(String lookupColumnHeader, String lookupValue, String clickColumnHeader) {
        this.getClickableCellFromLookUp(lookupColumnHeader, lookupValue, clickColumnHeader).click();
    }

    public String getCellValue(String columnHeader, int rowIndex) {
        return getCell(columnHeader, rowIndex).getText();
    }
    public String lookupCellValue(String lookupColumnHeader, String lookupValue, String returnColumnHeader) {
        return getCellFromLookUp(lookupColumnHeader, lookupValue, returnColumnHeader).getText();
    }

    private PageElement getCell(String columnHeader, int rowIndex) {
        return this.getCellFromLookUp("Rækkefølge", ((Integer)(rowIndex + 1)).toString(), columnHeader);
    }
    private PageElement getCellFromLookUp(String lookupColumnHeader, String lookupValue, String returnColumnHeader) {
        String xpath = this.getTableXPath() + String.format("/tbody/tr[td[count(ancestor::table[1]/thead[1]//th//tr[not(@style = 'display: none;')]/td[node() = '%1$s']//preceding::th) + 1]//*[normalize-space(node()) = '%2$s']]/td[count(ancestor::table[1]/thead[1]//th//tr[not(@style = 'display: none;')]/td[node() = '%3$s']//preceding::th) + 1]", lookupColumnHeader, lookupValue, returnColumnHeader);

        return new PageElement(this.frame, By.xpath(xpath));
    }
    private PageElement getClickableCellFromLookUp(String lookupColumnHeader, String lookupValue, String returnColumnHeader) {
        String xpath = this.getTableXPath() + String.format("/tbody/tr[td[count(ancestor::table[1]/thead[1]//th//tr[not(@style = 'display: none;')]/td[node() = '%1$s']//preceding::th) + 1]//*[normalize-space(node()) = '%2$s']]/td[count(ancestor::table[1]/thead[1]//th//tr[not(@style = 'display: none;')]/td[node() = '%3$s']//preceding::th) + 1]/a", lookupColumnHeader, lookupValue, returnColumnHeader);

        return new PageElement(this.frame, By.xpath(xpath));
    }

    private String getTableXPath() {
        return String.format("//table[@id = '%1$s']", this.tableId);
    }
}
