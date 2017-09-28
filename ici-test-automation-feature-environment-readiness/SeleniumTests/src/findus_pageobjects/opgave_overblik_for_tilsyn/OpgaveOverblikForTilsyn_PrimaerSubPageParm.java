package findus_pageobjects.opgave_overblik_for_tilsyn;

public class OpgaveOverblikForTilsyn_PrimaerSubPageParm {
    //Variable
    private String tildeltBruger;
    private String relationTilID;

    //Get/set
    public String getTildeltBruger() {
        return tildeltBruger;
    }

    public void setTildeltBruger(String tildeltBruger) {
        this.tildeltBruger = tildeltBruger;
    }

    public String getRelationTilID() {
        return this.relationTilID;
    }

    public void setRelationTilID(String txtRelationTilID) {
        this.relationTilID = txtRelationTilID;
    }
}
