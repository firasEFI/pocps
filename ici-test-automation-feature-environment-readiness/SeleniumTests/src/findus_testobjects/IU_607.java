package findus_testobjects;

import findus_datamodels.RapportSearchModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.rapporthistorik.RapporthistorikPage;

public class IU_607 {

    BasePsrmPage basePsrmPage;

    public IU_607(BasePsrmPage basePsrmPage) {
        this.basePsrmPage = basePsrmPage;
    }

    public RapporthistorikPage execute(RapportSearchModel rapportSearchModel) {
        basePsrmPage
            .menu()
            .opgavestyring()
            .rapporter()
            .vaelgBalanceOfStatement()
            .udfyldOgVisRapport(rapportSearchModel);
        return new RapporthistorikPage();
    }
}