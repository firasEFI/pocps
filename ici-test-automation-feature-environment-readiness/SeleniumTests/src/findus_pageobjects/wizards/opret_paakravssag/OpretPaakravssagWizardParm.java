package findus_pageobjects.wizards.opret_paakravssag;

import findus_pageobjects.DropdownOption;
import icisel.taxobjects.Fordring;

import java.util.ArrayList;
import java.util.List;

public class OpretPaakravssagWizardParm {

    //Variables
    //Toppen
    private Paakravstype paakravstype;
    private Inddrivelsesskridt inddrivelsesskridt;

    private List<Fordring> fordringer = new ArrayList<Fordring>();

    //Methods
    public enum Paakravstype implements DropdownOption {
        Med_partshoering("FDP", "Påkrav - med partshøring"),
        Uden_partshoering("FDM", "Påkrav - uden partshøring");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        Paakravstype(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    public enum Inddrivelsesskridt implements DropdownOption {
        Paakrav("DEML", "Påkrav");

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

    //Get/set
    public Paakravstype getPaakravstype() {
        return paakravstype;
    }

    public Inddrivelsesskridt getInddrivelsesskridt() {
        return inddrivelsesskridt;
    }

    public void setPaakravstype(Paakravstype paakravstype) {
        this.paakravstype = paakravstype;
    }

    public void setInddrivelsesskridt(Inddrivelsesskridt inddrivelsesskridt) {
        this.inddrivelsesskridt = inddrivelsesskridt;
    }

    public List<Fordring> getFordringer() {
        return this.fordringer;
    }
}

