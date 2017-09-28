package findus_pageobjects.sagsbehandlingsskridt;

import findus_testobjects.IU_612_godkendUdbetaling;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling extends Sagsbehandlingsskridt_PrimaerTabPage<Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling> {

    public final IU_612_godkendUdbetaling iu_612_godkendUdbetaling = new IU_612_godkendUdbetaling(this);
    private final Button godkendButton = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));

    public Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling() {
        super(new SagsbehandlingsskridtPage<Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling>() {});
    }

    public void activateGodkend() {
        godkendButton.click();
    }
}
