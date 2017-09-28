package pageobjects.smoketests.psrm_navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.FrameType;

/**
 * Created by asol on 17-07-2017.
 */
public class CaseWorkerNavi {

    public static void open360DegreeSearch(WebDriver driver) {

        int i = 0;

        boolean elementfound = false;

        while(!elementfound && i<10)

            try {

                openToolbarMenu(driver);

                (new WebDriverWait(driver, Navi.SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("CI_MAINMENU_topMenuItem0x0")).isDisplayed();
                    }
                });

                driver.findElement(By.id("CI_MAINMENU_topMenuItem0x0")).click();

                (new WebDriverWait(driver, Navi.SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
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

        (new WebDriverWait(driver, Navi.LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.switchTo().defaultContent().switchTo().frame("main").findElement(By.xpath("//span[@id='IM_menuButton' and not(@disabled)]")).isEnabled();
            }
        });

        WebElement toolbarMenu = driver.findElement(By.id("IM_menuButton"));

        for(int i = 0; i < 5; i++){
            toolbarMenu.click();
        }

        (new WebDriverWait(driver, Navi.LONGWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("CI_MAINMENU_topMenuItem0x4")).isDisplayed();
            }
        });

    }

    public static void moveToRegistration(WebDriver driver) {

        int i = 0;

        boolean elementfound = false;

        while(!elementfound && i<5)

            try {

                openToolbarMenu(driver);

                (new WebDriverWait(driver, Navi.SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("CI_MAINMENU_topMenuItem0x7")).isDisplayed();
                    }
                });

                WebElement registration = Navi.patientlyFindDisplayedElement(driver, By.id("CI_MAINMENU_topMenuItem0x7"));

                Actions builder = new Actions(driver);

                builder.moveToElement(registration).perform();

                Navi.patientlyFindDisplayedElement(FrameType.MAIN, driver, By.id("CI_CUSTOMERINFORMATION_subMenuItem1x0"));

                elementfound = true;

            }catch(TimeoutException e){

                i++;

            }

    }

    public static void moveToRegistrationPart(WebDriver driver) {

        int i = 0;

        boolean elementfound = false;

        while(!elementfound && i<5)

            try {

                openToolbarMenu(driver);

                (new WebDriverWait(driver, Navi.SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("CI_MAINMENU_topMenuItem0x7")).isDisplayed();
                    }
                });

                WebElement registration = Navi.patientlyFindDisplayedElement(driver, By.id("CI_MAINMENU_topMenuItem0x7"));

                Actions builder = new Actions(driver);

                builder.moveToElement(registration).perform();

                builder.moveToElement(registration)
                        .moveToElement(Navi.patientlyFindDisplayedElement(driver, By.id("CI_CUSTOMERINFORMATION_subMenuItem1x7"))).perform();

                elementfound = true;

            }catch(TimeoutException e){

                i++;

            }

    }

    public static void moveToAccounting(WebDriver driver) {

        int i = 0;

        boolean elementfound = false;

        while(!elementfound && i<5)

            try {

                openToolbarMenu(driver);

                (new WebDriverWait(driver, Navi.SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("CI_MAINMENU_topMenuItem0x7")).isDisplayed();
                    }
                });

                WebElement registration = Navi.patientlyFindDisplayedElement(driver, By.id("CI_MAINMENU_topMenuItem0x8"));

                Actions builder = new Actions(driver);

                builder.moveToElement(registration).perform();

                Navi.patientlyFindDisplayedElement(FrameType.MAIN, driver, By.id("C1_ACCOUNTING_subMenuItem0x2"));

                elementfound = true;

            }catch(TimeoutException e){

                i++;

            }

    }

    public static void verifyNoMassPaymentRights(WebDriver driver) {

        moveToAccounting(driver);

        try{

            driver.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("C1_ACCOUNTING_subMenuItem0x0"));

            org.testng.Assert.fail("testT001_GeneralAndGodkender failed: The caseworker can see forbidden functionality");

        }catch(NoSuchElementException e){

            System.out.println("The case worker does not have the roles.");

        }


    }

    public static void verifyNoAddPersonRights(WebDriver driver) {

        moveToRegistrationPart(driver);

        try{

            driver.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("CI0000000135"));

            org.testng.Assert.fail("testT001_GeneralAndGodkender failed: The caseworker can see forbidden functionality");

        }catch(NoSuchElementException e){

            System.out.println("The case worker does not have the roles.");

        }


    }

    public static void verifyNoIndividualPaymentRights(WebDriver driver) {

        moveToAccounting(driver);

        try{

            driver.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("C1_ACCOUNTING_subMenuItem0x1"));

            org.testng.Assert.fail("testT001_GeneralAndGodkender failed: The caseworker can see forbidden functionality");

        }catch(NoSuchElementException e){

            System.out.println("The case worker does not have the roles.");

        }


    }

    public static void verifyNoTaxFormEditRights(WebDriver driver) {

        try{

            driver.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("CI_CUSTOMERINFORMATION_subMenuItem1x1"));

            org.testng.Assert.fail("testT001_GeneralAndGodkender failed: The caseworker can see forbidden functionality");

            driver.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("CI_CUSTOMERINFORMATION_subMenuItem1x1"));

            org.testng.Assert.fail("testT001_GeneralAndGodkender failed: The caseworker can see forbidden functionality");

        }catch(NoSuchElementException e){

            System.out.println("The case worker does not have the roles.");

        }

        try{

            driver.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("CI_CUSTOMERINFORMATION_subMenuItem1x2"));

            org.testng.Assert.fail("testT001_GeneralAndGodkender failed: The caseworker can see forbidden functionality");

            driver.switchTo().defaultContent().switchTo().frame("main").findElement(By.id("CI_CUSTOMERINFORMATION_subMenuItem1x2"));

            org.testng.Assert.fail("testT001_GeneralAndGodkender failed: The caseworker can see forbidden functionality");

        }catch(NoSuchElementException e){

            System.out.println("The case worker does not have the roles.");

        }


    }

    public static void verifyNoEditButton(WebDriver driver) {

        try {

            //Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_1, driver, By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[1]/input"));

            new WebDriverWait(driver, Navi.LONGWAITTIME).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("main")));

            new WebDriverWait(driver, Navi.LONGWAITTIME).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("zoneMapFrame_1")));

            driver.switchTo().defaultContent().switchTo().frame("main").switchTo()
                    .frame("zoneMapFrame_1").findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[1]/input"));

            org.testng.Assert.fail("testT001_GeneralAndGodkender failed: The caseworker can see forbidden functionality");

        } catch (NoSuchElementException e) {

            System.out.println("The case worker does not have the roles.");

        } catch (TimeoutException e) {

            System.out.println("The case worker does not have the roles. ");

        }

    }

    public static void openTaxFormSearch(WebDriver driver) {

        openToolbarMenu(driver);

        WebElement registration = driver.findElement(By.id("CI_MAINMENU_topMenuItem0x4"));

        Actions builder = new Actions(driver);

        boolean elementCaught = false;

        int i = 0;

        while(!elementCaught && i<10) {
            try {

                builder.moveToElement(registration).perform();

                builder.moveToElement(registration).moveToElement(driver.findElement(By.id("CI_FORM_subMenuItem1x0")))
                        .perform();

                (new WebDriverWait(driver, Navi.SHORTWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.findElement(By.id("c1txfrmqTabMenu")).isDisplayed();
                    }
                });

                //driver.findElement(By.id("c1addrTabMenuAdd")).click();

                Navi.patientlyClick(driver, By.id("c1txfrmqTabMenu"));

                (new WebDriverWait(driver, Navi.MEDIUMWAITTIME)).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.switchTo().defaultContent().switchTo().frame("main").switchTo().frame("tabPage").findElement(By.id("multiQueryZoneFilters1")).isDisplayed();
                    }
                });

                elementCaught = true;

            } catch (TimeoutException e) {

                i++;

            } catch (NoSuchElementException e){

                i++;

            } catch (StaleElementReferenceException e){

                i++;

            }
        }



    }

}
