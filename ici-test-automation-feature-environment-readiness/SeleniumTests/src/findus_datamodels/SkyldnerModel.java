package findus_datamodels;

/**
 * Created by nielsjes on 16-08-2017.
 */
public abstract class SkyldnerModel {

    private String email;
    private String telefonnummer;
    private String skyldnerId;
    private String interntID;

    public String getInterntID() {
        return interntID;
    }

    public void setInterntID(String interntID) {
        this.interntID = interntID;
    }

    protected SkyldnerModel(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getSkyldnerId() {
        return skyldnerId;
    }

    public void setSkyldnerId(String skyldnerId) {
        this.skyldnerId = skyldnerId;
    }
}
