package findus_pageobjects.opgave_overblik_for_tilsyn;

import findus_datamodels.PsrmUserModel;
import findus_pageobjects.PopupWindow;
import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.Utils;
import findus_pageobjects.WebList;
import findus_pageobjects.opgave_overblik.OpgaveOverblik_PrimaerSubPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class BrugersoegningPopupWindow extends PopupWindow<OpgaveOverblikForTilsyn_PrimaerSubPage> {

    Button btnSoeg = new Button(Frames.defaultContent, By.id("anTLZ1Refresh"));
    Input txtBrugerID = new Input(Frames.defaultContent, By.id("filter1.F1"));
    Input txtFornavn = new Input(Frames.defaultContent, By.id("filter1.F3"));
    Input txtEfternavn = new Input(Frames.defaultContent, By.id("filter1.F2"));
    WebList lstResultTable = new WebList(Frames.defaultContent, "dataExplorerTable1");

    protected BrugersoegningPopupWindow(OpgaveOverblikForTilsyn_PrimaerSubPage parentPage) {
        super(By.id("filter1.F3"), parentPage);
    }

    public BrugersoegningPopupWindow fillForm(PsrmUserModel user) {
        Utils.setTextBoxTextIfNotNull(txtBrugerID, user.getSagsbehanlderId());
        Utils.setTextBoxTextIfNotNull(txtEfternavn, user.getEfternavn());
        Utils.setTextBoxTextIfNotNull(txtFornavn, user.getFornavn());

        return this;
    }

    public BrugersoegningPopupWindow activateSoeg() {
        btnSoeg.click();

        return this;
    }

    public OpgaveOverblikForTilsyn_PrimaerSubPage activateSoegExpectOneResult() {
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());

        btnSoeg.click();

        resolver.waitForPopupToClose(10000);

        return parentPage;
    }

    public OpgaveOverblikForTilsyn_PrimaerSubPage selectUser(String userId) {
        PopupWindowResolver resolver = new PopupWindowResolver(Engine.getDriver());

        lstResultTable.clickCell("Bruger", userId);

        resolver.waitForPopupToClose(10000);

        return parentPage;
    }
}

