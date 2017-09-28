package peddersen.validation.PI5F1;

import static findus_datamodelWarehouses.RoleMapWarehouse.SAGSBEHANDLER_GENEREL;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import findus_controllers.ApplicationController;
import findus_datamodels.CprSkyldnerModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_PartsoplysningerSubPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_SagsbehandlingSubPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerAfdragsordningPage;
import icisel.taxobjects.Fordring;
import icisel.testng.TestContext;
import icisel.utils.driver.Engine;
import utils.PropertyProviderImpl;
import utils.batchJob.BatchJobRunner;
import utils.batchJob.BatchJobType;
import utils.timetravel.Timetravel;

public class TC45_VAL4281 extends TestContext {
    ApplicationController applicationController = new ApplicationController(new PropertyProviderImpl());
    String cprNummer = "0505831519";
    String OCRNummer = "698912749854934";
    
    // Fordringsdata
    String HF1_beloeb = "800"; 
//  String GHF1_beloeb = "100";
//  String HF2_beloeb = "1200";

    List<Fordring> fordringer = new ArrayList<>();

    @BeforeMethod
    public void setup() throws ParseException {
        setPropertyProvider(new PropertyProviderImpl(this));
        // OPSÆTNING
        Engine.getDriver().setTimeout(15000);
        doMaximizeWindow();

    }

//Step 1: Timetravel til t+20 eller 21.11.2017 i NyMF 

    	@Test(enabled = true)
        public void TC45_VAL4281_step1() throws IOException {

            Calendar cal = new GregorianCalendar(2017, 11, 21);
            Date date = cal.getTime();
            new Timetravel(new PropertyProviderImpl()).to(new Date());
        }

//Step 2: Batchjobbet C1-PFLM skal køres. Vent til batchjobbet er kørt ferdig.
    	
    	@Test (dependsOnMethods = { "TC45_VAL4281_step1" })
        public void TC45_VAL4281_step2() throws IOException {

    		BatchJobRunner batch  = new BatchJobRunner(this.getPropertyProvider());
    		batch.run(BatchJobType.C1_PFLM_CreateInstallment); 
    		// Dette batchjob er det rigtige: C1_PFLM_CreateInstallment = CreateInstallment, MarkInstallmentAsPaid & ClosePaymentPlanCase 
    	}
    	
//Step 3: 	Batchjobbet DKNB skal køres. Vent til batchjobbet er kørt ferdig.
    	
    	@Test (dependsOnMethods = { "TC45_VAL4281_step2" })
        public void TC45_VAL4281_step3() throws IOException {

    		//BatchJobRunner batch  = new BatchJobRunner(this.getPropertyProvider());
    		//batch.run(BatchJobType.DKNB);
        }
    	
//Step 4: 	Batchjobbet DKNBR skal køres. Vent til batchjobbet er kørt ferdig.
	
    	@Test (dependsOnMethods = { "TC45_VAL4281_step3" })
		public void TC45_VAL4281_step4() throws IOException {

    		//BatchJobRunner batch  = new BatchJobRunner(this.getPropertyProvider());
    		//batch.run(BatchJobType.DKNBR);
    	}
	
//Step 5: 	Log ind som sagsbehandler
    	BasePsrmPage step5;
    	@Test (dependsOnMethods = { "TC45_VAL4281_step4" })
	    public void TC45_VAL4281_step5() throws IOException {

    		step5 = applicationController.startAtLoginSSO().login(SAGSBEHANDLER_GENEREL);
	    }
	
//Step 6: 	Fremsøg skyldner i 360-grader overblikk 
		
    	_360GradersOverblik_PartsoplysningerSubPage step5A;
    	@Test (dependsOnMethods = { "TC45_VAL4281_step5" })
		public void TC45_VAL4281_step6() throws IOException {

    		CprSkyldnerModel skyldner = new CprSkyldnerModel();
    	    skyldner.setCprNummer(cprNummer);

    		step5A = step5
    	               .iu_213_fremsoegSkyldner.execute(skyldner);
 
		 }

//Step 7: 	Gå ind på skyldnerens sagsoverblik
		_360GradersOverblik_SagsbehandlingSubPage step7;
    	@Test (dependsOnMethods = { "TC45_VAL4281_step6" })
		public void TC45_VAL4281_step7() throws IOException {

			// Log in som sagsbehandler kode
		    step7 = step5A.getParentPage().activateSagsbehandling();
    	}		

//Step 8: 	Gå ind på afdragsordningssagen
    	Sagsbehandlingsskridt_PrimaerAfdragsordningPage step8;
    	@Test (dependsOnMethods = { "TC45_VAL4281_step7" })
		public void TC45_VAL4281_step8() throws IOException {

			step8 = step7.iu_606_aabnSkyldnersSag.execute_Afdragsordning("");
		}

//Step 9: Kontroller, at du kan se følgende informationer om den aktive afdragsordning Sagstype: afdragsordningsag tabeltræk Status: aktiv Tilknytning af fordringer (HF 1 medielicens, GHF1 (gebyr), HF2 og påkravgebyret)

