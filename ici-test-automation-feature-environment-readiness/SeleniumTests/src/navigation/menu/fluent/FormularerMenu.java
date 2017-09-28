package navigation.menu.fluent;

import navigation.menu.fluent.FluentMenu;
import navigation.menu.fluent.SoegTilfoejMenu;
import icisel.pageobjects.elements.Menu;

public class FormularerMenu extends FluentMenu {

    public FormularerMenu(Menu menu) {
        super(menu);
    }

    public SoegTilfoejMenu fordringsformular() {
        String subMenuText = "Fordringsformular";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu formularkontrol() {
        String subMenuText = "Formularkontrol";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu formularproces() {
        String subMenuText = "Formularproces";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu registreringsskabelon() {
        String subMenuText = "Registreringsskabelon";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu sidehovedForBatchFormularer() {
        String subMenuText = "Sidehoved for batch formularer";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

}
