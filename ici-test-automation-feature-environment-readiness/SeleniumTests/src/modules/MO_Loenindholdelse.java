package modules;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.pageobjects.frames.Frame;
import icisel.pageobjects.frames.Frames;
import icisel.testng.TestContext;
import icisel.utils.TO_Tools;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_Loenindholdelse;

public class MO_Loenindholdelse {
	/**
	 * Funktion opretter en loenindholdelse.
	 * 
	 * @param testContext
	 *            = this
	 * @param fordringsIDer
	 *            = FordringsIDer på de fordringer der skal inkluderes i
	 *            loenindholdelsen
	 * @param sLoenindholdelsestype
	 *            = "ORDI" el. "SPEC"
	 **/
	public static void opretLoenindholdelse(TestContext testContext, List<String> fordringsIDer,
			String sLoenindholdelsestype) {
		// Gaa til Opret afskrivning i konto kontekst menuen
		klikLoenindholdelse(testContext);
		// Vent
		TO_Tools.sleep(3000);
		// Vælg Lønindholdelsestypen
		vaelgLoenindholdelsestype(testContext, sLoenindholdelsestype);

		// Vælg fordringer
		vaelgFordringer(testContext, fordringsIDer);
		// Gem
		klikGem(testContext);
		// Opret
		klikValider(testContext);
		//Aktiver
		//klikAktiver(testContext);
	}

	public static void klikLoenindholdelse(TestContext testContext) {
		// Gaa til Opret afskrivning i konto kontekst menuen
		Frame subMenuFrame = Frames.main;
		MenuNavigator.kontoKontekst().getSubmenu(subMenuFrame, "Opret lønindeholdelsessag");
	}

	public static void vaelgLoenindholdelsestype(TestContext testContext, String sLoenindholdelsestype) {
		PatientWebDriver driver = testContext.getDriver();
		// Vaelg loenindholdelsestype
		Select aSelect1 = new Select(PO_Loenindholdelse.drp_Loenindholdelsestype.getWebElement(driver));
		aSelect1.selectByValue(sLoenindholdelsestype);
	}

	public static void vaelgFordringer(TestContext testContext, List<String> fordringsIDer) {
		for (String fordringsID : fordringsIDer) {
			vaelgFordring(fordringsID, testContext);
		}
	}

	public static void klikGem(TestContext testContext) {
		PatientWebDriver driver = testContext.getDriver();
		PO_Loenindholdelse.btn_Gem(driver).click();
	}

	public static void klikValider(TestContext testContext) {
		PatientWebDriver driver = testContext.getDriver();
		PO_Loenindholdelse.btn_Valider(driver).click();
	}
	
	public static void klikOpretUdenValidering(TestContext testContext) {
		PatientWebDriver driver = testContext.getDriver();
		PO_Loenindholdelse.btn_OpretUdenValidering(driver).click();
	}

	public static void klikAccepterSoftvalidering(TestContext testContext) {
		PatientWebDriver driver = testContext.getDriver();
		PO_Loenindholdelse.btn_AccepterSoftvalidering(driver).click();
	}

	public static void klikOpretSag(TestContext testContext) {
		PatientWebDriver driver = testContext.getDriver();
		PO_Loenindholdelse.btn_OpretSag(driver).click();
	}
	
	public static void klikAktiver(TestContext testContext) {
		PatientWebDriver driver = testContext.getDriver();
		PO_Loenindholdelse.btn_Aktiver(driver).click();
	}
	
	
	public static String tjekStatusSlut(TestContext testContext) {
		PatientWebDriver driver = testContext.getDriver();
		return PO_Loenindholdelse.txt_StatusFoerAktivering(driver);
	}

	// Se MO_OCRLinje.vaelgFordringListe for mulig forbedring
	public static List<WebElement> getAabneOpgaver(TestContext testContext) {
		PatientWebDriver driver = testContext.getDriver();
		List<WebElement> opgaver = PO_Loenindholdelse.txt_AabneOpgaver(driver);
		return opgaver;
	}

	public static List<WebElement> getIkkeBehandledeBetalinger(TestContext testContext) {
		PatientWebDriver driver = testContext.getDriver();
		List<WebElement> betalinger = PO_Loenindholdelse.txt_IkkeBehandledeBetalinger(driver);
		return betalinger;
	}

	 public static String aktiveLoenindholdelser(){
	     try {
	        return PO_Loenindholdelse.txt_AktiveLoenindholdelser().getText();
	     } catch (Exception e)
         {
             return null;
         }
     }

     public static String loenindeholdelsesType(){
         try {
             return PO_Loenindholdelse.txt_LoenindeholdelsesType().getText();
         } catch (Exception e)
         {
             return null;
         }
     }
	 
	/**
	 * Funktion vælger hvilken fordring der skal inkluderes i lønindholdelsen
	 * 
	 * @param fordringsID
	 *            = indsæt fordringsID'et på den ønskede fordring som en string
	 */
	public static void vaelgFordring(String fordringsID, TestContext testContext) {
		PatientWebDriver driver = testContext.getDriver();
		try {
			PO_Loenindholdelse.chk_Fordringer(fordringsID, driver).click();
		} catch (Exception e) {
			System.out.println("Fordring med fordringsID " + fordringsID + " er ikke på skyldner!");
		}
	}
	 
	
	public static List<WebElement> valideringsbrud(TestContext testContext) {
		PatientWebDriver driver = testContext.getDriver();
		List<WebElement> valideringsbrud = PO_Loenindholdelse.txt_Valideringsbrud(driver);
		return valideringsbrud;
	}
	
	public static Boolean tjekOmSagKanOprettes(TestContext testContext) {
		PatientWebDriver driver = testContext.getDriver();
		Boolean kanOprettes = true;
		try {
			System.out.println(PO_Loenindholdelse.btn_AccepterSoftvalideringUdenVent(driver).getAttribute("display"));
		} catch (Exception e) {
			kanOprettes = false;
		}
		return kanOprettes;
	}
}
