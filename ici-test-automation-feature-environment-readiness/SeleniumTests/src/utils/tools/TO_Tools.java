package utils.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import icisel.utils.TO_Constant;
import icisel.utils.driver.Engine;

public class TO_Tools {
    /**
     * Default vaerdier til taalmodig ventetid
     * 
     * @see TO_Tools#findPatiently(WebDriver, By, long, long)
     */
    public static final long DEFAULT_PATIENT_TIMEOUT = 15000; // 15 s
    public static final long DEFAULT_PATIENT_POLL_PERIOD = 200; // 200 ms

    // Dato funktion format DDMMYYYY
    /**
     * Funktion returnerer en dato som string i formatet dd-MM-yyyy
     * 
     * @param i
     *            = Number of days to add to todays date
     * 
     * @return Returns todays date plus i.
     */
    public static String getDagsDatoPlus_DDMMYYYY(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, i);
        Date nextDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(nextDate);
    }

    /**
     * Funktion returnerer en given dato som String med udgangspunkt i PSRMs dato. Dette
     * kræver dog, at man giver metoden PSRM datoen, som kan findes via
     * MO_Utilities.getPsrmDateTime
     * 
     * @param psrmDato
     *            = String med datoen i psrm systemet
     * @param dageFraNu
     *            = Antal dage fra denne dato, som der ønskes en dato for
     * @return = returnerer en ny dato som String
     * @throws ParseException
     */
    public static String getPsrmDatoPlus_DDMMYYYY(String psrmDato, int dageFraNu) throws ParseException {
        // Omdan string til dato format
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse(psrmDato);

        // Tilføj dage til psrmDato
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dageFraNu);

        // Konverter dato til en String
        Date nextDate = calendar.getTime();

        return format.format(nextDate);
    }
    
    /**
     * Funktion returnerer en given dato som Calendar objekt med udgangspunkt i PSRMs dato. Dette
     * kræver dog, at man giver metoden PSRM datoen, som kan findes via
     * MO_Utilities.getPsrmDateTime
     * 
     * @param psrmDato
     *            = String med datoen i psrm systemet
     * @param dageFraNu
     *            = Antal dage fra denne dato, som der ønskes en dato for
     * @return = returnerer en ny dato som Date objekt
     * @throws ParseException
     */
    public static Calendar getPsrmDatoPlus_DDMMYYYY_Calendar(Date psrmDato, int dageFraNu) throws ParseException {
        // Tilføj dage til psrmDato
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(psrmDato);
        calendar.add(Calendar.DATE, dageFraNu);

        return calendar;
    }

    // Dato funktion format YYYYMMDD HH:mm:ss.SSS
    /**
     * Funktionen returnerer en datoTid i formatet yyyy-MM-dd'T'HH:mm:ss.SSS
     * 
     * @param driver
     *            = driver to be used
     * @param i
     *            = Number of days to add to todays date
     * @return Returns todays date plus i.
     */
    public static String getDagsDatoPlus_YYYYMMDD_Tid(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, i);
        Date nextDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return dateFormat.format(nextDate);
    }

    // Sleep funktion
    /**
     * Funktion lader koden vente i specificeret tid. En faktor styrer fra
     * TO_Constant samtlige sleep timere.
     * 
     * @param i
     *            = Milliseconds to sleep/wait
     */
    public static void sleep(int i) {
        try {
            int timeToSleep = (int) (i * TO_Constant.faktor);
            Thread.sleep(timeToSleep);
            // TimeUnit.MILLISECONDS.sleep(i*TO_Constant.faktor);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    // Generer Random number med antal karakterer som parameter
    /**
     * Funktion returnerer et tilfaeldigt nummer af en specificeret laengde
     * mellem 1 og 18
     * 
     * @param len
     *            = Length of random number (between 1 and 18)
     * @return Returns a random number as a string
     */
    public final static String RandomNumber(long len) {
        if (len > 18) throw new IllegalStateException("Too many digits");
        long tLen = (long) Math.pow(10, len - 1) * 9;

        long number = (long) (Math.random() * tLen) + (long) Math.pow(10, len - 1) * 1;

        String tVal = number + "";
        if (tVal.length() != len) {
            throw new IllegalStateException("The random number '" + tVal + "' is not '" + len + "' digits");
        }
        return tVal;
    }

    /**
     * Funktion angiver tidsforskel mellem starttid og sluttid i sekunder
     * 
     * @param startTime
     *            = tid i millisekunder
     * @param endTime
     *            = tid i millisekunder
     * @return = returnerer tidsforskel i sekunder
     */
    public final static String TimeDifferenceSeconds(long startTime, long endTime) {
        // Initier string
        String timeDiff = null;

        // Beregn tidsforskel
        long diff = endTime - startTime;

        // Lav tallet om til en double, saa et decimaltal kan opnaas
        double diffDecimal = (double) diff;

        // Omregn tidsforskel fra millisekunder til sekunder
        diffDecimal = diffDecimal / 1000;

        // Konverter til string
        timeDiff = Double.toString(diffDecimal);

        // Returner tidsforskel
        return timeDiff;
    }

    /**
     * Funktion printer tekst til en output fil
     * 
     * @param path
     *            = sti til outputfil
     * @param text
     *            = String der skal printes i filen
     */
    public static void PrintToFile(String path, String text) {
        File file = new File(path);
        FileWriter writer;

        try {
            writer = new FileWriter(file, true);
            PrintWriter printer = new PrintWriter(writer);
            printer.append("\n" + text);

            writer.close();
        } catch (IOException e) {
            // Something went wrong
        }
    }

    /**
     * Skifter til nyeste vindue.
     * 
     * Credit for kode til Stackoverflow spoergsmaal 9588827, svar af Surya.
     */
    public static void skiftTilNyaabnetVindue() {
        WebDriver driver = Engine.getDriver();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public static void zoomOut() {
        for (int i = 0; i < 3; i++) {
            Engine.getDriver().findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        }
    }

    public static String randomNDigitNumber(int n) {
        char[] id = new char[n];
        Random rand = new Random();

        for (int i = 0; i < id.length; i++) {
            id[i] = Character.forDigit(rand.nextInt(10), 10);
        }

        return String.valueOf(id);
    }

    /**
     * Trykker paa en tast paa tastaturet.
     * 
     * @param key
     *            den knap, der skal trykkes paa
     */
    public static void trykKnap(Keys key) {
        Actions builder = new Actions(Engine.getDriver());
        builder.sendKeys(key).perform();
    }
    
    public static String parseDomain(String psrmUrl) {
        // find third "/"
        int numForwardslash = 0;
        int i;
        for (i = 0; i < psrmUrl.length(); i++) {
            if (psrmUrl.charAt(i) == '/') {
                numForwardslash++;
            }
            if (numForwardslash == 3) {
                break;
            }
        }
        return psrmUrl.substring(0, i);
    }
}
