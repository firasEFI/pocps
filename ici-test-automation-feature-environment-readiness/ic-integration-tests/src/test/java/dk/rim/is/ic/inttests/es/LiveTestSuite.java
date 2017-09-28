package dk.rim.is.ic.inttests.es;

import dk.rim.is.ic.inttests.es.cnsnrh.CVRNummerSENummerRelationHentTest;
import dk.rim.is.ic.inttests.es.dmifoh.DMIFordringOverblikHentTest;
import dk.rim.is.ic.inttests.es.evrh.EjerVirksomhedRelationHentTest;
import dk.rim.is.ic.inttests.es.vaelrsh.VirksomhedAlleEjerLederRelationSamplingHentTest;
import dk.rim.is.ic.inttests.es.vbfkh.VirksomhedBrancheForholdKlassifikationHentTest;
import dk.rim.is.ic.inttests.es.vkosh.VirksomhedKontaktOplysningSamlingHentTest;
import dk.rim.is.ic.inttests.es.vs.VirksomhedSoegTest;
import dk.rim.is.ic.inttests.es.vsosh.VirksomhedStamOplysningSamlingHentTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//TODO this is just a copy of IntegrationTestSuite in order to execute by jenkins System Integration Tests
//TODO it needs to adjust later
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DMIFordringOverblikHentTest.class,
        VirksomhedStamOplysningSamlingHentTest.class,
        VirksomhedBrancheForholdKlassifikationHentTest.class,
        VirksomhedKontaktOplysningSamlingHentTest.class,
        VirksomhedSoegTest.class,
        CVRNummerSENummerRelationHentTest.class,
        EjerVirksomhedRelationHentTest.class,
        VirksomhedAlleEjerLederRelationSamplingHentTest.class
})
public class LiveTestSuite {
}
