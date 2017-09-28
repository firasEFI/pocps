package findus_pageobjects.wizards.markering_af_fordringer;

import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import org.openqa.selenium.By;

public class MarkeringAfFordringerWizardPage extends BaseWizardPage {

    public MarkeringAfFordringerWizardPage() {
        super("Markering af fordringer");
    }

// region "PageElements"
    final Button btnSoeg = new Button(Frames.uiMap, By.id("ok"));
    final Button btnVaelgAlt = new Button(Frames.uiMap, By.id("select_all_button"));
    final Button btnFravaelgAlt = new Button(Frames.uiMap, By.id("deselect_all_button"));
    final Button btnMarkerFordringer = new Button(Frames.uiMap, By.id("ok"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));
    final Input txtChkvalerr = new Input(Frames.uiMap, By.id("chkValErr"));
    final Input txtKonto = new Input(Frames.uiMap, By.id("accountIdInf"));
    final Input txtMarkeringstype = new Input(Frames.uiMap, By.id("procFlowTypeInf"));
    final Input txtInddrivelsessag = new Input(Frames.uiMap, By.id("collectionStepInf"));
    final Input txtMarkeringsdato = new Input(Frames.uiMap, By.id("markDateInf"));
    final Input txtJournaliseringsId = new Input(Frames.uiMap, By.id("journalisingId"));
    final Dropdown cboMarkeringshandling = new Dropdown(Frames.uiMap, By.id("actionType"));
    final Dropdown cboAarsag = new Dropdown(Frames.uiMap, By.id("reasonInf"));

// endregion

}

