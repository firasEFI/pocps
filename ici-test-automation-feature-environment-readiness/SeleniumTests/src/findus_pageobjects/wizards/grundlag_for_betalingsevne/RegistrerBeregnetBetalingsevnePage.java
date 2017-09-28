package findus_pageobjects.wizards.grundlag_for_betalingsevne;

import findus_datamodels.BeregnetBetalingsevne;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.Utils;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.DatePicker;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

/**
 * Created by nielsjes on 25-08-2017.
 */
public class RegistrerBeregnetBetalingsevnePage {

    Input txtMaanedligtBetalingsevneBudget = new Input(Frames.uiMap, By.id("boGroup_calcPayAbility_mounthPayAbility"));
    Input txtMaanedligtBetalingsevneTabelTraek = new Input(Frames.uiMap, By.id("boGroup_calcPayAbility_mounthPayAbLookup"));
    Dropdown cboAnvendIInddrivelsesskridt = new Dropdown(Frames.uiMap, By.id("boGroup_calcPayAbility_useInCallStep"));
    DatePicker dtpBeregnetBetalingsevneDato = new DatePicker(Frames.uiMap, By.id("boGroup_calcPayAbility_payAbilityDate"));

    Button btnGem = new Button(Frames.uiMap, By.xpath("//input[@value = 'Gem']"));
    Button btnAnnuller = new Button(Frames.uiMap, By.xpath(""));

    public RegistrerBeregnetBetalingsevnePage fillForm(BeregnetBetalingsevne betalingsevne) {
        Utils.setTextBoxTextIfNotNull(this.txtMaanedligtBetalingsevneBudget, betalingsevne.getMaanedligtBetalingsevneBudget());
        Utils.setTextBoxTextIfNotNull(this.txtMaanedligtBetalingsevneTabelTraek, betalingsevne.getMaanedligtBetalingsevneTabelTraek());
        Utils.setDropdownVisibleTextIfNotNull(this.cboAnvendIInddrivelsesskridt, betalingsevne.getAnvendIInddrivelsesskridt());
        Utils.setDatePickerValueIfNotNull(this.dtpBeregnetBetalingsevneDato, betalingsevne.getBeregnetBetalingsevneDato());

        return this;
    }

    public BasePsrmPage activateGem() {
        btnGem.click();

        return new BasePsrmPage();
    }
}
