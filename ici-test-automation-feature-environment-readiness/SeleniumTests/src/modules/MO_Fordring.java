package modules;

import java.util.InputMismatchException;

import org.apache.http.ParseException;
import org.codehaus.plexus.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import icisel.taxobjects.Fordring;
import icisel.testng.TestContext;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_Opret_Fordring;

public class MO_Fordring {

    /**
     * Funktion opretter fordring vha. et fordringsobjekt
     * 
     * @param testContext
     *            = I testcases = this, i MO_Methods = testContext
     * @param fordring
     *            =
     */
    public static Fordring opretFordring(TestContext testContext, Fordring fordring) {
        MO_Fordring.goToFordringsFormular(testContext, fordring);

        MO_Fordring.fillFordringsFormular(testContext, fordring);

        return MO_Fordring.postFordring(testContext, fordring);
    }

    public static void goToFordringsFormular(final TestContext testContext, Fordring fordring) {
        MenuNavigator.menu().formularer().fordringsformular().tilfoej();
        PatientWebDriver driver = testContext.getDriver();

        // Indsaet fordringstype i tekstfeltet
        PO_Opret_Fordring.input_Formulartype.sendKeys(fordring.getsFormulartype());

        Actions actions = new Actions(driver);
        actions.moveToElement(PO_Opret_Fordring.input_Formulartype.getWebElement(driver)).sendKeys(Keys.TAB); // ...driver.findElement(By.xpath("//input[@oramdlabel='C1_EDIT_LBL']"))).sendKeys(Keys.TAB);
        actions.perform();

        // Tryk TAB paa tastaturet, indtil ordet "Modtag Fordring" fremkommer
        // PO_Opret_Fordring.input_Formulartype.waitingFor(PO_Opret_Fordring.txt_modtagFordring).pressKey(Keys.TAB);

        // Screenshot
        testContext.doScreenshot();

        // Tryk paa "OK"
        PO_Opret_Fordring.btn_FormulartypeOK.waitUntilClickable();
        PO_Opret_Fordring.btn_FormulartypeOK.click();

        // Klik på "Vis Alle", indtil "Skjul alle" fremkommer
        PO_Opret_Fordring.lnk_VisAlle.waitingFor(PO_Opret_Fordring.lnk_SkjulAlle).click();

    }

    public static void fillFordringsFormular(final TestContext testContext, Fordring fordring) {
        // Indtast oplysninger fra excel ark i formularen
        PatientWebDriver driver = testContext.getDriver();

        // Improve efficiency of sendKeys to Input by bypassing ici-sel's
        // self-validating sendKeys

        PO_Opret_Fordring.drp_Formularkilde.pick(fordring.getsFormularkilde());
        PO_Opret_Fordring.drp_Formularkilde.pick(fordring.getsFormularkilde());
        // Now we are already in the correct frame.
        driver.findElement(PO_Opret_Fordring.input_ModtagelsesdatoPrimaer.by)
                .sendKeys(fordring.getsModtagelsesdatoPrimaer());
        driver.findElement(PO_Opret_Fordring.input_ActionId.by).sendKeys(fordring.getsAktionsID());
        driver.findElement(PO_Opret_Fordring.input_FordringshaverID.by).sendKeys(fordring.getsFordringshaverID());
        driver.findElement(PO_Opret_Fordring.input_FordringstypeKode.by).sendKeys(fordring.getsFordringstypeKode());
        driver.findElement(PO_Opret_Fordring.input_FordringshaverReference.by)
                .sendKeys(fordring.getsFordringshaverReference());
        driver.findElement(PO_Opret_Fordring.input_FordringsID.by).sendKeys(fordring.getsFordringsID());
        driver.findElement(PO_Opret_Fordring.input_ModtagelsesdatoFordring)
                .sendKeys(fordring.getsModtagelsesdatoFordring());
        driver.findElement(PO_Opret_Fordring.input_Forfaldsdato.by).sendKeys(fordring.getsForfaldsdato());
        driver.findElement(PO_Opret_Fordring.input_IndarbejdelsesDato.by).sendKeys(fordring.getsIndarbejdelsesDato());
        driver.findElement(PO_Opret_Fordring.input_SidsteBetalingsdato.by).sendKeys(fordring.getsSidsteBetalingsdato());
        driver.findElement(PO_Opret_Fordring.input_ValutaKode.by).sendKeys(fordring.getsValutaKode());
        driver.findElement(PO_Opret_Fordring.input_Beloeb.by).sendKeys(fordring.getsBeloeb());
        driver.findElement(PO_Opret_Fordring.input_OriginaleValutaKode.by).sendKeys(fordring.getsValutaKode());
        driver.findElement(PO_Opret_Fordring.input_OriginaleBeloeb.by).sendKeys(fordring.getsBeloeb());
        driver.findElement(PO_Opret_Fordring.input_PeriodeFraDato.by).sendKeys(fordring.getsPeriodeFraDato());
        driver.findElement(PO_Opret_Fordring.input_PeriodeTilDato.by).sendKeys(fordring.getsPeriodeTilDato());
        driver.findElement(PO_Opret_Fordring.input_RenteregelNummer.by).sendKeys(fordring.getsRenteregelNummer());
        PO_Opret_Fordring.drp_RentesatsRegel.pick(fordring.getsRentesatsRegel());
        driver.findElement(PO_Opret_Fordring.input_Rentesats.by).sendKeys(fordring.getsRentesats());
        // safe fill on CPR last field to slow down and make sure that this
        // field IS filled
        PO_Opret_Fordring.input_PersonCPRNummer.sendKeys(fordring.getsPersonCPRNummer());

        // Screenshot
        testContext.doScreenshot();

    }

