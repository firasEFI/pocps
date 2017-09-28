package smoketests.smoke;

import icisel.utils.driver.LocalDriverManager;
import icisel.utils.driver.RetryAnalyzer;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;
import pageobjects.smoketests._360degreeview._360CaseworkPage;
import pageobjects.smoketests._360degreeview._360PersonPage;
import pageobjects.smoketests._360degreeview._360PersonsListPage;
import pageobjects.smoketests._360degreeview._360TabPage;
import pageobjects.smoketests.payments.*;
import pageobjects.smoketests.psrm_navigation.Navi;

/**
 * Created by asol on 08-08-2017.
 */
public class PaymentTests {

    /**
     * Login PSRM. Click ”Menu” ->”360 graders soegning”. Search for CPR-nr:
     * 0505754018 (Jensen, Caja). Open the "Skyldner" using the name link
     * resulting from the search. Click on the tab "Sagsbehandling". Open the
     * zone "Betalingshaendelser for kontoen". Click on the context menu for the
     * "Betalingshaendelses ID" link, and click "Overblik over betaling". Verify
     * payment fields according to the payment file. Logout PSRM.
     */
    @Test(skipFailedInvocations = true, retryAnalyzer = RetryAnalyzer.class)
    public void testT001_CREMUL_OCR_FIK() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        Navi.loginPSRM(driver);

        System.out.println("Running testT001_CREMUL_OCR_FIK");

        // Test data
        String deptorID = "0100000020";
        String cprNumber = "0505754018";
        String personFirstName = "Caja";
        String personLastName = "Jensen";
        String personFullName = personLastName + ", " + personFirstName;
        final String personFullNameRightOrder = personFirstName + " " + personLastName;
        String paymentAmount = "11.500,23kr.";

        Navi.open360DegreeSearch(driver);

        _360TabPage current_360TabPage = new _360TabPage(driver);

        current_360TabPage.selectDropdown(_360TabPage.DROP_PERSON);

        _360PersonPage current_360PersonPage = new _360PersonPage(driver);

        current_360PersonPage.searchForID(_360PersonPage.ID_TYPE_CPR, cprNumber);

        _360PersonsListPage current_360PersonsListPage = new _360PersonsListPage(driver);

        current_360PersonsListPage.findPersonAndClickNameLink(personFullNameRightOrder);

        Navi.openTaxRole(driver);

        _360CaseworkPage current_360CaseworkPage = new _360CaseworkPage(driver);

        current_360CaseworkPage.clickPaymentsForAccount();
        current_360CaseworkPage.searchAndClickContextForPayment(paymentAmount, personFullName);
        current_360CaseworkPage.clickOverviewOfPayment();

        OverviewOfPaymentPage currentOverviewOfPaymentPage = new OverviewOfPaymentPage(driver);

        // Check that the payment is as expected.

        // Personal details
        assert currentOverviewOfPaymentPage.debtorID().getText().equals(deptorID);
        assert currentOverviewOfPaymentPage.cprNumber().getText().equals(cprNumber);
        assert currentOverviewOfPaymentPage.debtorName().getText().equals(personFullNameRightOrder);
        assert currentOverviewOfPaymentPage.account().getText().contains(personFullName);
        assert currentOverviewOfPaymentPage.paymentID().getText().contains(paymentAmount);
        assert currentOverviewOfPaymentPage.paymentID().getText().contains(personFullName);

        // Payment distribution details
        assert currentOverviewOfPaymentPage.pdAmount().getText().equals(paymentAmount);

        // Source of payment details.
        assert currentOverviewOfPaymentPage.socPaymentAmount().getText().equals(paymentAmount);

        // Payments details
        // assert
        // currentOverviewOfPaymentPage.paymentInfo().getText().contains(paymentAmount);
        assert currentOverviewOfPaymentPage.paymentInfo().getText().contains(personFullName);
        // assert
        // currentOverviewOfPaymentPage.paymentAmount().getText().equals(paymentAmount);

        Navi.logoutPSRM(driver);

