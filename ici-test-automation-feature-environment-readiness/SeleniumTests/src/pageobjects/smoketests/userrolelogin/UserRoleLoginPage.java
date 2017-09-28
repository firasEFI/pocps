package pageobjects.smoketests.userrolelogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by asol on 14-07-2017.
 */
public class UserRoleLoginPage {

    private WebDriver driver;

    public UserRoleLoginPage(WebDriver driver) {

           this.driver = driver;

    }

    public WebElement sagsbehandlerGenerelt(){

        return Navi.patientlyFindDisplayedElement(driver, By.id("Sagsbehandler_generel"));

    }

    public void clickSagsbehandlerGenerelt(){

        Navi.patientlyClick(driver, By.id("Sagsbehandler_generel"));

    }

    public WebElement sagsbehandlerGodkender(){

        return Navi.patientlyFindDisplayedElement(driver, By.id("Sagsbehandler_godkender"));

    }

    public void clickSagsbehandlerGodkender(){

        Navi.patientlyClick(driver, By.id("Sagsbehandler_godkender"));

    }

    public WebElement betalingssagsbehandler(){

        return Navi.patientlyFindDisplayedElement(driver, By.id("Betalingssagsbehandler"));

    }

    public void clickBetalingssagsbehandler(){

        Navi.patientlyClick(driver, By.id("Betalingssagsbehandler"));

    }

    public WebElement betalingssagsbehandlerGodkender(){

        return Navi.patientlyFindDisplayedElement(driver, By.id("Betalingssagsbehandler_godkender"));

    }

    public void clickBetalingssagsbehandlerGodkender(){

        Navi.patientlyClick(driver, By.id("Betalingssagsbehandler_godkender"));

    }

    public WebElement fordringshaversagsbehandler(){

        return Navi.patientlyFindDisplayedElement(driver, By.id("Fordringshaversagsbehandler"));

    }

    public void clickFordringshaversagsbehandler(){

        Navi.patientlyClick(driver, By.id("Fordringshaversagsbehandler"));

    }

    public WebElement seSagsbehandlerMedNoter(){

        return Navi.patientlyFindDisplayedElement(driver, By.id("Se-sagsbehandler_med_noter"));

    }

    public void clickSeSagsbehandlerMedNoter(){

        Navi.patientlyClick(driver, By.id("Se-sagsbehandler_med_noter"));

    }

    public WebElement funktionsleder(){

        return Navi.patientlyFindDisplayedElement(driver, By.id("Funktionsleder"));

    }

    public void clickFunktionsleder(){

        Navi.patientlyClick(driver, By.id("Funktionsleder"));

    }

    public WebElement systemadministrator(){

        return Navi.patientlyFindDisplayedElement(driver, By.id("Systemadministrator"));

    }

    public void clickSystemadministrator(){

        Navi.patientlyClick(driver, By.id("Systemadministrator"));

    }

    public WebElement vipSagsbehandler(){

        return Navi.patientlyFindDisplayedElement(driver, By.id("VIP-sagsbehandler"));

    }

    public void clickVIPSagsbehandler(){

        Navi.patientlyClick(driver, By.id("VIP-sagsbehandler"));

    }

    public void clickScumlink(){

        Navi.patientlyClickAttempt(driver, By.id("scumlink"), FrameType.TAB_PAGE, By.id("fullName"));

    }
}
