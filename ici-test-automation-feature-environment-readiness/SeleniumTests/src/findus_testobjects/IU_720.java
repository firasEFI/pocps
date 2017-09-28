package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.Brevoplysninger_soeg.BrevoplysningerSearchArgs;
import findus_pageobjects.Brevoplysninger_soeg.BrevoplysningerSoeg_PrimaerSubPage;
import findus_pageobjects.brevoplysninger.Brevoplysninger_PrimaerSubPage;

public class IU_720 {

    private final BasePsrmPage startPage;

    public IU_720(BasePsrmPage startPage) {
        this.startPage = startPage;
    }

    public Brevoplysninger_PrimaerSubPage execute(BrevoplysningerSearchArgs searchArgs) {
        this.startPage
                .menu().opgavestyring().breve().soeg();

        BrevoplysningerSoeg_PrimaerSubPage brevoplysningerSoeg_primaerSubPage = new BrevoplysningerSoeg_PrimaerSubPage();

        return brevoplysningerSoeg_primaerSubPage.fillForm(searchArgs)
                .activateSoeg()
                .activateGaaTilBrevoplysninger(0);
    }
}
