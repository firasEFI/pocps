package smoketests.smoke;

/**
 * Created by asol on 28-08-2017.
 */
public class PaymentAbilityTests {

    /**
     * Step 1: Click on the Menu tab and choose 360 Degree Search
     * Step 2: Test that Search By: Debtors and Claimants appears as it should.
     * Step 3: Test that Search By: Case works as it should.
     * Step 4: Test that Search By: Agents works as it should.
     * Step 5: Test that Search By: Document works as it should.
     * Step 6: Test that Search By: Claim works as it should.
     * Final Step: Logout and exit PSRM
     */
/*    @Test//(skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    public void testT001_PaymentAbilityALoen() {

        String accountID = "0100000005";

        final EventFiringWebDriver driver = LocalDriverManager.getDriver();

        Navi.loginPSRM(driver);

        Navi.openCaseManagementCaseStep(driver);

        CaseManagementPage currentCaseManagementPage = new CaseManagementPage(driver);

        currentCaseManagementPage.payplanType().selectByValue("PAYMENTABILITY");

        currentCaseManagementPage.clickOK();

        InformationForCasePage currentInformationForCasePage = new InformationForCasePage(driver);

        currentInformationForCasePage.accountId().sendKeys(accountID);

        currentInformationForCasePage.calcMethod().selectByValue("A2");

        currentInformationForCasePage.incomeRegFreq().selectByValue("M");

    }*/

}
