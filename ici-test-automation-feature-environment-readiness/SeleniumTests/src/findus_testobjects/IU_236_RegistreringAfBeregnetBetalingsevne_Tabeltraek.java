package findus_testobjects;

import findus_datamodels.BeregnetBetalingsevne;
import findus_pageobjects.BasePsrmPage;

public class IU_236_RegistreringAfBeregnetBetalingsevne_Tabeltraek {

    private BasePsrmPage page;

    public IU_236_RegistreringAfBeregnetBetalingsevne_Tabeltraek(BasePsrmPage page) {
        this.page = page;
    }

    /**
     * Funktion opretter beregning af betalingsevne ud fra tabeltræk
     * 
     * @param betalingsevne = Set betalingsevneværdier forud for kørsel af testobjektet
     * @return BasePsrmPage
     */
    public BasePsrmPage execute(BeregnetBetalingsevne betalingsevne) {

        page
                .partKontekstMenu()
                .tilfoejBetalingsevne()
                .continueWithBeregnetBetalingsevne()
                .fillForm(betalingsevne)
                .activateGem();

        return new BasePsrmPage();
    }
}
