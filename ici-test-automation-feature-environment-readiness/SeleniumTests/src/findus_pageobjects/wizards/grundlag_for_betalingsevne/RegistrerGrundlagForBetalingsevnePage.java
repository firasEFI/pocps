package findus_pageobjects.wizards.grundlag_for_betalingsevne;

import findus_datamodels.GrundlagForBetalingsEvneModel;
import findus_datamodels.GrundlagForBetalingsevneAarligModel;
import findus_datamodels.GrundlagForBetalingsevneMaanedligModel;
import findus_pageobjects.BasePage;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.DropdownOption;
import findus_pageobjects.Utils;
import findus_pageobjects.synchronization.SynchronizeByElementPresent;
import icisel.pageobjects.elements.*;
import icisel.pageobjects.frames.Frame;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

import javax.rmi.CORBA.Util;

/**
 * Created by nielsjes on 24-08-2017.
 */
public class RegistrerGrundlagForBetalingsevnePage extends BasePage {

    Dropdown cboRegistreretIndkomstforhold = new Dropdown(Frames.uiMap, By.id("boGroup_incomeRegFreq"));
    Dropdown boGroup_CalcMethod = new Dropdown(Frames.uiMap, By.id("boGroup_CalcMethod"));

    //Månedlig - elementer

    BruttoindkomstElements maaned_1_Elementer = new BruttoindkomstElements(1);
    BruttoindkomstElements maaned_2_Elementer = new BruttoindkomstElements(2);

    Input txtArbejdsmarkedsbidrag = new Input(Frames.uiMap, By.id("boGroup_labourMarkCon"));
    Input txtPensionsindbetalinger = new Input(Frames.uiMap, By.id("boGroup_penPay"));
    Input txtAtpBidrag = new Input(Frames.uiMap, By.id("boGroup_denish"));
    Input txtSkat = new Input(Frames.uiMap, By.id("boGroup_tax"));
    Input txtBeregnetMaanedligNettoindkomst = new Input(Frames.uiMap, By.id("boGroup_calGrIn"));
    Input txtBeregnetAarligNettoindkomst = new Input(Frames.uiMap, By.id("boGroup_calNetIn"));

    //Årlig - elementer

    Input txtAarligBruttoIndkomst = new Input(Frames.uiMap, By.id("boGroup_yearly1_annualGrossInc"));
    Input txtAarligNettoindkomst = new Input(Frames.uiMap, By.id("boGroup_yearly1_annualNetInc"));
    Input txtAarsopgoerelseNr = new Input(Frames.uiMap, By.id("boGroup_yearly1_yearlySt"));
    Dropdown cboBegrundelseForAarligIndkomst = new Dropdown(Frames.uiMap, By.id("boGroup_yearly1_annualIncRes"));
    Dropdown cboAar = new Dropdown(Frames.uiMap, By.id("boGroup_yearly1_yearRes"));
    Dropdown cboAfdrag = new Dropdown(Frames.uiMap, By.id("boGroup_yearly1_install"));
    Dropdown boGroup_dependantCh = new Dropdown(Frames.uiMap, By.id("boGroup_dependantCh"));
    Input txtBeregnetAarligAfdrag = new Input(Frames.uiMap, By.id("boGroup_yearly1_calcAnnIn"));

