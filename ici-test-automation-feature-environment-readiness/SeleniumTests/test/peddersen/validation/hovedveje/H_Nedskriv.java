package peddersen.validation.hovedveje;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import icisel.taxobjects.Fordring;
import icisel.taxobjects.Fordringstype;
import icisel.utils.driver.Engine;
import icisel.utils.driver.RetryAnalyzer;
import modules.MO_360GradersSoegning;
import modules.MO_Navigation360GradersOverblik;
import modules.MO_Nedskriv;
import modules.MO_Utilities;
import utils.SkippedTestCaseException;

@Test(groups = { "included" }, invocationCount = 1)
public class H_Nedskriv extends Hovedvej {

    String kundeNummer = "0505606000";
    List<Fordring> fordringer = new ArrayList<>();

    @BeforeMethod
    public void setup() {
        Engine.getDriver().setTimeout(15000);
        doMaximizeWindow();

        Fordring fordring = new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, kundeNummer, "20000");
        fordringer.clear();
        fordringer.add(fordring);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void hovedvej_nedskriv() throws Exception {
        // FLOW
        // TODO Når det er muligt at logge ind med bestemt brugerrettighed, da
        // skal denne opdateres.
        // Login som sagsbehandler
        doLogin();

        // Hent PSRM datoen
        String psrmDato = MO_Utilities.getPsrmDateTime(this)[0];

        // Slaa kunde op og map fordringsID'er på kundens fordringer
        List<String> fordringsIDs = MO_360GradersSoegning.soegKundeViaCprOgMapFordringsIDs(this, kundeNummer,
                fordringer);
        if (fordringsIDs.isEmpty()) {
            throw new SkippedTestCaseException(
                    "Alle matchende fordringer er allerede afskrevet på denne bruger - skipper testcase: "
                            + this.getTestCaseName());
        }

        // Map interne fordringsID'er til brug i nedskrivningen
        List<String> interneFordringsIDs = MO_Utilities.mapInterneFordringer(this, fordringsIDs);

        // Nedskriv fordring
        MO_Nedskriv.nedskrivFordring_Modregning(this, psrmDato, 0, interneFordringsIDs.get(0),
                interneFordringsIDs.get(0), "200");

        // Tjek skyldners fordringer
        MO_Navigation360GradersOverblik.tjekSkyldnersFordringer(this);

    }

}
