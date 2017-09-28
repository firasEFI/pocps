package peddersen.verification.betaling_examples;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.testng.annotations.Test;

import icisel.testng.TestContext;
import modules.MO_Indbetaling;
import utils.PropertyProviderImpl;
import utils.betaling.BetalingsfilType;

public class Cremul_Daekningsloes extends TestContext {

    @Test
    public void test() throws ParseException, IOException {
        setPropertyProvider(new PropertyProviderImpl(this));

        // Variables
        double paymentAmount = 500;
        String ocrLine = "983722834311540";
        BetalingsfilType indbetalingsType = BetalingsfilType.CREMUL_DAEKNINGSLOES;
        BetalingsfilType finstaType = BetalingsfilType.FINSTA_DAEKNINGSLOES;

        // Indbetalinger
        // Cremul
        MO_Indbetaling.opretIndbetaling(indbetalingsType, paymentAmount, new Date(), ocrLine, getPropertyProvider());

        // Finsta SKB
        MO_Indbetaling.opretIndbetaling(finstaType, paymentAmount, new Date(), ocrLine, getPropertyProvider());

    }

}
