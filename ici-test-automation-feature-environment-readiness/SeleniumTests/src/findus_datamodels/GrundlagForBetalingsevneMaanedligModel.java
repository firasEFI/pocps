package findus_datamodels;

import findus_pageobjects.DropdownOption;

import java.util.Calendar;

public class GrundlagForBetalingsevneMaanedligModel extends GrundlagForBetalingsEvneModel {

    public enum MaanedSomGrundlag {
        MAANED_1,
        MAANED_2
    }

    public enum Indkomsttype implements DropdownOption {
        LOENINDKOMST("00", "Lønindkomst"),
        UDDANNELSESYDELSE("01", "Uddannelsesydelse (SU)"),
        INDKOMST_UNDER_REGLERNE_VEDR_GROENLANDSSKAT("03", "Indkomst under reglerne vedr. Grønlandsskat"),
        ANDEN_PERSONLIG_INDKOMST_EKS_AM_BIDRAG("04", "Anden personlig indkomst, eksklusiv AM-bidrag."),
        B_INDKOMST("05", "B-indkomst"),
        KONTANTHJAELP_RESSOURCEFORLOEBELSE("06", "Kontanthjælp, ressourceforløbsydelse baseret på kontanthjælp og kontantydelse"),
        SYGE_OG_BARSELSDAGPENGE("07", "Syge- og barselsdagpenge udbetalt fra staten"),
        FORSKERORDNINGEN("08", "Forskerordningen"),
        AM_BIDRAGSFRI_OG_A_SKATTEFRI_LØN("09", "AM-bidragsfri og A-skattefri løn i ansættelsesforhold"),
        INDKOMST_FRA_AFU_FONDEN("20", "Indkomst fra AFU-fonden"),
        ANDRE_PERS_FORDELE("24", "Andre pers. fordele hvor lønindeholdelse ikke kan foretages");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        private Indkomsttype(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
        }



    private BruttoindkomstModel bruttoindkomst1;
    private BruttoindkomstModel bruttoindkomst2;

    private Double arbejdsmarkedsbidrag;
    private Double pensionsindbetalinger;
    private Double atpBidrag;
    private Double skat;
    private Double beregnetMaanedligNettoindkomst;
    private Double beregnetAarligNettoindkomst;

    private MaanedSomGrundlag maanedSomGrundlag;

    public BruttoindkomstModel getBruttoindkomst1() {
        return bruttoindkomst1;
    }

    public void setBruttoindkomst1(BruttoindkomstModel bruttoindkomst1) {
        this.bruttoindkomst1 = bruttoindkomst1;
    }

    public BruttoindkomstModel getBruttoindkomst2() {
        return bruttoindkomst2;
    }

    public void setBruttoindkomst2(BruttoindkomstModel bruttoindkomst2) {
        this.bruttoindkomst2 = bruttoindkomst2;
    }

    public Double getArbejdsmarkedsbidrag() {
        return arbejdsmarkedsbidrag;
    }

    public void setArbejdsmarkedsbidrag(double arbejdsmarkedsbidrag) {
        this.arbejdsmarkedsbidrag = arbejdsmarkedsbidrag;
    }

    public Double getPensionsindbetalinger() {
        return pensionsindbetalinger;
    }

    public void setPensionsindbetalinger(double pensionsindbetalinger) {
        this.pensionsindbetalinger = pensionsindbetalinger;
    }

    public Double getAtpBidrag() {
        return atpBidrag;
    }
    public void setAtpBidrag(double atpBidrag) {
        this.atpBidrag = atpBidrag;
    }

    public Double getSkat() {
        return skat;
    }
    public void setSkat(double skat) {
        this.skat = skat;
    }

    public Double getBeregnetMaanedligNettoindkomst() {
        return beregnetMaanedligNettoindkomst;
    }
    public void setBeregnetMaanedligNettoindkomst(double beregnetMaanedligNettoindkomst) {
        this.beregnetMaanedligNettoindkomst = beregnetMaanedligNettoindkomst;
    }

    public Double getBeregnetAarligNettoindkomst() {
        return beregnetAarligNettoindkomst;
    }
    public void setBeregnetAarligNettoindkomst(double beregnetAarligNettoindkomst) {
        this.beregnetAarligNettoindkomst = beregnetAarligNettoindkomst;
    }

