package findus_testobjects;

import findus_datamodels.SkyldnerModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_PartsoplysningerSubPage;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;

/**
 * Created by nielsjes on 21-08-2017.
 */
public class IU_213_FremsoegSkyldnerFra360gradersSoegning {

    private BasePsrmPage page;

    public IU_213_FremsoegSkyldnerFra360gradersSoegning(BasePsrmPage page) {
        this.page = page;
    }

    public _360GradersOverblik_PartsoplysningerSubPage execute(SkyldnerModel skyldner) {
        SkyldnereOgFordringsHavereSearchArgs searchArgs = SkyldnereOgFordringsHavereSearchArgs.createSearchArgsForIdFromSkyldner(skyldner);

        return page.menu()
                .a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(searchArgs)
                .activateSoeg()
                .selectFirstSearchResultNavn()
                .activatePartsoplysninger();


    }
}
