package findus_pageobjects._360_graders_overblik;

import findus_pageobjects.Accordion;
import findus_pageobjects.SubPage;
import findus_pageobjects.WebList;
import findus_pageobjects.betalingshaendelse.BetalingshaendelsePage;
import findus_testobjects.IU_600;
import findus_testobjects.IU_616;
import findus_testobjects.IU_598;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;

public class _360GradersOverblik_KontoSubPage extends SubPage<_360GradersOverblikPage> {

    final Button btnRentesimulering = new Button(Frames.zoneMapFrame_1, By.id("forecastButton"));
    final Input txtDato = new Input(Frames.zoneMapFrame_1, By.id("forecastDt"));
    
    final Accordion accFinansielleTransaktioner = new Accordion(new PageElement(Frames.tabPage, By.id("zoneHeader3")), By.id("dataExplorerTable3"));
    final WebList lstFinansielleTransaktioner = new WebList(Frames.tabPage, "dataExplorerTable3");

    final WebList lstKontoOversigt = new WebList(Frames.tabPage, "dataExplorerTable4");

    public _360GradersOverblik_KontoSubPage() {
        super(new _360GradersOverblikPage());
    }
    protected _360GradersOverblik_KontoSubPage(_360GradersOverblikPage parentPage) {
        super(parentPage);
    }

    PageElement hardcodedGotoBetalingshaendelse = new PageElement(Frames.tabPage, By.xpath("//*[@id=\"dataExplorerTableBody3\"]/tr[4]/td[2]/a/span/img"));

    public IU_616 iu_616_fremsoegKontoudtogForFordringshaverFraFanenKonto = new IU_616(this);

    public IU_600 iu_600_findUdbetalingPaaSkyldnerenPaa360GradersOverblikFanenSagsbehandling = new IU_600(this);

    public BetalingshaendelsePage activateFinansielleTransaktionerIndbetaling() {

        activateFinansielleTransaktioner();
        hardcodedGotoBetalingshaendelse.click();
        //lstKontoOversigt.clickCell("Aktuelle beløb", "-9.455,53kr.", "Gå til"); //FIXME, also with non-hardcoded input

        return new BetalingshaendelsePage();
    }

    public void activateKontoOversigtTransaktionerUdbetaling() {
        //TODO changed to not hardcoded udbetaling as this table contains both indbetaling and udbetaling, use webList instead!
        //PageElement transaktionerFoersteIndbetaling = new PageElement(Frames.tabPage, By.xpath(".//*[@id='dataExplorerTableBody4']/tr[2]/td[5]/a/span"));
        //transaktionerFoersteIndbetaling.click();

        accFinansielleTransaktioner.ensureExpanded();

        this.lstFinansielleTransaktioner.clickCell("Aktuelle beløb", "4.500,00kr.", "Gå til");
    }

    public void activateFinansielleTransaktioner() {
        accFinansielleTransaktioner.ensureExpanded();
    }
    
    /**
     * Funktion hiver datoværdien ud af datofeltet "Dato" og returnerer dette som en date
     * @return PSRM-datoen som date
     * @throws ParseException
     */
    public Date getPsrmDate() throws ParseException{
        //Opret datoværdi til returnering
        String psrmDato = null;
        
        //Klik på knappen Rentesimulering
        btnRentesimulering.click();
        
        //Hiv datoværdi ud af feltet "Dato"
        psrmDato = txtDato.getInputText();
        System.out.println("PSRM dato: " + psrmDato); //FIXME Slet dette efter test
        
        //Omdan datoen fra String til date
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(psrmDato);
        
        return date;
    }
}
