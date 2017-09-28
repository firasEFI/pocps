package ironman;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import icisel.taxobjects.Fordring;
import icisel.taxobjects.Medielicens;
import icisel.taxobjects.Parkeringsafgift;
import icisel.testng.TestContext;
import icisel.testng.UserRole;
import modules.MO_360GradersSoegning;
import modules.MO_Loenindholdelse;
import utils.PropertyProviderImpl;

public class VAL_TC_3857 extends TestContext {
	
	String cpr = "0505754417";
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	private void beforeClass() {
		// set the property provider for the context
		this.setPropertyProvider(new PropertyProviderImpl(this));
		getActiveUser().setUserRole(UserRole.SAGSBEHANDLER);
	}
	
	@Test(groups = { "Testcase" })
	public void main() throws Exception {
		
		Fordring hf1 = new Medielicens(cpr, "30");
		Fordring hf2 = new Parkeringsafgift(cpr, "30");
		ArrayList<Fordring> fordringer = new ArrayList<Fordring>();
		fordringer.add(hf1);
		fordringer.add(hf2);
		
		//Step 1
		doLogin();
		
		//Step 2
		List<String> fordringsID = MO_360GradersSoegning.soegKundeViaCprOgMapFordringsIDs(this, cpr, fordringer);

		//Step 3
		MO_Loenindholdelse.klikLoenindholdelse(this);
		MO_Loenindholdelse.vaelgLoenindholdelsestype(this, "ORDI");
		
		//Step 4
		MO_Loenindholdelse.vaelgFordringer(this, fordringsID);
		MO_Loenindholdelse.klikGem(this);
		MO_Loenindholdelse.klikValider(this);
		
		//Step 5
		List<WebElement> valideringsbrud = MO_Loenindholdelse.valideringsbrud(this);
		String valideringsbrud1 = valideringsbrud.get(0).getText();
		softAssert.assertEquals(valideringsbrud1, "The sum of claims are below the threshold H", "Der vises ikke hvorfor lønindholdelse ikke kan oprettes");
		softAssert.assertFalse(MO_Loenindholdelse.tjekOmSagKanOprettes(this), "Lønindholdelse afvises ikke, selvom summen af fordringerne er under 100DKK");

		//Step 6
		doLogout();
		softAssert.assertAll();
	}
}
