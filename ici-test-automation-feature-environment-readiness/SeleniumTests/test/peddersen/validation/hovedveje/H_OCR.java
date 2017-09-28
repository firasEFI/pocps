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
import modules.MO_Navigation360GradersOverblik;
import modules.MO_OcrLinje;
import modules.MO_Utilities;

@Test(groups = { "included" }, invocationCount = 1)
public class H_OCR extends Hovedvej {

    String kundeNummer = "0505794702";
    ArrayList<Fordring> fordringer = new ArrayList<Fordring>();

    @BeforeMethod
    public void setup() {
        Engine.getDriver().setTimeout(15000);
        doMaximizeWindow();

        Fordring fordring1 = new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, kundeNummer, "999,99");
        Fordring fordring2 = new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, kundeNummer, "2500");

        fordringer.clear();
        fordringer.add(fordring1);
        fordringer.add(fordring2);
    }

    public void hovedvej_ocr() throws Exception {
        // FLOW
        // TODO Når det er muligt at logge ind med bestemt brugerrettighed, da
        // skal denne opdateres.
        // Login som sagsbehandler
        doLogin();

        // Hent PSRMs dato
        String psrmDato = MO_Utilities.getPsrmDateTime(this)[0];

        // Slaa kunde op og map fordringsID'er på kundens fordringer
        List<String> fordringsIDs = MO_360GradersSoegning.soegKundeViaCprOgMapFordringsIDs(this, kundeNummer,
                fordringer);

        // Opret OCR linje 1 paa skyldners fordring 1
        String[] fordringsListe = { fordringsIDs.get(0) };
        String OCRNummer1 = MO_OcrLinje.OpretOcrFordring(this, psrmDato, fordringsListe, 0);
        Assert.assertNotNull(OCRNummer1);

        // Tjek at OCR linje fremgaar af skyldners sager
        MO_Navigation360GradersOverblik.TjekSkyldnersOcrLinjer(this);

        // Opret OCR linje 2 paa skyldners konto
        String OCRNummer2 = MO_OcrLinje.OpretOcrKonto(this, psrmDato, 200);
        Assert.assertNotNull(OCRNummer2);

        // Tjek at OCR linje fremgaar af skyldners sager
        MO_Navigation360GradersOverblik.TjekSkyldnersOcrLinjer(this);

    }

}
