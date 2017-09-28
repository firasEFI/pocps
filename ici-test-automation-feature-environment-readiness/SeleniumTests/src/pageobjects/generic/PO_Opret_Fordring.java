package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Checkbox;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

public class PO_Opret_Fordring {

    public static final Input input_Formulartype = new Input(Frames.uiMap, By.id("formType"));

    public static final Input input_DokumentLocator = new Input(Frames.uiMap, By.id("documentLocator"));

    public static final Button btn_FormulartypeOK = new Button(Frames.uiMap, By.id("ok"));

    public static final Button btn_FordringPlus = new Button(Frames.uiMap, By.xpath(
            "/html/body/div[1]/table[2]/tbody/tr[2]/td/div/table/tbody/tr/td[2]/div/table/tbody/tr/td/div[3]/div[2]/table/tbody/tr/td/table/tbody/tr/td/div[1]/span[1]/img"));

    public static final Input input_FordringshaverID = new Input(Frames.uiMap, By.id("fordringhaverID_asCurrent"));

    public static final Input input_FordringstypeKode = new Input(Frames.uiMap, By.id("fordringTypeKode_asCurrent"));

    public static final Input input_PUnitsNummer = new Input(Frames.uiMap, By.id("pEnhedNummer_asCurrent"));

    public static final Input input_FordringsID = new Input(Frames.uiMap, By.id("fordringsId_asCurrent"));

    public static final Input input_HovedFordringsID = new Input(Frames.uiMap, By.id("hovedFordringsId_asCurrent"));

    public static final Input input_FordringshaverBeskrivelse = new Input(Frames.uiMap,
            By.id("fordringshaverBeskr_asCurrent"));

    public static final Input input_ModtagelsesdatoFordring = new Input(Frames.uiMap,
            By.id("modtagelseDato_asCurrent"));

    public static final Input input_Forfaldsdato = new Input(Frames.uiMap, By.id("forfaldDato_asCurrent"));

    public static final Input input_SidsteBetalingsdato = new Input(Frames.uiMap,
            By.id("sidsteRettidigtBetalingsdato_asCurrent"));

    public static final Input input_ValutaKode = new Input(Frames.uiMap, By.id("valutaKode_asCurrent_0_0"));

    public static final Input input_Beloeb = new Input(Frames.uiMap, By.id("beloeb_asCurrent_0_0"));

    public static final Input input_BeloebDKK = new Input(Frames.uiMap, By.id("beloebDKK_asCurrent_0_0"));

    public static final Input input_OriginaleValutaKode = new Input(Frames.uiMap,
            By.id("oprindeligValutaKode_asCurrent_0_0"));

    public static final Input input_OriginaleBeloeb = new Input(Frames.uiMap, By.id("oprindeligBeloeb_asCurrent_0_0"));

    public static final Input input_OriginaleBeloebDKK = new Input(Frames.uiMap,
            By.id("oprindeligBeloebDKK_asCurrent_0_0"));

    public static final Input input_PeriodeFraDato = new Input(Frames.uiMap, By.id("periodeFraDato_asCurrent_0_0"));

    public static final Input input_PeriodeTilDato = new Input(Frames.uiMap, By.id("periodeTilDato_asCurrent_0_0"));

    public static final Input input_PeriodeType = new Input(Frames.uiMap, By.id("periodeType_asCurrent_0_0"));

    public static final Input input_RenteregelNummer = new Input(Frames.uiMap, By.id("renteRegelNummer_asCurrent_0_0"));

    public static final Dropdown drp_RentesatsRegel = new Dropdown(Frames.uiMap, By.id("renteSatsKode_asCurrent_0_0"));

    public static final Input input_Rentesats = new Input(Frames.uiMap, By.id("renteSats_asCurrent_0_0"));

    public static final Button btn_GemFordring = new Button(Frames.uiMap,
            By.xpath("//*[@oramdlabel = 'SAVE_BTN_LBL']"));

    public static final Button btn_GemOgFortsaet = new Button(Frames.uiMap,
            By.xpath("/html/body/div[1]/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/input[3]"));

    public static final Button btn_ValiderForm = new Button(Frames.uiMap,
            By.xpath("//*[@oramdlabel = 'CHECK_FORM_BTN_LBL']"));

    public static final Button btn_ValiderOgPost = new Button(Frames.zoneMapFrame_1,
            By.xpath("//*[@oramdlabel = 'C1_VAL_AND_POST_LBL']"));

    public static final PageElement txt_modtagFordring = new PageElement(Frames.uiMap,
            By.xpath("//span[contains(.,'Modtag Fordring')]"));

    public static final Link lnk_VisAlle = new Link(Frames.uiMap, By.xpath(
            "/html/body/div[1]/table[2]/tbody/tr[2]/td/div/table/tbody/tr/td[1]/div/table/tbody/tr[5]/td/a/span"));

    public static final Link lnk_SkjulAlle = new Link(Frames.uiMap, By.xpath("//span[contains(.,'Skjul alle')]"));

    public static final Dropdown drp_Formularkilde = new Dropdown(Frames.uiMap, By.id("formSource"));

    public static final Input input_ModtagelsesdatoPrimaer = new Input(Frames.uiMap, By.id("receiveDate"));

    public static final Input input_IndarbejdelsesDato = new Input(Frames.uiMap, By.id("stiftelseTidspunkt_asCurrent"));

