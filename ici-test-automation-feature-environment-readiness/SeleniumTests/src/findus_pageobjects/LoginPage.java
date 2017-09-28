package findus_pageobjects;

import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

import findus_datamodels.PsrmUserModel;
import findus_pageobjects.synchronization.SynchronizeByElementPresent;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

/**
 * Created by nielsjes on 16-08-2017.
 */
public class LoginPage extends BasePage {

    private static final Synchronizer synchronizer = new SynchronizeByElementPresent(
            new PageElement(Frames.defaultContent, By.id("userId")), 10);

    // Elements
    final Input userId = new Input(Frames.defaultContent, By.id("userId"));
    final Input password = new Input(Frames.defaultContent, By.id("password"));
    final Button loginButton = new Button(Frames.defaultContent, By.id("loginButton"));

    public LoginPage() {
        super(synchronizer);
    }

    // Operations
    public LoginPage UdfyldFormular(PsrmUserModel user) {
        userId.sendKeys(user.getSagsbehanlderId());
        password.sendKeys(user.getPassword());

        return this;
    }

    public LoginPage LoginMedIkkeValidBruger(PsrmUserModel user) {
        this.UdfyldFormular(user).ActivateLogin();

        return new LoginPage();
    }

    public BasePsrmPage LoginMedValidBruger(PsrmUserModel user) {

        this.UdfyldFormular(user).ActivateLogin();
        BasePsrmPage basePsrmPage = new BasePsrmPage();
        basePsrmPage.ensureLanguageIsDanish();
        return basePsrmPage;
    }

    public void ActivateLogin() {
        loginButton.click();
    }
}
