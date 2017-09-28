package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerAfdragsordningPage;
import findus_pageobjects.wizards.afdragsordning.AfdragsordningWizardPage;
import findus_pageobjects.wizards.afdragsordning.AfdragsordningWizardParm;

/**
 * 
 * @author mschioeler
 *
 */
public class IU_697_OpretInaktivAfdragsordningUdFraTabeltraek_AlleFordringerVaelges {
    BasePsrmPage startPage;

    public IU_697_OpretInaktivAfdragsordningUdFraTabeltraek_AlleFordringerVaelges(BasePsrmPage startPage) {
        super();
        this.startPage = startPage;
    }

    public Sagsbehandlingsskridt_PrimaerAfdragsordningPage execute(AfdragsordningWizardParm parm) {
        return ((AfdragsordningWizardPage) startPage.kontoKontekstMenu().opretAfdragsordningssag().acceptAlert())
                .activateVaelgAlt().fillForm(parm)
                .activateGem()
                .activateOpretInaktivAfdragsOrdning();
    }
}
