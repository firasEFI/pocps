package findus_pageobjects;

import icisel.pageobjects.elements.PageElement;
import org.openqa.selenium.By;

import javax.sql.rowset.Predicate;

public class Accordion {

    private PageElement accordionElement;
    private By waitFor;

    public Accordion(PageElement element) {
        this.accordionElement = element;
    }

    public Accordion(PageElement element, By waitFor) {
        this.accordionElement = element;
        this.waitFor = waitFor;
    }

    private boolean isExpanded() {
        return accordionElement.findElement(By.xpath(".//img[contains(@src, 'discloseExpanded_ena.png')]")).isDisplayed();
    }

    public void ensureExpanded() {
        if(!isExpanded()) {
            accordionElement.click();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(waitFor != null)
            new PageElement(accordionElement.frame, waitFor).waitFor();
    }

    public String getHeader () {
        return accordionElement.getText().trim();
    }

}
