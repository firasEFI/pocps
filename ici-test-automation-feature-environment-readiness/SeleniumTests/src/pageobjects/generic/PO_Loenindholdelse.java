package pageobjects.generic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Checkbox;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import pageobjects.smoketests.psrm_navigation.Navi;
import utils.FrameType;

public class PO_Loenindholdelse {

	public static final Dropdown drp_Loenindholdelsestype = new Dropdown(Frames.uiMap,
			By.id("boGroup_salaryWithholdType"));

	public static final Button btn_Gem(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("SAVE_BTN_MP"));
		return new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
	}

	public static final Button btn_Cancel(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("CANCEL_BTN_MP"));
		return new Button(Frames.uiMap, By.id("CANCEL_BTN_MP"));
	}

	public static final Button btn_VaelgAlt(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("select_all_button"));
		return new Button(Frames.uiMap, By.id("select_all_button"));
	}

	public static final Button btn_FravaelgAlt(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("deselect_all_button"));
		return new Button(Frames.uiMap, By.id("deselect_all_button"));
	}

	public static final Button btn_VisRelateredeFordringer(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver, By.id("show_related_claims"));
		return new Button(Frames.uiMap, By.id("show_related_claims"));
	}

	public static final Checkbox chk_Fordringer(String fordringsID, WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.UI_MAP, driver,
				By.xpath(".//table[@id='myTable']//tr[contains(.," + fordringsID + ")]//input[@type='checkbox']"));
		return new Checkbox(Frames.uiMap,
				By.xpath(".//table[@id='myTable']//tr[contains(.," + fordringsID + ")]//input[@type='checkbox']"));
	}


	public static final List<WebElement> txt_AabneOpgaver(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver,
				By.xpath(".//*[@id='TRANSITION_1' and @value='Acceptér softvalidering']"));
		List<WebElement> opgaver = driver
				.findElements(By.xpath(".//*[@id='exceptions_openToDos']/table/tbody[*]/tr/td"));
		return opgaver;
	}

	public static final List<WebElement> txt_IkkeBehandledeBetalinger(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver,
				By.xpath(".//*[@id='TRANSITION_1' and @value='Acceptér softvalidering']"));
		List<WebElement> betalinger = driver
				.findElements(By.xpath(".//*[@id='exceptions_nonPlacedPayments']/table/tbody[*]/tr/td/a"));
		return betalinger;
	}
	
	public static final List<WebElement> txt_Valideringsbrud(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver,
				By.xpath(".//*[@id='boStatus' and text()!='Kladde']"));
		List<WebElement> valideringsbrud = driver
				.findElements(By.xpath(".//*[@id='exceptions_exceptionList']/table/tbody[*]/tr"));
		return valideringsbrud;
	}

    public static final PageElement txt_AktiveLoenindholdelser (){
        return new PageElement(Frames.tabPage,
                By.xpath(".//*[@id='dataExplorerTableBody1']//tr[contains(., \"Aktiv lønindeholdelse\")]"));
   }

    public static final PageElement txt_LoenindeholdelsesType (){
        return new PageElement(Frames.zoneMapFrame_2,
                By.xpath(".//*[@id=\"salaryWithholdType\"]"));
    }


	public static final Button btn_Valider(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.id("TRANSITION_1"));
		return new Button(Frames.zoneMapFrame_2, By.xpath(".//*[@id='TRANSITION_1' and @value='Validér']"));
	}

	public static final Button btn_OpretUdenValidering(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.xpath(".//*[@id='TRANSITION_2' and @value='Opret Uden Validering']"));
		return new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_2"));
	}

	public static final Button btn_Annuller(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.xpath(".//*[@id='TRANSITION_3' and @value='Annullér']"));
		return new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_3"));
	}

	public static final Button btn_AccepterSoftvalidering(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.xpath(".//*[@id='TRANSITION_1' and @value!='Opret Uden Validering']"));
		return new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));
	}
	
	public static final Button btn_AccepterSoftvalideringUdenVent(WebDriver driver) {
		return new Button(Frames.zoneMapFrame_2, By.xpath(".//*[@id='TRANSITION_1' and @value='Acceptér softvalidering']"));
	}
	

	public static final Button btn_OpretSag(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.xpath(".//*[@id='TRANSITION_1' and @value='Opret sag']"));
		return new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));
	}
	
	public static final Button btn_Aktiver(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.xpath(".//*[@id='TRANSITION_1' and @value='Aktivér']"));
		return new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));
	}

	public static final String txt_StatusFoerAktivering(WebDriver driver) {
		Navi.patientlyFindDisplayedElement(FrameType.ZONE_MAP_FRAME_2, driver, By.xpath(".//*[@id='TRANSITION_1' and @value='Aktivér']"));
		return new PageElement(Frames.zoneMapFrame_2, By.xpath(".//*[@id='boStatus']")).getText();
	}
}
