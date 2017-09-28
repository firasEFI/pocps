package utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.Predicate;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.taxobjects.Fordring;
import icisel.testng.SmartAssert;
import icisel.testng.TestContext;
import icisel.utils.TO_Tools;
import icisel.utils.driver.patient.Patient;
import modules.MO_360GradersSoegning;
import pageobjects.generic.PO_Opret_Fordring;

/**
 * @author piorecki
 *
 */
public class FordringManager {

    private TestContext testContext;

    public FordringManager(TestContext testContext) {
        this.testContext = testContext;
    }

    /**
     * Søger efter fordring for et cpr number. Antager at bruger allerede er
     * logget på PSRM.
     * 
     * @param cprNummer
     */
    public List<Fordring> getFordringerByCpr(String cprNummer) {
        List<Fordring> fordringer = new ArrayList<>();

        MO_360GradersSoegning.goToSoegefunktion(testContext);

        MO_360GradersSoegning.indtastCPR(cprNummer);

        MO_360GradersSoegning.klikSoeg();

        // check if any results - waiting patiently for results to appear
        List<WebElement> webElements = testContext.getDriver()
                .findElementsPatiently(By.xpath("//table/tbody[@id='dataExplorerTableBody1']//tr[@class='']"));
        System.out.println("Number of results returned in fordrings search: " + webElements.size());

        if (webElements.size() > 0) {
            for (@SuppressWarnings("unused")
            WebElement webElement : webElements) {
                // in principle we should copy data from the returned
                // webelements into
                // the fordring(s) for now we just return a list with empty
                // fordringer
                fordringer.add(new Fordring());
            }
        }

        testContext.resetFrameContext();

        return fordringer;
    }

    /**
     * Funktion opretter fordring vha. et fordringsobjekt.
     * 
     * @param fordring
     */
    public void opretFordring(Fordring fordring) {

        goToFordringsFormularByType(fordring.getsFormulartype());

        fillFordringsFormular(fordring);

        postFordring();
    }

