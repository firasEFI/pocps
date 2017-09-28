package findus_datamodels;

/**
 * Created by nielsjes on 16-08-2017.
 */
public class PsrmUserModel {

    private String sagsbehanlderId;
    private String password;
    private String fornavn;
    private String efternavn;

    //Get/set
    public String getSagsbehanlderId() {
        return sagsbehanlderId;
    }

    public String getPassword() {
        return password;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setSagsbehanlderId(String sagsbehanlderId) {
        this.sagsbehanlderId = sagsbehanlderId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }
}
