package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;

public class PO_SoegFordringsformular {
	
	public static final Dropdown drp_soegEfter = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));
	
	//De følgende hører til muligheden "Part"
	// ... ingen indtil videre
	
	//De følgende hører til muligheden "Modtagelsesdato/Type/Status/Arkiveringstype"
	public static final Input input_ModtagelsesdatoStart = new Input(Frames.tabPage, By.id("filter1.F1"));
	
	public static final Input input_ModtagelsesdatoSlut = new Input(Frames.tabPage, By.id("filter1.F2"));
	
	public static final Input input_Formulartype = new Input(Frames.tabPage, By.id("formType"));
	
	public static final Dropdown drp_Status = new Dropdown(Frames.tabPage, By.id("boStatus"));
	
	public static final Dropdown drp_FordringsformularArkiveringstype = new Dropdown(Frames.tabPage, By.id("taxFormFilingType"));
	
	public static final Button btn_Soeg = new Button(Frames.tabPage, By.id("anTLZ1Refresh"));
	

}
