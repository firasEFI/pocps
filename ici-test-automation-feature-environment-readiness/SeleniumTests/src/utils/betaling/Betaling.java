package utils.betaling;

import java.util.Date;
import java.util.InputMismatchException;

import utils.tools.Etc;

public class Betaling {
    private static final int OCR_LENGTH = 15;

    private String brugerInitialer;

    private double beloebIDkk;

    private Date dato;

    private String ocr;

    private String uniqueId;

    private String kontonummer;

    private String justeringsID;

    public String getJusteringsID() {
        return justeringsID;
    }

    public void setJusteringsID(String justeringsID) {
        this.justeringsID = justeringsID;
    }

    /**
     * Optional field
     */
    private String adviseringstekst;

    public String getAdviseringstekst() {
        return adviseringstekst;
    }

    public void setAdviseringstekst(String adviseringstekst) {
        this.adviseringstekst = adviseringstekst;
    }

    /**
     * Full constructor for indbetaling
     * 
     * @param brugerInitialer
     *            is not necessary, is not used currently
     * @param beloebIDkk
     * @param dato
     * @param ocr
     * @param uniqueId
     * @param kontonummer
     * @param adviseringstekst
     * @param justeringsID
     *            is only used with Debmul files. With non-debmul file leave as
     *            null
     */
    public Betaling(String brugerInitialer, double beloebIDkk, Date dato, String ocr, String uniqueId,
            String kontonummer, String adviseringstekst, String justeringsID) {
        super();
        this.brugerInitialer = brugerInitialer;
        this.beloebIDkk = beloebIDkk;
        this.dato = dato;
        setOcr(ocr);
        this.uniqueId = uniqueId;
        this.kontonummer = kontonummer;
        this.adviseringstekst = adviseringstekst;
        this.justeringsID = justeringsID;
    }

    /**
     * Full constructor for udbetaling
     * 
     * @param brugerInitialer
     *            is not necessary, is not used currently
     * @param beloebIDkk
     * @param dato
     * @param ocr
     * @param uniqueId
     * @param kontonummer
     * @param adviseringstekst
     * @param justeringsID
     *            is only used with Debmul files. With non-debmul file leave as
     *            null
     */
    public Betaling(String brugerInitialer, double beloebIDkk, Date dato, String ocr, String uniqueId,
            String kontonummer, String adviseringstekst) {
        this.brugerInitialer = brugerInitialer;
        this.beloebIDkk = beloebIDkk;
        this.dato = dato;
        setOcr(ocr);
        this.uniqueId = uniqueId;
        this.kontonummer = kontonummer;
        this.adviseringstekst = adviseringstekst;
        this.justeringsID = null;
    }

    /**
     * This is the medium-level constructor that can be used for udbetalinger
     * Constructor with default value of adviseringstekst: {@code null} and
     * brugerInitialer: {@code null} and uniqueId: {@code null}
     * 
     * @param beloebIDkk
     * @param dato
     * @param ocr
     * @param kontonummer
     * @param justeringsID
     */

    public Betaling(double beloebIDkk, Date dato, String ocr, String kontonummer, String justeringsID) {
        this(null, beloebIDkk, dato, ocr, null, kontonummer, null, justeringsID);
    }

    /**
     * This is the medium-level constructor that can be used for indbetalinger
     * Constructor with default value of adviseringstekst: {@code null} and
     * brugerInitialer: {@code null} and uniqueId: {@code null}
     * 
     * @param beloebIDkk
     * @param dato
     * @param ocr
     * @param kontonummer
     */
    public Betaling(double beloebIDkk, Date dato, String ocr, String kontonummer) {
        this(null, beloebIDkk, dato, ocr, null, kontonummer, null, null);
    }

    /**
     * This is the minimum constructor for udbetalinger Uses type to define
     * account no.
     * 
     * @param beloebIDkk
     * @param dato
     * @param ocr
     * @param type
     *            will set "kontonummer" to predefined account based on
     *            Betalingsfiltype
     * @param justeringsID
     */
    public Betaling(double beloebIDkk, Date dato, String ocr, BetalingsfilType type, String justeringsID) {
        this(beloebIDkk, dato, ocr, defaultKontonummer(type), justeringsID);
    }

    /**
     * This is the minimum constructor for indbetalinger. Uses type to define
     * account no.
     * 
     * @param beloebIDkk
     * @param dato
     * @param ocr
     * @param type
     *            will set "kontonummer" to predefined account based on type
     */
    public Betaling(double beloebIDkk, Date dato, String ocr, BetalingsfilType type) {
        this(beloebIDkk, dato, ocr, defaultKontonummer(type), null);
    }

    private static String defaultKontonummer(BetalingsfilType type) {
        switch (type) {
        case CREMUL_DAEKNINGSLOES:
        case CREMUL_STANDARD:
        case CREMUL_INTERNATIONAL:
        case FINSTA_SKB:
        case FINSTA_DAEKNINGSLOES: {
            return "4069208398";
        }
        case M602_BETALINGSSERVICE:
        case M602_REJECTED_BETALINGSSERVICE:
        case M602_CANCELLED_BETALINGSSERVICE:
        case M602_TILBGAEFOERT_BETALING_FRA_BETALINGSSERVICE:
        case M602_COMPLETED_PAYMENTS_MADE_BY_INDBETALINGSKORT:
        case M602_TILBAGEFOERT_BETALING_FRA_INDBETALINGSKORT:
        case FINSTA_NETS: {
            return "4069208371";
        }
        case DEBMUL_STANDARD:
            return "4069208339";
        default:
            return null;
        }
    }

    public String getBrugerInitialer() {
        return brugerInitialer;
    }

    public void setBrugerInitialer(String brugerInitialer) {
        this.brugerInitialer = brugerInitialer;
    }

    public double getBeloebIDkk() {
        return beloebIDkk;
    }

    public void setBeloebIDkk(double beloebIDkk) {
        this.beloebIDkk = beloebIDkk;
    }

    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public String getOcr() {
        return ocr;
    }

    public void setOcr(String ocr) {
        if (!isValidOcr(ocr)) {
            int ocrLen = (ocr == null)? 0: ocr.length();
            String msg = String.format("Ugyldig OCR; længde %d. Skal være %d eller null", ocrLen, OCR_LENGTH);
            throw new InputMismatchException(msg);
        }
        this.ocr = ocr;
    }

    private boolean isValidOcr(String ocr) {
        if (ocr == null ) return true;
        int inputLen = ocr.length();
        if (inputLen == OCR_LENGTH || inputLen == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String uniqueIdRand() {
        // set random ID if not specified by user. Max char for unique ID = 7.
        return Etc.generateNDigitNumberAsString(7);
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }

}
