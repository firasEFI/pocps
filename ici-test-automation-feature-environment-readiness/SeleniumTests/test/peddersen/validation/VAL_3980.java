package peddersen.validation;

import org.testng.annotations.Test;

import findus_controllers.ApplicationController;
import findus_datamodelWarehouses.RoleMapWarehouse;
import findus_datamodelWarehouses.SkyldnerWarehouse;
import findus_datamodels.SkyldnerModel;
import findus_pageobjects.wizards.afdragsordning.AfdragsordningWizardParm;
import findus_pageobjects.wizards.afdragsordning.AfdragsordningWizardParm.AdresseType;
import findus_pageobjects.wizards.afdragsordning.AfdragsordningWizardParm.VaelgAfdOrdnType;
import icisel.testng.TestContext;
import utils.PropertyProviderImpl;

public class VAL_3980 extends TestContext {
    SkyldnerModel skyldner = SkyldnerWarehouse.skyldner_Val3780();
    AfdragsordningWizardParm parm = new AfdragsordningWizardParm();

    @Test
    public void dummy() {
        setPropertyProvider(new PropertyProviderImpl());

        parm.setVaelgAfdOrdnType(VaelgAfdOrdnType.PAL);
        parm.setAdresseType(AdresseType.DBD);

        new ApplicationController(getPropertyProvider())
                .startAtLoginSSO()
                .login(RoleMapWarehouse.SAGSBEHANDLER_GENEREL)
                .iu_213_fremsoegSkyldner.execute(skyldner);
                //.iu_697_OpretInaktivAfdragsordningUdFraTabeltræk_AlleFordringerVaelges.execute(parm);

    }

}
