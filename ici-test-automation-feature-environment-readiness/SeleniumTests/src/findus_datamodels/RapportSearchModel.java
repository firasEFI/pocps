package findus_datamodels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RapportSearchModel {

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    final Date bogfoeringsDatoFra;
    final Date bogfoeringsDatoTil;
    final String kontoNummer;
    final String eksternKildeID;

    public RapportSearchModel(Date bogfoeringsDatoFra, Date bogfoeringsDatoTil, String kontoNummer, String eksternKildeID) {
        this.bogfoeringsDatoFra = bogfoeringsDatoFra;
        this.bogfoeringsDatoTil = bogfoeringsDatoTil;
        this.kontoNummer = kontoNummer;
        this.eksternKildeID = eksternKildeID;
    }

    public Date getBogfoeringsDatoFra() {
        return bogfoeringsDatoFra;
    }

    public Date getBogfoeringsDatoTil() {
        return bogfoeringsDatoTil;
    }

    public String getBogfoeringsDatoFraFormatted() {
        return dateFormat.format(bogfoeringsDatoFra);
    }

    public String getBogfoeringsDatoTilFormatted() {
        return dateFormat.format(bogfoeringsDatoTil);
    }

    public String getKontoNummer() {
        return kontoNummer;
    }

    public String getEksternKildeID() {
        return eksternKildeID;
    }

    public boolean bogfoeringsDatoFraIsNotNull() {
        return bogfoeringsDatoFra != null;
    }

    public boolean bogfoeringsDatoTilIsNotNull() {
        return bogfoeringsDatoTil != null;
    }

    public boolean kontoNummerIsNotEmptyOrNull() {
        return kontoNummer != null && !kontoNummer.isEmpty();
    }

    public boolean eksternKildeIDIsNotEmptyOrNull() {
        return eksternKildeID != null && !eksternKildeID.isEmpty();
    }

    public static class RapportSearchModelBuilder {
        private Date bogfoeringsDatoFra;
        private Date bogfoeringsDatoTil;
        private String kontoNummer;
        private String eksternKildeID;

        public RapportSearchModelBuilder setBogfoeringsDatoFra(Date bogfoeringsDatoFra) {
            this.bogfoeringsDatoFra = bogfoeringsDatoFra;
            return this;
        }

        public RapportSearchModelBuilder setBogfoeringsDatoTil(Date bogfoeringsDatoTil) {
            this.bogfoeringsDatoTil = bogfoeringsDatoTil;
            return this;
        }

        public RapportSearchModelBuilder setKontoNummer(String kontoNummer) {
            this.kontoNummer = kontoNummer;
            return this;
        }

        public RapportSearchModelBuilder setEksternKildeID(String eksternKildeID) {
            this.eksternKildeID = eksternKildeID;
            return this;
        }

        public RapportSearchModel createRapportSearchModel() {
            return new RapportSearchModel(bogfoeringsDatoFra, bogfoeringsDatoTil, kontoNummer, eksternKildeID);
        }
    }
}