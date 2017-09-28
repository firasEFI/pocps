package findus_datamodels;

import findus_pageobjects.DropdownOption;

import java.util.Calendar;

public class BeregnetBetalingsevne {

    public enum AnvendIInddrivelsesskridt implements DropdownOption {
        BUDGET("BDG", "Budget"),
        TABELTRAEK("LUP", "Tabeltræk");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        private AnvendIInddrivelsesskridt(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    private Double maanedligtBetalingsevneBudget;
    private Double MaanedligtBetalingsevneTabelTraek;
    private AnvendIInddrivelsesskridt anvendIInddrivelsesskridt;
    private Calendar beregnetBetalingsevneDato;

    public Double getMaanedligtBetalingsevneBudget() {
        return maanedligtBetalingsevneBudget;
    }
    public void setMaanedligtBetalingsevneBudget(Double maanedligtBetalingsevneBudget) {
        this.maanedligtBetalingsevneBudget = maanedligtBetalingsevneBudget;
    }

    public Double getMaanedligtBetalingsevneTabelTraek() {
        return MaanedligtBetalingsevneTabelTraek;
    }
    public void setMaanedligtBetalingsevneTabelTraek(Double maanedligtBetalingsevneTabelTraek) {
        MaanedligtBetalingsevneTabelTraek = maanedligtBetalingsevneTabelTraek;
    }

    public AnvendIInddrivelsesskridt getAnvendIInddrivelsesskridt() {
        return anvendIInddrivelsesskridt;
    }
    public void setAnvendIInddrivelsesskridt(AnvendIInddrivelsesskridt anvendIInddrivelsesskridt) {
        this.anvendIInddrivelsesskridt = anvendIInddrivelsesskridt;
    }

    public Calendar getBeregnetBetalingsevneDato() {
        return beregnetBetalingsevneDato;
    }
    public void setBeregnetBetalingsevneDato(Calendar beregnetBetalingsevneDato) {
        this.beregnetBetalingsevneDato = beregnetBetalingsevneDato;
    }
}
