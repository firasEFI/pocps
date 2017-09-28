package findus_pageobjects.opgave;

import findus_pageobjects.DropdownOption;

public class OpgaveFremrykParm {
    //Variables
    private String bruger;
    private String rolle;
    private String detaljer;
    private TildelType tildelType;

    //Methods
    public enum TildelType implements DropdownOption {
        BRUGER("SNDU", "Bruger"),
        ROLLE("SNDR", "Rolle");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        TildelType(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    //Get/set
    public TildelType getSendTil() {
        return tildelType;
    }

    public String getBruger() {
        return bruger;
    }

    public String getRolle() {
        return rolle;
    }

    public String getDetaljer() {
        return detaljer;
    }

    public void setSendTil(TildelType tildelType) {
        this.tildelType = tildelType;
    }

    public void setBruger(String bruger) {
        this.bruger = bruger;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public void setDetaljer(String detaljer) {
        this.detaljer = detaljer;
    }
}
