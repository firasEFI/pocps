package modules;

import org.openqa.selenium.support.ui.Select;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.testng.TestContext;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_SoegFordringsformular;

public class MO_SoegFordringsformular {

	public static void findVenteTabel(TestContext testContext, String modtagelsesdatoStart, String modtagelsesdatoSlut) {
		soegFordring();
		soegEfterModtagelsesdatoTypeStatusArkiveringstype(testContext, modtagelsesdatoStart, modtagelsesdatoSlut, "DK-WAITINGTB");
	}
	
	public static void soegFordring() {
		MenuNavigator.menu().formularer().fordringsformular().soeg();
	}
	
	public static void soegEfterPart(TestContext testContext) {
		soegEfter("C1-TXFRMQ8", testContext);
	}
	
	public static void soegEfterModtagelsesdatoTypeStatusArkiveringstype(TestContext testContext, 
			String modtagelsesdatoStart, String modtagelsesdatoSlut, String formulartype) {
		soegEfter("C1-TXFRMQ1", testContext);
		PO_SoegFordringsformular.input_ModtagelsesdatoStart.sendKeys(modtagelsesdatoStart);
		PO_SoegFordringsformular.input_ModtagelsesdatoSlut.sendKeys(modtagelsesdatoSlut);
		PO_SoegFordringsformular.input_Formulartype.sendKeys(formulartype);
		PO_SoegFordringsformular.btn_Soeg.click();
	}
	
	public static void soegEfterAdresse(TestContext testContext) {
		soegEfter("C1-TXFRMQ3", testContext);
	}
	
	public static void soegEfterEksternBatchID(TestContext testContext) {
		soegEfter("C1-TXFRMQ6", testContext);
	}
	
	public static void soegEfterDocumentLocator(TestContext testContext) {
		soegEfter("C1-TXFRMQ7", testContext);
	}
	
	public static void soegEfter(String soegEfter, TestContext testContext) {
		PatientWebDriver driver = testContext.getDriver();
		// Vælg hvad der søges efter 
		Select aSelect1 = new Select(PO_SoegFordringsformular.drp_soegEfter.getWebElement(driver));
		aSelect1.selectByValue(soegEfter);
	}

}
