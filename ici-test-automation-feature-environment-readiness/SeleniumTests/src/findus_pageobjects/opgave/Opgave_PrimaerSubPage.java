package findus_pageobjects.opgave;

import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.SubPage;
import findus_pageobjects.oversigt_over_daekninger.OversigtOverDaekningerPage;
import findus_pageobjects.sagsbehandlingsskridt.SagsbehandlingsskridtPage;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling;
import findus_testobjects.IU_601;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class Opgave_PrimaerSubPage extends SubPage<OpgavePage> {
    final Button btnTildel = new Button(Frames.tabPage, By.id("FORWARD_SW"));
    final Link lnkLinkTilDetaljer = new Link(Frames.tabPage, By.id("FULL_MSG"));

    final PageElement linkTilGodkendelsesSide = new PageElement(Frames.tabPage, By.id("FULL_MSG"));

    final Button btnTildelFraOverblik = new Button(Frames.tabPage, By.xpath(".//*[@id='dataExplorerTableBody1']//span/input[@value='Tildel']"));

    public Opgave_PrimaerSubPage(OpgavePage parentPage) {
        super(parentPage);
    }

    public Opgave_PrimaerSubPageTildelPopupWindow activateOpgave_PrimærSubPageTildelPopupWindow() {
        PopupWindowResolver<Opgave_PrimaerSubPageTildelPopupWindow> popupResolver = new PopupWindowResolver<>(Engine.getDriver());

        this.btnTildel.click();

        Opgave_PrimaerSubPageTildelPopupWindow popup = popupResolver.waitForPopup(new PopupWindowResolver.GetPopupWindow<Opgave_PrimaerSubPageTildelPopupWindow>() {
            @Override
            public Opgave_PrimaerSubPageTildelPopupWindow GetWindow() {
                return new Opgave_PrimaerSubPageTildelPopupWindow(Opgave_PrimaerSubPage.this);
            }
        },10000);

        return popup;
    }

    public Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling gaaTilGodkendelsessiden() {
        linkTilGodkendelsesSide.click();

        return new Sagsbehandlingsskridt_PrimaerIndividuelUdbetaling();
    }

    /**
     * Vær opmærksom på at dette link kan navigere til forskellige sider.
     * @return OversigtOverDaekningerPage
     */
    public OversigtOverDaekningerPage activateLinkTilDetaljer_OversigtOverDakninger(){
        lnkLinkTilDetaljer.click();
        return new OversigtOverDaekningerPage();
    }

    public Opgave_PrimaerSubPage activateTildel(){
        btnTildelFraOverblik.click();   

        return this;
    }

}
