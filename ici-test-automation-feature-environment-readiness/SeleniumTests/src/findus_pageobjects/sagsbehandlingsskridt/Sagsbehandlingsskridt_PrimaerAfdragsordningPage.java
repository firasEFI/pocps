package findus_pageobjects.sagsbehandlingsskridt;

import static utils.IciSelExpectedConditions.clickingMakesWindowAppearAndThenSwitchToIt;

import org.openqa.selenium.By;

import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.wizards.afdragsordning.AfdragsordningWizardPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;

public class Sagsbehandlingsskridt_PrimaerAfdragsordningPage extends Sagsbehandlingsskridt_PrimaerTabPage<Sagsbehandlingsskridt_PrimaerAfdragsordningPage> {

    final Button btnOpretInaktivAfdragsOrdning = new Button(Frames.zoneMapFrame_2, By.xpath("//input[@value='Opret inaktiv afdragsordning']"));
    final Button btnOpretAfgBrev = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_2"));
    final Button btnRediger = new Button(Frames.zoneMapFrame_2, By.xpath("EDIT"));
    final Button btnAnnuller = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));

    public Sagsbehandlingsskridt_PrimaerAfdragsordningPage() {
        super(new SagsbehandlingsskridtPage<Sagsbehandlingsskridt_PrimaerAfdragsordningPage>() {});
    }

    //Opret inaktiv afdragsordning (bliver på primærtabpage, dog ny type)
    public Sagsbehandlingsskridt_PrimaerAfdragsordningPage activateOpretInaktivAfdragsOrdning() {
        PageElement statusIsOprettetInaktiv = new PageElement(Frames.zoneMapFrame_2, By.xpath("//*[@id='boStatus' and text()='Oprettet (inaktiv)']"));
        
        btnOpretInaktivAfdragsOrdning.waitingFor(statusIsOprettetInaktiv).click();

        return this;
    }

    public AfdragsordningWizardPage activateRediger() {
        this.btnRediger.click();

        return new AfdragsordningWizardPage();
    }

    public void activateAnnullerAndAcceptPrompt() {
        PatientWebDriver driver = Engine.getDriver();
        String originalWindow = driver.getWindowHandle();

        driver.pause().until(clickingMakesWindowAppearAndThenSwitchToIt(btnAnnuller));

        new Button(Frames.defaultContent, By.xpath("//input[@value='OK']")).clickElementUntilNotPresent();

        driver.switchTo().window(originalWindow);
    }

    //Popup opret afgørelsesbrev (Nyt popup vindue som giver nyt primærtabpage type ved gem, dog i koden går vi direkte til logtab)
    public OpretAfgoerelsesbrevBrevPopupWindow activateOpretAfgoerelsesbrevBrev() {
        PopupWindowResolver<OpretAfgoerelsesbrevBrevPopupWindow> popupResolver = new PopupWindowResolver<>(Engine.getDriver());

        this.btnOpretAfgBrev.click();

        OpretAfgoerelsesbrevBrevPopupWindow popup = popupResolver.waitForPopup(new PopupWindowResolver.GetPopupWindow<OpretAfgoerelsesbrevBrevPopupWindow>() {
            @Override
            public OpretAfgoerelsesbrevBrevPopupWindow GetWindow() {
                return new OpretAfgoerelsesbrevBrevPopupWindow(Sagsbehandlingsskridt_PrimaerAfdragsordningPage.this);
            }
        },10000);

        return popup;
    }
}