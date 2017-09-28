package peddersen.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import icisel.taxobjects.Fordring;
import icisel.taxobjects.Gebyropkraevning;
import icisel.taxobjects.Medielicens;
import icisel.taxobjects.Parkeringsafgift;
import icisel.taxobjects.Renteopkraevning;
import icisel.testng.TestContext;
import icisel.testng.UserRole;
import icisel.utils.driver.Engine;
import modules.MO_360GradersSoegning;
import modules.MO_Navigation360GradersOverblik;
import modules.MO_Paakrav;
import modules.MO_Paakrav.AndenAktoerOption;
import modules.MO_Paakrav.PaakravstypeOption;
import modules.MO_Paakrav.TypeAfAndenAktoerOption;
import modules.MO_Tilbagesend;
import modules.MO_Utilities;
import peddersen.ScreenshotTakingTestContext;
import utils.PropertyProviderImpl;

public class VAL_170 extends ScreenshotTakingTestContext {

    @BeforeMethod
    public void initialize() {
        setPropertyProvider(new PropertyProviderImpl(this));
    }

    @Test(groups = { "Hovedveje" }, invocationCount = 1)
    public void main() throws Exception {
        // OPSÆTNING
        Engine.getDriver().setTimeout(15000);
        doMaximizeWindow();

        // DATA
        //Skyldner type A
        String cprNummer = "0505750268";
        //Anden aktør cpr
        String cprAndenAktoer = "0505752430";

        //Fordringer
        Fordring hf1 = new Medielicens(cprNummer, "1000");
        Fordring hf2 = new Medielicens(cprNummer, "2000");
        ArrayList<Fordring> fordringer = new ArrayList<Fordring>(Arrays.asList(hf1, hf2));
        

        // FLOW      
        
//Step 1: Log ind som sagsbehandler (generel)
        // TODO Når det er muligt at logge ind med bestemt brugerrettighed, da
        // skal denne opdateres.
        // Login som sagsbehandler (generel)
        getActiveUser().setUserRole(UserRole.SAGSBEHANDLER);
        doLogin();

//Step 2: Undladt ifølge Kammeradvokatens godkendte gennemløb: https://iciskm.atlassian.net/plugins/servlet/ac/com.thed.zephyr.je/general-search-test-executions?project.id=10600&issue.id=29498&execution.id=0001493287768691-242ac112-0001#!
        
//Step 3: Undladt ifølge Kammeradvokatens godkendte gennemløb: https://iciskm.atlassian.net/plugins/servlet/ac/com.thed.zephyr.je/general-search-test-executions?project.id=10600&issue.id=29498&execution.id=0001493287768691-242ac112-0001#!    
        
//Alternativt step: Henter PSRM datoen
        String psrmDato = MO_Utilities.getPsrmDateTime(this)[0];
        
//Alternativt step: Fremsøg skyldneren og dennes fordringer
        // Slaa kunde op og map fordringsID'er på kundens fordringer
        List<String> fordringsIDs = MO_360GradersSoegning.soegKundeViaCprOgMapFordringsIDs(this, cprNummer,
                fordringer);

//Step 4: Gå ind på skærmbilledet for opret sag og opret en sag med sagstypen "påkrav"
//Step 5: Udvælg de fordringer, der skal oprettes en påkravssag på
//Step 6: Tjek at der kan tilknyttes forskellige fordringstyper fra forskellige fordringshavere
//Step 7: Når fordringerne er valgt tjek, at du kan oprette sagen
        
        //Steps 4-7 klares med metodekaldet nedenfor, som går til skærmbilledet for opretning af en
        //påkravssag, hvor fordringerne hf1 og hf2 udvælges. Disse er fra forskellige fordringshavere. 
        //Dernæst oprettes sagen og sagsID for påkravssagen gemmes til senere brug.
        List<Fordring> fordringsListe = new ArrayList<>();
        
        Fordring fordring1 = new Fordring();
        fordring1.setsFordringsID(fordringsIDs.get(0));
        fordringsListe.add(fordring1);
        
        Fordring fordring2 = new Fordring();
        fordring2.setsFordringsID(fordringsIDs.get(1));
        fordringsListe.add(fordring2);
        
        String sagsID = MO_Paakrav.opretPaakravssag(this, PaakravstypeOption.Påkrav_uden_partshøring, fordringsListe);
        
//Step 8: Tilknyt en anden aktør til sagen
        //Der tilknyttes en anden aktør som værende en anden part med CPR nummer af typen "Anden aktør"
//        MO_Paakrav.tilknytAndenAktoer(this, sagsID, TypeAfAndenAktoerOption.Part_med_CPR, cprAndenAktoer, AndenAktoerOption.Anden_aktør);
        
//Step 9: Gå ind på skyldners sagsoverblik
//Step 10: Tjek at du kan se påkravssagen du lige har oprettet
        
        //Steps 9-10 klares med metodekaldet nedenfor, som går ind på skyldners sagsoverblik, 
        //hvor den tjekker at en sag fremgår med det ønskede sags ID
        MO_Navigation360GradersOverblik.TjekSkyldnersSager(this, sagsID);
        
//Step 11: Tjek at du kan se datoen for påkravssagens oprettelse - dvs. d.d. og at det fremgår, at sagstypen er "påkrav"
        //Hent oprettelsesdatoen og tjek at datoen er systemets dags dato:
        MO_Navigation360GradersOverblik.assertSagsOprettelsestidspunkt(sagsID, psrmDato);
        
//Step 12: Tjek at påkravssagen står som den førstnævnte i oversigtsbilledet
        //Tjek at påkravssagen står som nummer 1 i tabellen:
        MO_Navigation360GradersOverblik.assertSagsRaekkefoelge(sagsID, "1");
        
//Step 13: Tjek at du kan klikke på påkravssagen og dermed komme ind på selve sagen
        //Klik på sagen via linket med dens sagsID.
        MO_Navigation360GradersOverblik.klikSagsID(this, sagsID);
        
//Step 14: Tjek at du kan se alle de fordringer, du har tilknyttet påkravssagen og at saldoen for de enkelte fordringer fremgår
        //Tjek at alle forventet tilknyttede fordringer er på påkravssagen og at deres saldo fremgår
//        FOR HVER FORDRING, FIND DEN OG TJEK ENDELSEN, SE METODE I MO PÅKRAV
        
//Step 15: Tjek at den anden aktør du har tilknyttet sagen fremgår med aktøres kontaktoplysninger
        //TODO
        
//Step 16: Log ud
        doLogout();
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
}
