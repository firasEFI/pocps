package findus_pageobjects.Brevoplysninger_soeg;

import findus_pageobjects.SubPage;
import findus_pageobjects.Utils;
import findus_pageobjects.WebList;
import findus_pageobjects.brevoplysninger.Brevoplysninger_PrimaerSubPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.DatePicker;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class BrevoplysningerSoeg_PrimaerSubPage extends SubPage<BrevoplysningerSoegPage>{

    final Input txtPart = new Input(Frames.tabPage, By.id("personId"));
    final DatePicker dtpStiftelsesdatoFra = new DatePicker(Frames.tabPage, By.id("createDateFrom"));
    final DatePicker dtpStiftelsesdatoTil = new DatePicker(Frames.tabPage, By.id("createDateTo"));
    final Dropdown cboInddrivelsesskridt = new Dropdown(Frames.tabPage, By.id("ccclassid"));
    final Dropdown cboKontakttype = new Dropdown(Frames.tabPage, By.id("cctypecd"));
    final Input txtBruger = new Input(Frames.tabPage, By.id("userId"));
    final Input txtKundekontaktId = new Input(Frames.tabPage, By.id("ccId"));
    final Input txtNavn = new Input(Frames.tabPage, By.id("personName"));
    final Input txtBeskrivelse = new Input(Frames.tabPage, By.id("comments"));

    final Button btnSoeg = new Button(Frames.tabPage, By.id("anTLZ1Refresh"));

    final WebList lstSearchResults = new WebList(Frames.tabPage, "dataExplorerTable1");

    public BrevoplysningerSoeg_PrimaerSubPage() {
        super(new BrevoplysningerSoegPage());
    }

    protected BrevoplysningerSoeg_PrimaerSubPage(BrevoplysningerSoegPage parentPage) {
        super(parentPage);
    }

    public BrevoplysningerSoeg_PrimaerSubPage fillForm(BrevoplysningerSearchArgs searchArgs) {
        Utils.setTextBoxTextIfNotNull(this.txtPart, searchArgs.getPart());
        Utils.setDatePickerValueIfNotNull(this.dtpStiftelsesdatoFra, searchArgs.getStiftelsesdatoFra());
        Utils.setDatePickerValueIfNotNull(this.dtpStiftelsesdatoTil, searchArgs.getStiftelsesdatoTil());
        Utils.setDropdownVisibleTextIfNotNull(this.cboInddrivelsesskridt, searchArgs.getInddrivelsesskridt());
        Utils.setDropdownVisibleTextIfNotNull(this.cboKontakttype, searchArgs.getKontakttype());
        Utils.setTextBoxTextIfNotNull(this.txtBruger, searchArgs.getBruger());
        Utils.setTextBoxTextIfNotNull(this.txtKundekontaktId, searchArgs.getKundekontaktId());
        Utils.setTextBoxTextIfNotNull(this.txtNavn, searchArgs.getNavn());
        Utils.setTextBoxTextIfNotNull(this.txtBeskrivelse, searchArgs.getBeskrivelse());

        return this;
    }

    public BrevoplysningerSoeg_PrimaerSubPage activateSoeg() {
        this.btnSoeg.click();

        return this;
    }

    public Brevoplysninger_PrimaerSubPage activateGaaTilBrevoplysninger(int rowIndex) {
        this.lstSearchResults.clickCell("Kundekontakt ID", 0);

        return new Brevoplysninger_PrimaerSubPage();
    }
}
