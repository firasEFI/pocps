package utils.retursvar;

public enum RetursvarType {
    KVITTERING,
    RETURSVAR2_ACCEPT,
    RETURSVAR2_REJECT,
    RETURSVAR5,
    RETURSVAR7,
    RETURSVAR8,
    RETURSVAR9;
    public String fileTypeTextForInput() {
        //For matching up the Enum with the dropdown in web version of Integration_file
        switch (this) {
        case KVITTERING:
            return "NEMKONTO_RECEIPT";
        case RETURSVAR2_ACCEPT:
            return "NEMKONTO_RECEIPT";
        case RETURSVAR2_REJECT:
            return "NEMKONTO_RECEIPT";
        case RETURSVAR5:
            return "NEMKONTO_RECEIPT";
        case RETURSVAR7:
            return "NEMKONTO_RECEIPT";
        case RETURSVAR8:
            return "NEMKONTO_RECEIPT";
        case RETURSVAR9:
            return "NEMKONTO_RECEIPT";
        default:
            return null;
        }
    }
}
