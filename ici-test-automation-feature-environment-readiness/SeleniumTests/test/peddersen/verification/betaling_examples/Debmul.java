package peddersen.verification.betaling_examples;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.testng.annotations.Test;

import icisel.testng.TestContext;
import modules.MO_Indbetaling;
import modules.MO_Udbetaling;
import utils.PropertyProviderImpl;
import utils.betaling.BetalingsfilType;

public class Debmul extends TestContext {

    @Test
    public void test() throws ParseException, IOException {
        setPropertyProvider(new PropertyProviderImpl(this));

        // Variables
        double paymentAmount = 1000;
        String ocrLine = null;
        String justeringsID = "856523221545";

        BetalingsfilType udbetalingsType = BetalingsfilType.DEBMUL_STANDARD;
        BetalingsfilType finstaType = BetalingsfilType.FINSTA_DEBMUL;

        // Udbetalinger
        MO_Udbetaling.opretUdbetaling(udbetalingsType, paymentAmount, new Date(), ocrLine, justeringsID, this);

        // Finsta Nets
        MO_Indbetaling.opretIndbetaling(finstaType, paymentAmount, new Date(), ocrLine, getPropertyProvider());

    }

}