    public static Fordring postFordring(final TestContext testContext, Fordring fordring) {
        final PatientWebDriver driver = testContext.getDriver();

        // scroll to top
        WebElement ele = driver.findElement(By.className("oraPageTitle"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

        testContext.waitForPageToBeLoaded();
        PO_Opret_Fordring.btn_GemFordring.clickElementUntilNotPresent();

        // wait for text "pending" in span
        MO_Utilities.ventTilFeltIndeholderTekst(
                new PageElement(Frames.zoneMapFrame_1, By.xpath("//span[@orafield='taxFormInformation']")), "Pending");

        PO_Opret_Fordring.btn_ValiderOgPost.click();

        // wait for page to load before doing the screenshot
        testContext.waitForPageToBeLoaded();
        testContext.doScreenshot();

        MO_Utilities.ventTilFeltIndeholderTekst(
                new PageElement(Frames.zoneMapFrame_1, By.xpath("//span[@orafield='taxFormInformation']")), "Posted");

        // wait for page to load before doing the screenshot
        testContext.waitForPageToBeLoaded();
        testContext.doScreenshot();

        // New: Save internal ID if creation was succesful
        String internalId = extractNDigitNumberInParantheses(PO_Opret_Fordring.txt_InterntFordringsID.getText(), 10);
        fordring.setsInternId(internalId);

        return fordring;
    }

    public static String extractNDigitNumberInParantheses(String inputStringWithNumber, int lengthOfNumber) {
        if (!(lengthOfNumber > 0)) {
            throw new InputMismatchException("Length of number must be greater than 0.");
        }

        // Find first paranthesis pair with correct length
        int fromIndex = 0;
        int stringLength = inputStringWithNumber.length();
        NumberFormatException e = null;

        while (stringLength - fromIndex > lengthOfNumber) {
            // Return contents of paranthesis pair
            int left = inputStringWithNumber.indexOf('(', fromIndex) + 1;
            int right = inputStringWithNumber.indexOf(')', left);

            if (right - left == lengthOfNumber) {
                String numberCandidate = inputStringWithNumber.substring(left, right);

                try {
                    Assert.assertTrue(StringUtils.isNumeric(numberCandidate));
                    return numberCandidate;
                } catch (NumberFormatException e2) {
                    e = e2;
                }
            }
            fromIndex = right + 1;
        }
        if (e != null) {
            throw e;
        } else {
            throw new ParseException(String
                    .format("String %s does not contain number of length %d enclosed in parantheses.",
                            inputStringWithNumber, lengthOfNumber));
        }

    }
}
