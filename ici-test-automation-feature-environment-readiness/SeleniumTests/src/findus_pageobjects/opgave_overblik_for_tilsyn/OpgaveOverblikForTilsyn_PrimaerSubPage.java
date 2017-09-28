package findus_pageobjects.opgave_overblik_for_tilsyn;

import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.SubPage;
import findus_pageobjects.WebList;
import findus_pageobjects.opgave.OpgavePage;
import findus_pageobjects.opgave.Opgave_PrimaerSubPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class OpgaveOverblikForTilsyn_PrimaerSubPage extends SubPage<OpgaveOverblikForTilsynPage> {

    final WebList lstTable = new WebList(Frames.tabPage, "dataExplorerDisplay1");
    final Input txtTildeltBruger = new Input(Frames.tabPage, By.id("assignedUser"));
    final Button btnActivateSoeg = new Button(Frames.tabPage, By.id("anTLZ1Refresh"));
    final Button btnAssignedUser_search = new Button(Frames.tabPage, By.id("assignedUser_search"));

    public OpgaveOverblikForTilsyn_PrimaerSubPage(OpgaveOverblikForTilsynPage parentPage) {
        super(parentPage);
    }

    public OpgaveOverblikForTilsyn_PrimaerSubPage fillForm(OpgaveOverblikForTilsyn_PrimaerSubPageParm opgaveOverblikForTilsyn_primaerSubPageParm){
        txtTildeltBruger.sendKeys(opgaveOverblikForTilsyn_primaerSubPageParm.getTildeltBruger());

        return this;
    }

    public OpgaveOverblikForTilsyn_PrimaerSubPage activateSoeg(){
        btnActivateSoeg.click();
        return this;
    }

    public Opgave_PrimaerSubPage selectOpgaveByIndex(int rowIndex){
        lstTable.clickCell("Opgavetype", rowIndex);

        return new Opgave_PrimaerSubPage(new OpgavePage());
    }

    public BrugersoegningPopupWindow activateSøgTildelBruger() {
        PopupWindowResolver<BrugersoegningPopupWindow> popupResolver = new PopupWindowResolver<>(Engine.getDriver());

        this.btnAssignedUser_search.click();

        BrugersoegningPopupWindow popup = popupResolver.waitForPopup(new PopupWindowResolver.GetPopupWindow<BrugersoegningPopupWindow>() {
            @Override
            public BrugersoegningPopupWindow GetWindow() {
                return new BrugersoegningPopupWindow(OpgaveOverblikForTilsyn_PrimaerSubPage.this);
            }
        },10000);

        return popup;
    }
}
