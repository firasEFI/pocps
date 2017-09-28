package findus_datamodels;

public class CprSkyldnerModel extends SkyldnerModel {

    private String fornavn;
    private String efternavn;
    private String cprNummer;

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getFuldeNavn() { return String.format("{0} {1}", fornavn, efternavn); }

    public void setCprNummer(String cprNummer) {
        this.cprNummer = cprNummer;
    }

    public String getCprNummer() {
        return this.cprNummer;
    }

    public CprSkyldnerModel() {
    }
}
