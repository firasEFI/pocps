package modules;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.pageobjects.PO_Navigation;
import icisel.testng.SmartAssert;
import icisel.testng.TestContext;
import icisel.utils.TO_Tools;
import icisel.utils.driver.patient.PatientWebDriver;
import modules.types.KontoTransaktionsType;
import pageobjects.generic.PO_360GradersOverblik;
import pageobjects.generic.types.FaneTypes;

public class MO_Navigation360GradersOverblik {

    /**
     * Funktion leder til 360 graders overblik for den valgte skyldner og
     * direkte ind paa den specificerede fane
     * 
     * @param fane
     */
    public static void SkyldnerFaner(TestContext testContext, FaneTypes fane) {
        // Gå via konto kontekst menu til skyldners 360 graders overblik
        goTo360GradersOverblik(testContext);

        // Gaa til valgt fane
        switch(fane) {
        case PARTSOPLYSNING:
            klikFane(testContext, PO_360GradersOverblik.btn_Partsoplysningsfane);
            break;
        case FORDRING:
            klikFane(testContext, PO_360GradersOverblik.btn_Fordringsfane);
            break;
        case SAGSBEHANDLING:
            klikFane(testContext, PO_360GradersOverblik.btn_Sagsbehandlingsfane);
            break;
        case KONTO:
            klikFane(testContext, PO_360GradersOverblik.btn_Kontofane);
            break;
        default:
            System.out.println("Fane ikke genkendt!");
        }
    }

    /**
     * Funktion gaar til skyldners overblik og viser alle sager, samt
     * kontrollerer at sagen fremgår af siden
     * 
     * @param sagsID
     *            = SagsID på den sag, som det ønskes tjekket for om er på
     *            skyldners sagsoverblik
     */
    public static void TjekSkyldnersSager(TestContext testContext, String sagsID) {
        // Gaa til skyldners 360 graders overblik og ind paa fanen
        // Sagsbehandling
        MO_Navigation360GradersOverblik.SkyldnerFaner(testContext, FaneTypes.SAGSBEHANDLING);

        // Screenshot
        testContext.waitForPageToBeLoaded();
        testContext.doScreenshot();
        
        // Udvid overblikket over OCR-linjer såfremt dette ikke allerede er tilfældet.
        expandOverblikOcr(testContext);
        
        testContext.waitForPageToBeLoaded();
        Assert.assertTrue(PO_360GradersOverblik.lnk_Sagsbehandling_SagsID(sagsID).isDisplayed(),
                "Sagen (ID: " + sagsID + ") kan ikke ses af skyldners sagsbehandlingsfane");
    }

    /**
     * Funktion gaar til skyldners overblik og viser alle sager og OCR linjer.
     */
    public static void TjekSkyldnersOcrLinjer(TestContext testContext) {
        // Gaa til skyldners 360 graders overblik og ind paa fanen
        // Sagsbehandling
        MO_Navigation360GradersOverblik.SkyldnerFaner(testContext, FaneTypes.SAGSBEHANDLING);

        // Udvid overblikket over OCR-linjer såfremt dette ikke allerede er
        // tilfældet.
        expandOverblikOcr(testContext);

        // Screenshot
        testContext.doScreenshot();
    }

    /**
     * Funktion gaar til skyldners overblik og viser alle fordringer
     */
    public static void tjekSkyldnersFordringer(TestContext testContext) {
        // Gaa til skyldners sagsbehandlingsoverblik
        MO_Navigation360GradersOverblik.SkyldnerFaner(testContext, FaneTypes.FORDRING);

        // Tryk paa vis alle fordringer
        klikFordringerVisAlle(testContext);

        // Tryk paa vis fordringer med 0 i saldo
        klikFordringVis0Saldo(testContext);

        // Screenshot
        testContext.doScreenshot();
    }

    public static void goTo360GradersOverblik(TestContext testContext) {
        MenuNavigator.kontoKontekst().gaaTil360GradersOverblik();

        testContext.waitForPageToBeLoaded();
    }

    public static void klikFane(TestContext testContext, WebElement element) {
        TO_Tools.sleep(100);
        while (element.getAttribute("class").equalsIgnoreCase("inactiveTab")) {
            element.click();
            TO_Tools.sleep(50);
        }

        // Vent
        testContext.waitForPageToBeLoaded();
    }

    /**
     * Funktionen befinder sig i fanen "Fordringer"
     * 
     * @param testContext
     *            = testContext
     */
    public static void klikFordringerVisAlle(TestContext testContext) {
        PO_Navigation.btn_KundeOverblikFordringerVisAlle.click();

        // Vent
        testContext.waitForPageToBeLoaded();
    }

    /**
     * Funktionen befinder sig i fanen "Fordringer"
     * 
     * @param testContext
     *            = testContext
     */
    public static void klikFordringVis0Saldo(TestContext testContext) {
        PO_Navigation.btn_KundeOverblikFordringerVis0Saldo.click();

        // Vent
        testContext.waitForPageToBeLoaded();
    }

    /**
     * Knap befinder sig under Sagsbehandlingsfanen
     * 
     * @param testContext
     *            = testContext
     */
    public static void expandOverblikOcr(TestContext testContext) {
        Boolean overblikExpanded = false;

        PO_360GradersOverblik.btn_Sagsbehandling_ExpandOverblikOcr.waitFor();

        while (!overblikExpanded)
            if (PO_360GradersOverblik.btn_Sagsbehandling_ExpandOverblikOcr.getAttribute("class")
                    .equalsIgnoreCase("imgDiscloseCollapsedEna")) {
                PO_360GradersOverblik.btn_Sagsbehandling_ExpandOverblikOcr.click();

                testContext.waitForPageToBeLoaded();

                if (!PO_360GradersOverblik.btn_Sagsbehandling_ExpandOverblikOcr.getAttribute("class")
                        .equalsIgnoreCase("imgDiscloseCollapsedEna")) {
                    PO_360GradersOverblik.btn_Sagsbehandling_OverblikOcrTopText.waitFor();
                    SmartAssert.assertTrue(testContext,
                            PO_360GradersOverblik.btn_Sagsbehandling_OverblikOcrTopText.isDisplayed(),
                            "Overblik over OCR-linjer er ikke vist som forventet");
                    overblikExpanded = true;
                }
            }
    }

