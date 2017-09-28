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
public class OverviewOfPaymentPage {

    // Public PageElement fields for ICI-Sel
    // "Primaer" area
    public PageElement skyldnerID = new PageElement(Frames.zoneMapFrame_1, By.id("personId"));
    public PageElement cprNummer = new PageElement(Frames.zoneMapFrame_1, By.id("personIdNumber"));
    public PageElement skyldernsNavn = new PageElement(Frames.zoneMapFrame_1, By.id("personName"));
    public PageElement konto = new PageElement(Frames.zoneMapFrame_1, By.id("accountId"));
    public PageElement betalingshaendelsesID = new PageElement(Frames.zoneMapFrame_1, By.id("paymentEventId"));
    public PageElement bogfoeringsdato = new PageElement(Frames.zoneMapFrame_1, By.id("creationDateTime"));
    public PageElement virkningsdato = new PageElement(Frames.zoneMapFrame_1, By.id("effectiveDate"));
    public PageElement daekning = new PageElement(Frames.zoneMapFrame_1, By.id("distributionType"));

    // "Daekningsdetaljer for indbetaling" area.
    // PageElements will have the prefix "d" as in "Daekningsdetaljer" to distinguish them from the remaining
    // PageElements on the page.
    // Many of the fields will have an english name as the name of many of the fields in PSRM is identical, i.e. they
    // can not be distinguished by their PSRM name.
    public PageElement dBeloeb = new PageElement(Frames.zoneMapFrame_1, By.xpath("//td[@class='oraNormal oraDisplayCell oraMoney' and @oratype='money' and @orafield='amount']"));
    public PageElement dDaekningsregel = new PageElement(Frames.zoneMapFrame_1, By.xpath("/html/body/div[2]/table/tbody/tr/td[3]"));
    public PageElement dCharacteristicTypeCode= new PageElement(Frames.zoneMapFrame_1, By.id("characteristicTypeCode"));
    public PageElement dCharacteristicValue = new PageElement(Frames.zoneMapFrame_1, By.id("characteristicValue"));
    public PageElement dAdhocCharacteristicValue = new PageElement(Frames.zoneMapFrame_1, By.id("adhocCharacteristicValue"));
    public PageElement dCharacteristicValueForeignKey1 = new PageElement(Frames.zoneMapFrame_1, By.id("characteristicValueForeignKey1"));
    public PageElement dCharacteristicValueForeignKey2 = new PageElement(Frames.zoneMapFrame_1, By.id("characteristicValueForeignKey2"));
    public PageElement dCharacteristicValueForeignKey3 = new PageElement(Frames.zoneMapFrame_1, By.id("characteristicValueForeignKey3"));
    public PageElement dCharacteristicValueForeignKey4 = new PageElement(Frames.zoneMapFrame_1, By.id("characteristicValueForeignKey4"));
    public PageElement dCharacteristicValueForeignKey5 = new PageElement(Frames.zoneMapFrame_1, By.id("characteristicValueForeignKey5"));
    public PageElement dCharacteristicValueInfo = new PageElement(Frames.zoneMapFrame_1, By.id("characteristicValueInfo"));
    public PageElement dCharacteristicFKInfo = new PageElement(Frames.zoneMapFrame_1, By.id("characteristicFKInfo"));

    // "Indbetalingskilder" area
    // PageElements will have the prefix "ik" as in "IndbetalingsKilder" to distinguish them from the remaining
    // PageElements on the page.
    public PageElement ikIndbetalingsdato = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[2]"));
    public PageElement ikBetalingsdato = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[3]"));
    public PageElement ikIndbetalingsbeloeb = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[4]"));
    public PageElement ikIndbetalingstype = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[5]"));
    public PageElement ikIndbetalingskilde = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[6]"));
    public PageElement ikIndbetalingsstatus= new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[7]"));
    public PageElement ikAnnulleringsaarsag = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[8]"));
    public PageElement ikBetalingsinformation = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[9]"));
    public PageElement ikTransaktionskode = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[10]"));
    public PageElement ikMeddelse = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[11]"));
    public PageElement ikPengeintituttetsReferencenummerBic = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[12]"));
    public PageElement ikRimReference = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[13]"));
    public PageElement ikOcrLinjeNummer = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[14]"));
    public PageElement ikBankRegistreringsnummer = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[15]"));

