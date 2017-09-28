package pageobjects.smoketests.payments;

import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

/**
 * Created by asol on 08-08-2017.
 */
public class OverviewOfCoveragePage {

    /*
    * Public fields for ICI-Sel based Selenium tests.
    */
    public PageElement eksternKildeId= new PageElement(Frames.tabPage, By.id("EXT_SOURCE_ID"));
    public PageElement eksternOverfoerselId = new PageElement(Frames.tabPage, By.id("EXT_TRANSMIT_ID"));
    public PageElement bogfoeringsdato = new PageElement(Frames.tabPage, By.id("ACCOUNTING_DT"));
    public PageElement regelvaerdi = new PageElement(Frames.tabPage, By.id("DST_RULE_VALUE"));
    public PageElement indbetalingsdetaljer = new PageElement(Frames.tabPage, By.id("PAY_DTLS"));
    public PageElement betalerkontoId = new PageElement(Frames.tabPage, By.id("ACCT_ID"));
    public PageElement indbetalingsbeloeb = new PageElement(Frames.tabPage, By.id("TENDER_AMT"));
    public PageElement micrId = new PageElement(Frames.tabPage, By.id("MICR_ID"));
    public PageElement kundeId = new PageElement(Frames.tabPage, By.id("CUST_ID"));
    public PageElement navn = new PageElement(Frames.tabPage, By.id("NAME1"));
    public PageElement indbetalingskildeId = new PageElement(Frames.tabPage, By.id("TNDR_CTL_ID"));
    public PageElement tjekNummer = new PageElement(Frames.tabPage, By.id("CHECK_NBR"));
    public PageElement eksterntReferenceId = new PageElement(Frames.tabPage, By.id("EXT_REFERENCE_ID"));
    public PageElement bankRoutingNummer = new PageElement(Frames.tabPage, By.id("ROUTING_NBR"));
    public PageElement eksternKontoId = new PageElement(Frames.tabPage, By.id("EXT_ACCT_ID"));
    public PageElement eksterntKontoNavn = new PageElement(Frames.tabPage, By.id("ENTITY_NAME"));
    public PageElement betalingshaendelseProcesId = new PageElement(Frames.tabPage, By.id("PEVT_PROCESS_ID"));
    public PageElement betalingshaendelsesId = new PageElement(Frames.tabPage, By.id("PAY_EVENT_ID"));

    private WebDriver driver;

    public OverviewOfCoveragePage(WebDriver driver) {
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

    public WebElement externalSourceID() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("EXT_SOURCE_ID"));
    }
    public WebElement externalTransactionID() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("EXT_TRANSMIT_ID"));
    }
    public WebElement accountingDate() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("ACCOUNTING_DT"));
    }
    public WebElement ruleValue() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("DST_RULE_VALUE"));
    }
    public WebElement paymentDetails() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("PAY_DTLS"));
    }
    public WebElement accountID() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("ACCT_ID"));
    }
    public WebElement paymentAmount() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("TENDER_AMT"));
    }
    public WebElement micrID() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("MICR_ID"));
    }
    public WebElement customerID() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("CUST_ID"));
    }
    public WebElement name() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("NAME1"));
    }
    public WebElement paymentSourceID() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("TNDR_CTL_ID"));
    }
    public WebElement checkNumber() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("CHECK_NBR"));
    }
    public WebElement externalReferenceID() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("EXT_REFERENCE_ID"));
    }
    public WebElement bankRoutingNumber() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("ROUTING_NBR"));
    }
    public WebElement externalAccountID() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("EXT_ACCT_ID"));
    }
    public WebElement externalAccountName() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("ENTITY_NAME"));
    }
    public WebElement paymentProcessID() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("PEVT_PROCESS_ID"));
    }
    public WebElement paymentID() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.id("PAY_EVENT_ID"));
    }

}
