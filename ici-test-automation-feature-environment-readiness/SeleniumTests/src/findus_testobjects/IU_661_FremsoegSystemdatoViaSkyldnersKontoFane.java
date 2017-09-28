package findus_testobjects;

import findus_pageobjects._360_graders_overblik._360GradersOverblikPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_KontoSubPage;

public class IU_661_FremsoegSystemdatoViaSkyldnersKontoFane {

    private _360GradersOverblikPage page;

    public IU_661_FremsoegSystemdatoViaSkyldnersKontoFane(_360GradersOverblikPage page) {
        this.page = page;
    }

    /**
     * Funktion går fra skyldners 360 graders overblik til konto-fanen
     * 
     * Afvigelse fra IU: 
     * Dette testobjekt henter ikke systemdatoen ud af den fremkomne side. Til at gøre dette anvendes 
     * metoden "getPsrmDate" på _360GradersOverblik_KontoSubPage via
     * [fremkommen side].getPsrmDate();
     * 
     * @return _360GradersOverblik_KontoSubPage
     */
    public _360GradersOverblik_KontoSubPage execute() {

       return page
                .activateKonto();
    }
}
