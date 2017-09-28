package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frame;
import icisel.pageobjects.frames.Frames;

public class PO_Afskriv {

    public static final Dropdown drp_Afskrivningsmulighed = new Dropdown(Frames.uiMap, By.id("writeOffOption"));

    public static final Input input_DividendeProcent = new Input(Frames.uiMap, By.id("percentageValue"));

    public static final Input input_Virkningsdato = new Input(Frames.uiMap, By.id("effDate"));

    public static final Dropdown drp_Afskrivningsaarsag = new Dropdown(Frames.uiMap, By.id("writeOffReason"));

    public static final Button btn_SoegFordring = new Button(Frames.uiMap, By.id("obligationId_search"));

    public static Button btn_FordringMedID(String sFordringsID) {
        String xpath = String.format("//span[text()='%s']", sFordringsID);
        return new Button(Frames.defaultContent, By.xpath(xpath));
    }

    public static final Input txt_Fordringsid = new Input(Frames.uiMap, By.id("obligationId_0"));

    public static final Input txt_Afskrivningsbeloeb = new Input(Frames.uiMap, By.id("writeOffAmount_0"));

    public static final Button btn_FordringSoeg = new Button(Frames.uiMap, By.id("obligationId_search"));

    public static final Button btn_Opret = new Button(Frames.uiMap,
            By.xpath("/html/body/table[3]/tbody/tr[11]/td/table/tbody/tr/td/input[1]"));

    public static final Button btn_SendTilGodkendelse = btn_withText(Frames.zoneMapFrame_2, "Send til godkendelse");

    public static final Button btn_Godkend = btn_withText(Frames.zoneMapFrame_2, "Godkend");

    public static final PageElement txt_SagsId = new PageElement(Frames.zoneMapFrame_2, By.id("processFlowId"));

    public static final Link lnk_Opgave = new Link(Frames.tabPage,
            By.xpath("//span[contains(.,'Godkend afskrivning, Oprettet')]"));

    public static final Link lnk_LinkTilDetaljer = new Link(Frames.tabPage, By.id("FULL_MSG"));

    public static Button btn_withText(Frame frame, String buttonTextExactMatch) {
        String xpath = String.format("//input[@type='button' and @value='%s']", buttonTextExactMatch);
        return new Button(frame, By.xpath(xpath));
    }

}
