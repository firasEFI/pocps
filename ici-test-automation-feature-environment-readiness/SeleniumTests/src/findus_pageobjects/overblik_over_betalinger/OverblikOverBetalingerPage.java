package findus_pageobjects.overblik_over_betalinger;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;

/**
 * Created by nielsjes on 31-08-2017.
 */
public class OverblikOverBetalingerPage extends BasePsrmPage{

    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Overblik over betalinger", 5);

    public OverblikOverBetalingerPage(){
        super(synchronizer);
    }

}
