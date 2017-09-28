package peddersen.verification.betaling_examples;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.testng.annotations.Test;

import icisel.testng.TestContext;
import modules.MO_Indbetaling;
import utils.PropertyProviderImpl;
import utils.betaling.BetalingsfilType;

public class Kub_finsta extends TestContext {

    @Test
    public void test() throws ParseException, IOException {
        setPropertyProvider(new PropertyProviderImpl(this));

        // Variables
        double paymentAmount = 4300.61;
        BetalingsfilType finstaType = BetalingsfilType.FINSTA_NETS;

        // Finsta Nets
        MO_Indbetaling.opretIndbetaling(finstaType, paymentAmount, new Date(), null, getPropertyProvider());

    }

}
