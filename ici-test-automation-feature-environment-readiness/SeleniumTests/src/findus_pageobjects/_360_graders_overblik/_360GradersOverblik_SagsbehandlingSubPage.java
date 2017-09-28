package findus_pageobjects._360_graders_overblik;

import findus_datamodels.Sag;
import findus_pageobjects.Accordion;
import findus_pageobjects.SubPage;
import findus_pageobjects.WebList;
import findus_pageobjects.betalingshaendelse.BetalingshaendelsePage;
import findus_pageobjects.betalingshaendelse.Betalingshaendelse_IndbetalingerSubPage;
import findus_pageobjects.indbetaling.IndbetalingPage;
import findus_pageobjects.information.InformationPage;
import findus_pageobjects.information.InformationPage_PrimaerSubPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerAfdragsordningPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerOcrLinjePage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerPaakravssagPage;
import findus_testobjects.IU_606_AabnSkyldnersSag;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import icisel.pageobjects.frames.IFrame;

import org.openqa.selenium.By;

import javax.ws.rs.NotSupportedException;

public class _360GradersOverblik_SagsbehandlingSubPage extends SubPage<_360GradersOverblikPage> {

    private Accordion betalingshaendelserForKontoenAccordion = new Accordion(new PageElement(Frames.tabPage, By.id("zoneHeader7")));
    private Accordion udbetalingAccordion = new Accordion(new PageElement(Frames.tabPage, By.id("zoneHeader8")));
    private Accordion noterAccordion = new Accordion(new PageElement(Frames.tabPage, By.id("zoneHeader2")));
    private Accordion overblikOCRAccordion = new Accordion(new PageElement(Frames.tabPage, By.id("zoneHeader6")));

    WebList lstSager = new WebList(Frames.tabPage, "dataExplorerTable1");
    WebList lstNoter = new WebList(Frames.tabPage, "dataExplorerTable2");
    WebList lstBetalingshaendelserForKontoen = new WebList(Frames.tabPage, "dataExplorerTable7");
    WebList lstOCR = new WebList(Frames.tabPage, "dataExplorerTable6");    WebList betalingsHaendelseForKontoentTabel = new WebList(Frames.tabPage, "dataExplorerZone7");
    public final IU_606_AabnSkyldnersSag iu_606_aabnSkyldnersSag = new IU_606_AabnSkyldnersSag(this);

    protected _360GradersOverblik_SagsbehandlingSubPage(_360GradersOverblikPage parentPage) {
        super(parentPage);
    }

    public Sagsbehandlingsskridt_PrimaerAfdragsordningPage selectFirstAfdragsordningssag() {
        this.lstSager.clickCell("Sagstype", "Afdragsordning", "Sags ID");

        return new Sagsbehandlingsskridt_PrimaerAfdragsordningPage();
    }

//    public SagsbehandlingsskridtPage selectFirstSag() {
//        this.lstSager.clickCell("Sags ID", 0);

//
////        PageElement firstSag = new PageElement(Frames.tabPage, By.xpath("//a[node() = '62206597717212']"));
////        //FIXME hardcoded?
////        firstSag.waitFor();
////        firstSag.click();

//
//        return new SagsbehandlingsskridtPage();

//    }

    public Sagsbehandlingsskridt_PrimaerPaakravssagPage selectFirstPaakravssag() {
        this.lstSager.clickCell("Sagstype", "Påkrav", "Sags ID");

        return new Sagsbehandlingsskridt_PrimaerPaakravssagPage();
    }

    public boolean containsSag(String sagsId) {
        throw new RuntimeException("Not implemented");
    }

    public Sag getSag(String sagsId) {
        throw new RuntimeException("Not implemented");
    }

public Sagsbehandlingsskridt_PrimaerPaakravssagPage selectSpecificSag(String sagsID) {
        this.lstSager.clickCell("Sags ID", sagsID, "Sags ID");

        return new Sagsbehandlingsskridt_PrimaerPaakravssagPage();
    }
    
    public String getSagstype(String sagsID){
        String sagsType = this.lstSager.lookupCellValue("Sags ID", sagsID, "Sagstype");
        return sagsType;
    }













//    public SagsbehandlingsskridtPage selectFirstAlternative() {
//
//
//        //Jesper test
//        PageElement firstSag = new PageElement(Frames.tabPage, By.xpath("//*[@id=\"dataExplorerTableBody1\"]/tr/td[3]/a/span"));
//        firstSag.waitFor();
//        firstSag.clickElementUntilNotPresent();
//
//        return new SagsbehandlingsskridtPage();
//    }


    public Betalingshaendelse_IndbetalingerSubPage gaaTilSideBetalingshaendelseSubsideIndbetalinger() {
        betalingshaendelserForKontoenAccordion.ensureExpanded();
        new PageElement(Frames.tabPage, By.xpath(".//*[@id='dataExplorerTableBody7']/tr/td[3]/a/span")).click(); //todo alter to not hardcoded link
        BetalingshaendelsePage betalingshaendelsePage = new BetalingshaendelsePage();
        return betalingshaendelsePage.activateIndbetalinger();
    }

    public IndbetalingPage gaaTilUdbetalingOgKontrollerKorrektStatus(String status) {
    	
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	
        udbetalingAccordion.ensureExpanded();
        new PageElement(Frames.tabPage, By.xpath(".//*[@id='payouts']/table/tbody/tr/td[1]/a")).click(); // todo alter to not hardcoded link
        IndbetalingPage betalingshaendelsePage = new IndbetalingPage();
        boolean betalingKorrektStatus = betalingshaendelsePage.betalingsHarStatus(status);
        //if(betalingKorrektStatus) {
            return betalingshaendelsePage;


        //}
        //return null;
    }

    public BetalingshaendelsePage klikPaaPilenPaaLinjenBetalingsHaendelsenForKontoenDerefterLinket(String lookupColumnHeader, String lookupValue, String clickColumnHeader) {
        betalingshaendelserForKontoenAccordion.ensureExpanded();
        betalingsHaendelseForKontoentTabel.clickCell(lookupColumnHeader, lookupValue, clickColumnHeader);

        return new BetalingshaendelsePage();
    }


    public _360GradersOverblik_SagsbehandlingSubPage expandOverblikOCRlinjerAccordion() {
        overblikOCRAccordion.ensureExpanded();
        return this;
    }

    public Sagsbehandlingsskridt_PrimaerOcrLinjePage activateSpecificLinkTilOCRSagsbehandlingsskridt(int rowIndex){
        lstOCR.clickCell("OCR proces", rowIndex);
        return new Sagsbehandlingsskridt_PrimaerOcrLinjePage();
    }

    public InformationPage_PrimaerSubPage klikPaaModkravDaekningsloesBetaling(String noteId) {
        lstNoter.clickCell("Note ID", String.format("Note vedhæftet - %s , Systemoprettet", noteId));
        return new InformationPage_PrimaerSubPage(new InformationPage());
    }}
