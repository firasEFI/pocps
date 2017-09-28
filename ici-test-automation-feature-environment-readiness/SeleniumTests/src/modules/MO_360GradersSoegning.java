package modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.taxobjects.Fordring;
import icisel.testng.SmartAssert;
import icisel.testng.TestContext;
import modules.types.KontoTransaktionsType;
import pageobjects.generic.PO_Slaa_Kunde_Op;

public class MO_360GradersSoegning {
    /**
     * IU-213 - Fremsøg skyldner fra 360-graders søgning via CPR-nr. - Partsoplysninger
     * 
     * Funktion fremsøger skyldner via 360 graders søgning på baggrund af dennes CPR-nummer og går 
     * til skyldnerens 360 graders overblik, på Partsoplysninger fanen
     * 
     * Entry: Siden er loaded og topmenuen er tilgængelig
     * Exit: Skyldners 360 graders overblik på fanen Partsoplysninger
     * 
     * @param testContext = testContext
     * @param sKundeNummer = Skyldners CPR-nummer
     * @throws InterruptedException
     */
    public static void soegKundeViaCPR(TestContext testContext, String sKundeNummer) {
        System.out.println("Fremsoeger kunde via kundens ID...");

        // Gaa til 360 graders soegning
        goToSoegefunktion(testContext);

        // Indtast kundens ID
        indtastCPR(sKundeNummer);

        // Soeg kunde
        klikSoeg();

        // Vaelg kunde
        vaelgKunde(testContext);

        System.out.println("Kunde fundet og fremsoegt");
    }

    public static List<String> soegKundeViaCprOgMapFordringsIDs(TestContext testContext, String sKundeNummer,
            List<Fordring> fordringer) {
        
        List<String> results = new ArrayList<>();
        HashMap<String, String> fordringsMap = new HashMap<>();
        
        System.out.println("Fremsoeger kunde via cpr: " + sKundeNummer);
        soegKunde(testContext, sKundeNummer);

        // Vaelg kunde konto
        Boolean kundeKontoFundet = vaelgKonto(testContext);
        if (kundeKontoFundet) {
            fordringsMap = MO_Navigation360GradersOverblik.getAktiveFordringer(
                    MO_Navigation360GradersOverblik.getTransaktionerFraKonto(testContext, KontoTransaktionsType.OPRETTELSE));
        }
        

        Boolean refreshFordringsMapNeeded = false;
        for (Fordring fordring : fordringer) {
            Boolean foundMatchingFordring = false;
            for (Iterator<Map.Entry<String, String>> it = fordringsMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = it.next();
                if (entry.getValue().equals(fordring.getsBeloeb())) {
                    System.out.println("Fandt matchende fordring på beløb: "+ fordring.getsBeloeb());
                    results.add(entry.getKey());
                    it.remove();
                    foundMatchingFordring = true;
                    break;
                }
            }
            if (!foundMatchingFordring) {
                System.out.println("Mangler fordring på beløb: "+ fordring.getsBeloeb());
                System.out.println("Opretter fordring");
                MO_Fordring.opretFordring(testContext, fordring);
                refreshFordringsMapNeeded = true;
            }
        }
        
        // søg kunde konto igen (hvis der blev oprettet fordringer) for at finde alle fordrings id'er
        if (refreshFordringsMapNeeded) {
            soegKunde(testContext, sKundeNummer);
            vaelgKonto(testContext);
            fordringsMap = MO_Navigation360GradersOverblik.getAktiveFordringer(
                    MO_Navigation360GradersOverblik.getTransaktionerFraKonto(testContext, KontoTransaktionsType.OPRETTELSE));
        } 
        
        // opret listen med resultater
        for (Fordring fordring : fordringer) {
            for (Iterator<Map.Entry<String, String>> it = fordringsMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = it.next();
                if (entry.getValue().equals(fordring.getsBeloeb())) {
                    results.add(entry.getKey());
                    it.remove();
                    break;
                }
            }
        }

        System.out.println("Kunde konto og fordringer klar");

        return results;
    }
    
    private static void soegKunde(TestContext testContext, String kundeNummer) {
        // Gaa til 360 graders soegning igen
        goToSoegefunktion(testContext);

        // Indtast kundens ID
        indtastCPR(kundeNummer);

        // Soeg kunde
        klikSoeg();
    }

