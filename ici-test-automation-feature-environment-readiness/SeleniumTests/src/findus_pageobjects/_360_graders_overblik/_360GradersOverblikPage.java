package findus_pageobjects._360_graders_overblik;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.Tab;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import findus_testobjects.IU_598;
import findus_testobjects.IU_661_FremsoegSystemdatoViaSkyldnersKontoFane;
import icisel.pageobjects.frames.Frames;

public class _360GradersOverblikPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("360 graders overblik", 5);

    final Tab partsoplysningerTab = new Tab(Frames.tabMenu, "Partsoplysninger");
    final Tab fordringerTab = new Tab(Frames.tabMenu, "Fordringer");
    final Tab sagsbehandlingTab = new Tab(Frames.tabMenu, "Sagsbehandling");
    final Tab kontoTab = new Tab(Frames.tabMenu, "Konto");

    public _360GradersOverblikPage(){
        super(synchronizer);
    }

    public _360GradersOverblik_PartsoplysningerSubPage activatePartsoplysninger()
    {
        partsoplysningerTab.ensureSelected();

        return new _360GradersOverblik_PartsoplysningerSubPage(this);
    }

    public _360GradersOverblik_FordringerSubPage activateFordringer()
    {
        fordringerTab.ensureSelected();

        return new _360GradersOverblik_FordringerSubPage(this);
    }

    public _360GradersOverblik_SagsbehandlingSubPage activateSagsbehandling()
    {
        sagsbehandlingTab.ensureSelected();

        return new _360GradersOverblik_SagsbehandlingSubPage(this);
    }

    public _360GradersOverblik_KontoSubPage activateKonto()
    {
        kontoTab.ensureSelected();

        return new _360GradersOverblik_KontoSubPage(this);
    }
    // endregion

    public IU_598 iu_598_findIndbetalingPaaSkylderenPaa360GradersOverblikFanenSagsbehandling = new IU_598(this);
    
    public IU_661_FremsoegSystemdatoViaSkyldnersKontoFane iu_661_fremsoegSystemdatoViaSkyldnersKontoFane = new IU_661_FremsoegSystemdatoViaSkyldnersKontoFane(this);

}

