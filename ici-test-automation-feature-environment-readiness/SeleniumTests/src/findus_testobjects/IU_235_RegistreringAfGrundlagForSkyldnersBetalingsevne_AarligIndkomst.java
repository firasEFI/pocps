package findus_testobjects;

import findus_datamodels.GrundlagForBetalingsevneAarligModel;
import findus_pageobjects.BasePsrmPage;

public class IU_235_RegistreringAfGrundlagForSkyldnersBetalingsevne_AarligIndkomst {

    private BasePsrmPage page;

    public IU_235_RegistreringAfGrundlagForSkyldnersBetalingsevne_AarligIndkomst(BasePsrmPage page) {
        this.page = page;
    }

    /**
     * Funktion opretter grundlag for betalingsevne ud fra en årlig indkomst
     * 
     * @param grundlag = Set grundlagsværdier forud for kørsel af testobjektet
     * @return BasePsrmPage
     */
    public BasePsrmPage execute(GrundlagForBetalingsevneAarligModel grundlag) {

        page
                .partKontekstMenu()
                .tilfoejBetalingsevne()
                .continueWithGrundlagForBetalingsevne()
                .fillForm(grundlag)
                .activateGem();

        return new BasePsrmPage();
    }
}
