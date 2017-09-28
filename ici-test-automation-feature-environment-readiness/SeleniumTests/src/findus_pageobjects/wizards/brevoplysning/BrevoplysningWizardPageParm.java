package findus_pageobjects.wizards.brevoplysning;

import findus_pageobjects.DropdownOption;

public class BrevoplysningWizardPageParm {

    //Variables
    private String part;
    private BrevoplysningWizardPageParm.Inddrivelsesskridt inddrivelsesskridt;
    private BrevoplysningWizardPageParm.Kundekontakttype kundekontakttype;

    //Methods
    public enum Inddrivelsesskridt implements DropdownOption {
        DCLT("DCLT", "Afgørelsesbrev"),
        FDLT("FDLT", "Breve uden partshøring"),
        FTXT("FTXT", "Fritekstbrev"),
        BFLT("BFLT", "Orienteringsbrev"),
        RCLT("RCLT", "Partshøringsbrev"),
        DLPA("DLPA", "Påkravsbrev med partshøring"),
        RMLT("RMLT", "Rykkerbrev");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        Inddrivelsesskridt(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    public enum Kundekontakttype implements DropdownOption {
        FRITEKSTBREV("IND0906DIV01", "Fritekstbrev");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        Kundekontakttype(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    //Get/set
    public String getPart() {
        return part;
    }

    public Inddrivelsesskridt getInddrivelsesskridt() {
        return inddrivelsesskridt;
    }

    public Kundekontakttype getKundekontakttype() {
        return kundekontakttype;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public void setInddrivelsesskridt(Inddrivelsesskridt inddrivelsesskridt) {
        this.inddrivelsesskridt = inddrivelsesskridt;
    }

    public void setKundekontakttype(Kundekontakttype kundekontakttype) {
        this.kundekontakttype = kundekontakttype;
    }
}
