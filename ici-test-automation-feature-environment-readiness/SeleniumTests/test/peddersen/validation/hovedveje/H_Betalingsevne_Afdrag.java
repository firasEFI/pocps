package peddersen.validation.hovedveje;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import icisel.taxobjects.Fordring;
import icisel.taxobjects.Fordringstype;
import icisel.utils.driver.Engine;
import modules.MO_360GradersSoegning;
import modules.MO_Afdragsordning;
import modules.MO_Betalingsevne;
import modules.MO_Navigation360GradersOverblik;
import modules.MO_OcrLinje;
import modules.MO_Utilities;

@Test(groups = { "excluded" }, invocationCount = 10)
public class H_Betalingsevne_Afdrag extends Hovedvej {

    // DATA
    String kundeNummer = "0505779967";
    ArrayList<Fordring> fordringer = new ArrayList<Fordring>();

    @BeforeMethod
    public void setup() {
        // OPSÆTNING
        Engine.getDriver().setTimeout(30000);
        doMaximizeWindow();

        Fordring fordring1 = new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, kundeNummer, "1000");
        Fordring fordring2 = new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, kundeNummer, "2000");

        fordringer.clear();
        fordringer.add(fordring1);
        fordringer.add(fordring2);
    }

    @Test(invocationCount = 1)
    public void hovedvej_betalingsevne_afdrag() throws Exception {
        // FLOW
        // TODO Når det er muligt at logge ind med bestemt brugerrettighed, da
        // skal denne opdateres.
        // Login som sagsbehandler
        doLogin();

        // Hent psrmDato
        String psrmDato = MO_Utilities.getPsrmDateTime(this)[0];

        // Slå kunde op og map dennes fordringsID'er. Såfremt en fordring
        // mangler, da oprettes denne.
        List<String> fordringsIDs = MO_360GradersSoegning.soegKundeViaCprOgMapFordringsIDs(this, kundeNummer,
                fordringer);

        // Opret betalingsevne for budget
        MO_Betalingsevne.beregnBetalingsevne_Budget(this, "500", "250", psrmDato);

        // Opret afdragsordning
        String[] fordringsListe = { fordringsIDs.get(1) };
        String sagsID = MO_Afdragsordning.opretAktivAfdragsordning_Budget(this, fordringsListe, "Månedlig",
                "Samme som skyldners kontaktadresse", psrmDato);

        // Tjek at afdragsordningen fremgaar af skyldners sager
        MO_Navigation360GradersOverblik.TjekSkyldnersSager(this, sagsID);

        // Opret OCR linje til afdragsordning
        String OCRNummer1 = MO_OcrLinje.OpretOcrSag(this, psrmDato, sagsID, 0);
        Assert.assertNotNull(OCRNummer1);

        // Tjek at OCR-linjen fremgaar af skyldners sager
        MO_Navigation360GradersOverblik.TjekSkyldnersOcrLinjer(this);

        // Luk afdragsordningssag, så skyldneren ikke er i settlement stop
        MO_Afdragsordning.lukAfdragssordningssag(this, sagsID, "Oprettet");

    }

}