package findus_pageobjects.wizards.afdragsordning;

import findus_pageobjects.DropdownOption;

public class AfdragsordningWizardParm {

    //Variables
    //Sagsbehandlingsskridt
    private String aarsagOprettelseAfdOrdn;
    private VaelgAfdOrdnType vaelgAfdOrdnType;
    private String noteValgTypeAfdOrdn;

    //Inkluderede påkrav
    private String inklPaakrav;

    //Inkluderede fordringer
    private BegrundFjAfdOrdn begrundFjAfdOrdn;
    private String andenAarsag;
    private String noteForAtTilfoejeForordning;

    //Detaljer for afdragsordning
    private AfdOrdnFrekv afdOrdnFrekv;
    private String afdragsBeloeb;
    private String noteValgAfdrBeloeb;
    private String datoFoersteAfd;
    private String gyldighedsperiode;
    private boolean oprRykkerMnglBetMan;

    //Adressetype
    private AdresseType adresseType;
    private String noteForAdresse;
    private String skyldNavn;
    private String coNavn;
    private String adresse1;
    private String adresse2;
    private String adresse3;
    private String postNr;

    //Information om afdragsfri periode
    private String dateFra;
    private String dateTil;
    private String forAfdrFriPeriode;

    //Methods
    public enum VaelgAfdOrdnType implements DropdownOption {
        PAB("PAB", "Baseret på betalingsevne udfra budget"),
        PAL("PAL", "Baseret på betalingsevne udfra tabeltræk"),
        SZD("SZD", "Baseret på gældens størrelse"),
        FRN("FRN", "Baseret på kulance aftale"),
        COL("COL", "Baseret på sikkerhedsstillelse"),
        ADS("ADS", "Bobehandling"),
        VLT("VLT", "Frivillig afdragsordning");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        VaelgAfdOrdnType(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    public enum BegrundFjAfdOrdn implements DropdownOption {
        OTH("OTH", "Andet"),
        RTN("RTN", "Returneret"),
        RCL("RCL", "Tilbagekaldt"),
        GSE("GSE", "Underlagt opsættende virkning");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        BegrundFjAfdOrdn(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    public enum AfdOrdnFrekv implements DropdownOption {
        HFY("HFY", "Hvert halve år"),
        QUR("QUR", "Hvert kvartal"),
        MON("MON", "Månedlig"),
        YRL("YRL", "Årlig");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        AfdOrdnFrekv(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    public enum AdresseType implements DropdownOption {
        CSA("CSA", "Opret adresse for afdragsordning"),
        DBD("DBD", "Samme som skyldners kontaktadresse");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        AdresseType(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    //Get/set
    public VaelgAfdOrdnType getVaelgAfdOrdnType() {
        return vaelgAfdOrdnType;
    }

    public BegrundFjAfdOrdn getBegrundFjAfdOrdn() {
        return begrundFjAfdOrdn;
    }

    public AfdOrdnFrekv getAfdOrdnFrekv() {
        return afdOrdnFrekv;
    }

    public AdresseType getAdresseType() {
        return adresseType;
    }

    public String getAarsagOprettelseAfdOrdn() {
        return aarsagOprettelseAfdOrdn;
    }

    public String getNoteValgTypeAfdOrdn() {
        return noteValgTypeAfdOrdn;
    }

    public String getInklPaakrav() {
        return inklPaakrav;
    }

    public String getAndenAarsag() {
        return andenAarsag;
    }

    public String getNoteForAtTilfoejeForordning() {
        return noteForAtTilfoejeForordning;
    }

    public String getAfdragsBeloeb() {
        return afdragsBeloeb;
    }

    public String getNoteValgAfdrBeloeb() {
        return noteValgAfdrBeloeb;
    }

    public String getDatoFoersteAfd() {
        return datoFoersteAfd;
    }

    public String getGyldighedsperiode() {
        return gyldighedsperiode;
    }

    public boolean isOprRykkerMnglBetMan() {
        return oprRykkerMnglBetMan;
    }

    public String getNoteForAdresse() {
        return noteForAdresse;
    }

    public String getSkyldNavn() {
        return skyldNavn;
    }

    public String getCoNavn() {
        return coNavn;
    }

    public String getAdresse1() {
        return adresse1;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public String getAdresse3() {
        return adresse3;
    }

    public String getPostNr() {
        return postNr;
    }

    public String getDateFra() {
        return dateFra;
    }

    public String getDateTil() {
        return dateTil;
    }

    public String getForAfdrFriPeriode() {
        return forAfdrFriPeriode;
    }

    public void setAarsagOprettelseAfdOrdn(String aarsagOprettelseAfdOrdn) {
        this.aarsagOprettelseAfdOrdn = aarsagOprettelseAfdOrdn;
    }

    public void setVaelgAfdOrdnType(VaelgAfdOrdnType vaelgAfdOrdnType) {
        this.vaelgAfdOrdnType = vaelgAfdOrdnType;
    }

    public void setNoteValgTypeAfdOrdn(String noteValgTypeAfdOrdn) {
        this.noteValgTypeAfdOrdn = noteValgTypeAfdOrdn;
    }

    public void setInklPaakrav(String inklPaakrav) {
        this.inklPaakrav = inklPaakrav;
    }

    public void setBegrundFjAfdOrdn(BegrundFjAfdOrdn begrundFjAfdOrdn) {
        this.begrundFjAfdOrdn = begrundFjAfdOrdn;
    }

    public void setAndenAarsag(String andenAarsag) {
        this.andenAarsag = andenAarsag;
    }

    public void setNoteForAtTilfoejeForordning(String noteForAtTilfoejeForordning) {
        this.noteForAtTilfoejeForordning = noteForAtTilfoejeForordning;
    }

    public void setAfdOrdnFrekv(AfdOrdnFrekv afdOrdnFrekv) {
        this.afdOrdnFrekv = afdOrdnFrekv;
    }

    public void setAfdragsBeloeb(String afdragsBeloeb) {
        this.afdragsBeloeb = afdragsBeloeb;
    }

    public void setNoteValgAfdrBeloeb(String noteValgAfdrBeloeb) {
        this.noteValgAfdrBeloeb = noteValgAfdrBeloeb;
    }

    public void setDatoFoersteAfd(String datoFoersteAfd) {
        this.datoFoersteAfd = datoFoersteAfd;
    }

    public void setGyldighedsperiode(String gyldighedsperiode) {
        this.gyldighedsperiode = gyldighedsperiode;
    }

    public void setOprRykkerMnglBetMan(boolean oprRykkerMnglBetMan) {
        this.oprRykkerMnglBetMan = oprRykkerMnglBetMan;
    }

    public void setAdresseType(AdresseType adresseType) {
        this.adresseType = adresseType;
    }

    public void setNoteForAdresse(String noteForAdresse) {
        this.noteForAdresse = noteForAdresse;
    }

    public void setSkyldNavn(String skyldNavn) {
        this.skyldNavn = skyldNavn;
    }

    public void setCoNavn(String coNavn) {
        this.coNavn = coNavn;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public void setAdresse3(String adresse3) {
        this.adresse3 = adresse3;
    }

    public void setPostNr(String postNr) {
        this.postNr = postNr;
    }

    public void setDateFra(String dateFra) {
        this.dateFra = dateFra;
    }

    public void setDateTil(String dateTil) {
        this.dateTil = dateTil;
    }

    public void setForAfdrFriPeriode(String forAfdrFriPeriode) {
        this.forAfdrFriPeriode = forAfdrFriPeriode;
    }
}
