package testdata;

import icisel.taxobjects.Fordring;
import icisel.taxobjects.Fordringstype;
import utils.tools.TO_Tools;

public class StandardFordringer {
	
	public static Fordring getNewStandardFordring(){
		return getNewStandardMedielicens();
	}

	public static Fordring getNewStandardMedielicens() {
		String randomCpr = TO_Tools.randomNDigitNumber(10);
		String defaultAmount = "240";
		return new Fordring(Fordringstype.DR_MEDIELICENS_TIL_DR, randomCpr, defaultAmount);
	}

}
