package findus_pageobjects._360_graders_overblik;

import findus_pageobjects.SubPage;
import findus_pageobjects.WebList;
import findus_pageobjects.fordringshaverrelation.FordringshaverrelationPage;
import findus_pageobjects.fordringshaverrelation.Fordringshaverrelation_PrimaerSubPage;
import findus_pageobjects.fordringsoverblik.Fordringsoverblik;
import findus_pageobjects.fordringsoverblik.Fordringsoverblik_PrimaerSubPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;
import utils.tools.TO_Tools;

public class _360GradersOverblik_FordringerSubPage extends SubPage<_360GradersOverblikPage> {

    //TODO få integreret at klikke på disse knapper i WebList
    final Button tempVifte1 = new Button(Frames.tabPage, By.xpath("//*[@id='dataExplorerTableBody2']/tr[1]/td[1]/table/tbody/tr/td[1]/img"));
    private Button tempVifte2;

    final WebList lstSearchResults = new WebList(Frames.tabPage, "dataExplorerTable2");
    private WebList lstVifteResult = new WebList(Frames.tabPage, "dataExplorerTable3");

    public _360GradersOverblik_FordringerSubPage(_360GradersOverblikPage parentPage) {
        super(parentPage);
    }

    public _360GradersOverblik_FordringerSubPage selectSpecificFordringshaverBroadcast(int rowIndex) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.lstSearchResults.clickCell("", rowIndex);

        return this;
    }

    public Fordringshaverrelation_PrimaerSubPage selectSpecificFordringshaverInformation(int rowIndex) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.lstSearchResults.clickCell("Information", rowIndex);

        return new Fordringshaverrelation_PrimaerSubPage(new FordringshaverrelationPage());
    }

    public Fordringsoverblik_PrimaerSubPage selectSpecificFordringshaverInformation2() {
        try { //TODO Delete me
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.lstVifteResult.clickCell("Fordringstype", 0);

        return new Fordringsoverblik_PrimaerSubPage(new Fordringsoverblik());
    }

    //TODO Fix lokale variable til at bruge global WebList når den virker med dette table
    public _360GradersOverblik_FordringerSubPage selectSpecificFordringshavervifte(int rowIndex){
        Button tempVifte2 = new Button(Frames.tabPage, By.xpath("//*[@id='dataExplorerTableBody2']/tr["+rowIndex+"]/td[1]/table/tbody/tr/td[1]/img"));
        tempVifte2.click();
        TO_Tools.sleep(1000);
        return this;
    }

    //TODO Fix lokal variabel til at bruge global WebList når den virker med dette table
    public Fordringsoverblik_PrimaerSubPage activateFordring(){
        final Button tempLst1 = new Button(Frames.tabPage, By.xpath("//*[@id='dataExplorerTableBody3']/tr/td[2]/a"));
        tempLst1.click();
        return new Fordringsoverblik_PrimaerSubPage(new Fordringsoverblik());
    }
}
