package findus_pageobjects.opgave_overblik;

import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.SubPage;
import findus_pageobjects.Utils;
import findus_pageobjects.WebList;
import findus_pageobjects.opgave.OpgavePage;
import findus_pageobjects.opgave.Opgave_PrimaerSubPage;
import findus_testobjects.*;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class OpgaveOverblik_PrimaerSubPage extends SubPage<OpgaveOverblikPage> {

    //Søgetabel m. diverse knapper
    final Link lnkTilOpgaveOverblik_PrimaerSubPage = new Link(Frames.tabPage, By.xpath("//*[@id=\"dataExplorerTableBody1\"]/tr[1]/td[6]/a"));
    private WebList lstOpgaver = new WebList(Frames.tabPage, "dataExplorerTable1");
    private Button btnSoegTildeltTilBruger = new Button(Frames.tabPage, By.id("assignedUser_search"));
    private Button btnSoeg = new Button(Frames.tabPage, By.id("anTLZ1Refresh"));
    final WebList lstOpgaveTildeltSagsbehandler = new WebList(Frames.tabPage, "dataExplorerTable1");

    public final IU_601 iu_601 = new IU_601(this);
    public final IU_602 iu_602 = new IU_602(this);
    public final IU_611_gaaTilIndividuelUdbetaling iu_611_gaaTilIndividuelUdbetaling = new IU_611_gaaTilIndividuelUdbetaling(this);
    public final IU_613_lukOpgaveManuelt iu_613_lukOpgaveManuelt = new IU_613_lukOpgaveManuelt(this);

    //Søgeparametre
    final Dropdown cboOpgavePrioritet = new Dropdown(Frames.tabPage, By.id("toDoPriority"));
    final Input txtTildeltTilBruger = new Input(Frames.tabPage, By.id("assignedUser"));
    final Dropdown cboOpgavestatus = new Dropdown(Frames.tabPage, By.id("status"));
    final Dropdown cboOpgavestatus2 = new Dropdown(Frames.tabPage, By.id("status2"));
    final Input txtOpgaveID = new Input(Frames.tabPage, By.id("toDoId"));
    final Input txtRelationTilID = new Input(Frames.tabPage, By.id("toDoRelationId"));
    final Dropdown cboOpgavekategori = new Dropdown(Frames.tabPage, By.id("toDoCategory"));
    final Dropdown cboOpgavetype = new Dropdown(Frames.tabPage, By.id("toDoType"));
    final Input txtSenesteFristFra = new Input(Frames.tabPage, By.id("toDoDeadlineStart"));
    final Input txtSenesteFristTil = new Input(Frames.tabPage, By.id("toDoDeadlineTo"));
    final Input txtAfslutningsdatoFra = new Input(Frames.tabPage, By.id("completionDateStart"));
    final Input txtAfslutningsdatoTil = new Input(Frames.tabPage, By.id("completionDateTo"));
    
    public OpgaveOverblik_PrimaerSubPage(OpgaveOverblikPage parentPage) {
        super(parentPage);
    }
    
    //Metoder på siden
    public OpgavePage clickOpgave() {
        lstOpgaver.clickCell("Relation til ID", "002000000013", "Opgavetype");
        return new OpgavePage();
    }

    public SoegTildeltBrugerPopupWindow activateSoegTildeltBruger() {
        PopupWindowResolver<SoegTildeltBrugerPopupWindow> popupResolver = new PopupWindowResolver<>(Engine.getDriver());

        btnSoegTildeltTilBruger.click();

        SoegTildeltBrugerPopupWindow popup = popupResolver.waitForPopup(() -> new SoegTildeltBrugerPopupWindow(OpgaveOverblik_PrimaerSubPage.this),10000);

        return popup;
    }

    /**
     * Funktion klikker på Søg-knappen
     * 
     * @return OpgaveOverblik_PrimaerSubPage
     */
    public OpgaveOverblik_PrimaerSubPage activateSoeg(){
        btnSoeg.click();
        return this;
    }
    
    public OpgaveOverblik_PrimaerSubPage activateFirstResult(){
        lnkTilOpgaveOverblik_PrimaerSubPage.click();
        return this;
    }

    public OpgaveOverblik_PrimaerSubPage activateOpgaveTildel(String opgaveId) {
        this.lstOpgaver.clickCell("Opgave ID", opgaveId, "Tildel");

        return this;
    }

    public OpgaveOverblik_PrimaerSubPage trykPaaAfslutOpgaven(String opgaveID) {
        lstOpgaveTildeltSagsbehandler.clickCell("Opgave ID", opgaveID, "Afslut opgave");
        return this;
    }

    public Opgave_PrimaerSubPage selectOpgave(String opgaveID) {
        lstOpgaveTildeltSagsbehandler.clickCell("Opgave ID", opgaveID, "Opgavetype");
        return new Opgave_PrimaerSubPage(new OpgavePage());
    }

    /**
     * Funktion fylder alle ikke-null elementer i opgave overblikkets søgeparametre
     * @param searchArgs = OpgaveSearchArgs objekt
     * @return OpgaveOverblik_PrimaerSubPage
     */
    public OpgaveOverblik_PrimaerSubPage fillForm(OpgaveSearchArgs searchArgs) {
        Utils.setDropdownVisibleTextIfNotNull(cboOpgavePrioritet, searchArgs.getOpgavePrioritet());
        Utils.setTextBoxTextIfNotNull(txtTildeltTilBruger, searchArgs.getTildeltTilBruger());
        Utils.setDropdownVisibleTextIfNotNull(cboOpgavestatus, searchArgs.getOpgavestatus());
        Utils.setDropdownVisibleTextIfNotNull(cboOpgavestatus2, searchArgs.getOpgavestatus());
        Utils.setTextBoxTextIfNotNull(txtOpgaveID, searchArgs.getOpgaveID());
        Utils.setTextBoxTextIfNotNull(txtRelationTilID, searchArgs.getRelationTilID());
        Utils.setDropdownVisibleTextIfNotNull(cboOpgavekategori, searchArgs.getOpgavekategori());
        Utils.setDropdownVisibleTextIfNotNull(cboOpgavetype, searchArgs.getOpgavetype());
        Utils.setTextBoxTextIfNotNull(txtSenesteFristFra, searchArgs.getSenesteFristFra());
        Utils.setTextBoxTextIfNotNull(txtSenesteFristTil, searchArgs.getSenesteFristTil());
        Utils.setTextBoxTextIfNotNull(txtAfslutningsdatoFra, searchArgs.getAfslutningsdatoFra());
        Utils.setTextBoxTextIfNotNull(txtAfslutningsdatoTil, searchArgs.getAfslutningsdatoTil());
        
        return this;
    }
}
