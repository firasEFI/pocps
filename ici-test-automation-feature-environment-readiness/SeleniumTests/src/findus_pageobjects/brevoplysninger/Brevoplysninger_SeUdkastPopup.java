package findus_pageobjects.brevoplysninger;

import findus_pageobjects.PopupWindow;
import findus_pageobjects.sagsbehandlingsskridt.Sagsbehandlingsskridt_PrimaerTabPage;
import modules.MO_Fordring;
import org.openqa.selenium.By;

/**
 * Created by nielsjes on 05-09-2017.
 */
public class Brevoplysninger_SeUdkastPopup extends PopupWindow<Brevoplysninger_PrimaerSubPage> {
    {
    }

    protected Brevoplysninger_SeUdkastPopup(By elementToSyncronize, Brevoplysninger_PrimaerSubPage brevoplysninger_primaerSubPage) {
        super(elementToSyncronize, brevoplysninger_primaerSubPage);
    }

    public void closeUdkastPage(){
        
    }

}
