package navigation.menu.fluent;


import findus_pageobjects._360_graders_overblik._360GradersOverblikPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_FordringerSubPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_KontoSubPage;
import findus_pageobjects.wizards.afdragsordning.AfdragsordningWizardPage;
import findus_pageobjects.wizards.opret_afskrivning.OpretAfskrivningWizardPage;
import findus_pageobjects.wizards.opret_ocr_linje_for_part.OpretOcrLinjeForPartWizardPage;
import findus_pageobjects.wizards.opret_paakravssag.OpretPaakravssagWizardPage;
import icisel.pageobjects.elements.Menu;
import icisel.pageobjects.frames.Frame;
import icisel.pageobjects.frames.Frames;

public class KontoKontekstMenu extends FluentMenu {
    Frame subMenuFrame = Frames.main;

    KontoKontekstMenu(Menu menu) {
        super(menu);
    }

    public _360GradersOverblik_KontoSubPage gaaTilKonto() {
        String subMenuText = "Gå til konto";
        getSubmenu(subMenuFrame, subMenuText);
        return new _360GradersOverblik_KontoSubPage();
    }

    public Menu genaktiverSkyldner() {
        String subMenuText = "Genaktiver skyldner";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu omposterIndbetaling() {
        String subMenuText = "Ompostér indbetaling";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu createFee() {
        String subMenuText = "Create Fee";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu tilbagesendFordring() {
        String subMenuText = "Tilbagesend fordring";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu fordelBetalingManuelt() {
        String subMenuText = "Fordel betaling manuelt";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public OpretPaakravssagWizardPage opretPaakravssag() {
        String subMenuText = "Opret påkravssag";
        getSubmenu(subMenuFrame, subMenuText);
        return new OpretPaakravssagWizardPage();
    }

    public OpretAfskrivningWizardPage opretAfskrivning() {
        String subMenuText = "Opret afskrivning";

        getSubmenu(subMenuFrame, subMenuText);

        return new OpretAfskrivningWizardPage();
    }

    public Menu tilbagefoerAfskrivning() {
        String subMenuText = "Tilbagefør afskrivning";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu opretNedskrivning() {
        String subMenuText = "Opret nedskrivning";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public OpretOcrLinjeForPartWizardPage opretOcrLinje() {
        String subMenuText = "Opret OCR-linje";
        getSubmenu(subMenuFrame, subMenuText);
        return new OpretOcrLinjeForPartWizardPage();
    }

    public Menu opretOevrigSag() {
        String subMenuText = "Opret øvrig sag";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu opretKlagesag() {
        String subMenuText = "Opret klagesag";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public AfdragsordningWizardPage opretAfdragsordningssag() {
        String subMenuText = "Opret afdragsordningssag";
        getSubmenu(subMenuFrame, subMenuText);
        return new AfdragsordningWizardPage();
    }

    public Menu opretRentefritagelse() {
        String subMenuText = "Opret rentefritagelse";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu markerFordringer() {
        String subMenuText = "Marker fordringer";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu opretBobehandlingssag() {
        String subMenuText = "Opret bobehandlingssag";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu opretUdlandssag() {
        String subMenuText = "Opret udlandssag";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu returnerFordringer() {
        String subMenuText = "Returner fordringer";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu opretUdlaegssag() {
        String subMenuText = "Opret udlægssag";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu opretHenstandssag() {
        String subMenuText = "Opret henstandssag";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public _360GradersOverblik_FordringerSubPage gaaTil360GradersOverblik() {
        String subMenuText = "Gå til 360 graders overblik";
        getSubmenu(subMenuFrame, subMenuText);
        return new _360GradersOverblik_FordringerSubPage(new _360GradersOverblikPage());
    }

    public SoegTilfoejMenu gaaTilAfdragsordning() {
        String subMenuText = "Gå til afdragsordning";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public SoegTilfoejMenu gaaTilAnkesag() {
        String subMenuText = "Gå til ankesag";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public SoegTilfoejMenu gaaTilBetaling() {
        String subMenuText = "Gå til betaling";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public Menu gaaTilBetaling_indbetalingssoegning() {
        String subMenuText = "Gå til betaling/indbetalingssøgning";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu gaaTilBetalingsforloeb() {
        String subMenuText = "Gå til betalingsforløb";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public SoegTilfoejMenu gaaTilBetalingshaendelse() {
        String subMenuText = "Gå til betalingshændelse";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public SoegTilfoejMenu gaaTilBetalingsservice() {
        String subMenuText = "Gå til betalingsservice";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public Menu gaaTilFinansielKontoHistorik() {
        String subMenuText = "Gå til finansiel konto historik";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public SoegTilfoejMenu gaaTilFordring() {
        String subMenuText = "Gå til fordring";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public Menu gaaTilFordringensFinancielleForloeb() {
        String subMenuText = "Gå til fordringens financielle forløb";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public SoegTilfoejMenu gaaTilFordringshaverrelation() {
        String subMenuText = "Gå til fordringshaverrelation";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public Menu gaaTilFordringsaendringer() {
        String subMenuText = "Gå til fordringsændringer";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public SoegTilfoejMenu gaaTilHenvisningTilIndrivelsesmyndighed() {
        String subMenuText = "Gå til henvisning til inddrivelsesmyndighed";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public SoegTilfoejMenu gaaTilHoeringssag() {
        String subMenuText = "Gå til høringssag";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public SoegTilfoejMenu gaaTilJustering() {
        String subMenuText = "Gå til justering";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public SoegTilfoejMenu gaaTilNedskrivning() {
        String subMenuText = "Gå til nedskrivning";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

    public Menu gaaTilRentefritagelse() {
        String subMenuText = "Gå til rentefritagelse";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public Menu gaaTilRenteregulering() {
        String subMenuText = "Gå til renteregulering";
        return getSubmenu(subMenuFrame, subMenuText);
    }

    public SoegTilfoejMenu gaaTilSagsprocess() {
        String subMenuText = "Gå til sagsprocess";
        return new SoegTilfoejMenu(getSubmenu(subMenuFrame, subMenuText));
    }

}
