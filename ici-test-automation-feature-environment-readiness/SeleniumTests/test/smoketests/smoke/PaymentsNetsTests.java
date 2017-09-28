package smoketests.smoke;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.testng.SmartAssert;
import icisel.testng.TestContext;
import icisel.utils.driver.patient.PatientWebDriver;
import icisel.utils.driver.RetryAnalyzer;
import modules.MO_360GradersSoegning;
import org.testng.annotations.Test;
import pageobjects.smoketests._360degreeview._360CaseworkPage;
import pageobjects.smoketests._360degreeview._360OverviewPage;
import pageobjects.smoketests.payments.OverviewOfCoveragePage;
import pageobjects.smoketests.payments.OverviewOfPaymentPage;
import pageobjects.smoketests.payments.TodoOverviewPage;
import pageobjects.smoketests.payments.TodoPage;
import utils.PropertyProviderImpl;

/**
 * Created by asol on 08-08-2017.
 */
public class PaymentsNetsTests extends TestContext{

     /*
     * @BeforeTest public void setup() { setPropertyProvider(new
     * PropertyProviderImpl()); doMaximizeWindow(); }
     */

    /**
     * Login PSRM. Click ”Menu” ->”360 graders soegning”. Search for CPR-nr:
     * 0505841638 (Giese, Signy). Open the "Skyldner" using the name link
     * resulting from the search. Click on the tab "Sagsbehandling". Open the
     * zone "Betalingshaendelser for kontoen". Click on the context menu for the
     * "Betalingshaendelses ID" link, and click "Overblik over betaling". Verify
     * payment fields according to the payment file. Logout PSRM.
     */
    @Test(skipFailedInvocations = true, retryAnalyzer = RetryAnalyzer.class)
    public void testT001_M0602_NetsPaymentType0215() throws InterruptedException {
        setPropertyProvider(new PropertyProviderImpl());
        System.out.println("Running testT001_M0602_NetsPaymentType0215");

        // Test data
        String cprNumber = "0505841638";
        String debtorID = "0100000019";
        String personFirstName = "Signy";
        String personLastName = "Giese";
        String personFullName = personLastName + ", " + personFirstName;
        String personFullNameRightOrder = personFirstName + " " + personLastName;

        String amount = "800,00kr.";
        String accoutingDate = "18-07-2017";
        String paymentType = "Betalingsservice Nets";
        String sourceOfPayment = "Indbetalinger BS";
        String rimReference = "100900633";
        String ocrLine = "955692588862858";

        // Driver for pageobjects.smoketests pages that require it.
        PatientWebDriver pDriver = getDriver();

        doLogin();

        MO_360GradersSoegning.soegKundeViaCPR(this, cprNumber);

        _360OverviewPage current_360OverviewPage = new _360OverviewPage();
        current_360OverviewPage.sagsbehandling.click();

        _360CaseworkPage current_360CaseworkPage = new _360CaseworkPage(pDriver);
        // ICI-Sel click is commented out until it has been made stable
        // (IS-23958)
        // current_360CaseworkPage.betalingshaendelserForKontoen.click();
        current_360CaseworkPage.clickPaymentsForAccount();
        current_360CaseworkPage.searchAndClickContextForPayment(accoutingDate, amount, personFullName);
        current_360CaseworkPage.overblikOverBetaling.click();

        OverviewOfPaymentPage currentOverviewOfPaymentPage = new OverviewOfPaymentPage(pDriver);

        // Verify the payment

        // "Primaer" details
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.skyldnerID.getText(), debtorID,
                errorMsg(debtorID, currentOverviewOfPaymentPage.skyldnerID.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.cprNummer.getText(), cprNumber,
                errorMsg(cprNumber, currentOverviewOfPaymentPage.cprNummer.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.skyldernsNavn.getText(), personFullNameRightOrder,
                errorMsg(personFullNameRightOrder, currentOverviewOfPaymentPage.skyldernsNavn.getText()));
        SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.konto.getText().contains(personFullName),
                errorMsgContains(personFullName, currentOverviewOfPaymentPage.skyldernsNavn.getText()));
        SmartAssert.assertTrue(this,
                currentOverviewOfPaymentPage.betalingshaendelsesID.getText().contains(accoutingDate),
                errorMsgContains(accoutingDate, currentOverviewOfPaymentPage.betalingshaendelsesID.getText()));
        SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.betalingshaendelsesID.getText().contains(amount),
                errorMsgContains(amount, currentOverviewOfPaymentPage.betalingshaendelsesID.getText()));
        SmartAssert.assertTrue(this,
                currentOverviewOfPaymentPage.betalingshaendelsesID.getText().contains(personFullName),
                errorMsgContains(personFullName, currentOverviewOfPaymentPage.betalingshaendelsesID.getText()));
        SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.betalingshaendelsesID.getText().contains(ocrLine),
                errorMsgContains(ocrLine, currentOverviewOfPaymentPage.betalingshaendelsesID.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.bogfoeringsdato.getText(), accoutingDate,
                errorMsg(accoutingDate, currentOverviewOfPaymentPage.bogfoeringsdato.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.virkningsdato.getText(), accoutingDate,
                errorMsg(accoutingDate, currentOverviewOfPaymentPage.virkningsdato.getText()));

        // "Daekningsdetaljer for indbetaling" details
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.dBeloeb.getText(), amount,
                errorMsg(amount, currentOverviewOfPaymentPage.dBeloeb.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.dCharacteristicValueInfo.getText(), ocrLine,
                errorMsg(ocrLine, currentOverviewOfPaymentPage.dCharacteristicValueInfo.getText()));

        // "Indbetalingskilder" details
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikIndbetalingsdato.getText(), accoutingDate,
                errorMsg(accoutingDate, currentOverviewOfPaymentPage.ikIndbetalingsdato.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikBetalingsdato.getText(), accoutingDate,
                errorMsg(accoutingDate, currentOverviewOfPaymentPage.ikBetalingsdato.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikIndbetalingsbeloeb.getText(), amount,
                errorMsg(amount, currentOverviewOfPaymentPage.ikIndbetalingsbeloeb.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikIndbetalingstype.getText(), paymentType,
                errorMsg(paymentType, currentOverviewOfPaymentPage.ikIndbetalingstype.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikIndbetalingskilde.getText(), sourceOfPayment,
                errorMsg(sourceOfPayment, currentOverviewOfPaymentPage.ikIndbetalingskilde.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikRimReference.getText(), rimReference,
                errorMsg(rimReference, currentOverviewOfPaymentPage.ikRimReference.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikOcrLinjeNummer.getText(), ocrLine,
                errorMsg(ocrLine, currentOverviewOfPaymentPage.ikOcrLinjeNummer.getText()));

        // "Indbetalinger" details
        //SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.iBetalingsInfo.getText().contains(accoutingDate),
        //        errorMsgContains(accoutingDate, currentOverviewOfPaymentPage.iBetalingsInfo.getText()));
        //SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.iBetalingsInfo.getText().contains(amount),
        //        errorMsgContains(amount, currentOverviewOfPaymentPage.iBetalingsInfo.getText()));
        //SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.iBetalingsInfo.getText().contains(personFullName),
        //        errorMsgContains(personFullName, currentOverviewOfPaymentPage.iBetalingsInfo.getText()));
        //SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.iBeloeb.getText(), amount,
        //        errorMsg(amount, currentOverviewOfPaymentPage.iBeloeb.getText()));
        //SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.iFordringsID.getText().contains(accoutingDate),
        //        errorMsgContains(accoutingDate, currentOverviewOfPaymentPage.iFordringsID.getText()));

        doLogout();

        System.out.println("* Login PSRM. Click ”Menu” ->”360 graders soegning”. Search for CPR-nr:\n" +
                "     * 0505841638 (Giese, Signy). Open the \"Skyldner\" using the name link\n" +
                "     * resulting from the search. Click on the tab \"Sagsbehandling\". Open the\n" +
                "     * zone \"Betalingshaendelser for kontoen\". Click on the context menu for the\n" +
                "     * \"Betalingshaendelses ID\" link, and click \"Overblik over betaling\". Verify\n" +
                "     * payment fields according to the payment file. Logout PSRM.");

    }

    /**
     * Login PSRM. Click ”Menu” ->”360 graders soegning”. Search for CPR-nr:
     * 0505749863 (Lang, Tilde). Open the "Skyldner" using the name link
     * resulting from the search. Click on the tab "Sagsbehandling". Open the
     * zone "Betalingshaendelser for kontoen". Click on the context menu for the
     * "Betalingshaendelses ID" link, and click "Overblik over betaling". Verify
     * payment fields according to the payment file. Logout PSRM.
     */
    @Test(skipFailedInvocations = true, retryAnalyzer = RetryAnalyzer.class)
    public void testT002_M0602_NetsPaymentType0211() throws InterruptedException {
        setPropertyProvider(new PropertyProviderImpl());
        System.out.println("Running testT002_M0602_NetsPaymentType0211");

        // Test data
        String cprNumber = "0505749863";
        String debtorID = "0100000018";
        String personFirstName = "Tilde";
        String personLastName = "Lang";
        String personFullName = personLastName + ", " + personFirstName;
        String personFullNameRightOrder = personFirstName + " " + personLastName;

        String amount = "500,00kr.";
        String accoutingDate = "18-07-2017";
        String paymentType = "Betalingsservice Nets";
        String sourceOfPayment = "Indbetalinger BS";
        String rimReference = "RIM: 859714121881082";
        String ocrLine = "625107876470890";

        // Driver for pageobjects.smoketests pages that require it.
        PatientWebDriver pDriver = getDriver();

        doLogin();

        MO_360GradersSoegning.soegKundeViaCPR(this, cprNumber);

        _360OverviewPage current_360OverviewPage = new _360OverviewPage();
        current_360OverviewPage.sagsbehandling.click();

        _360CaseworkPage current_360CaseworkPage = new _360CaseworkPage(pDriver);
        // ICI-Sel click is commented out until it has been made stable
        // (IS-23958)
        // current_360CaseworkPage.betalingshaendelserForKontoen.click();
        current_360CaseworkPage.clickPaymentsForAccount();
        current_360CaseworkPage.searchAndClickContextForPayment(accoutingDate, amount, personFullName);
        current_360CaseworkPage.overblikOverBetaling.click();

        OverviewOfPaymentPage currentOverviewOfPaymentPage = new OverviewOfPaymentPage(pDriver);

        // Verify the payment

        // "Primaer" details
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.skyldnerID.getText(), debtorID,
                errorMsg(debtorID, currentOverviewOfPaymentPage.skyldnerID.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.cprNummer.getText(), cprNumber,
                errorMsg(cprNumber, currentOverviewOfPaymentPage.cprNummer.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.skyldernsNavn.getText(), personFullNameRightOrder,
                errorMsg(personFullNameRightOrder, currentOverviewOfPaymentPage.skyldernsNavn.getText()));
        SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.konto.getText().contains(personFullName),
                errorMsgContains(personFullName, currentOverviewOfPaymentPage.skyldernsNavn.getText()));
        SmartAssert.assertTrue(this,
                currentOverviewOfPaymentPage.betalingshaendelsesID.getText().contains(accoutingDate),
                errorMsgContains(accoutingDate, currentOverviewOfPaymentPage.betalingshaendelsesID.getText()));
        SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.betalingshaendelsesID.getText().contains(amount),
                errorMsgContains(amount, currentOverviewOfPaymentPage.betalingshaendelsesID.getText()));
        SmartAssert.assertTrue(this,
                currentOverviewOfPaymentPage.betalingshaendelsesID.getText().contains(personFullName),
                errorMsgContains(personFullName, currentOverviewOfPaymentPage.betalingshaendelsesID.getText()));
        SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.betalingshaendelsesID.getText().contains(ocrLine),
                errorMsgContains(ocrLine, currentOverviewOfPaymentPage.betalingshaendelsesID.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.bogfoeringsdato.getText(), accoutingDate,
                errorMsg(accoutingDate, currentOverviewOfPaymentPage.bogfoeringsdato.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.virkningsdato.getText(), accoutingDate,
                errorMsg(accoutingDate, currentOverviewOfPaymentPage.virkningsdato.getText()));

        // "Daekningsdetaljer for indbetaling" details
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.dBeloeb.getText(), amount,
                errorMsg(amount, currentOverviewOfPaymentPage.dBeloeb.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.dCharacteristicValueInfo.getText(), ocrLine,
                errorMsg(ocrLine, currentOverviewOfPaymentPage.dCharacteristicValueInfo.getText()));

        // "Indbetalingskilder" details
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikIndbetalingsdato.getText(), accoutingDate,
                errorMsg(accoutingDate, currentOverviewOfPaymentPage.ikIndbetalingsdato.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikBetalingsdato.getText(), accoutingDate,
                errorMsg(accoutingDate, currentOverviewOfPaymentPage.ikBetalingsdato.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikIndbetalingsbeloeb.getText(), amount,
                errorMsg(amount, currentOverviewOfPaymentPage.ikIndbetalingsbeloeb.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikIndbetalingstype.getText(), paymentType,
                errorMsg(paymentType, currentOverviewOfPaymentPage.ikIndbetalingstype.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikIndbetalingskilde.getText(), sourceOfPayment,
                errorMsg(sourceOfPayment, currentOverviewOfPaymentPage.ikIndbetalingskilde.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikRimReference.getText(), rimReference,
                errorMsg(rimReference, currentOverviewOfPaymentPage.ikRimReference.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.ikOcrLinjeNummer.getText(), ocrLine,
                errorMsg(ocrLine, currentOverviewOfPaymentPage.ikOcrLinjeNummer.getText()));

        // "Indbetalinger" details
        SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.iBetalingsInfo.getText().contains(accoutingDate),
                errorMsgContains(accoutingDate, currentOverviewOfPaymentPage.iBetalingsInfo.getText()));
        SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.iBetalingsInfo.getText().contains(amount),
                errorMsgContains(amount, currentOverviewOfPaymentPage.iBetalingsInfo.getText()));
        SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.iBetalingsInfo.getText().contains(personFullName),
                errorMsgContains(personFullName, currentOverviewOfPaymentPage.iBetalingsInfo.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfPaymentPage.iBeloeb.getText(), amount,
                errorMsg(amount, currentOverviewOfPaymentPage.iBeloeb.getText()));
        SmartAssert.assertTrue(this, currentOverviewOfPaymentPage.iFordringsID.getText().contains(accoutingDate),
                errorMsgContains(accoutingDate, currentOverviewOfPaymentPage.iFordringsID.getText()));

        doLogout();

        System.out.println("* Login PSRM. Click ”Menu” ->”360 graders soegning”. Search for CPR-nr:\n" +
                "     * 0505749863 (Lang, Tilde). Open the \"Skyldner\" using the name link\n" +
                "     * resulting from the search. Click on the tab \"Sagsbehandling\". Open the\n" +
                "     * zone \"Betalingshaendelser for kontoen\". Click on the context menu for the\n" +
                "     * \"Betalingshaendelses ID\" link, and click \"Overblik over betaling\". Verify\n" +
                "     * payment fields according to the payment file. Logout PSRM.");

    }

    /**
     * Login PSRM. Click "Menu" -> "Opgave" -> "Opgave Overblik". Find
     * Opgavetype = "Ophaev indbetaling, Oprettet" and Relation til ID = "BS".
     * Click on the link "Ophaev indbetaling, Oprettet" and verify: - Opgavetype
     * = "DK-PDCLE" - Rolle = "DK-PAYCASW" - Beskrivelse = "Besked fra NETS om
     * daekningsloes indbetaling: Skal ophaeves: 1. Fremfind den oprindelige
     * indbetaling som skal ophaeves med aarsagskode A (daekningsloes). 2. Den
     * daekningsloese indbetaling annulleres (Cancel) 3. Opgaven lukkes." Click
     * on the link "Ophaev indbetaling (88888, 91)" Validate payment fields,
     * according to the file. Make sure that "Indbetalingsbeloeb" has a negative
     * amount. Logout PSRM.
     */
    @Test(skipFailedInvocations = true, retryAnalyzer = RetryAnalyzer.class)
    public void testT003_M0602_NetsPaymentWithInsufficientFunds() {
        setPropertyProvider(new PropertyProviderImpl());
        System.out.println("Running testT003_M0602_NetsPaymentWithInsufficientFunds");

        // Test data
        String todoTypeText = "Ophæv indbetaling, Oprettet";
        String relationToID = "BS";
        String details = "Ophæv indbetaling (88888, 91)";
        String status = "Oprettet";
        String todoTypeID = "DK-PDCLE";
        String role = "DK-PAYCASW";
        // Description might look a bit confusing but it was attempted to setup
        // here as it appears in PSRM.
        String description = "Besked fra NETS om dækningsløs indbetaling:\n" + "\n" + "Skal ophæves:\n"
                + "1. Fremfind den oprindelige indbetaling som skal ophæves med årsagskode A (dækningsløs).\n"
                + "2. Den dækningsløse indbetaling annulleres (Cancel)\n" + "3. Opgaven lukkes.";
        String amount = "500,00kr.";
        String accountingDate = "18-07-2017";
        String externalReferenceId = "RIM: 859714121881082";

        PatientWebDriver pDriver = getDriver();

        doLogin();

        MenuNavigator.menu().opgave().opgaveOverblik();

        TodoOverviewPage currentTodoOverviewPage = new TodoOverviewPage(pDriver);
        currentTodoOverviewPage.searchAndClickTodo(todoTypeText, relationToID);

        TodoPage currentTodoPage = new TodoPage(pDriver);

        SmartAssert.assertEquals(this, currentTodoPage.opgavetype.getText(), todoTypeID,
                errorMsg(todoTypeID, currentTodoPage.opgavetype.getText()));
        SmartAssert.assertEquals(this, currentTodoPage.rolle.getText(), role,
                errorMsg(role, currentTodoPage.rolle.getText()));
        SmartAssert.assertEquals(this, currentTodoPage.linkTilDetaljer.getText(), details,
                errorMsg(details, currentTodoPage.linkTilDetaljer.getText()));
        SmartAssert.assertEquals(this, currentTodoPage.status.getText(), status,
                errorMsg(status, currentTodoPage.status.getText()));
        SmartAssert.assertEquals(this, currentTodoPage.beskrivelse.getAttribute("value"), description,
                errorMsg(description, currentTodoPage.beskrivelse.getAttribute("value")));

        currentTodoPage.linkTilDetaljer.click();

        OverviewOfCoveragePage currentOverviewOfCoveragePage = new OverviewOfCoveragePage(pDriver);

        // Verify
        SmartAssert.assertEquals(this, currentOverviewOfCoveragePage.eksternKildeId.getAttribute("value"), relationToID,
                errorMsg(relationToID, currentOverviewOfCoveragePage.eksternKildeId.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfCoveragePage.bogfoeringsdato.getAttribute("value"),
                accountingDate, errorMsg(accountingDate, currentOverviewOfCoveragePage.bogfoeringsdato.getText()));
        SmartAssert.assertTrue(this,
                currentOverviewOfCoveragePage.indbetalingsbeloeb.getAttribute("value").contains(amount),
                errorMsgContains(amount, currentOverviewOfCoveragePage.indbetalingsbeloeb.getText()));
        SmartAssert.assertTrue(this,
                currentOverviewOfCoveragePage.indbetalingsbeloeb.getAttribute("value").substring(0, 1).equals("-"),
                errorMsgContains("-" + amount, currentOverviewOfCoveragePage.indbetalingsbeloeb.getText()));
        SmartAssert.assertEquals(this, currentOverviewOfCoveragePage.eksterntReferenceId.getAttribute("value"),
                externalReferenceId,
                errorMsg(externalReferenceId, currentOverviewOfCoveragePage.eksterntReferenceId.getText()));

        doLogout();

        System.out.println("     * Login PSRM. Click \"Menu\" -> \"Opgave\" -> \"Opgave Overblik\". Find\n" +
                "     * Opgavetype = \"Ophaev indbetaling, Oprettet\" and Relation til ID = \"BS\".\n" +
                "     * Click on the link \"Ophaev indbetaling, Oprettet\" and verify: - Opgavetype\n" +
                "     * = \"DK-PDCLE\" - Rolle = \"DK-PAYCASW\" - Beskrivelse = \"Besked fra NETS om\n" +
                "     * daekningsloes indbetaling: Skal ophaeves: 1. Fremfind den oprindelige\n" +
                "     * indbetaling som skal ophaeves med aarsagskode A (daekningsloes). 2. Den\n" +
                "     * daekningsloese indbetaling annulleres (Cancel) 3. Opgaven lukkes.\" Click\n" +
                "     * on the link \"Ophaev indbetaling (88888, 91)\" Validate payment fields,\n" +
                "     * according to the file. Make sure that \"Indbetalingsbeloeb\" has a negative\n" +
                "     * amount. Logout PSRM.");

    }

    private String errorMsg(String a, String b) {
        return "Exptected " + a + " but found " + b;
    }

    private String errorMsgContains(String a, String b) {
        return "Expected " + a + " to be contained in " + b;
    }

}
