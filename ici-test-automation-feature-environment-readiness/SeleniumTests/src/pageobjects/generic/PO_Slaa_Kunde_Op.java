package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_Slaa_Kunde_Op {

    public static final Dropdown drp_IdType = new Dropdown(Frames.tabPage, By.id("idType"));

    public static final Input input_IdNummer = new Input(Frames.tabPage, By.id("idValue"));

    public static final Input input_FuldeNavn = new Input(Frames.tabPage, By.id("fullName"));

    // Dropdown-menu til soegetype
    public static final Dropdown drp_Soegetype = new Dropdown(Frames.tabPage, By.id("searchTypeExt"));

    public static final Button btn_Soeg = new Button(Frames.tabPage, By.id("anTLZ1Refresh"));

    public static final Link lnk_Kunde = new Link(Frames.tabPage,
            By.xpath("//div[@id='dataExplorerScrollDiv1']/table/tbody/tr[1]/td[2]"));

    public static final Link lnk_Konto = new Link(Frames.tabPage,
            By.xpath(".//*[@id='dataExplorerTableBody1']/tr/td/a/span[@title='Gå til Konto']"));

    public static final Button btn_ErrorIkon = new Button(Frames.tabPage, By.id("dataExplorerError1Icon"));

    // Herunder findes metoder til navigation i skyldners personlige detaljer
    public static final Input input_Fornavn = new Input(Frames.zoneMapFrame_1,
            By.xpath("/html/body/div[3]/div/table/tbody/tr/td[1]"));

    public static final Input input_Efternavn = new Input(Frames.zoneMapFrame_1,
            By.xpath("/html/body/div[3]/div/table/tbody/tr/td[2]"));

    public static final Input input_Adresse = new Input(Frames.zoneMapFrame_1,
            By.xpath("/html/body/div[6]/div/table/tbody[1]/tr/td[4]/a"));

    public static final PageElement txt_Doedsfald = new PageElement(Frames.zoneMapFrame_1,
            By.xpath("/html/body/div[7]/div/div[3]/span"));

    public static final PageElement txt_Adressebeskyttelse = new PageElement(Frames.zoneMapFrame_1,
            By.xpath("/html/body/div[7]/div/div[9]/span"));

    public static final PageElement txt_CPRnummer = new PageElement(Frames.zoneMapFrame_1,
            By.xpath("/html/body/div[4]/div[1]/table/tbody/tr/td[3]"));
}
