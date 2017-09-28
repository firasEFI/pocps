package ironman;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import icisel.taxobjects.*;
import icisel.testng.TestContext;
import modules.MO_360GradersSoegning;
import modules.MO_Loenindholdelse;
import utils.PropertyProviderImpl;

public class VAL_TC_3614 extends TestContext{

	String cpr = "0505879384";
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	private void beforeClass() {
		// set the property provider for the context
		this.setPropertyProvider(new PropertyProviderImpl(this));
	}
	
	@Test(groups = { "Testcase" })
	public void main() throws Exception {
		
		Fordring hf = new Medielicens(cpr, "500");
		ArrayList<Fordring> fordringer = new ArrayList<Fordring>();
		fordringer.add(hf);
		
		//Step 1
		doLogin();
		
		//Step 2
		List<String> fordringsID = MO_360GradersSoegning.soegKundeViaCprOgMapFordringsIDs(this, cpr, fordringer);

		//Step 3
		MO_Loenindholdelse.klikLoenindholdelse(this);
		MO_Loenindholdelse.vaelgLoenindholdelsestype(this, "SPEC");
		MO_Loenindholdelse.vaelgFordringer(this, fordringsID);
		MO_Loenindholdelse.klikGem(this);
		MO_Loenindholdelse.klikValider(this);
		
		//Step 4
		softAssert.assertFalse(MO_Loenindholdelse.tjekOmSagKanOprettes(this), "Lønindholdelse afvises ikke, selvom skyldneren er død");
		
		//Step 5
		List<WebElement> valideringsbrud = MO_Loenindholdelse.valideringsbrud(this);
		String valideringsbrud1 = valideringsbrud.get(0).getText();
		softAssert.assertEquals(valideringsbrud1, "The debtor is dead H", "Der vises ikke, hvorfor lønindholdelse ikke kan oprettes");

		//Step 6
		doLogout();
		softAssert.assertAll();
	}
}
