package peddersen.verification.betaling_examples;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.testng.annotations.Test;

import icisel.testng.TestContext;
import modules.MO_Indbetaling;
import utils.PropertyProviderImpl;
import utils.betaling.BetalingsfilType;

public class M602_BetalingService extends TestContext {

    @Test
    public void test() throws ParseException, IOException {
        setPropertyProvider(new PropertyProviderImpl(this));

        // Variables
        double paymentAmount = 2000;
        String ocrLine = "065863714900258";
        BetalingsfilType indbetalingsType = BetalingsfilType.M602_BETALINGSSERVICE;
        BetalingsfilType finstaType = BetalingsfilType.FINSTA_NETS;

        // Indbetalinger
        // M602 Betalingsservice
        MO_Indbetaling.opretIndbetaling(indbetalingsType, paymentAmount, new Date(), ocrLine, getPropertyProvider());

        // Finsta Nets
        MO_Indbetaling.opretIndbetaling(finstaType, paymentAmount, new Date(), ocrLine, getPropertyProvider());
    }

}
