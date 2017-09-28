package peddersen.verification.retursvar_examples;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.Test;

import icisel.testng.TestContext;
import modules.MO_Retursvar;
import utils.PropertyProviderImpl;
import utils.retursvar.RetursvarType;

public class Retursvar_Retursvar9_Test extends TestContext {

    @Test
    public void test() throws ParseException, IOException {
        setPropertyProvider(new PropertyProviderImpl(this));

        // Variables
        double paymentAmount = 1000;
        //Customer ID can be either CPR (10 digits) or CVR (8 digits)
        String customerID = "0505831519";
        String justeringsID = "950223350956";
        String messageID = "D_2017_09_14_16_33_45.xml";
        RetursvarType svar9_acpt = RetursvarType.RETURSVAR9;
//        RetursvarType svar2_acpt = RetursvarType.RETURSVAR2_ACCEPT;
        
//        MO_Retursvar.opretRetursvar(svar2_acpt, paymentAmount, customerID, justeringsID, messageID, this);
        MO_Retursvar.opretRetursvar(svar9_acpt, paymentAmount, customerID, justeringsID, messageID, this);

    }

}
