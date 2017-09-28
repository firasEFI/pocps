package findus.T6Flows;

import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import org.testng.annotations.Test;

/**
 * Created by nielsjes on 01-09-2017.
 */
public class ATEST_28 {

    private BasePsrmPage step1Page;

    @Test
    public void ATEST_28_step1(){
        // Step 1: Log ind i PSRM som betalingssagsbehandler
        step1Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler()); //FIXME betalingssagsbehandler

    }
}
