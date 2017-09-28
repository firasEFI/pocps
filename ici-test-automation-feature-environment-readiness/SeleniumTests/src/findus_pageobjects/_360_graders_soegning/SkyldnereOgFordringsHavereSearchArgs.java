package findus_pageobjects._360_graders_soegning;

import findus_datamodels.CprSkyldnerModel;
import findus_datamodels.SkyldnerModel;
import findus_pageobjects.DropdownOption;

public class SkyldnereOgFordringsHavereSearchArgs {

    public enum SoegeType implements DropdownOption {
        FUZZY("", "Fuzzy"),
        STANDARD("", "Standard");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        private SoegeType(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    public enum IdType implements DropdownOption {
        AKR_NUMMER("", "AKR-nummer"),
        ALTERNATIVT_ID("", "Alternativt ID"),
        CPR_NUMMER("", "CPR-nummer"),
        CVR_NUMMER("", "CVR-nummer"),
        FORDRINGSHAVER_ID("", "Fordringshaver ID"),
        SE_NUMMER("", "SE-nummer");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        private IdType(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    private String fuldeNavn;
    private String fornavn;
    private String efternavn;
    private String email;
    private String telefonnummer;
    private String skyldnerId;
    private Boolean visKunInaktive;
    private SoegeType soegeType;
    private IdType idType;
    private String id;

    public String getFuldeNavn() {
        return fuldeNavn;
    }

    public void setFuldeNavn(String fuldeNavn) {
        this.fuldeNavn = fuldeNavn;
    }

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

    public Boolean isVisKunInaktive() {
        return visKunInaktive;
    }

    public void setVisKunInaktive(Boolean visKunInaktive) {
        this.visKunInaktive = visKunInaktive;
    }

    public SoegeType getSoegeType() {
        return soegeType;
    }

    public void setSoegeType(SoegeType soegeType) {
        this.soegeType = soegeType;
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SkyldnereOgFordringsHavereSearchArgs() {
    }

    public static SkyldnereOgFordringsHavereSearchArgs createSearchArgsForIdFromSkyldner(SkyldnerModel skyldner) {
        if(skyldner == null)
            throw new IllegalArgumentException("skyldner cannot be null");

        SkyldnereOgFordringsHavereSearchArgs searchArgs = new SkyldnereOgFordringsHavereSearchArgs();

        if(skyldner instanceof CprSkyldnerModel) {
            searchArgs.setIdType(IdType.CPR_NUMMER);
            searchArgs.setId(((CprSkyldnerModel) skyldner).getCprNummer());
        } else
            throw new UnsupportedOperationException("Not implemented... yet");

        return searchArgs;
    }

    public static SkyldnereOgFordringsHavereSearchArgs createFromSkyldner(CprSkyldnerModel skyldner) {
        SkyldnereOgFordringsHavereSearchArgs searchArgs = createSearchArgsForIdFromSkyldner(skyldner);

        searchArgs.setFornavn(skyldner.getFornavn());
        searchArgs.setEfternavn(skyldner.getEfternavn());
        searchArgs.setEmail(skyldner.getEmail());
        searchArgs.setTelefonnummer(skyldner.getTelefonnummer());
        searchArgs.setSkyldnerId(skyldner.getSkyldnerId());

        return searchArgs;
    }
}
