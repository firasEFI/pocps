package findus_datamodels;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Sag {

    public enum SagsType {
        AFDRAGSORDNING("Afdragsordning"),
        BOBEHANDLINGSSAG("Bobehandlingssag"),
        HENSTANDSSAG("Henstandssag"),
        HOERINGSSAG("Høringssag"),
        LOENINDEHOLDELSESSAG("Lønindeholdelsessag"),
        OPRET_KLAGESAG("Opret klagesag"),
        OPRET_OEVRIG_SAG("Opret øvrig sag"),
        PAAKRAV("Påkrav"),
        UDLANDSSAG("Udlandssag"),
        UDLAEGSSAG("Udlægssag");

        private final String name;

        private SagsType(String name) {
            this.name = name;
        }
    }

    private String sagsId;
    private SagsType sagsType;
    private LocalDateTime oprettelsesdato;
    private LocalDateTime Afslutningsdato;
    private String status;

    public String getSagsId() {
        return sagsId;
    }

    public void setSagsId(String sagsId) {
        this.sagsId = sagsId;
    }

    public SagsType getSagsType() {
        return sagsType;
    }

    public void setSagsType(SagsType sagsType) {
        this.sagsType = sagsType;
    }

    public LocalDateTime getOprettelsesdato() {
        return oprettelsesdato;
    }

    public void setOprettelsesdato(LocalDateTime oprettelsesdato) {
        this.oprettelsesdato = oprettelsesdato;
    }

    public LocalDateTime getAfslutningsdato() {
        return Afslutningsdato;
    }

    public void setAfslutningsdato(LocalDateTime afslutningsdato) {
        Afslutningsdato = afslutningsdato;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Sag() {

    }
}
