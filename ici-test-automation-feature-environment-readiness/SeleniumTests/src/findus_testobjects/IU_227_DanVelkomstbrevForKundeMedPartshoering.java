package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerPaakravssagPage;
import findus_pageobjects.wizards.opret_paakravssag.OpretPaakravssagWizardParm;

/**
 * @author Jakob Rahr Bork Jensen
 *
 */
public class IU_227_DanVelkomstbrevForKundeMedPartshoering {

    private BasePsrmPage page;

    public IU_227_DanVelkomstbrevForKundeMedPartshoering(BasePsrmPage page) {
        this.page = page;
    }

    /**
     * Funktion opretter en påkravssag med de relevante fordringer der måtte vælges tilknyttet
     * 
     * @param parm = Set påkravssagsværdier forud for kørsel af testobjektet
     * @return SagsbehandlingsskridtPage
     */
    public Sagsbehandlingsskridt_PrimaerPaakravssagPage execute(OpretPaakravssagWizardParm parm) {

        page
                .kontoKontekstMenu()
                .opretPaakravssag()
                .fillForm(parm)
                .activateOpret();

        return new Sagsbehandlingsskridt_PrimaerPaakravssagPage();
    }
}
