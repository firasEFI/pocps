package peddersen.validation.hovedveje;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import icisel.taxobjects.Fordring;
import icisel.taxobjects.Fordringstype;
import icisel.testng.User;
import icisel.testng.UserRole;
import modules.MO_360GradersSoegning;
import modules.MO_Afskriv;
import modules.MO_Afskriv.Afskrivningsaarsag;
import modules.MO_Afskriv.Afskrivningsmulighed;
import modules.MO_Fordring;

@Test(groups = { "included" })
public class H_Afskriv extends Hovedvej {
    private String sKundeNummer = "0505780140";
    private String sBeloeb = "1000,00";

    // fordring har tilfaeldigt FordringsID
    private Fordring fordring = new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, sKundeNummer, sBeloeb);

    @BeforeClass(alwaysRun = true)
    private void setup() {
        doMaximizeWindow();

        doLogin();
        MO_Fordring.opretFordring(this, fordring);
        doLogout();
    }

    @Test(invocationCount = 1)
    public void hovedvej_afskriv() throws Exception {
        this.setActiveUser(new User("GEB", "netcompany-123", UserRole.DEFAULT));

        // Login to PSRM
        doLogin();

        // Slaa kunde op
        MO_360GradersSoegning.soegKundeViaCPR(this, sKundeNummer);


        // Afskriv fordring
        String sagsId = MO_Afskriv.opretAfskrivningOgSendTilGodkendelse(this, Afskrivningsmulighed.PROCENT, 99,
                Afskrivningsaarsag.EFTERGIVELSE, fordring);

        doLogout();

        setActiveUser(new User("IFK", "netcompany-123", UserRole.DEFAULT));
        doLogin();

        MO_Afskriv.godkendAfskrivning(this, sagsId);

        doLogout();

    }

}
