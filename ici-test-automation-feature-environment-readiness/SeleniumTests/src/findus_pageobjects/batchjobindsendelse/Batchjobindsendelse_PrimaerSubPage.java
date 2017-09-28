package findus_pageobjects.batchjobindsendelse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;

import findus_pageobjects.SubPage;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;

public class Batchjobindsendelse_PrimaerSubPage extends SubPage<BatchjobindsendelsePage> {
    protected Batchjobindsendelse_PrimaerSubPage(BatchjobindsendelsePage parentPage) {
        super(parentPage);
    }
    
    final Input today = new Input(Frames.tabPage, By.id("BATCH_START_DTTM_FWDTTM_P1"));
    final Input timeOfDay = new Input(Frames.tabPage, By.id("BATCH_START_DTTM_FWDTTM_P2"));
    
    /**
     * Funktion hiver datoværdien ud af datofeltet ud for "Ønsket udførselstidspunkt" og returnerer dette som en date
     * @return PSRM-datoen som date
     * @throws ParseException
     */
    public Date getPsrmDate() throws ParseException{
        String psrmDato = null;
        psrmDato = today.getInputText();
        System.out.println("PSRM dato: " + psrmDato); //FIXME Slet dette efter test
        //Omdan datoen fra String til date
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(psrmDato);
        
        return date;
    }
    
}
