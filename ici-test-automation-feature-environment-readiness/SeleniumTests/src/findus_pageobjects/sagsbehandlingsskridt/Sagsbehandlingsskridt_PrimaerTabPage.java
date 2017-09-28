package findus_pageobjects.sagsbehandlingsskridt;

import findus_pageobjects.Accordion;
import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.SubPage;
import findus_pageobjects.brevoplysninger.BrevoplysningerPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public abstract class Sagsbehandlingsskridt_PrimaerTabPage<TPrimaerTabPage> extends SubPage<SagsbehandlingsskridtPage<TPrimaerTabPage>> {
//
//    final Accordion accSagsbehandlingsskridt = new Accordion(new PageElement(Frames.zoneMapFrame_2, By.id("zoneHeader2")));
//
//    final PageElement celSagsbehandlingsskridtId = new PageElement(Frames.zoneMapFrame_2, By.id("processFlowId"));
//    final PageElement celStatus = new PageElement(Frames.zoneMapFrame_2, By.id("boStatus"));
//    final PageElement celOprettelsestidspunkt = new PageElement(Frames.zoneMapFrame_2, By.id("creationDateTime"));
//
//    final Button btnOpretVelkomstOgPaakravsbrev = new Button(Frames.zoneMapFrame_2, By.xpath("//input[@value = 'Opret velkomst-/påkravsbrev']"));
//    final Link lnkGaaTilVelkomstbrev = new Link(Frames.zoneMapFrame_2, By.xpath("//*[@id=\"FdmCcId\"]/a")); ////*[@id="FdmCcId"]/a
//    final Button btnOpretInaktivAfdragsOrdning = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_1"));
//    final Link lnkGaaTilInaktivAfdOrdn = new Link(Frames.zoneMapFrame_2, By.id("inactivePayplan"));
//    final Button btnOpretAfgBrev = new Button(Frames.zoneMapFrame_2, By.id("TRANSITION_2"));
//
    protected Sagsbehandlingsskridt_PrimaerTabPage(SagsbehandlingsskridtPage<TPrimaerTabPage> parentPage) {
        super(parentPage);
    }
//
//// region "Operations"
//    public OpretVelkomstEllerPaakravsBrevPopupWindow activateOpretVelkomstOgPaakravsbrev() {
//        PopupWindowResolver<OpretVelkomstEllerPaakravsBrevPopupWindow> popupResolver = new PopupWindowResolver<>(Engine.getDriver());
//
//        this.btnOpretVelkomstOgPaakravsbrev.click();
//
//        OpretVelkomstEllerPaakravsBrevPopupWindow popup = popupResolver.waitForPopup(new PopupWindowResolver.GetPopupWindow<OpretVelkomstEllerPaakravsBrevPopupWindow>() {
//            @Override
//            public OpretVelkomstEllerPaakravsBrevPopupWindow GetWindow() {
//                return new OpretVelkomstEllerPaakravsBrevPopupWindow(Sagsbehandlingsskridt_PrimaerTabPage.this);
//            }
//        },10000);
//
//        return popup;
//    }
//
//    public BrevoplysningerPage activateGaaTilPaakravsbrev(){
//        lnkGaaTilVelkomstbrev.clickElementUntilNotPresent();
//
//        return new BrevoplysningerPage();
//    }
//
//    //Opret inaktiv afdragsordning (bliver på primærtabpage, dog ny type)
//    public Sagsbehandlingsskridt_PrimaerTabPage activateOpretInaktivAfdragsOrdning(){
//
//        btnOpretInaktivAfdragsOrdning.click();
//        return this;
//    }
//
//    //Link til inaktiv afdragsordning (bliver på primærtabpage, dog ny type)
//    public Sagsbehandlingsskridt_PrimaerTabPage activateGaaTilInaktivAfdOrdn(){
//
//        lnkGaaTilInaktivAfdOrdn.click();
//        return this;
//    }
//
//    //Popup opret afgørelsesbrev (Nyt popup vindue som giver nyt primærtabpage type ved gem, dog i koden går vi direkte til logtab)
//    public OpretAfgoerelsesbrevBrevPopupWindow activateOpretAfgoerelsesbrevBrev() {
//        PopupWindowResolver<OpretAfgoerelsesbrevBrevPopupWindow> popupResolver = new PopupWindowResolver<>(Engine.getDriver());
//
//        this.btnOpretAfgBrev.click();
//
//        OpretAfgoerelsesbrevBrevPopupWindow popup = popupResolver.waitForPopup(new PopupWindowResolver.GetPopupWindow<OpretAfgoerelsesbrevBrevPopupWindow>() {
//            @Override
//            public OpretAfgoerelsesbrevBrevPopupWindow GetWindow() {
//                return new OpretAfgoerelsesbrevBrevPopupWindow(Sagsbehandlingsskridt_PrimaerTabPage.this);
//            }
//        },10000);
//
//        return popup;
//    }
//    // endregion
}
