package findus_pageobjects.wizards.opret_opgave_manuelt;

import findus_pageobjects.DropdownOption;

public class OpretOpgaveManueltWizardPageParm {


    //Opret opgave
    private VaelgOpgavekategori vaelgOpgavekategori;
    private VaelgOpgavetype vaelgOpgavetype;
    private String beskrivelse;
    private String tidligsteSlutdato;
    private String frist;

    //Tildelt til
    private VaelgSendTil vaelgSendTil;
    private String tildeltSagsbehandler;


    //Get/set
    public VaelgOpgavekategori getVaelgOpgavekategori() {
        return vaelgOpgavekategori;
    }

    public VaelgOpgavetype getVaelgOpgavetype() {
        return vaelgOpgavetype;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public String getTidligsteSlutdato() {
        return tidligsteSlutdato;
    }

    public String getTildeltSagsbehandler() {
        return tildeltSagsbehandler;
    }

    public VaelgSendTil getVaelgSendTil() {
        return vaelgSendTil;
    }

    public String getFrist() {
        return frist;
    }

    public void setVaelgOpgavekategori(VaelgOpgavekategori vaelgOpgavekategori) {
        this.vaelgOpgavekategori = vaelgOpgavekategori;
    }

    public void setVaelgOpgavetype(VaelgOpgavetype vaelgOpgavetype) {
        this.vaelgOpgavetype = vaelgOpgavetype;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public void setTidligsteSlutdato(String tidligsteSlutdato) {
        this.tidligsteSlutdato = tidligsteSlutdato;
    }

    public void setFrist(String frist) {
        this.frist = frist;
    }

    public void setVaelgSendTil(VaelgSendTil vaelgSendTil) {
        this.vaelgSendTil = vaelgSendTil;
    }

    public void setTildeltSagsbehandler(String tildeltSagsbehandler) {
        this.tildeltSagsbehandler = tildeltSagsbehandler;
    }

    //Methods
    public enum VaelgOpgavekategori implements DropdownOption {
        HANDLEAGENT("HandleAgent", "Administrere anden aktør"),
        HANDLEDEBTOR("HandleDebtor", "Administrere skyldner"),
        PAYPLAN("Payplan", "Afdragsordning"),
        SETTLEMENT("Settlement", "Afregne"),
        WRITEOFF("Writeoff", "Afskrive"),
        COMPLAINTS("Complaints", "Behandle"),
        FOREIGNCASE("Foreigncase", "Bistandsanmodning til og fra udlandet"),
        DEFERRAL("Deferral", "Henstand"),
        DOCUMENTS("Documents", "Håndtere dokumenter og oplysninger"),
        LIMITATION("Limitation", "Håndtere forældelse"),
        HANDLECASES("HandleCases", "Håndtere sager"),
        PAYOUTS("Payouts", "Håndtere udbetalinger"),
        PAYMENTS("Payments", "Modtag og håndter betalinger"),
        CLAIMS("Claims", "Modtage og håndtere fordringer"),
        WRITEDOWNS("Writedowns", "Nedskrivning"),
        RECOVERYINTERESTS("RecoveryInterests", "Oprette og håndtere inddrivelsesrenter"),
        FIRSTDEMAND("FirstDemand", "Påkrav"),
        GARNISHMENT("Garnishment", "Udlæg"),
        CHOOSEDEBTORS("ChooseDebtors", "Udvælge Skyldnere");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        VaelgOpgavekategori(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    public enum VaelgOpgavetype implements DropdownOption {
        DK_CRTFD("DK-CRTFD", "Send påkrav"),
        DK_CRTPP("DK-CRTPP", "Opret afdragsording");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        VaelgOpgavetype(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    public enum VaelgSendTil implements DropdownOption {
        SNDU("SNDU", "Bruger"),
        SNDR("SNDR", "Rolle");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        VaelgSendTil(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }
}
