package pageobjects.smoketests.psrm_navigation;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.smoketests._360degreeview._360PersonPage;
import utils.FrameType;
import utils.PropertyProviderImpl;

import java.util.List;
import java.util.Set;

/**
 * Created by asol on 24-05-2017.
 */
public class Navi {

    public Navi(WebDriver driver) {

    }

    public final static int SHORTWAITTIME = 5;
    public final static int MEDIUMWAITTIME = 10;
    public final static int LONGWAITTIME = 20;
    public final static int VERYLONGWAITTIME = 40;

    public static void defaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }
    public static void dataFrame(WebDriver driver) {
        defaultContent(driver);
        driver.switchTo().frame("dataFrame");
    }
    public static void mainFrame(WebDriver driver) {
        defaultContent(driver);
        driver.switchTo().frame("main");
    }
    public static void dashboard(WebDriver driver) {
        mainFrame(driver);
        new WebDriverWait(driver, LONGWAITTIME).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("dashboard")));
    }
    public static void uiMap(WebDriver driver) {
        mainFrame(driver);
        new WebDriverWait(driver, LONGWAITTIME).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("uiMap")));
    }
    public static void tabMenu(WebDriver driver) {
        mainFrame(driver);
        new WebDriverWait(driver, LONGWAITTIME).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("tabMenu")));
    }
    public static void tabPage(WebDriver driver) {
        try {
            mainFrame(driver);

            new WebDriverWait(driver, LONGWAITTIME).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("tabPage")));
        }catch(TimeoutException e) {
            (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("tabPage")).isDisplayed();
                }
            });
            driver.switchTo().frame("tabPage");
        }catch(StaleElementReferenceException e) {
            (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("tabPage")).isDisplayed();
                }
            });
            driver.switchTo().frame("tabPage");
        }catch(NoSuchFrameException e){
            driver.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage");
        }
    }
    public static void zoneMapFrame_1(WebDriver driver) {
        tabPage(driver);
        new WebDriverWait(driver, LONGWAITTIME).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("zoneMapFrame_1")));
    }
    public static void zoneMapFrame_2(WebDriver driver) {
        tabPage(driver);
        new WebDriverWait(driver, LONGWAITTIME).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("zoneMapFrame_2")));
    }
    public static void zoneMapFrame_4(WebDriver driver) {
        tabPage(driver);
        new WebDriverWait(driver, LONGWAITTIME).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("zoneMapFrame_4")));
    }
    public static void zoneMapFrame_106(WebDriver driver) {
        dashboard(driver);
        new WebDriverWait(driver, LONGWAITTIME).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("zoneMapFrame_106")));
    }
    public static void quickAddTndr(WebDriver driver) {
        tabPage(driver);
        new WebDriverWait(driver, LONGWAITTIME).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("QUICK_ADD_TNDR")));
    }
    public static void userCharGrid(WebDriver driver) {
        tabPage(driver);
        new WebDriverWait(driver, LONGWAITTIME).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("USER_CHAR_GRID")));
    }

    /**
     * Wait for methods.
     */
    public static void waitForReadyStateComplete(final WebDriver driver) {
        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return ((JavascriptExecutor)d).executeScript("return document.readyState").equals("complete");
            }
        });
    }

    public static void waitForDisplayedElement(final FrameType frameType, final WebDriver driver, final By by) {
        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                switch (frameType) {
                    case DEFAULT_CONTENT:
                        return findElementAtDefaultContent(d, by).isDisplayed();
                    case MAIN:
                        return findElementAtMain(d, by).isDisplayed();
                    case DASHBOARD:
                        return findElementAtDashboard(d, by).isDisplayed();
                    case UI_MAP:
                        return findElementAtUiMap(d, by).isDisplayed();
                    case TAB_MENU:
                        return findElementAtTabMenu(d, by).isDisplayed();
                    case TAB_PAGE:
                        return findElementAtTabPage(d, by).isDisplayed();
                    case ZONE_MAP_FRAME_1:
                        return findElementAtZoneMapFrame_1(d, by).isDisplayed();
                    case ZONE_MAP_FRAME_2:
                        return findElementAtZoneMapFrame_2(d, by).isDisplayed();
                    case ZONE_MAP_FRAME_4:
                        return findElementAtZoneMapFrame_4(d, by).isDisplayed();
                    default:
                        throw new IllegalArgumentException("Invalid frame: " + frameType);
                }
            }
        });
    }

    public static void waitForDisplayedElement(final WebDriver driver, final By by) {
        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(by).isDisplayed();
            }
        });

    }

    public static void waitForEnabledElement(final FrameType frameType, final WebDriver driver, final By by) {
        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                switch (frameType) {
                    case DASHBOARD:
                        return findElementAtDashboard(d, by).isEnabled();
                    case UI_MAP:
                        return findElementAtUiMap(d, by).isEnabled();
                    case TAB_MENU:
                        return findElementAtTabMenu(d, by).isEnabled();
                    case TAB_PAGE:
                        return findElementAtTabPage(d, by).isEnabled();
                    case ZONE_MAP_FRAME_1:
                        return findElementAtZoneMapFrame_1(d, by).isEnabled();
                    case ZONE_MAP_FRAME_2:
                        return findElementAtZoneMapFrame_2(d, by).isEnabled();
                    case ZONE_MAP_FRAME_4:
                        return findElementAtZoneMapFrame_4(d, by).isEnabled();
                    case ZONE_MAP_FRAME_106:
                        return findElementAtZoneMapFrame_106(d, by).isEnabled();
                    case QUICK_ADD_TNDR:
                        return findElementAtQuickAddTndr(d, by).isEnabled();
                    case USER_CHAR_GRID:
                        return findElementAtUserCharGrid(d, by).isEnabled();
                    default:
                        throw new IllegalArgumentException("Invalid frame: " + frameType);
                }
            }
        });
    }



    /**
     * Methods to find a WebElement in frames.
     */
    public static WebElement patientlyFindDisplayedElement(final FrameType frameType, final WebDriver driver, final By by){

        waitForDisplayedElement(frameType, driver, by);

        return findElementAt(frameType, driver, by);

    }

    public static WebElement patientlyFindDisplayedElement(final WebDriver driver, final By by){

        waitForDisplayedElement(driver, by);

        return driver.findElement(by);

    }

    public static WebElement patientlyFindEnabledElement(final FrameType frameType, final WebDriver driver, final By by){

        waitForEnabledElement(frameType, driver, by);

        return findElementAt(frameType, driver, by);

    }

    public static Select patientlySelectDisplayedElement(final FrameType frameType, final WebDriver driver, final By by){

        waitForDisplayedElement(frameType, driver, by);

        return selectElementAt(frameType, driver, by);

    }

    private static WebElement findElementAt(FrameType frameType, WebDriver driver, By by) {
        switch (frameType) {
            case MAIN:
                return findElementAtMain(driver, by);
            case UI_MAP:
                return findElementAtUiMap(driver, by);
            case TAB_MENU:
                return findElementAtTabMenu(driver, by);
            case TAB_PAGE:
                return findElementAtTabPage(driver, by);
            case ZONE_MAP_FRAME_1:
                return findElementAtZoneMapFrame_1(driver, by);
            case ZONE_MAP_FRAME_2:
                return findElementAtZoneMapFrame_2(driver, by);
            case ZONE_MAP_FRAME_4:
                return findElementAtZoneMapFrame_4(driver, by);
            default:
                throw new IllegalArgumentException("Invalid frame: " + frameType);
        }
    }

    private static Select selectElementAt(FrameType frameType, WebDriver driver, By by) {
        switch (frameType) {
            case UI_MAP:
                return selectElementAtUiMap(driver, by);
            case TAB_MENU:
                return selectElementAtTabMenu(driver, by);
            case TAB_PAGE:
                return selectElementAtTabPage(driver, by);
            case ZONE_MAP_FRAME_1:
                return selectElementAtZoneMapFrame_1(driver, by);
            default:
                throw new IllegalArgumentException("Invalid frame: " + frameType);
        }
    }

    public static void waitForSelectedElement(WebDriver driver, final WebElement element){

        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return element.isEnabled();
            }
        });

    }

    /**
     * Methods to perform clicks
     */
    public static void patientlyClick(WebDriver driver, final By by){

        WebDriverWait wait = new WebDriverWait(driver, MEDIUMWAITTIME);

        WebElement click = wait.until(ExpectedConditions.elementToBeClickable(by));

        click.click();

    }

    public static void patientlyClickAttempt(final WebDriver driver, final FrameType currentFrame, final By by,
                                             final FrameType nextFrame, final By byNextPageElement){

        boolean elementCaught = false;

        int j = 0;

        while(!elementCaught && j<5) {

            try {

                patientlyFindDisplayedElement(currentFrame, driver, by);

                patientlyClick(driver, by);

                patientlyFindDisplayedElement(nextFrame, driver, byNextPageElement);

                elementCaught = true;

            } catch (TimeoutException e) {
                j++;
            }
        }

        if(!elementCaught) {
            org.testng.Assert.fail("The element could not be clicked on.");
        }
    }

    public static void patientlyClickAttempt(final WebDriver driver, final By by,
                                             final FrameType nextFrame, final By byNextPageElement){

        boolean elementCaught = false;

        int j = 0;

        while(!elementCaught && j<5) {

            try {

                patientlyClick(driver, by);

                patientlyFindDisplayedElement(nextFrame, driver, byNextPageElement);

                elementCaught = true;

            } catch (TimeoutException e) {
                j++;
            }
        }
    }

    public static void loginPSRM(final WebDriver driver) {
        PropertyProviderImpl properties = new PropertyProviderImpl();

        invokeBrowser(properties.getUrl(), driver);

        System.out.println("Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 40)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("login");
            }
        });

        patientlyFindDisplayedElement(driver, By.id("userId")).sendKeys(properties.getUsername());

        patientlyFindDisplayedElement(driver, By.id("password")).sendKeys(properties.getPassword());

        // Now submit the form. WebDriver will find the form for us from the element
        patientlyFindDisplayedElement(driver, By.id("loginButton")).click();

        try {

            (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.switchTo().defaultContent().switchTo().frame("main")
                            .findElement(By.id("dashboardArea")).isDisplayed();
                }
            });

            System.out.println("Successfully logged in");

        }catch (TimeoutException e){


            (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.switchTo().defaultContent().switchTo().frame("main")
                            .findElement(By.id("dashboardArea")).isDisplayed();
                }
            });

            System.out.println("Successfully logged in");

        }

    }

    public static void logoutPSRM(WebDriver driver) {

        driver.switchTo().defaultContent().switchTo().frame("main");

        WebElement gebAutomaticTester = driver.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("youAreLoggedInAsSpan"));

        boolean elementCaught = false;

        int j = 0;

        while(!elementCaught && j<10) {
            try {

                gebAutomaticTester.click();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("Log ud")).isEnabled();
                    }
                });

                WebElement logout = driver.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("Log ud")); //Logout for english Log ud for danish

                Actions builder = new Actions(driver);

                builder.moveToElement(logout).perform();

                logout.click();

                System.out.println("Successfully logged out test with hashcode " + driver.hashCode());

                elementCaught = true;

            } catch (TimeoutException e) {

                j++;

            } catch (NoSuchElementException e){

                j++;

            } catch (StaleElementReferenceException e) {

                j++;

            }

        }

    }

    public static void open360DegreeSearch(WebDriver driver) {

        int i = 0;

        boolean elementfound = false;

        while(!elementfound && i<10)

        try {

            openToolbarMenu(driver);

            (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.findElement(By.id("CI_MAINMENU_topMenuItem0x0")).isDisplayed();
                }
            });

            driver.findElement(By.id("CI_MAINMENU_topMenuItem0x0")).click();

            (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage")
                            .findElement(By.id("multiQueryZoneFilters1")).isDisplayed();
                }
            });

            elementfound = true;

        }catch(TimeoutException e){

            i++;

        }

    }

    public static void openToolbarMenu(WebDriver driver) {

        driver.switchTo().defaultContent().switchTo().frame("main");

        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.switchTo().defaultContent().switchTo().frame("main").findElement(By.xpath("//span[@id='IM_menuButton' and not(@disabled)]")).isEnabled();
            }
        });

        WebElement toolbarMenu = driver.findElement(By.id("IM_menuButton"));

        for(int i = 0; i < 5; i++){
            toolbarMenu.click();
        }

        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("CI_MAINMENU_topMenuItem0x8")).isDisplayed();
            }
        });

    }

    public static void openAdminMenu(WebDriver driver) {

        driver.switchTo().defaultContent().switchTo().frame("main");

        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.switchTo().defaultContent().switchTo().frame("main").findElement(By.xpath("//span[@id='IM_adminButton' and not(@disabled)]")).isEnabled();
            }
        });

        WebElement adminMenu = driver.findElement(By.id("IM_adminButton"));

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {

            try {

                adminMenu.click();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("CI_ADMINMENU_topMenuItem0x3")).isDisplayed();
                    }
                });

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            } catch (WebDriverException e){

                break;

            }

        }

    }

    public static void openTaxRole(final WebDriver driver) {

        //waitForEnabledElement(FrameType.TAB_MENU, driver, By.id("C1360TXR_T_LBL"));
        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return Navi.findElementAtTabMenu(d, By.id("C1360TXR_T_LBL")).isEnabled();
            }
        });

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {

            try {
                patientlyClickAttempt(driver, FrameType.TAB_MENU, By.id("C1360TXR_T_LBL"), FrameType.TAB_PAGE, By.xpath("//span[@id='zoneHeader4']//table[@class='zoneHeader']//a[@class='oraZoneHeaderLink' and not(@disabled) and text() = 'Tilføj']"));

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            }

        }

    }

    public static void openAccount(final WebDriver driver) {

        Navi.tabMenu(driver);

        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("C1360ACCT_T_LBL")).isDisplayed();
            }
        });

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {

            try {

                WebElement accountTab = driver.findElement(By.id("C1360ACCT_T_LBL"));

                accountTab.click();

                elementCaught = true;

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return findElementAtZoneMapFrame_1(driver, By.id("forecastButton")).isDisplayed();
                    }
                });

            } catch (TimeoutException e) {

                i++;

            }

        }

    }

    public static void openFinancialInformation(final WebDriver driver) {

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {

            try {

                Navi.tabMenu(driver);

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("C1360FIN_T_LBL")).isEnabled();
                    }
                });

                WebElement financialInformationTab = driver.findElement(By.id("C1360FIN_T_LBL"));

                financialInformationTab.click();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return findElementAtZoneMapFrame_1(driver, By.id("forecastDt")).isDisplayed();
                    }
                });

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            }

        }

    }

    public static void openShowAccountContext(WebDriver driver){

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {

            try {

                Navi.dashboard(driver);

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("title_heading_214")).isDisplayed();
                    }
                });

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.xpath("//img[@title='Vis konto kontekst']")).isDisplayed();
                    }
                });

                WebElement showAccountContext = driver.findElement(By.xpath("//img[@title='Vis konto kontekst']"));

                showAccountContext.click();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("CI_CONTEXTACCOUNT_subMenuItem0x2")).isDisplayed();
                    }
                });

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            }

        }

    }

    public static void openReactivateDebtor(WebDriver driver){

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {

            try {

                openShowAccountContext(driver);

                Navi.mainFrame(driver);

                WebElement reactivateDebtor = driver.findElement(By.id("CI_CONTEXTACCOUNT_subMenuItem0x1"));

                reactivateDebtor.click();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("uiMap").findElement(By.id("ReactivationReason")).isDisplayed();
                    }
                });

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            }

        }

    }

    public static void openCreateDemandLetter(WebDriver driver){

        boolean elementFound = false;

        int i = 0;

        while(!elementFound && i<5) {

            try {

                openShowAccountContext(driver);

                Navi.mainFrame(driver);

                driver.findElement(By.id("CI_CONTEXTACCOUNT_subMenuItem0x6")).click();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("uiMap").findElement(By.id("isSelected_0")).isDisplayed();
                    }
                });

                elementFound = true;

            } catch (TimeoutException e) {

                i++;

            } catch (StaleElementReferenceException e){

                break;

            }

        }

    }

    public static void openCreateFee(WebDriver driver){

        boolean elementFound = false;

        int i = 0;

        while(!elementFound && i<5) {

            try {

                openShowAccountContext(driver);

                Navi.mainFrame(driver);

                WebElement createFee = driver.findElement(By.id("CI_CONTEXTACCOUNT_subMenuItem0x3"));

                createFee.click();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("uiMap").findElement(By.id("claimType")).isDisplayed();
                    }
                });

                elementFound = true;

            } catch (TimeoutException e) {

                i++;

            }

        }

    }

    public static void openCreateAfdragsordning(WebDriver driver){

        boolean elementFound = false;

        int i = 0;

        while(!elementFound && i<5) {

            try {

                openShowAccountContext(driver);

                Navi.mainFrame(driver);

                WebElement createFee = driver.findElement(By.id("CI_CONTEXTACCOUNT_subMenuItem0x13"));

                createFee.click();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("uiMap").findElement(By.id("boGroup_payplanTypeNote")).isDisplayed();
                    }
                });

                elementFound = true;

            } catch (TimeoutException e) {

                i++;

            }

        }

    }

    public static void open360DegreeSearchPage(WebDriver driver) {

        boolean elementFound = false;

        int i = 0;

        while(!elementFound && i<5) {

            try {

                openToolbarMenu(driver);

                WebElement _360DegreeSearch = driver.findElement(By.id("CI_MAINMENU_topMenuItem0x0"));

                _360DegreeSearch.click();

                _360PersonPage newpage = new _360PersonPage(driver);

                newpage.fullNameField();

                elementFound = true;

            } catch (TimeoutException e) {

                i++;

            }

        }

    }

    public static void openMyPreferences(WebDriver driver) {

        openToolbarMenu(driver);

        WebElement myPreferences = driver.findElement(By.id("CI_MAINMENU_topMenuItem0x12"));

        myPreferences.click();

    }

    public static void registrationAddAddress(WebDriver driver) {

        openToolbarMenu(driver);

        WebElement registration = driver.findElement(By.id("CI_MAINMENU_topMenuItem0x6"));

        Actions builder = new Actions(driver);

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {
            try {

                builder.moveToElement(registration).perform();

                builder.moveToElement(registration).moveToElement(driver.findElement(By.id("CI_CUSTOMERINFORMATION_subMenuItem1x0")))
                        .perform();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("c1addrTabMenuAdd")).isDisplayed();
                    }
                });

                //driver.findElement(By.id("c1addrTabMenuAdd")).click();

                patientlyClick(driver, By.id("c1addrTabMenuAdd"));

                (new WebDriverWait(driver, MEDIUMWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("uiMap").findElement(By.id("boGroup_address1")).isDisplayed();
                    }
                });

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            } catch (StaleElementReferenceException e){

                i++;

            }
        }



    }

    public static void openRegistrationAddressSearch(WebDriver driver) {

        openToolbarMenu(driver);

        WebElement registration = driver.findElement(By.id("CI_MAINMENU_topMenuItem0x6"));

        Actions builder = new Actions(driver);

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {

            try {

                builder.moveToElement(registration).perform();

                builder.moveToElement(registration).moveToElement(driver.findElement(By.id("CI_CUSTOMERINFORMATION_subMenuItem1x0")))
                        .perform();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("c1addrqTabMenu")).isDisplayed();
                    }
                });

                driver.findElement(By.id("c1addrqTabMenu")).click();

                elementCaught = true;

            } catch (StaleElementReferenceException e) {
                i++;
            } catch (NoSuchElementException e) {
                i++;
            }

        }

    }

    public static void openRegistrationPersonAdd(WebDriver driver) {

        openToolbarMenu(driver);

        WebElement registration = driver.findElement(By.id("CI_MAINMENU_topMenuItem0x6"));

        Actions builder = new Actions(driver);

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {
            try {
                builder.moveToElement(registration).perform();

                builder.moveToElement(registration).moveToElement(driver.findElement(By.id("CI_CUSTOMERINFORMATION_subMenuItem1x4")))
                        .perform();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("CI0000000135")).isDisplayed();
                    }
                });

                driver.findElement(By.id("CI0000000135")).click();

                elementCaught = true;

            } catch (StaleElementReferenceException e) {
                i++;
            } catch (NoSuchElementException e) {
                i++;
            } catch (TimeoutException e){
                i++;
            }
        }

        (new WebDriverWait(driver, LONGWAITTIME)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("uiMap").findElement(By.id("type")).isDisplayed();
            }
        });

    }

    public static void openToDoAdd(WebDriver driver){

        openToolbarMenu(driver);

        WebElement toDo = driver.findElement(By.id("CI_MAINMENU_topMenuItem0x11"));

        Actions builder = new Actions(driver);

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<5) {
            try {

                builder.moveToElement(toDo).perform();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("CI_TODO_subMenuItem0x7")).isDisplayed();
                    }
                });

                //driver.findElement(By.id("c1addrTabMenuAdd")).click();

                patientlyClick(driver, By.id("CI_TODO_subMenuItem0x7"));

                (new WebDriverWait(driver, MEDIUMWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage").findElement(By.id("toDoPriority")).isDisplayed();
                    }
                });

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            }
        }

    }

    public static void openTaxFormSearch(WebDriver driver) {

        openToolbarMenu(driver);

        WebElement registration = driver.findElement(By.id("CI_MAINMENU_topMenuItem0x3"));

        Actions builder = new Actions(driver);

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {
            try {

                builder.moveToElement(registration).perform();

                builder.moveToElement(registration).moveToElement(driver.findElement(By.id("CI_FORM_subMenuItem1x0")))
                        .perform();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("c1txfrmqTabMenu")).isDisplayed();
                    }
                });

                //driver.findElement(By.id("c1addrTabMenuAdd")).click();

                patientlyClick(driver, By.id("c1txfrmqTabMenu"));

                (new WebDriverWait(driver, MEDIUMWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage").findElement(By.id("multiQueryZoneFilters1")).isDisplayed();
                    }
                });

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            }
        }



    }

    public static void openToolBarHome(WebDriver driver) {

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {

            try {

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("IM_USER_HOME")).isDisplayed();
                    }
                });

                WebElement home = driver.findElement(By.id("IM_USER_HOME"));

                home.click();

                elementCaught = true;

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("USER_ID")).isDisplayed();
                    }
                });

            } catch (TimeoutException e) {

                i++;

            }

        }

    }

    public static void openBBusinessObjectSearch(WebDriver driver) {

        openAdminMenu(driver);

        WebElement b = driver.findElement(By.id("CI_ADMINMENU_topMenuItem0x1"));

        Actions builder = new Actions(driver);

        builder.moveToElement(b).perform();

        builder.moveToElement(b).moveToElement(driver.findElement(By.id("CI_SG_ADMIN_B_subMenuItem1x8"))).perform();

        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("BusinessObjectTabMenu")).isDisplayed();
            }
        });

        driver.findElement(By.id("BusinessObjectTabMenu")).click();

    }

    public static void openFBusinessObjectSearch(WebDriver driver) {

        openAdminMenu(driver);

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {

            try {

                WebElement f = driver.findElement(By.id("CI_ADMINMENU_topMenuItem0x4"));

                Actions builder = new Actions(driver);

                mainFrame(driver);

                builder.moveToElement(f).perform();

                builder.moveToElement(f).moveToElement(driver.findElement(By.id("CI_SG_ADMIN_F_subMenuItem1x18"))).perform();

                (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("BusinessObjectTabMenu")).isDisplayed();
                    }
                });

                patientlyClick(driver, By.id("BusinessObjectTabMenu"));

                (new WebDriverWait(driver, MEDIUMWAITTIME)).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return getHandleToWindow("Forretningsobjekt søgning", d).findElement(By.id("BUS_OBJ_CD")).isDisplayed();
                    }
                });

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            } catch (NoSuchElementException e){

                i++;

            } catch (StaleElementReferenceException e){

                openAdminMenu(driver);

                i++;

            }

        }

    }

    public static void openTaskOverview(WebDriver driver) {

        openToolbarMenu(driver);

        WebElement todo = driver.findElement(By.id("CI_MAINMENU_topMenuItem0x11"));

        Actions builder = new Actions(driver);

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {
            try {
                builder.moveToElement(todo).perform();

                builder.moveToElement(todo).moveToElement(driver.findElement(By.id("CI_TODO_subMenuItem0x7")))
                        .perform();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("CI_TODO_subMenuItem0x7")).isDisplayed();
                    }
                });

                driver.findElement(By.id("CI_TODO_subMenuItem0x7")).click();

                elementCaught = true;

            } catch (StaleElementReferenceException e) {
                i++;
            }
        }

        (new WebDriverWait(driver, LONGWAITTIME)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage").findElement(By.id("title_heading_1")).isDisplayed();
            }
        });

    }
    public static void openMovePaymentFromWaitingaccount(WebDriver driver) {

        openToolbarMenu(driver);

        WebElement payments = driver.findElement(By.id("CI_MAINMENU_topMenuItem0x4"));

        Actions builder = new Actions(driver);

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {
            try {
                builder.moveToElement(payments).perform();

                builder.moveToElement(payments).moveToElement(driver.findElement(By.id("C1_PAY_PROCESSING_subMenuItem0x13")))
                        .perform();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("C1_PAY_PROCESSING_subMenuItem0x13")).isDisplayed();
                    }
                });

                driver.findElement(By.id("C1_PAY_PROCESSING_subMenuItem0x13")).click();

                elementCaught = true;

            } catch (StaleElementReferenceException e) {
                i++;
            }
        }

        (new WebDriverWait(driver, LONGWAITTIME)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("uiMap").findElement(By.id("boGroup_accountId")).isDisplayed();
            }
        });

    }

    public static void clickToolBarHistory(WebDriver driver) {

        driver.switchTo().defaultContent().switchTo().frame("main");

        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("IM_HISTORY")).isDisplayed();
            }
        });

        WebElement history = driver.findElement(By.id("IM_HISTORY"));

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {

            try {

                history.click();

                (new WebDriverWait(driver, SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("menuDiv_0")).isDisplayed();
                    }
                });

                elementCaught = true;

            } catch (TimeoutException e) {
                i++;
            }
        }

    }

    public static void clickToolbarHistoryGoBack(WebDriver driver) {

        driver.switchTo().defaultContent().switchTo().frame("main");

        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("IM_GOBACK")).isDisplayed();
            }
        });

        WebElement goBack = driver.findElement(By.id("IM_GOBACK"));

        goBack.click();

    }

    public static void clickToolbarHistoryGoForward(WebDriver driver) {

        driver.switchTo().defaultContent().switchTo().frame("main");

        (new WebDriverWait(driver, LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("IM_GOFORWARD")).isDisplayed();
            }
        });

        WebElement goForward = driver.findElement(By.id("IM_GOFORWARD"));

        goForward.click();

    }

    public static WebDriver getHandleToWindow(String title, WebDriver driver){

        //parentWindowHandle = WebDriverInitialize.getDriver().getWindowHandle(); // save the current window handle.
        WebDriver popup = null;
        Set<String> windowIterator = driver.getWindowHandles();
        System.err.println("No of windows :  " + windowIterator.size());
        for (String s : windowIterator) {
            String windowHandle = s;
            popup = driver.switchTo().window(windowHandle);
            System.out.println("Window Title : " + popup.getTitle());
            System.out.println("Window Url : " + popup.getCurrentUrl());
            if (popup.getTitle().equals(title) ){
                System.out.println("Selected Window Title : " + popup.getTitle());
                return popup;
            }

        }
        System.out.println("Window Title :" + popup.getTitle());
        System.out.println();
        return popup;
    }

    public static void invokeBrowser(String url, final WebDriver driver) {

        System.out.println("Thread id = " + Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = " + driver.hashCode());
        driver.get(url);

    }

    /**
     * Methods for finding a single WebElement in a frame.
     */
    private static WebElement findElementAtDefaultContent(final WebDriver driver, By by) {
        defaultContent(driver);
        return driver.findElement(by);
    }
    private static WebElement findElementAtMain(final WebDriver driver, By by) {
        mainFrame(driver);
        return driver.findElement(by);
    }
    private static WebElement findElementAtDashboard(final WebDriver driver, By by) {
        dashboard(driver);
        return driver.findElement(by);
    }
    private static WebElement findElementAtUiMap(final WebDriver driver, By by) {
        uiMap(driver);
        return driver.findElement(by);
    }
    public static WebElement findElementAtTabPage(final WebDriver driver, By by) {
        tabPage(driver);
        return driver.findElement(by);
    }
    public static WebElement findElementAtTabMenu(final WebDriver driver, By by) {
        tabMenu(driver);
        return driver.findElement(by);
    }
    private static WebElement findElementAtZoneMapFrame_1(final WebDriver driver, By by) {
        zoneMapFrame_1(driver);
        return driver.findElement(by);
    }
    private static WebElement findElementAtZoneMapFrame_2(final WebDriver driver, By by) {
        zoneMapFrame_2(driver);
        return driver.findElement(by);
    }
    private static WebElement findElementAtZoneMapFrame_4(final WebDriver driver, By by) {
        zoneMapFrame_4(driver);
        return driver.findElement(by);
    }
    private static WebElement findElementAtZoneMapFrame_106(final WebDriver driver, By by) {
        zoneMapFrame_106(driver);
        return driver.findElement(by);
    }
    private static WebElement findElementAtQuickAddTndr(final WebDriver driver, By by) {
        quickAddTndr(driver);
        return driver.findElement(by);
    }
    private static WebElement findElementAtUserCharGrid(final WebDriver driver, By by) {
        userCharGrid(driver);
        return driver.findElement(by);
    }

    /**
     * Methods for finding a single Select in a frame.
     */
    public static Select selectElementAtDashboard(final WebDriver driver, By by) {
        dashboard(driver);
        return new Select(driver.findElement(by));
    }
    public static Select selectElementAtUiMap(final WebDriver driver, By by) {
        uiMap(driver);
        return new Select(driver.findElement(by));
    }
    public static Select selectElementAtTabPage(final WebDriver driver, By by) {
        tabPage(driver);
        return new Select(driver.findElement(by));
    }
    public static Select selectElementAtTabMenu(final WebDriver driver, By by) {
        tabMenu(driver);
        return new Select(driver.findElement(by));
    }
    public static Select selectElementAtZoneMapFrame_1(final WebDriver driver, By by) {
        zoneMapFrame_1(driver);
        return new Select(driver.findElement(by));
    }

    /**
     *
     * Methods for finding a list of WebElements in a frame.
     */
    public static List<WebElement> findElementsAtTabPage(final WebDriver driver, By by) {
        tabPage(driver);
        return driver.findElements(by);
    }

}
