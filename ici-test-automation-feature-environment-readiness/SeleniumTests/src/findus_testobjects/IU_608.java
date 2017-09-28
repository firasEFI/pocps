package findus_testobjects;

import findus_datamodels.RapportSearchModel;
import findus_pageobjects.BasePsrmPage;

public class IU_608 {

    BasePsrmPage basePsrmPage;

    public IU_608(BasePsrmPage basePsrmPage) {
        this.basePsrmPage = basePsrmPage;
    }

    public void execute(RapportSearchModel rapportSearchModel) {
        basePsrmPage
                .menu()
                .opgavestyring()
                .rapporter()
                .vaelgCremul_Finsta()
                .udfyldOgVisRapport(rapportSearchModel);
    }
}