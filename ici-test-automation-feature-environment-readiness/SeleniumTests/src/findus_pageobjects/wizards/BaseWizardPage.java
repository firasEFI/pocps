package findus_pageobjects.wizards;

import findus_pageobjects.BasePage;
import findus_pageobjects.synchronization.SynchronizeByOraPageTitle;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseWizardPage<TReturnPage> extends BasePage {

    protected TReturnPage returnPage;

    public String getErrorText() {
        String errorXPath = "//span[contains(@class, 'oraErrorText')]";

        WebDriver driver = Engine.getDriver();

        PageElement errorElement = new PageElement(Frames.uiMap, By.xpath(errorXPath));

        try {
            return errorElement.getText();
        } catch(Exception ex) {
            return null;
        }
    }

    protected BaseWizardPage(String wizardTitle) {
        super(new SynchronizeByOraPageTitle(wizardTitle));
    }

    protected BaseWizardPage(TReturnPage returnPage, String wizardTitle) {
        super(new SynchronizeByOraPageTitle(wizardTitle));

        if(returnPage == null)
            throw new IllegalArgumentException("returnPage cannot be null");

        this.returnPage = returnPage;
    }
}