    /**
     * Funktion fremsoeger en kunde via 360 graders soegning i topmenuen ved
     * hjaelp af kundens navn. Kan goeres uden tidligere steps. Soeger paa navn
     * i stedet for CPR.
     * 
     * @param driver
     *            = driver
     * @param sFuldeNavn
     *            = Hentes fra excelark.
     * @throws InterruptedException
     */
    public static void soegKundeViaFuldeNavn(TestContext testContext, String sFuldeNavn) throws InterruptedException {
        soegKundeViaFuldeNavn(testContext, sFuldeNavn, null);
    }

    /**
     * Funktion fremsoeger en kunde via 360 graders soegning i topmenuen ved
     * hjaelp af kundens navn. Kan goeres uden tidligere steps. Soeger paa navn
     * i stedet for CPR.
     * 
     * @param driver
     *            = driver
     * @param sFuldeNavn
     *            = Hentes fra excelark.
     * @param sSoegetype
     *            valgfrit argument, som specificerer evt. valg af soegetype
     * @throws InterruptedException
     */
    public static void soegKundeViaFuldeNavn(TestContext testContext, String sFuldeNavn, String sSoegetype)
            throws InterruptedException {
        System.out.println("Fremsoeger kunde via kundens fulde navn...");

        // Gaa til 360 graders soegning
        goToSoegefunktion(testContext);

        // Indtast kundens fulde navn
        indtastFuldeNavn(sFuldeNavn, sSoegetype);

        // Soeg kunde
        klikSoeg();

        // Vaelg kunde
        vaelgKunde(testContext);

        System.out.println("Kunde fundet og fremsoegt");
    }

    /**
     * Funktion gaar til 360 graders soegning
     * 
     * @param driver
     *            = driver
     */
    public static void goToSoegefunktion(TestContext testContext) {
        // Gaa til 360 graders soegning
        MenuNavigator.menu().a360GradersSoegning();

        testContext.waitForPageToBeLoaded();
    }

    public static void indtastCPR(String sKundeNummer) {
        // Indsaet kundenummer (ID nummer)
        PO_Slaa_Kunde_Op.input_IdNummer.sendKeys(sKundeNummer);

        // Vaelg ID type
        PO_Slaa_Kunde_Op.drp_IdType.pick("CPR");
    }

    public static void indtastFuldeNavn(String sFuldeNavn, String sSoegetype) {
        // Indsaet Fulde navn
        PO_Slaa_Kunde_Op.input_FuldeNavn.sendKeys(sFuldeNavn);

        if (sSoegetype != null) {
            // Map alle mulighder
            HashMap<String, String> soegeType_til_value_map = new HashMap<String, String>();
            soegeType_til_value_map.put("Fuzzy", "C1FZ");
            soegeType_til_value_map.put("Standard", "C1ST");
            soegeType_til_value_map.put("", "");

            PO_Slaa_Kunde_Op.drp_Soegetype.pick(soegeType_til_value_map.get(sSoegetype));
        }
    }

    public static void klikSoeg() {
        // Tryk paa "Søg"
        PO_Slaa_Kunde_Op.btn_Soeg.click();
    }

    private static void vaelgKunde(TestContext testContext) {
        try {
            // Vent paa at skyldner kommer frem
            PO_Slaa_Kunde_Op.lnk_Kunde.waitFor();

            // Screenshot
            testContext.doScreenshot();

            // Klik paa kunden der kommer frem
            PO_Slaa_Kunde_Op.lnk_Kunde.click();

        } catch (Exception e) {
            SmartAssert.fail(testContext, String.format("Kunne ikke finde skyldner"));
        }

        // Vent til at dashboard er loaded
        MO_Wait.waitForRightMenu();
        // Screenshot
        testContext.doScreenshot();
    }

    public static Boolean vaelgKonto(TestContext testContext) {
        Boolean skyldnerFundet = false;
        try {
            // Vent paa at skyldner kommer frem
            // PO_Slaa_Kunde_Op.lnk_Konto.waitFor();

            // Screenshot
            testContext.doScreenshot();

            // Klik paa kunden der kommer frem
            testContext.getDriver().findElementPatiently(PO_Slaa_Kunde_Op.lnk_Konto, 5000, 50).click();

            skyldnerFundet = true;
        } catch (Exception e) {
            System.out.println("Kunne ikke finde skyldner");
        }

        // Vent til at dashboard er loaded
        MO_Wait.waitForRightMenu();
        // Screenshot
        testContext.doScreenshot();

        return skyldnerFundet;
    }
}
