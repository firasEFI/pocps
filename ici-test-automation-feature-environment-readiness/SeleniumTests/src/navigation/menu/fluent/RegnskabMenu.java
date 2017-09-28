package navigation.menu.fluent;

import navigation.menu.fluent.FluentMenu;
import navigation.menu.fluent.SoegTilfoejMenu;
import icisel.pageobjects.elements.Menu;

public class RegnskabMenu extends FluentMenu {

    public RegnskabMenu(Menu menu) {
        super(menu);
    }

    public Menu masseGodkendelse() {
        String subMenuText = "Masse godkendelse";
        return getSubmenu(subMenuText);
    }

    public Menu individuelUdbetaling() {
        String subMenuText = "Individuel udbetaling";
        return getSubmenu(subMenuText);
    }

    public Menu soegBetalingsEvne() {
        String subMenuText = "Søg betalingsevne";
        return getSubmenu(subMenuText);
    }

    public Menu balanceForFordringer() {
        String subMenuText = "Balance for fordringer";
        return getSubmenu(subMenuText);
    }

    public Menu bankhaendelse() {
        String subMenuText = "Bankhændelse";
        return getSubmenu(subMenuText);
    }

    public Menu betalinger() {
        String subMenuText = "Betalinger";
        return getSubmenu(subMenuText);
    }

    public Menu detaljeretVisningAfRenter() {
        String subMenuText = "Detaljeret visning af renter";
        return getSubmenu(subMenuText);
    }

    public Menu fordringensFinansielleForloeb() {
        String subMenuText = "Fordringens finansielle forløb";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu fordringsProcesKontrol() {
        String subMenuText = "Fordrings proceskontrol";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu fordringsaendringer() {
        String subMenuText = "Fordringsændringer";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu justeringer() {
        String subMenuText = "Justeringer";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu kontoFinansielForloeb() {
        String subMenuText = "Konto finansiel forløb";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu nedskrivning() {
        String subMenuText = "Nedskrivning";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu overbetalingsProces() {
        String subMenuText = "Overbetalingsproces";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu procesForAendringer() {
        String subMenuText = "Proces for ændringer";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu regnskabskalender() {
        String subMenuText = "Regnskabskalender";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu renteRegulering() {
        String subMenuText = "Rente regulering";
        return getSubmenu(subMenuText);
    }

    public Menu saldokontrol() {
        String subMenuText = "Saldokontrol";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu tilbagebetalingskilde() {
        String subMenuText = "Tilbagebetalingskilde";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu ftVoucher() {
        String subMenuText = "FT Voucher";
        return getSubmenu(subMenuText);
    }
}
