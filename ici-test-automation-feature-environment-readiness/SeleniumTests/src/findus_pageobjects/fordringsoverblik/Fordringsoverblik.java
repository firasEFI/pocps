package findus_pageobjects.fordringsoverblik;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;

public class Fordringsoverblik extends BasePsrmPage {
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Fordringsoverblik", 5);

    public Fordringsoverblik(){
        super(synchronizer);
    }
}
