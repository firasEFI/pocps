package findus_pageobjects.betalingshaendelse;

import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.SubPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.Engine;
import org.openqa.selenium.By;

public class Betalingshaendelse_IndbetalingerSubPage extends SubPage<BetalingshaendelsePage> {
    public Betalingshaendelse_IndbetalingerSubPage(BetalingshaendelsePage parentPage) {
        super(parentPage);
    }
    Button annullerKnap = new Button(Frames.tabPage, By.id("PAY_TNDR$TNDR_CANCEL_SW"));

    public Betalingshaendelse_IndbetalingerPopWindow activateAnnullerKnap() {
        PopupWindowResolver<Betalingshaendelse_IndbetalingerPopWindow> popupResolver = new PopupWindowResolver<>(Engine.getDriver());
        annullerKnap.click();

        Betalingshaendelse_IndbetalingerPopWindow popup = popupResolver.waitForPopup(new PopupWindowResolver.GetPopupWindow<Betalingshaendelse_IndbetalingerPopWindow>() {
            @Override
            public Betalingshaendelse_IndbetalingerPopWindow GetWindow() {
                return new Betalingshaendelse_IndbetalingerPopWindow(Betalingshaendelse_IndbetalingerSubPage.this);
            }
        },10000);
        return popup;
    }

}
