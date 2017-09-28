package findus_pageobjects.sagsbehandlingsskridt;

import findus_pageobjects.SubPage;
import findus_pageobjects.WebList;
import findus_pageobjects.brevoplysninger.BrevoplysningerPage;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class Sagsbehandlingsskridt_LogTabPage extends SubPage<SagsbehandlingsskridtPage>{

    final WebList lstLog1 = new WebList(Frames.tabPage, "dataExplorerTable1");

    protected  Sagsbehandlingsskridt_LogTabPage(SagsbehandlingsskridtPage parentPage) {
        super(parentPage);
    }

    public BrevoplysningerPage selectFirstBrevoplysninger() {


        //temp hack
        String xpath = "//*[@id = 'dataExplorerTableBody1']/tr[1]/td[6]";
        PageElement cell = new PageElement(Frames.tabPage, By.xpath("//*[@id = 'dataExplorerTableBody1']/tr[1]/td[6]"));
        cell.click();

        return new BrevoplysningerPage();
    }
}
