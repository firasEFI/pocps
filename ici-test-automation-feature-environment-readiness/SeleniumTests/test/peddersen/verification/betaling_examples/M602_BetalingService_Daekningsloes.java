package peddersen.verification.betaling_examples;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.testng.annotations.Test;

import icisel.testng.TestContext;
import modules.MO_Indbetaling;
import utils.PropertyProviderImpl;
import utils.betaling.BetalingsfilType;

public class M602_BetalingService_Daekningsloes extends TestContext {

    @Test
    public void test() throws ParseException, IOException {
        setPropertyProvider(new PropertyProviderImpl(this));

        // Variables
        double paymentAmount = 621;
        String ocrLine = "306525030767674";
        BetalingsfilType indbetalingsDaekningsloesType = BetalingsfilType.FINSTA_DAEKNINGSLOES;
        BetalingsfilType indbetalingsType = BetalingsfilType.M602_TILBAGEFOERT_BETALING_FRA_INDBETALINGSKORT;
        BetalingsfilType finstaType = BetalingsfilType.FINSTA_NETS;

        // Indbetalinger
        // Finsta Dækningsløs
        MO_Indbetaling.opretIndbetaling(indbetalingsDaekningsloesType, paymentAmount, new Date(), ocrLine,
                getPropertyProvider());

        // M602 Tilbageført betaling fra indbetalingskort
        MO_Indbetaling.opretIndbetaling(indbetalingsType, paymentAmount, new Date(), ocrLine, getPropertyProvider());

        // Finsta Nets
        MO_Indbetaling.opretIndbetaling(finstaType, paymentAmount, new Date(), ocrLine, getPropertyProvider());
    }

}
