package navigation.menu.fluent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import icisel.pageobjects.elements.Menu;
import icisel.pageobjects.frames.Frame;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_Navigation;

public class MenuNavigator {
    public static TopMenu menu() {
        Menu menu = PO_Navigation.btn_Menu;
        TopMenu subMenu = new TopMenu(menu);
        clickAndWaitForElement(menu, null, subMenu.getMenu().getByOfVisibleMenu("360 graders søgning"));
        return subMenu;
    }

    public static TopAdmin admin() {
        Menu menu = PO_Navigation.btn_Admin;
        TopAdmin subMenu = new TopAdmin(menu);
        clickAndWaitForElement(menu, null, subMenu.getMenu().getByOfVisibleMenu("A"));
        return subMenu;
    }

    public static KontoKontekstMenu kontoKontekst() {
        Menu menu = PO_Navigation.menu_visKontoKontekst_Menu;
        KontoKontekstMenu subMenu = new KontoKontekstMenu(menu);
        clickAndWaitForElement(menu, subMenu.subMenuFrame,
                subMenu.getMenu().getByOfVisibleMenu("Gå til 360 graders overblik"));
        return subMenu;
    }

    public static PartKontekstMenu partKontekst() {
        Menu menu = PO_Navigation.menu_visPartKontekst_Menu;
        PartKontekstMenu subMenu = new PartKontekstMenu(menu);
        clickAndWaitForElement(menu, subMenu.subMenuFrame,
                subMenu.getMenu().getByOfVisibleMenu("Gå til 360 graders overblik"));
        return subMenu;
    }

    public static UserMenu userMenu() {
        Menu menu = PO_Navigation.menu_userMenu;
        UserMenu subMenu = new UserMenu(menu);
        clickAndWaitForElement(menu, subMenu.subMenuFrame, subMenu.getMenu().getByOfVisibleMenu("Præferencer"));
        return subMenu;

    }

    private static void clickAndWaitForElement(final Menu menu, Frame frameOfExpectedElement,
            final By byOfExpectedElement) {
        menu.click(); // initial click

        final PatientWebDriver driver = Engine.getDriver();

        long t0 = System.currentTimeMillis();
        long timeout = driver.getTimeout();

        boolean doSwitchFrame = (frameOfExpectedElement != null);

        while (System.currentTimeMillis() - t0 < timeout) {
            if (doSwitchFrame)
                driver.goTo(frameOfExpectedElement);
            try {
                driver.findElement(byOfExpectedElement);
                break;
            } catch (WebDriverException e) {
                menu.click();
            }

        }
    }

}
