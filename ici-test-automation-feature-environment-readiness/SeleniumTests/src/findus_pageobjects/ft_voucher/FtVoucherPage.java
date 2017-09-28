package findus_pageobjects.ft_voucher;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class FtVoucherPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("FT Voucher", 5);

    public FtVoucherPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public FtVoucher_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new FtVoucher_PrimaerSubPage(this);
    }

    public FtVoucher_BilagsnummerSubPage selectSearchForBilagsnummer() {
        multiQueryZoneFilters1.pick("Bilagsnummer");

        return new FtVoucher_BilagsnummerSubPage(this);
    }

    public FtVoucher_AccountingDateSubPage selectSearchForAccountingDate() {
        multiQueryZoneFilters1.pick("Accounting Date");

        return new FtVoucher_AccountingDateSubPage(this);
    }

    public FtVoucher_VoucherDateSubPage selectSearchForVoucherDate() {
        multiQueryZoneFilters1.pick("Voucher Date");

        return new FtVoucher_VoucherDateSubPage(this);
    }


    // endregion

}

