package findus_pageobjects;

import java.util.concurrent.TimeUnit;

import findus_testobjects.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Predicate;

import findus_core.CorePsrmConfiguration;
import findus_pageobjects.synchronization.SynchronizeByElementPresent;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;
import navigation.menu.fluent.KontoKontekstMenu;
import navigation.menu.fluent.MenuNavigator;
import navigation.menu.fluent.PartKontekstMenu;
import navigation.menu.fluent.TopAdmin;
import navigation.menu.fluent.TopMenu;

public class BasePsrmPage extends BasePage {

    private static Synchronizer syncronizer = new SynchronizeByElementPresent(new PageElement(Frames.dashboard, By.xpath(".//button[@onclick='refreshToDoUserSummary()']")), 15);

    public PageElement pageTitle = new PageElement(Frames.main, By.id("ptitle"));
    public PageElement forsidePageElementMenuPunkt = new PageElement(Frames.main, By.id("IM_USER_HOME"));

    public boolean isLanguageDanish;

    public TopMenu menu() {
        return MenuNavigator.menu();
    }

    public TopAdmin admin() {
        return MenuNavigator.admin();
    }

    public PartKontekstMenu partKontekstMenu() {
        return MenuNavigator.partKontekst();
    }

    public KontoKontekstMenu kontoKontekstMenu() {
        return MenuNavigator.kontoKontekst();
    }

    public BasePsrmPage() {
        this(BasePsrmPage.syncronizer);
    }

    protected BasePsrmPage(Synchronizer syncronizer) {
        super(syncronizer);

        FluentWait<PatientWebDriver> waitForWizardClose = new FluentWait<PatientWebDriver>(Engine.getDriver())
                .pollingEvery(CorePsrmConfiguration.getInstance().getPollingInterval().toMillis(), TimeUnit.MILLISECONDS)
                .withTimeout(CorePsrmConfiguration.getInstance().getBusyTimeout().toMillis(), TimeUnit.MILLISECONDS)
                .withMessage("Wizard not closed");

        waitForWizardClose.until(new Predicate<PatientWebDriver>() {
            @Override
            public boolean apply(PatientWebDriver input) {
                Engine.getDriver().goTo(Frames.uiMap);

                return input.findElement(By.tagName("body")).getAttribute("data-ispopup") == null;
            }
        });
    }

    public String createTabLocator(String title)
    {
        return String.format("//tr[@id = 'tabRow']/td/table/tbody/tr/td[@role= 'tab' and text() = '%s']", title);
    }

