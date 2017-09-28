package peddersen.examples;

import java.rmi.UnexpectedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.Test;

import icisel.services.NyMFSOAPClient;
import icisel.services.model.AarsagKode;
import icisel.services.model.FordringAktionStatus;
import icisel.services.model.NedskrivFordring;
import icisel.services.model.NyMFStatus;
import icisel.taxobjects.Fordring;
import icisel.taxobjects.Fordringstype;
import icisel.testng.PropertyProvider;
import utils.PropertyProviderImpl;
import utils.batchJob.BatchJobRunner;

public class TestNyMfSeleniumOpretNedskrivFordring {
    private PropertyProvider propertyProvider = new PropertyProviderImpl();
    private String cprNr = "0505562623";
    private String beloeb = "1000";
    private String oprindeligtBeloeb = "1000";
    private String fordringshaverReference = UUID.randomUUID().toString();
    private String nedskrivVirkningsdato = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    private NyMFStatus statusContext;
    
    
    @Test(groups = "soapSel.opretFordring", invocationCount = 1)
    public void opretFordring() throws ParseException {
        NyMFSOAPClient nyMfClient = new NyMFSOAPClient(propertyProvider);
 
        Fordring fordring = new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, cprNr, beloeb);
        fordring.setsOprindeligtBeloeb(oprindeligtBeloeb);
        fordring.setsFordringshaverReference(fordringshaverReference);
        fordring.setsForfaldsdato("01-03-2016");
        fordring.setsIndarbejdelsesDato("01-03-2016");
        fordring.setsSidsteBetalingsdato("01-03-2016");
        fordring.setsPeriodeFraDato("01-03-2016");
        fordring.setsPeriodeTilDato("31-08-2016");
        fordring.setsHaeftelsesForaeldelsesdato("01-03-2019");
        
        NyMFStatus status = nyMfClient.opret(fordring);
        
        Assert.assertEquals(status.getAktionStatusKode(), FordringAktionStatus.MODTAGET);
        
        // need this to transfer parameters between testcases
        statusContext = status;
    }
    
    
    @Test(groups = "soapSel.sendBatchJobsAfterFordringOpret", dependsOnGroups = "soapSel.opretFordring", invocationCount = 1)
    public void sendBatchJobsAfterFordringOpret() throws UnexpectedException {  
        // Run the batch jobs using Selenium
        BatchJobRunner batchRunner = new BatchJobRunner(propertyProvider);
        batchRunner.runNymfBatchJob();        
    }
    
    
    @Test(groups = "soapSel.kvitteringHentPsrmOprettet", dependsOnGroups = "soapSel.sendBatchJobsAfterFordringOpret", invocationCount = 1)
    public void kvitteringHentPsrmOprettet() {
        NyMFSOAPClient nyMfClient = new NyMFSOAPClient(propertyProvider);
        
        NyMFStatus status = nyMfClient.kvitteringHent(statusContext.getLeveranceId());
   
        Assert.assertEquals(status.getAktionStatusKode(), FordringAktionStatus.MODTAGET);
    }
    
    
    @Test(groups = "soapSel.nedskrivFordring", dependsOnGroups = "soapSel.kvitteringHentPsrmOprettet", invocationCount = 1)
    public void nedskrivFordring() throws ParseException {
        NyMFSOAPClient nyMfClient = new NyMFSOAPClient(propertyProvider);
        
        NedskrivFordring nedskrivFordring = new NedskrivFordring(statusContext.getFordringId(), cprNr, beloeb, nedskrivVirkningsdato, AarsagKode.INDB);
        
        NyMFStatus status = nyMfClient.nedskriv(nedskrivFordring);

        Assert.assertEquals(status.getAktionStatusKode(), FordringAktionStatus.MODTAGET);
        
        // need this to transfer parameters between testcases
        statusContext = status;
    }
    
    
    @Test(groups = "soapSel.sendBatchJobsAfterNedskrivFordring", dependsOnGroups = "soapSel.nedskrivFordring", invocationCount = 1)
    public void sendBatchJobsAfterNedskrivFordring() throws UnexpectedException {  
        // Run the batch jobs using Selenium
        BatchJobRunner batchRunner = new BatchJobRunner(propertyProvider);
        batchRunner.runNymfBatchJob();        
    }
    
    @Test(groups = "soapSel.kvitteringHentPsrmNedskriv", dependsOnGroups = "soapSel.sendBatchJobsAfterNedskrivFordring", invocationCount = 1)
    public void kvitteringHentPsrmNedskriv() {
        NyMFSOAPClient nyMfClient = new NyMFSOAPClient(propertyProvider);
        
        NyMFStatus status = nyMfClient.kvitteringHent(statusContext.getLeveranceId());
   
        Assert.assertEquals(status.getAktionStatusKode(), FordringAktionStatus.MODTAGET);
    }
}