    /**
     * Funktionen finder Strings med fordringsID'er under Konto-fanen som
     * WebElementer
     * 
     * @param testContext
     *            = textContext
     * @return = returnerer liste med eksterne fordringsID'er som WebElementer
     */
    public static List<WebElement> getFordringsIDs(TestContext testContext) {
        PatientWebDriver driver = testContext.getDriver();

        // Lav liste af webelementer der indeholder fordringsID'erne.
        List<WebElement> fordringsIDs = PO_360GradersOverblik.lnk_Konto_FordringsID(driver);

        return fordringsIDs;
    }

    public static List<WebElement> getHaeftelser(TestContext testContext) {
        PatientWebDriver driver = testContext.getDriver();

        // Lav liste af webelementer der indeholder fordringsID'erne.
        List<WebElement> haeftelsesbeloeber = PO_360GradersOverblik.lnk_Konto_Haeftelse(driver);

        return haeftelsesbeloeber;
    }
    
    
    /**
     * Funktionen finder alle transaktioner fra konto oversigten. Der returneres et map af fordringsId kolonnen og beløb.
     * Kalderen af funktionen kan så efterfølgende hente de ønskede data ud fra fordringsId feltet.
     * 
     * @param testContext
     * 
     * @return map bestående af fordringsId og beløb. Bemærk at fordringsId indeholder meget mere information end selve fordringsId'en.
     */
    public static HashMap<String, String> getTransaktionerFraKonto(TestContext testContext, KontoTransaktionsType type) {
        PatientWebDriver driver = testContext.getDriver();
        HashMap<String, String> transaktionMap = new HashMap<>();
        
        List<WebElement> rows = PO_360GradersOverblik.lnk_Konto_Oversigt(driver);
        
        System.out.println("Fandt antal transaktioner på skyldner konto: "+rows.size());

        for (WebElement row : rows) {
            if (type == KontoTransaktionsType.ALL || type.getTypeString().equals(row.findElement(By.xpath("td[6]")).getText())) {
                // henter hele fordrings id feltet, som består af beløb, intern id, ekstern id, m.m.
                String fordringsIdCombined = row.findElement(By.xpath("td[10]")).getText();            
                String beloeb = row.findElement(By.xpath("td[7]")).getText();     
                beloeb = beloeb.replaceAll(",00kr.", "").replaceAll("\\.", "");
                
                transaktionMap.put(fordringsIdCombined, beloeb);
            }
        }
 
        return transaktionMap;
    }
    
    /**
     * Funktionen finder alle aktive (!= 0,00kr.) fordringer fra et map af transaktioner.
     * 
     * @param testContext
     * 
     * @return map bestående af det eksterne fordringsId og beløb.
     */
    public static HashMap<String, String> getAktiveFordringer(HashMap<String, String> transaktionsMap) {
        HashMap<String, String> filtreretMap = new HashMap<>();
        
        for (Map.Entry<String, String> entry : transaktionsMap.entrySet()) {
            String fordringsIdCombined = entry.getKey();
            // fordringer som allerede er nedskrevet skal ikke tælles med
            if (fordringsIdCombined.contains("- 0,00kr.")) {
                continue;
            }
            
            // find den eksterne fordrings id
            Pattern fordringsIDPattern = Pattern.compile("([0-9])+ ");
            String fordringId = null;
            Matcher fordringsIDMatcher = fordringsIDPattern.matcher(fordringsIdCombined);
            while (fordringsIDMatcher.find()) {
                fordringId = fordringsIDMatcher.group(0).trim();
            }
         
            String beloeb = entry.getValue();
            filtreretMap.put(fordringId, beloeb);
        }
 
        return filtreretMap;
    }

    /**
     * Knap befinder sig under Sagsbehandlingsfanen
     * 
     * @param testContext
     *            = testContext
     */
    public static void klikSagsID(TestContext testContext, String sagsID) {
        PO_360GradersOverblik.lnk_Sagsbehandling_SagsID(sagsID).click();

        // Vent på at side er loaded
        testContext.waitForPageToBeLoaded();
    }
    
    public static String getSagsOprettelsestidspunkt(String sagsID){
        String oprettelsestidspunkt = PO_360GradersOverblik.txt_Sagsbehandling_Oprettelsestidspunkt(sagsID).getText();
        
        return oprettelsestidspunkt;
    }
    
    public static void assertSagsOprettelsestidspunkt(String sagsID, String psrmDato){
        String oprettelsestidspunkt = getSagsOprettelsestidspunkt(sagsID);
        Assert.assertEquals(oprettelsestidspunkt, psrmDato);
    }
    
    public static String getSagsRaekkefoelge(String sagsID){
        String nummer = PO_360GradersOverblik.txt_Sagsbehandling_SagerRaekkefoelge(sagsID).getText();
        
        return nummer;
    }
    
    public static void assertSagsRaekkefoelge(String sagsID, String expectedNummer){
        String nummer = getSagsOprettelsestidspunkt(sagsID);
        Assert.assertEquals(nummer, expectedNummer);
    }

}
