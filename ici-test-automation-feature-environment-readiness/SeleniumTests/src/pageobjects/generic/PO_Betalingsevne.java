package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.DatePicker;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_Betalingsevne {
    public static final Dropdown drp_Betalingsevne = new Dropdown(Frames.uiMap, By.id("processFlowId"));

    public static final Button btn_OK = new Button(Frames.uiMap, By.id("ok"));

    public static final Input txt_Konto = new Input(Frames.uiMap, By.id("boGroup_accountId"));

    public static final Input txt_MaanedBetalBudget = new Input(Frames.uiMap,
            By.id("boGroup_calcPayAbility_mounthPayAbility"));

    public static final Input txt_MaanedBetalTabeltraek = new Input(Frames.uiMap,
            By.id("boGroup_calcPayAbility_mounthPayAbLookup"));

    public static final Dropdown drp_AnvendInddrivelsesskridt = new Dropdown(Frames.uiMap,
            By.id("boGroup_calcPayAbility_useInCallStep"));

    public static final Button btn_Gem = new Button(Frames.uiMap,
            By.xpath("/html/body/table[2]/tbody/tr[19]/td/table/tbody/tr/td/input[1]"));

    public static final Button btn_Annuller = new Button(Frames.uiMap, By.xpath("//html//input[@value='Annuller']"));

    public static final Input txt_BeregnetBetalingsevneDato = new Input(Frames.uiMap,
            By.id("boGroup_calcPayAbility_payAbilityDate"));

    public static final Input txt_ModtagelsesdatoBudget = new Input(Frames.uiMap,
            By.id("boGroup_calcPayAbility_budgetReceiveDate"));

    public static final Input txt_GyldighedsperiodeFra = new Input(Frames.uiMap, By.id("boGroup_validityfrom"));

    public static final DatePicker picker_GyldighedsperiodeFra = new DatePicker(Frames.uiMap,
            By.cssSelector("tr#trValidityfrom img.dateTime"));
    public static final DatePicker picker_GyldighedsperiodeTil = new DatePicker(Frames.uiMap,
            By.cssSelector("tr#trValidityTo img.dateTime"));

    public static final Input txt_GyldighedsperiodeTil = new Input(Frames.uiMap, By.id("boGroup_validityTo"));

    public static final PageElement txt_Fejlbesked = new PageElement(Frames.uiMap, By.id("ERRMSG-TEXT-SPAN"));

    public static final Dropdown drp_Forsoegerpligt = new Dropdown(Frames.uiMap, By.id("boGroup_dependantCh"));

}
