package utils.betaling;

public enum BetalingsfilType {
    CREMUL_STANDARD,
    CREMUL_INTERNATIONAL,
    CREMUL_DAEKNINGSLOES,
    CREMUL_UDEN_OCR_ELLER_TREDJEMANDS_BETALING,

    FINSTA_SKB,
    FINSTA_NETS,
    FINSTA_DAEKNINGSLOES,
    FINSTA_DEBMUL,
    FINSTA_KUB,

    M602_BETALINGSSERVICE,
    M602_REJECTED_BETALINGSSERVICE,
    M602_CANCELLED_BETALINGSSERVICE,
    M602_TILBGAEFOERT_BETALING_FRA_BETALINGSSERVICE,
    M602_COMPLETED_PAYMENTS_MADE_BY_INDBETALINGSKORT,
    M602_TILBAGEFOERT_BETALING_FRA_INDBETALINGSKORT,
    
    DEBMUL_STANDARD;

    /**
     * @return the visible text corresponding to the Dropdown "File type" on
     *         https://5.44.137.168:451/test/batchfiles/newFile.jsp
     */
    public String fileTypeTextForInput() {
        switch (getOvertype()) {
        case CREMUL:
            return "SKB_CREMUL";
        case FINSTA:
            return "SKB_FINSTA";
        case M602:
            return "NETS_602";
        case DEBMUL:
            return "SKB_DEBMUL";
        default:
            return null;
        }

    }

    public String transactionCode() {
        if (!getOvertype().equals(BetalingsfilOvertype.M602)) {
            throw new RuntimeException("This indbetalingsfiltype does not have a transaction code.");
        }
        switch (this) {
        case M602_BETALINGSSERVICE:
            return "0236";
        case M602_REJECTED_BETALINGSSERVICE:
            return "0237";
        case M602_CANCELLED_BETALINGSSERVICE:
            return "0238";
        case M602_TILBGAEFOERT_BETALING_FRA_BETALINGSSERVICE:
            return "0239";
        case M602_COMPLETED_PAYMENTS_MADE_BY_INDBETALINGSKORT:
            return "0297";
        case M602_TILBAGEFOERT_BETALING_FRA_INDBETALINGSKORT:
            return "0299";
        default:
            return null;
        }
    }

    public BetalingsfilOvertype getOvertype() {
        switch (this) {
        case CREMUL_STANDARD:
        case CREMUL_INTERNATIONAL:
        case CREMUL_DAEKNINGSLOES:
        case CREMUL_UDEN_OCR_ELLER_TREDJEMANDS_BETALING:
            return BetalingsfilOvertype.CREMUL;
        case FINSTA_SKB:
        case FINSTA_NETS:
        case FINSTA_DAEKNINGSLOES:
        case FINSTA_DEBMUL:
        case FINSTA_KUB:
            return BetalingsfilOvertype.FINSTA;
        case M602_BETALINGSSERVICE:
        case M602_REJECTED_BETALINGSSERVICE:
        case M602_CANCELLED_BETALINGSSERVICE:
        case M602_TILBGAEFOERT_BETALING_FRA_BETALINGSSERVICE:
        case M602_COMPLETED_PAYMENTS_MADE_BY_INDBETALINGSKORT:
        case M602_TILBAGEFOERT_BETALING_FRA_INDBETALINGSKORT:
            return BetalingsfilOvertype.M602;
        case DEBMUL_STANDARD:
            return BetalingsfilOvertype.DEBMUL;
        default:
            return null;
        }

    }
}
