package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_KontoSubPage;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;

public class IU_573 {

    BasePsrmPage basePsrmPage;

    public IU_573(BasePsrmPage basePsrmPage) {
        this.basePsrmPage = basePsrmPage;
    }

    public _360GradersOverblik_KontoSubPage execute(SkyldnereOgFordringsHavereSearchArgs skyldnerSearchArgs) {
        return basePsrmPage
                .menu()
                .a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(skyldnerSearchArgs)
                .activateSoeg()
                .selectKonto(skyldnerSearchArgs);
    }

}