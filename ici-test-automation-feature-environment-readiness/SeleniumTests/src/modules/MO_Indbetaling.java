package modules;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import icisel.testng.PropertyProvider;
import utils.batchJob.BatchJobRunner;
import utils.betaling.Betaling;
import utils.betaling.BetalingsManager;
import utils.betaling.BetalingsfilType;

public class MO_Indbetaling {

    /**
     * Funktion opretter en betaling, som siden sendes afsted og køres gennem
     * systemet via diverse batchjobs.
     * 
     * Entry: Foregår på en ny side, så den kan startes fra alle steder i
     * løsningen Exit: Der skal logges ind i PSRM forfra
     * 
     * @param BetalingsfilType
     *            = BetalingsfilType.[type] - vælg den type betaling der ønskes
     * @param beloebDkk
     *            = Indbetalingsbeløb som double
     * @param datoForIndbetaling
     *            = Dato med dagen hvor indbetalingen ønskes ajourført
     * @param ocrLinje
     *            = Ocr-linje som indbetalingen skal ramme ind på, som String.
     *            For Finsta-filer, kan denne værdi være null.
     * @param kontonummer
     *            = Kontonummer som indbetalingen skal ramme ind på, som String
     * @param testContext
     *            = testContext / this
     * @param justeringsID
     *            = JusteringsID (bruges kun ved en udbetaling)
     * @throws IOException
     */

    public static void opretIndbetaling(BetalingsfilType betalingsfilType, Double beloebDkk,
            Date datoForIndbetaling, String ocrLinje, String kontonummer, PropertyProvider propertyProvider)
            throws IOException {

        // Specificer indbetalingen
        Betaling indbetaling = new Betaling(beloebDkk, datoForIndbetaling, ocrLinje, kontonummer);

        // =========== Generate Indbetalingsfil ============
        standardOpretProcedure(indbetaling, betalingsfilType, propertyProvider);

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
     *            = Indbetalingsbeløb som double
     * @param datoForIndbetaling
     *            = Dato med dagen hvor indbetalingen ønskes ajourført
     * @param ocrLinje
     *            = Ocr-linje som indbetalingen skal ramme ind på, som String.
     *            For Finsta-filer, kan denne værdi være null.
     * @param unikID
     *            = Unikt ID for indbetalingen, angivet som String
     * @param kontonummer
     *            = Kontonummer som indbetalingen skal ramme ind på, som String
     * @param adviseringstekst
     *            = Adviseringstekst som String, fx "IK71 0000" + OCR linje
     * @param testContext
     *            = testContext / this
     * @param justeringsID
     *            = JusteringsID (bruges kun ved en udbetaling)
     * @throws IOException
     */
    public static void opretIndbetaling(BetalingsfilType betalingsfilType, String brugerinitialer, Double beloebDkk,
            Date datoForIndbetaling, String ocrLinje, String unikID, String kontonummer, String adviseringstekst,
            PropertyProvider propertyProvider) throws IOException {

        // Specificer indbetalingen
        Betaling indbetaling = new Betaling(brugerinitialer, beloebDkk, datoForIndbetaling, ocrLinje, unikID,
                kontonummer, adviseringstekst);

        // =========== Generate Indbetalingsfil ============
        standardOpretProcedure(indbetaling, betalingsfilType, propertyProvider);
    }

    /**
     * Funktion opretter en betaling, som siden sendes afsted og køres gennem
     * systemet via diverse batchjobs.
     * 
     * Entry: Foregår på en ny side, så den kan startes fra alle steder i
     * løsningen Exit: Der skal logges ind i PSRM forfra
     * 
     * @param BetalingsfilType
     *            = BetalingsfilType.[type] - vælg den type betaling der ønskes
     * @param beloebDkk
     *            = Indbetalingsbeløb som double
     * @param datoForIndbetaling
     *            = Dato med dagen hvor indbetalingen ønskes ajourført
     * @param ocrLinje
     *            = Ocr-linje som indbetalingen skal ramme ind på, som String.
     *            For Finsta-filer, kan denne værdi være null.
     * @param kontonummer
     *            = Kontonummer som indbetalingen skal ramme ind på, som String
     * @param propertyProvider
     *            = testContext / this
     * @throws IOException
     */
    public static void opretIndbetaling(BetalingsfilType betalingsfilType, Double beloebDkk,
            Date datoForIndbetaling, String ocrLinje, PropertyProvider propertyProvider)
            throws IOException {

        // Specificer indbetalingen
        Betaling indbetaling = new Betaling(beloebDkk, datoForIndbetaling, ocrLinje, betalingsfilType);

        // =========== Generate Indbetalingsfil ============
        standardOpretProcedure(indbetaling, betalingsfilType, propertyProvider);

    }

    private static void standardOpretProcedure(Betaling indbetaling, BetalingsfilType betalingsfilType,
            PropertyProvider propertyProvider) throws IOException {

        // =========== Generate Indbetalingsfil ============
        BetalingsManager manager = new BetalingsManager(propertyProvider);
        File file = manager.opretBetalingsfil(indbetaling, betalingsfilType);

        // Submit Indbetalingsfil
        manager.indsendBetalingsfil(file, betalingsfilType);

        // Run Batchjob
        BatchJobRunner batch = new BatchJobRunner(propertyProvider);
        batch.runBetalingsBatchJob(betalingsfilType.getOvertype());

    }

}
