package peddersen.verification.retursvar_examples;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.Test;

import icisel.testng.TestContext;
import modules.MO_Retursvar;
import utils.PropertyProviderImpl;
import utils.retursvar.RetursvarType;

public class Retursvar_Retursvar7_Test extends TestContext {

    @Test
    public void test() throws ParseException, IOException {
        setPropertyProvider(new PropertyProviderImpl(this));

        // Variables
        double paymentAmount = 1000;
        //Customer ID can be either CPR (10 digits) or CVR (8 digits)
        String customerID = "0505831527";
        String justeringsID = "501910319905";
        String messageID = "D_2017_09_14_15_58_13.xml";
        RetursvarType svar2_acpt = RetursvarType.RETURSVAR2_ACCEPT;
        RetursvarType svar7_acpt = RetursvarType.RETURSVAR7;
        RetursvarType kvittering = RetursvarType.KVITTERING;
        
        MO_Retursvar.opretRetursvar(kvittering, paymentAmount, customerID, justeringsID, messageID, this);
        MO_Retursvar.opretRetursvar(svar2_acpt, paymentAmount, customerID, justeringsID, messageID, this);
        MO_Retursvar.opretRetursvar(svar7_acpt, paymentAmount, customerID, justeringsID, messageID, this);

    }

}
