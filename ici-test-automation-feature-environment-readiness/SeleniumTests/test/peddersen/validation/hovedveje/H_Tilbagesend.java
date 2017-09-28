
package peddersen.validation.hovedveje;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import icisel.taxobjects.Fordring;
import icisel.taxobjects.Fordringstype;
import icisel.utils.driver.Engine;
import modules.MO_360GradersSoegning;
import modules.MO_Navigation360GradersOverblik;
import modules.MO_Tilbagesend;
import modules.MO_Utilities;

@Test(groups = { "included" }, invocationCount = 1)
public class H_Tilbagesend extends Hovedvej {

    String kundeNummer = "0505761677";
    ArrayList<Fordring> fordringer = new ArrayList<Fordring>();

    @BeforeMethod
    public void setup() {

        Engine.getDriver().setTimeout(15000);
        doMaximizeWindow();

        Fordring fordring1 = new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, kundeNummer, "1300");

        fordringer.clear();
        fordringer.add(fordring1);
    }

    public void hovedvej_tilbagesend() {
        // FLOW
        // TODO Når det er muligt at logge ind med bestemt brugerrettighed, da
        // skal denne opdateres.
        // Login som sagsbehandler
        doLogin();

        // Slaa kunde op og map fordringsID'er på kundens fordringer
        List<String> fordringsIDs = MO_360GradersSoegning.soegKundeViaCprOgMapFordringsIDs(this, kundeNummer,
                fordringer);

        List<String> interneFordringsIDs = MO_Utilities.mapInterneFordringer(this, fordringsIDs);

        // Tilbagesend fordring
        MO_Tilbagesend.tilbagesendFordring_Fejl(this, interneFordringsIDs.get(0));

        // Tjek at fordringen er trukket tilbage fra skyldnerens overblik
        MO_Navigation360GradersOverblik.tjekSkyldnersFordringer(this);

    }

}
