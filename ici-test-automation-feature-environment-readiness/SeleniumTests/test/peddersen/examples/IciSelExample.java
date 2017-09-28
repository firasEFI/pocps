package peddersen.examples;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import icisel.testng.SmartAssert;
import icisel.testng.TestContext;
import modules.MO_360GradersSoegning;
import utils.PropertyProviderImpl;

/**
 * Created by msl on 07-07-2017.
 * This class is meant to show basic syntax
 * in an ici-sel test case.
 */

//An ici-sel test case extends TestContext by definition
public class IciSelExample extends TestContext{

    /*For things you need to do to configure
    the system so it's ready for the test,
    but which aren't part of the test itself.
     */
    @BeforeTest
    public void setup(){
        setPropertyProvider(new PropertyProviderImpl());
        doMaximizeWindow(); //implicitly also creates an new driver if needed
    }

    //Tests must be annotated with @Test
    @Test
    public void iciselExample() throws Exception{
        doLogin(); // this method is a part of TestContext (it uses the property fil
        MO_360GradersSoegning.soegKundeViaFuldeNavn(this, "Rasmussen, Asger");
        MenuNavigator.partKontekst().gaaTil360GradersOverblik();

        //make element to represent field we want to assert has the correct phone number.
        //THIS IS AGAINST BEST PRACTICE, since you should always put pageelements in appropriate classes.
        //This is just to illustratte how things work in ici-sel.
        PageElement phoneNumberField = new PageElement(Frames.zoneMapFrame_1, By.xpath("//td[@orafield='phone']"));
        String phoneNumber = phoneNumberField.getText();


        //Another ici-sel feature, which should be preferred over regular assert (see documentation)
        String expectedPhoneNumber =  "81718169";
        String errormsg = "Exptected " + expectedPhoneNumber + " but found " + phoneNumber;
        SmartAssert.assertEquals(this, phoneNumber, expectedPhoneNumber, errormsg);

        doLogout();
    }

    @Test
    public void adminMenu() throws Exception{
        doLogin(); // this method is a part of TestContext (it uses the property fil

        MenuNavigator.admin().getSubmenu("A").pick("Afdeling").pick("Tilføj");
    }

    @Test
    public void dropdown() throws Exception{
        doLogin(); // this method is a part of TestContext (it uses the property fil

        MenuNavigator.menu().a360GradersSoegning();
        Dropdown idType = new Dropdown(Frames.tabPage, By.id("idType"));
        idType.pick("CPR"); //uses value, will add pickVisible in next ici-sel release;
    }
}
