package findus_testobjects;

import findus_pageobjects._360_graders_soegning._360GradersSoegningPage;

/**
 * Created by nielsjes on 21-08-2017.
 */
public class IU_214_AabnSkyldnersAfdragsordningTilRedigering {
    private _360GradersSoegningPage page;

    public IU_214_AabnSkyldnersAfdragsordningTilRedigering(_360GradersSoegningPage page) { this.page = page; }


    public _360GradersSoegningPage execute()
    {
        // Det vil være en metode på et pageobject som returnere denne page
        // step1: Vælg fanen Sagsbehandling
        //page.activateSagsbehandling();
        // step2: Vælg afdragsordningen under sager
        // step3: Tryk på knappen Rediger
        // FIXME correct return type


        return new _360GradersSoegningPage();
    }

}
