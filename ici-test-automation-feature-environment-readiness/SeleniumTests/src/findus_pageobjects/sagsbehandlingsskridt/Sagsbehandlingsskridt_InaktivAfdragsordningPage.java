package findus_pageobjects.sagsbehandlingsskridt;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.frames.Frames;

public class Sagsbehandlingsskridt_InaktivAfdragsordningPage extends Sagsbehandlingsskridt_PrimaerTabPage<Sagsbehandlingsskridt_InaktivAfdragsordningPage> {

    final Button btnOpretInaktivAfdragsOrdning = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));
    final Button btnOpretAfgBrev = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_2"));
    final Button btnRediger = new Button(Frames.zoneMapFrame_2, By.id("EDIT"));
    final Button btnAnnuller = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));

    public Sagsbehandlingsskridt_InaktivAfdragsordningPage() {
        super(new SagsbehandlingsskridtPage<Sagsbehandlingsskridt_InaktivAfdragsordningPage>() {});
    }
    
    

}