    /**
     * Går til oprettelse af fordring menu og vælger den korrekte type fodring.
     * 
     * @param fordringType
     */
    public void goToFordringsFormularByType(String fordringType) {
        MenuNavigator.menu().formularer().fordringsformular().tilfoej();

        // Indsaet fordringstype i tekstfeltet
        PO_Opret_Fordring.input_Formulartype.sendKeys(fordringType);

        // Tryk TAB paa tastaturet
        testContext.getDriver().findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.TAB));

        // Screenshot
        testContext.doScreenshot();

        // Tryk paa "OK"
        PO_Opret_Fordring.btn_FormulartypeOK.click();

        PO_Opret_Fordring.lnk_VisAlle.click();

        // Vent
        PO_Opret_Fordring.input_PersonCPRNummer.waitFor();
    }

    /**
     * Udfylder en fordrings formular.
     * 
     * @param fordring
     */
    public void fillFordringsFormular(Fordring fordring) {
        System.out.println("Udfylder formular");

        // Indtast oplysninger fra excel ark i formularen
        PO_Opret_Fordring.drp_Formularkilde.pick(fordring.getsFormularkilde());
        PO_Opret_Fordring.input_ModtagelsesdatoPrimaer.sendKeys(fordring.getsModtagelsesdatoPrimaer());
        PO_Opret_Fordring.input_ActionId.sendKeys(fordring.getsAktionsID());
        PO_Opret_Fordring.input_FordringshaverID.sendKeys(fordring.getsFordringshaverID());
        PO_Opret_Fordring.input_FordringstypeKode.sendKeys(fordring.getsFordringstypeKode());
        PO_Opret_Fordring.input_FordringshaverReference.sendKeys(fordring.getsFordringshaverReference());
        PO_Opret_Fordring.input_FordringsID.sendKeys(fordring.getsFordringsID());
        PO_Opret_Fordring.input_ModtagelsesdatoFordring.sendKeys(fordring.getsModtagelsesdatoFordring());
        PO_Opret_Fordring.input_Forfaldsdato.sendKeys(fordring.getsForfaldsdato());
        PO_Opret_Fordring.input_IndarbejdelsesDato.sendKeys(fordring.getsIndarbejdelsesDato()); // fixes
                                                                                                // med
                                                                                                // eget
                                                                                                // parameter
                                                                                                // om
                                                                                                // noedvendigt
        PO_Opret_Fordring.input_SidsteBetalingsdato.sendKeys(fordring.getsSidsteBetalingsdato());
        PO_Opret_Fordring.input_ValutaKode.sendKeys(fordring.getsValutaKode());
        PO_Opret_Fordring.input_Beloeb.sendKeys(fordring.getsBeloeb());
        PO_Opret_Fordring.input_PeriodeFraDato.sendKeys(fordring.getsPeriodeFraDato());
        PO_Opret_Fordring.input_PeriodeTilDato.sendKeys(fordring.getsPeriodeTilDato());
        PO_Opret_Fordring.input_RenteregelNummer.sendKeys(fordring.getsRenteregelNummer());
        PO_Opret_Fordring.drp_RentesatsRegel.pick(fordring.getsRentesatsRegel());
        PO_Opret_Fordring.input_Rentesats.sendKeys(fordring.getsRentesats());
        PO_Opret_Fordring.input_PersonCPRNummer.sendKeys(fordring.getsPersonCPRNummer());

        // Nye felter; udkommenterede er udgaaet
        PO_Opret_Fordring.input_HovedFordringsID.sendKeys(fordring.getsHovedFordringsID());
        PO_Opret_Fordring.input_FordringshaverBeskrivelse.sendKeys(fordring.getsFordringshaverBeskrivelse());
        PO_Opret_Fordring.input_AlternativKontaktId.sendKeys(fordring.getsAlternativKontaktID());
        PO_Opret_Fordring.input_VirksomhedSENummer.sendKeys(fordring.getsVirksomhedSENummer());
        PO_Opret_Fordring.input_HaeftelsesForaeldelsesdato.sendKeys(fordring.getsHaeftelsesForaeldelsesdato());
        if (fordring.isChk_HaeftelseUnderBobehandling()) PO_Opret_Fordring.chk_HaeftelseUnderBobehandling.click();
        if (fordring.isChk_HaeftelsesAfgoerelse()) PO_Opret_Fordring.chk_HaeftelsesAfgoerelse.click();
        PO_Opret_Fordring.input_HaeftelsesAfgoerelsesdato.sendKeys(fordring.getsHaeftelsesAfgoerelsesdato());
        if (fordring.isChk_HaeftelsesUdligning()) PO_Opret_Fordring.chk_HaeftelsesUdligning.click();
        PO_Opret_Fordring.input_HaeftelsesForligsdato.sendKeys(fordring.getsHaeftelsesForligsdato());
        PO_Opret_Fordring.input_NoteTekst.sendKeys(fordring.getsNoteTekst());
        PO_Opret_Fordring.input_DokumentType.sendKeys(fordring.getsNoteTekst());
        PO_Opret_Fordring.input_DokumentFilType.sendKeys(fordring.getsDokumentFilType());
        PO_Opret_Fordring.input_DokumentFilIndhold.sendKeys(fordring.getsDokumentFilIndhold());

        // Screenshot
        testContext.doScreenshot();
    }

    /**
     * Gemmer en fordrings formular.
     * 
     * @param propertyProvider
     */
    public void postFordring() {

        PO_Opret_Fordring.btn_GemOgFortsaet.click();

        Patient.ventTilHoejreMenuErLoadet(testContext.getDriver());

        // TODO: piorecki: Nedenstående sleep skal fjernes/laves om
        TO_Tools.sleep(8000);

        // Tryk paa "Gem"
        PO_Opret_Fordring.btn_GemFordring.click();

        // Tryk paa "Valider & Post"
        // Vent
        TO_Tools.sleep(3000);
        PO_Opret_Fordring.btn_ValiderOgPost.clickUntilDisabled();

        // Wait until status equals Posted
        Predicate<WebDriver> test = new Predicate<WebDriver>() {
            private String[] accepteredeStati = { "Posted", "Journaliseret" };

            public boolean apply(WebDriver arg0) {
                testContext.getDriver().setCurrentFrame(null);
                String text = PO_Opret_Fordring.txt_Status.getText();
                boolean statusAccepteret = false;
                for (String tjek : accepteredeStati) {
                    if (text.equalsIgnoreCase(tjek)) {
                        statusAccepteret = true;
                        break;
                    }
                }
                return statusAccepteret;
            }
        };
        // vent indtil ovenstaaende (navnlig indtil status er "Posted" eller
        // "Journaliseret")
        long timeout = 45000;
        Patient.waitUntilTrue(testContext.getDriver(), test, timeout);
        String statusTekst = PO_Opret_Fordring.txt_Status.getText();
        boolean assertionTest = (statusTekst.toLowerCase().contains("posted"))
                || (statusTekst.toLowerCase().contains("journaliseret"));
        String fejlmeddelelse = String
                .format("Forventede fordringsstatus 'Posted' eller 'Journaliseret', men fandt '%s'", statusTekst);
        SmartAssert.assertTrue(testContext, assertionTest, fejlmeddelelse);

        // Screenshot
        testContext.doScreenshot();
    }
}
