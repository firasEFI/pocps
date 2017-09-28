package findus_pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.utils.driver.Engine;
import productabstractions.RoleMap;

public class MockSSOPortalPage extends BasePage {
    public static final String pageTitle = "Mock SSO Portal";

    // Use SeleniumWebDriver for effiency
    // Checkboxes
    final By chk_sagsbehandler_generel = By.id("Sagsbehandler_generel");
    final By chk_sagsbehandler_godkender = By.id("Sagsbehandler_godkender");
    final By chk_betalingssagsbehandler = By.id("Betalingssagsbehandler");
    final By chk_betalingssagsbehandler_godkender = By.id("Betalingssagsbehandler_godkender");
    final By chk_fordringshaversagsbehandler = By.id("Fordringshaversagsbehandler");
    final By chk_se_sagsbehandler_med_noter = By.id("sagsbehandler_med_noter");
    final By chk_funktionsleder = By.id("Funktionsleder");
    final By chk_systemadministrator = By.id("Systemadministrator");
    final By chk_vip_sagsbehandler = By.id("VIP-sagsbehandler");
    final By link_scumlink = By.id("scumlink");

    public MockSSOPortalPage() {
        // When logging out from PSRM, a timeout larger than 0 is needed
        this(new SynchronizeByPageTitle(pageTitle, 10));
    }

    protected MockSSOPortalPage(Synchronizer synchronizer) {
        super(synchronizer);
    }

    public BasePsrmPage login(RoleMap roleMap) {
        fillRoleMap(roleMap);
        return activateLinkToPsrm_roleTest();
    }

    MockSSOPortalPage fillRoleMap(RoleMap roleMap) {
        clickCheckboxes(Engine.getDriver(), roleMap);
        return this;
    }

    BasePsrmPage activateLinkToPsrm_roleTest() {
        Engine.getDriver().findElement(link_scumlink).click();
        return new BasePsrmPage();
    }

    public void clickCheckboxes(WebDriver driver, RoleMap rolemap) {
        boolean[] clickCheckboxes = {
                rolemap.isSagsbehandlerGenerel(),
                rolemap.isSagsbehandlerGodkender(),
                rolemap.isBetalingssagsbehandler(),
                rolemap.isBetalingssagsbehandlerGodkender(),
                rolemap.isFordringshaversagsbehandler(),
                rolemap.isSeSagsbehandlerMedNoter(),
                rolemap.isFunktionsleder(),
                rolemap.isSystemadministrator(),
                rolemap.isVipSagsbehandler() };

        By[] byIds = {
                chk_sagsbehandler_generel,
                chk_sagsbehandler_godkender,
                chk_betalingssagsbehandler,
                chk_betalingssagsbehandler_godkender,
                chk_fordringshaversagsbehandler,
                chk_se_sagsbehandler_med_noter,
                chk_funktionsleder,
                chk_systemadministrator,
                chk_vip_sagsbehandler
        };

        for (int i = 0; i < byIds.length; i++) {
            if (clickCheckboxes[i]) {
                driver.findElement(byIds[i]).click();
            }
        }
    }

}
