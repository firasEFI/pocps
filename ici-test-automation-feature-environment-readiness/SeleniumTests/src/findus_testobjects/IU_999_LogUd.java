package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.LoginPage;

public class IU_999_LogUd {

    private BasePsrmPage page;

    public IU_999_LogUd(BasePsrmPage page) {
        this.page = page;
    }

    public LoginPage execute() {

        // return this.userMenu().logUd();
        return new LoginPage();
    }
}
