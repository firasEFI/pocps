package smoketests.smoke;

import icisel.utils.driver.RetryAnalyzer;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import icisel.utils.driver.LocalDriverManager;
import pageobjects.smoketests.psrm_navigation.CaseWorkerNavi;
import pageobjects.smoketests.psrm_navigation.Navi;
import pageobjects.smoketests.taxform.DocumentLocatorPage;
import pageobjects.smoketests.taxform.PartPage;
import pageobjects.smoketests.userrolelogin.UserRoleLoginPage;
import utils.FrameType;
import utils.PropertyProviderImpl;

/**
 * Created by asol on 14-07-2017.
 */
public class UserRolesTests {

    /**
     * Step 1: Make sure Sagsbehandler - Generel is picked.
     * Step 2: Pick Sagsbehandler - Godkender aswell.
     * Step 3: Pick Betalingssagsbehandler -Godkender aswell
     * Step 4: Pick Funktionsleder aswell.
     * Step 5: Pick Systemadministrator aswell.
     * Step 6: Pick VIP-Sagsbehandler aswell.
     * Step 7: Click on Link til PSRM (Rolletest).
     * Step 8: Click on Menu.
     * Step 9: Go to Registration.
     * Step 10: Make sure the choices Fordring and Fordringshaverrelation are NOT present.
     * Step 11: Go to Fordringsformular-> Fordring -> Soeg.
     * Step 12: Search for document locator: 002000000004
     * Step 13: Click on Rediger.
     * Step 14: Try to change the Tax Form and save it.
     * Step 15: Make sure the error: bla is present. Final Step: Logout of PSRM.
     * 
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT001_GeneralNotFordring() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        PropertyProviderImpl properties = new PropertyProviderImpl();

        System.out.println(properties.getLoginUrl());

        Navi.invokeBrowser(properties.getLoginUrl(), driver);

        String documentLocator = "SEL_SMOKETEST_15";

        System.out.println("Page title is: " + driver.getTitle());

        UserRoleLoginPage current_UserRoleLoginPage = new UserRoleLoginPage(driver);

        current_UserRoleLoginPage.sagsbehandlerGenerelt();

        current_UserRoleLoginPage.clickSagsbehandlerGodkender();

        current_UserRoleLoginPage.clickBetalingssagsbehandlerGodkender();

        current_UserRoleLoginPage.clickFunktionsleder();

        current_UserRoleLoginPage.clickSystemadministrator();

        current_UserRoleLoginPage.clickVIPSagsbehandler();

        current_UserRoleLoginPage.clickScumlink();

        driver.navigate().to(properties.getLoginUrl());

        CaseWorkerNavi.moveToRegistration(driver);

        CaseWorkerNavi.verifyNoTaxFormEditRights(driver);

        CaseWorkerNavi.openTaxFormSearch(driver);

        PartPage current_PartPage = new PartPage(driver);

        current_PartPage.multiQueryZoneFilters().selectByValue("C1-TXFRMQ7");

        DocumentLocatorPage current_DocumentLocatorPage = new DocumentLocatorPage(driver);

        current_DocumentLocatorPage.filter().sendKeys(documentLocator);

        current_DocumentLocatorPage.clickSearch();

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE,
                By.xpath("/html/body/div[1]/div/table/tbody/tr[8]/td/div/table/tbody/tr/td[4]/a/span"),
                FrameType.ZONE_MAP_FRAME_4, By.id("mainInfoDiv"));

        CaseWorkerNavi.verifyNoEditButton(driver);

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Make sure Sagsbehandler - Generel is picked.\n" +
                "     * Step 2: Pick Sagsbehandler - Godkender aswell.\n" +
                "     * Step 3: Pick Betalingssagsbehandler -Godkender aswell\n" +
                "     * Step 4: Pick Funktionsleder aswell.\n" +
                "     * Step 5: Pick Systemadministrator aswell.\n" +
                "     * Step 6: Pick VIP-Sagsbehandler aswell.\n" +
                "     * Step 7: Click on Link til PSRM (Rolletest).\n" +
                "     * Step 8: Click on Menu.\n" +
                "     * Step 9: Go to Registration.\n" +
                "     * Step 10: Make sure the choices Fordring and Fordringshaverrelation are NOT present.\n" +
                "     * Step 11: Go to Fordringsformular-> Fordring -> Soeg.\n" +
                "     * Step 12: Search for document locator: 002000000004\n" +
                "     * Step 13: Click on Rediger.\n" +
                "     * Step 14: Try to change the Tax Form and save it.\n" +
                "     * Step 15: Make sure the error: bla is present. Final Step: Logout of PSRM.");

    }

    /**
     * Step 1: Make sure Betalingssagsbehandler - Generel is picked.
     * Step 2: Pick Sagsbehandler - Godkender aswell.
     * Step 3: Pick Betalingssagsbehandler - Godkender aswell
     * Step 4: Pick Funktionsleder aswell.
     * Step 5: Pick Systemadministrator aswell.
     * Step 6: Pick VIP-Sagsbehandler aswell.
     * Step 7: Click on Link til PSRM (Rolletest).
     * Step 8: Click on Menu.
     * Step 9: Go to Registration.
     * Step 10: Make sure the choices Fordring and Fordringshaverrelation are NOT present.
     * Step 11: Go to Fordringsformular -> Fordring -> Soeg.
     * Step 12: Search for document locator: 002000000004
     * Step 13: Click on Rediger.
     * Step 14: Try to change the Tax Form and save it.
     * Step 15: Make sure the error: bla is present.
     * Final Step: Logout of PSRM.
     * 
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT002_BetalingsNotFordring() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        PropertyProviderImpl properties = new PropertyProviderImpl();

        Navi.invokeBrowser(properties.getLoginUrl(), driver);

        String documentLocator = "SEL_SMOKETEST_15";

        System.out.println("Page title is: " + driver.getTitle());

        UserRoleLoginPage current_UserRoleLoginPage = new UserRoleLoginPage(driver);

        current_UserRoleLoginPage.clickBetalingssagsbehandler();

        current_UserRoleLoginPage.clickSagsbehandlerGodkender();

        current_UserRoleLoginPage.clickBetalingssagsbehandlerGodkender();

        current_UserRoleLoginPage.clickFunktionsleder();

        current_UserRoleLoginPage.clickSystemadministrator();

        current_UserRoleLoginPage.clickVIPSagsbehandler();

        current_UserRoleLoginPage.clickScumlink();

        CaseWorkerNavi.moveToRegistration(driver);

        CaseWorkerNavi.verifyNoTaxFormEditRights(driver);

        CaseWorkerNavi.openTaxFormSearch(driver);

        PartPage current_PartPage = new PartPage(driver);

        current_PartPage.multiQueryZoneFilters().selectByValue("C1-TXFRMQ7");

        DocumentLocatorPage current_DocumentLocatorPage = new DocumentLocatorPage(driver);

        current_DocumentLocatorPage.filter().sendKeys(documentLocator);

        current_DocumentLocatorPage.clickSearch();

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE,
                By.xpath("/html/body/div[1]/div/table/tbody/tr[8]/td/div/table/tbody/tr/td[4]/a/span"),
                FrameType.ZONE_MAP_FRAME_4, By.id("statusDescription"));

        CaseWorkerNavi.verifyNoEditButton(driver);

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Make sure Betalingssagsbehandler - Generel is picked. \n" +
                "     * Step 2: Pick Sagsbehandler - Godkender aswell. \n" +
                "     * Step 3: Pick Betalingssagsbehandler - Godkender aswell \n" +
                "     * Step 4: Pick Funktionsleder aswell. \n" +
                "     * Step 5: Pick Systemadministrator aswell. \n" +
                "     * Step 6: Pick VIP-Sagsbehandler aswell. \n" +
                "     * Step 7: Click on Link til PSRM (Rolletest).\n" +
                "     * Step 8: Click on Menu. \n" +
                "     * Step 9: Go to Registration. \n" +
                "     * Step 10: Make sure the choices Fordring and Fordringshaverrelation are NOT present. \n" +
                "     * Step 11: Go to Fordringsformular -> Fordring -> Soeg. \n" +
                "     * Step 12: Search for document locator: 002000000004 \n" +
                "     * Step 13: Click on Rediger. \n" +
                "     * Step 14: Try to change the Tax Form and save it. \n" +
                "     * Step 15: Make sure the error: bla is present.\n" +
                "     * Final Step: Logout of PSRM.");

    }

    /**
     * Step 1: Make sure Se-sagsbehandler - Generel is picked.
     * Step 2: Pick Sagsbehandler - Godkender aswell.
     * Step 3: Pick Betalingssagsbehandler - Godkender aswell
     * Step 4: Pick Funktionsleder aswell.
     * Step 5: Pick Systemadministrator aswell.
     * Step 6: Pick VIP-Sagsbehandler aswell.
     * Step 7: Click on Link til PSRM (Rolletest).
     * Step 8: Click on Menu.
     * Step 9: Go to Registration.
     * Step 10: Make sure the choices Fordring and Fordringshaverrelation are NOT present.
     * Step 11: Go to Fordringsformular -> Fordring -> Soeg.
     * Step 12: Search for document locator: 002000000004
     * Step 13: Click on Rediger. Step 14: Try to change the Tax Form and save it.
     * Step 15: Make sure the error: bla is present.
     * Final Step: Logout of PSRM.
     * 
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT003_SeCaseworkerNotFordring() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        PropertyProviderImpl properties = new PropertyProviderImpl();

        Navi.invokeBrowser(properties.getLoginUrl(), driver);

        String documentLocator = "SEL_SMOKETEST_15";

        System.out.println("Page title is: " + driver.getTitle());

        UserRoleLoginPage current_UserRoleLoginPage = new UserRoleLoginPage(driver);

        current_UserRoleLoginPage.clickSeSagsbehandlerMedNoter();

        current_UserRoleLoginPage.clickSagsbehandlerGodkender();

        current_UserRoleLoginPage.clickBetalingssagsbehandlerGodkender();

        current_UserRoleLoginPage.clickFunktionsleder();

        current_UserRoleLoginPage.clickSystemadministrator();

        current_UserRoleLoginPage.clickVIPSagsbehandler();

        current_UserRoleLoginPage.clickScumlink();

        CaseWorkerNavi.moveToRegistration(driver);

        CaseWorkerNavi.verifyNoTaxFormEditRights(driver);

        CaseWorkerNavi.openTaxFormSearch(driver);

        PartPage current_PartPage = new PartPage(driver);

        current_PartPage.multiQueryZoneFilters().selectByValue("C1-TXFRMQ7");

        DocumentLocatorPage current_DocumentLocatorPage = new DocumentLocatorPage(driver);

        current_DocumentLocatorPage.filter().sendKeys(documentLocator);

        current_DocumentLocatorPage.clickSearch();

        Navi.patientlyClickAttempt(driver, FrameType.TAB_PAGE,
                By.xpath("/html/body/div[1]/div/table/tbody/tr[8]/td/div/table/tbody/tr/td[4]/a/span"),
                FrameType.ZONE_MAP_FRAME_4, By.id("statusDescription"));

        CaseWorkerNavi.verifyNoEditButton(driver);

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Make sure Se-sagsbehandler - Generel is picked.\n" +
                "     * Step 2: Pick Sagsbehandler - Godkender aswell.\n" +
                "     * Step 3: Pick Betalingssagsbehandler - Godkender aswell\n" +
                "     * Step 4: Pick Funktionsleder aswell.\n" +
                "     * Step 5: Pick Systemadministrator aswell.\n" +
                "     * Step 6: Pick VIP-Sagsbehandler aswell.\n" +
                "     * Step 7: Click on Link til PSRM (Rolletest).\n" +
                "     * Step 8: Click on Menu.\n" +
                "     * Step 9: Go to Registration.\n" +
                "     * Step 10: Make sure the choices Fordring and Fordringshaverrelation are NOT present.\n" +
                "     * Step 11: Go to Fordringsformular -> Fordring -> Soeg.\n" +
                "     * Step 12: Search for document locator: 002000000004\n" +
                "     * Step 13: Click on Rediger. Step 14: Try to change the Tax Form and save it.\n" +
                "     * Step 15: Make sure the error: bla is present.\n" +
                "     * Final Step: Logout of PSRM.");

    }

    /**
     * Step 1: Make sure Sagsbehandler - Generel is picked.
     * Step 2: Pick Sagsbehandler - Godkender aswell.
     * Step 3: Pick Fordringshaversagsbehandler aswell
     * Step 4: Pick Funktionsleder aswell.
     * Step 5: Pick Systemadministrator aswell.
     * Step 6: Pick VIP-Sagsbehandler aswell.
     * Step 7: Click on Link til PSRM (Rolletest).
     * Step 8: Click on Menu.
     * Step 9: Go to Regnskab.
     * Step 10: Make sure the choices Masse Godkendelse and Individuel Udbetaling are NOT present.
     * Final Step: Logout of PSRM.
     * 
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT004_GeneralNotBetalingGodkender() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        PropertyProviderImpl properties = new PropertyProviderImpl();

        Navi.invokeBrowser(properties.getLoginUrl(), driver);

        System.out.println("Page title is: " + driver.getTitle());

        UserRoleLoginPage current_UserRoleLoginPage = new UserRoleLoginPage(driver);

        current_UserRoleLoginPage.sagsbehandlerGenerelt();

        current_UserRoleLoginPage.clickSagsbehandlerGodkender();

        current_UserRoleLoginPage.clickFordringshaversagsbehandler();

        current_UserRoleLoginPage.clickFunktionsleder();

        current_UserRoleLoginPage.clickSystemadministrator();

        current_UserRoleLoginPage.clickVIPSagsbehandler();

        current_UserRoleLoginPage.clickScumlink();

        CaseWorkerNavi.verifyNoMassPaymentRights(driver);

        CaseWorkerNavi.verifyNoIndividualPaymentRights(driver);

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Make sure Sagsbehandler - Generel is picked. \n" +
                "     * Step 2: Pick Sagsbehandler - Godkender aswell. \n" +
                "     * Step 3: Pick Fordringshaversagsbehandler aswell \n" +
                "     * Step 4: Pick Funktionsleder aswell.\n" +
                "     * Step 5: Pick Systemadministrator aswell. \n" +
                "     * Step 6: Pick VIP-Sagsbehandler aswell. \n" +
                "     * Step 7: Click on Link til PSRM (Rolletest). \n" +
                "     * Step 8: Click on Menu. \n" +
                "     * Step 9: Go to Regnskab. \n" +
                "     * Step 10: Make sure the choices Masse Godkendelse and Individuel Udbetaling are NOT present. \n" +
                "     * Final Step: Logout of PSRM.");

    }

    /**
     * Step 1: Make sure Betalingssagsbehandler - Generel is picked.
     * Step 2: Pick Sagsbehandler - Godkender aswell.
     * Step 3: Pick Fordringshaversagsbehandler aswell
     * Step 4: Pick Funktionsleder aswell.
     * Step 5: Pick Systemadministrator aswell.
     * Step 6: Pick VIP-Sagsbehandler aswell.
     * Step 7: Click on Link til PSRM (Rolletest).
     * Step 8: Click on Menu.
     * Step 9: Go to Regnskab.
     * Step 10: Make sure the choices Masse Godkendelse and Individuel Udbetaling are NOT present.
     * Final Step: Logout of PSRM.
     * 
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT005_BetalingNotBetalingGodkender() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        PropertyProviderImpl properties = new PropertyProviderImpl();

        Navi.invokeBrowser(properties.getLoginUrl(), driver);

        driver.navigate().refresh();

        System.out.println("Page title is: " + driver.getTitle());

        UserRoleLoginPage current_UserRoleLoginPage = new UserRoleLoginPage(driver);

        current_UserRoleLoginPage.clickBetalingssagsbehandler();

        current_UserRoleLoginPage.clickSagsbehandlerGodkender();

        current_UserRoleLoginPage.clickFordringshaversagsbehandler();

        current_UserRoleLoginPage.clickFunktionsleder();

        current_UserRoleLoginPage.clickSystemadministrator();

        current_UserRoleLoginPage.clickVIPSagsbehandler();

        current_UserRoleLoginPage.clickScumlink();

        CaseWorkerNavi.verifyNoMassPaymentRights(driver);

        CaseWorkerNavi.verifyNoIndividualPaymentRights(driver);

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Make sure Betalingssagsbehandler - Generel is picked.\n" +
                "     * Step 2: Pick Sagsbehandler - Godkender aswell.\n" +
                "     * Step 3: Pick Fordringshaversagsbehandler aswell\n" +
                "     * Step 4: Pick Funktionsleder aswell.\n" +
                "     * Step 5: Pick Systemadministrator aswell.\n" +
                "     * Step 6: Pick VIP-Sagsbehandler aswell.\n" +
                "     * Step 7: Click on Link til PSRM (Rolletest).\n" +
                "     * Step 8: Click on Menu.\n" +
                "     * Step 9: Go to Regnskab.\n" +
                "     * Step 10: Make sure the choices Masse Godkendelse and Individuel Udbetaling are NOT present.\n" +
                "     * Final Step: Logout of PSRM.");

    }

    /**
     * Step 1: Make sure Se-sagsbehandler - Generel is picked.
     * Step 2: Pick Sagsbehandler - Godkender aswell.
     * Step 3: Pick Fordringshaversagsbehandler aswell
     * Step 4: Pick Funktionsleder aswell.
     * Step 5: Pick Systemadministrator aswell.
     * Step 6: Pick VIP-Sagsbehandler aswell.
     * Step 7: Click on Link til PSRM (Rolletest).
     * Step 8: Click on Menu.
     * Step 9: Go to Regnskab.
     * Step 10: Make sure the choices Masse Godkendelse and Individuel Udbetaling are NOT present.
     * Final Step: Logout of PSRM.
     * 
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT006_SeCaseworkerNotBetalingGodkender() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        PropertyProviderImpl properties = new PropertyProviderImpl();

        Navi.invokeBrowser(properties.getLoginUrl(), driver);

        System.out.println("Page title is: " + driver.getTitle());

        UserRoleLoginPage current_UserRoleLoginPage = new UserRoleLoginPage(driver);

        current_UserRoleLoginPage.clickSeSagsbehandlerMedNoter();

        current_UserRoleLoginPage.clickSagsbehandlerGodkender();

        current_UserRoleLoginPage.clickFordringshaversagsbehandler();

        current_UserRoleLoginPage.clickFunktionsleder();

        current_UserRoleLoginPage.clickSystemadministrator();

        current_UserRoleLoginPage.clickVIPSagsbehandler();

        current_UserRoleLoginPage.clickScumlink();

        CaseWorkerNavi.verifyNoMassPaymentRights(driver);

        CaseWorkerNavi.verifyNoIndividualPaymentRights(driver);

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Make sure Se-sagsbehandler - Generel is picked. \n" +
                "     * Step 2: Pick Sagsbehandler - Godkender aswell. \n" +
                "     * Step 3: Pick Fordringshaversagsbehandler aswell \n" +
                "     * Step 4: Pick Funktionsleder aswell.\n" +
                "     * Step 5: Pick Systemadministrator aswell. \n" +
                "     * Step 6: Pick VIP-Sagsbehandler aswell. \n" +
                "     * Step 7: Click on Link til PSRM (Rolletest). \n" +
                "     * Step 8: Click on Menu. \n" +
                "     * Step 9: Go to Regnskab. \n" +
                "     * Step 10: Make sure the choices Masse Godkendelse and Individuel Udbetaling are NOT present. \n" +
                "     * Final Step: Logout of PSRM.");

    }

    /**
     * Step 1: Make sure Sagsbehandler - Generel is picked.
     * Step 2: Pick Betalingssagsbehandler - Godkender aswell.
     * Step 3: Pick Funktionsleder aswell.
     * Step 4: Pick Systemadministrator aswell.
     * Step 5: Pick VIP-Sagsbehandler aswell.
     * Step 6: Click on Link til PSRM (Rolletest).
     * Step 7: Click on Menu.
     * Step 8: Go to Registrering->Part.
     * Step 9: Make sure the choice Tilfoej is NOT present.
     * Final Step: Logout of PSRM.
     * 
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT007_GeneralNotFordringAndGodkender() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        PropertyProviderImpl properties = new PropertyProviderImpl();

        Navi.invokeBrowser(properties.getLoginUrl(), driver);

        System.out.println("Page title is: " + driver.getTitle());

        UserRoleLoginPage current_UserRoleLoginPage = new UserRoleLoginPage(driver);

        current_UserRoleLoginPage.sagsbehandlerGenerelt();

        current_UserRoleLoginPage.clickBetalingssagsbehandlerGodkender();

        current_UserRoleLoginPage.clickFunktionsleder();

        current_UserRoleLoginPage.clickSystemadministrator();

        current_UserRoleLoginPage.clickVIPSagsbehandler();

        current_UserRoleLoginPage.clickScumlink();

        CaseWorkerNavi.verifyNoAddPersonRights(driver);

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Make sure Sagsbehandler - Generel is picked.\n" +
                "     * Step 2: Pick Betalingssagsbehandler - Godkender aswell. \n" +
                "     * Step 3: Pick Funktionsleder aswell. \n" +
                "     * Step 4: Pick Systemadministrator aswell. \n" +
                "     * Step 5: Pick VIP-Sagsbehandler aswell. \n" +
                "     * Step 6: Click on Link til PSRM (Rolletest).\n" +
                "     * Step 7: Click on Menu. \n" +
                "     * Step 8: Go to Registrering->Part. \n" +
                "     * Step 9: Make sure the choice Tilfoej is NOT present. \n" +
                "     * Final Step: Logout of PSRM.");

    }

    /**
     * Step 1: Make sure Betalingssagsbehandler - Generel is picked.
     * Step 2: Pick Betalingssagsbehandler - Godkender aswell.
     * Step 3: Pick Funktionsleder aswell.
     * Step 4: Pick Systemadministrator aswell.
     * Step 5: Pick VIP-Sagsbehandler aswell.
     * Step 6: Click on Link til PSRM (Rolletest).
     * Step 7: Click on Menu.
     * Step 8: Go to Registrering->Part.
     * Step 9: Make sure the choice Tilfoej is NOT present.
     * Final Step: Logout of PSRM.
     * 
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT008_BetalingNotFordringAndGodkender() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        PropertyProviderImpl properties = new PropertyProviderImpl();

        Navi.invokeBrowser(properties.getLoginUrl(), driver);

        System.out.println("Page title is: " + driver.getTitle());

        UserRoleLoginPage current_UserRoleLoginPage = new UserRoleLoginPage(driver);

        current_UserRoleLoginPage.clickBetalingssagsbehandler();

        current_UserRoleLoginPage.clickBetalingssagsbehandlerGodkender();

        current_UserRoleLoginPage.clickFunktionsleder();

        current_UserRoleLoginPage.clickSystemadministrator();

        current_UserRoleLoginPage.clickVIPSagsbehandler();

        current_UserRoleLoginPage.clickScumlink();

        CaseWorkerNavi.verifyNoAddPersonRights(driver);

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Make sure Betalingssagsbehandler - Generel is picked.\n" +
                "     * Step 2: Pick Betalingssagsbehandler - Godkender aswell.\n" +
                "     * Step 3: Pick Funktionsleder aswell.\n" +
                "     * Step 4: Pick Systemadministrator aswell.\n" +
                "     * Step 5: Pick VIP-Sagsbehandler aswell.\n" +
                "     * Step 6: Click on Link til PSRM (Rolletest).\n" +
                "     * Step 7: Click on Menu.\n" +
                "     * Step 8: Go to Registrering->Part.\n" +
                "     * Step 9: Make sure the choice Tilfoej is NOT present.\n" +
                "     * Final Step: Logout of PSRM.");

    }

    /**
     * Step 1: Make sure Se-sagsbehandler - Generel is picked.
     * Step 2: Pick Betalingssagsbehandler - Godkender aswell.
     * Step 3: Pick Funktionsleder aswell.
     * Step 4: Pick Systemadministrator aswell.
     * Step 5: Pick VIP-Sagsbehandler aswell.
     * Step 6: Click on Link til PSRM (Rolletest).
     * Step 7: Click on Menu.
     * Step 8: Go to Registrering->Part.
     * Step 9: Make sure the choice Tilfoej is NOT present.
     * Final Step: Logout of PSRM.
     * 
     */
    @Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT009_SeCaseworkerNotFordringAndGodkender() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        PropertyProviderImpl properties = new PropertyProviderImpl();