    public MaanedSomGrundlag getMaanedSomGrundlag() {
        return this.maanedSomGrundlag;
    }
    public void setMaanedSomGrundlag(MaanedSomGrundlag maanedSomGrundlag) {
        this.maanedSomGrundlag = maanedSomGrundlag;
    }

    public class BruttoindkomstModel {

        private Double bruttoindkomst;
        private Calendar periode;
        private Indkomsttype indkomsttype;
        private BetalingsevneEnums.Afdrag afdrag;
        private Double fradragsbeloeb;
        private Double a_indkomstMedAmBidrag;
        private Double a_indkomstUdenAmBidrag;
        private Double b_indkomstMedAmBidrag;
        private Double b_indkomstUdenAmBidrag;
        private Double egenDelAfAtpBidrag;
        private Double angivetA_Skat;
        private Double amBidrag;
        private Double nettoindkomst;
        private Double indberetningskilder;
        private Double indberetningstyper;

        public Double getBruttoindkomst() {
            return bruttoindkomst;
        }
        public void setBruttoindkomst(double bruttoindkomst) {
            this.bruttoindkomst = bruttoindkomst;
        }

        public Calendar getPeriode() {
            return periode;
        }
        public void setPeriode(Calendar periode) {
            this.periode = periode;
        }

        public Indkomsttype getIndkomsttype() {
            return indkomsttype;
        }
        public void setIndkomsttype(Indkomsttype indkomsttype) {
            this.indkomsttype = indkomsttype;
        }

        public BetalingsevneEnums.Afdrag getAfdrag() {
            return afdrag;
        }
        public void setAfdrag(BetalingsevneEnums.Afdrag afdrag) {
            this.afdrag = afdrag;
        }

        public Double getFradragsbeloeb() {
            return fradragsbeloeb;
        }
        public void setFradragsbeloeb(double fradragsbeloeb) {
            this.fradragsbeloeb = fradragsbeloeb;
        }

        public Double getA_indkomstMedAmBidrag() {
            return a_indkomstMedAmBidrag;
        }
        public void setA_indkomstMedAmBidrag(double a_indkomstMedAmBidrag) {
            this.a_indkomstMedAmBidrag = a_indkomstMedAmBidrag;
        }

        public Double getA_indkomstUdenAmBidrag() {
            return a_indkomstUdenAmBidrag;
        }
        public void setA_indkomstUdenAmBidrag(double a_indkomstUdenAmBidrag) {
            this.a_indkomstUdenAmBidrag = a_indkomstUdenAmBidrag;
        }

        public Double getB_indkomstMedAmBidrag() {
            return b_indkomstMedAmBidrag;
        }
        public void setB_indkomstMedAmBidrag(double b_indkomstMedAmBidrag) {
            this.b_indkomstMedAmBidrag = b_indkomstMedAmBidrag;
        }

        public Double getB_indkomstUdenAmBidrag() {
            return b_indkomstUdenAmBidrag;
        }
        public void setB_indkomstUdenAmBidrag(double b_indkomstUdenAmBidrag) {
            this.b_indkomstUdenAmBidrag = b_indkomstUdenAmBidrag;
        }

        public Double getEgenDelAfAtpBidrag() {
            return egenDelAfAtpBidrag;
        }
        public void setEgenDelAfAtpBidrag(double egenDelAfAtpBidrag) {
            this.egenDelAfAtpBidrag = egenDelAfAtpBidrag;
        }

        public Double getAngivetA_Skat() {
            return angivetA_Skat;
        }
        public void setAngivetA_Skat(double angivetA_Skat) {
            this.angivetA_Skat = angivetA_Skat;
        }

        public Double getAmBidrag() {
            return amBidrag;
        }
        public void setAmBidrag(double amBidrag) {
            this.amBidrag = amBidrag;
        }

        public Double getNettoindkomst() {
            return nettoindkomst;
        }
        public void setNettoindkomst(double nettoindkomst) {
            this.nettoindkomst = nettoindkomst;
        }

        public Double getIndberetningskilder() {
            return indberetningskilder;
        }
        public void setIndberetningskilder(double indberetningskilder) {
            this.indberetningskilder = indberetningskilder;
        }

        public Double getIndberetningstyper() {
            return indberetningstyper;
        }
        public void setIndberetningstyper(double indberetningstyper) {
            this.indberetningstyper = indberetningstyper;
        }
    }
}
