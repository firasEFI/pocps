package findus_testobjects;

import findus_datamodels.SkyldnerModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.wizards.opret_opgave_manuelt.OpretOpgaveManueltWizardPageParm;

/**
 1	Klik på fanen Menu	 	rullemenu vises
 2	Klik på punktet Opgave	 	rullemenu med underpunkter vises
 3	Klik på underpunktet Opret opgave manuelt	 	Billedet Opret opgave vises

 4	Klik på "pil" ud for punktet Opgavekategori	 	rullemenu med underpunkter vises
 5	Vælg underpunktet: Udvælge skyldnere
 6	Klik på "pil" ud for punktet Opgavetype og vælg: Send påkrav	 	Punkterne Prioritet, Beskrivelse, tidligste slutdato og Frist udfyldes automatisk

 7	Klik på "forstørrelsesglas" under punktet Parter/Part	 	Billedet Søg part vises

 8	Indtast cpr.nr. på skyldner under punktet CPR og klik Søg	 	Skyldner fremsøges og feltet Part under Parter udfyldes automatisk

 9	Klik på "pil" ud for punktet Send til og vælg Bruger i feltet	 	Ny felt Tildelt sagsbehandler åbner
 10	Indtast W-nr i feltet Tildelt sagsbehandler og tryk på TAB	 	Den ønskede sagsbehandler ses nu i feltet Tildelt sagsbehandler

 11	Klik på Gem	 	Opgaven oprettes og skærmbilledet lukkes
 */
public class IU_645 {

    BasePsrmPage basePsrmPage;

    public IU_645(BasePsrmPage basePsrmPage) {
        this.basePsrmPage = basePsrmPage;
    }

    public BasePsrmPage execute (OpretOpgaveManueltWizardPageParm opretOpgaveManueltWizardPageParm, SkyldnerModel skyldner) {
        return basePsrmPage
                .menu()
                .opgave()
                .opretOpgaveManuelt()
                .fillForm(opretOpgaveManueltWizardPageParm, skyldner)
                .activateGem();
    }
}