        Navi.invokeBrowser(properties.getLoginUrl(), driver);

        System.out.println("Page title is: " + driver.getTitle());

        UserRoleLoginPage current_UserRoleLoginPage = new UserRoleLoginPage(driver);

        current_UserRoleLoginPage.clickSeSagsbehandlerMedNoter();

        current_UserRoleLoginPage.clickBetalingssagsbehandlerGodkender();

        current_UserRoleLoginPage.clickFunktionsleder();

        current_UserRoleLoginPage.clickSystemadministrator();

        current_UserRoleLoginPage.clickVIPSagsbehandler();

        current_UserRoleLoginPage.clickScumlink();

        CaseWorkerNavi.verifyNoAddPersonRights(driver);

        Navi.logoutPSRM(driver);

        System.out.println("     * Step 1: Make sure Se-sagsbehandler - Generel is picked.\n" +
                "     * Step 2: Pick Betalingssagsbehandler - Godkender aswell.\n" +
                "     * Step 3: Pick Funktionsleder aswell.\n" +
                "     * Step 4: Pick Systemadministrator aswell.\n" +
                "     * Step 5: Pick VIP-Sagsbehandler aswell.\n" +
                "     * Step 6: Click on Link til PSRM (Rolletest).\n" +
                "     * Step 7: Click on Menu.\n" +
                "     * Step 8: Go to Registrering->Part.\n" +
                "     * Step 9: Make sure the choice Tilfoej is NOT present.\n" +
                "     * Final Step: Logout of PSRM.");

    }

}