        System.out.println("     * Login PSRM. Click ”Menu” ->”360 graders soegning”. Search for CPR-nr:\n" +
                "     * 0505754018 (Jensen, Caja). Open the \"Skyldner\" using the name link\n" +
                "     * resulting from the search. Click on the tab \"Sagsbehandling\". Open the\n" +
                "     * zone \"Betalingshaendelser for kontoen\". Click on the context menu for the\n" +
                "     * \"Betalingshaendelses ID\" link, and click \"Overblik over betaling\". Verify\n" +
                "     * payment fields according to the payment file. Logout PSRM.");
    }

    /**
     * Login PSRM. Click "Menu" -> "Opgave" -> "Opgave Overblik". Find
     * Opgavetype = "Placer indbetaling fra ventekonto, Oprettet" and Relation
     * til ID = "TM". Click on the link "Placer indbetaling fra ventekonto,
     * Oprettet" and verify: - Opgavetype = "DK-PDWAC" - Rolle = "DK-PAYCASW" -
     * Beskrivelse = "Indbetalingen er blevet placeret paa en ventekonto for
     * indbetalingsarten. (1) Bankoverfoersel uden OCR-linje, (2) Tredjemands
     * sat paa ventekonto, eller (3) Udenlandsk bankoverfoersel uden OCR-linje.
     * Tag stilling til hvor indbetalingen nu skal placeres." Click on the link
     * "Placer indbetaling fra ventekonto (88888, 78)". Validate payment fields,
     * according to the file. Note down the "Betalingshaendelses ID". Click
     * "Menu" -> "Indbetalinger" -> "Placer indbetaling fra ventekonto" Select
     * Konto, insert the "Betalingshaendelses ID" in the "Indbetalingshaendelses
     * ID fra" field, choose "Virkningsdato", choose a "Skyldner konto" (Note
     * CPR-nr), Daekningsregel = "Via skyldner konto", aarsag = "Anden", Anden
     * aarsag = "Test". Click "Gem". Go to ”Menu” ->”360 graders soegning”.
     * Search for the selected "Skyldner" CPR-nr. Open the "Skyldner" using the
     * name link resulting from the search. Click on the tab "Sagsbehandling".
     * Open the zone "Betalingshaendelser for kontoen" and verify that the
     * payment has been created for the "Skyldner". Logout PSRM.
     */
    //@Test(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    //@Test
    public void testT002_CREMUL_TM() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        Navi.loginPSRM(driver);

        System.out.println("Running testT002_CREMUL_TM");

        // Test data
        String todoTypeText = "Placer indbetaling fra ventekonto, Oprettet";
        String relationToID = "TM";
        String todoTypeID = "DK-PDWAC";
        String role = "DK-PAYCASW";
        String description = "Indbetalingen er blevet placeret på en ventekonto for indbetalingsarten. (1) Bankoverførsel uden OCR-linje, (2) Tredjemands sat på ventekonto, eller (3) Udenlandsk bankoverførsel uden OCR-linje. Tag stilling til hvor indbetalingen nu skal placeres.";
        String amount = "500,00kr.";
        String externalReferenceID = "FOCUS ADVOKATER P/S";
        String debtorAccountID = "0100000007";
        String cprNumber = "0803734279";
        String personFirstName = "Bjarke";
        String personLastName = "Nordentoft";
        String personFullName = personLastName + ", " + personFirstName;
        final String personFullNameRightOrder = personFirstName + " " + personLastName;

        Navi.openTaskOverview(driver);

        TodoOverviewPage currentTodoOverviewPage = new TodoOverviewPage(driver);

        currentTodoOverviewPage.searchAndClickTodo(todoTypeText, relationToID);

        TodoPage currentTodoPage = new TodoPage(driver);

        assert currentTodoPage.todoTypeID().getText().equals(todoTypeID);
        assert currentTodoPage.role().getText().equals(role);
        assert currentTodoPage.comments().getAttribute("value").equals(description);

        currentTodoPage.clickLinkForDetails();

        OverviewOfCoveragePage currentOverviewOfCoveragePage = new OverviewOfCoveragePage(driver);

        // Verify the payment
        assert currentOverviewOfCoveragePage.externalSourceID().getAttribute("value").equals(relationToID);
        assert currentOverviewOfCoveragePage.paymentAmount().getAttribute("value").equals(amount);
        assert currentOverviewOfCoveragePage.externalReferenceID().getAttribute("value").equals(externalReferenceID);

        String accountID = currentOverviewOfCoveragePage.accountID().getAttribute("value");
        String paymentEventID = currentOverviewOfCoveragePage.paymentID().getText();
        String effectiveDate = currentOverviewOfCoveragePage.accountingDate().getAttribute("value");

        Navi.openMovePaymentFromWaitingaccount(driver);

        PlacePaymentFromWaitingaccountPage currentPlacePaymentFromWaitingaccountpage = new PlacePaymentFromWaitingaccountPage(
                driver);

        currentPlacePaymentFromWaitingaccountpage.searchAccountID(accountID);
        currentPlacePaymentFromWaitingaccountpage.searchPaymentEventID(paymentEventID);
        currentPlacePaymentFromWaitingaccountpage.searchEffectiveDate(effectiveDate);
        currentPlacePaymentFromWaitingaccountpage.selectReason(PlacePaymentFromWaitingaccountPage.OTH);
        currentPlacePaymentFromWaitingaccountpage.otherReason().sendKeys("Test");
        //currentPlacePaymentFromWaitingaccountpage.transferType().selectByValue("EXT");
        currentPlacePaymentFromWaitingaccountpage.chooseDebtorByID(debtorAccountID);
        currentPlacePaymentFromWaitingaccountpage.selectDistributionRule(pageobjects.smoketests.payments.PlacePaymentFromWaitingaccountPage.PAYACC);
        currentPlacePaymentFromWaitingaccountpage.selectReason(pageobjects.smoketests.payments.PlacePaymentFromWaitingaccountPage.OTH);
        currentPlacePaymentFromWaitingaccountpage.clickSave();

        Navi.open360DegreeSearch(driver);

        // Select type of person.
        _360TabPage current_360TabPage = new _360TabPage(driver);

        current_360TabPage.selectDropdown(_360TabPage.DROP_PERSON);

        // Search for person based on CPR-number.
        _360PersonPage current_360PersonPage = new _360PersonPage(driver);

        current_360PersonPage.searchForID(_360PersonPage.ID_TYPE_CPR, cprNumber);

        // Click on the person's name.
        _360PersonsListPage current_360PersonsListPage = new _360PersonsListPage(driver);

        current_360PersonsListPage.findPersonAndClickNameLink(personFullNameRightOrder);

        // Click on the "Sagsbehandling" tab.
        Navi.openTaxRole(driver);

        // Click on "Betalingshaendelser for kontoen".
        _360CaseworkPage current_360CaseworkPage = new _360CaseworkPage(driver);

        current_360CaseworkPage.clickPaymentsForAccount();
        current_360CaseworkPage.searchAndClickContextForPayment(effectiveDate, amount, personFullName);
        current_360CaseworkPage.clickOverviewOfPayment();

        // Verify payment for the debtor.
        OverviewOfPaymentPage currentOverviewOfPaymentPage = new OverviewOfPaymentPage(driver);

        // Primary details
        assert currentOverviewOfPaymentPage.debtorID().getText().equals(debtorAccountID);
        assert currentOverviewOfPaymentPage.cprNumber().getText().equals(cprNumber);
        assert currentOverviewOfPaymentPage.debtorName().getText().equals(personFullNameRightOrder);
        assert currentOverviewOfPaymentPage.account().getText().contains(personFullName);
        assert currentOverviewOfPaymentPage.paymentID().getText().contains(effectiveDate);
        assert currentOverviewOfPaymentPage.paymentID().getText().contains(amount);
        assert currentOverviewOfPaymentPage.paymentID().getText().contains(personFullName);

        // Payment distribution details
        assert currentOverviewOfPaymentPage.pdAmount().getText().equals(amount);
        assert currentOverviewOfPaymentPage.pdCharacteristicFKInfo().getText().contains(personFullName);

        // Source of payment details
        assert currentOverviewOfPaymentPage.socPaymentAmount().getText().equals(amount);

        // Payments details
        assert currentOverviewOfPaymentPage.paymentInfo().getText().contains(effectiveDate);
        assert currentOverviewOfPaymentPage.paymentInfo().getText().contains(amount);
        assert currentOverviewOfPaymentPage.paymentInfo().getText().contains(personFullName);
        assert currentOverviewOfPaymentPage.paymentAmount().getText().equals(amount);

        Navi.logoutPSRM(driver);

        System.out.println("* Login PSRM. Click \"Menu\" -> \"Opgave\" -> \"Opgave Overblik\". Find\n" +
                "     * Opgavetype = \"Placer indbetaling fra ventekonto, Oprettet\" and Relation\n" +
                "     * til ID = \"TM\". Click on the link \"Placer indbetaling fra ventekonto,\n" +
                "     * Oprettet\" and verify: - Opgavetype = \"DK-PDWAC\" - Rolle = \"DK-PAYCASW\" -\n" +
                "     * Beskrivelse = \"Indbetalingen er blevet placeret paa en ventekonto for\n" +
                "     * indbetalingsarten. (1) Bankoverfoersel uden OCR-linje, (2) Tredjemands\n" +
                "     * sat paa ventekonto, eller (3) Udenlandsk bankoverfoersel uden OCR-linje.\n" +
                "     * Tag stilling til hvor indbetalingen nu skal placeres.\" Click on the link\n" +
                "     * \"Placer indbetaling fra ventekonto (88888, 78)\". Validate payment fields,\n" +
                "     * according to the file. Note down the \"Betalingshaendelses ID\". Click\n" +
                "     * \"Menu\" -> \"Indbetalinger\" -> \"Placer indbetaling fra ventekonto\" Select\n" +
                "     * Konto, insert the \"Betalingshaendelses ID\" in the \"Indbetalingshaendelses\n" +
                "     * ID fra\" field, choose \"Virkningsdato\", choose a \"Skyldner konto\" (Note\n" +
                "     * CPR-nr), Daekningsregel = \"Via skyldner konto\", aarsag = \"Anden\", Anden\n" +
                "     * aarsag = \"Test\". Click \"Gem\". Go to ”Menu” ->”360 graders soegning”.\n" +
                "     * Search for the selected \"Skyldner\" CPR-nr. Open the \"Skyldner\" using the\n" +
                "     * name link resulting from the search. Click on the tab \"Sagsbehandling\".\n" +
                "     * Open the zone \"Betalingshaendelser for kontoen\" and verify that the\n" +
                "     * payment has been created for the \"Skyldner\". Logout PSRM.");
    }

    /**
     * Login PSRM. Click "Menu" -> "Opgave" -> "Opgave Overblik". Find
     * Opgavetype = "Placer indbetaling fra ventekonto, Oprettet" and Relation
     * til ID = "BNKU". Click on the link "Placer indbetaling fra ventekonto,
     * Oprettet" and verify: - Opgavetype = "DK-PDWAC" - Rolle = "DK-PAYCASW" -
     * Beskrivelse = "Indbetalingen er blevet placeret paa en ventekonto for
     * indbetalingsarten. (1) Bankoverfoersel uden OCR-linje, (2) Tredjemands
     * sat paa ventekonto, eller (3) Udenlandsk bankoverfoersel uden OCR-linje.
     * Tag stilling til hvor indbetalingen nu skal placeres." Click on the link
     * "Placer indbetaling fra ventekonto (88888, 78)". Validate payment fields,
     * according to the file. Note down the "Betalingshaendelses ID". Click
     * "Menu" -> "Indbetalinger" -> "Overfoer indbetaling fra ventekonto" Select
     * Konto, insert the "Betalingshaendelses ID" in the "Indbetalingshaendelses
     * ID fra" field, choose "Virkningsdato", choose a "Skyldner konto" (Note
     * CPR-nr), Daekningsregel = "Via skyldner konto", aarsag = "Anden", Anden
     * aarsag = "Test". Click "Gem". Go to ”Menu” ->”360 graders soegning”.
     * Search for the selected "Skyldner" account-nr. Open the "Skyldner" using
     * the name link resulting from the search. Click on the tab
     * "Sagsbehandling". Open the zone "Betalingshaendelser for kontoen" and
     * verify that the payment has been created for the "Skyldner". Logout PSRM.
     */
    @Test(skipFailedInvocations = true, retryAnalyzer = RetryAnalyzer.class)
    public void testT003_CREMUL_BNKU() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        Navi.loginPSRM(driver);

        System.out.println("Running testT003_CREMUL_BNKU");

        // Test data
        String todoTypeText = "Placer indbetaling fra ventekonto, Oprettet";
        String relationToID = "BNKU";
        String todoTypeID = "DK-PDWAC";
        String role = "DK-PAYCASW";
        String description = "Indbetalingen er blevet placeret på en ventekonto for indbetalingsarten. (1) Bankoverførsel uden OCR-linje, (2) Tredjemands sat på ventekonto, eller (3) Udenlandsk bankoverførsel uden OCR-linje. Tag stilling til hvor indbetalingen nu skal placeres.";
        String amount = "1.000,00kr.";
        String externalReferenceID = "Betal. 3825-0041915568";
        String debtorAccountID = "0100000007";
        String cprNumber = "0803734279";
        String personFirstName = "Bjarke";
        String personLastName = "Nordentoft";
        String personFullName = personLastName + ", " + personFirstName;
        final String personFullNameRightOrder = personFirstName + " " + personLastName;

        Navi.openTaskOverview(driver);

        TodoOverviewPage currentTodoOverviewPage = new TodoOverviewPage(driver);

        currentTodoOverviewPage.searchAndClickTodo(todoTypeText, relationToID);

        TodoPage currentTodoPage = new TodoPage(driver);

        assert currentTodoPage.todoTypeID().getText().equals(todoTypeID);
        assert currentTodoPage.role().getText().equals(role);
        assert currentTodoPage.comments().getAttribute("value").equals(description);

        currentTodoPage.clickLinkForDetails();

        OverviewOfCoveragePage currentOverviewOfCoveragePage = new OverviewOfCoveragePage(driver);

        // Verify
        assert currentOverviewOfCoveragePage.externalSourceID().getAttribute("value").equals(relationToID);
        assert currentOverviewOfCoveragePage.paymentAmount().getAttribute("value").equals(amount);
        assert currentOverviewOfCoveragePage.externalReferenceID().getAttribute("value").equals(externalReferenceID);

        String accountID = currentOverviewOfCoveragePage.accountID().getAttribute("value");
        String paymentEventID = currentOverviewOfCoveragePage.paymentID().getText();
        String effectiveDate = currentOverviewOfCoveragePage.accountingDate().getAttribute("value");

        Navi.openMovePaymentFromWaitingaccount(driver);

        PlacePaymentFromWaitingaccountPage currentPlacePaymentFromWaitingaccountpage = new PlacePaymentFromWaitingaccountPage(
                driver);

        currentPlacePaymentFromWaitingaccountpage.searchAccountID(accountID);
        currentPlacePaymentFromWaitingaccountpage.searchPaymentEventID(paymentEventID);
        currentPlacePaymentFromWaitingaccountpage.searchEffectiveDate(effectiveDate);
        currentPlacePaymentFromWaitingaccountpage.selectReason(PlacePaymentFromWaitingaccountPage.OTH);
        currentPlacePaymentFromWaitingaccountpage.otherReason().sendKeys("test");
        //currentPlacePaymentFromWaitingaccountpage.transferType().selectByValue("EXT");
        currentPlacePaymentFromWaitingaccountpage.chooseDebtorByID(debtorAccountID);
        currentPlacePaymentFromWaitingaccountpage.selectDistributionRule(pageobjects.smoketests.payments.PlacePaymentFromWaitingaccountPage.PAYACC);
        currentPlacePaymentFromWaitingaccountpage.selectReason(pageobjects.smoketests.payments.PlacePaymentFromWaitingaccountPage.OTH);
        currentPlacePaymentFromWaitingaccountpage.clickSave();

        Navi.open360DegreeSearch(driver);

        // Select type of person.
        _360TabPage current_360TabPage = new _360TabPage(driver);

        current_360TabPage.selectDropdown(_360TabPage.DROP_PERSON);

        // Search for person based on CPR-number.
        _360PersonPage current_360PersonPage = new _360PersonPage(driver);

        current_360PersonPage.searchForID(_360PersonPage.ID_TYPE_CPR, cprNumber);

        // Click on the person's name.
        _360PersonsListPage current_360PersonsListPage = new _360PersonsListPage(driver);

        current_360PersonsListPage.findPersonAndClickNameLink(personFullNameRightOrder);

        // Click on the "Sagsbehandling" tab.
        Navi.openTaxRole(driver);

        // Click on "Betalingshaendelser for kontoen".
        _360CaseworkPage current_360CaseworkPage = new _360CaseworkPage(driver);

        current_360CaseworkPage.clickPaymentsForAccount();
        current_360CaseworkPage.searchAndClickContextForPayment(effectiveDate, amount, personFullName);
        current_360CaseworkPage.clickOverviewOfPayment();

        // Verify payment for the debtor
        OverviewOfPaymentPage currentOverviewOfPaymentPage = new OverviewOfPaymentPage(driver);

        // Primary details
        assert currentOverviewOfPaymentPage.debtorID().getText().equals(debtorAccountID);
        assert currentOverviewOfPaymentPage.cprNumber().getText().equals(cprNumber);
        assert currentOverviewOfPaymentPage.debtorName().getText().equals(personFullNameRightOrder);
        assert currentOverviewOfPaymentPage.account().getText().contains(personFullName);
        assert currentOverviewOfPaymentPage.paymentID().getText().contains(effectiveDate);
        assert currentOverviewOfPaymentPage.paymentID().getText().contains(amount);
        assert currentOverviewOfPaymentPage.paymentID().getText().contains(personFullName);

        // Payment distribution details
        assert currentOverviewOfPaymentPage.pdAmount().getText().equals(amount);
        assert currentOverviewOfPaymentPage.pdCharacteristicFKInfo().getText().contains(personFullName);

        // Source of payment details
        assert currentOverviewOfPaymentPage.socPaymentAmount().getText().equals(amount);

        // Payments details
        assert currentOverviewOfPaymentPage.paymentInfo().getText().contains(effectiveDate);
        assert currentOverviewOfPaymentPage.paymentInfo().getText().contains(amount);
        assert currentOverviewOfPaymentPage.paymentInfo().getText().contains(personFullName);
        assert currentOverviewOfPaymentPage.paymentAmount().getText().equals(amount);

        Navi.logoutPSRM(driver);

        System.out.println("     * Login PSRM. Click \"Menu\" -> \"Opgave\" -> \"Opgave Overblik\". Find\n" +
                "     * Opgavetype = \"Placer indbetaling fra ventekonto, Oprettet\" and Relation\n" +
                "     * til ID = \"BNKU\". Click on the link \"Placer indbetaling fra ventekonto,\n" +
                "     * Oprettet\" and verify: - Opgavetype = \"DK-PDWAC\" - Rolle = \"DK-PAYCASW\" -\n" +
                "     * Beskrivelse = \"Indbetalingen er blevet placeret paa en ventekonto for\n" +
                "     * indbetalingsarten. (1) Bankoverfoersel uden OCR-linje, (2) Tredjemands\n" +
                "     * sat paa ventekonto, eller (3) Udenlandsk bankoverfoersel uden OCR-linje.\n" +
                "     * Tag stilling til hvor indbetalingen nu skal placeres.\" Click on the link\n" +
                "     * \"Placer indbetaling fra ventekonto (88888, 78)\". Validate payment fields,\n" +
                "     * according to the file. Note down the \"Betalingshaendelses ID\". Click\n" +
                "     * \"Menu\" -> \"Indbetalinger\" -> \"Overfoer indbetaling fra ventekonto\" Select\n" +
                "     * Konto, insert the \"Betalingshaendelses ID\" in the \"Indbetalingshaendelses\n" +
                "     * ID fra\" field, choose \"Virkningsdato\", choose a \"Skyldner konto\" (Note\n" +
                "     * CPR-nr), Daekningsregel = \"Via skyldner konto\", aarsag = \"Anden\", Anden\n" +
                "     * aarsag = \"Test\". Click \"Gem\". Go to ”Menu” ->”360 graders soegning”.\n" +
                "     * Search for the selected \"Skyldner\" account-nr. Open the \"Skyldner\" using\n" +
                "     * the name link resulting from the search. Click on the tab\n" +
                "     * \"Sagsbehandling\". Open the zone \"Betalingshaendelser for kontoen\" and\n" +
                "     * verify that the payment has been created for the \"Skyldner\". Logout PSRM.");
    }

    /**
     * Login PSRM. Click "Menu" -> "Opgave" -> "Opgave Overblik". Find
     * Opgavetype = "Placer indbetaling fra ventekonto, Oprettet" and Relation
     * til ID = "BNK". Click on the link "Placer indbetaling fra ventekonto,
     * Oprettet" and verify: - Opgavetype = "DK-PDWAC" - Rolle = "DK-PAYCASW" -
     * Beskrivelse = "Indbetaling er modtaget fra tredjemand, der har anfoert
     * virkningsdato og skyldner (eller skyldnere). Placér indbetaling paa den
     * anfoerte skyldner Selenium test" Click on the link "Placer indbetaling
     * fra ventekonto (88888, 78)". Validate payment fields, according to the
     * file. Note down the "Betalingshaendelses ID". Click "Menu" ->
     * "Indbetalinger" -> "Placer indbetaling fra ventekonto" Select Konto,
     * insert the "Betalingshaendelses ID" in the "Indbetalingshaendelses ID
     * fra" field, choose "Virkningsdato", choose a "Skyldner konto" (Note
     * CPR-nr), Daekningsregel = "Via skyldner konto", aarsag = "Anden", Anden
     * aarsag = "Test". Click "Gem". Go to ”Menu” ->”360 graders soegning”.
     * Search for the selected "Skyldner" CPR-nr. Open the "Skyldner" using the
     * name link resulting from the search. Click on the tab "Sagsbehandling".
     * Open the zone "Betalingshaendelser for kontoen" and verify that the
     * payment has been created for the "Skyldner". Logout PSRM.
     */
    @Test(skipFailedInvocations = true, retryAnalyzer = RetryAnalyzer.class)
    public void testT004_CREMUL_BNK() {

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        Navi.loginPSRM(driver);

        System.out.println("Running testT004_CREMUL_BNK");

        // Test data
        String todoTypeText = "Placer indbetaling fra ventekonto, Oprettet";
        String relationToID = "BNK";
        String todoTypeID = "DK-PDWAC";
        String role = "DK-PAYCASW";
        String description = "Indbetaling er modtaget fra tredjemand, der har anført virkningsdato og skyldner (eller skyldnere). Placér indbetaling på den anførte skyldner Selenium test";
        String amount = "999,00kr.";
        String externalReferenceID = "KxBENHAVNS POLITI";
        String debtorAccountID = "0100000007";
        String cprNumber = "0803734279";
        String personFirstName = "Bjarke";
        String personLastName = "Nordentoft";
        String personFullName = personLastName + ", " + personFirstName;
        final String personFullNameRightOrder = personFirstName + " " + personLastName;

        Navi.openTaskOverview(driver);

        TodoOverviewPage currentTodoOverviewPage = new TodoOverviewPage(driver);

        currentTodoOverviewPage.searchAndClickTodo(todoTypeText, relationToID);

        TodoPage currentTodoPage = new TodoPage(driver);

        assert currentTodoPage.todoTypeID().getText().equals(todoTypeID);
        assert currentTodoPage.role().getText().equals(role);
        assert currentTodoPage.comments().getAttribute("value").equals(description);

        currentTodoPage.clickLinkForDetails();

        OverviewOfCoveragePage currentOverviewOfCoveragePage = new OverviewOfCoveragePage(driver);

        // Verify the payment
        assert currentOverviewOfCoveragePage.externalSourceID().getAttribute("value").equals(relationToID);
        assert currentOverviewOfCoveragePage.paymentAmount().getAttribute("value").equals(amount);
        assert currentOverviewOfCoveragePage.externalReferenceID().getAttribute("value").equals(externalReferenceID);

        String accountID = currentOverviewOfCoveragePage.accountID().getAttribute("value");
        String paymentEventID = currentOverviewOfCoveragePage.paymentID().getText();
        String effectiveDate = currentOverviewOfCoveragePage.accountingDate().getAttribute("value");

        Navi.openMovePaymentFromWaitingaccount(driver);

        PlacePaymentFromWaitingaccountPage currentPlacePaymentFromWaitingaccountpage = new PlacePaymentFromWaitingaccountPage(
                driver);

        currentPlacePaymentFromWaitingaccountpage.searchAccountID(accountID);
        currentPlacePaymentFromWaitingaccountpage.searchPaymentEventID(paymentEventID);
        currentPlacePaymentFromWaitingaccountpage.searchEffectiveDate(effectiveDate);
        currentPlacePaymentFromWaitingaccountpage.selectReason(PlacePaymentFromWaitingaccountPage.OTH);
        currentPlacePaymentFromWaitingaccountpage.otherReason().sendKeys("Test");
        //currentPlacePaymentFromWaitingaccountpage.transferType().selectByValue("EXT");
        currentPlacePaymentFromWaitingaccountpage.chooseDebtorByID(debtorAccountID);
        currentPlacePaymentFromWaitingaccountpage.selectDistributionRule(pageobjects.smoketests.payments.PlacePaymentFromWaitingaccountPage.PAYACC);
        currentPlacePaymentFromWaitingaccountpage.selectReason(pageobjects.smoketests.payments.PlacePaymentFromWaitingaccountPage.OTH);
        currentPlacePaymentFromWaitingaccountpage.clickSave();

        Navi.open360DegreeSearch(driver);

        // Select type of person.
        _360TabPage current_360TabPage = new _360TabPage(driver);

        current_360TabPage.selectDropdown(_360TabPage.DROP_PERSON);

        // Search for person based on CPR-number.
        _360PersonPage current_360PersonPage = new _360PersonPage(driver);

        current_360PersonPage.searchForID(_360PersonPage.ID_TYPE_CPR, cprNumber);

        // Click on the person's name.
        _360PersonsListPage current_360PersonsListPage = new _360PersonsListPage(driver);

        current_360PersonsListPage.findPersonAndClickNameLink(personFullNameRightOrder);

        // Click on the "Sagsbehandling" tab.
        Navi.openTaxRole(driver);

        // Click on "Betalingshaendelser for kontoen".
        _360CaseworkPage current_360CaseworkPage = new _360CaseworkPage(driver);

        current_360CaseworkPage.clickPaymentsForAccount();
        current_360CaseworkPage.searchAndClickContextForPayment(effectiveDate, amount, personFullName);
        current_360CaseworkPage.clickOverviewOfPayment();

        // Verify payment for the debtor.
        OverviewOfPaymentPage currentOverviewOfPaymentPage = new OverviewOfPaymentPage(driver);

        // Primary details
        assert currentOverviewOfPaymentPage.debtorID().getText().equals(debtorAccountID);
        assert currentOverviewOfPaymentPage.cprNumber().getText().equals(cprNumber);
        assert currentOverviewOfPaymentPage.debtorName().getText().equals(personFullNameRightOrder);
        assert currentOverviewOfPaymentPage.account().getText().contains(personFullName);
        assert currentOverviewOfPaymentPage.paymentID().getText().contains(effectiveDate);
        assert currentOverviewOfPaymentPage.paymentID().getText().contains(amount);
        assert currentOverviewOfPaymentPage.paymentID().getText().contains(personFullName);

        // Payment distribution details
        assert currentOverviewOfPaymentPage.pdAmount().getText().equals(amount);
        assert currentOverviewOfPaymentPage.pdCharacteristicFKInfo().getText().contains(personFullName);

        // Source of payment details
        assert currentOverviewOfPaymentPage.socPaymentAmount().getText().equals(amount);

        // Payments details
        assert currentOverviewOfPaymentPage.paymentInfo().getText().contains(effectiveDate);
        assert currentOverviewOfPaymentPage.paymentInfo().getText().contains(amount);
        assert currentOverviewOfPaymentPage.paymentInfo().getText().contains(personFullName);
        assert currentOverviewOfPaymentPage.paymentAmount().getText().equals(amount);

        Navi.logoutPSRM(driver);

        System.out.println("     * Login PSRM. Click \"Menu\" -> \"Opgave\" -> \"Opgave Overblik\". Find\n" +
                "     * Opgavetype = \"Placer indbetaling fra ventekonto, Oprettet\" and Relation\n" +
                "     * til ID = \"BNK\". Click on the link \"Placer indbetaling fra ventekonto,\n" +
                "     * Oprettet\" and verify: - Opgavetype = \"DK-PDWAC\" - Rolle = \"DK-PAYCASW\" -\n" +
                "     * Beskrivelse = \"Indbetaling er modtaget fra tredjemand, der har anfoert\n" +
                "     * virkningsdato og skyldner (eller skyldnere). Placér indbetaling paa den\n" +
                "     * anfoerte skyldner Selenium test\" Click on the link \"Placer indbetaling\n" +
                "     * fra ventekonto (88888, 78)\". Validate payment fields, according to the\n" +
                "     * file. Note down the \"Betalingshaendelses ID\". Click \"Menu\" ->\n" +
                "     * \"Indbetalinger\" -> \"Placer indbetaling fra ventekonto\" Select Konto,\n" +
                "     * insert the \"Betalingshaendelses ID\" in the \"Indbetalingshaendelses ID\n" +
                "     * fra\" field, choose \"Virkningsdato\", choose a \"Skyldner konto\" (Note\n" +
                "     * CPR-nr), Daekningsregel = \"Via skyldner konto\", aarsag = \"Anden\", Anden\n" +
                "     * aarsag = \"Test\". Click \"Gem\". Go to ”Menu” ->”360 graders soegning”.\n" +
                "     * Search for the selected \"Skyldner\" CPR-nr. Open the \"Skyldner\" using the\n" +
                "     * name link resulting from the search. Click on the tab \"Sagsbehandling\".\n" +
                "     * Open the zone \"Betalingshaendelser for kontoen\" and verify that the\n" +
                "     * payment has been created for the \"Skyldner\". Logout PSRM.");
    }

}