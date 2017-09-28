package findus_pageobjects.sagsbehandlingsskridt;

import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.brevoplysninger.BrevoplysningerPage;
import findus_pageobjects.brevoplysninger.Brevoplysninger_PrimaerSubPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class Sagsbehandlingsskridt_PrimaerPaakravssagPage extends Sagsbehandlingsskridt_PrimaerTabPage<Sagsbehandlingsskridt_PrimaerPaakravssagPage> {

    final Button btnOpretVelkomstOgPaakravsbrev = new Button(Frames.zoneMapFrame_2, By.xpath("//input[@value = 'Opret velkomst-/påkravsbrev']"));
    final Link lnkGaaTilVelkomstbrev = new Link(Frames.zoneMapFrame_2, By.xpath("//*[@id=\"FdmCcId\"]/a")); ////*[@id="FdmCcId"]/a

    public Sagsbehandlingsskridt_PrimaerPaakravssagPage() {
        super(new SagsbehandlingsskridtPage<Sagsbehandlingsskridt_PrimaerPaakravssagPage>() {});
    }

    public OpretVelkomstEllerPaakravsBrevPopupWindow activateOpretVelkomstOgPaakravsbrev() {
        PopupWindowResolver<OpretVelkomstEllerPaakravsBrevPopupWindow> popupResolver = new PopupWindowResolver<>(Engine.getDriver());

        this.btnOpretVelkomstOgPaakravsbrev.clickUntilDisabled();

        OpretVelkomstEllerPaakravsBrevPopupWindow popup = popupResolver.waitForPopup(new PopupWindowResolver.GetPopupWindow<OpretVelkomstEllerPaakravsBrevPopupWindow>() {
            @Override
            public OpretVelkomstEllerPaakravsBrevPopupWindow GetWindow() {
                return new OpretVelkomstEllerPaakravsBrevPopupWindow(Sagsbehandlingsskridt_PrimaerPaakravssagPage.this);
            }
        },10000);

        return popup;
    }

    public Brevoplysninger_PrimaerSubPage activateGaaTilPaakravsbrev(){
        lnkGaaTilVelkomstbrev.waitUntilClickable();
        lnkGaaTilVelkomstbrev.clickElementUntilNotPresent();

        return new Brevoplysninger_PrimaerSubPage();
    }

    public boolean containsFordring(String fordringsId) {
        throw new RuntimeException("Not implemented");
    }
}
