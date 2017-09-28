package navigation.menu.fluent;

import navigation.menu.fluent.FluentMenu;
import navigation.menu.fluent.SoegTilfoejMenu;
import icisel.pageobjects.elements.Menu;

public class IndbetalingerMenu extends FluentMenu {

    public IndbetalingerMenu(Menu menu) {
        super(menu);
    }

    public SoegTilfoejMenu betaling() {
        String subMenuText = "Betaling";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu betalingFraSap38() {
        String subMenuText = "Betaling fra SAP38";
        return getSubmenu(subMenuText);
    }

    public Menu betalingIndbetalingsSoegning() {
        String subMenuText = "Betaling/indbetalingssøgning";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu betalingshaendelse() {
        String subMenuText = "Betalingshændelse";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu betalingsproces() {
        String subMenuText = "Betalingsproces";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu finansielleTransaktionerPaaBetaling() {
        String subMenuText = "Finansielle transaktioner på betaling";
        return getSubmenu(subMenuText);
    }

    public Menu indbetalingTilfoej() {
        String subMenuText = "Indbetaling (tilføj)";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu indbetalingsKilde() {
        String subMenuText = "Indbetalingskilde";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu indbetalingsKontrol() {
        String subMenuText = "Indbetalingskontrol";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu indbetalingskontrolProcess() {
        String subMenuText = "Indbetalingskontrol proces";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu kontoBetalingsforloeb() {
        String subMenuText = "Konto betalingsforløb";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu manuelBetalingshaendelse() {
        String subMenuText = "Manuel betalingshændelse";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu manuelIndbetalingTilfoej() {
        String subMenuText = "Manuel indbetaling (tilføj)";
        return getSubmenu(subMenuText);
    }

    public Menu overfoerIndbetalingFraVentekonto() {
        String subMenuText = "Overfør indbetaling fra ventekonto";
        return getSubmenu(subMenuText);
    }

}
