package modules;

import icisel.testng.TestContext;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.generic.PO_Sagsbehandlingsskridt;
import pageobjects.smoketests.psrm_navigation.Navi;

public class MO_Sagsbehandlingsskridt {

    public static void klikPrimaerFane(TestContext testContext){
        PO_Sagsbehandlingsskridt.btn_PrimaerFane.click();
    }

    public static void klikLogFane(TestContext testContextr){
        PO_Sagsbehandlingsskridt.btn_LogFane.click();
    }

    public static String oprettelsesTidspunkt(){
        return PO_Sagsbehandlingsskridt.txt_DatoOprettet.getText();
    }
    
    public static String status() {
    	return PO_Sagsbehandlingsskridt.txt_Status.getText();
    }


}
