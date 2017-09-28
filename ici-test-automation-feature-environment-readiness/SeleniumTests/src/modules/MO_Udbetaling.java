package modules;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import icisel.testng.TestContext;
import utils.batchJob.BatchJobRunner;
import utils.betaling.Betaling;
import utils.betaling.BetalingsManager;
import utils.betaling.BetalingsfilType;

public class MO_Udbetaling {

    /**
     * Funktion opretter en betaling, som siden sendes afsted og køres gennem
     * systemet via diverse batchjobs.
     * 
     * Entry: Foregår på en ny side, så den kan startes fra alle steder i
     * løsningen Exit: Der skal logges ind i PSRM forfra
     * 
     * @param betalingsfilType
     *            = BetalingsfilType.[type] - vælg den type betaling der ønskes
     * @param beloebDkk
     *            = udbetalingsbeløb som double
     * @param datoForudbetaling
     *            = Dato med dagen hvor udbetalingen ønskes ajourført
     * @param ocrLinje
     *            = Ocr-linje som udbetalingen skal ramme ind på, som String
     * @param kontonummer
     *            = Kontonummer som udbetalingen skal ramme ind på, som String
     * @param testContext
     *            = testContext / this
     * @param justeringsID
     *            = JusteringsID (bruges kun ved en udbetaling)
     * @throws IOException
     */

    public static void opretUdbetaling(BetalingsfilType betalingsfilType, Double beloebDkk,
            Date datoForudbetaling, String ocrLinje, String kontonummer, String justeringsID, TestContext testContext)
            throws IOException {

        // Specificer udbetalingen
        Betaling udbetaling = new Betaling(beloebDkk, datoForudbetaling, ocrLinje, kontonummer, justeringsID);

        // =========== Generate udbetalingsfil ============
        standardOpretProcedure(udbetaling, betalingsfilType, testContext);
    }

    /**
     * Funktion opretter en betaling, som siden sendes afsted og køres gennem
     * systemet via diverse batchjobs.
     * 
     * Entry: Foregår på en ny side, så den kan startes fra alle steder i
     * løsningen Exit: Der skal logges ind i PSRM forfra
     * 
     * @param betalingsfilType
     *            = BetalingsfilType.[type] - vælg den type betaling der ønskes
     * @param brugerinitialer
     *            = Brugerens initialer angivet som String
     * @param beloebDkk
     *            = udbetalingsbeløb som double
     * @param datoForudbetaling
     *            = Dato med dagen hvor udbetalingen ønskes ajourført
     * @param ocrLinje
     *            = Ocr-linje som udbetalingen skal ramme ind på, som String
     * @param unikID
     *            = Unikt ID for udbetalingen, angivet som String
     * @param kontonummer
     *            = Kontonummer som udbetalingen skal ramme ind på, som String
     * @param adviseringstekst
     *            = Adviseringstekst som String, fx "IK71 0000" + OCR linje
     * @param testContext
     *            = testContext / this
     * @param justeringsID
     *            = JusteringsID (bruges kun ved en udbetaling)
     * @throws IOException
     */
    public static void opretUdbetaling(BetalingsfilType betalingsfilType, String brugerinitialer, Double beloebDkk,
            Date datoForudbetaling, String ocrLinje, String unikID, String kontonummer, String adviseringstekst,
            String justeringsID,
            TestContext testContext) throws IOException {

        // Specificer udbetalingen
        Betaling udbetaling = new Betaling(brugerinitialer, beloebDkk, datoForudbetaling, ocrLinje, unikID,
                kontonummer, adviseringstekst, justeringsID);

        // =========== Generate Udbetalingsfil ============
        standardOpretProcedure(udbetaling, betalingsfilType, testContext);
    }

    /**
     * Funktion opretter en betaling, som siden sendes afsted og køres gennem
     * systemet via diverse batchjobs.
     * 
     * Entry: Foregår på en ny side, så den kan startes fra alle steder i
     * løsningen Exit: Der skal logges ind i PSRM forfra
     * 
     * @param betalingsfilType
     *            = BetalingsfilType.[type] - vælg den type betaling der ønskes
     * @param beloebDkk
     *            = udbetalingsbeløb som double
     * @param datoForudbetaling
     *            = Dato med dagen hvor udbetalingen ønskes ajourført
     * @param ocrLinje
     *            = Ocr-linje som udbetalingen skal ramme ind på, som String
     * @param kontonummer
     *            = Kontonummer som udbetalingen skal ramme ind på, som String
     * @param testContext
     *            = testContext / this
     * @throws IOException
     */
    public static void opretUdbetaling(BetalingsfilType betalingsfilType, Double beloebDkk,
            Date datoForudbetaling, String ocrLinje, String justeringsID, TestContext testContext)
            throws IOException {

        // Specificer udbetalingen
        Betaling udbetaling = new Betaling(beloebDkk, datoForudbetaling, ocrLinje, betalingsfilType, justeringsID);
     // =========== Generate Udbetalingsfil ============
        standardOpretProcedure(udbetaling, betalingsfilType, testContext);

    }
    private static void standardOpretProcedure(Betaling udbetaling, BetalingsfilType betalingsfilType,
            TestContext testContext) throws IOException {

        // =========== Generate udbetalingsfil ============
        BetalingsManager manager = new BetalingsManager(testContext.getPropertyProvider());
        File file = manager.opretBetalingsfil(udbetaling, betalingsfilType);

        // Submit udbetalingsfil
        manager.indsendBetalingsfil(file, betalingsfilType);

        // Run Batchjob

        BatchJobRunner batch = new BatchJobRunner(testContext.getPropertyProvider());
        batch.runBetalingsBatchJob(betalingsfilType.getOvertype());
    }

}
