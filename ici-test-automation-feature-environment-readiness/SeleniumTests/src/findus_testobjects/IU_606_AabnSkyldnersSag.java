package findus_testobjects;

import org.testng.Assert;

import findus_pageobjects.WebList;
import findus_pageobjects._360_graders_overblik._360GradersOverblikPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_SagsbehandlingSubPage;
import findus_pageobjects.sagsbehandlingsskridt.SagsbehandlingsskridtPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerAfdragsordningPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerPaakravssagPage;
import icisel.pageobjects.frames.Frames;

public class IU_606_AabnSkyldnersSag {

    private _360GradersOverblik_SagsbehandlingSubPage page;
    
    public IU_606_AabnSkyldnersSag(_360GradersOverblik_SagsbehandlingSubPage page) {
        this.page = page;
    }
        
    /**
     * Funktion klikker på en påkravssag under skyldnerens sagsoverblik ud fra kendskab til sagens ID.
     * 
     * @param sagsID = Sagens sags ID under fanen Sags ID
     * @return Sagsbehandlingsskridt_PrimaerPaakravssagPage
     */
    public Sagsbehandlingsskridt_PrimaerPaakravssagPage execute_Paakrav(String sagsID) {
        String sagstype = page.getSagstype(sagsID);
        Assert.assertEquals(sagstype, "Påkrav", "Sagstype er ikke 'Påkrav' som forventet");
        
        page
            .selectSpecificSag(sagsID);

        return new Sagsbehandlingsskridt_PrimaerPaakravssagPage();
    }
    
    /**
     * Funktion klikker på en påkravssag under skyldnerens sagsoverblik ud fra kendskab til sagens ID.
     * 
     * @param sagsID = Sagens sags ID under fanen Sags ID
     * @return Sagsbehandlingsskridt_PrimaerPaakravssagPage
     */
    public Sagsbehandlingsskridt_PrimaerAfdragsordningPage execute_Afdragsordning(String sagsID) {
        String sagstype = page.getSagstype(sagsID);
        Assert.assertEquals(sagstype, "Afdragsordning", "Sagstype er ikke 'Afdragsordning' som forventet");
        
        page
            .selectSpecificSag(sagsID);

        return new Sagsbehandlingsskridt_PrimaerAfdragsordningPage();
    }
}