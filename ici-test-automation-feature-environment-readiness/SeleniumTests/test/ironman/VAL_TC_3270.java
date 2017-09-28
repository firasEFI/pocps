package ironman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import icisel.taxobjects.Fordring;
import icisel.taxobjects.Fordringstype;
import icisel.testng.TestContext;
import icisel.testng.UserRole;
import modules.MO_360GradersSoegning;
import modules.MO_Utilities;
import utils.PropertyProviderImpl;

public class VAL_TC_3270  extends TestContext {
    String kundeNummer = "0505750284";

    @BeforeClass
    private void beforeClass() {
        // set the property provider for the context
        this.setPropertyProvider(new PropertyProviderImpl(this));
    }

    @Test(groups = { "Testcase" })
    public void main()  throws Exception {

        //Fordringer
        Fordring hf1 =  new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, kundeNummer, "1000");
        Fordring hf1_gebyr = new Fordring(Fordringstype.DR_GEBYR_OPKRAEVNING, "240");
        Fordring hf1_rente = new Fordring(Fordringstype.DR_RENTE_OPKRAEVNING, "110");
        hf1.addUnderFordring(hf1_gebyr);
        hf1.addUnderFordring(hf1_rente);
        
        
        Fordring hf2 =  new Fordring(Fordringstype.RIGSPOLITIET_PARKERINGSAFGIFTER, kundeNummer, "1100");
        Fordring hf2_gebyr = new Fordring(Fordringstype.DR_GEBYR_OPKRAEVNING, "120");
        Fordring hf2_rente = new Fordring(Fordringstype.DR_RENTE_OPKRAEVNING, "130");
        hf2.addUnderFordring(hf2_gebyr);
        hf2.addUnderFordring(hf2_rente);

        //Tvangsbøde
        Fordring hf3 = new Fordring(Fordringstype.RIGSPOLITIET_TVANGSBOEDE, kundeNummer, "1200");

        //Arbejdsmarkedsbidrag - TODO: omfattet af udligningsstop
        Fordring hf4 = new Fordring(Fordringstype.KOBRA_ARBEJDSMARKEDSBIDRAG, kundeNummer, "1300");
        
        //Parkeringsafgift - inkluderet i indrivelseskridt af typen afdragsordning (tabel)
        Fordring hf5 = new Fordring(Fordringstype.RIGSPOLITIET_PARKERINGSAFGIFTER, kundeNummer, "1400");
 
        //Parkeringsafgift - forældet fordring
        Fordring hf6 = new Fordring(Fordringstype.RIGSPOLITIET_PARKERINGSAFGIFTER, kundeNummer, "1500");
        hf6.setsHaeftelsesForaeldelsesdato("01082017");


        ArrayList<Fordring> fordringer = new ArrayList<Fordring>(
                Arrays.asList(hf1, hf1_gebyr, hf1_rente,
                        hf2, hf2_gebyr, hf2_rente,
                        hf3, hf4, hf5, hf6));

        // Step 1: Log ind som sagsbehandler
        getActiveUser().setUserRole(UserRole.SAGSBEHANDLER);
        doLogin();
        System.out.println("Logget ind");

        // Slaa kunde op og map fordringsID'er på kundens fordringer
        List<String> fordringsIDs = MO_360GradersSoegning.soegKundeViaCprOgMapFordringsIDs(this, kundeNummer,
                fordringer);

        List<String> interneFordringsIDs = MO_Utilities.mapInterneFordringer(this, fordringsIDs);
        
        Assert.assertTrue(interneFordringsIDs.size() > 0);
    }
}
