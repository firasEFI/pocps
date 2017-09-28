package findus_pageobjects.sagsbehandlingsskridt;

import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class Sagsbehandlingsskridt_PrimaerOcrLinjePage extends Sagsbehandlingsskridt_PrimaerTabPage<Sagsbehandlingsskridt_PrimaerOcrLinjePage> {

    final PageElement celIdentificeringsnummer = new PageElement(Frames.zoneMapFrame_2, By.id("OCR-line_id"));

    public Sagsbehandlingsskridt_PrimaerOcrLinjePage() {
        super(new SagsbehandlingsskridtPage<Sagsbehandlingsskridt_PrimaerOcrLinjePage>() {});
    }

    public String getIDnumberOfOCRLine() {
        return this.celIdentificeringsnummer.getText();
    }
}
