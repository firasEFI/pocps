package findus_datamodels;

import findus_pageobjects.DropdownOption;
import org.apache.xpath.operations.Bool;

public class GrundlagForBetalingsevneAarligModel extends GrundlagForBetalingsEvneModel {

    public enum BegrundelseForAarligIndkomst implements DropdownOption {
        IKKE_FORELIGGER_OPLYSNINGER_OM_SKYLDNERS_INDKOMST("00", "At der ikke foreligger oplysninger om skyldners indkomst i indkomstregisteret.");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        private BegrundelseForAarligIndkomst(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    public enum Aar implements DropdownOption {
        _2015("2015", "2015"),
        _2016("2016", "2016");

        private final String value;
        private final String visibleText;

        public String getValue() { return this.value; }

        public String getVisibleText() {return this.visibleText; }

        private Aar(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }
    public enum WebServiceCallMethod implements DropdownOption {
        _manuelIndtastning("M2", "Manuel indtastning");

        private final String value;
        private final String visibleText;

        public String getValue() { return this.value; }

        public String getVisibleText() {return this.visibleText; }

        private WebServiceCallMethod(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    private Double bruttoindkomst;
    private Double nettoindkomst;
    private Integer aarsopgørelsesnr;
    private BegrundelseForAarligIndkomst begrundelseForAarligIndkomst;
    private Aar aar;

    public WebServiceCallMethod getWebServiceCallMethod() {return webServiceCallMethod;}

    public void setWebServiceCallMethod(WebServiceCallMethod webServiceCallMethod) {this.webServiceCallMethod = webServiceCallMethod; }

    private WebServiceCallMethod webServiceCallMethod;
    private BetalingsevneEnums.Afdrag afdrag;
    private Double beregnetAarligAfdrag;
    private Boolean forsoegerPligt;

    public Double getBruttoindkomst() {
        return bruttoindkomst;
    }
    public void setBruttoindkomst(Double bruttoindkomst) {
        this.bruttoindkomst = bruttoindkomst;
    }

    public Double getNettoindkomst() {
        return nettoindkomst;
    }
    public void setNettoindkomst(Double nettoindkomst) {
        this.nettoindkomst = nettoindkomst;
    }

    public Integer getAarsopgørelsesnr() {
        return aarsopgørelsesnr;
    }
    public void setAarsopgørelsesnr(Integer aarsopgørelsesnr) {
        this.aarsopgørelsesnr = aarsopgørelsesnr;
    }

    public BegrundelseForAarligIndkomst getBegrundelseForAarligIndkomst() {
        return begrundelseForAarligIndkomst;
    }
    public void setBegrundelseForAarligIndkomst(BegrundelseForAarligIndkomst begrundelseForAarligIndkomst) {
        this.begrundelseForAarligIndkomst = begrundelseForAarligIndkomst;
    }

    public Aar getAar() {
        return aar;
    }
    public void setAar(Aar aar) {
        this.aar = aar;
    }

    public BetalingsevneEnums.Afdrag getAfdrag() {
        return afdrag;
    }
    public void setAfdrag(BetalingsevneEnums.Afdrag afdrag) {
        this.afdrag = afdrag;
    }

    public Double getBeregnetAarligAfdrag() {
        return beregnetAarligAfdrag;
    }
    public void setBeregnetAarligAfdrag(Double beregnetAarligAfdrag) {
        this.beregnetAarligAfdrag = beregnetAarligAfdrag;
    }
    public Boolean getForsoegerPligt() {
        return forsoegerPligt;
    }

    public void setForsoegerPligt(Boolean forsoegerPligt) {
        this.forsoegerPligt = forsoegerPligt;
    }

}