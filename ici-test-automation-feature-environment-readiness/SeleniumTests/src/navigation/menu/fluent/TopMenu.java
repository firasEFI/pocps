package navigation.menu.fluent;

import findus_pageobjects._360_graders_soegning._360GradersSoegningPage;
import icisel.pageobjects.elements.Menu;

public class TopMenu extends FluentMenu {

    TopMenu(Menu menu) {
        super(menu);
    }

    public _360GradersSoegningPage a360GradersSoegning() {
        String subMenuName = "360 graders søgning";
         getSubmenu(subMenuName);
         return new _360GradersSoegningPage();
    }

    public AktivMenu aktiv() {
        String subMenuText = "Aktiv";
        return new AktivMenu(getSubmenu(subMenuText));
    }

    public FaktureringMenu fakturering() {
        String subMenuText = "Fakturering";
        return new FaktureringMenu(getSubmenu(subMenuText));
    }

    public FormularerMenu formularer() {
        String subMenuText = "Formularer";
        return new FormularerMenu(getSubmenu(subMenuText));
    }

    public IndbetalingerMenu indbetalinger() {
        String subMenuText = "Indbetalinger";
        return new IndbetalingerMenu(getSubmenu(subMenuText));
    }

    public OpgavestyringMenu opgavestyring() {
        String subMenuText = "Opgavestyring";
        return new OpgavestyringMenu(getSubmenu(subMenuText));
    }

    public RegistreringMenu registrering() {
        String subMenuText = "Registrering";
        return new RegistreringMenu(getSubmenu(subMenuText));
    }

    public RegnskabMenu regnskab() {
        String subMenuText = "Regnskab";
        return new RegnskabMenu(getSubmenu(subMenuText));
    }

    public SagsbehandlingMenu sagsbehandling() {
        String subMenuText = "Sagsbehandling";
        return new SagsbehandlingMenu(getSubmenu(subMenuText));
    }

    public SatserMenu satser() {
        String subMenuText = "Satser";
        return new SatserMenu(getSubmenu(subMenuText));
    }

    public SelvbetjeningMenu selvbetjening() {
        String subMenuText = "Selvbetjening";
        return new SelvbetjeningMenu(getSubmenu(subMenuText));
    }

    public OpgaveMenu opgave() {
        String subMenuText = "Opgave";
        return new OpgaveMenu(getSubmenu(subMenuText));
    }

    public MinePraeferencerMenu minePraeferencer() {
        String subMenuText = "Mine præferencer";
        return new MinePraeferencerMenu(getSubmenu(subMenuText));
    }

    public BatchMenu batch() {
        String subMenuText = "Batch";
        return new BatchMenu(getSubmenu(subMenuText));
    }

    public EksternBeskedMenu eksternBesked() {
        String subMenuText = "Ekstern besked";
        return new EksternBeskedMenu(getSubmenu(subMenuText));
    }

    public HistorikMenu historik() {
        String subMenuText = "Historik";
        return new HistorikMenu(getSubmenu(subMenuText));
    }

}
