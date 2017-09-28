package findus.T6Flows;

import findus_datamodelWarehouses.PsrmUsersWarehouse;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;
import org.testng.annotations.Test;

/**
 * Created by nielsjes on 01-09-2017.
 */
public class ATEST_27 {
    private BasePsrmPage step1Page;

    @Test
    public void ATEST_27_step1(){
        // Step 1: Log ind i PSRM som betalingssagsbehandler
        step1Page = new LoginPage().LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler()); //FIXME betalingssagsbehandler

    }

    @Test(dependsOnMethods = {"ATEST_27_step1"})
    public void ATEST_27_step2(){
        // Step 2: TODO Indlæs dagens FINSTA filer
    }

    @Test(dependsOnMethods = {"ATEST_27_step2"})
    public void ATEST_27_step3(){
        // Step 3: TODO Træk rapport for Kontospecifikationer for kontis for dagens dato.
    }
}