    public static final Input input_ActionId = new Input(Frames.uiMap, By.id("aktionsId_asCurrent"));

    public static final PageElement txt_Fejlliste = new PageElement(Frames.uiMap,
            By.xpath(".//table[@id='exceptionsDetails']/tbody/tr/td[@onclick[contains(.,'showExceptionAlert')]]"));

    public static final Dropdown drp_Afdeling = new Dropdown(Frames.tabPage, By.id("CIS_DIVISION"));

    public static final PageElement txt_Fordringshaverrelation = new PageElement(Frames.tabPage, By.id("TAX_ROLE_ID"));

    public static final Button btn_FordringshaverrelationSoeg = new Button(Frames.tabPage, By.id("IM_TAX_ROLE_ID"));

    public static final PageElement txt_Fordringstype = new PageElement(Frames.tabPage, By.id("SA_TYPE_CD"));

    public static final Button btn_Gem = new Button(Frames.main, By.id("IM_SAVE"));

    public static final Input input_Soegefelt = new Input(Frames.tabPage, By.xpath("//*[@name='SA_INFO']"));

    public static final Input input_FordringshaverReference = new Input(Frames.uiMap,
            By.id("fordringshaverRef_asCurrent"));

    public static final Link link_check = new Link(Frames.zoneMapFrame_4, By.xpath(
            "/html/body/div[1]/table[2]/tbody/tr[2]/td/div/table/tbody/tr/td[2]/div/table/tbody/tr/td/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[2]/span/a"));

    public static final Button btn_Close = new Button(Frames.main, By.xpath("//*[@id='scriptClose2']"));

    public static final PageElement tab_zoneHeader4 = new PageElement(Frames.tabPage, By.id("zoneHeader4"));

    // HaeftelsesStruktur
    public static final Input input_PersonCPRNummer = new Input(Frames.uiMap, By.id("personCPRNummer_asCurrent_0_0"));

    public static final Input input_AlternativKontaktId = new Input(Frames.uiMap,
            By.id("alternativKontaktID_asCurrent_0_0"));

    public static final Input input_VirksomhedSENummer = new Input(Frames.uiMap,
            By.id("virksomhedSENummer_asCurrent_0_0"));

    public static final Input input_AflaestDato = new Input(Frames.uiMap, By.id("laesDatoTid_asCurrent_0_0"));

    public static final Input input_HaeftelsesForaeldelsesdato = new Input(Frames.uiMap,
            By.id("haeftelseForaeldelseDato_asCurrent_0_0"));

    public static final Checkbox chk_HaeftelseUnderBobehandling = new Checkbox(Frames.uiMap,
            By.id("haeftelseUnderBobehandling_asCurrent_0_0"));

    public static final Checkbox chk_HaeftelsesAfgoerelse = new Checkbox(Frames.uiMap,
            By.id("haeftelseDom_asCurrent_0_0"));

    public static final Checkbox chk_HaeftelsesUdligning = new Checkbox(Frames.uiMap,
            By.id("haeftelseForlig_asCurrent_0_0"));

    public static final Checkbox chk_Fordringsappel = new Checkbox(Frames.uiMap, By.id("fordringPaaklaget_asCurrent"));

    public static final Input input_HaeftelsesAfgoerelsesdato = new Input(Frames.uiMap,
            By.id("haeftelseDomDato_asCurrent_0_0"));

    public static final Input input_HaeftelsesForligsdato = new Input(Frames.uiMap,
            By.id("haeftelseForligDato_asCurrent_0_0"));

    // Note struktur
    public static final Input input_NoteOprettelsestidspunkt = new Input(Frames.uiMap,
            By.id("noteOprettetTidspunkt_asCurrent_0_0"));

    public static final Input input_NoteOprettetAf = new Input(Frames.uiMap, By.id("noteOprettetAf_asCurrent_0_0"));

    public static final Input input_fordringensEksterneReference = new Input(Frames.uiMap,
            By.id("fordringEksternReference_asCurrent_0_0"));

    public static final Input input_NoteTekst = new Input(Frames.uiMap, By.id("noteTekst_asCurrent_0_0"));

    // Dokument struktur
    public static final Input input_DokumentType = new Input(Frames.uiMap, By.id("dokumentArt_asCurrent_0_0"));

    public static final Input input_DokumentEksternReference = new Input(Frames.uiMap,
            By.id("dokumentEksternReference_asCurrent_0_0"));

    public static final Input input_DokumentFilType = new Input(Frames.uiMap, By.id("dokumentFilType_asCurrent_0_0"));

    public static final Input input_DokumentFilIndhold = new Input(Frames.uiMap,
            By.id("dokumentFilIndhold_asCurrent_0_0"));

    public static final Input input_DokumentNummer = new Input(Frames.uiMap, By.id("dokumentNummer_asCurrent_0_0"));

    /**
     * @return WebElement med tekst svarende til "Status" i slutbillede paa
     *         Opret Fordring
     */
    public static final PageElement txt_Status = new PageElement(Frames.zoneMapFrame_4, By.id("statusDescription"));

    /**
     * Fordringens interne ID, ved headeren "Fordringsformular"
     */
    public static final PageElement txt_InterntFordringsID = new PageElement(Frames.zoneMapFrame_4,
            By.id("obligationId"));
}
