package findus_pageobjects.wizards.kundekontakt;

public class Kundekontakt {

    private String modtager;
    private Boolean digitalPost;
    private String modtagerAdresse;
    private String att;

    private Boolean tilfoejGaeldsoverblik;
    private Boolean tilfoejOcrLinje;
    private String ocrLinje;

    private String fritekstOverskrift;
    private String fritekstAndenAktoerJournalNr;
    private String fritekst;
    private String fritekstOevrigeOplysninger;

    private String forbladOverskrift;
    private String tekstTilForblad;

    public String getModtager() {
        return this.modtager;
    }
    public void setModtager(String modtager) {
        this.modtager = modtager;
    }

    public Boolean getDigitalPost() {
        return this.digitalPost;
    }
    public void setDigitalPost(Boolean digitalPost) {
        this.digitalPost = digitalPost;
    }

    public String getModtagerAdresse() {
        return this.modtagerAdresse;
    }
    public void setModtagerAdresse(String modtagerAdresse) {
        this.modtagerAdresse = modtagerAdresse;
    }

    public String getAtt() {
        return this.att;
    }
    public void setAtt(String att) {
        this.att = att;
    }

    public Boolean getTilfoejGaeldsoverblik() { return this.tilfoejGaeldsoverblik; }
    public void setTilfoejGaeldsoverblik(Boolean gaeldsoverblik) { this.tilfoejGaeldsoverblik = tilfoejGaeldsoverblik; }

    public Boolean getTilfoejOcrLinje() {
        return this.tilfoejOcrLinje;
    }
    public void setTilfoejOcrLinje(Boolean tilfoejOcrLinje) {
        this.tilfoejOcrLinje = tilfoejOcrLinje;
    }

    public String getOcrLinje() { return this.ocrLinje; }
    public void setOcrLinje(String ocrLinje) { this.ocrLinje = ocrLinje; }

    public String getFritekstOverskrift() {
        return fritekstOverskrift;
    }
    public void setFritekstOverskrift(String fritekstOverskrift) {
        this.fritekstOverskrift = fritekstOverskrift;
    }

    public String getFritekstAndenAktoerJournalNr() {
        return fritekstAndenAktoerJournalNr;
    }
    public void setFritekstAndenAktoerJournalNr(String fritekstAndenAktoerJournalNr) {
        this.fritekstAndenAktoerJournalNr = fritekstAndenAktoerJournalNr;
    }

    public String getFritekst() {
        return fritekst;
    }
    public void setFritekst(String fritekst) {
        this.fritekst = fritekst;
    }

    public String getFritekstOevrigeOplysninger() {
        return fritekstOevrigeOplysninger;
    }
    public void setFritekstOevrigeOplysninger(String fritekstOevrigeOplysninger) {
        this.fritekstOevrigeOplysninger = fritekstOevrigeOplysninger;
    }

    public String getForbladOverskrift() {
        return forbladOverskrift;
    }
    public void setForbladOverskrift(String forbladOverskrift) {
        this.forbladOverskrift = forbladOverskrift;
    }

    public String getTekstTilForblad() {
        return tekstTilForblad;
    }
    public void setTekstTilForblad(String tekstTilForblad) {
        this.tekstTilForblad = tekstTilForblad;
    }

    public Kundekontakt() {
    }


}
