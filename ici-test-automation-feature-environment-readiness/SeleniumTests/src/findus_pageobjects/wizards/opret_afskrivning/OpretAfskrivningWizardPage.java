package findus_pageobjects.wizards.opret_afskrivning;

import findus_datamodels.AfskrivningModel;
import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerAfdragsordningPage;
import findus_pageobjects.wizards.BaseWizardPage;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.elements.*;
import icisel.taxobjects.Fordring;
import org.openqa.selenium.By;

public class OpretAfskrivningWizardPage extends BaseWizardPage {

    final Button btnOpretAfskrivning = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='DK_CR_WOFF_BTN']"));
    final Button btnAnnuller = new Button(Frames.uiMap, By.xpath("//input[@oramdlabel='CANCEL_LBL']"));
    final Input txtChkvalerr = new Input(Frames.uiMap, By.id("chkValErr"));
    final Input txtErrorexist = new Input(Frames.uiMap, By.id("errorExist"));
    final Input txtKonto = new Input(Frames.uiMap, By.id("accountIdInf"));
    final Input txtDividendeProcent = new Input(Frames.uiMap, By.id("percentageValue"));
    final Input txtVirkningsdato = new Input(Frames.uiMap, By.id("effdate"));
    final Dropdown cboAfskrivningsmulighed = new Dropdown(Frames.uiMap, By.id("writeOffOption"));
    final Dropdown cboAfskrivningsaarsag = new Dropdown(Frames.uiMap, By.id("writeOffReason"));

    public OpretAfskrivningWizardPage() {
        super("Opret afskrivning");
    }

    public OpretAfskrivningWizardPage udfyldFormular(AfskrivningModel afskrivningModel)
    {
        return this;
    }

    public OpretAfskrivningWizardPage tilfoejFordring(Fordring fordring)
    {
        return new OpretAfskrivningWizardPage();
    }

    public Sagsbehandlingsskridt_PrimaerAfdragsordningPage AktiverOpretAfskrivning()
    {
        return new Sagsbehandlingsskridt_PrimaerAfdragsordningPage();
    }

    public BasePsrmPage AktiverAnnuller()
    {
        return new BasePsrmPage();
    }
}