    /**
     * FIXME: This method should be implemented as a synchronization class instead and be used, when returning a pageobject which represents a specific tab to be active.
     */
    protected void ensureSelectedTab(final WebElement tabElement) {
        String tabElementClass = tabElement.getAttribute("class");

        if(tabElementClass.equals("inactiveTab")) {
            FluentWait<WebDriver> waitForActiveTab = new FluentWait<WebDriver>(Engine.getDriver())
                    .pollingEvery(CorePsrmConfiguration.getInstance().getPollingInterval().toMillis(), TimeUnit.MILLISECONDS)
                    .withTimeout(CorePsrmConfiguration.getInstance().getBusyTimeout().toMillis(), TimeUnit.MILLISECONDS);
                waitForActiveTab
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(WebDriver input) {
                        tabElement.click();
                        return tabElement.getAttribute("class").equals("activeTab");
                    }
                });  
        }
        else if(!tabElementClass.equals("activeTab"))
            throw new IllegalArgumentException("tabElement is not a tab");
    }
    
    public IU_213_FremsoegSkyldnerFra360gradersSoegning iu_213_fremsoegSkyldner = new IU_213_FremsoegSkyldnerFra360gradersSoegning(this);

    public IU_227_DanVelkomstbrevForKundeMedPartshoering iu_227_danVelkomstbrevForKundeMedPartshoering = new IU_227_DanVelkomstbrevForKundeMedPartshoering(this);

    public IU_231_GaaTilOpgaveOverblik iu_231_gaaTilOpgaveOverblik = new IU_231_GaaTilOpgaveOverblik(this);

    public IU_235_RegistreringAfGrundlagForSkyldnersBetalingsevne_AarligIndkomst iu_235_registreringAfGrundlagForSkyldnersBetalingsevne_AarligIndkomst = new IU_235_RegistreringAfGrundlagForSkyldnersBetalingsevne_AarligIndkomst(this);

    public IU_236_RegistreringAfBeregnetBetalingsevne_Tabeltraek iu_236_registreringAfBeregnetBetalingsevne_Tabeltraek = new IU_236_RegistreringAfBeregnetBetalingsevne_Tabeltraek(this);

    public IU_241_GaaTilSkyldneroverblikket iu_241_gaaTilSkyldneroverblikket = new IU_241_GaaTilSkyldneroverblikket(this);

    public IU_246_AabnSagsoverblikketForSkylder iu_246_aabnSagsoverblikketForSkylder = new IU_246_AabnSagsoverblikketForSkylder(this);

    public IU_527_OpretAfdragsordning iu_527_opretAfdragsordning = new IU_527_OpretAfdragsordning(this);

    public IU_573 iu_573_fremsoegSkyldnerVisKontoOverblik = new IU_573(this);

    public IU_591 iu_591 = new IU_591(this);

    public IU_593 iu_593_opretAfdragsOrdning = new IU_593(this);

    public IU_605 iu_605_kontrollerAtOpgaveVedroerendeGodkendelseAfUdbetalingErOprettet = new IU_605(this);

    public IU_607 iu_607_traekRapportForKontospecifikationer = new IU_607(this);

    public IU_641 iu_641 = new IU_641(this);

    public IU_645 iu_645 = new IU_645(this);

    public IU_646 iu_646_vendTilbageTil360GraderPaaKundeViaDashboardFindKontoUdtog = new IU_646(this);

    public IU_648 iu_648 = new IU_648(this);

    public IU_653_FremsoegSystemdatoenIPsrm iu_653_fremsoegSystemdatoenIPsrm = new IU_653_FremsoegSystemdatoenIPsrm(this);

    public IU_697_OpretInaktivAfdragsordningUdFraTabeltraek_AlleFordringerVaelges iu_697_OpretInaktivAfdragsordningUdFraTabeltræk_AlleFordringerVaelges = new IU_697_OpretInaktivAfdragsordningUdFraTabeltraek_AlleFordringerVaelges(
            this);

    public IU_720 iu_720 = new IU_720(this);

    public IU_999_LogUd iu_999_logUd = new IU_999_LogUd(this);

    public MockSSOPortalPage logout() {
        PatientWebDriver driver = Engine.getDriver();
        driver.manage().deleteAllCookies();
        try {
            driver.get(driver.getCurrentUrl());
        } catch (UnhandledAlertException e) {
            driver.switchTo().alert().accept();
            driver.get(driver.getCurrentUrl());
        }
        return new MockSSOPortalPage();
    }

    /**
     * FIXME: The fixed wait must be replaced by a syncronization instead.
     */
    public void ensureLanguageIsDanish() {
        isLanguageDanish = forsidePageElementMenuPunkt.getText().trim().equals("Forside");
        if(!isLanguageDanish) {
            new Button(Frames.dashboard, By.xpath("//*[@id=\"data_203\"]/div/div/button")).click();
            isLanguageDanish = true;
            waitSeconds(2);
            long start_time = System.currentTimeMillis();
            long wait_time = 5000;
            long end_time = start_time + wait_time;
            while (System.currentTimeMillis() < end_time) {
                try {
                    Alert alert = ExpectedConditions.alertIsPresent().apply(Engine.getDriver());
                    if(alert != null && alert.getText().startsWith("You have unsaved changes")) {
                        alert.accept();
                    }
                } catch (NoAlertPresentException ex) {
                }
            }
        }
    }

    /**
     * FIXME: This method must be removed
     * @param secs
     */
    private void waitSeconds(long secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
