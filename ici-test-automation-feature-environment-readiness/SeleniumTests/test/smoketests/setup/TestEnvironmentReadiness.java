package smoketests.setup;

import findus.BaseTest;
import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import icisel.utils.driver.LocalDriverManager;
import pageobjects.smoketests.psrm_navigation.Navi;

/**
 * Disse tests er med til at klarmelde et miljø.
 * Hvis bare én af disse tests fejler, så kan test automatiseringsteamet ikke arbejde.
 */
public class TestEnvironmentReadiness extends BaseTest {

    private BasePsrmPage basePsrmPage;


    /**
     * Vi starter med at logge på og fremsøge en skyldner. Det er absolut basic basic.
     * Derefter skal vi sikre at en masse mere virker.
     */
    @BeforeMethod
    public void initialize()
    {
        basePsrmPage = super.applicationController.startAtLoginNormal()
                .LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler());

        basePsrmPage.iu_213_fremsoegSkyldner.execute(SkyldnerWarehouse.getSkyldner_test83());
    }


    @AfterMethod
    public void stopApplication()
    {
        Engine.stop();
    }

    @Test
    public void test_iu_231_gaaTilOpgaveOverblik()
    {
        basePsrmPage.iu_231_gaaTilOpgaveOverblik.execute();
    }

    @Test
    public void test_iu_235_RegistreringAfGrundlagForSkyldnersBetalingsevne_AarligIndkomst_ikke_destruktiv()
    {
        basePsrmPage
                .partKontekstMenu()
                .tilfoejBetalingsevne()
                .continueWithGrundlagForBetalingsevne()
                .activateAnnuller();
    }

    @Test
    public void test_OpretPaakravSag()
    {
        basePsrmPage
            .kontoKontekstMenu()
            .opretPaakravssag()
            .activateAnnuller();
    }
}