    	@Test (dependsOnMethods = { "TC45_VAL4281_step8" })
		public void TC45_VAL4281_step9() throws IOException {

			// Indsæt assert i koden
		    
		}

//Step 10: Kontroller, at det fremgår, at der er afsendt en opkrævning for det første afdrag i afdragsordningen

    	@Test (dependsOnMethods = { "TC45_VAL4281_step9" })
		public void TC45_VAL4281_step10() throws IOException {

			// Indsæt assert i koden
		    
    	}
				
//Step 11: Du skal kontrollere at opkrævningen alene relaterer sig til det næste afdrag (negativ test)

    	@Test (dependsOnMethods = { "TC45_VAL4281_step10" })
		public void TC45_VAL4281_step11() throws IOException {

			// Indsæt assert i koden
			
    	}

//Step 12: Du skal tilgå det billede, hvor du kan se retursvar fra NETS. Kontroller at retursvaret indeholder påkrævede informationer om opkrævningsfilen.

    	@Test (dependsOnMethods = { "TC45_VAL4281_step11" })
		public void TC45_VAL4281_step12() throws IOException {

			// Tilgå billede hvor man kan se retursvar fra NETS
			
		}

//Step 13: Log ud

    	@Test (dependsOnMethods = { "TC45_VAL4281_step12" })
		public void TC45_VAL4281_step13() throws IOException {

			// Log ud
    		doLogout();
		}

//Step 14: Log ind som systemadministrator/bruger med alle rettigheder

    	@Test (dependsOnMethods = { "TC45_VAL4281_step13" })
		public void TC45_VAL4281_step14() throws IOException {
    		BasePsrmPage step14;
			// Log ind som sys adm.
    		step14 = applicationController.startAtLoginSSO().login(SAGSBEHANDLER_GENEREL); //TODO: Skal ændres til Systemadm
		}
		
//Step 15: Du skal nu igen tilgå til det sted i løsningen, hvor du kan få vist afsendte opkrævningsfiler for afdragsordningen

    	@Test (dependsOnMethods = { "TC45_VAL4281_step14" })
		public void TC45_VAL4281_step15() throws IOException {

			// Gå til hvor afsendte filer vises
			
		}

//Step 16: Du skal kontrollere, at opkrævningsfilen indeholder følgende påkrævede oplysninger: 
		/*
		- CPR-nummer
		- Afdragsbeløb 500 kroner
		- Betalingsdato 19.12.2017
		- Afgørelses-ID
		- Debitorgruppenummer
		- Kundenummer
		- PBS-nummer
		- OCR-linje 
		 */
		
    	@Test (dependsOnMethods = { "TC45_VAL4281_step15" })
		public void TC45_VAL4281_step16() throws IOException {

			// Indsæt assert
			
		}

//Step 17:  Du skal kontrollere, at størrelsen på gælden i opkrævningsfilen svarer til den samlede restgæld på afdragsordningssagen (2240 kroner)
							
    	@Test (dependsOnMethods = { "TC45_VAL4281_step16" })
		public void TC45_VAL4281_step17() throws IOException {

		// Indsæt assert
		
		}
			
//Step 18:  Du skal kontrollere, at opkrævningsfilen indeholder oplysninger om opkrævnings- og/eller forsendelsesadresse. 
			
    	@Test (dependsOnMethods = { "TC45_VAL4281_step17" })
		public void TC45_VAL4281_step18() throws IOException {

		// Indsæt assert
		
		}
			
//Step 19:  Log ud
			
    	@Test (dependsOnMethods = { "TC45_VAL4281_step18" })
		public void TC45_VAL4281_step19() throws IOException {

    	doLogout();
		}			

    }