    Button btnGem = new Button(Frames.uiMap, By.id("SAVE_BTN_MP"));
    Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@value='Annuller']"));

    /**
     * The page is in sync when this element isn't displayed
     */
    RegistrerGrundlagForBetalingsevnePage() {
        super(new SynchronizeByElementPresent(new PageElement(Frames.uiMap, By.xpath("//div[@style='display: none;']/label[@for='boGroup_paymentAbilityAmount']")), 10));
    }

    public RegistrerGrundlagForBetalingsevnePage fillForm(GrundlagForBetalingsEvneModel grundlagForBetalingsEvne) {
        if(grundlagForBetalingsEvne == null)
            throw new IllegalArgumentException("grundlagForBetalingsEvne cannot be null");

        if(grundlagForBetalingsEvne instanceof GrundlagForBetalingsevneMaanedligModel) {
            fillFormMaanedlig((GrundlagForBetalingsevneMaanedligModel)grundlagForBetalingsEvne);
        } else if(grundlagForBetalingsEvne instanceof GrundlagForBetalingsevneAarligModel) {
            fillFormAarlig((GrundlagForBetalingsevneAarligModel)grundlagForBetalingsEvne);
        } else
            throw new UnsupportedOperationException("Not implemented... yet");;

        return this;
    }

    private void fillFormMaanedlig(GrundlagForBetalingsevneMaanedligModel grundlagForBetalingsevne) {
        this.cboRegistreretIndkomstforhold.pickByVisibleText("Månedlig");

        if(this.maaned_1_Elementer != null)
            this.maaned_1_Elementer.fillForm(grundlagForBetalingsevne.getBruttoindkomst1());

        if(this.maaned_2_Elementer != null)
            this.maaned_1_Elementer.fillForm(grundlagForBetalingsevne.getBruttoindkomst2());

        switch (grundlagForBetalingsevne.getMaanedSomGrundlag()) {
            case MAANED_1:
                Utils.setCheckboxCheckedIfNotNull(this.maaned_1_Elementer.chkBrugDenneMånedSomGrundlag, true);

                break;
            case MAANED_2:
                Utils.setCheckboxCheckedIfNotNull(this.maaned_2_Elementer.chkBrugDenneMånedSomGrundlag, true);

                break;
            default:
                throw new UnsupportedOperationException("Not implemented... yet");
        }

        Utils.setTextBoxTextIfNotNull(this.txtArbejdsmarkedsbidrag, grundlagForBetalingsevne.getArbejdsmarkedsbidrag());
        Utils.setTextBoxTextIfNotNull(this.txtPensionsindbetalinger, grundlagForBetalingsevne.getPensionsindbetalinger());
        Utils.setTextBoxTextIfNotNull(this.txtAtpBidrag, grundlagForBetalingsevne.getAtpBidrag());
        Utils.setTextBoxTextIfNotNull(this.txtSkat, grundlagForBetalingsevne.getSkat());
        Utils.setTextBoxTextIfNotNull(this.txtBeregnetMaanedligNettoindkomst, grundlagForBetalingsevne.getBeregnetMaanedligNettoindkomst());
        Utils.setTextBoxTextIfNotNull(this.txtBeregnetAarligNettoindkomst, grundlagForBetalingsevne.getBeregnetAarligNettoindkomst());
    }

    private void fillFormAarlig(GrundlagForBetalingsevneAarligModel grundlagForBetalingsevne) {
        if(grundlagForBetalingsevne == null)
            throw new IllegalArgumentException("grundlagForBetalingsevne cannot be null");


        Utils.setDropdownVisibleTextIfNotNull(this.boGroup_CalcMethod, grundlagForBetalingsevne.getWebServiceCallMethod());
        Utils.setDropdownVisibleTextIfNotNull(this.boGroup_dependantCh, (grundlagForBetalingsevne.getForsoegerPligt() ? "Ja" : "Nej"));
        Utils.setDropdownVisibleTextIfNotNull(this.cboRegistreretIndkomstforhold, "Årlig");
        Utils.setTextBoxTextIfNotNull(this.txtAarligBruttoIndkomst, grundlagForBetalingsevne.getBruttoindkomst());
        Utils.setTextBoxTextIfNotNull(this.txtAarligNettoindkomst, grundlagForBetalingsevne.getNettoindkomst());
        Utils.setTextBoxTextIfNotNull(this.txtAarsopgoerelseNr, grundlagForBetalingsevne.getAarsopgørelsesnr());
        Utils.setDropdownVisibleTextIfNotNull(this.cboBegrundelseForAarligIndkomst, "At der ikke foreligger oplysninger om skyldners indkomst i indkomstregisteret.");
        Utils.setDropdownVisibleTextIfNotNull(this.cboAar, grundlagForBetalingsevne.getAar());
        Utils.setDropdownVisibleTextIfNotNull(this.cboAfdrag, grundlagForBetalingsevne.getAfdrag());
        Utils.setTextBoxTextIfNotNull(this.txtBeregnetAarligAfdrag, grundlagForBetalingsevne.getBeregnetAarligAfdrag());
    }

    public BasePsrmPage activateGem(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.btnGem.click();

        return new BasePsrmPage();
    }

    public BasePsrmPage activateAnnuller() {
        this.btnAnnuller.click();

        return new BasePsrmPage();
    }

    class BruttoindkomstElements {

        private final int monthNumber;

        Input txtBruttoindkomst = new Input(Frames.uiMap, By.id("boGroup_grossSalary1_grossSalary"));
        Input txtPeriode = new Input(Frames.uiMap, By.id("boGroup_grossSalary1_monthRes"));
        Dropdown cboIndkomsttype = new Dropdown(Frames.uiMap, By.id("boGroup_grossSalary1_typeOfIncome"));
        Dropdown cboAfdrag = new Dropdown(Frames.uiMap, By.id("boGroup_grossSalary1_install"));
        Input txtFradragsbeloeb = new Input(Frames.uiMap, By.id("boGroup_grossSalary1_Fradragsbeloeb"));
        Input txtA_indkomstMedAmBidrag = new Input(Frames.uiMap, By.id("boGroup_grossSalary1_AIndkomstMedAmBidrag"));
        Input txtA_indkomstUdenAmBidrag = new Input(Frames.uiMap, By.id("boGroup_grossSalary1_AIndkomstUdenAmBidrag"));
        Input txtB_indkomstMedAmBidrag = new Input(Frames.uiMap, By.id("boGroup_grossSalary1_BIndkomstMedAmBidrag"));
        Input txtB_indkomstUdenAmBidrag = new Input(Frames.uiMap, By.id("boGroup_grossSalary1_BIndkomstUdenAmBidrag"));
        Input txtEgenDelAfAtpBidrag = new Input(Frames.uiMap, By.id("boGroup_grossSalary1_EgenDelAfATPBidrag"));
        Input txtAngivetA_Skat = new Input(Frames.uiMap, By.id("boGroup_grossSalary1_AngivetASkat"));
        Input txtAmBidrag = new Input(Frames.uiMap, By.id("boGroup_grossSalary1_AmBidrag"));
        Input txtNettoindkomst = new Input(Frames.uiMap, By.id("boGroup_grossSalary1_Nettoindkomst"));
        Input txtIndberetningskilder = new Input(Frames.uiMap, By.id("boGroup_grossSalary1_IndberetningsKilder"));
        Input txtIndberetningstyper = new Input(Frames.uiMap, By.id("boGroup_grossSalary1_IndberetningsTyper"));
        Checkbox chkBrugDenneMånedSomGrundlag = new Checkbox(Frames.uiMap, By.id("boGroup_grossSalary1_useGrossCal"));

        private BruttoindkomstElements(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        private void fillForm(GrundlagForBetalingsevneMaanedligModel.BruttoindkomstModel bruttoindkomst) {
            Utils.setTextBoxTextIfNotNull(this.txtBruttoindkomst, bruttoindkomst.getBruttoindkomst());
            Utils.setTextBoxTextIfNotNull(this.txtPeriode, bruttoindkomst.getPeriode());
            Utils.setDropdownVisibleTextIfNotNull(this.cboIndkomsttype, bruttoindkomst.getIndkomsttype());
            Utils.setDropdownVisibleTextIfNotNull(this.cboAfdrag, bruttoindkomst.getAfdrag());
            Utils.setTextBoxTextIfNotNull(this.txtFradragsbeloeb, bruttoindkomst.getFradragsbeloeb());
            Utils.setTextBoxTextIfNotNull(this.txtA_indkomstMedAmBidrag, bruttoindkomst.getA_indkomstMedAmBidrag());
            Utils.setTextBoxTextIfNotNull(this.txtA_indkomstUdenAmBidrag, bruttoindkomst.getA_indkomstUdenAmBidrag());
            Utils.setTextBoxTextIfNotNull(this.txtB_indkomstMedAmBidrag, bruttoindkomst.getB_indkomstMedAmBidrag());
            Utils.setTextBoxTextIfNotNull(this.txtB_indkomstUdenAmBidrag, bruttoindkomst.getB_indkomstUdenAmBidrag());
            Utils.setTextBoxTextIfNotNull(this.txtEgenDelAfAtpBidrag, bruttoindkomst.getEgenDelAfAtpBidrag());
            Utils.setTextBoxTextIfNotNull(this.txtAngivetA_Skat, bruttoindkomst.getAngivetA_Skat());
            Utils.setTextBoxTextIfNotNull(this.txtAmBidrag, bruttoindkomst.getAmBidrag());
            Utils.setTextBoxTextIfNotNull(this.txtNettoindkomst, bruttoindkomst.getNettoindkomst());
            Utils.setTextBoxTextIfNotNull(this.txtIndberetningskilder, bruttoindkomst.getIndberetningskilder());
            Utils.setTextBoxTextIfNotNull(this.txtIndberetningstyper, bruttoindkomst.getIndberetningstyper());
        }
    }
}
