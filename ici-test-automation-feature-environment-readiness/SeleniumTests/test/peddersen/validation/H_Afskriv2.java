package peddersen.validation;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import findus_controllers.ApplicationController;
import findus_datamodelWarehouses.RoleMapWarehouse;
import findus_pageobjects.MockSSOPortalPage;
import icisel.taxobjects.Fordring;
import icisel.taxobjects.Medielicens;
import icisel.testng.PropertyProvider;
import icisel.testng.TestContext;
import icisel.utils.driver.Engine;
import modules.MO_Afskriv;
import modules.MO_Afskriv.Afskrivningsaarsag;
import modules.MO_Afskriv.Afskrivningsmulighed;
import utils.PropertyProviderImpl;

@Test(groups = { "excluded" }, invocationCount = 1)
public class H_Afskriv2 extends TestContext {
    private String cpr = "0505780140";
    private String sBeloeb = "1000,00";

    // fordring har tilfaeldigt FordringsID
    private Fordring fordring = new Medielicens(cpr, sBeloeb);

    private MockSSOPortalPage loginPage;

    @BeforeMethod(alwaysRun = true)
    private void testData() {
        PropertyProvider pp = new PropertyProviderImpl(this);
        setPropertyProvider(pp);
        loginPage = new ApplicationController(pp).startAtLoginSSO(true);
    }

    public void main() throws Exception {

        loginPage.login(RoleMapWarehouse.SAGSBEHANDLER_GENEREL)
                .menu().a360GradersSoegning();
        // .fremsoegSkyldnerVedCpr(cpr);

        // Afskriv fordring
        String sagsId = MO_Afskriv.opretAfskrivningOgSendTilGodkendelse(this, Afskrivningsmulighed.PROCENT, 99,
                Afskrivningsaarsag.EFTERGIVELSE, fordring);

        doLogout();
        //
        // this.setActiveUser(new User("GEB", "netcompany-123",
        // UserRole.DEFAULT));
        // doLogin();
        // //
        // // setSsoEnabled(true);
        // //
        // // doLogin("SAG1");
        //
        // MO_Afskriv.godkendAfskrivning(this, sagsId);
        //
        // doLogout();

    }

    @AfterMethod
    public void failureHandler(ITestResult result) {
        doScreenshotIfIsFailure(result);
    }

    @AfterClass
    public void cleanup() {
        try {
            Engine.stop();
        } catch (Exception e) {
            // do nothing
        }
    }

}
