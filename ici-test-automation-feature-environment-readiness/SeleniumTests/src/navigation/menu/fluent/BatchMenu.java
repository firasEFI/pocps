package navigation.menu.fluent;

import navigation.menu.fluent.FluentMenu;
import navigation.menu.fluent.SoegTilfoejMenu;
import icisel.pageobjects.elements.Menu;

public class BatchMenu extends FluentMenu {

    public BatchMenu(Menu menu) {
        super(menu);
    }

    public SoegTilfoej_Batchjobindsendelse batchJobIndsendelse() {
        String subMenuText = "Batchjobindsendelse";
        return new SoegTilfoej_Batchjobindsendelse(new SoegTilfoejMenu(getSubmenu(subMenuText)));
    }

    public Menu batchkoerseltrae() {
        String subMenuText = "Batchkørselstræ";
        return getSubmenu(subMenuText);
    }

    public SoegTilfoejMenu forespoergsel() {
        String subMenuText = "Forespørgsel";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public SoegTilfoejMenu indsamlOgSendForespoergsel() {
        String subMenuText = "Indsaml og send forespørgsel";
        return new SoegTilfoejMenu(getSubmenu(subMenuText));
    }

    public Menu rapportafgivelse() {
        String subMenuText = "Rapportafgivelse";
        return getSubmenu(subMenuText);
    }

    public Menu rapportforloeb() {
        String subMenuText = "Rapportforløb";
        return getSubmenu(subMenuText);
    }

}
