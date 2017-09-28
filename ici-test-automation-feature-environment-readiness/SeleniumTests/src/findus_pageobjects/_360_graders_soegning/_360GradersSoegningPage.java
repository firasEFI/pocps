package findus_pageobjects._360_graders_soegning;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.WebList;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_KontoSubPage;
import findus_pageobjects._360_graders_overblik._360GradersOverblik_PartsoplysningerSubPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;
import findus_pageobjects._360_graders_overblik._360GradersOverblikPage;

public class _360GradersSoegningPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("360 graders søgning", 5);

    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));
    final Dropdown cboSoegEfter = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));
    final Button anTLZ1Refresh = new Button(Frames.tabPage, By.id("anTLZ1Refresh"));
    final WebList lstSearchResults = new WebList(Frames.tabPage, "dataExplorerTable1");

    public _360GradersSoegningPage(){
        super(synchronizer);
    }

    // region "Navgation elements"

    public _360GradersSoegning_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new _360GradersSoegning_PrimaerSubPage(this);
    }

    public _360GradersSoegning_SkyldnereOgFordringshavereSubPage selectSearchForSkyldnereOgFordringshavere() {
        cboSoegEfter.pick("DK-360QRYQ1");

        return new _360GradersSoegning_SkyldnereOgFordringshavereSubPage(this);
    }

    public _360GradersSoegning_SagerSubPage selectSearchForSager() {
        cboSoegEfter.pick("DK-360QRYQ6");

        return new _360GradersSoegning_SagerSubPage(this);
    }

    public _360GradersSoegning_AndenAktoerSubPage selectSearchForAndenAktoer() {
        cboSoegEfter.pick("DK-360QRYQ7");

        return new _360GradersSoegning_AndenAktoerSubPage(this);
    }

    public _360GradersSoegning_DokumenterSubPage selectSearchForDokumenter() {
        cboSoegEfter.pick("DK-360QRYQ8");

        return new _360GradersSoegning_DokumenterSubPage(this);
    }

    public _360GradersSoegning_FordringSubPage selectSearchForFordring() {
        cboSoegEfter.pick("DK-360QRYQ9");

        return new _360GradersSoegning_FordringSubPage(this);
    }

    public _360GradersSoegning_PaymentSubPage selectSearchForPayment() {
        cboSoegEfter.pick("DK-360QRYQ10");

        return new _360GradersSoegning_PaymentSubPage(this);
    }

    public _360GradersSoegning_TodosSubPage selectSearchForTodos() {
        cboSoegEfter.pick("DK-360QRYQ11");

        return new _360GradersSoegning_TodosSubPage(this);
    }

    public _360GradersSoegning_AddressSubPage selectSearchForAddress() {
        cboSoegEfter.pick("DK-360QRYQ12");

        return new _360GradersSoegning_AddressSubPage(this);
    }

    public _360GradersSoegningPage activateSoeg() {
        anTLZ1Refresh.click();

        return new _360GradersSoegningPage();
    }

    public _360GradersOverblikPage selectFirstSearchResultNavn() {
        this.lstSearchResults.clickCell("Navn", 0);

        return new _360GradersOverblikPage();
    }

    public _360GradersOverblikPage selectSearchResultNavnByIdvaerdi(String idvaerdi) {
        if(idvaerdi == null || idvaerdi.isEmpty())
            throw new IllegalArgumentException("idvaerdi cannot be null or empty");

        this.lstSearchResults.clickCell("ID værdi", idvaerdi, "Navn");

        return new _360GradersOverblikPage();
    }

    public _360GradersOverblik_KontoSubPage selectKonto(SkyldnereOgFordringsHavereSearchArgs skyldner) {
        if(skyldner == null)
            throw new IllegalArgumentException("dataPaaSkyldner cannot be null");

        if(skyldner.getId() != null)
            this.lstSearchResults.clickCell("ID værdi", convertCPRToIDVaerdi(skyldner.getId()), "Konto");
        else if(skyldner.getSkyldnerId() != null)
            this.lstSearchResults.clickCell("Internt ID", skyldner.getSkyldnerId(), "Konto");
        else if(skyldner.getFuldeNavn() != null)
            this.lstSearchResults.clickCell("Navn", skyldner.getFuldeNavn(), "Konto");
        else
            throw new IllegalArgumentException("skyldner does not contain any data to locate search results");

        return new _360GradersOverblik_KontoSubPage();
    }

    public _360GradersOverblik_PartsoplysningerSubPage selectNavn(SkyldnereOgFordringsHavereSearchArgs skyldner) {
        if(skyldner == null)
            throw new IllegalArgumentException("dataPaaSkyldner cannot be null");
        if(skyldner.getId() != null)
            this.lstSearchResults.clickCell("ID værdi", convertCPRToIDVaerdi(skyldner.getId()), "Navn");
        else if(skyldner.getSkyldnerId() != null)
            this.lstSearchResults.clickCell("Internt ID", skyldner.getSkyldnerId(), "Navn");
        else if(skyldner.getFuldeNavn() != null)
            this.lstSearchResults.clickCell("Navn", skyldner.getFuldeNavn(), "Navn");
        else
            throw new IllegalArgumentException("skyldner does not contain any data to locate search results");

        return new _360GradersOverblik_PartsoplysningerSubPage(new _360GradersOverblikPage());
    }

    private String convertCPRToIDVaerdi (String cpr){
        return cpr.substring(0,6)+"-xxxx";
    }

    // endregion

}

