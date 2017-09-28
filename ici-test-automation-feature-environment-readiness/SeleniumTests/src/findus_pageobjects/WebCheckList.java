package findus_pageobjects;

import icisel.pageobjects.elements.PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebCheckList {

    private final PageElement tableElement;

    public WebCheckList(PageElement tableElement) {
        if(tableElement == null)
            throw new IllegalArgumentException("tableElement cannot be null");

        this.tableElement = tableElement;
    }

    public boolean isRowChecked(int index) {
        return getRowCheckBoxByIndex(index).isSelected();
    }

    public void checkRow(int index) {
        WebElement checkBox = getRowCheckBoxByIndex(index);

        if(!checkBox.isSelected())
            checkBox.click();
    }

    public void checkRow(String columnHeader, String value) {

    }

    private WebElement getRowCheckBoxByIndex(int index) {
        String xpath = "tbody[" + (index + 1) + "]/tr/td[1]/input";

        return tableElement.findElement(By.xpath(xpath));
    }

    private WebElement getRowCheckBoxByColumnHeaderAndValue() {
        return null;
    }
}
