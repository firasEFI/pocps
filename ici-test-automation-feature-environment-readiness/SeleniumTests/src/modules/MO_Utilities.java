package modules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.pageobjects.elements.Button;
import icisel.taxobjects.Fordring;
import icisel.testng.TestContext;
import icisel.utils.TO_Tools;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_BatchJobIndsendelse;
import pageobjects.generic.PO_Navigation;
import pageobjects.generic.PO_Tilbagesend;

public class MO_Utilities {

    /**
     * Denne funktion kan indsættes steder i løsningen, hvor det forventes at
     * der til tider kommer en alert grundet en udestående opgave. Dette er fx
     * ved afdragsordninger og påkravssager. Såfremt der er en alert, da fanger
     * og accepterer funktionen alerten. Er der ingen sker der intet.
     * 
     * @param testContext
     */
    public static void alertHandlerAccept(TestContext testContext) {
        PatientWebDriver driver = testContext.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 5);

        try {
            wait.until(ExpectedConditions.alertIsPresent()).accept();

        } catch (NoAlertPresentException e) {
        }
    }

    public static String matchFordringEkstern(List<WebElement> fordringsIDs, List<WebElement> haefterlser,
            Fordring fordringsDetalje, List<String> valgteFordringer) {
        String fordringsID = null;
        for (int i = 0; i < fordringsIDs.size(); i++) {
            Boolean alleredeValgt = false;
            for (int j = 0; j < valgteFordringer.size(); j++) {
                if (fordringsIDs.get(i).getText().contains(valgteFordringer.get(j))) {
                    alleredeValgt = true;
                    break;
                }
            }
            if (!alleredeValgt) {
                try {
                    String fordringsTekst = fordringsIDs.get(i).getText();
                    String forventetBeloeb = formatBeloeb(fordringsDetalje.getsBeloeb());
                    if (fordringsTekst.toLowerCase().contains(fordringsDetalje.fordringsTypeNavn().toLowerCase())
                            && haefterlser.get(i).getText().contains(forventetBeloeb)) {
                        Pattern fordringsIDPattern = Pattern.compile("([0-9])+ ");
                        Matcher fordringsIDMatcher = fordringsIDPattern.matcher(fordringsTekst);
                        while (fordringsIDMatcher.find()) {
                            // Anvender trim til at fjerne whitespace i
                            // slutningen
                            fordringsID = fordringsIDMatcher.group(0).trim();
                        }
                        break;
                    }
                } catch (Exception e) {
                    break;
                }
            }
        }
        return fordringsID;
    }

    public static String matchFordringIntern(List<WebElement> fordringsIDs, String eksternFordringsID,
            List<String> valgteFordringer) {
        String internFordringsID = null;
        for (int i = 0; i < fordringsIDs.size(); i++) {
            Boolean alleredeValgt = false;
            for (int j = 0; j < valgteFordringer.size(); j++) {
                if (fordringsIDs.get(i).getText().contains(valgteFordringer.get(j))) {
                    alleredeValgt = true;
                    break;
                }
            }
            if (!alleredeValgt) {
                if (fordringsIDs.get(i).getText().contains(eksternFordringsID)) {
                    String split1 = fordringsIDs.get(i).getText()
                            .substring(fordringsIDs.get(i).getText().lastIndexOf("("));
                    String[] split2 = split1.split("[(]");
                    String[] split3 = split2[1].split("[)]");
                    internFordringsID = split3[0];
                    break;
                }
            }
        }
        return internFordringsID;
    }

    public static List<String> mapInterneFordringer(TestContext testContext, List<String> eksterneFordringer) {
        // Hent fordringsID'erne
        List<WebElement> fordringsIDs = MO_Navigation360GradersOverblik.getFordringsIDs(testContext);

        // Match fordringer
        List<String> IDs = new ArrayList<>();

        for (int i = 0; i < eksterneFordringer.size(); i++) {
            String ID = MO_Utilities.matchFordringIntern(fordringsIDs, eksterneFordringer.get(i), IDs);

            // Tjek at der er fundet et match, således at fordringsID'et ikke
            // længere er "null"
            Assert.assertFalse(ID == null,
                    "Den forventede fordring " + eksterneFordringer.get(i) + " blev ikke fundet i PSRM. ");

            IDs.add(ID);
        }

        return IDs;
    }

    /**
     * Funktionen returnerer PSRMs dato og tid som to argumenter i en String[] i
     * formattet "dd-MM-yyyy" og ""HH-mm-ss"
     * 
     * @param context
     *            = testContext
     * @return String[] med {dato, tid} i formattet "dd-MM-yyyy" og ""HH-mm-ss"
     */
    public static String[] getPsrmDateTime(TestContext context) {
        goToBatchJobIndsendelse(context);

        Engine.getDriver().pause().until(MO_Wait.inputHasVisibleText(PO_BatchJobIndsendelse.today));
        String[] datoOgTid = { PO_BatchJobIndsendelse.today.getInputText(),
                PO_BatchJobIndsendelse.timeOfDay.getInputText() };

        return datoOgTid;
    }

    private static void goToBatchJobIndsendelse(TestContext context) {
        MenuNavigator.menu().batch().batchJobIndsendelse().tilfoej();
        context.waitForPageToBeLoaded();

    }

    /**
     * Funktion finder og returnerer skyldners kontonummer. Kraever at en
     * skyldner er fremsoegt og valgt.
     * 
     * @return = Skyldners kontonummer som string
     */
    public static String hentKontonummer() {
        System.out.println("Henter kontonummer");

        // Opret return-string
        String Kontonummer = null;

        // Hent tekst hvor kontonummer staar i
        String onClickValue = PO_Navigation.lnk_Kontonummer.getAttribute("onclick");

        // Split tekst ved ,
        String[] split = onClickValue.split(",");

        // Split tekst ved '
        String[] split2 = split[2].split("'");

        // Set kontonummer
        Kontonummer = split2[1];

        System.out.println("Kontonummer hentet: " + Kontonummer);

        return Kontonummer;
    }

    /**
     * Funktion vaelger fordring ud fra et angivet fordringsID
     * 
     * @param searchButton
     *            = element der trykkes paa for at aabne nyt vindue og finde
     *            fordring
     * @param fordringsID
     *            = Angives i excelark. Skal matche FordringsID paa fordring paa
     *            skyldner
     */
    public static void VaelgFordring(TestContext testContext, Button searchButton, String fordringsID) {
        PatientWebDriver driver = testContext.getDriver();
        try {
            // //Gem oprindeligt vindue
            // String parentWindow = driver.getWindowHandle();
            //
            // //Klik paa soegeikonet ud for Fordring - AABNER NYT VINDUE
            // searchButton.click();
            //
            //
            // //Vent
            // TO_Tools.sleep(3000);
            //
            // //Skift til nyt vindue
            // String subWindow = null;
            // Set<String> handles = driver.getWindowHandles(); // get all
            // window handles
            // Iterator<String> iterator = handles.iterator();
            // while (iterator.hasNext()){
            // subWindow = iterator.next();
            // }
            // driver.switchTo().window(subWindow); // switch to popup window

            // Screenshot
            testContext.doScreenshot();

            PO_Tilbagesend.btn_Soeg_FordringsID.clickElementAndSwitchToNewWindow();

            TO_Tools.sleep(10000);

            PO_Navigation.lnk_FordringsID(fordringsID);

        } catch (Exception e) {
            System.out.println("Ingen fordring!");

        }
    }

    /**
     * Funktion vaelger oeverste fordring
     * 
     * @param searchButton
     *            = element der trykkes paa for at aabne nyt vindue og finde
     *            fordring
     */
    public static void VaelgFordringOEverst(TestContext testContext, WebElement searchButton) {
        PatientWebDriver driver = testContext.getDriver();

        try {
            // Gem oprindeligt vindue
            String parentWindow = driver.getWindowHandle();

            // Klik paa soegeikonet ud for Fordring - AABNER NYT VINDUE
            searchButton.click();

            // Vent
            TO_Tools.sleep(3000);

            // Skift til nyt vindue
            String subWindow = null;
            Set<String> handles = driver.getWindowHandles(); // get all window
            // handles
            Iterator<String> iterator = handles.iterator();
            while (iterator.hasNext()) {
                subWindow = iterator.next();
            }
            driver.switchTo().window(subWindow); // switch to popup window

            // Screenshot
            testContext.doScreenshot();

            // Lav liste over fordringer (hvis kun �n fordring kan dette ikke
            // naas at ses, men det fejler ikke)
            List<WebElement> fordringer = PO_Navigation.lnk_Fordring(driver);

            // Vaelg den oeverste
            fordringer.get(0).click();

            // Vent
            TO_Tools.sleep(2500);

            // Gaa tilbage til hovedvindue
            driver.switchTo().window(parentWindow);
        } catch (Exception e) {
            System.out.println("Ingen fordring!");

        }
    }

    /**
     * Funktion vaelger hvilken sag der skal vaelges.
     * 
     * @param searchButton
     *            = element der trykkes paa for at aabne nyt vindue og finde sag
     * @param sagsID
     *            = Sags ID generet af inddrivelsesskridt (paakrav,
     *            afdragsordning mm.). Kan retuneres direkte af OpretPaakrav
     *            eller OpretAfdragsordning
     */
    public static void VaelgSag(TestContext testContext, WebElement searchButton, String sagsID) {
        PatientWebDriver driver = testContext.getDriver();

        try {
            // Gem oprindeligt vindue
            String parentWindow = driver.getWindowHandle();

            // Klik paa soegeikonet ud for Fordring - AABNER NYT VINDUE
            searchButton.click();

            // Vent
            TO_Tools.sleep(4000);

            // Skift til nyt vindue
            String subWindow = null;
            Set<String> handles = driver.getWindowHandles(); // get all window
            // handles
            Iterator<String> iterator = handles.iterator();
            while (iterator.hasNext()) {
                subWindow = iterator.next();
            }
            driver.switchTo().window(subWindow); // switch to popup window

            // Screenshot
            testContext.doScreenshot();

            // Lav liste over sager (hvis kun �n sag kan dette ikke naas at ses,
            // men det fejler ikke)
            List<WebElement> sager = PO_Navigation.lnk_Fordring(driver);

            if (sager.size() == 0 || sager.size() == 1)
                System.out.println("Én sag!");
            else {
                String text = null;
                for (int i = 1; i <= sager.size(); i++) {
                    text = PO_Navigation.txt_Sagstekst(i).getText();
                    if (text.contains(sagsID)) {
                        sager.get(i - 1).click();
                        break;
                    }
                }
            }

            // Vent
            TO_Tools.sleep(3000);

            // Gaa tilbage til hovedvindue
            driver.switchTo().window(parentWindow);
        } catch (Exception e) {
            System.out.println("Ingen fordring!");

        }
    }

    /**
     * Funktion omdanner beloeb "xx,xx kr" (string) til "xx,xx" (double)
     * 
     * @param sBeloeb
     *            = beloebbet der oenskes korrigeret
     * @return = beloebet som double
     */
    public static double faaBeloebDkk(String sBeloeb) {
        // fjern white space
        sBeloeb = sBeloeb.trim();

        // hvis "kr." er medtaget, saa fjern det
        if (sBeloeb.contains("kr")) {
            sBeloeb = sBeloeb.substring(0, sBeloeb.indexOf("kr"));
        }

        sBeloeb = sBeloeb.replace(".", ""); // fjern formatteringspunktum
        sBeloeb = sBeloeb.replace(",", "."); // decimal komma
        return Double.parseDouble(sBeloeb);
    }

    /**
     * Funktion formatterer en string, således at beløbet ændres til at
     * indeholde punktummer de rigtige steder, fx fra "1000" til "1.000"
     * 
     * @param string
     *            = Indsæt string der udgør et beløb
     * @return = returnerer string med punktummer indsat de rigtige steder
     */
    public static String formatBeloeb(String string) {
        // Opdel string ved komma hvis relevant og gem heltallet
        String[] split = string.split(",");
        String beforeComma = split[0];

        // Identificer det nødvendige antal punktummer og hvor det første skal
        // være
        int antalpunktummer = (beforeComma.length() - 1) / 3;

        if (antalpunktummer == 0)
            return string;

        int startposition = beforeComma.length() - antalpunktummer * 3;

        // Indsæt punktummer
        StringBuilder newString = new StringBuilder(string);
        for (int i = startposition; i <= beforeComma.length(); i = i + 4) {
            if (i != 0)
                newString.insert(i, ".");
            else
                i--;
        }

        // Omdan Stringbuilder til string og returner den nye string
        String finalString = newString.toString();
        return finalString;
    }

    public static void ventTilFeltIndeholderTekst(WebElement felt, String tekst) {
        int maxAttempts = 100;
        int attempts = 1;

        while (attempts < maxAttempts) {
            if (felt.getText().contains(tekst)) {
                TO_Tools.sleep(500);
                break;
            }
            TO_Tools.sleep(100);
            attempts++;
        }
    }

    @Deprecated
    public static String klikOgSkiftTilNytVindue(TestContext testContext, WebElement element) {
        PatientWebDriver driver = testContext.getDriver();

        // Gem oprindeligt vindue
        String parentWindow = driver.getWindowHandle();
        int numWinHandles = driver.getWindowHandles().size();

        // Klik på element der åbner nyt vindue
        element.click();

        // Vent på at nyt vindue er åbnet
        int maxAttempts = 100;
        int attempts = 1;
        while (attempts <= maxAttempts) {
            int newNumWinHandles = driver.getWindowHandles().size();
            if (newNumWinHandles > numWinHandles) {
                TO_Tools.sleep(500);
                break;
            } else {
                TO_Tools.sleep(100);
                attempts++;
            }
        }

        String subWindow1 = null;
        Set<String> handles = driver.getWindowHandles(); // get all window
        // handles
        Iterator<String> iterator = handles.iterator();

        while (iterator.hasNext()) {
            subWindow1 = iterator.next();
        }
        driver.switchTo().window(subWindow1); // switch to popup window

        // Vent på at ny side indlæses
        testContext.waitForPageToBeLoaded();
        // TO_Tools.sleep(1000);

        Assert.assertFalse(parentWindow.equals(subWindow1), "Parent og subwindow er ens");

        // Returner oprindeligt vindue, så det senere kan kaldes
        return parentWindow;
    }

}
