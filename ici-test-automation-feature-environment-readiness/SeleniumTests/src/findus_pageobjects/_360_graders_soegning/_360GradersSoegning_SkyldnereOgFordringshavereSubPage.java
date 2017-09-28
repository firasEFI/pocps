package findus_pageobjects._360_graders_soegning;

import org.openqa.selenium.By;

import findus_pageobjects.SubPage;
import findus_pageobjects.Utils;
import icisel.pageobjects.elements.Checkbox;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;

public class _360GradersSoegning_SkyldnereOgFordringshavereSubPage extends SubPage<_360GradersSoegningPage> {

    protected _360GradersSoegning_SkyldnereOgFordringshavereSubPage(_360GradersSoegningPage parentPage) {
        super(parentPage);
    }

    // elementer
    Input txtFullName = new Input(Frames.tabPage, By.id("fullName"));
    Input txtFornavn = new Input(Frames.tabPage, By.id("firstName"));
    Input txtEfternavn = new Input(Frames.tabPage, By.id("lastName"));
    Input txtEmail = new Input(Frames.tabPage, By.id("emailName"));
    Input txtTelefon = new Input(Frames.tabPage, By.id("phoneNumber"));
    Input txtSkyldnerId = new Input(Frames.tabPage, By.id("internalPersonId"));
    Checkbox chkVisKunInaktive = new Checkbox(Frames.tabPage, By.id("includeInactive"));
    Dropdown cboIdType = new Dropdown(Frames.tabPage, By.id("idType"));
    Input txtIdNummer = new Input(Frames.tabPage, By.id("idValue"));
    Dropdown cboSoegeType = new Dropdown(Frames.tabPage, By.id("searchTypeExt"));

    // operationer
    public _360GradersSoegning_SkyldnereOgFordringshavereSubPage fillForm(
            SkyldnereOgFordringsHavereSearchArgs searchArgs) {
        Utils.setTextBoxTextIfNotNull(this.txtFullName, searchArgs.getFuldeNavn());
        Utils.setTextBoxTextIfNotNull(this.txtFornavn, searchArgs.getFornavn());
        Utils.setTextBoxTextIfNotNull(this.txtEfternavn, searchArgs.getEfternavn());
        Utils.setTextBoxTextIfNotNull(this.txtEmail, searchArgs.getEmail());
        Utils.setTextBoxTextIfNotNull(this.txtTelefon, searchArgs.getTelefonnummer());
        Utils.setTextBoxTextIfNotNull(this.txtSkyldnerId, searchArgs.getSkyldnerId());
        Utils.setCheckboxCheckedIfNotNull(this.chkVisKunInaktive, searchArgs.isVisKunInaktive());
        Utils.setTextBoxTextIfNotNull(this.txtIdNummer, searchArgs.getId());
        Utils.setDropdownVisibleTextIfNotNull(this.cboIdType, searchArgs.getIdType());
        Utils.setDropdownVisibleTextIfNotNull(this.cboSoegeType, searchArgs.getSoegeType());

        return this;
    }

    public _360GradersSoegningPage activateSoeg() {
        return getParentPage().activateSoeg();
    }
}
