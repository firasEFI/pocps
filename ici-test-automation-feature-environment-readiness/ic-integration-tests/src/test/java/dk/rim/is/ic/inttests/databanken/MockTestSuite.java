package dk.rim.is.ic.inttests.databanken;

/**
 * Created by niar on 21-09-2017.
 */
import dk.rim.is.ic.inttests.databanken.atr.AnnualTaxReturnTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AnnualTaxReturnTest.class,
        DataBankUploadCprListFileTest.class
})
public class MockTestSuite {
}
