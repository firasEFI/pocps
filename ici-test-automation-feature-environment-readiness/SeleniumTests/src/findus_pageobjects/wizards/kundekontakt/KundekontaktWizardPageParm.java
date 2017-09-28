package findus_pageobjects.wizards.kundekontakt;

public class KundekontaktWizardPageParm {
    //***Variables
    //Primær
    //Modtager information
    private String modtager;
    private boolean digitalpost;
    private String modtagerAdresse;
    private String att;
    //Vedhæftninger
    //Valgmuligheder
    private boolean tilfoejGaeldsoverblik;
    private boolean tilfoejOCRLinje;
    //Information for forbladet
    private String forbladOverskrift;
    private String tekstTilForblad;
    //Fritekst
    private String overskrift;
    private String andenAktoerJournalNr;
    private String fritekst;


    //***Get
    public String getModtager() {
        return modtager;
    }

    public boolean isDigitalpost() {
        return digitalpost;
    }

    public String getModtagerAdresse() {
        return modtagerAdresse;
    }

    public String getAtt() {
        return att;
    }

    public boolean isTilfoejGaeldsoverblik() {
        return tilfoejGaeldsoverblik;
    }

    public boolean isTilfoejOCRLinje() {
        return tilfoejOCRLinje;
    }

    public String getForbladOverskrift() {
        return forbladOverskrift;
    }

    public String getTekstTilForblad() {
        return tekstTilForblad;
    }

    public String getOverskrift() {
        return overskrift;
    }

    public String getAndenAktoerJournalNr() {
        return andenAktoerJournalNr;
    }

    public String getFritekst() {
        return fritekst;
    }

    //***Set
    public void setModtager(String modtager) {
        this.modtager = modtager;
    }

    public void setDigitalpost(boolean digitalpost) {
        this.digitalpost = digitalpost;
    }

    public void setModtagerAdresse(String modtagerAdresse) {
        this.modtagerAdresse = modtagerAdresse;
    }

    public void setAtt(String att) {
        this.att = att;
    }

    public void setTilfoejGaeldsoverblik(boolean tilfoejGaeldsoverblik) {
        this.tilfoejGaeldsoverblik = tilfoejGaeldsoverblik;
    }

    public void setTilfoejOCRLinje(boolean tilfoejOCRLinje) {
        this.tilfoejOCRLinje = tilfoejOCRLinje;
    }

    public void setForbladOverskrift(String forbladOverskrift) {
        this.forbladOverskrift = forbladOverskrift;
    }

    public void setTekstTilForblad(String tekstTilForblad) {
        this.tekstTilForblad = tekstTilForblad;
    }

    public void setOverskrift(String overskrift) {
        this.overskrift = overskrift;
    }

    public void setAndenAktoerJournalNr(String andenAktoerJournalNr) {
        this.andenAktoerJournalNr = andenAktoerJournalNr;
    }

    public void setFritekst(String fritekst) {
        this.fritekst = fritekst;
    }
}
