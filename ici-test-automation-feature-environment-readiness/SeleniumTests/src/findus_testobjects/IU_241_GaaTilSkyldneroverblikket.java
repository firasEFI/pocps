package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblikPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_KontoSubPage;
import findus_pageobjects.batchjobindsendelse.BatchjobindsendelsePage;

public class IU_241_GaaTilSkyldneroverblikket {

    private BasePsrmPage page;

    public IU_241_GaaTilSkyldneroverblikket(BasePsrmPage page) {
        this.page = page;
    }

    /**
     * Funktion går via konto kontekst menu ind på skyldners 360 graders overblik under fordringer-fanen
     * 
     * @return _360GradersOverblikPage
     */
    public _360GradersOverblikPage execute() {

       page
                .kontoKontekstMenu()
                .gaaTil360GradersOverblik();
       
       return new _360GradersOverblikPage();
    }
}
