package navigation.menu.fluent;

import findus_pageobjects._360_graders_overblik._360GradersOverblikPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_PartsoplysningerSubPage;
import findus_pageobjects.partsforloeb.PartsforloebPage;
import findus_pageobjects.wizards.grundlag_for_betalingsevne.GrundlagForBetalingsevneWizardPage;
import icisel.pageobjects.elements.Menu;
import icisel.pageobjects.frames.Frame;
import icisel.pageobjects.frames.Frames;

public class PartKontekstMenu extends FluentMenu {
    final public Frame subMenuFrame = Frames.main;

    public PartKontekstMenu(Menu menu) {
        super(menu);
    }

    public Menu gaaTilPart() {
        String subMenuText = "Gå til part";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public PartsforloebPage gaaTilPartensForloeb() {
        String subMenuText = "Gå til partens forløb";
        getSubmenu(subMenuFrame, subMenuText);
        return new PartsforloebPage();
    }

    public SoegTilfoejMenu brevoplysning() {
        String subMenuText = "Brevoplysning";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public _360GradersOverblik_PartsoplysningerSubPage gaaTil360GradersOverblik() {
        String subMenuText = "Gå til 360 graders overblik";
        getSubmenu(subMenuFrame, subMenuText);
        return new _360GradersOverblik_PartsoplysningerSubPage(new _360GradersOverblikPage());
    }

    public SoegTilfoejMenu gaaTilAnkesag() {
        String subMenuText = "Gå til ankesag";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public Menu gaaTilAutomatiskOpkaldsprogram() {
        String subMenuText = "Gå til automatisk opkaldsprogram";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu gaaTilInddrivelsessag() {
        String subMenuText = "Gå til inddrivelsessag";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public SoegTilfoejMenu gaaTilKonkurs() {
        String subMenuText = "Gå til konkurs";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public SoegTilfoejMenu gaaTilKonto() {
        String subMenuText = "Gå til konto";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public SoegTilfoejMenu gaaTilKundekontakt() {
        String subMenuText = "Gå til kundekontakt";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public Menu gaaTilRegistreringsskabelon() {
        String subMenuText = "Gå til registreringsskabelon";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu gaaTilRentefritagelse() {
        String subMenuText = "Gå til rentefritagelse";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public GrundlagForBetalingsevneWizardPage tilfoejBetalingsevne() {
        String subMenuText = "Tilføj betalingsevne";
        getSubmenu(subMenuFrame, subMenuText);

        return new GrundlagForBetalingsevneWizardPage();
    }

}
