package findus_pageobjects.opgave_overblik;

import findus_pageobjects.DropdownOption;

public class OpgaveSearchArgs {

    //Variables
    //Midten
    private OpgavePrioritet opgavePrioritet;
    private String tildeltTilBruger;
    private Opgavestatus opgavestatus;
    private String opgaveID;
    private String relationTilID;
    private String opgavekategori;
    private String opgavetype;
    private String senesteFristFra;
    private String senesteFristTil;
    private String afslutningsdatoFra;
    private String afslutningsdatoTil;

    
    //Methods
    public enum OpgavePrioritet implements DropdownOption {
        Prioritet_10("010", "Prioritet 10 - højest"),
        Prioritet_20("020", "Prioritet 20"),
        Prioritet_30("030", "Prioritet 30"),
        Prioritet_40("040", "Prioritet 40"),
        Prioritet_50("050", "Prioritet 50"),
        Prioritet_60("060", "Prioritet 60"),
        Prioritet_70("070", "Prioritet 70"),
        Prioritet_80("080", "Prioritet 80"),
        Prioritet_90("090", "Prioritet 90 - lavest");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        OpgavePrioritet(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    public enum Opgavestatus implements DropdownOption {
        Blank(" ", " "),
        Afsluttet("C", "Afsluttet"),
        Aktiv("W", "Aktiv"),
        Oprettet("O", "Oprettet");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        Opgavestatus(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    //Get
    public OpgaveSearchArgs.OpgavePrioritet getOpgavePrioritet() {
        return opgavePrioritet;
    }

    public String getTildeltTilBruger() {
        return tildeltTilBruger;
    }
    
    public OpgaveSearchArgs.Opgavestatus getOpgavestatus() {
        return opgavestatus;
    }
    
    public String getOpgaveID() {
        return opgaveID;
    }
    
    public String getRelationTilID() {
        return relationTilID;
    }
    
    public String getOpgavekategori() {
        return opgavekategori;
    }
    
    public String getOpgavetype() {
        return opgavetype;
    }
    
    public String getSenesteFristFra() {
        return senesteFristFra;
    }
    
    public String getSenesteFristTil() {
        return senesteFristTil;
    }
    
    public String getAfslutningsdatoFra() {
        return afslutningsdatoFra;
    }
    
    public String getAfslutningsdatoTil() {
        return afslutningsdatoTil;
    }
    
    //Set
    public void setOpgavePrioritet(OpgavePrioritet opgavePrioritet) {
        this.opgavePrioritet = opgavePrioritet;
    }
    
    public void setTildeltTilBruger(String tildeltTilBruger) {
        this.tildeltTilBruger = tildeltTilBruger;
    }

    public void setOpgavestatus(Opgavestatus opgavestatus) {
        this.opgavestatus = opgavestatus;
    }
    
    public void setOpgaveID(String opgaveID) {
        this.opgaveID = opgaveID;
    }
    
    public void setRelationTilID(String relationTilID) {
        this.relationTilID = relationTilID;
    }
    
    public void setOpgavekategori(String opgavekategori) {
        this.opgavekategori = opgavekategori;
    }
    
    public void setOpgavetype(String opgavetype) {
        this.opgavetype = opgavetype;
    }
    
    public void setSenesteFristFra(String senesteFristFra) {
        this.senesteFristFra = senesteFristFra;
    }
    
    public void setSenesteFristTil(String senesteFristTil) {
        this.senesteFristTil = senesteFristTil;
    }
    
    public void setAfslutningsdatoFra(String afslutningsdatoFra) {
        this.afslutningsdatoFra = afslutningsdatoFra;
    }
    
    public void setAfslutningsdatoTil(String afslutningsdatoTil) {
        this.afslutningsdatoTil = afslutningsdatoTil;
    }
    
    
    
}
