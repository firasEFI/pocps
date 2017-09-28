package dk.rim.is.ic.inttests.nets;

import dk.rim.is.ic.inttests.nets.dmibtlm.INDBetalingsoplysningerTraekListeModtagTest;
import dk.rim.is.ic.inttests.nets.dmibtls.INDBetalingsanmodningerTraekListeSendServiceTest;
import dk.rim.is.ic.inttests.nets.dmiism.INDBetalingskortStatusModtagServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//TODO this is just a copy of IntegrationTestSuite in order to execute by jenkins System Integration Tests
//TODO it needs to adjust later
@RunWith(Suite.class)
@Suite.SuiteClasses({
        INDBetalingsoplysningerTraekListeModtagTest.class,
        INDBetalingsanmodningerTraekListeSendServiceTest.class,
        INDBetalingskortStatusModtagServiceTest.class
})
public class MockTestSuite {
}
