package findus_testobjects;

import findus_datamodels.RapportSearchModel;
import findus_pageobjects.BasePsrmPage;

public class IU_618 {

    BasePsrmPage basePsrmPage;

    public IU_618(BasePsrmPage basePsrmPage) {
        this.basePsrmPage = basePsrmPage;
    }

    public void execute(RapportSearchModel rapportSearchModel) {
        basePsrmPage
                .menu()
                .opgavestyring()
                .rapporter()
                .vaelgDebmul_Finsta()
                .udfyldOgVisRapport(rapportSearchModel);
    }
}