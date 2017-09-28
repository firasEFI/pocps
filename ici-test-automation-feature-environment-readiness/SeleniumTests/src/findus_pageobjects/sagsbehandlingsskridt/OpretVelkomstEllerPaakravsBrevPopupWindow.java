package findus_pageobjects.sagsbehandlingsskridt;

import findus_pageobjects.DropdownOption;
import findus_pageobjects.PopupWindow;
import findus_pageobjects.PopupWindowResolver;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Checkbox;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class  OpretVelkomstEllerPaakravsBrevPopupWindow extends PopupWindow<Sagsbehandlingsskridt_PrimaerPaakravssagPage> {

    public enum TypeAfBrev implements DropdownOption {
        PAAKRAVSBREV_MED_PARTSHOERING("IND0704AFD02", "Påkravsbrev - med partshøring"),
        PAAKRAVSBREV_UDEN_PARTSHOERING("IND0704AFD01", "Påkravsbrev - uden partshøring"),
        VELKOMSTBREV_MED_PARTSHOERING("IND0705AFD01", "Velkomstbrev - med partshøring"),
        VELKOMSTBREV_UDEN_PARTSHOERING("IND0705AFD02", "Velkomstbrev - uden partshøring");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        private TypeAfBrev(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }

    final Dropdown cboInddrivelsesSkridt = new Dropdown(Frames.defaultContent, By.id("select_customer_class"));
    final Dropdown cboTypeAfBrev = new Dropdown(Frames.defaultContent, By.id("select_customer_type"));
    final Checkbox chkGebyr = new Checkbox(Frames.defaultContent, By.id("includeFee"));
    final Input aarsagFelt = new Input(Frames.defaultContent, By.id("reasonExcludeFee"));

    final Button btnOk = new Button(Frames.defaultContent, By.id("okButton"));
    final Button btnAnnuller = new Button(Frames.defaultContent, By.xpath("//input[@value = 'Annuller']"));

    public boolean canSetGebyr() {
        return this.chkGebyr.isEnabled();
    }

    public OpretVelkomstEllerPaakravsBrevPopupWindow(Sagsbehandlingsskridt_PrimaerPaakravssagPage parentPage) {
        super(By.id("includeFee"), parentPage);
    }

    public OpretVelkomstEllerPaakravsBrevPopupWindow fillFormUdenGebyr(TypeAfBrev typeAfBrev, String aarsag) {
        this.cboTypeAfBrev.pickByVisibleText(typeAfBrev.visibleText);

        if(this.chkGebyr.isSelected()){
            this.chkGebyr.click();
            this.aarsagFelt.sendKeys(aarsag);
        }

        return this;
    }

    public OpretVelkomstEllerPaakravsBrevPopupWindow fillFormMedGebyr(TypeAfBrev typeAfBrev) {
        this.cboTypeAfBrev.pickByVisibleText(typeAfBrev.visibleText);

        if(!this.chkGebyr.isSelected())
            this.chkGebyr.click();

        return this;
    }

    public Sagsbehandlingsskridt_PrimaerPaakravssagPage activateOk() {
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());

        this.btnOk.click();

        resolver.waitForPopupToClose(10000);

        return this.parentPage;
    }

    public Sagsbehandlingsskridt_PrimaerTabPage activateAnnuller() {
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());

        this.btnOk.click();

        resolver.waitForPopupToClose(10000);

        return this.parentPage;
    }
}
