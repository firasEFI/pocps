package findus_pageobjects.Brevoplysninger_soeg;

import java.time.LocalDate;

public class BrevoplysningerSearchArgs {

    private String part;
    private LocalDate stiftelsesdatoFra;
    private LocalDate stiftelsesdatoTil;
    private String inddrivelsesskridt;
    private String kontakttype;
    private String bruger;
    private String kundekontaktId;
    private String navn;
    private String beskrivelse;

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public LocalDate getStiftelsesdatoFra() {
        return stiftelsesdatoFra;
    }

    public void setStiftelsesdatoFra(LocalDate stiftelsesdatoFra) {
        this.stiftelsesdatoFra = stiftelsesdatoFra;
    }

    public LocalDate getStiftelsesdatoTil() {
        return stiftelsesdatoTil;
    }

    public void setStiftelsesdatoTil(LocalDate stiftelsesdatoTil) {
        this.stiftelsesdatoTil = stiftelsesdatoTil;
    }

    public String getInddrivelsesskridt() {
        return inddrivelsesskridt;
    }

    public void setInddrivelsesskridt(String inddrivelsesskridt) {
        this.inddrivelsesskridt = inddrivelsesskridt;
    }

    public String getKontakttype() {
        return kontakttype;
    }

    public void setKontakttype(String kontakttype) {
        this.kontakttype = kontakttype;
    }

    public String getBruger() {
        return bruger;
    }

    public void setBruger(String bruger) {
        this.bruger = bruger;
    }

    public String getKundekontaktId() {
        return kundekontaktId;
    }

    public void setKundekontaktId(String kundekontaktId) {
        this.kundekontaktId = kundekontaktId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
}
