package peddersen.examples;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import icisel.taxobjects.Fordring;
import icisel.taxobjects.Fordringstype;
import icisel.utils.driver.Engine;
import modules.MO_360GradersSoegning;
import modules.MO_Fordring;
import peddersen.testconfig.PeddersenTestContext;
import utils.PropertyProviderImpl;

public class fremsoeg_kunde extends PeddersenTestContext {

    int cprNum = 1234657810;

    @BeforeMethod
    public void opretFordring() {
        setPropertyProvider(new PropertyProviderImpl(this));
        String cpr = Integer.toString(cprNum);
        Engine.getDriver().setTimeout(15000);
        boolean fordringOprettet = false;
        if (!fordringOprettet) {
            doLogin();

            Fordring fordring = new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, cpr, "240");
            MO_Fordring.opretFordring(this, fordring);

            doLogout();
        }

    }

    @Test(groups = { "Testcase" }, invocationCount = 1)
    public void main() throws Exception {
        String cpr = Integer.toString(cprNum);
        doLogin();

        String sKundeNummer = cpr;
        // String sIdType = "CPR";
        String sInddrivelsesskridt = "DEML";
        String sTypeAfBrev = "IND0704AFD01";
        String sFordringsID = "0";
        // String sIndbetalingskildeType = "ADHC";
        // String sIndbetalingskontrolStatus = "10";
        // String sBeloeb = "750";
        // String sDaekningsregel = "DK-PAYOCR";
        // String sInkluder = "CASE";

        // Slaa kunde op
        MO_360GradersSoegning.soegKundeViaCPR(this, sKundeNummer);

        // cprNum++;
    }

    @AfterMethod
    public void close() {
        Engine.closeDriver();
    }

}
