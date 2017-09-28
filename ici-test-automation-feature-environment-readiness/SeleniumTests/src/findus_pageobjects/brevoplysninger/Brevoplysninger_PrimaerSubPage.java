package findus_pageobjects.brevoplysninger;

import findus_pageobjects.wizards.fordringer.FordringerWizardPage;
import org.openqa.selenium.By;

import findus_pageobjects.PopupWindowResolver;
import findus_pageobjects.SubPage;
import findus_pageobjects.wizards.kundekontakt.KundekontaktWizardPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.frames.Frames;
import icisel.testng.PropertyProvider;
import icisel.utils.driver.Engine;
import utils.batchJob.BatchJobRunner;
import utils.batchJob.BatchJobType;

public class Brevoplysninger_PrimaerSubPage extends SubPage<BrevoplysningerPage> {

    final Button btnSeUdkast = new Button(Frames.zoneMapFrame_1, By.id("fetchDraftID"));
    final Button btnRediger = new Button(Frames.zoneMapFrame_1,
            By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr/td[1]/input"));
    final Button btnGenererUdkast = new Button(Frames.zoneMapFrame_1,
            By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr/td[5]/input"));
    final Button btnAnnuler = new Button(Frames.zoneMapFrame_1,
            By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr/td[6]/input"));
    final Button btnGodkendUdkast = new Button(Frames.zoneMapFrame_1,
            By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr/td[5]/input"));
    final Button btnSendTilAogD = new Button(Frames.zoneMapFrame_1,
            By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr/td[5]/input"));
    final Button btnKasserUdkast = new Button(Frames.zoneMapFrame_1,
            By.xpath("/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr/td[6]/input"));
    final Button btnHaandterFordringer = new Button(Frames.zoneMapFrame_1,
            By.xpath("//input[@value='Håndter fordringer']"));

    public Brevoplysninger_PrimaerSubPage() {
        super(new BrevoplysningerPage());
    }

    protected Brevoplysninger_PrimaerSubPage(BrevoplysningerPage parentPage) {
        super(parentPage);
    }

    public KundekontaktWizardPage activateRediger() {
        this.btnRediger.click();

        return new KundekontaktWizardPage();
    }

    public Brevoplysninger_PrimaerSubPage activateGenererUdkast() {
        this.btnGenererUdkast.click();

        return this;
    }

    public Brevoplysninger_PrimaerSubPage activateGodkendUdkast() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.btnGodkendUdkast.click();
        // TODO Return popup instead
        return this;
    }

    public Brevoplysninger_PrimaerSubPage activateSeUdkast() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.btnSeUdkast.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String mainWindow = Engine.getDriver().getWindowHandle();

        for (String winHandle : Engine.getDriver().getWindowHandles()) {
            Engine.getDriver().switchTo().window(winHandle);
            System.out.println("\n" + winHandle);
        }
        Engine.getDriver().close();

        System.out.println(mainWindow);

        Engine.getDriver().switchTo().window(mainWindow);

        return this;
    }

    public Brevoplysninger_PrimaerSubPage activateSendTilAogD() {
        this.btnSendTilAogD.click();

        return this;
    }

    public Brevoplysninger_PrimaerSubPage runBatchJob(BatchJobType job, PropertyProvider pp) {
        new BatchJobRunner(pp).run(job);
        return this;
    }

    public FordringerWizardPage activateHaandterFordringer(){
        btnHaandterFordringer.click();
        return new FordringerWizardPage();
    }
}
