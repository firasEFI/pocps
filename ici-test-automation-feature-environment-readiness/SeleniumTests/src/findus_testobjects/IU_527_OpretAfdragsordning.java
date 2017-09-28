package findus_testobjects;

import findus_datamodels.AfskrivningModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.sagsbehandlingsskridt.SagsbehandlingsskridtPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerAfdragsordningPage;
import icisel.taxobjects.Fordring;

public class IU_527_OpretAfdragsordning {

    private BasePsrmPage page;

    public IU_527_OpretAfdragsordning(BasePsrmPage page) {
        this.page = page;
    }

    public Sagsbehandlingsskridt_PrimaerAfdragsordningPage execute(AfskrivningModel afskrivningModel) {
        // FIXME: new Fordring skal være en fordring eller flere knyttet til afskrivningsmodellen.
        return page.kontoKontekstMenu()
                .opretAfskrivning()
                .udfyldFormular(afskrivningModel)
                .tilfoejFordring(new Fordring())
                .tilfoejFordring(new Fordring())
                .AktiverOpretAfskrivning();
    }
}