    // "Indbetalinger" area
    // PageElements will have the prefix "i" as in "Indbetalinger" to distinguish them from the remaining
    // PageElements on the page.
    public PageElement iBetalingsInfo = new PageElement(Frames.tabPage, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[4]"));
    public PageElement iBeloeb = new PageElement(Frames.tabPage, By.xpath("/html/body/div[3]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[5]"));
    public PageElement iFordringsID = new PageElement(Frames.tabPage, By.xpath("/html/body/div[3]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[6]"));
    public PageElement iAnnulleringsaarsag = new PageElement(Frames.tabPage, By.xpath("/html/body/div[3]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[7]"));
    public PageElement iStatus = new PageElement(Frames.tabPage, By.xpath("/html/body/div[3]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[8]"));

    private WebDriver driver;

    public OverviewOfPaymentPage(WebDriver driver) {
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

    /*
     * Primary information
     */
    public WebElement debtorID() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("personId"));
    }
    public WebElement cprNumber() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("personIdNumber"));
    }
    public WebElement debtorName() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("personName"));
    }
    public WebElement account() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("accountId"));
    }
    public WebElement paymentID() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("paymentEventId"));
    }
    public WebElement creationDate() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("creationDateTime"));
    }
    public WebElement effectiveDate() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("effectiveDate"));
    }
    public WebElement distributionType() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("distributionType"));
    }

    /*
     * Details for payment distribution (pd)/html/body/div[2]/table/tbody/tr/td[2]
     */
    public WebElement pdAmount() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.xpath("//td[@class='oraNormal oraDisplayCell oraMoney' and @oratype='money' and @orafield='amount']"));
    }
    public WebElement pdDistributionRule() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.xpath("/html/body/div[2]/table/tbody/tr/td[3]"));
    }
    public WebElement pdCharacteristicTypeCode() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("characteristicTypeCode"));
    }
    public WebElement pdCharacteristicValue() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("characteristicValue"));
    }
    public WebElement pdAdhocCharacteristicValue() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("adhocCharacteristicValue"));
    }
    public WebElement pdCharacteristicValueForeignKey1() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("characteristicValueForeignKey1"));
    }
    public WebElement pdCharacteristicValueForeignKey2() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("characteristicValueForeignKey2"));
    }
    public WebElement pdCharacteristicValueForeignKey3() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("characteristicValueForeignKey3"));
    }
    public WebElement pdCharacteristicValueForeignKey4() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("characteristicValueForeignKey4"));
    }
    public WebElement pdCharacteristicValueForeignKey5() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("characteristicValueForeignKey5"));
    }
    public WebElement pdCharacteristicValueInfo() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("characteristicValueInfo"));
    }
    public WebElement pdCharacteristicFKInfo() {
        return Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.id("characteristicFKInfo"));
    }

    /*
     * Details about source of payment (soc)
     */
    public WebElement socDateOfPayment() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[2]"));
    }
    public WebElement socPayDate() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[3]"));
    }
    public WebElement socPaymentAmount() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[4]"));
    }
    public WebElement socPaymentType() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[5]"));
    }
    public WebElement socPaymentSource() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[6]"));
    }
    public WebElement socPaymentStatus() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[7]"));
    }
    public WebElement socCancelReason() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[8]"));
    }
    public WebElement socPaymentInformation() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[9]"));
    }
    public WebElement socTransactionCode() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[10]"));
    }
    public WebElement socMessage() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[11]"));
    }
    public WebElement socBic() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[12]"));
    }
    public WebElement socRimReference() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[13]"));
    }
    public WebElement socOcrLineNumber() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[14]"));
    }
    public WebElement socBankRegistrationNumber() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[15]"));
    }

    /*
     * Details for payments
     */
    public WebElement paymentInfo() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[3]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[4]"));
    }
    public WebElement paymentAmount() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[3]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[5]"));
    }
    public WebElement taxformID() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[3]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[6]"));
    }
    public WebElement cancelReason() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[3]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[7]"));
    }
    public WebElement status() {
        return Navi.patientlyFindDisplayedElement(FrameType.TAB_PAGE, driver, By.xpath("/html/body/div[3]/div/table/tbody/tr[5]/td/div/table/tbody/tr/td[8]"));
    }

}
