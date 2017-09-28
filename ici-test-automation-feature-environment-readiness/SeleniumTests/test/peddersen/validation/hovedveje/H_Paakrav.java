package peddersen.validation.hovedveje;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import icisel.taxobjects.Fordring;
import icisel.taxobjects.Fordringstype;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;
import modules.MO_360GradersSoegning;
import modules.MO_Fordring;
import modules.MO_Paakrav;
import modules.MO_Paakrav.PaakravstypeOption;
import modules.MO_Paakrav.TypeAfBrevOption;

@Test(groups = { "included" }, invocationCount = 1)
public class H_Paakrav extends Hovedvej {

    // String sKundeNummer = "0505753402";
    String sKundeNummer = "0505753423";
    List<Fordring> fordringer = new ArrayList<>();

    @BeforeMethod
    public void setup() {
        PatientWebDriver driver = Engine.getDriver();
        driver.setTimeout(60000); // plenty of timeout
        doMaximizeWindow();

        doLogin();

        // Just make sure that the list is empty before starting the testcase
        fordringer.clear();

        // create new fordring
        Fordring fordring = new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, sKundeNummer, "240");
        fordring = MO_Fordring.opretFordring(this, fordring);
        fordringer.add(fordring);

        doLogout();
    }

    public void hovedvej_paakrav() throws Exception {
        doLogin();

        String sInddrivelsesskridt = "DEML";
        String sTypeAfBrev = "IND0704AFD01";

        // Slaa kunde op
        MO_360GradersSoegning.soegKundeViaCPR(this, sKundeNummer);

        // Opret paakrav
        String SagsID = MO_Paakrav.opretPaakrav(this, PaakravstypeOption.Påkrav_uden_partshøring, fordringer,
                TypeAfBrevOption.Velkomstbrev_uden_partshøring, true);

    }

}
