package navigation.menu.fluent;

import findus_pageobjects.batchjobindsendelse.BatchjobindsendelsePage;
import icisel.pageobjects.elements.Menu;

public class SoegTilfoej_Batchjobindsendelse  {
    SoegTilfoejMenu menu;
    
    public SoegTilfoej_Batchjobindsendelse(SoegTilfoejMenu menu) {
        this.menu = menu;
    }
    
    public BatchjobindsendelsePage tilfoej(){
        menu.tilfoej();
        return new BatchjobindsendelsePage();
    }

}
