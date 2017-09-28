package findus_pageobjects.sagsbehandlingsskridt;

public class OpretAfgoerelsesbrevBrevParm {
    //Variables
    private String datoFoersteAfd;
    private boolean aktvAfdOrdnMedDetSamme;
    private String aktiveringsNote;

    //Get/set
    public String getDatoFoersteAfd() {
        return datoFoersteAfd;
    }

    public boolean isAktvAfdOrdnMedDetSamme() {
        return aktvAfdOrdnMedDetSamme;
    }

    public String getAktiveringsNote() {
        return aktiveringsNote;
    }

    public void setDatoFoersteAfd(String datoFoersteAfd) {
        this.datoFoersteAfd = datoFoersteAfd;
    }

    public void setAktvAfdOrdnMedDetSamme(boolean aktvAfdOrdnMedDetSamme) {
        this.aktvAfdOrdnMedDetSamme = aktvAfdOrdnMedDetSamme;
    }

    public void setAktiveringsNote(String aktiveringsNote) {
        this.aktiveringsNote = aktiveringsNote;
    }
}
